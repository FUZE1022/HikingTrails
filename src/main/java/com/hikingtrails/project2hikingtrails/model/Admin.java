package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    private boolean isAdmin = true;

    public Admin(User user) {
        super(user.getUsername(), user.getPassword(), user.getPhoneNumber(), user.getProfilePicture(),
                user.getFollowers(), user.getFollowing());
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
}
