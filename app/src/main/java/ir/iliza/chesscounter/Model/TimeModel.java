package ir.iliza.chesscounter.Model;



public class TimeModel {
    private int minutes;
    private int seconds;
    private int milliseconds;



    public TimeModel(int minutes, int seconds, int milliseconds){
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public TimeModel setMinutes(int minutes) {
        this.minutes = minutes;
        return this;
    }

    public TimeModel setSeconds(int seconds) {
        this.seconds = seconds;
        return this;
    }

    public TimeModel setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
        return this;
    }


}
