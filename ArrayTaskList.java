package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;


public class ArrayTaskList extends TaskList{

    private Task tasks[];

    public ArrayTaskList() {
        this.tasks = new Task[0];
    }

    public ArrayTaskList createEmptyObj() {
        return new ArrayTaskList();
    }

    public void add(Task task) {
        if(task == null) throw new IllegalArgumentException();
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
        return "ArrayTaskList { contain " + this.size() + " Tasks} ";
    }
}
