module com.hikingtrails.project2hikingtrails {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hikingtrails.project2hikingtrails to javafx.fxml;
    exports com.hikingtrails.project2hikingtrails;
    exports com.hikingtrails.project2hikingtrails.controller;
    opens com.hikingtrails.project2hikingtrails.controller to javafx.fxml;
}