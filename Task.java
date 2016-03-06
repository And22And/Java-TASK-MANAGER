public class Task{

    private String text;
    private int start;
    private int end;
    private int interval;
    private boolean activity;

    public Task() {

    }

    public Task(String title, int time) {
        if(time < 0) throw new IllegalArgumentException();
        this.setTitle(title);
        this.setTime(time);
    }

    public Task(String title, int start, int end, int interval) {
        if(interval < 0 || start > end || start < 0) throw new IllegalArgumentException();
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


    public int getTime() {
        return this.start;
    }


    public void setTime(int time) {
        if(time < 0) throw new IllegalArgumentException();
        this.interval = 0;
        this.end = time;
        this.start = time;
    }


    public int getStartTime() {
        return this.start;
    }


    public int getEndTime() {
        return this.end;
    }



    public int getRepeatInterval() {
        return this.interval;
    }


    public void setTime(int start, int end, int interval) {
        if(interval < 0 || start > end || start < 0) throw new IllegalArgumentException();
        this.start = start;
        this.end = end;
        this.interval = interval;
    }


    public boolean isRepeated() {
        return interval != 0;
    }


    public int nextTimeAfter(int current) {
        if(!this.isActive() || current >= this.getEndTime()) return -1;
        if(!this.isRepeated()) return this.getEndTime();
        int next = this.getStartTime();
        while(next <= current) {
            next += this.getRepeatInterval();
        }
        if(next > this.getEndTime()) return -1;
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
                this.getStartTime() == task.getStartTime() &&
                this.getEndTime() == task.getEndTime();
    }

    @Override
    public int hashCode() {
        int a = isActive() ? 2 : 3;
        return this.getStartTime() +this.getRepeatInterval() + this.getEndTime()  + this.text.hashCode() + a;
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
    public Object clone() {
        Task task = new Task();
        task.setTime(this.getStartTime(), this.getEndTime(), this.getRepeatInterval());
        task.setActive(this.isActive());
        task.setTitle(this.getTitle());
        return task;
    }
}
