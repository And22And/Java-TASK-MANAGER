package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GUIController {

    private static File f = new File("E:\\Program\\Java\\JavaPractice\\src\\ua\\edu\\sumdu\\j2se\\AndriySliahetskiy\\tasks\\Text.txt");
    private  static TaskList taskList = new LinkedTaskList();

    public static File getF() {
        return f;
    }

    public static TaskList getTaskList() {
        return taskList;
    }

    public static LinkedTaskList getTasks() {
        LinkedTaskList list = new LinkedTaskList();
        TaskIO.readText(list, f);
        return list;
    }

    public  static void saveTasks() {
        TaskIO.writeText(taskList, f);
    }

    public static LinkedTaskList getTasksForTime(int seconds) {
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d2.setTimeInMillis(d2.getTimeInMillis() + seconds);
        LinkedTaskList list = new LinkedTaskList();
        TaskIO.readText(list, f);
        list = (LinkedTaskList)Tasks.incoming(list, d1, d2);
        return list;
    }

    public static String tasksToText(TaskList tasks) {
        SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss.mmm]");
        String str = "";
        Date d1 = new Date();
        for(int i = 0; i < tasks.size(); i++) {
            Task t = tasks.getTask(i);
            str += GUIController.getTaskList().getIndex(t) + ". ";
            str += '\"' + t.getTitle().replace("\"", "\"\"") + '\"';
            if (t.isRepeated()) {
                d1.setTime(t.getStartTime().getTimeInMillis());
                str += " from " + format.format(d1);
                d1.setTime(t.getEndTime().getTimeInMillis());
                str += " to " + format.format(d1);
                str += " every " + TaskIO.intToTime(t.getRepeatInterval());
            } else {
                str += " at ";
                d1.setTime(t.getStartTime().getTimeInMillis());
                str += format.format(d1);
            }
            if (!t.isActive())  str += " inactive";
            if (i != tasks.size() - 1)  str += ";\n";
            else  str += ".";
        }
        return str;
    }

    public static Task textToTask(String str) {
        SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss.mmm]");
        String title = str.substring(str.indexOf('\"') + 1, str.lastIndexOf('\"'));
        title = title.replace("\"\"", "\"");
        str = str.substring(str.lastIndexOf('\"'));
        if (str.contains("at")) {
            Calendar d1 = Calendar.getInstance();
            try {
                d1.setTimeInMillis(format.parse(str.substring(str.indexOf('['), str.indexOf(']') + 1)).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            str = str.substring(str.indexOf(']') + 1);
            Task t = new Task(title, d1);
            if (!str.contains("inactive")) {
                t.setActive(true);
            }
            return t;
        }
        if (str.contains("from")) {
            Calendar d1 = Calendar.getInstance();
            try {
                d1.setTimeInMillis(format.parse(str.substring(str.indexOf('['), str.indexOf(']') + 1)).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            str = str.substring(str.indexOf(']') + 1);
            Calendar d2 = Calendar.getInstance();
            try {
                d2.setTimeInMillis(format.parse(str.substring(str.indexOf('['), str.indexOf(']') + 1)).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            str = str.substring(str.indexOf(']') + 1);
            Task t = new Task(title, d1, d2, TaskIO.timeToInt(str.substring(str.indexOf('['), str.indexOf(']') + 1)));
            if (!str.contains("inactive")) {
                t.setActive(true);
            }
            return t;
        }
        return null;
    }
}
