package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model;import java.util.logging.Logger;public class ListOfTask {    private Task curr;    private ListOfTask next;    private ListOfTask priv;    public ListOfTask() {        this.curr = new Task();        this.next = null;    }    public ListOfTask(Task task) {        this.curr = task;        this.next = null;        this.priv = null;    }    public Task getCurr(){        return this.curr;    }    public ListOfTask getNext(){        return this.next;    }    public ListOfTask getPriv(){        return this.priv;    }    public void setNext(ListOfTask task) {        if(task != null){ task.setPriv(this);}        this.next = task;    }    public void setPriv(ListOfTask task){        this.priv = task;    }    public void setCurr(Task task) {        this.curr = task;    }    @Override    public Object clone(){        ListOfTask clone = new ListOfTask();        ListOfTask task = this;        clone.setCurr((Task) this.getCurr().clone());        while(task != null) {            clone.setNext(new ListOfTask());            clone = clone.getNext();            clone.setCurr((Task)task.getCurr().clone());            task = task.getNext();        }        return clone;    }}