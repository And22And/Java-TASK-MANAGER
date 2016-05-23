package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;


import java.util.Calendar;
import java.util.Date;

public class ArrayTaskList1 {

    private Task tasks[];

    public ArrayTaskList1() {
        this.tasks = new Task[0];
    }

    public void add(Task task) {  //– метод, що додає до списку вказану задачу.
        if(task == null) throw new IllegalArgumentException();
        Task newtasks[] = new Task[this.tasks.length+1];
        int i;
        for (i = 0; i < this.tasks.length; i++) {
            newtasks[i] =  this.tasks[i];
        }
        newtasks[i] = task;
        this.tasks = newtasks;
    }

    public boolean remove(Task task) { //– метод, що видаляє задачу із списку і повертає істину, якщо така задача була у списку. Якщо у списку було декілька таких задач, необхідно видалити однубудь-яку.
        boolean is = false;
        for (int i = 0; i < this.tasks.length; i++) {
            if(this.tasks[i] == task) {
                Task newtasks[] = new Task[this.tasks.length-1];
                int j;
                is = true;
                for (j = 0; j < i; j++) {
                    newtasks[j] =  this.tasks[j];
                }
                for(;j < this.tasks.length - 1; j++, i++) {
                    newtasks[j] = this.tasks[j+1];
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

    public ArrayTaskList1 incoming(Calendar from,Calendar to) {
        ArrayTaskList1 list = new ArrayTaskList1();
        for (Task task: this.tasks) {
            Calendar time = task.nextTimeAfter(from);
            if(time != null && time.before(to)) {
                list.add(task);
            }
        }
        return list;
    }  //– метод, що повертає підмножину задач, які заплановані на виконання хоча б раз після часу from і не пізніше ніж to.
}
