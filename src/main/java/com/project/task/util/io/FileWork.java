package com.project.task.util.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileWork {
    private Scanner scn;
    private FileReader fileReader;
    private File file;

    public FileWork(String path) {
        file = new File(path);
        if (!file.exists()) {
            System.out.println("Ошибка: не существует файла указанного вами.");
        }
    }

//    public void loadFile(String path) {
//        file = new File(path);
//        if (!file.exists()) {
//            System.out.println("Ошибка: не существует файла указанного вами.");
//        }
//    }

    public ArrayList<String> read() throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        if (file.exists()) {
            fileReader = new FileReader(file);
            scn = new Scanner(fileReader);
            while (scn.hasNext()) {
                list.add(scn.next());
            }
            fileReader.close();
        } else {
            System.out.println("Нельзя произвести операцию. Файла не существует.");
        }
        return list;
    }

    public String readString() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if (file.exists()) {
            fileReader = new FileReader(file);
            scn = new Scanner(fileReader);
            while (scn.hasNext()) {
                stringBuilder.append(scn.nextLine());
                stringBuilder.append("\n");
            }
            fileReader.close();
        } else {
            System.out.println("Нельзя произвести операцию. Файла не существует.");
        }

        return stringBuilder.toString();
    }

//    public void write(ArrayList<String> list) throws IOException {
//        if (file.exists()) {
//            FileWriter fileWriter = new FileWriter(file, true);
//            for (String str : list) {
//                fileWriter.write(str);
//                fileWriter.write(" ");
//            }
//            fileWriter.close();
//        } else {
//            System.out.println("Нельзя произвести операцию. Файла не существует.");
//        }
//    }
}
