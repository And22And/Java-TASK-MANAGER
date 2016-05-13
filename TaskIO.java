package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;


import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskIO {

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
                    output.writeLong(t.getStartTime().getTime());
                    output.writeLong(t.getEndTime().getTime());
                } else {
                    output.writeLong(t.getStartTime().getTime());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(TaskList tasks, Writer out) { //– записує задачі зі списку у потік в текстовому    форматі, описаному нижче.
        try {
            SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss.mmm]");
            for(int i = 0; i < tasks.size(); i++) {
                Task t = tasks.getTask(i);
                out.write('\"' + t.getTitle().replace("\"", "\"\"") + '\"');
                if(t.isRepeated()) {
                    out.write(" from " + format.format(t.getStartTime()));
                    out.write(" to " + format.format(t.getEndTime().getTime()));
                    out.write(" every " + intToTime(t.getRepeatInterval()));
                } else {
                    out.write(format.format(t.getStartTime().getTime()));
                }
                if(!t.isActive()) out.write(" inactive");
                if(i != tasks.size()-1) out.write(";\n");
                else out.write(".");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                    Date d1 = new Date();
                    d1.setTime(input.readLong());
                    Date d2 = new Date();
                    d2.setTime(input.readLong());
                    Task t = new Task(str, d1, d2, rep);
                    t.setActive(b);
                    tasks.add(t);
                } else {
                    Date d = new Date();
                    d.setTime(input.readLong());
                    Task t = new Task(str, d);
                    t.setActive(b);
                    tasks.add(t);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(TaskList tasks, Reader in) { //  – зчитує задачі із потоку у список.
        try {
            BufferedReader reader = new BufferedReader(in);
            SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss.mmm]");
            String str;
            do {
                str = reader.readLine();
                String title = str.substring(str.indexOf('\"'), str.lastIndexOf('\"')+1);
                str = str.substring(str.lastIndexOf('\"'));
                if(str.contains("at")) {
                    Date d1 = format.parse(str.substring(str.indexOf('['), str.indexOf(']')+1));
                    str = str.substring(str.indexOf(']')+1);
                    Task t = new Task(title, d1);
                    if(!str.contains("inactive")) {
                        t.setActive(true);
                    }
                    tasks.add(t);
                }
                if(str.contains("from")) {
                    Date d1 = format.parse(str.substring(str.indexOf('['), str.indexOf(']')+1));
                    str = str.substring(str.indexOf(']')+1);
                    Date d2 = format.parse(str.substring(str.indexOf('['), str.indexOf(']')+1));
                    str = str.substring(str.indexOf(']')+1);
                    Task t = new Task(title, d1, d2, timeToInt(str.substring(str.indexOf('['), str.indexOf(']')+1)));
                    if(!str.contains("inactive")) {
                        t.setActive(true);
                    }
                    tasks.add(t);
                }
            } while(str.charAt(str.length() - 1) != '.');
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeBinary(TaskList tasks, File file) { //– записує задачі із списку у файл.
        try {
            write(tasks, new FileOutputStream(file));
            /*Writer output = new OutputStreamWriter(new FileOutputStream(file));
            output.write(tasks.size());
            for(int i = 0; i < tasks.size(); i++) {
                Task t = tasks.getTask(i);
                output.write(t.getTitle().length());
                for(int j = 0; j < t.getTitle().length(); j++) {
                    output.write(t.getTitle().charAt(j));
                }
                output.write(t.isActive() ? 1 : 0);
                output.write(t.getRepeatInterval());
                if(t.isRepeated()) {
                    output.write((int)t.getStartTime().getTime());
                    output.write((int)t.getEndTime().getTime());
                } else {
                    output.write((int)t.getStartTime().getTime());
                }
                output.flush();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeText(TaskList tasks, File file) {  //– записує задачі у файл у текстовому форматі
        try{
            FileWriter out = new FileWriter("E:\\Program\\Java\\JavaPractice\\src\\ua\\edu\\sumdu\\j2se\\AndriySliahetskiy\\tasks\\Text.txt", false);
            write(tasks, out);
           /* SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss.mmm]");
            for(int i = 0; i < tasks.size(); i++) {
                Task t = tasks.getTask(i);
                out.write('\"' + t.getTitle().replace("\"", "\"\"") + '\"');
                if(t.isRepeated()) {
                    out.write(" from " + format.format(t.getStartTime()));
                    out.write(" to " + format.format(t.getEndTime().getTime()));
                    out.write(" every " + intToTime(t.getRepeatInterval()));
                } else {
                    out.write(format.format(t.getStartTime().getTime()));
                }
                if(!t.isActive()) out.write("inactive");
                if(i != tasks.size()-1) out.write(";\n");
                else out.write(".");
                out.flush();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBinary(TaskList tasks, File file) { //– зчитує задачі із файлу у список задач.
        try {
            DataInputStream input = new DataInputStream(new FileInputStream(file));
            read(tasks, input);
            /*int n = input.readInt();
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
                    Date d1 = new Date();
                    d1.setTime(input.readLong());
                    Date d2 = new Date();
                    d2.setTime(input.readLong());
                    Task t = new Task(str, d1, d2, rep);
                    tasks.add(t);
                } else {
                    Date d = new Date();
                    d.setTime(input.readLong());
                    Task t = new Task(str, d);
                    tasks.add(t);
                }
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readText(TaskList tasks, File file) { //– зчитує задачі із файлу у текстовому вигляді.
        try {
            read(tasks, new FileReader(file));
            /*BufferedReader reader = new BufferedReader(new FileReader(file));
            SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss.mmm]");
            String str;
            do {
                str = reader.readLine();
                String title = str.substring(str.indexOf('\"'), str.lastIndexOf('\"')+1);
                str = str.substring(str.lastIndexOf('\"'));
                if(str.contains("at")) {
                    Date d1 = format.parse(str.substring(str.indexOf('['), str.indexOf(']')+1));
                    str = str.substring(str.indexOf(']')+1);
                    Task t = new Task(title, d1);
                    if(!str.contains("inactive")) {
                        t.setActive(true);
                    }
                    tasks.add(t);
                }
                if(str.contains("from")) {
                    Date d1 = format.parse(str.substring(str.indexOf('['), str.indexOf(']')+1));
                    str = str.substring(str.indexOf(']')+1);
                    Date d2 = format.parse(str.substring(str.indexOf('['), str.indexOf(']')+1));
                    str = str.substring(str.indexOf(']')+1);
                    Task t = new Task(title, d1, d2, timeToInt(str.substring(str.indexOf('['), str.indexOf(']')+1)));
                    if(!str.contains("inactive")) {
                        t.setActive(true);
                    }
                    tasks.add(t);
                }
            } while(str.charAt(str.length() - 1) != '.');*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String intToTime(int sec) {
        String time = "[";
        int seconds = sec;
        int days = seconds/86400;
        seconds -= days*86400;
        int hours = seconds/3600;
        seconds -= hours*3600;
        int minutes = seconds/60;
        seconds %= 60;
        if(days != 0) {
            time += days + "day";
            if(days > 1) time += 's';
        }
        if(hours != 0) {
            time += hours + "hour";
            if(hours > 1) time += 's';
        }
        if(minutes != 0) {
            time += minutes + "minute";
            if(minutes > 1) time += 's';
        }
        if(seconds != 0) {
            time += seconds + "second";
            if(seconds > 1) time += 's';
        }
        time += ']';
        return time;
    }

    private static int timeToInt(String time) {
        int sec = 0;
        String str = time.substring(1).trim();
        int i = 0;
        if(str.contains("day")) {
            while(Character.isDigit(str.charAt(i))) i++;
            sec += 86400 * Integer.parseInt(str.substring(0, i).trim());
            str = str.substring(i + 4).trim();
        }
        if(str.contains("hour")) {
            while(Character.isDigit(str.charAt(i))) i++;
            sec += 3600 * Integer.parseInt(str.substring(0, i).trim());
            str = str.substring(i + 5).trim();
        }
        if(str.contains("minute")) {
            while(Character.isDigit(str.charAt(i))) i++;
            sec += 60 * Integer.parseInt(str.substring(0, i).trim());
            str = str.substring(i + 7).trim();
        }
        if(str.contains("second")) {
            while(Character.isDigit(str.charAt(i))) i++;
            sec += Integer.parseInt(str.substring(0, i).trim());
        }
        return sec;
    }

}
