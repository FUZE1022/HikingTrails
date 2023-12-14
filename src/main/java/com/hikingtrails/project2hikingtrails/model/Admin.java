package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(User user) {
        super(user.getUsername(), user.getPassword(), user.getPhoneNumber(), user.getProfilePicture());
    }
}
