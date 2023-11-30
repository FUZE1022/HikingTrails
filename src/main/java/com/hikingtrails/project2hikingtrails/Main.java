package com.hikingtrails.project2hikingtrails;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.Trail;
import com.hikingtrails.project2hikingtrails.model.TrailSetContainer;
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

    public static void main(String[] args) {
//        TrailSetContainer trailSetContainer = DataCenter.getInstance().getTrailSetContainer();
//        trailSetContainer.addTrail(new Trail("Avalon/East Farm Preserve", "Avalon Preserve", "3.5", "269", "Easy",
//                "Loop", null));
//        trailSetContainer.addTrail(new Trail("Connetquot River State Park Preserve", "Bohemia County Park", "3.0",
//                "269", "Easy", "Loop", null));
//        trailSetContainer.addTrail(new Trail("Wertheim Wildlife Trail", "Wertheim National Wildlife Refuge", "3.2",
//                "49", "Easy", "Loop", null));
//        trailSetContainer.addTrail(new Trail("Terrel River Trail", "Terrel River County Park", "2.7", "36", "Easy",
//                "Loop", null));
//        trailSetContainer.addTrail(new Trail("Glacier Ridge Preserve Trail", "Bald Hill Cultural Park", "11.2", "816",
//                "Hard", "Loop", null));
//        trailSetContainer.addTrail(new Trail("Rocky Point Trail", "Rocky Point State Pine Barrens Preserve", "10.4",
//                "236", "Moderate", "Loop", null));
//        trailSetContainer.addTrail(new Trail("Pine Trail Nature Preserve", "Ridge, New York", "4.5", "82", "Eays", "Out &" +
//                " Back", null));
//        trailSetContainer.addTrail(new Trail("North Shore Rail Trail", "Port Jefferson Station, New York", "10.5", "209",
//                "Moderate", "Point to Point", null));
        BackUp.loadData();
        launch();
    }
}