package com.project.task;

import com.project.task.ed.WorkWindow;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new WorkWindow().setVisible(true);
                } catch (Exception ex) {
                    System.out.println("Ошибка!");
                }
            }
        });
    }
}
