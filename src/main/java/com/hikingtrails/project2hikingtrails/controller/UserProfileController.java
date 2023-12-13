package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    @FXML
    private Button editPasswordBtn, editPhoneBtn, editProfilePicBtn, checkHistoryBtn,
            checkReviewsAndCommentsBtn, deleteHistoryBtn, checkImageAndCommentsBtn, profilePhoneConfirmBtn,
            profilePasswordConfirmBtn, showReviewContentBtn;
    @FXML
    private TextField profileUsernameTf, profilePasswordTf, profilePhoneTf;
    @FXML
    private TextArea hikingHistoryCommentTa, reviewTa;
    @FXML
    private ImageView hikingHistoryIv, profilePictureIv, reviewIv;
    @FXML
    private Hyperlink followersHl, followingHl;
    @FXML
    private Label followersLbl, followingLbl, noPicturesLbl, checkHikingHistoryLbl1, checkHikingHistoryLbl2,
            noPictureLbl, allReviewsLbl, allCommentsLbl;
    @FXML
    private TableView<HikingHistory> hikingHistoryTv;
    @FXML
    private TableColumn<HikingHistory, String> hikingHistoryTrailNameTc, hikingHistoryStartTimeTc,
    hikingHistoryEndTimeTc, hikingHistoryStartDateTc, hikingHistoryEndDateTc, hikingHistoryDistanceTc,
            hikingHistoryDurationTc, hikingHistoryPaceTc, hikingHistoryCommentsTc;
    @FXML
    private TableView<Review> profileReviewTv;
    @FXML
    private TableColumn<Review, String> reviewTrailNameTc, reviewRatingTc, reviewDateTc, reviewTimeTc,
            reviewCommentsTc;
    @FXML
    private Pane commentPane;

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

        followersLbl.setText(String.valueOf(currentUser.getFollowersTreeSet().size()));
        followingLbl.setText(String.valueOf(currentUser.getFollowingTreeSet().size()));
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
        checkHikingHistoryLbl1.setVisible(true);
        checkHikingHistoryLbl2.setVisible(true);
        checkImageAndCommentsBtn.setVisible(true);
        deleteHistoryBtn.setVisible(true);
        hikingHistoryTv.setVisible(true);
        profileReviewTv.setVisible(false);
        commentPane.setVisible(false);
        reviewIv.setVisible(false);
        reviewTa.setVisible(false);
        noPictureLbl.setVisible(false);
        allReviewsLbl.setVisible(false);
        allCommentsLbl.setVisible(false);
        showReviewContentBtn.setVisible(false);
    }

    public void checkReviewAndComments() {
        profileReviewTv.setVisible(true);
        commentPane.setVisible(true);
        allCommentsLbl.setVisible(true);
        allReviewsLbl.setVisible(true);
        showReviewContentBtn.setVisible(true);
        checkHikingHistoryLbl1.setVisible(false);
        checkHikingHistoryLbl2.setVisible(false);
        checkImageAndCommentsBtn.setVisible(false);
        deleteHistoryBtn.setVisible(false);
        hikingHistoryTv.setVisible(false);
        noPicturesLbl.setVisible(false);
        hikingHistoryIv.setVisible(false);
        hikingHistoryCommentTa.setVisible(false);
        CommentLinkedList comments = currentUser.getComments();
        Node paginationOrMessage = createPagination(comments);
        commentPane.getChildren().clear();
        commentPane.getChildren().add(paginationOrMessage);
        if (paginationOrMessage instanceof Pagination) {
            VBox.setVgrow(paginationOrMessage, Priority.ALWAYS);
        }
        populateReviewTable();
    }

    public void showReviewContent() {
        if (profileReviewTv.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a review to see the content of.");
            alert.showAndWait();
            return;
        } else {
            String reviewPics = profileReviewTv.getSelectionModel().getSelectedItem().getPhotos();
            String review = profileReviewTv.getSelectionModel().getSelectedItem().getReview();
            reviewIv.setImage(null);
            if (reviewPics.equals("No Photo")) {
                noPictureLbl.setVisible(true);
                reviewIv.setVisible(false);
            } else {
                noPictureLbl.setVisible(false);
                reviewIv.setVisible(true);
                reviewIv.setImage(new Image(reviewPics));
            }
            reviewTa.setVisible(true);
            reviewTa.setText(review);
        }
    }

    public void deleteHistory() {
        HikingHistory trail = hikingHistoryTv.getSelectionModel().getSelectedItem();
        hikingHistory.removeHikingHistory(trail);
        populateTable();
    }

    public void checkImageAndComments() {
        if (hikingHistoryTv.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a review to see the content of.");
            alert.showAndWait();
            return;
        } else {
            String hikingPics = hikingHistoryTv.getSelectionModel().getSelectedItem().getPictures();
            String hikingComments = hikingHistoryTv.getSelectionModel().getSelectedItem().getComments();
            if (hikingPics.equals("No Photo")) {
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
    }

    public Node createPagination(CommentLinkedList comments) {
        if (comments.isEmpty()) {
            return new Label("No Comments");
        }
        Pagination pagination = new Pagination(comments.size());
        pagination.setPageFactory(pageIndex -> {
            if (pageIndex >= comments.size()) {
                return null;
            } else {
                Comment comment = comments.getPage(pageIndex);
                TextArea commentArea = new TextArea(comment.getComment());
                commentArea.setEditable(false);
                return commentArea;
            }
        });
        pagination.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return pagination;
    }

    public void showFollowers() {

    }

    public void showFollowing() {

    }

    private void populateReviewTable() {
        reviewTrailNameTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTrailName()));
        reviewRatingTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRating()));
        reviewDateTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
        reviewTimeTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTime()));
        profileReviewTv.setItems(FXCollections.observableArrayList(currentUser.getReviews().getUserReviewsLinkedList()));
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
