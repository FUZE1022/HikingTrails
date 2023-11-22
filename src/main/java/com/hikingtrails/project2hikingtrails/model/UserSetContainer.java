package com.hikingtrails.project2hikingtrails.model;

import java.util.HashSet;
import java.util.Set;

public abstract class UserSet {
    private Set<User> userSet;

    public UserSet() {
        this.userSet = new HashSet<User>();
    }
}
