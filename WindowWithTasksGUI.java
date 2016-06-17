package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.View;

import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller.GUIController;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Клиент on 28.05.2016.
 */
public class WindowWithTasksGUI extends TaskGUI {

    JTextArea textArea;
    JTextField text;
    TaskList tasks;

    public void windowWithTasks(TaskList tasks, final JFrame frame) {
        TaskGUI gui = new TaskGUI();
        gui.setGUI("Tasks", 600, 600);

        this.tasks = tasks;
        gui.getP().setLayout(new BorderLayout());
        this.textArea = new JTextArea(20, 20);
        this.textArea.setEditable(false);
        this.textArea.setText(GUIController.tasksToText(tasks));
        this.textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(this.textArea);
        gui.getP().add(scrollPane, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        gui.getP().add(panel, BorderLayout.SOUTH);
        gui.getF().getContentPane().add(gui.getP());
        this.text = new JTextField("", 4);
        this.text.setDocument(getPlainDocument(4));
        JButton button = new JButton("Delete");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (WindowWithTasksGUI.this.text.getText() != "") {
                    if (Integer.parseInt(WindowWithTasksGUI.this.text.getText()) >= GUIController.getTaskList().size()) WindowWithTasksGUI.this.text.setText("");
                    else {
                        WindowWithTasksGUI.this.tasks.remove(GUIController.getTaskList().getTask(Integer.parseInt(WindowWithTasksGUI.this.text.getText())));
                        GUIController.getTaskList().remove(GUIController.getTaskList().getTask(Integer.parseInt(WindowWithTasksGUI.this.text.getText())));
                    }
                    GUIController.saveTasks();
                    WindowWithTasksGUI.this.textArea.setText(GUIController.tasksToText(WindowWithTasksGUI.this.tasks));
                }
            }
        });
        JButton changeBut = new JButton("Change");
        changeBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangeGUI changeGUI = new ChangeGUI();
                if (!WindowWithTasksGUI.this.text.getText().isEmpty()) {
                    if (Integer.parseInt(WindowWithTasksGUI.this.text.getText()) >= GUIController.getTaskList().size()) WindowWithTasksGUI.this.text.setText("");
                    else {
                        changeGUI.changeTask(WindowWithTasksGUI.this.textArea, WindowWithTasksGUI.this.tasks, GUIController.getTasks().getTask(Integer.parseInt(WindowWithTasksGUI.this.text.getText())).clone());
                    }
                }
            }
        });
        JButton addBut = new JButton("Add");
        addBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddGUI addGUI = new AddGUI();
                addGUI.addTaskGUI(WindowWithTasksGUI.this.textArea, WindowWithTasksGUI.this.tasks);
            }
        });
        gui.getP().add(this.text, BorderLayout.BEFORE_FIRST_LINE);
        gui.getP().add(button, BorderLayout.AFTER_LINE_ENDS);
        gui.getP().add(changeBut, BorderLayout.BEFORE_LINE_BEGINS);
        gui.getP().add(addBut, BorderLayout.AFTER_LAST_LINE);
        gui.getF().setPreferredSize(new Dimension(900, 600));
        gui.getF().pack();
        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.setVisible(true);
                this.windowClosed(e);
            }
        };
        gui.getF().addWindowListener(wndCloser);
        gui.getF().setLocationRelativeTo(null);
        gui.getF().setVisible(true);
    }
}
