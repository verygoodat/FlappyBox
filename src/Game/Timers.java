package Game;

import java.util.*;

public class Timers {

    public static ArrayList<Timer> timers = new ArrayList<Timer>();
    private static ArrayList<TimerTask> timeactions
            = new ArrayList<TimerTask>();
    private static ArrayList<Integer> intervals = new ArrayList<Integer>();
    private static TimerTask tt;
    public static int i1;

    public static Timer createTimer(TimerTask ts, int i2)
    {
        i1 = i2;
        timeactions.add(ts);
        intervals.add(i2);
        Timer timer = new Timer();
        timers.add(timer);
        return timer;
    }
    public static void stopTimers()
    {
        if (tt != null)
        tt.cancel();
    }

    public static void startTimers()
    {
        for (int i = 0; i < timers.size(); i++)
        {
            final int n = i;
            tt = new TimerTask() {
                @Override
                public void run() {
                    timeactions.get(n).run();
                }
            };
            timers.get(i).scheduleAtFixedRate(tt,
                    i1, intervals.get(i));
        }

    }

}
