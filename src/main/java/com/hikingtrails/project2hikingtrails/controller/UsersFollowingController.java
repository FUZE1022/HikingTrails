package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.FollowingTreeSet;
import com.hikingtrails.project2hikingtrails.model.User;
import com.hikingtrails.project2hikingtrails.util.BackUp;
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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsersFollowingController implements Initializable {
    @FXML
    private ListView<String> followingListView;
    @FXML
    private Button backBtn, unfollowBtn, blockBtn, profileBtn;

    private User currentUser = DataCenter.getInstance().getCurrentUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateFollowingListView();
    }

    public void populateFollowingListView() {
        FollowingTreeSet following = currentUser.getFollowingTreeSet();
        ObservableList<String> followingList = FXCollections.observableArrayList(following.getFollowing());
        followingListView.setItems(followingList);
    }

    private boolean showConfirmationDialog(String username) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Block User");
        alert.setHeaderText("Block User Confirmation");
        alert.setContentText("Are you sure you want to block " + username + "?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public void block() {
        String userToBlock = followingListView.getSelectionModel().getSelectedItem();
        if (userToBlock != null && showConfirmationDialog(userToBlock)) {
            currentUser.getFollowingTreeSet().removeFollowing(userToBlock);
            User blockedUser = DataCenter.getInstance().getUserTreeSet().getUser(userToBlock);
            if (blockedUser != null) {
                blockedUser.getFollowersTreeSet().removeFollower(currentUser.getUsername());
                currentUser.getBlockTreeSet().addBlocked(userToBlock);
            }
            BackUp.saveData();
            populateFollowingListView();
        }
    }

    public void unfollow() {
        String userToUnfollow = followingListView.getSelectionModel().getSelectedItem();
        followingListView.getSelectionModel().clearSelection();
        currentUser.getFollowingTreeSet().removeFollowing(userToUnfollow);
        if (userToUnfollow != null) {
            User unfollowedUser = DataCenter.getInstance().getUserTreeSet().getUser(userToUnfollow);
            if (unfollowedUser != null) {
                unfollowedUser.getFollowersTreeSet().removeFollower(currentUser.getUsername());
            }
            populateFollowingListView();
            BackUp.saveData();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No user selected");
            alert.setContentText("Please select a user to unfollow");
            alert.showAndWait();
        }
    }

    public void profile(ActionEvent event) throws IOException {
        if(followingListView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No user selected");
            alert.setContentText("Please select a user to view their profile");
            alert.showAndWait();
            return;
        }
        User tempUser = DataCenter.getInstance().getUserTreeSet().getUser(followingListView.getSelectionModel().getSelectedItem());
        DataCenter.getInstance().setTempCurrentUser(tempUser);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserFollowingProfileView.fxml"));
        Stage stage = new Stage();
        Scene newScene = new Scene(fxmlLoader.load(), 1018, 592);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        stage.setScene(newScene);
        stage.show();
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