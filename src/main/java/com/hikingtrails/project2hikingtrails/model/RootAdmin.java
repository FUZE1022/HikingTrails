package com.hikingtrails.project2hikingtrails.model;

public class RootAdmin {
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
