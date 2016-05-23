package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;


import java.util.Iterator;

public class LinkedTaskList extends TaskList {

    private ListOfTask tasks;

    public LinkedTaskList() {

    }

    public LinkedTaskList createEmptyObj() {
        return new LinkedTaskList();
    }

    public void add(Task task) {
        if(task == null) throw new IllegalArgumentException();
        if(this.tasks == null) {
            this.tasks = new ListOfTask(task);
        }
        else {
            ListOfTask currTask = this.tasks;
            while (currTask.getNext() != null) {
                currTask = currTask.getNext();
            }
            currTask.setNext(new ListOfTask(task));
        }
    }

    public boolean remove(Task task) {
        ListOfTask currTask = this.tasks;
        if(currTask == null) return false;
        if(currTask.getCurr().equals(task)) {
            if(this.tasks.getNext() == null) this.tasks = null;
            else {
                this.tasks = this.tasks.getNext();
                this.tasks.setPriv(null);
            }
            return true;
        }
        while(currTask != null) {
            if(currTask.getCurr().equals(task)) {
                if(currTask.getNext() == null) currTask.getPriv().setNext(null);
                else{currTask.getPriv().setNext(currTask.getNext());}
                return true;
            }
            currTask = currTask.getNext();
        }
        return false;
    }

    public int size() {
        int n = 0;
        ListOfTask currTask = this.tasks;
        while(currTask != null) {
            currTask = currTask.getNext();
            n++;
        }
        return n;
    }

    public Task getTask(int index) {
        if(index >= this.size() || index<0) throw new IllegalArgumentException();
        ListOfTask currTask = this.tasks;
        for (int i = 0; i < index; i++) {
            currTask = currTask.getNext();
        }
        return currTask.getCurr();
    }

    public int getIndex(Task task) {
        ListOfTask t = this.tasks;
        for (int i = 0;  i < this.size(); i++) {
            if(task.equals(t.getCurr())) return i;
            t = t.getNext();
        }
        return -1;
    }

    @Override
    public String toString() {
        int n = this.size();
        String str = "LinkedTaskList { contain " + n + " Tasks}\n";
        for(int i = 0; i < n; i++) {
            str += this.getTask(i).toString() + "\n";
        }
        return str;
    }

    @Override
    public Iterator<Task> iterator() {
        return new LinkedTaskListIterator();
    }

    public class LinkedTaskListIterator implements Iterator<Task>{

        private ListOfTask task = LinkedTaskList.this.tasks;
        private ListOfTask priv = null;

        @Override
        public boolean hasNext() {
            return task != null;
        }

        @Override
        public Task next() {
            priv = task;
            if(task != null) task = task.getNext();
            return priv.getCurr();
        }

        @Override
        public void remove() {
            if(priv == null) throw new IllegalStateException();
            if(priv.getPriv() == null) {
                if(task != null) task.setPriv(null);
                LinkedTaskList.this.remove(priv.getCurr());
                priv = null;
            }
            else {
                priv = priv.getPriv();
                LinkedTaskList.this.remove(priv.getNext().getCurr());
                priv.setNext(task);
            }

        }

    }

}