package com.buneev.hwjavacore32;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String saveDir = "/users/yaroslavbuneev/Dev/Games/savegames/";
        String zipFileName = "save.zip";
        List<String> fileList = Arrays.asList(saveDir + "save1.dat",
                saveDir + "save2.dat",
                saveDir + "save3.dat");
        //      List<Integer> resultList = new ArrayList<>();
        GameProgress save1 = new GameProgress(5, 40, 8, 653.1);
        GameProgress save2 = new GameProgress(100, 10, 18, 1009.0);
        GameProgress save3 = new GameProgress(55, 90, 99, 9959.5);

        saveGame(fileList.get(0), save1);
        saveGame(fileList.get(1), save2);
        saveGame(fileList.get(2), save3);

        zipFiles(saveDir + zipFileName, fileList);

        File dir = new File(saveDir);
        for (String fileName : dir.list()) {
            System.out.println(fileName);
            if (!fileName.equals(zipFileName)) {
                File currentFile = new File(saveDir + fileName);
                System.out.println(fileName);
                System.out.println(currentFile.delete());
            }
        }
    }

    static boolean saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    static boolean zipFiles(String zipFile, List<String> files) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (String fileName : files) {
                try (FileInputStream fis = new FileInputStream(fileName)) {
                    ZipEntry entry = new ZipEntry(fileName.substring(fileName.lastIndexOf("/") + 1));
                    System.out.println(fileName.substring(fileName.lastIndexOf("/") + 1));
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return false;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}