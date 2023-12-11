package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class HikingHistoryLinkedList implements Serializable {
    private List<HikingHistory> hikingHistoryLinkedList;

    public HikingHistoryLinkedList() {
        this.hikingHistoryLinkedList = new LinkedList<>();
    }

    public void addHikingHistory(HikingHistory hikingHistory) {
        hikingHistoryLinkedList.add(hikingHistory);
        BackUp.saveData();
    }

    public void removeHikingHistory(HikingHistory hikingHistory) {
        hikingHistoryLinkedList.remove(hikingHistory);
        BackUp.saveData();
    }

    public boolean containsHikingHistory(HikingHistory hikingHistory) {
        return hikingHistoryLinkedList.contains(hikingHistory);
    }

    public HikingHistory getHikingHistory(HikingHistory hikingHistory) {
        return hikingHistoryLinkedList.get(hikingHistoryLinkedList.indexOf(hikingHistory));
    }

    public List<HikingHistory> getHikingHistoryLinkedList() {
        return hikingHistoryLinkedList;
    }

    public void setHikingHistoryLinkedList(List<HikingHistory> hikingHistoryLinkedList) {
        this.hikingHistoryLinkedList = hikingHistoryLinkedList;
        BackUp.saveData();
    }

    public void displayHikingHistoryLinkedList() {
        for (HikingHistory hikingHistory : hikingHistoryLinkedList) {
            System.out.println(hikingHistory);
        }
    }

    @Override
    public String toString() {
        return "HikingHistoryLinkedList{" +
                "hikingHistoryLinkedList=" + hikingHistoryLinkedList +
                '}';
    }
}
