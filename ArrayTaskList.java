package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model;


import java.util.Arrays;
import org.apache.log4j.Logger;

public class ArrayTaskList extends TaskList{

    final private static Logger log = Logger.getLogger(TaskList.class);

    private Task tasks[];

    public ArrayTaskList() {
        this.tasks = new Task[0];
    }

    public ArrayTaskList createEmptyObj() {
        return new ArrayTaskList();
    }

    @Override
    public int getIndex(Task task) {
        for (int i = 0; i < size(); i++) {
            if(task.equals(getTask(i))) return i;
        }
        return  -1;
    }

    public void add(Task task) {
        if(task == null) {
            IllegalArgumentException e = new IllegalArgumentException();
            log.error(e);
            throw e;
        }
        Task newtasks[] = new Task[this.size()+1];
        int i;
        for (i = 0; i < this.size(); i++) {
            newtasks[i] =  this.getTask(i);
        }
        newtasks[i] = task;
        this.tasks = newtasks;
    }

    public boolean remove(Task task) {
        boolean is = false;
        for (int i = 0; i < this.size(); i++) {
            if(this.tasks[i].equals(task)) {
                Task newtasks[] = new Task[this.size()-1];
                int j;
                is = true;
                for (j = 0; j < i; j++) {
                    newtasks[j] =  this.getTask(j);
                }
                for(;j < this.size() - 1; j++, i++) {
                    newtasks[j] = this.getTask(j+1);
                }
                this.tasks = newtasks;
            }
        }
        return is;
    }

    public int size() {  //– метод, що повертає кількість задач у списку.
        return this.tasks.length;
    }

    public Task getTask(int index) { //– метод, що повертає задачу, яка знаходиться на вказаному місці у списку, перша задача має індекс 0.
        return this.tasks[index];
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasks=" + Arrays.toString(tasks) +
                '}';
    }
}
