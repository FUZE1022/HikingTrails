package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class UserMainController implements Initializable {
    @FXML
    private StackPane stackPane;
    @FXML
    private Label userLbl;
    @FXML
    private Button exitBtn, backBtn, homeBtn, profileBtn, trailsBtn, adminBtn, hikingBtn;
    @FXML
    private ImageView searchUserIv;
    @FXML
    private TextField userSearchTf;

    private User currentUser = DataCenter.getInstance().getCurrentUser();
    private UserTreeSet userTreeSet = DataCenter.getInstance().getUserTreeSet();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLbl.setText(currentUser.getUsername());
//        if(currentUser.getIsAdmin())
////            adminBtn.setVisible(true);
//        RootAdmin rootAdmin = new RootAdmin();
//        rootAdmin.promoteToAdmin(currentUser);
//        System.out.println(currentUser.getIsAdmin());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserHomeView.fxml"));
        Parent newSceneRoot = null;
        try {
            newSceneRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void homeOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserHomeView.fxml"));
        Parent newSceneRoot = loader.load();
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void profileOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserProfileView.fxml"));
        Parent newSceneRoot = loader.load();
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void trailsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserTrailsView.fxml"));
        Parent newSceneRoot = loader.load();
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void hikingOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views/" +
                "UserHikingView.fxml"));
        Parent newSceneRoot = loader.load();
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void searchUser() {
        String username = userSearchTf.getText().trim();
        if(userTreeSet.getUser(username) != null && !username.equalsIgnoreCase(currentUser.getUsername()))
            System.out.println("User found");
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("User not found or Username can not be your own!");
            alert.showAndWait();
        }
    }

    public void exit() {
        System.exit(0);
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
}
