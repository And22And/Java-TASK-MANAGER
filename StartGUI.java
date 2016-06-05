package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.View;

import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller.GUIController;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller.TaskIO;

import javax.swing.*;
import java.awt.event.*;
import org.apache.log4j.Logger;

/**
 * Created by Клиент on 28.05.2016.
 */
public class StartGUI {

    final private static Logger log = Logger.getLogger(StartGUI.class);

    static FindGUI findGUI;
    static WindowWithTasksGUI windowWithTasksGUI;

    public WindowWithTasksGUI getWindowWithTasksGUI() {
        return windowWithTasksGUI;
    }

    public void setWindowWithTasksGUI(WindowWithTasksGUI windowWithTasksGUI) {
        this.windowWithTasksGUI = windowWithTasksGUI;
    }

    public FindGUI getFindGUI() {
        return findGUI;
    }

    public void setFindGUI(FindGUI findGUI) {
        this.findGUI = findGUI;
    }

    public static void main(String[] args) {
        log.info("Start");
        NoticeGUI notice = new NoticeGUI();
        notice.start();
        final StartGUI taskGUI = new StartGUI();
        taskGUI.setFindGUI(new FindGUI());
        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                log.info("End");
                System.exit(0);
            }
        };
        taskGUI.setFindGUI(new FindGUI());
        taskGUI.setWindowWithTasksGUI(new WindowWithTasksGUI());

        TaskIO.readText(GUIController.getTaskList(), GUIController.getF());
        final TaskGUI gui = new TaskGUI();
        gui.setGUI("Main", 300, 200, wndCloser);

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
                windowWithTasksGUI.windowWithTasks(GUIController.getTasks(), gui.getF());
            }
        };
        showAllButton.addActionListener(actionListener2);

        JButton showPlane = new JButton("Show plane");
        ActionListener actionListener3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               findGUI.findPlaneGUI();
            }
        };
        showPlane.addActionListener(actionListener3);

        gui.getP().add(showDayButton, gui.getC());
        gui.getC().gridx = 1;
        gui.getP().add(showAllButton, gui.getC());
        gui.getC().gridy++;
        gui.getC().gridx = 0;
        gui.getP().add(showPlane, gui.getC());
        gui.getF().setVisible(true);
    }
}
