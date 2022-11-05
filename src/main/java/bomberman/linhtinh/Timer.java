package bomberman.linhtinh;

import java.time.LocalTime;

public class Timer {
    LocalTime time;

    public Timer() {
        time = LocalTime.now();
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double switchBackToSecond() {
        return time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond() + time.getNano()/(double) 1000000000;
    }

    public double TimeDiff(Timer timer2) {
        double tm1 = this.switchBackToSecond();
        timer2.setTime(LocalTime.now());
        double tm2 = timer2.switchBackToSecond();
        double longTime = tm2 - tm1;
        return longTime;
    }

}
