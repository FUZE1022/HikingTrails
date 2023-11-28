package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.UserSearchTree;
import com.hikingtrails.project2hikingtrails.model.UserSetContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordPf;
    @FXML
    private Button loginBtn, exitBtn;
    @FXML
    private Hyperlink newUserHl;
    private UserSetContainer userSetContainer = DataCenter.getInstance().getUserSetContainer();
    private UserSearchTree userSearchTree = DataCenter.getInstance().getUserSearchTree();
    public void login(ActionEvent event) throws IOException {
        if(userSearchTree.isValidUser(username.getText().trim(), passwordPf.getText().trim())){
            DataCenter.getInstance().setCurrentUser(userSetContainer.getUser(username.getText().trim()));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                    "/UserMainView.fxml"));
            Stage stage = new Stage();
            Scene newScene = new Scene(fxmlLoader.load(), 884, 582);
            //stage.initStyle(StageStyle.UNDECORATED);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            stage.setScene(newScene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User not found");
            alert.setHeaderText(null);
            alert.setContentText("Username and password combination not found!");
            alert.showAndWait();
        }
    }

    public void goToUserCreation(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/NewUserView.fxml"));
        Stage stage = new Stage();
        Scene newScene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("User creation");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        stage.setScene(newScene);
        stage.show();
    }

    public void exit(){
        System.exit(0);
    }
}
