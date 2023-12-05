package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.*;

public class TrailHashMap implements Serializable {
    private Map<Integer, Trail> trailSet;
    private final int MAX_SIZE = 50000;

    public TrailHashMap() {
        this.trailSet = new HashMap<>(MAX_SIZE);
    }

    public void addTrail(Trail trail) throws Exception {
        if(trailSet.size() == MAX_SIZE) {
            throw new Exception("Trail set is full");
        }
        this.trailSet.put(trail.getId(), trail);
        BackUp.saveData();
    }

    public void removeTrailFromSet(Trail trail) {
        trailSet.remove(trail.getId());
    }

    public void removeTrailFromSet(int id) {
        trailSet.remove(id);
    }

    public boolean containsTrailInSet(Trail trail) {
        return trailSet.containsValue(trail);
    }

    public Optional<Trail> getTrail(int id) {
        return Optional.ofNullable(this.trailSet.get(id));
    }

    public Map<Integer, Trail> getTrailSet() {
        return trailSet;
    }

    public void setTrailSet(Map<Integer, Trail> trailSet) {
        this.trailSet = trailSet;
    }

    public void displayTrailSet() {
        for (Trail trail : trailSet.values()) {
            System.out.println(trail);
        }
    }
}
