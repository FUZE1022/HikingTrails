package com.hikingtrails.project2hikingtrails;

import com.hikingtrails.project2hikingtrails.model.*;
import com.hikingtrails.project2hikingtrails.util.BackUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/hikingtrails/project2hikingtrails/views" +
                "/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Login");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        BackUp.loadData();
//        TrailTreeSet trailHashMap = DataCenter.getInstance().getTrailTreeSet();
//        trailHashMap.addTrail(new Trail("Avalon/East Farm Preserve", "Avalon Preserve", "3.5", "269", "Easy",
//                "Loop"));
//        trailHashMap.addTrail(new Trail("Connetquot River State Park Preserve", "Bohemia County Park", "3.0",
//                "269", "Easy", "Loop"));
//        trailHashMap.addTrail(new Trail("Wertheim Wildlife Trail", "Wertheim National Wildlife Refuge", "3.2",
//                "49", "Easy", "Loop"));
//        trailHashMap.addTrail(new Trail("Terrel River Trail", "Terrel River County Park", "2.7", "36", "Easy",
//                "Loop"));
//        trailHashMap.addTrail(new Trail("Glacier Ridge Preserve Trail", "Bald Hill Cultural Park", "11.2", "816",
//                "Hard", "Loop"));
//        trailHashMap.addTrail(new Trail("Rocky Point Trail", "Rocky Point State Pine Barrens Preserve", "10.4",
//                "236", "Moderate", "Loop"));
//        trailHashMap.addTrail(new Trail("Pine Trail Nature Preserve", "Ridge, New York", "4.5", "82", "Eays", "Out &" +
//                " Back"));
//        trailHashMap.addTrail(new Trail("North Shore Rail Trail", "Port Jefferson Station, New York", "10.5", "209",
//                "Moderate", "Point to Point"));
        launch();
    }
}