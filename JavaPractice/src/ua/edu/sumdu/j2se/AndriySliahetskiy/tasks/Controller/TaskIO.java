package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.Task;
import ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model.TaskList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TaskIO {

    final private static Logger log = Logger.getLogger(TaskIO.class);

    public static void write(TaskList tasks, OutputStream out) { //– записує задачі із списку у потік у бінарному форматі, описаному нижче.
        try {
            DataOutput output = new DataOutputStream(out);
            output.writeInt(tasks.size());
            for(int i = 0; i < tasks.size(); i++) {
                Task t = tasks.getTask(i);
                output.writeInt(t.getTitle().length());
                for(int j = 0; j < t.getTitle().length(); j++) {
                    output.writeChar(t.getTitle().charAt(j));
                }
                output.writeBoolean(t.isActive());
                output.writeInt(t.getRepeatInterval());
                if(t.isRepeated()) {
                    output.writeLong(t.getStartTime().getTimeInMillis());
                    output.writeLong(t.getEndTime().getTimeInMillis());
                } else {
                    output.writeLong(t.getStartTime().getTimeInMillis());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
        finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e);
            }
        }
    }

    public static void write(TaskList tasks, Writer out) { //– записує задачі зі списку у потік в текстовому    форматі, описаному нижче.
        try {
            SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss.SSS]");
            Date d = new Date();
            for(int i = 0; i < tasks.size(); i++) {
                Task t = tasks.getTask(i);
                out.write('\"' + t.getTitle().replace("\"", "\"\"") + '\"');
                if(t.isRepeated()) {
                    d.setTime(t.getStartTime().getTimeInMillis());
                    out.write(" from " + format.format(d));
                    d.setTime(t.getEndTime().getTimeInMillis());
                    out.write(" to " + format.format(d));
                    out.write(" every " + intToTime(t.getRepeatInterval()));
                } else {
                    out.write(" at ");
                    d.setTime(t.getStartTime().getTimeInMillis());
                    out.write(format.format(d));
                }
                if(!t.isActive()) out.write(" inactive");
                if(i != tasks.size()-1) out.write(";\n");
                else out.write(".");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
        finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e);
            }
        }
    }

    public static void read(TaskList tasks, InputStream in) { //– зчитує задачі із потоку у даний список    задач.
        try {
            DataInput input = new DataInputStream(in);
            int n = input.readInt();
            for(int i = 0; i < n; i++) {

                int l = input.readInt();
                String str = new String();
                for(int j = 0; j < l; j++) {
                    str += input.readChar();
                }
                boolean b;
                b = input.readBoolean();
                int rep =input.readInt();
                if(rep != 0) {
                    Calendar d1 = Calendar.getInstance();
                    d1.setTimeInMillis(input.readLong());
                    Calendar d2 = Calendar.getInstance();
                    d2.setTimeInMillis(input.readLong());
                    Task t = new Task(str, d1, d2, rep);
                    t.setActive(b);
                    tasks.add(t);
                } else {
                    Calendar d = Calendar.getInstance();
                    d.setTimeInMillis(input.readLong());
                    Task t = new Task(str, d);
                    t.setActive(b);
                    tasks.add(t);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e);
            }
        }
    }

    public static void read(TaskList tasks, Reader in) { //  – зчитує задачі із потоку у список.
        try {
            BufferedReader reader = new BufferedReader(in);
            SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss.SSS]");
            String str = reader.readLine();
            while(str != null && !str.isEmpty()) {
                String title = str.substring(str.indexOf('\"')+1, str.lastIndexOf('\"'));
                title = title.replace("\"\"", "\"");
                str = str.substring(str.lastIndexOf('\"'));
                if(str.contains("at")) {
                    Calendar d1 = Calendar.getInstance();
                    d1.setTimeInMillis(format.parse(str.substring(str.indexOf('['), str.indexOf(']')+1)).getTime());
                    str = str.substring(str.indexOf(']')+1);
                    Task t = new Task(title, d1);
                    if(!str.contains("inactive")) {
                        t.setActive(true);
                    }
                    tasks.add(t);
                }
                if(str.contains("from")) {
                    Calendar d1 = Calendar.getInstance();
                    d1.setTimeInMillis(format.parse(str.substring(str.indexOf('['), str.indexOf(']')+1)).getTime());
                    str = str.substring(str.indexOf(']')+1);
                    Calendar d2 = Calendar.getInstance();
                    d2.setTimeInMillis(format.parse(str.substring(str.indexOf('['), str.indexOf(']')+1)).getTime());
                    str = str.substring(str.indexOf(']')+1);
                    Task t = new Task(title, d1, d2, timeToInt(str.substring(str.indexOf('['), str.indexOf(']')+1)));
                    if(!str.contains("inactive")) {
                        t.setActive(true);
                    }
                    tasks.add(t);
                }
                str = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e);
            }
        }
    }

    public static void writeBinary(TaskList tasks, File file) { //– записує задачі із списку у файл.
        try {
            write(tasks, new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public static void writeText(TaskList tasks, File file) {  //– записує задачі у файл у текстовому форматі
        try{
            write(tasks, new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public static void readBinary(TaskList tasks, File file) { //– зчитує задачі із файлу у список задач.
        try {
            read(tasks,  new DataInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public static void readText(TaskList tasks, File file) { //– зчитує задачі із файлу у текстовому вигляді.
        try {
            read(tasks, new FileReader(file));
        }
        catch (FileNotFoundException e) {
            log.info("Text.txt not find. Create");
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
                log.error(e1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public static String intToTime(int sec) {
        String time = "[";
        int seconds = sec;
        int days = seconds/86400;
        seconds -= days*86400;
        int hours = seconds/3600;
        seconds -= hours*3600;
        int minutes = seconds/60;
        seconds %= 60;
        if(days != 0) {
            time += days + " day";
            if(days > 1) time += 's';
            time += " ";
        }
        if(hours != 0) {
            time += hours + " hour";
            if(hours > 1) time += 's';
            time += " ";
        }
        if(minutes != 0) {
            time += minutes + " minute";
            if(minutes > 1) time += 's';
            time += " ";
        }
        if(seconds != 0) {
            time += seconds + " second";
            if(seconds > 1) time += 's';
        }
        time += ']';
        return time;
    }

    public static int timeToInt(String time) {
        int sec = 0;
        String str = time.substring(1).trim();
        int i = 0;
        if(str.contains("day")) {
            while(Character.isDigit(str.charAt(i))) i++;
            sec += 86400 * Integer.parseInt(str.substring(0, i).trim());
            str = str.substring(i + 5).trim();
        }
        if(str.contains("hour")) {
            while(Character.isDigit(str.charAt(i))) i++;
            sec += 3600 * Integer.parseInt(str.substring(0, i).trim());
            str = str.substring(i + 6).trim();
        }
        if(str.contains("minute")) {
            while(Character.isDigit(str.charAt(i))) i++;
            sec += 60 * Integer.parseInt(str.substring(0, i).trim());
            str = str.substring(i + 8).trim();
        }
        if(str.contains("second")) {
            while(Character.isDigit(str.charAt(i))) i++;
            sec += Integer.parseInt(str.substring(0, i).trim());
        }
        return sec;
    }



}
