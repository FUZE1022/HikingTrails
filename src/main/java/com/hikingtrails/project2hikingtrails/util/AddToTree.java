package com.hikingtrails.project2hikingtrails.util;

import com.hikingtrails.project2hikingtrails.model.User;
import com.hikingtrails.project2hikingtrails.model.UserSearchTree;
import com.hikingtrails.project2hikingtrails.model.UserSetContainer;

public final class AddToTree {
    private AddToTree() {}

    public static UserSearchTree addToTree(UserSetContainer userSet) {
        UserSearchTree userSearchTree = new UserSearchTree();
        for (User user : userSet.getUserSet())
            userSearchTree.addUser(user);
        return userSearchTree;
    }
}
