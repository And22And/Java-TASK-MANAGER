package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.View;

import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller.GUIController;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.Task;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.TaskList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * Created by Клиент on 28.05.2016.
 */
public class ChangeGUI extends TaskGUI {

    final private static Logger log = Logger.getLogger("ChangeGUI.class");

    private TaskGUI gui;
    private JTextArea textArea;
    private TaskList tasks;
    private Task task;
    private Task changedTask;
    private JTextField title;
    private JTextField year;
    private JTextField year1;
    private JTextField mounth;
    private JTextField mounth1;
    private JTextField day;
    private JTextField day1;
    private JTextField hour;
    private JTextField hour1;
    private JTextField minutes;
    private JTextField minutes1;
    private JTextField second;
    private JTextField second1;
    private JTextField interval1;
    private JCheckBox box;
    private JCheckBox active;

    public void changeTask(JTextArea textArea, TaskList tasks, Task t) {
        this.gui = new TaskGUI();
        this.gui.setGUI("Change", 450, 400);
        this.tasks = tasks;
        this.task = t;
        this.textArea = textArea;
        this.changedTask = t.clone();

        JLabel titleText = new JLabel("Title");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(titleText, this.gui.getC());
        this.title = new JTextField(this.task.getTitle(), 20);
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.title, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel yearText = new JLabel("Year");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(yearText, this.gui.getC());
        this.year = new JTextField("" + this.task.getStartTime().get(Calendar.YEAR), 4);
        //this.year.setDocument(getPlainDocument(4));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.year, this.gui.getC());
        this.year1 = new JTextField(this.task.getEndTime().get(Calendar.YEAR)+"", 4);
        //this.year1.setDocument(getPlainDocument(4));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.year1, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel mounthText = new JLabel("Mounth");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(mounthText, this.gui.getC());
        this.mounth = new JTextField((this.task.getStartTime().get(Calendar.MONTH)+1)+"", 2);
        //this.mounth.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.mounth, this.gui.getC());
        this.mounth1 = new JTextField((this.task.getEndTime().get(Calendar.MONTH)+1)+"", 2);
        // this.mounth1.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.mounth1, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel dayText = new JLabel("Day");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(dayText, this.gui.getC());
        this.day = new JTextField(this.task.getStartTime().get(Calendar.DAY_OF_MONTH)+"", 2);
        //this.day.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.day, this.gui.getC());
        this.day1 = new JTextField(this.task.getEndTime().get(Calendar.DAY_OF_MONTH)+"", 2);
        //this.day1.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(day1, gui.getC());

        this.gui.getC().gridy++;
        JLabel hourText = new JLabel("Hour");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(hourText, this.gui.getC());
        this.hour = new JTextField(this.task.getStartTime().get(Calendar.HOUR_OF_DAY)+"", 2);
        //this.hour.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.hour, this.gui.getC());
        this.hour1 = new JTextField(this.task.getEndTime().get(Calendar.HOUR_OF_DAY)+"", 2);
        //this.hour1.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.hour1, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel minutesText = new JLabel("Minutes");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(minutesText, this.gui.getC());
        this.minutes = new JTextField(this.task.getStartTime().get(Calendar.MINUTE )+"", 2);
        //this.minutes.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.minutes, this.gui.getC());
        this.minutes1 = new JTextField(this.task.getEndTime().get(Calendar.MINUTE )+"", 2);
        //this.minutes1.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.minutes1, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel secondText = new JLabel("Seconds");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(secondText, this.gui.getC());
        this.second = new JTextField(this.task.getStartTime().get(Calendar.SECOND )+"", 2);
        //this.minutes.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.second, this.gui.getC());
        this.second1 = new JTextField(task.getEndTime().get(Calendar.SECOND )+"", 2);
        //this.minutes1.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.second1, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel intervalText = new JLabel("Interval");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(intervalText, this.gui.getC());
        final JLabel interval = new JLabel("0");
        this.gui.getC().gridx = 1;
        this.gui.getP().add(interval,this.gui.getC());
        this.interval1 = new JTextField(this.task.getRepeatInterval() + "", 8);
        interval.setVisible(false);
        //this.interval1.setDocument(getPlainDocument(8));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.interval1, this.gui.getC());

        this.gui.getC().gridy++;
        this.gui.getC().gridx = 1;
        this.box = new JCheckBox();
        this.box.setSelected(this.task.isRepeated());
        if(!this.task.isRepeated()) {
            this.year1.setVisible(false);
            this.mounth1.setVisible(false);
            this.day1.setVisible(false);
            this.hour1.setVisible(false);
            this.minutes1.setVisible(false);
            this.second1.setVisible(false);
            this.interval1.setVisible(false);
            interval.setVisible(true);
        }
        box.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                ChangeGUI.this.year1.setVisible(!ChangeGUI.this.year1.isVisible());
                ChangeGUI.this.mounth1.setVisible(!ChangeGUI.this.mounth1.isVisible());
                ChangeGUI.this.day1.setVisible(!ChangeGUI.this.day1.isVisible());
                ChangeGUI.this.hour1.setVisible(!ChangeGUI.this.hour1.isVisible());
                ChangeGUI.this.minutes1.setVisible(!ChangeGUI.this.minutes1.isVisible());
                ChangeGUI.this.interval1.setVisible(!ChangeGUI.this.interval1.isVisible());
                ChangeGUI.this.second1.setVisible(!ChangeGUI.this.second1.isVisible());
                interval.setVisible(!interval.isVisible());
            }
        });
        this.gui.getP().add(this.box, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel activeText = new JLabel("Activity");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(activeText, this.gui.getC());
        this.gui.getC().gridx = 1;
        this.active = new JCheckBox();
        this.active.setSelected(this.task.isActive());
        this.gui.getP().add(this.active, this.gui.getC());

        this.gui.getC().gridy++;
        this.gui.getC().gridx = 0;
        ActionListener actionListener1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (GUIController.chekField(ChangeGUI.this.year, ChangeGUI.this.mounth, ChangeGUI.this.day, ChangeGUI.this.hour, ChangeGUI.this.minutes, ChangeGUI.this.second)
                        && (!ChangeGUI.this.box.isSelected() || ChangeGUI.this.box.isSelected() && GUIController.chekField(ChangeGUI.this.year1, ChangeGUI.this.mounth1, ChangeGUI.this.day1, ChangeGUI.this.hour1, ChangeGUI.this.minutes1, ChangeGUI.this.second1))) {
                    Calendar d1 = Calendar.getInstance();
                    d1.set(Calendar.YEAR, Integer.parseInt(ChangeGUI.this.year.getText()));
                    d1.set(Calendar.MONTH, Integer.parseInt(ChangeGUI.this.mounth.getText()) - 1);
                    d1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ChangeGUI.this.day.getText()));
                    d1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ChangeGUI.this.hour.getText()));
                    d1.set(Calendar.MINUTE, Integer.parseInt(ChangeGUI.this.minutes.getText()));
                    d1.set(Calendar.SECOND, Integer.parseInt(ChangeGUI.this.second.getText()));
                    d1.set(Calendar.MILLISECOND, 0);
                    d1.setTimeInMillis(d1.getTimeInMillis());
                    if (box.isSelected()) {
                        Calendar d2 = Calendar.getInstance();
                        d2.set(Calendar.YEAR, Integer.parseInt(ChangeGUI.this.year1.getText()));
                        d2.set(Calendar.MONTH, Integer.parseInt(ChangeGUI.this.mounth1.getText()) - 1);
                        d2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ChangeGUI.this.day1.getText()));
                        d2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ChangeGUI.this.hour1.getText()));
                        d2.set(Calendar.MINUTE, Integer.parseInt(ChangeGUI.this.minutes1.getText()));
                        d2.set(Calendar.SECOND, Integer.parseInt(ChangeGUI.this.second1.getText()));
                        d2.set(Calendar.MILLISECOND, 0);
                        d2.setTimeInMillis(d2.getTimeInMillis());
                        ChangeGUI.this.task.setActive(active.isSelected());
                        ChangeGUI.this.task.setTitle(title.getText());
                        ChangeGUI.this.task.setTime(d1, d2, Integer.parseInt(interval.getText()));
                    } else {
                        ChangeGUI.this.task.setActive(active.isSelected());
                        ChangeGUI.this.task.setTitle(title.getText());
                        ChangeGUI.this.task.setTime(d1);
                    }
                    GUIController.getTaskList().getTask( GUIController.getTaskList().getIndex(ChangeGUI.this.changedTask)).setTask(ChangeGUI.this.task);
                    GUIController.saveTasks();
                    ChangeGUI.this.tasks.getTask(  ChangeGUI.this.tasks.getIndex(ChangeGUI.this.changedTask)).setTask(ChangeGUI.this.task);
                    ChangeGUI.this.gui.getF().setVisible(false);
                    ChangeGUI.this.textArea.setText(GUIController.tasksToText(ChangeGUI.this.tasks));
                }
            }
        };
        JButton addButton = new JButton("Ok");
        addButton.addActionListener(actionListener1);
        this.gui.getP().add(addButton, this.gui.getC());
        this.gui.getF().setVisible(true);
    }

}
