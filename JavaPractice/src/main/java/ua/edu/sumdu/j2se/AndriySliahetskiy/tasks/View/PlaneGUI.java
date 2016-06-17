package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.View;

import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller.GUIController;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.Task;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.SortedMap;
import java.util.logging.Logger;

/**
 * Created by Клиент on 28.05.2016.
 */
public class PlaneGUI extends TaskGUI{

    final private static Logger log = Logger.getLogger("PlaneGUI.class");

    private JTextArea textArea;
    private TaskList tasks;

    public void planeGUI(SortedMap<Calendar, Set<Task>> plane){

        TaskGUI gui = new TaskGUI();
        gui.setGUI("Tasks", 600, 600);

        gui.getP().setLayout(new BorderLayout());
        this.textArea = new JTextArea(20, 20);
        this.textArea.setEditable(false);
        this.textArea.setText(planeToText(plane));
        this.textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(this.textArea);
        gui.getP().add(scrollPane, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        gui.getP().add(panel, BorderLayout.SOUTH);
        gui.getF().getContentPane().add(gui.getP());
        gui.getF().setPreferredSize(new Dimension(900, 600));
        gui.getF().pack();
        gui.getF().setLocationRelativeTo(null);
        gui.getF().setVisible(true);
    }
}
