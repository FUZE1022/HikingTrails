package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.HikingHistory;
import com.hikingtrails.project2hikingtrails.model.HikingHistoryLinkedList;
import com.hikingtrails.project2hikingtrails.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    @FXML
    private Button editPasswordBtn, editPhoneBtn, editProfilePicBtn, checkHistoryBtn,
            checkReviewsAndCommentsBtn, deleteHistoryBtn, checkImageAndCommentsBtn, profilePhoneConfirmBtn,
            profilePasswordConfirmBtn;
    @FXML
    private TextField profileUsernameTf, profilePasswordTf, profilePhoneTf;
    @FXML
    private TextArea hikingHistoryCommentTa;
    @FXML
    private ImageView hikingHistoryIv, profilePictureIv;
    @FXML
    private Hyperlink followersHl, followingHl;
    @FXML
    private Label followersLbl, followingLbl, noPicturesLbl;
    @FXML
    private TableView<HikingHistory> hikingHistoryTv;
    @FXML
    private TableColumn<HikingHistory, String> hikingHistoryTrailNameTc, hikingHistoryStartTimeTc,
    hikingHistoryEndTimeTc, hikingHistoryStartDateTc, hikingHistoryEndDateTc, hikingHistoryDistanceTc,
            hikingHistoryDurationTc, hikingHistoryPaceTc, hikingHistoryCommentsTc;
    private User currentUser = DataCenter.getInstance().getCurrentUser();
    private HikingHistoryLinkedList hikingHistory = currentUser.getHikingHistory();
    private FileChooser fileChooser;
    private File file;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profileUsernameTf.setText(currentUser.getUsername());
        profilePasswordTf.setText(currentUser.getPassword());
        profilePhoneTf.setText(currentUser.getPhoneNumber());
        profilePictureIv.setImage(new Image(currentUser.getProfilePicture()));

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        fileChooser.setInitialDirectory(new File("savedProfilePictures"));

        populateTable();
    }


    public void editPassword() {
        profilePasswordTf.clear();
        profilePasswordTf.setDisable(false);
        editPasswordBtn.setVisible(false);
        profilePasswordConfirmBtn.setVisible(true);
    }

    public void editPhone() {
        profilePhoneTf.clear();
        profilePhoneTf.setDisable(false);
        editPhoneBtn.setVisible(false);
        profilePhoneConfirmBtn.setVisible(true);
    }

    public void editProfilePic() {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            profilePictureIv.setImage(new Image(file.toURI().toString()));
            currentUser.setProfilePicture(file.toURI().toString());
        }
    }

    public void profilePasswordConfirm() {
        String password = profilePasswordTf.getText();
        currentUser.setPassword(password);
        profilePasswordTf.setDisable(true);
        editPasswordBtn.setVisible(true);
        profilePasswordConfirmBtn.setVisible(false);
        profilePasswordTf.setText(currentUser.getPassword());
    }

    public void profilePasswordOnTyped() {
        String password = profilePasswordTf.getText();

        boolean isLengthValid = password.length() >= 8;
        boolean hasSpecialCharacter = password.matches(".*[^a-zA-Z0-9].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasCapitalLetter = password.matches(".*[A-Z].*");

        boolean notValid = !isLengthValid || !hasSpecialCharacter || !hasNumber || !hasCapitalLetter;

        profilePasswordTf.setStyle(notValid ? "-fx-text-inner-color: red;" : "-fx-text-inner-color: green;");
        profilePasswordConfirmBtn.setDisable(notValid);
    }

    public void profilePhoneConfirm() {
        String phone = profilePhoneTf.getText();
        currentUser.setPhoneNumber(phone);
        profilePhoneTf.setDisable(true);
        editPhoneBtn.setVisible(true);
        profilePhoneConfirmBtn.setVisible(false);
        profilePhoneTf.setText(currentUser.getPhoneNumber());
    }

    public void profilePhoneOnTyped() {
        String phone = profilePhoneTf.getText();
        boolean isLengthValid = phone.length() == 10;
        boolean hasNumber = phone.matches("[0-9]+");
        boolean isTaken = DataCenter.getInstance().getUserTreeSet().containsUserNumberInSet(phone);

        boolean notValid = !isLengthValid || !hasNumber || isTaken;

        profilePhoneTf.setStyle(notValid ? "-fx-text-inner-color: red;" : "-fx-text-inner-color: green;");
        profilePhoneConfirmBtn.setDisable(notValid);
    }

    public void checkHistory() {

    }

    public void checkReviewAndComments() {

    }

    public void deleteHistory() {
        HikingHistory trail = hikingHistoryTv.getSelectionModel().getSelectedItem();
        hikingHistory.removeHikingHistory(trail);
        populateTable();
    }

    public void checkImageAndComments() {
        String hikingPics = hikingHistoryTv.getSelectionModel().getSelectedItem().getPictures();
        String hikingComments = hikingHistoryTv.getSelectionModel().getSelectedItem().getComments();
        if(hikingPics.equals("No Photo")) {
            noPicturesLbl.setVisible(true);
            hikingHistoryIv.setVisible(false);
        } else {
            noPicturesLbl.setVisible(false);
            hikingHistoryIv.setVisible(true);
            hikingHistoryIv.setImage(new Image(hikingPics));
        }
        hikingHistoryCommentTa.setVisible(true);
        hikingHistoryCommentTa.setText(hikingComments);
    }

    public void showFollowers() {

    }

    public void showFollowing() {

    }

    private void populateTable() {
        hikingHistoryTrailNameTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTrialName()));
        hikingHistoryStartTimeTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStartTime()));
        hikingHistoryEndTimeTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndTime()));
        hikingHistoryStartDateTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStartDate()));
        hikingHistoryEndDateTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndTime()));
        hikingHistoryDistanceTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDistance()));
        hikingHistoryDurationTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDuration()));
        hikingHistoryPaceTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAvgPace()));
        hikingHistoryTv.setItems(FXCollections.observableArrayList(hikingHistory.getHikingHistoryLinkedList()));
    }
}
