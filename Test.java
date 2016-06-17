package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;

import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.LinkedTaskList;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.TaskIO;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        LinkedTaskList list = new LinkedTaskList();
        TaskIO.readText(list, new File("E:\\Program\\Java\\JavaPractice\\src\\ua\\edu\\sumdu\\j2se\\AndriySliahetskiy\\tasks\\Text.txt"));
        list.remove(list.getTask(4));
        TaskIO.writeText(list, new File("E:\\Program\\Java\\JavaPractice\\src\\ua\\edu\\sumdu\\j2se\\AndriySliahetskiy\\tasks\\Text.txt"));
    }
}
