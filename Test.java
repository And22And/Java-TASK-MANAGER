package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        LinkedTaskList list = new LinkedTaskList();
        TaskIO.readText(list, new File("E:\\Program\\Java\\JavaPractice\\src\\ua\\edu\\sumdu\\j2se\\AndriySliahetskiy\\tasks\\Text.txt"));
        list.remove(list.getTask(4));
        TaskIO.writeText(list, new File("E:\\Program\\Java\\JavaPractice\\src\\ua\\edu\\sumdu\\j2se\\AndriySliahetskiy\\tasks\\Text.txt"));
    }
}
