package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;

public class RootAdmin implements Serializable {
    private String username, password;

    public RootAdmin() {
        this("Admin", "SCCC");
    }
    private RootAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void promoteToAdmin(User user) {
        new Admin(user);
    }

    public void demoteToUser(Admin admin) {
        new User(admin);
    }
}
