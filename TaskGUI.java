package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TaskGUI  extends GridBagLayout {

    private GridBagConstraints c;
    private JFrame f;
    private JPanel p;


    public TaskGUI() {
        this.f = new JFrame();
        this.p = new JPanel();
        this.p.setLayout(new GridBagLayout());
        this.c = new GridBagConstraints();
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.insets = new Insets(2, 2, 2, 2);
        this.c.weightx = 1.0;
        this.c.weighty = 1.0;
        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        this.f.addWindowListener(wndCloser);
        this.f.getContentPane().add(p);
        this.f.setSize(600, 200);
        this.f.setVisible(true);
    }

    public TaskGUI(String title, int x, int y) {
        this.f = new JFrame();
        this.p = new JPanel();
        this.p.setLayout(new GridBagLayout());
        this.c = new GridBagConstraints();
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.insets = new Insets(2, 2, 2, 2);
        this.c.weightx = 1.0;
        this.c.weighty = 1.0;
        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                this.windowClosed(e);
            }
        };
        this.f.addWindowListener(wndCloser);
        this.f.getContentPane().add(p);
        this.f.setSize(x, y);
    }

    public JFrame getF() {
        return f;
    }

    public JPanel getP() {
        return p;
    }

    public GridBagConstraints getC() {
        return c;
    }

    public static void main(String[] args) {
        TaskIO.readText(GUIController.getTaskList(), GUIController.getF());
        TaskGUI gui = new TaskGUI();
        JButton showDayButton = new JButton("Find Tasks");
        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               findTaskGUI();
            }
        };
        showDayButton.addActionListener(actionListener1);
        JButton showAllButton = new JButton("Show all");
        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {windowWithTasks(GUIController.getTasks());
            }
        };
        JButton addTaskButton = new JButton("Add task");
        ActionListener actionListener3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTaskGUI();
            }
        };
        showDayButton.addActionListener(actionListener1);
        showAllButton.addActionListener(actionListener2);
        addTaskButton.addActionListener(actionListener3);
        gui.getP().add(showDayButton, gui.getC());
        gui.getC().gridx = 1;
        gui.getP().add(showAllButton, gui.getC());
        gui.getC().gridy = 1;
        gui.getC().gridx = 0;
        gui.getP().add(addTaskButton, gui.getC());
        gui.getF().setVisible(true);
    }

    public static void windowWithTasks(final TaskList tasks) {
        TaskGUI gui = new TaskGUI("Tasks", 600, 600);
        gui.getP().setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea(20, 20);
        textArea.setText(GUIController.tasksToText(tasks));
        textArea.setCaretPosition(0);
        final JScrollPane scrollPane = new JScrollPane(textArea);
        gui.getP().add(scrollPane, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        gui.getP().add(panel, BorderLayout.SOUTH);
        gui.getF().getContentPane().add(gui.getP());
        final JTextField text = new JTextField("", 4);
        text.setDocument(getPlainDocument(4));
        JButton button = new JButton("Delete");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.getText() != "") {
                    if (Integer.parseInt(text.getText()) >= GUIController.getTaskList().size()) text.setText("");
                    else {
                        tasks.remove(GUIController.getTaskList().getTask(Integer.parseInt(text.getText())));
                        GUIController.getTaskList().remove(GUIController.getTaskList().getTask(Integer.parseInt(text.getText())));
                    }
                    GUIController.saveTasks();
                    textArea.setText(GUIController.tasksToText(tasks));
                }
            }
        });
        gui.getP().add(text, BorderLayout.BEFORE_FIRST_LINE);
        gui.getP().add(button, BorderLayout.AFTER_LINE_ENDS);
        gui.getF().setPreferredSize(new Dimension(900, 600));
        gui.getF().pack();
        gui.getF().setLocationRelativeTo(null);
        gui.getF().setVisible(true);
    }

   public static void addTaskGUI() {
       final TaskGUI gui = new TaskGUI("Tasks", 400, 300);

       JLabel titleText = new JLabel("Title");
       gui.getC().gridx = 0;
       gui.getP().add(titleText, gui.getC());
       final JTextField title = new JTextField("", 20);
       gui.getC().gridx = 1;
       gui.getP().add(title, gui.getC());

       gui.getC().gridy++;
       JLabel yearText = new JLabel("Year");
       gui.getC().gridx = 0;
       gui.getP().add(yearText, gui.getC());
       final JTextField year = new JTextField("", 4);
       year.setDocument(getPlainDocument(4));
       gui.getC().gridx = 1;
       gui.getP().add(year, gui.getC());
       final JTextField year1 = new JTextField("", 4);
       year1.setDocument(getPlainDocument(4));
       gui.getC().gridx = 2;
       gui.getP().add(year1, gui.getC());

       gui.getC().gridy++;
       JLabel mounthText = new JLabel("Mounth");
       gui.getC().gridx = 0;
       gui.getP().add(mounthText, gui.getC());
       final JTextField mounth = new JTextField("", 2);
       mounth.setDocument(getPlainDocument(2));
       gui.getC().gridx = 1;
       gui.getP().add(mounth, gui.getC());
       final JTextField mounth1 = new JTextField("", 2);
       mounth1.setDocument(getPlainDocument(2));
       gui.getC().gridx = 2;
       gui.getP().add(mounth1, gui.getC());

       gui.getC().gridy++;
       JLabel dayText = new JLabel("Day");
       gui.getC().gridx = 0;
       gui.getP().add(dayText, gui.getC());
       final JTextField day = new JTextField("", 2);
       day.setDocument(getPlainDocument(2));
       gui.getC().gridx = 1;
       gui.getP().add(day, gui.getC());
       final JTextField day1 = new JTextField("", 2);
       day1.setDocument(getPlainDocument(2));
       gui.getC().gridx = 2;
       gui.getP().add(day1, gui.getC());

       gui.getC().gridy++;
       JLabel hourText = new JLabel("Hour");
       gui.getC().gridx = 0;
       gui.getP().add(hourText, gui.getC());
       final JTextField hour = new JTextField("", 2);
       hour.setDocument(getPlainDocument(2));
       gui.getC().gridx = 1;
       gui.getP().add(hour, gui.getC());
       final JTextField hour1 = new JTextField("", 2);
       hour1.setDocument(getPlainDocument(2));
       gui.getC().gridx = 2;
       gui.getP().add(hour1, gui.getC());

       gui.getC().gridy++;
       JLabel minutesText = new JLabel("Minutes");
       gui.getC().gridx = 0;
       gui.getP().add(minutesText, gui.getC());
       final JTextField minutes = new JTextField("", 2);
       minutes.setDocument(getPlainDocument(2));
       gui.getC().gridx = 1;
       gui.getP().add(minutes, gui.getC());
       final JTextField minutes1 = new JTextField("", 2);
       minutes1.setDocument(getPlainDocument(2));
       gui.getC().gridx = 2;
       gui.getP().add(minutes1, gui.getC());

       gui.getC().gridy++;
       JLabel intervalText = new JLabel("Interval");
       gui.getC().gridx = 0;
       gui.getP().add(intervalText, gui.getC());
       final JLabel interval = new JLabel("0");
       gui.getC().gridx = 1;
       gui.getP().add(interval, gui.getC());
       final JTextField interval1 = new JTextField("", 8);
       interval.setVisible(false);
       interval1.setDocument(getPlainDocument(8));
       gui.getC().gridx = 2;
       gui.getP().add(interval1, gui.getC());

       gui.getC().gridy++;
       gui.getC().gridx = 1;
       final JCheckBox box = new JCheckBox();
       box.setSelected(true);
       box.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                year1.setVisible(!year1.isVisible());
                mounth1.setVisible(!mounth1.isVisible());
                day1.setVisible(!day1.isVisible());
                hour1.setVisible(!hour1.isVisible());
                minutes1.setVisible(!minutes1.isVisible());
                interval1.setVisible(!interval1.isVisible());
                interval.setVisible(!interval.isVisible());
            }
       });
       gui.getP().add(box, gui.getC());

       gui.getC().gridy++;
       gui.getC().gridx = 0;
       ActionListener actionListener1 = new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               Calendar d1 = Calendar.getInstance();
               d1.set(Integer.parseInt(year.getText()), Integer.parseInt(mounth.getText())-1, Integer.parseInt(day.getText()), Integer.parseInt(hour.getText()), Integer.parseInt(minutes.getText()));
                if(box.isSelected()) {
                    Calendar d2= Calendar.getInstance();
                    d2.set(Integer.parseInt(year1.getText()), Integer.parseInt(mounth1.getText())-1, Integer.parseInt(day1.getText()), Integer.parseInt(hour1.getText()), Integer.parseInt(minutes1.getText()));
                    GUIController.getTaskList().add(new Task(title.getText(), d1, d2, Integer.parseInt(interval1.getText())));
                } else {
                    GUIController.getTaskList().add(new Task(title.getText(), d1));
                }
               GUIController.saveTasks();
               gui.getF().setVisible(false);
               try {
                   gui.finalize();
               } catch (Throwable throwable) {
                   throwable.printStackTrace();
               }
           }
       };
       JButton addButton = new JButton("Add");
       addButton.addActionListener(actionListener1);
       gui.getP().add(addButton, gui.getC());
       gui.getF().setVisible(true);
   }

    public static void findTaskGUI() {
        final TaskGUI gui = new TaskGUI("Tasks", 400, 300);

        JLabel yearText = new JLabel("Year");
        gui.getC().gridx = 0;
        gui.getP().add(yearText, gui.getC());
        final JTextField year = new JTextField("", 4);
        year.setDocument(getPlainDocument(4));
        gui.getC().gridx = 1;
        gui.getP().add(year, gui.getC());
        final JTextField year1 = new JTextField("", 4);
        year1.setDocument(getPlainDocument(4));
        gui.getC().gridx = 2;
        gui.getP().add(year1, gui.getC());

        gui.getC().gridy++;
        JLabel mounthText = new JLabel("Mounth");
        gui.getC().gridx = 0;
        gui.getP().add(mounthText, gui.getC());
        final JTextField mounth = new JTextField("", 2);
        mounth.setDocument(getPlainDocument(2));
        gui.getC().gridx = 1;
        gui.getP().add(mounth, gui.getC());
        final JTextField mounth1 = new JTextField("", 2);
        mounth1.setDocument(getPlainDocument(2));
        gui.getC().gridx = 2;
        gui.getP().add(mounth1, gui.getC());

        gui.getC().gridy++;
        JLabel dayText = new JLabel("Day");
        gui.getC().gridx = 0;
        gui.getP().add(dayText, gui.getC());
        final JTextField day = new JTextField("", 2);
        day.setDocument(getPlainDocument(2));
        gui.getC().gridx = 1;
        gui.getP().add(day, gui.getC());
        final JTextField day1 = new JTextField("", 2);
        day1.setDocument(getPlainDocument(2));
        gui.getC().gridx = 2;
        gui.getP().add(day1, gui.getC());

        gui.getC().gridy++;
        JLabel hourText = new JLabel("Hour");
        gui.getC().gridx = 0;
        gui.getP().add(hourText, gui.getC());
        final JTextField hour = new JTextField("", 2);
        hour.setDocument(getPlainDocument(2));
        gui.getC().gridx = 1;
        gui.getP().add(hour, gui.getC());
        final JTextField hour1 = new JTextField("", 2);
        hour1.setDocument(getPlainDocument(2));
        gui.getC().gridx = 2;
        gui.getP().add(hour1, gui.getC());

        gui.getC().gridy++;
        JLabel minutesText = new JLabel("Minutes");
        gui.getC().gridx = 0;
        gui.getP().add(minutesText, gui.getC());
        final JTextField minutes = new JTextField("", 2);
        minutes.setDocument(getPlainDocument(2));
        gui.getC().gridx = 1;
        gui.getP().add(minutes, gui.getC());
        final JTextField minutes1 = new JTextField("", 2);
        minutes1.setDocument(getPlainDocument(2));
        gui.getC().gridx = 2;
        gui.getP().add(minutes1, gui.getC());

        gui.getC().gridy++;
        JButton button = new JButton("Find");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                c1.set(Integer.parseInt(year.getText()), Integer.parseInt(mounth.getText())-1, Integer.parseInt(day.getText()), Integer.parseInt(hour.getText()), Integer.parseInt(minutes.getText()));
                c2.set(Integer.parseInt(year1.getText()), Integer.parseInt(mounth1.getText())-1, Integer.parseInt(day1.getText()), Integer.parseInt(hour1.getText()), Integer.parseInt(minutes1.getText()));
                LinkedTaskList list = (LinkedTaskList) Tasks.incoming(GUIController.getTaskList(),c1, c2);
                windowWithTasks(list);
            }
        });
        gui.getP().add(button, gui.getC());

        gui.getF().setVisible(true);
    }

    public static PlainDocument getPlainDocument(final int l) {
        PlainDocument pd = new PlainDocument() {
            String chars = "0123456789";
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (chars.contains(str)) {
                    if (getLength()< l) {
                        super.insertString(offs, str, a);
                    }
                }
            }
        };
        return  pd;
    }

    private static Date getDate(int mounth, int day, int hour, int minutes) {
        Date date = new Date();
        long a = 31536000;
        a *= 1000;
        Date d1 = new Date();
        d1.setTime(date.getTime() - date.getTime() % a);
        System.out.println(d1);
        date.setTime(date.getTime() - date.getTime() % a + minutes * 60000 + hour * 3600000 + day * 86400000 + getDaysFromMounth(mounth) * 86400000);
        System.out.println(date);
        return date;
    }

    public static int getDaysFromMounth(int month) {
        int days = 0;
        long a = 31536000;
        a *= 1000;
        if(month >= 1) days += 31;
        if(month >= 2) {
            Date d = new Date();
            if((d.getTime()/a + 2)%4 == 0 && !((d.getTime()/a - 30)%100 == 0) ) days += 29;
            else days += 28;
        }
        if(month >= 3) days += 31;
        if(month >= 4) days += 30;
        if(month >= 5) days += 31;
        if(month >= 6) days += 30;
        if(month >= 7) days += 31;
        if(month >= 8) days += 31;
        if(month >= 9) days += 30;
        if(month >= 10) days += 31;
        if(month >= 11) days += 30;
        if(month == 12) days += 31;
        return days;
    }

}
