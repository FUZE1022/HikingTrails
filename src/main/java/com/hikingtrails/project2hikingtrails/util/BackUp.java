package com.hikingtrails.project2hikingtrails.util;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.TrailSetContainer;
import com.hikingtrails.project2hikingtrails.model.UserSearchTree;
import com.hikingtrails.project2hikingtrails.model.UserSetContainer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class BackUp {
    private static final String BACKUP_USERS_FILE_PATH = "backUp/users.dat";
    private static final String BACKUP_USERSTREE_FILE_PATH = "backUp/usersTree.dat";
    private static final String BACKUP_TRAILS_FILE_PATH = "backUp/trails.dat";
    private BackUp() {}
    private static boolean seralizedFileExists() {
        return Files.exists(Paths.get(BACKUP_USERS_FILE_PATH) ) && Files.exists(Paths.get(BACKUP_USERSTREE_FILE_PATH))
                && Files.exists(Paths.get(BACKUP_TRAILS_FILE_PATH));
    }

    public static void loadData() {
        if (!seralizedFileExists()) {
            createFile(BACKUP_USERS_FILE_PATH);
            createFile(BACKUP_USERSTREE_FILE_PATH);
            createFile(BACKUP_TRAILS_FILE_PATH);
            UserSetContainer userSetContainer = new UserSetContainer();
            DataCenter.getInstance().setUserSetContainer(userSetContainer);
            UserSearchTree userSearchTree = new UserSearchTree();
            DataCenter.getInstance().setUserSearchTree(userSearchTree);
            TrailSetContainer trailSetContainer = new TrailSetContainer();
            DataCenter.getInstance().setTrailSetContainer(trailSetContainer);
            saveData();
            return;
        }
        try {
            UserSetContainer loadUserSetContainer =
                    (UserSetContainer) Serializer.deserializeFromFile(BACKUP_USERS_FILE_PATH);
            UserSearchTree loadUserSearchTree =
                    (UserSearchTree) Serializer.deserializeFromFile(BACKUP_USERSTREE_FILE_PATH);
            TrailSetContainer loadTrailSetContainer =
                    (TrailSetContainer) Serializer.deserializeFromFile(BACKUP_TRAILS_FILE_PATH);
            DataCenter.getInstance().setUserSetContainer(loadUserSetContainer);
            DataCenter.getInstance().setUserSearchTree(loadUserSearchTree);
            DataCenter.getInstance().setTrailSetContainer(loadTrailSetContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveData() {
        try{
            Serializer.serializeToFile(DataCenter.getInstance().getUserSetContainer(), BACKUP_USERS_FILE_PATH);
            Serializer.serializeToFile(DataCenter.getInstance().getUserSearchTree(), BACKUP_USERSTREE_FILE_PATH);
            Serializer.serializeToFile(DataCenter.getInstance().getTrailSetContainer(), BACKUP_TRAILS_FILE_PATH);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void createFile(String pathName) {
        try {
            File file = new File(pathName);
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
