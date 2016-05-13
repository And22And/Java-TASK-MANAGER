package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
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
        Task t3 = t2.clone();
        t3.setActive(false);
        l1.add(t3);
        File f = new File("E:\\Program\\Java\\JavaPractice\\src\\ua\\edu\\sumdu\\j2se\\AndriySliahetskiy\\tasks\\Text.txt");
        TaskIO.writeText(l1, f);
        ArrayTaskList arr = new ArrayTaskList();
        TaskIO.readText(arr, f);
        System.out.println(arr);
    }
}
