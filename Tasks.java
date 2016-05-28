package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model;

import java.util.*;

public class Tasks{

  public static Iterable<Task> incoming(Iterable<Task> tasks, Calendar start, Calendar end){
        LinkedTaskList list = new LinkedTaskList();
        for(Task t: tasks) {
            if(t.nextTimeAfter(start) != null && !end.before(t.nextTimeAfter(start))) {
                list.add(t);
            }
        }
      return list;
    }

    public static Iterable<Task> incoming(Iterable<Task> tasks, Calendar start, Calendar end, boolean bool){
        LinkedTaskList list = new LinkedTaskList();
        for(Task t: tasks) {
            if(t.nextTimeAfter(start, bool) != null && !end.before(t.nextTimeAfter(start, bool))) {
                list.add(t);
            }
        }
        return list;
    }

    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Calendar start, Calendar end) {
        SortedMap<Date, Set<Task>> map = new TreeMap<Date, Set<Task>>();
        Calendar st = (Calendar)start.clone();
        st.setTimeInMillis(st.getTimeInMillis() - 1000);
        for(Task task: tasks) {
            Calendar date;
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
                date.add(Calendar.SECOND, task.getRepeatInterval());
            }
        }
        return map;
    }
}
