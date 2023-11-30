package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class TrailSetContainer implements Serializable {
    private Set<Trail> trailSet;

    public TrailSetContainer() {
        this.trailSet = new TreeSet<>();
    }

    public void addTrail(Trail trail) {
        this.trailSet.add(trail);
        BackUp.saveData();
    }

    public void removeTrail(Trail trail) {
        this.trailSet.remove(trail);
    }

    public boolean containsTrail(Trail trail) {
        return this.trailSet.contains(trail);
    }

    public Set<Trail> getTrailSet() {
        return this.trailSet;
    }

    public void setTrailSet(Set<Trail> trailSet) {
        this.trailSet = trailSet;
    }

    public Trail getTrail(String name) {
        for (Trail trail : trailSet) {
            if (trail.getName().equals(name))
                return trail;
        }
        return null;
    }

    public void displayTrailSet() {
        for (Trail trail : trailSet) {
            System.out.println(trail);
        }
    }
}
