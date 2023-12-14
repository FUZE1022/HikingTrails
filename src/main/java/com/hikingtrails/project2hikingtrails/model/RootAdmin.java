package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;

public class RootAdmin implements Serializable {
    private String username, password;
    private UserTreeSet userTreeSet = DataCenter.getInstance().getUserTreeSet();

    public RootAdmin() {
        this("Admin", "SCCC");
    }
    private RootAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin promoteUserToAdmin(User user) {
        Admin admin = new Admin(user);
        userTreeSet.removeUserFromSet(user);
        userTreeSet.addUserToSet(admin);
        return admin;
    }

    public User demoteAdminToUser(Admin admin) {
        User user = new User(admin.getUsername(), admin.getPassword(), admin.getPhoneNumber(), admin.getProfilePicture());
        userTreeSet.removeUserFromSet(admin);
        userTreeSet.addUserToSet(user);
        return user;
    }
}
