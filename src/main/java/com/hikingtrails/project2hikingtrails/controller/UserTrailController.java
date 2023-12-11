package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UserTrailController implements Initializable {
    @FXML
    private TableView<Trail> trailTV;
    @FXML
    private TableColumn<Trail, String> trailNameTC, headAddressTC, lengthTC, elevationTC, difficultyTC, typeTC, trailIdTC;
    @FXML
    private TableView<Review> reviewsTv;
    @FXML
    private TableColumn<Review, String> reviewsUsernameTc, reviewsTimeTc, reviewsDateTc, reviewsRatingTc;
    @FXML
    private ComboBox<String> trailComboBoxfilter, trailComboBoxfilterByLength, trailComboBoxfilterByElevation,
            trailComboBoxfilterByDifficulty, trailComboBoxfilterByType, trailReviewRatingCb;
    @FXML
    private Button filterBtn, trailWriteReviewBtn, trailCheckReviewBtn, trailReviewImageBtn, trailReviewPostBtn,
            seeReviewContentBtn, seeCommentsBtn, followAuthorBtn;
    @FXML
    private TextField filterTf, trailReviewTimeTf, trailReviewDateTf;
    @FXML
    private TextArea trailReviewReviewTa, reviewContentTa;
    @FXML
    private ImageView trailReviewPhotoIv, reviewPictureIv;
    @FXML
    private Line line1, line2, line3, line4, line5, line6, line7, line8;
    @FXML
    private Label reviewsLbl, noPicturesLbl;

    private ObservableList<String> filterListChoiceBox = FXCollections.observableArrayList("No Filter", "Name",
            "Length", "Elevation Gain", "Difficulty", "Type");

    private ObservableList<String> filterByLength = FXCollections.observableArrayList("0.0-3.0", "3.1-6.0", "6.1-9.0",
            "9.1-12.0", "12.1-15.0", "15.1-18.0", "18.1-21.0", "21.1+");
    private ObservableList<String> filterListElevation = FXCollections.observableArrayList("0-100", "101-200", "201-300",
            "301-400", "401-500", "501-600", "601-700", "701-800", "801-900", "901-1000", "1001+");
    private ObservableList<String> filterListDifficulty = FXCollections.observableArrayList("Easy", "Moderate", "Hard");
    private ObservableList<String> filterListType = FXCollections.observableArrayList("Loop", "Out & Back",
            "Point to Point");
    private ObservableList<String> ratings = FXCollections.observableArrayList("1", "2", "3", "4", "5");

    private TrailTreeSet trailTreeSet = DataCenter.getInstance().getTrailTreeSet();
    private User currentUser = DataCenter.getInstance().getCurrentUser();
    private FileChooser fileChooser;
    private File file;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTableView();
        trailComboBoxfilter.setItems(filterListChoiceBox);
        trailReviewRatingCb.setItems(ratings);

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        fileChooser.setInitialDirectory(new File("savedTrailPhotos"));
    }

    public void writeReview() {
        if(trailTV.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a trail to write a review for.");
            alert.showAndWait();
            return;
        } else {
            trailReviewRatingCb.setVisible(true);
            trailReviewTimeTf.setVisible(true);
            trailReviewDateTf.setVisible(true);
            trailReviewImageBtn.setVisible(true);
            trailReviewPostBtn.setVisible(true);
            trailReviewReviewTa.setVisible(true);
            trailReviewPhotoIv.setVisible(true);
            line1.setVisible(true);
            line2.setVisible(true);
            line3.setVisible(true);
            line4.setVisible(true);
        }
    }

    public void checkReviews() {
        if(trailTV.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a trail to check reviews for.");
            alert.showAndWait();
            return;
        } else {
            reviewsTv.setVisible(true);
            seeReviewContentBtn.setVisible(true);
            seeCommentsBtn.setVisible(true);
            followAuthorBtn.setVisible(true);
            reviewsLbl.setVisible(true);
            populateReviewTableView();
        }
    }

    public void seeReviewContent() {
        if(reviewsTv.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a review to see the content of.");
            alert.showAndWait();
            return;
        } else {
            reviewContentTa.setVisible(true);
            reviewPictureIv.setVisible(true);
            line5.setVisible(true);
            line6.setVisible(true);
            line7.setVisible(true);
            line8.setVisible(true);
            Review review = reviewsTv.getSelectionModel().getSelectedItem();
            reviewContentTa.setText(review.getReview());
            if(review.getPhotos() != null)
                reviewPictureIv.setImage(new Image(review.getPhotos()));
            else
                noPicturesLbl.setVisible(true);
        }
    }

    public void seeComments() {

    }

    public void followAuthor() {

    }

    public void uploadPicture() {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            trailReviewPhotoIv.setImage(new Image(file.toURI().toString()));
        }
    }

    public void postReview() {
        Trail trail = trailTV.getSelectionModel().getSelectedItem();
        String rating = trailReviewRatingCb.getValue();
        String time = trailReviewTimeTf.getText();
        String date = trailReviewDateTf.getText();
        String review = trailReviewReviewTa.getText();
        String photo = null;
        if (file != null)
            photo = file.toURI().toString();
        else
            photo = "No Photo";
        if (rating == null || time == null || date == null || review == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
            return;
        }
        trailTreeSet.getTrail(trail).getReviews().addReview(new Review(currentUser.getUsername(), trail.getName(),
                review, time, date, rating, photo));
        currentUser.getReviews().addReview(new Review(currentUser.getUsername(), trail.getName(), review, time, date,
                rating, photo));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Review posted successfully!");
        alert.showAndWait();
        trailReviewRatingCb.setVisible(false);
        trailReviewTimeTf.setVisible(false);
        trailReviewDateTf.setVisible(false);
        trailReviewImageBtn.setVisible(false);
        trailReviewPostBtn.setVisible(false);
        trailReviewReviewTa.setVisible(false);
        trailReviewPhotoIv.setVisible(false);
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);
        line4.setVisible(false);
        trailReviewRatingCb.setValue(null);
        trailReviewTimeTf.setText(null);
        trailReviewDateTf.setText(null);
        trailReviewReviewTa.setText(null);
        trailReviewPhotoIv.setImage(null);
    }

    public void filter() {
        switch (trailComboBoxfilter.getValue()) {
            case "Name":
                filterTf.setVisible(true);
                trailComboBoxfilterByLength.setVisible(false);
                trailComboBoxfilterByElevation.setVisible(false);
                trailComboBoxfilterByDifficulty.setVisible(false);
                trailComboBoxfilterByType.setVisible(false);
                filterBtn.setVisible(true);
                break;
            case "Length":
                filterTf.setVisible(false);
                trailComboBoxfilterByLength.setVisible(true);
                trailComboBoxfilterByElevation.setVisible(false);
                trailComboBoxfilterByDifficulty.setVisible(false);
                trailComboBoxfilterByType.setVisible(false);
                filterBtn.setVisible(false);
                trailComboBoxfilterByLength.setItems(filterByLength);
                break;
            case "Elevation Gain":
                filterTf.setVisible(false);
                trailComboBoxfilterByLength.setVisible(false);
                trailComboBoxfilterByElevation.setVisible(true);
                trailComboBoxfilterByDifficulty.setVisible(false);
                trailComboBoxfilterByType.setVisible(false);
                filterBtn.setVisible(false);
                trailComboBoxfilterByElevation.setItems(filterListElevation);
                break;
            case "Difficulty":
                filterTf.setVisible(false);
                trailComboBoxfilterByLength.setVisible(false);
                trailComboBoxfilterByElevation.setVisible(false);
                trailComboBoxfilterByDifficulty.setVisible(true);
                trailComboBoxfilterByType.setVisible(false);
                filterBtn.setVisible(false);
                trailComboBoxfilterByDifficulty.setItems(filterListDifficulty);
                break;
            case "Type":
                filterTf.setVisible(false);
                trailComboBoxfilterByLength.setVisible(false);
                trailComboBoxfilterByElevation.setVisible(false);
                trailComboBoxfilterByDifficulty.setVisible(false);
                trailComboBoxfilterByType.setVisible(true);
                filterBtn.setVisible(false);
                trailComboBoxfilterByType.setItems(filterListType);
                break;
            default:
                filterTf.setVisible(false);
                trailComboBoxfilterByLength.setVisible(false);
                trailComboBoxfilterByElevation.setVisible(false);
                trailComboBoxfilterByDifficulty.setVisible(false);
                trailComboBoxfilterByType.setVisible(false);
                filterBtn.setVisible(false);
                populateTableView();
                break;
        }
    }

    public void filterBtnOnAction() {
        String trail = filterTf.getText();
        if(trail == null)
            return;
        trailTV.setItems(filterListName(trailTV.getItems(), trail));
    }

    public void filterByLength() {
        populateTableView();
        String trail = trailComboBoxfilterByLength.getValue();
        if (trail == null)
            return;
        switch (trail) {
            case "0.0-3.0":
                trailTV.setItems(filterListLength(trailTV.getItems(), 0.0, 3.0));
                break;
            case "3.1-6.0":
                trailTV.setItems(filterListLength(trailTV.getItems(), 3.1, 6.0));
                break;
            case "6.1-9.0":
                trailTV.setItems(filterListLength(trailTV.getItems(), 6.1, 9.0));
                break;
            case "9.1-12.0":
                trailTV.setItems(filterListLength(trailTV.getItems(), 9.1, 12.0));
                break;
            case "12.1-15.0":
                trailTV.setItems(filterListLength(trailTV.getItems(), 12.1, 15.0));
                break;
            case "15.1-18.0":
                trailTV.setItems(filterListLength(trailTV.getItems(), 15.1, 18.0));
                break;
            case "18.1-21.0":
                trailTV.setItems(filterListLength(trailTV.getItems(), 18.1, 21.0));
                break;
            case "21.1+":
                trailTV.setItems(filterListLength(trailTV.getItems(), 21.1, Double.MAX_VALUE));
                break;
            default:
                populateTableView();
                break;
        }
    }

    public void filterByElevation() {
        populateTableView();
        String trail = trailComboBoxfilterByElevation.getValue();
        if (trail == null)
            return;
        switch (trail) {
            case "0-100":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 0, 100));
                break;
            case "101-200":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 101, 200));
                break;
            case "201-300":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 201, 300));
                break;
            case "301-400":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 301, 400));
                break;
            case "401-500":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 401, 500));
                break;
            case "501-600":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 501, 600));
                break;
            case "601-700":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 601, 700));
                break;
            case "701-800":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 701, 800));
                break;
            case "801-900":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 801, 900));
                break;
            case "901-1000":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 901, 1000));
                break;
            case "1001+":
                trailTV.setItems(filterListElevation(trailTV.getItems(), 1001, Integer.MAX_VALUE));
                break;
            default:
                populateTableView();
                break;
        }
    }

    public void filterByDifficulty() {
        populateTableView();
        String trail = trailComboBoxfilterByDifficulty.getValue();
        if (trail == null)
            return;
        switch (trail) {
            case "Easy":
                trailTV.setItems(filterListDifficulty(trailTV.getItems(), "Easy"));
                break;
            case "Moderate":
                trailTV.setItems(filterListDifficulty(trailTV.getItems(), "Moderate"));
                break;
            case "Hard":
                trailTV.setItems(filterListDifficulty(trailTV.getItems(), "Hard"));
                break;
            default:
                populateTableView();
                break;
        }
    }

    public void filterByType() {
        populateTableView();
        String trail = trailComboBoxfilterByType.getValue();
        if (trail == null)
            return;
        switch (trail) {
            case "Loop":
                trailTV.setItems(filterListType(trailTV.getItems(), "Loop"));
                break;
            case "Out & Back":
                trailTV.setItems(filterListType(trailTV.getItems(), "Out & Back"));
                break;
            case "Point to Point":
                trailTV.setItems(filterListType(trailTV.getItems(), "Point to Point"));
                break;
            default:
                populateTableView();
                break;
        }
    }

    private void populateTableView() {
        trailTV.getItems().clear();
        trailNameTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        headAddressTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHeadAddress()));
        lengthTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLength()));
        elevationTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getElevationGain()));
        difficultyTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDifficulty()));
        typeTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));
        trailTV.setItems(FXCollections.observableArrayList(trailTreeSet.getTrailSet()));
    }

    private void populateReviewTableView() {
        Trail trail = trailTV.getSelectionModel().getSelectedItem();
        reviewsTv.getItems().clear();
        reviewsUsernameTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUsername()));
        reviewsTimeTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTime()));
        reviewsDateTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
        reviewsRatingTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRating()));
        reviewsTv.setItems(FXCollections.observableArrayList(trailTreeSet.getTrail(trail).getReviews().getTrailReviewLinkedList()));
    }

    private ObservableList<Trail> filterListName(ObservableList<Trail> trailList, String name) {
        return trailList.stream()
                .filter(trail -> trail.getName().trim().equalsIgnoreCase(name.trim()))
                .findFirst()
                .map(FXCollections::observableArrayList)
                .orElse(FXCollections.observableArrayList());
    }

    private ObservableList<Trail> filterListLength(ObservableList<Trail> trailList, double lowerBound,
                                                   double upperBound) {
        return trailList.stream()
                .filter(trail -> Double.parseDouble(trail.getLength()) >= lowerBound &&
                        Double.parseDouble(trail.getLength()) <= upperBound)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private ObservableList<Trail> filterListElevation(ObservableList<Trail> trailList, int lowerBound,
                                                      int upperBound) {
        return trailList.stream()
                .filter(trail -> Integer.parseInt(trail.getElevationGain()) >= lowerBound &&
                        Integer.parseInt(trail.getElevationGain()) <= upperBound)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private ObservableList<Trail> filterListDifficulty(ObservableList<Trail> trailList, String difficulty) {
        return trailList.stream()
                .filter(trail -> trail.getDifficulty().equals(difficulty))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private ObservableList<Trail> filterListType(ObservableList<Trail> trailList, String type) {
        return trailList.stream()
                .filter(trail -> trail.getType().equals(type))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}
