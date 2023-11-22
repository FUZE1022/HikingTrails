package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.User;
import com.hikingtrails.project2hikingtrails.model.UserSearchTree;
import com.hikingtrails.project2hikingtrails.model.UserSetContainer;
import com.hikingtrails.project2hikingtrails.util.AddToTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class NewUserController {

    @FXML
    private TextField usernameTf, passwordTf, phoneNumTf;
    @FXML
    private Button createAccountBtn, profilePictureChooserBtn, goBackBtn;
    @FXML
    private ImageView profilePictureIv;
    @FXML
    private Label inValidUsername, inValidAccount1, inValidAccount2, inValidAccount3, inValidAccount4;
    private FileChooser fileChooser;
    private File file;
    private UserSetContainer userSetContainer = DataCenter.getInstance().getUserSetContainer();
    private UserSearchTree userSearchTree = DataCenter.getInstance().getUserSearchTree();
    public void initialize() {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        fileChooser.setInitialDirectory(new File("savedProfilePictures"));
    }

    public void createAccount() {
        UserSearchTree userTree = new UserSearchTree();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation");
        dialog.setHeaderText("Is this the user you'd like to create?");
        dialog.setContentText("Username: " + usernameTf.getText() + "\n" +
                "Password: " + passwordTf.getText() + "\n" +
                "Phone Number: " + phoneNumTf.getText());

        ButtonType yesButton = new ButtonType("Yes", ButtonType.OK.getButtonData());
        ButtonType noButton = new ButtonType("No", ButtonType.CANCEL.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(yesButton, noButton);

        dialog.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yesButton) {
                userSetContainer.addUserToSet(new User(usernameTf.getText(), passwordTf.getText(),
                        phoneNumTf.getText(), file.toURI().toString()));
                userSearchTree.addUser(new User(usernameTf.getText(), passwordTf.getText(), phoneNumTf.getText(),
                        file.toURI().toString()));
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("User created successfully!");
                successAlert.showAndWait();
            }
        });
    }

    public void chooseProfilePicture() {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            profilePictureIv.setImage(new javafx.scene.image.Image(file.toURI().toString()));
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
        if (userSetContainer.containsUserInSet(usernameTf.getText())) {
            usernameTf.setStyle("-fx-text-inner-color: red;");
            inValidUsername.setVisible(true);
            inValidUsername.setText("Username already exists");
            createAccountBtn.setDisable(true);
        } else {
            usernameTf.setStyle("-fx-text-inner-color: green;");
            inValidUsername.setVisible(false);
            createAccountBtn.setDisable(false);
        }
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
        //TODO fix phone number formatting
//        String currentText = phoneNumTf.getText();
//
//        // Remove non-numeric characters
//        String numericText = currentText.replaceAll("[^0-9]", "");
//
//        // Format the text as (123)-456-7890
//        StringBuilder formattedText = new StringBuilder("(");
//        for (int i = 0; i < Math.min(numericText.length(), 10); i++) {
//            formattedText.append(numericText.charAt(i));
//            if (i == 2) {
//                formattedText.append(")-");
//            } else if (i == 5) {
//                formattedText.append("-");
//            }
//        }
//
//        // Update the TextField with the formatted text
//        phoneNumTf.setText(formattedText.toString());
//
//        // Enable or disable the button based on the format
//        createAccountBtn.setDisable(!numericText.matches("\\d{10}"));
        // Get the current text in the TextField
        // Get the current text in the TextField
        String currentText = phoneNumTf.getText();

        // Remove non-numeric characters
        String numericText = currentText.replaceAll("[^0-9]", "");

        // Format the text as (123)-456-7890
        StringBuilder formattedText = new StringBuilder("(");
        for (int i = 0; i < Math.min(numericText.length(), 10); i++) {
            formattedText.append(numericText.charAt(i));
            if (i == 2 || i == 5) {
                formattedText.append(")-");
            } else if (i == 8) {
                formattedText.append("-");
            }
        }

        // Update the TextField with the formatted text
        phoneNumTf.setText(formattedText.toString());

        // Move caret to the next available position
        int caretPosition = Math.min(phoneNumTf.getCaretPosition(), 13);
        phoneNumTf.positionCaret(caretPosition);

        // Enable or disable the button based on the format
        createAccountBtn.setDisable(numericText.length() != 10);
    }
}
