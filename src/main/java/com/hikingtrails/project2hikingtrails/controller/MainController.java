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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    public void login(){
        if(userSearchTree.isValidUser(username.getText(), passwordPf.getText())){
            System.out.println("Valid user");
        }
        else {
            System.out.println("Invalid user");
        }
        userSetContainer.displayUserSet();
        userSearchTree.displayUserTreeMap();
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
