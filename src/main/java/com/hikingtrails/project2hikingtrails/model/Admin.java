package com.hikingtrails.project2hikingtrails.model;

public class Admin extends User{
    private boolean isAdmin = true;

    public Admin(User user) {
        super(user.getUsername(), user.getPassword(), user.getPhoneNumber(), user.getProfilePicture(), user.getHikingHistory(),
                user.getFollowers(), user.getFollowing(), user.getReviews());
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
}
