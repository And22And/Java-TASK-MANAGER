package ua.edu.sumdu.j2se.AndriySliahetskiy.tasks.Model;


import java.io.Serializable;
import java.util.Calendar;
import org.apache.log4j.Logger;

public class Task implements Serializable{

    final private static Logger log = Logger.getLogger(Task.class);

    private String text;
    private Calendar start;
    private Calendar end;
    private int interval;
    private boolean activity;

    public Task() {

    }

    public Task(String title, Calendar time) {
        if(time == null) {
            IllegalArgumentException e = new IllegalArgumentException();
            log.error(e);
            throw e;
        }
        this.setTitle(title);
        this.setTime(time);
    }

    public Task(String title, Calendar start, Calendar end, int interval) {
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


    public Calendar getTime() {
        return (Calendar)this.start.clone();
    }


    public void setTime(Calendar time) {
        if(time == null) {
            IllegalArgumentException e = new IllegalArgumentException();
            log.error(e);
            throw e;
        }
        this.interval = 0;
        this.end = time;
        this.start = time;
    }

    public void setTask(Task task) {
        setTitle(task.getTitle());
        setActive(task.isActive());
        setTime(task.getStartTime(), task.getEndTime(), task.getRepeatInterval());
    }

    public Calendar getStartTime() {
        return (Calendar)this.start.clone();
    }


    public Calendar getEndTime() {
        return (Calendar)this.end.clone();
    }



    public int getRepeatInterval() {
        return this.interval;
    }


    public void setTime(Calendar start, Calendar end, int interval) {
        if(start == null || end == null) {
            IllegalArgumentException e = new IllegalArgumentException();
            log.error(e);
            throw e;
        }
        if(interval < 0 || start.after(end)) {
            IllegalArgumentException e = new IllegalArgumentException();
            log.error(e);
            throw e;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }


    public boolean isRepeated() {
        return this.interval != 0;
    }


    public Calendar nextTimeAfter(Calendar current) {
        if( !this.isActive() || !current.before(this.getEndTime()) ) return null;
        if( !this.isRepeated() ) return this.getEndTime();
        Calendar next = this.getStartTime();
        while(!next.after(current)) {
            next.add(Calendar.SECOND, this.getRepeatInterval());
        }
        if( next.after(this.getEndTime()) ) return null;
        return next;
    }

    public Calendar nextTimeAfter(Calendar current, boolean bool) {
        if( bool != this.isActive() || !current.before(this.getEndTime()) ) return null;
        if( !this.isRepeated() ) return this.getEndTime();
        Calendar next = this.getStartTime();
        while(!next.after(current)) {
            next.add(Calendar.SECOND, this.getRepeatInterval());
        }
        if( next.after(this.getEndTime()) ) return null;
        return next;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return  false;
        if(getClass() != obj.getClass()) return false;
        if(this == obj) return true;
        Task task = (Task) obj;
        return  this.getTitle().equals(task.getTitle()) &&
                this.isActive() == task.isActive() &&
                this.getRepeatInterval() == task.getRepeatInterval() &&
                this.getStartTime().getTimeInMillis() == task.getStartTime().getTimeInMillis() &&
                this.getEndTime().getTimeInMillis() == task.getEndTime().getTimeInMillis();
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
    public Task clone(){
        Task task = new Task();
        Calendar start = null, end = null;
        if(this.start != null && this.end != null) {
            start = (Calendar) this.getStartTime().clone();
            end = (Calendar) this.getEndTime().clone();
        }
        task.setTime(start, end, this.getRepeatInterval());
        task.setActive(this.isActive());
        task.setTitle(this.getTitle());
        return task;
    }
}