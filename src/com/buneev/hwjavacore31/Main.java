package com.buneev.hwjavacore31;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String rootPath = "/users/yaroslavbuneev/Dev/Games/";
        StringBuilder sb = new StringBuilder();
        ArrayList<String> files = new ArrayList<>(Arrays.asList(
                "src/", "res/", "savegames/", "temp/",
                "src/main/", "src/test/",
                "src/main/Main.java", "src/main/Utils.java",
                "res/drawables/", "res/vectors/", "res/icons/",
                "temp/temp.txt"
                ));

        for (String filePath : files) {
            if (filePath.lastIndexOf('/') == filePath.length() - 1) {
                File dir = new File(rootPath + filePath.substring(0,filePath.length()-1));
                if (dir.mkdir()) {
                    sb.append("Каталог ")
                            .append(filePath)
                            .append(" создан\n");
                } else {
                    sb.append("Ошибка создания каталога ")
                            .append(filePath)
                            .append("\n");
                }
            } else {
                File file = new File(rootPath + filePath);
                try {
                    if (file.createNewFile()) {
                        sb.append("Файл ")
                                .append(filePath)
                                .append(" создан\n");
                    }
                } catch (IOException e){
                    sb.append("Ошибка создания файла ")
                            .append(filePath).append("(")
                            .append(e.getMessage())
                            .append(")\n");
                }
            }
        }
        String log = sb.toString();

        try (FileWriter writer = new FileWriter(rootPath + "temp/temp.txt", false)) {
            writer.write(log);
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
