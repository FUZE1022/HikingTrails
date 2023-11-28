package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMainController implements Initializable {
    @FXML
    private StackPane stackPane;
    @FXML
    private Label userLbl;
    @FXML
    private Button exitBtn, backBtn, homeBtn, profileBtn, trailsBtn;
    @FXML
    private ImageView profilePictureIv;

    private User currentUser = DataCenter.getInstance().getCurrentUser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLbl.setText(currentUser.getUsername());
        profilePictureIv.setImage(new Image(currentUser.getProfilePicture()));
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
