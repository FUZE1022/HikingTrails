package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewUserController implements Initializable {

    @FXML
    private TextField usernameTf, passwordTf, phoneNumTf;
    @FXML
    private Button createAccountBtn, profilePictureChooserBtn, goBackBtn, testBtn;
    @FXML
    private ImageView profilePictureIv;
    @FXML
    private Label inValidUsername, inValidAccount1, inValidAccount2, inValidAccount3, inValidAccount4, inValidAccount5,
            inValidAccount6, inValidAccount7;
    private FileChooser fileChooser;
    private File file;
    private UserTreeSet userTreeSet = DataCenter.getInstance().getUserTreeSet();
    //private UserTreeMap userTreeMap = DataCenter.getInstance().getUserTreeMap();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        fileChooser.setInitialDirectory(new File("savedProfilePictures"));
    }

    public void test() {
        RootAdmin rootAdmin = new RootAdmin();
        //rootAdmin.promoteToAdmin();
    }

    public void createAccount() {
        UserTreeMap userTree = new UserTreeMap();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation");
        dialog.setHeaderText("Is this the user you'd like to create?");
        dialog.setContentText("Username: " + usernameTf.getText() + "\n" +
                "Password: " + passwordTf.getText() + "\n" +
                "Phone Number: " + phoneNumTf.getText().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3"));

        ButtonType yesButton = new ButtonType("Yes", ButtonType.OK.getButtonData());
        ButtonType noButton = new ButtonType("No", ButtonType.CANCEL.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(yesButton, noButton);

        try {
            dialog.showAndWait().ifPresent(buttonType -> {
                if (buttonType == yesButton) {
                    userTreeSet.addUserToSet(new User(usernameTf.getText(), passwordTf.getText(),
                            phoneNumTf.getText(), file.toURI().toString()));
                    //userTreeMap.addUser(new User(usernameTf.getText(), passwordTf.getText(), phoneNumTf.getText(),
                            //file.toURI().toString()));
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("User created successfully!");
                    successAlert.showAndWait();
                    goBackBtn.fire();
                }
            });
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Make sure you have filled out all fields \n" +
                    "and have chosen a profile picture!");
            errorAlert.showAndWait();
        }
    }

    public void chooseProfilePicture() {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            profilePictureIv.setImage(new Image(file.toURI().toString()));
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/MainView.fxml"));
        Stage stage = new Stage();
        Scene newScene = new Scene(fxmlLoader.load(), 600, 400);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(newScene);
        stage.show();
    }

    public void usernameOnTyped() {
        String username = usernameTf.getText();

        boolean isTaken = userTreeSet.containsUsernameInSet(username);
        boolean showError = isTaken;

        inValidUsername.setVisible(showError);
        usernameTf.setStyle(showError ? "-fx-text-inner-color: red;" : "-fx-text-inner-color: green;");
        createAccountBtn.setDisable(showError);
    }

    public void passwordOnTyped() {
        String password = passwordTf.getText();

        boolean isLengthValid = password.length() >= 8;
        boolean hasSpecialCharacter = password.matches(".*[^a-zA-Z0-9].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasCapitalLetter = password.matches(".*[A-Z].*");

        boolean showError = !isLengthValid || !hasSpecialCharacter || !hasNumber || !hasCapitalLetter;

        inValidAccount1.setVisible(!isLengthValid);
        inValidAccount2.setVisible(!hasSpecialCharacter);
        inValidAccount3.setVisible(!hasNumber);
        inValidAccount4.setVisible(!hasCapitalLetter);

        passwordTf.setStyle(showError ? "-fx-text-inner-color: red;" : "-fx-text-inner-color: green;");
        createAccountBtn.setDisable(showError);
    }

    public void phoneOnTyped() {
        String phone = phoneNumTf.getText();
        boolean isLengthValid = phone.length() == 10;
        boolean hasNumber = phone.matches("[0-9]+");
        boolean isTaken = userTreeSet.containsUserNumberInSet(phone);

        boolean showError = !isLengthValid || !hasNumber || isTaken;

        inValidAccount5.setVisible(!hasNumber);
        inValidAccount6.setVisible(!isLengthValid);
        inValidAccount7.setVisible(isTaken);

        phoneNumTf.setStyle(showError ? "-fx-text-inner-color: red;" : "-fx-text-inner-color: green;");
        createAccountBtn.setDisable(showError);
    }
}
