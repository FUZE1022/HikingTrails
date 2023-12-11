package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;
import java.util.Objects;

public class HikingHistory implements Serializable {
    private String trialName, startTime, endTime, startDate, endDate, distance, duration, pictures ,avgPace, comments;

    public HikingHistory(String trialName, String startTime, String endTime, String startDate,
                         String endDate, String distance, String duration,
                         String pictures, String avgPace, String comments) {
        this.trialName = trialName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.duration = duration;
        this.pictures = pictures;
        this.avgPace = avgPace;
        this.comments = comments;
    }

    public String getTrialName() {
        return trialName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDistance() {
        return distance;
    }

    public String getDuration() {
        return duration;
    }

    public String getPictures() {
        return pictures;
    }

    public String getAvgPace() {
        return avgPace;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "HikingHistory{" +
                "trialName='" + trialName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", distance='" + distance + '\'' +
                ", duration='" + duration + '\'' +
                ", pictures='" + pictures + '\'' +
                ", avgPace='" + avgPace + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HikingHistory that = (HikingHistory) o;
        return Objects.equals(trialName, that.trialName) && Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) && Objects.equals(distance, that.distance) &&
                Objects.equals(duration, that.duration) && Objects.equals(pictures, that.pictures) &&
                Objects.equals(avgPace, that.avgPace) && Objects.equals(comments, that.comments) &&
                Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trialName, startTime, endTime, distance, duration, pictures, avgPace, comments);
    }
}
