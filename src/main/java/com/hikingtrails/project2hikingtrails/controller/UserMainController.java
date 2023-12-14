package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.*;
import com.hikingtrails.project2hikingtrails.util.BackUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserMainController implements Initializable {
    @FXML
    private StackPane stackPane;
    @FXML
    private Label userLbl;
    @FXML
    private Button exitBtn, backBtn, homeBtn, profileBtn, trailsBtn, adminBtn, hikingBtn;
    @FXML
    private ImageView searchUserIv;
    @FXML
    private TextField userSearchTf;

    private User currentUser = DataCenter.getInstance().getCurrentUser();
    private User tempCurrentUser = DataCenter.getInstance().getTempCurrentUser();
    private UserTreeSet userTreeSet = DataCenter.getInstance().getUserTreeSet();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLbl.setText(currentUser.getUsername());
        if(currentUser.getIsAdmin())
            adminBtn.setVisible(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserProfileView.fxml"));
        Parent newSceneRoot = null;
        try {
            newSceneRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void homeOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserHomeView.fxml"));
        Parent newSceneRoot = loader.load();
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void profileOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserProfileView.fxml"));
        Parent newSceneRoot = loader.load();
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void trailsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserTrailsView.fxml"));
        Parent newSceneRoot = loader.load();
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void hikingOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views/" +
                "UserHikingView.fxml"));
        Parent newSceneRoot = loader.load();
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void adminOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views/" +
                "AdminView.fxml"));
        Parent newSceneRoot = loader.load();
        stackPane.getChildren().add(newSceneRoot);
        newSceneRoot.toFront();
        for (Node node : stackPane.getChildren()) {
            if (node != newSceneRoot) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    public void searchUser(MouseEvent event) throws IOException {
        String username = userSearchTf.getText().trim();
        if (username.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No user entered");
            alert.setContentText("Please enter a user to search");
            alert.showAndWait();
            return;
        }
        if (username.equalsIgnoreCase(currentUser.getUsername())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot search for yourself");
            alert.setContentText("Please enter a user to search");
            alert.showAndWait();
            return;
        }
        if (userTreeSet.containsUsernameInSet(username)) {
            tempCurrentUser = userTreeSet.getUser(username);
            DataCenter.getInstance().setTempCurrentUser(tempCurrentUser);
            if(currentUser.getBlockTreeSet().isUserBlocked(tempCurrentUser.getUsername())) {
                if(showCBlockedUserDialog(tempCurrentUser.getUsername())) {
                    currentUser.getBlockTreeSet().removeBlocked(tempCurrentUser.getUsername());
                    tempCurrentUser.getFollowersTreeSet().addFollower(currentUser.getUsername());
                    currentUser.getFollowingTreeSet().addFollowing(tempCurrentUser.getUsername());
                    BackUp.saveData();
                    goToTempUserProfile(event);
                } else {
                    return;
                }
            } else
                goToTempUserProfile(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User not found");
            alert.setContentText("Please enter a valid user to search");
            alert.showAndWait();
        }
    }

    private void goToTempUserProfile(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/UserFollowingProfileView.fxml"));
        Stage stage = new Stage();
        Scene newScene = new Scene(fxmlLoader.load(), 1018, 592);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        stage.setScene(newScene);
        stage.show();
    }

    private boolean showCBlockedUserDialog(String username) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Blocked User");
        alert.setHeaderText("This user is blocked");
        alert.setContentText("Would you like to unblock and follow " + username + "?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public void exit() {
        System.exit(0);
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
