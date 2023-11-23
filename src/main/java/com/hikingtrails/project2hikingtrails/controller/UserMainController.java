package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMainController implements Initializable {

    @FXML
    private Label userLbl;
    @FXML
    private Button exitBtn;
    @FXML
    private ImageView profilePictureIv;

    private User currentUser = DataCenter.getInstance().getCurrentUser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLbl.setText(currentUser.getUsername());
        profilePictureIv.setImage(new Image(currentUser.getProfilePicture()));
    }

    public void exit() {
        System.exit(0);
    }
}
