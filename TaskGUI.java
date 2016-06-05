package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.View;


import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller.GUIController;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.*;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TaskGUI extends GridBagLayout {

    protected GridBagConstraints c;
    protected JFrame f;
    protected JPanel p;

    public TaskGUI() {

    }

    public void setGUI(String title, int x, int y) {
        this.f = new JFrame();
        this.getF().setTitle(title);
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

    public void setGUI(String title, int x, int y, WindowListener wndCloser) {
        this.f = new JFrame();
        this.getF().setTitle(title);
        this.p = new JPanel();
        this.p.setLayout(new GridBagLayout());
        this.c = new GridBagConstraints();
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.insets = new Insets(2, 2, 2, 2);
        this.c.weightx = 1.0;
        this.c.weighty = 1.0;
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

    public PlainDocument getPlainDocument(final int l) {
        return new PlainDocument() {
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
    }

    private Date getDate(int mounth, int day, int hour, int minutes) {
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

    public int getDaysFromMounth(int month) {
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

    public static int getDaysInMonth(int month) {
        long a = 31536000;
        a *= 1000;
        if(month == 1) return 31;
        if(month == 2) {
            Date d = new Date();
            if((d.getTime()/a + 2)%4 == 0 && !((d.getTime()/a - 30)%100 == 0) )return 29;
            else return 28;
        }
        if(month == 3) return 31;
        if(month == 4) return 30;
        if(month == 5) return 31;
        if(month == 6) return 30;
        if(month == 7) return 31;
        if(month == 8) return 31;
        if(month == 9) return 30;
        if(month == 10) return 31;
        if(month == 11) return 30;
        if(month == 12) return 31;
        return 0;
    }

    public String planeToText(SortedMap<Calendar, Set<Task>> plane) {
        String str = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(Map.Entry<Calendar, Set<Task>> entry : plane.entrySet()) {
            Date d = new Date();
            d.setTime(entry.getKey().getTimeInMillis());
            str += format.format(d) + "\n";
            for(Task task : entry.getValue()) {
                str += "\t" + GUIController.taskToText(task) + "\n";
            }
            str += "\n";
        }
        return str;
    }
}
