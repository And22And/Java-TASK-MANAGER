package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.View;

import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller.GUIController;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.Task;

import javax.swing.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Клиент on 05.06.2016.
 */
public class NoticeGUI extends Thread{

    @Override
    public void run() {
        Map<Task, Calendar> map = new HashMap<>();
        while(true) {
            Calendar curr = Calendar.getInstance();
            for (Task t : GUIController.getTaskList()) {
                if (t.isActive() && t.nextTimeAfter(curr) != null && t.nextTimeAfter(curr).getTimeInMillis() - curr.getTimeInMillis() < 600000) {
                    if (!map.containsKey(t) || !map.get(t).equals(t.nextTimeAfter(curr))) {
                        noticeWindow(t, t.nextTimeAfter(curr));
                        map.put(t, t.nextTimeAfter(curr));
                    }
                }
            }
        }
    }

    public void noticeWindow(Task task, Calendar calendar) {
        TaskGUI gui = new TaskGUI();
        gui.setGUI("Notice", 900, 150);
        JTextArea textArea = new JTextArea(20, 20);
        textArea.setEditable(false);
        textArea.setText("Task " + GUIController.taskToText(task) + " start at " + calendar.getTime());
        textArea.setCaretPosition(0);
        gui.getF().add(textArea);
        gui.getF().setVisible(true);
    }
}
