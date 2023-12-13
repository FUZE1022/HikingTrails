package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserFollowingProfileController implements Initializable {
    @FXML
    private Button checkHistoryBtn, checkReviewsAndCommentsBtn,  checkImageAndCommentsBtn, showReviewContentBtn,
            goBackBtn;
    @FXML
    private TextField profileUsernameTf;
    @FXML
    private TextArea hikingHistoryCommentTa, reviewTa;
    @FXML
    private ImageView hikingHistoryIv, profilePictureIv, reviewIv;
    @FXML
    private Label followersLbl, followingLbl, noPicturesLbl, noPictureLbl, allReviewsLbl, allCommentsLbl, blockedLbl;
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

    private User tempCurrentUser = DataCenter.getInstance().getTempCurrentUser();
    private HikingHistoryLinkedList hikingHistory = tempCurrentUser.getHikingHistory();
    private FileChooser fileChooser;
    private File file;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profileUsernameTf.setText(tempCurrentUser.getUsername());
        profilePictureIv.setImage(new Image(tempCurrentUser.getProfilePicture()));

        populateTable();

        followersLbl.setText(String.valueOf(tempCurrentUser.getFollowersTreeSet().size()));
        followingLbl.setText(String.valueOf(tempCurrentUser.getFollowingTreeSet().size()));
        blockedLbl.setText(String.valueOf(tempCurrentUser.getBlockTreeSet().size()));
    }

    public void checkHistory() {
        checkImageAndCommentsBtn.setVisible(true);
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
        checkImageAndCommentsBtn.setVisible(false);
        hikingHistoryTv.setVisible(false);
        noPicturesLbl.setVisible(false);
        hikingHistoryIv.setVisible(false);
        hikingHistoryCommentTa.setVisible(false);
        CommentLinkedList comments = tempCurrentUser.getComments();
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

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserMainView.fxml"));
        Stage stage = new Stage();
        Scene newScene = new Scene(fxmlLoader.load(), 1220, 700);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        stage.setScene(newScene);
        stage.show();
    }

    private void populateReviewTable() {
        reviewTrailNameTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTrailName()));
        reviewRatingTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRating()));
        reviewDateTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
        reviewTimeTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTime()));
        profileReviewTv.setItems(FXCollections.observableArrayList(tempCurrentUser.getReviews().getUserReviewsLinkedList()));
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

