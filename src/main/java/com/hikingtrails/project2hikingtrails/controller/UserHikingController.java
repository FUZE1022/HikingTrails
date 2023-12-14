package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.HikingHistory;
import com.hikingtrails.project2hikingtrails.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class UserHikingController implements Initializable {
    @FXML
    private TextField trailNameTf, hikeDurationTf, distHikedTf, avgPaceTf, startTimeTf, endTimeTf, startDateTf,
            endDateTf;
    @FXML
    private TextArea commentTa;

    @FXML
    private Button photoBtn, addBtn;

    @FXML
    private ImageView picIv;
    private FileChooser fileChooser;
    private File file = null;
    private User currentUser = DataCenter.getInstance().getCurrentUser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        fileChooser.setInitialDirectory(new File("savedTrailPhotos"));
    }

    public void addToHistory() {
        try {
            if (file == null) {
                currentUser.getHikingHistory().addHikingHistory(new HikingHistory(trailNameTf.getText(),
                        startTimeTf.getText(),
                        endTimeTf.getText(), startDateTf.getText(), endDateTf.getText(), distHikedTf.getText(),
                        hikeDurationTf.getText(), "No Photo", avgPaceTf.getText(), commentTa.getText()));
                successAlert();
            } else {
                currentUser.getHikingHistory().addHikingHistory(new HikingHistory(trailNameTf.getText(),
                        startTimeTf.getText(),
                        endTimeTf.getText(), startDateTf.getText(), endDateTf.getText(), distHikedTf.getText(),
                        hikeDurationTf.getText(), file.toURI().toString(), avgPaceTf.getText(), commentTa.getText()));
                successAlert();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all valid inputs for all fields, photos are optional.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void successAlert() {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText(null);
        successAlert.setContentText("User successfully created!");
        successAlert.showAndWait();
        trailNameTf.clear();
        startTimeTf.clear();
        endTimeTf.clear();
        startDateTf.clear();
        endDateTf.clear();
        distHikedTf.clear();
        hikeDurationTf.clear();
        avgPaceTf.clear();
        commentTa.clear();
        picIv.setImage(null);
    }

    public void uploadFile() {
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                String directoryPath = "savedPictures";
                File directory = new File(directoryPath);
                String uniqueFileName = "profile_" + System.currentTimeMillis() + ".png";
                File destinationFile = new File(directory, uniqueFileName);
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                picIv.setImage(new Image(destinationFile.toURI().toString()));
                file = destinationFile;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
