package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;

public abstract class TaskList implements Iterable<Task>, Serializable {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public abstract TaskList createEmptyObj();

    public abstract int getIndex(Task task);

    public void add(TaskList tasks) {
        if(tasks == null) throw new IllegalArgumentException();
        for(int i = 0; i < tasks.size(); i++) {
            add(tasks.getTask(i));
        }
    }

    public TaskList incoming(Calendar from, Calendar to) {
        TaskList list = this.createEmptyObj();// if (this instanceOf ArrayTaskList)
        for (int i = 0; i < this.size(); i++) {
            Calendar time = this.getTask(i).nextTimeAfter(from);
            if(time.before(to)) {
                list.add(this.getTask(i));
            }
        }
        return list;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return  false;
        if(getClass() != obj.getClass()) return false;
        if(this == obj) return true;
        TaskList list = (TaskList) obj;
        if(this.size() != list.size()) return false;
        for(int i = 0; i < this.size(); i++) {
            if(!this.getTask(i).equals(list.getTask(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int a = -1;
        for (int i = 0; i <this.size(); i++) {
            a += this.getTask(i).hashCode();
        }
        return this.size() + a;
    }

    @Override
    public Iterator<Task> iterator() {
        return new TaskListIterator();
    }

    public class TaskListIterator implements Iterator<Task>{

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < TaskList.this.size();
        }

        @Override
        public Task next() {
            return TaskList.this.getTask(i++);
        }

        @Override
        public void remove() {
            if(i == 0) throw new IllegalStateException();
            TaskList.this.remove(TaskList.this.getTask(--i));
        }
    }

    @Override
    public TaskList clone(){
        TaskList list = this.createEmptyObj();
        for (int i = 0; i < this.size(); i++) {
            list.add((Task)this.getTask(i).clone());
        }
        return list;
    }
}
