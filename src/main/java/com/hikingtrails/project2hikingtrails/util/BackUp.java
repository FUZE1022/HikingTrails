package com.hikingtrails.project2hikingtrails.util;

import com.hikingtrails.project2hikingtrails.model.DataCenter;
import com.hikingtrails.project2hikingtrails.model.TrailTreeSet;
import com.hikingtrails.project2hikingtrails.model.UserTreeSet;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class BackUp {
    private static final String BACKUP_USERS_FILE_PATH = "backUp/users.dat";
    private static final String BACKUP_TRAILS_FILE_PATH = "backUp/trails.dat";
    private BackUp() {}
    private static boolean seralizedFileExists() {
        return Files.exists(Paths.get(BACKUP_USERS_FILE_PATH)) && Files.exists(Paths.get(BACKUP_TRAILS_FILE_PATH));
    }

    public static void loadData() {
        if (!seralizedFileExists()) {
            createFile(BACKUP_USERS_FILE_PATH);
            createFile(BACKUP_TRAILS_FILE_PATH);
            UserTreeSet userTreeSet = new UserTreeSet();
            DataCenter.getInstance().setUserTreeSet(userTreeSet);
            TrailTreeSet trailTreeSet = new TrailTreeSet();
            DataCenter.getInstance().setTrailTreeSet(trailTreeSet);
            saveData();
            return;
        }
        try {
            UserTreeSet loadUserTreeSet =
                    (UserTreeSet) Serializer.deserializeFromFile(BACKUP_USERS_FILE_PATH);
            TrailTreeSet loadTrailTreeSet =
                    (TrailTreeSet) Serializer.deserializeFromFile(BACKUP_TRAILS_FILE_PATH);
            DataCenter.getInstance().setUserTreeSet(loadUserTreeSet);
            DataCenter.getInstance().setTrailTreeSet(loadTrailTreeSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveData() {
        try{
            Serializer.serializeToFile(DataCenter.getInstance().getUserTreeSet(), BACKUP_USERS_FILE_PATH);
            Serializer.serializeToFile(DataCenter.getInstance().getTrailTreeSet(), BACKUP_TRAILS_FILE_PATH);
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
