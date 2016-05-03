package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks;


import java.util.Date;

public class DataTask {
    private String text;
    private Date start;
    private Date end;
    private int interval;
    private boolean activity;

    public DataTask() {

    }

    public DataTask(String title, Date time) {
        if(time.before(new Date())) throw  new IllegalArgumentException();
        this.setTitle(title);
        this.setTime(time);
    }

    public DataTask(String title, Date start, Date end, int interval) {
        this.setTitle(title);
        this.setTime(start, end, interval);
    }


    public void setTitle(String title) {
        this.text = title;
    }


    public String getTitle() {
        return this.text;
    }


    public boolean isActive() {
        return this.activity;
    }


    public void setActive(boolean active) {
        this.activity = active;
    }


    public Date getTime() {
        return (Date)this.start.clone();
    }


    public void setTime(Date time) {
        if(time.before(new Date())) throw  new IllegalArgumentException();
        this.interval = 0;
        this.end = time;
        this.start = time;
    }


    public Date getStartTime() {
        return (Date)this.start.clone();
    }


    public Date getEndTime() {
        return (Date)this.end.clone();
    }



    public int getRepeatInterval() {
        return this.interval;
    }


    public void setTime(Date start, Date end, int interval) {
        if(start == null || end == null) throw new IllegalArgumentException("Null");
        if(interval < 0 || start.after(end)) throw new IllegalArgumentException("Wrong interval");
        this.start = start;
        this.end = end;
        this.interval = interval;
    }


    public boolean isRepeated() {
        return interval != 0;
    }


    public Date nextTimeAfter(Date current) {
        if( !this.isActive() || current.after(this.getEndTime()) ) return null;
        if( !this.isRepeated() ) return this.getEndTime();
        Date next = this.getStartTime();
        while(next.after(current)) {
            next.setTime(this.getRepeatInterval()+next.getTime());
        }
        if( next.after(this.getEndTime()) ) return null;
        return next;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return  false;
        if(getClass() != obj.getClass()) return false;
        if(this == obj) return true;
        DataTask task = (DataTask) obj;
        return  this.getTitle().equals(task.getTitle()) &&
                this.isActive() == task.isActive() &&
                this.getRepeatInterval() == task.getRepeatInterval() &&
                this.getStartTime().equals(task.getStartTime()) &&
                this.getEndTime().equals(task.getEndTime());
    }

    public int hashCode() {
        return this.getStartTime().hashCode() + this.getRepeatInterval() * (this.isActive()? 1 : 2) + this.getEndTime().hashCode() + this.text.hashCode();
    }

    @Override
    public String toString() {
        return"Task{ text:" + this.getTitle() +
                ", start: " + this.getStartTime() +
                ", end: " + this.getEndTime() +
                ", interval: " + this.getRepeatInterval() +
                ", activity:" +  this.isActive() +
                "} ";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        DataTask task = new DataTask();
        task.setTime((Date)this.getStartTime().clone(), (Date)this.getEndTime().clone(), this.getRepeatInterval());
        task.setActive(this.isActive());
        task.setTitle(this.getTitle());
        return task;
    }
}

