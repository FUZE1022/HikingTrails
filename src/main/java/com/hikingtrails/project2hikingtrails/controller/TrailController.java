package com.hikingtrails.project2hikingtrails.controller;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.Trail;
import com.hikingtrails.project2hikingtrails.model.TrailHashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TrailController implements Initializable {
    @FXML
    private TableView<Trail> trailTV;
    @FXML
    private TableColumn<Trail, String> trailNameTC, headAddressTC, lengthTC, elevationTC, difficultyTC, typeTC, trailIdTC;
    @FXML
    private ComboBox<String> trailComboBoxfilter, trailComboBoxfilterByLength, trailComboBoxfilterByElevation,
            trailComboBoxfilterByDifficulty, trailComboBoxfilterByType;
    @FXML
    private Button filterBtn;
    @FXML
    private TextField filterTf;

    private ObservableList<String> filterListChoiceBox = FXCollections.observableArrayList("No Filter", "Name",
            "Length", "Elevation Gain", "Difficulty", "Type");

    private ObservableList<String> filterByLength = FXCollections.observableArrayList("0.0-3.0", "3.1-6.0", "6.1-9.0",
            "9.1-12.0", "12.1-15.0", "15.1-18.0", "18.1-21.0", "21.1+");
    private ObservableList<String> filterListElevation = FXCollections.observableArrayList("0-100", "101-200", "201-300",
            "301-400", "401-500", "501-600", "601-700", "701-800", "801-900", "901-1000", "1001+");
    private ObservableList<String> filterListDifficulty = FXCollections.observableArrayList("Easy", "Moderate", "Hard");
    private ObservableList<String> filterListType = FXCollections.observableArrayList("Loop", "Out & Back",
            "Point to Point");

    private TrailHashMap trailHashMap = DataCenter.getInstance().getTrailHashMap();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTableView();
        trailComboBoxfilter.setItems(filterListChoiceBox);
    }

    public void filterBtnOnAction() {
        String trail = filterTf.getText();
        if(trail == null)
            return;
        trailTV.setItems(filterListName(trailTV.getItems(), trail));
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
        trailTV.setItems(FXCollections.observableArrayList(trailHashMap.getTrailSet().values()));
    }

    private ObservableList<Trail> filterListName(ObservableList<Trail> trailList, String name) {
//        ObservableList<Trail> filteredList = FXCollections.observableArrayList();
//        for (Trail trail : trailList) {
//            if (trail.getName().equalsIgnoreCase(name)) {
//                filteredList.add(trail);
//            }
//        }
//        return filteredList;
        return trailList.stream()
                .filter(trail -> trail.getName().equalsIgnoreCase(name))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

    }

    private ObservableList<Trail> filterListLength(ObservableList<Trail> trailList, double lowerBound,
                                                   double upperBound) {
//        ObservableList<Trail> filteredList = FXCollections.observableArrayList();
//        for (Trail trail : trailList) {
//            if (Double.parseDouble(trail.getLength()) >= lowerBound &&
//                    Double.parseDouble(trail.getLength()) <= upperBound) {
//                filteredList.add(trail);
//            }
//        }
//        return filteredList;
        return trailList.stream()
                .filter(trail -> Double.parseDouble(trail.getLength()) >= lowerBound &&
                        Double.parseDouble(trail.getLength()) <= upperBound)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private ObservableList<Trail> filterListElevation(ObservableList<Trail> trailList, int lowerBound,
                                                      int upperBound) {
//        ObservableList<Trail> filteredList = FXCollections.observableArrayList();
//        for (Trail trail : trailList) {
//            if (Integer.parseInt(trail.getElevationGain()) >= lowerBound &&
//                    Integer.parseInt(trail.getElevationGain()) <= upperBound) {
//                filteredList.add(trail);
//            }
//        }
//        return filteredList;
        return trailList.stream()
                .filter(trail -> Integer.parseInt(trail.getElevationGain()) >= lowerBound &&
                        Integer.parseInt(trail.getElevationGain()) <= upperBound)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private ObservableList<Trail> filterListDifficulty(ObservableList<Trail> trailList, String difficulty) {
//        ObservableList<Trail> filteredList = FXCollections.observableArrayList();
//        for (Trail trail : trailList) {
//            if (trail.getDifficulty().equals(difficulty)) {
//                filteredList.add(trail);
//            }
//        }
//        return filteredList;
        return trailList.stream()
                .filter(trail -> trail.getDifficulty().equals(difficulty))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private ObservableList<Trail> filterListType(ObservableList<Trail> trailList, String type) {
//        ObservableList<Trail> filteredList = FXCollections.observableArrayList();
//        for (Trail trail : trailList) {
//            if (trail.getType().equals(type)) {
//                filteredList.add(trail);
//            }
//        }
//        return filteredList;
        return trailList.stream()
                .filter(trail -> trail.getType().equals(type))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}
