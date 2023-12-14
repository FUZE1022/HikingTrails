package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PromotedAdminController implements Initializable {
    @FXML
    private Button addTrailBtn, editTrailBtn;
    @FXML
    private TableView<Trail> trailTV;
    @FXML
    private TableColumn<Trail, String> trailNameTC, headAddressTC, lengthTC, elevationTC, difficultyTC, typeTC, trailIdTC;
    @FXML
    private TextField adminTrailTf, adminHeadAddressTf, adminLengthTf, adminElevationGainTf;
    @FXML
    private ComboBox<String> adminDifficultyCb, adminTypeCb;
    @FXML
    private Label chooseLbl;
    private ObservableList<String> difficutlyChoiceBox = FXCollections.observableArrayList("Easy", "Moderate",
            "Hard");

    private ObservableList<String> typeChoiceBox = FXCollections.observableArrayList("Point to Point", "Loop",
            "Out & Back");
    private TrailTreeSet trailTreeSet = DataCenter.getInstance().getTrailTreeSet();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminDifficultyCb.setItems(difficutlyChoiceBox);
        adminTypeCb.setItems(typeChoiceBox);
        populateTrailTable();
    }

    public void addTrail() {
        try {
            Trail newTrail = new Trail(adminTrailTf.getText(), adminHeadAddressTf.getText(), adminLengthTf.getText(),
                    adminElevationGainTf.getText(), adminDifficultyCb.getSelectionModel().getSelectedItem(),
                    adminTypeCb.getSelectionModel().getSelectedItem());
            if(trailTreeSet.containsTrailInSet(newTrail))
                showAlert("Trail already exists.");
            else if(adminTrailTf.getText().isEmpty() || adminHeadAddressTf.getText().isEmpty() ||
                    adminLengthTf.getText().isEmpty() || adminElevationGainTf.getText().isEmpty() ||
                    adminDifficultyCb.getSelectionModel().getSelectedItem() == null ||
                    adminTypeCb.getSelectionModel().getSelectedItem() == null)
                showAlert("Please fill out all fields.");
            else
                trailTreeSet.addTrail(newTrail);
        } catch (Exception e) {
            showAlert("Can't add over 50,000 trails.");
        }
        populateTrailTable();
    }

    public void editTrail() {
        Trail selectedTrail = trailTV.getSelectionModel().getSelectedItem();
        if (selectedTrail != null) {
            if (!adminTrailTf.getText().isEmpty())
                selectedTrail.setName(adminTrailTf.getText());
            if (!adminHeadAddressTf.getText().isEmpty())
                selectedTrail.setHeadAddress(adminHeadAddressTf.getText());
            if (!adminLengthTf.getText().isEmpty())
                selectedTrail.setLength(adminLengthTf.getText());
            if (!adminElevationGainTf.getText().isEmpty())
                selectedTrail.setElevationGain(adminElevationGainTf.getText());
            if (adminDifficultyCb.getSelectionModel().getSelectedItem() != null)
                selectedTrail.setDifficulty(adminDifficultyCb.getSelectionModel().getSelectedItem());
            if (adminTypeCb.getSelectionModel().getSelectedItem() != null)
                selectedTrail.setType(adminTypeCb.getSelectionModel().getSelectedItem());
        } else {
            showAlert("Please select a trail to edit.");
        }
        populateTrailTable();
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void populateTrailTable() {
        trailTV.getItems().clear();
        trailNameTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        headAddressTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHeadAddress()));
        lengthTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLength()));
        elevationTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getElevationGain()));
        difficultyTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDifficulty()));
        typeTC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));
        trailTV.setItems(FXCollections.observableArrayList(trailTreeSet.getTrailSet()));
    }
}
