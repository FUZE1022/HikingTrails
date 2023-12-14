package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootAdminController implements Initializable {
    @FXML
    private Button demoteAdminBtn, promoteUserBtn, backBtn, addTrailBtn, editTrailBtn, removeTrailBtn,
            adminUserSettingsBtn, adminTrailSettingsBtn;
    @FXML
    private TableView<User> adminUserTv;
    @FXML
    private TableColumn<User, String> adminUsernameTc, adminPasswordTc, adminPhoneTc, adminIsAdminTc;
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
    private UserTreeSet userTreeSet = DataCenter.getInstance().getUserTreeSet();
    private TrailTreeSet trailTreeSet = DataCenter.getInstance().getTrailTreeSet();
    private RootAdmin rootAdmin = new RootAdmin();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminDifficultyCb.setItems(difficutlyChoiceBox);
        adminTypeCb.setItems(typeChoiceBox);
        populateUserTable();
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

    public void removeTrail() {
        Trail selectedTrail = trailTV.getSelectionModel().getSelectedItem();
        if (selectedTrail != null) {
            trailTreeSet.removeTrail(selectedTrail);
        } else {
            showAlert("Please select a trail to remove.");
        }
        populateTrailTable();
    }

    public void showTrailSettings() {
        setTrailVisibility(true);
        setUsersVisibility(false);
    }

    public void showUserSettings() {
        setTrailVisibility(false);
        setUsersVisibility(true);
    }

    private void setTrailVisibility(boolean isVisible) {
        adminTrailTf.setVisible(isVisible);
        adminHeadAddressTf.setVisible(isVisible);
        adminLengthTf.setVisible(isVisible);
        adminElevationGainTf.setVisible(isVisible);
        adminDifficultyCb.setVisible(isVisible);
        adminTypeCb.setVisible(isVisible);
        trailTV.setVisible(isVisible);
        removeTrailBtn.setVisible(isVisible);
        editTrailBtn.setVisible(isVisible);
        addTrailBtn.setVisible(isVisible);
        chooseLbl.setVisible(isVisible);
    }

    private void setUsersVisibility(boolean isVisible) {
        adminUserTv.setVisible(isVisible);
        demoteAdminBtn.setVisible(isVisible);
        promoteUserBtn.setVisible(isVisible);
    }



    public void promoteUser() {
        User selectedUser = adminUserTv.getSelectionModel().getSelectedItem();
        if (selectedUser != null && !selectedUser.getIsAdmin()) {
            rootAdmin.promoteUserToAdmin(selectedUser);
            populateUserTable();
        } else {
            showAlert("The selected user is already an admin or no user is selected.");
        }
    }

    public void demoteAdmin() {
        User selectedUser = adminUserTv.getSelectionModel().getSelectedItem();
        if (selectedUser != null && selectedUser.getIsAdmin()) {
            rootAdmin.demoteAdminToUser((Admin) selectedUser);
            populateUserTable();
        } else {
            showAlert("The selected user is not an admin or no user is selected.");
        }
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void populateUserTable() {
        adminUserTv.getItems().clear();
        adminUsernameTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUsername()));
        adminPasswordTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPassword()));
        adminPhoneTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhoneNumber()));
        adminIsAdminTc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIsAdmin() ? "Yes" : "No"));
        adminUserTv.setItems(FXCollections.observableArrayList(userTreeSet.getUserSet()));
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

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/MainView.fxml"));
        Stage stage = new Stage();
        Scene newScene = new Scene(fxmlLoader.load(), 600, 400);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(newScene);
        stage.show();
    }
}
