package com.project.task.ed;

import com.project.task.util.io.FileWork;
import com.project.task.util.Obfuscator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWindow extends JFrame{
    private JPanel pnlMain;
    private JButton btnLoadFile;
    private JTextArea taNotepad;
    private JButton btnObf;

    private ArrayList<String> voc;

    public WorkWindow() throws IOException {
        super("Obfuscation code.");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setBounds(200,100,1500,800);
        super.add(pnlMain);
        Font font = new Font("Monaco",Font.PLAIN, 18);
        taNotepad.setFont(font);
        taNotepad.setForeground(Color.WHITE);
        FileWork fileWork = new FileWork("Files/Vocabulary.txt");
        final Obfuscator obfuscator = new Obfuscator();
        voc = fileWork.read();


        btnLoadFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int result = fileChooser.showOpenDialog(WorkWindow.super.rootPane);
                String pathToFile = null;
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    pathToFile = selectedFile.getAbsolutePath();
                }
                FileWork fileWork = new FileWork(pathToFile);
                try {
                    taNotepad.setText(fileWork.readString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnObf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder txt = new StringBuilder(taNotepad.getText());
                txt.insert(0," ");
                taNotepad.setText(obfuscator.darken(txt.toString(), voc));
            }
        });
    }
}
