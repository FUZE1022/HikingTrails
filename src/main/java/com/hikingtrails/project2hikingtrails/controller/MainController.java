package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.UserTreeMap;
import com.hikingtrails.project2hikingtrails.model.UserTreeSet;
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
    private UserTreeSet userTreeSet = DataCenter.getInstance().getUserTreeSet();
    private UserTreeMap userTreeMap = DataCenter.getInstance().getUserTreeMap();
    public void login(ActionEvent event) throws IOException {
//        if(username.getText().equalsIgnoreCase("Admin") && passwordPf.getText().equals("SCCC")) {
//            System.out.println("Admin login");
//            //TODO: Admin login
//        }
        if(userTreeMap.isValidUser(username.getText().trim(), passwordPf.getText())) {
            DataCenter.getInstance().setCurrentUser(userTreeSet.getUser(username.getText().trim()));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                    "/UserMainView.fxml"));
            Stage stage = new Stage();
            Scene newScene = new Scene(fxmlLoader.load(), 1180, 700);
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
