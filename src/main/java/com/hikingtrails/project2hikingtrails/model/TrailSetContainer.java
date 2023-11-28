package com.hikingtrails.project2hikingtrails.model;

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

    public void displayTrailSet() {
        for (Trail trail : trailSet) {
            System.out.println(trail);
        }
    }
}
