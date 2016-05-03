package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;

import java.awt.*;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args){
        LinkedTaskList list = new LinkedTaskList();
        ArrayTaskList arr = new ArrayTaskList();
        Date a = new Date();
        Date b = new Date();
        b.setTime(b.getTime() + 360 * 1000);
        Date c = new Date();
        c.setTime(c.getTime());
        Date d = new Date();
        d.setTime(d.getTime() + 360 * 1000);
        Task t1 = new Task("A", a, b, 6);
        Task t2 = new Task("B", c, d, 1);
        t1.setActive(true);
        t2.setActive(true);
        LinkedTaskList l1 = new LinkedTaskList();
        l1.add(t1);
        l1.add(t2);
        /*System.out.println(!a.after(a));
        System.out.println(t1);
        t1.setActive(true);
        System.out.println(t1.nextTimeAfter(t1.getStartTime()));
        Task t2 = (Task) t1.clone();
        Task t3 = (Task) t1.clone();
        t2.setTitle("B");
        t3.setTitle("C");
        list.add(t1);
        list.add(t2);
        list.add(t3);
        System.out.println(arr.remove(t2));
        System.out.println(arr.remove(t2));
        Iterator<Task> iter = list.iterator();
        for(Task t : arr){
            System.out.println(t.toString());
        }

        SortedMap<Date, Set<Task>> l2 = Tasks.calendar(l1, a, b);
        for(Map.Entry<Date,  Set<Task>> dat : l2.entrySet()) {

            System.out.println("\n" + dat.getKey());
            for (Task t: dat.getValue()) {
                System.out.println(t);
            }
        }*/
        SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss.mmm]");
        Date d1 = new Date();
        Font myFont = new Font("TimesRoman", Font.BOLD,   30);
        System.out.println(format.format(d1));
    }
}
