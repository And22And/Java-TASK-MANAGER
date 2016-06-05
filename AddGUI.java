package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.View;

import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller.GUIController;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.Task;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.TaskList;

import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import org.apache.log4j.Logger;

public class AddGUI extends TaskGUI{

    final private static Logger log = Logger.getLogger(AddGUI.class);

    private TaskGUI gui;
    private JTextArea textArea;
    private TaskList tasks;
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

    public void addTaskGUI(final JTextArea textArea, final TaskList tasks) {
        this.gui = new TaskGUI();
        this.gui.setGUI("Change", 450, 400);
        this.tasks = tasks;
        this.textArea = textArea;

        JLabel titleText = new JLabel("Title");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(titleText, this.gui.getC());
        this.title = new JTextField("", 20);
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.title, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel yearText = new JLabel("Year");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(yearText, this.gui.getC());
        this.year = new JTextField("", 4);
        this.year.setDocument(getPlainDocument(4));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.year, this.gui.getC());
        this.year1 = new JTextField("", 4);
        this.year1.setDocument(getPlainDocument(4));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.year1, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel mounthText = new JLabel("Mounth");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(mounthText, this.gui.getC());
        this.mounth = new JTextField("", 2);
        this.mounth.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.mounth, this.gui.getC());
        this.mounth1 = new JTextField("", 2);
         this.mounth1.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.mounth1, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel dayText = new JLabel("Day");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(dayText, this.gui.getC());
        this.day = new JTextField("", 2);
        this.day.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.day, this.gui.getC());
        this.day1 = new JTextField("", 2);
        this.day1.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(day1, gui.getC());

        this.gui.getC().gridy++;
        JLabel hourText = new JLabel("Hour");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(hourText, this.gui.getC());
        this.hour = new JTextField("", 2);
        this.hour.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.hour, this.gui.getC());
        this.hour1 = new JTextField("", 2);
        this.hour1.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.hour1, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel minutesText = new JLabel("Minutes");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(minutesText, this.gui.getC());
        this.minutes = new JTextField("", 2);
        this.minutes.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.minutes, this.gui.getC());
        this.minutes1 = new JTextField("", 2);
        this.minutes1.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.minutes1, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel secondText = new JLabel("Seconds");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(secondText, this.gui.getC());
        this.second = new JTextField("", 2);
        this.minutes.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 1;
        this.gui.getP().add(this.second, this.gui.getC());
        this.second1 = new JTextField("", 2);
        this.minutes1.setDocument(getPlainDocument(2));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.second1, this.gui.getC());

        this.gui.getC().gridy++;
        JLabel intervalText = new JLabel("Interval");
        this.gui.getC().gridx = 0;
        this.gui.getP().add(intervalText, this.gui.getC());
        final JLabel interval = new JLabel("0");
        this.gui.getC().gridx = 1;
        this.gui.getP().add(interval,this.gui.getC());
        this.interval1 = new JTextField("", 8);
        interval.setVisible(false);
        this.interval1.setDocument(getPlainDocument(8));
        this.gui.getC().gridx = 2;
        this.gui.getP().add(this.interval1, this.gui.getC());

        this.gui.getC().gridy++;
        this.gui.getC().gridx = 1;
        this.box = new JCheckBox();
        this.box.setSelected(true);
        box.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                AddGUI.this.year1.setVisible(!AddGUI.this.year1.isVisible());
                AddGUI.this.mounth1.setVisible(!AddGUI.this.mounth1.isVisible());
                AddGUI.this.day1.setVisible(!AddGUI.this.day1.isVisible());
                AddGUI.this.hour1.setVisible(!AddGUI.this.hour1.isVisible());
                AddGUI.this.minutes1.setVisible(!AddGUI.this.minutes1.isVisible());
                AddGUI.this.interval1.setVisible(!AddGUI.this.interval1.isVisible());
                AddGUI.this.second1.setVisible(!AddGUI.this.second1.isVisible());
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
        this.gui.getP().add(this.active, this.gui.getC());

        this.gui.getC().gridy++;
        this.gui.getC().gridx = 0;
        ActionListener actionListener1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (GUIController.chekField(AddGUI.this.year, AddGUI.this.mounth, AddGUI.this.day, AddGUI.this.hour, AddGUI.this.minutes, AddGUI.this.second)
                        && (!AddGUI.this.box.isSelected() || AddGUI.this.box.isSelected() && GUIController.chekField(AddGUI.this.year1, AddGUI.this.mounth1, AddGUI.this.day1, AddGUI.this.hour1, AddGUI.this.minutes1, AddGUI.this.second1))) {
                    Task t = new Task();
                    Calendar d1 = Calendar.getInstance();
                    d1.set(Integer.parseInt(AddGUI.this.year.getText()), Integer.parseInt(AddGUI.this.mounth.getText()) - 1, Integer.parseInt(AddGUI.this.day.getText()), Integer.parseInt(AddGUI.this.hour.getText()), Integer.parseInt(AddGUI.this.minutes.getText()));
                    d1.set(Calendar.SECOND, Integer.parseInt(AddGUI.this.second.getText()));
                    d1.set(Calendar.MILLISECOND, 0);
                    if (box.isSelected()) {
                        Calendar d2 = Calendar.getInstance();
                        d2.set(Calendar.YEAR, Integer.parseInt(AddGUI.this.year1.getText()));
                        d2.set(Calendar.MONTH, Integer.parseInt(AddGUI.this.mounth1.getText()) - 1);
                        d2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(AddGUI.this.day1.getText()));
                        d2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(AddGUI.this.hour1.getText()));
                        d2.set(Calendar.MINUTE, Integer.parseInt(AddGUI.this.minutes1.getText()));
                        d2.set(Calendar.SECOND, Integer.parseInt(AddGUI.this.second1.getText()));
                        d2.set(Calendar.MILLISECOND, 0);
                        d2.setTimeInMillis(d2.getTimeInMillis());
                        System.out.println(d2);
                        d2.set(Calendar.SECOND, Integer.parseInt(AddGUI.this.second1.getText()));
                        t.setTime(d1, d2, Integer.parseInt(interval1.getText()));
                    } else {
                        t.setTime(d1);
                    }
                    t.setActive(active.isSelected());
                    t.setTitle(title.getText());
                    GUIController.getTaskList().add(t);
                    GUIController.saveTasks();
                    AddGUI.this.tasks.add(t);
                    AddGUI.this.textArea.setText(GUIController.tasksToText(AddGUI.this.tasks));
                }
            }
        };
        JButton addButton = new JButton("Add");
        addButton.addActionListener(actionListener1);
        this.gui.getP().add(addButton, this.gui.getC());
        this.gui.getF().setVisible(true);
   }
}
