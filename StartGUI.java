package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.View;

import sun.awt.WindowClosingListener;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller.GUIController;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.TaskIO;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Клиент on 28.05.2016.
 */
public class StartGUI {

    public static void main(String[] args) {
        final FindGUI findGUI = new FindGUI();
        findGUI.setGUI("Find", 450, 300);
        final WindowWithTasksGUI windowWithTasksGUI = new WindowWithTasksGUI();

        TaskIO.readText(GUIController.getTaskList(), GUIController.getF());
        TaskGUI gui = new TaskGUI();
        gui.setGUI("Main", 300, 200);
        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        gui.getF().addWindowListener(wndCloser);
        JButton showDayButton = new JButton("Find Tasks");
        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               findGUI.findTaskGUI();
            }
        };
        showDayButton.addActionListener(actionListener1);
        JButton showAllButton = new JButton("Show all");
        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowWithTasksGUI.windowWithTasks(GUIController.getTasks());
            }
        };
        showDayButton.addActionListener(actionListener1);
        showAllButton.addActionListener(actionListener2);

        gui.getP().add(showDayButton, gui.getC());
        gui.getC().gridx = 1;
        gui.getP().add(showAllButton, gui.getC());
        gui.getC().gridy = 1;
        gui.getC().gridx = 0;
        /////
        gui.getF().setVisible(true);
    }
}
