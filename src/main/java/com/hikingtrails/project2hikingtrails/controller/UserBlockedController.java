package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.BlockTreeSet;
import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.FollowersTreeSet;
import com.hikingtrails.project2hikingtrails.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserBlockedController implements Initializable {
    @FXML
    private Button backButton, unblockBtn;
    @FXML
    private ListView<String> blockedListView;
    private User currentUser = DataCenter.getInstance().getCurrentUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateBlockedListView();
    }

    private void populateBlockedListView() {
        BlockTreeSet blocked = currentUser.getBlockTreeSet();
        ObservableList<String> blockedList = FXCollections.observableArrayList(blocked.getBlockedTree());
        blockedListView.setItems(blockedList);
    }

    public void unblock() {
        String userToUnblock = blockedListView.getSelectionModel().getSelectedItem();
        if (userToUnblock != null) {
            currentUser.getBlockTreeSet().removeBlocked(userToUnblock);
            User unblockedUser = DataCenter.getInstance().getUserTreeSet().getUser(userToUnblock);
            if (unblockedUser != null) {
                unblockedUser.getFollowersTreeSet().addFollower(currentUser.getUsername());
                currentUser.getFollowingTreeSet().addFollowing(userToUnblock);
            }
            populateBlockedListView();
        }
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
}
