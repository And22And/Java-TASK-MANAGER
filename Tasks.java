package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;

import java.util.*;

public class Tasks{

  public static Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end){
        LinkedTaskList list = new LinkedTaskList();
        for(Task t: tasks) {
            if(t.nextTimeAfter(start) != null && !end.before(t.nextTimeAfter(start))) {
                list.add(t);
            }
        }
      return list;
    }

    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end) {
        SortedMap<Date, Set<Task>> map = new TreeMap<Date, Set<Task>>();
        Date st = (Date)start.clone();
        st.setTime(st.getTime() - 1000);
        for(Task task: tasks) {
            Date date;
            date = task.nextTimeAfter(st);
            while(date != null && !end.before(date)) {
                if (map.containsKey(date)) {
                    map.get(date).add(task);
                }
                else {
                    HashSet<Task> set = new HashSet<Task>();
                    set.add(task);
                    map.put((Date) date.clone(), set);
                }
                date.setTime(date.getTime() + task.getRepeatInterval() * 1000);
            }
        }
        return map;
    }
}