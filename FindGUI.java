package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.View;

import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller.GUIController;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.LinkedTaskList;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.Tasks;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * Created by Клиент on 28.05.2016.
 */
public class FindGUI extends TaskGUI

{
    public void findTaskGUI() {
        final TaskGUI gui = new TaskGUI();
        final WindowWithTasksGUI windowWithTasksGUI = new WindowWithTasksGUI();
        gui.setGUI("Find", 450, 400);

        JLabel yearText = new JLabel("Year");
        gui.getC().gridx = 0;
        gui.getP().add(yearText, gui.getC());
        final JTextField year = new JTextField("", 4);
        year.setDocument(TaskGUI.getPlainDocument(4));
        gui.getC().gridx = 1;
        gui.getP().add(year, gui.getC());
        final JTextField year1 = new JTextField("", 4);
        year1.setDocument(TaskGUI.getPlainDocument(4));
        gui.getC().gridx = 2;
        gui.getP().add(year1, gui.getC());

        gui.getC().gridy++;
        JLabel mounthText = new JLabel("Mounth");
        gui.getC().gridx = 0;
        gui.getP().add(mounthText, gui.getC());
        final JTextField mounth = new JTextField("", 2);
        mounth.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 1;
        gui.getP().add(mounth, gui.getC());
        final JTextField mounth1 = new JTextField("", 2);
        mounth1.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 2;
        gui.getP().add(mounth1, gui.getC());

        gui.getC().gridy++;
        JLabel dayText = new JLabel("Day");
        gui.getC().gridx = 0;
        gui.getP().add(dayText, gui.getC());
        final JTextField day = new JTextField("", 2);
        day.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 1;
        gui.getP().add(day, gui.getC());
        final JTextField day1 = new JTextField("", 2);
        day1.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 2;
        gui.getP().add(day1, gui.getC());

        gui.getC().gridy++;
        JLabel hourText = new JLabel("Hour");
        gui.getC().gridx = 0;
        gui.getP().add(hourText, gui.getC());
        final JTextField hour = new JTextField("", 2);
        hour.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 1;
        gui.getP().add(hour, gui.getC());
        final JTextField hour1 = new JTextField("", 2);
        hour1.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 2;
        gui.getP().add(hour1, gui.getC());

        gui.getC().gridy++;
        JLabel minutesText = new JLabel("Minutes");
        gui.getC().gridx = 0;
        gui.getP().add(minutesText, gui.getC());
        final JTextField minutes = new JTextField("", 2);
        minutes.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 1;
        gui.getP().add(minutes, gui.getC());
        final JTextField minutes1 = new JTextField("", 2);
        minutes1.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 2;
        gui.getP().add(minutes1, gui.getC());

        gui.getC().gridy++;
        JLabel secondText = new JLabel("Seconds");
        gui.getC().gridx = 0;
        gui.getP().add(secondText, gui.getC());
        final JTextField second = new JTextField("", 2);
        minutes.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 1;
        gui.getP().add(second, gui.getC());
        final JTextField second1 = new JTextField("", 2);
        minutes1.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 2;
        gui.getP().add(second1, gui.getC());

        gui.getC().gridy++;
        JLabel activeText = new JLabel("Active");
        gui.getC().gridx = 0;
        gui.getP().add(activeText, gui.getC());
        final JCheckBox active = new JCheckBox("Active");
        minutes.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 1;
        gui.getP().add( active, gui.getC());
        final JCheckBox active1 = new JCheckBox("Not active");
        minutes1.setDocument(TaskGUI.getPlainDocument(2));
        gui.getC().gridx = 2;
        gui.getP().add(active1, gui.getC());

        gui.getC().gridy++;
        JButton button = new JButton("Find");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GUIController.chekField(year, mounth, day, hour, minutes, second) && GUIController.chekField(year1, mounth1, day1, hour1, minutes1, second1)) {
                    Calendar c1 = Calendar.getInstance();
                    Calendar c2 = Calendar.getInstance();
                    c1.set(Integer.parseInt(year.getText()), Integer.parseInt(mounth.getText()) - 1, Integer.parseInt(day.getText()), Integer.parseInt(hour.getText()), Integer.parseInt(minutes.getText()));
                    c1.set(Calendar.SECOND, Integer.parseInt(second.getText()));
                    c2.set(Integer.parseInt(year1.getText()), Integer.parseInt(mounth1.getText()) - 1, Integer.parseInt(day1.getText()), Integer.parseInt(hour1.getText()), Integer.parseInt(minutes1.getText()));
                    c2.set(Calendar.SECOND, Integer.parseInt(second1.getText()));
                    LinkedTaskList list = new LinkedTaskList();
                    if (active.isSelected())
                        list.add((LinkedTaskList) Tasks.incoming(GUIController.getTaskList(), c1, c2, true));
                    if (active1.isSelected())
                        list.add((LinkedTaskList) Tasks.incoming(GUIController.getTaskList(), c1, c2, false));
                    windowWithTasksGUI.windowWithTasks(list);
                }
            }
        });
        gui.getP().add(button, gui.getC());

        gui.getF().setVisible(true);
    }
}
