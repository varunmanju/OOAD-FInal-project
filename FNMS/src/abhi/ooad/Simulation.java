package abhi.ooad;

import java.util.Iterator;

// top level object to run the simulation
public class Simulation implements Logger {
    Store store_northside;
    Store store_southside;
    int dayCounter;
    Weekday weekDay;

    // enum for Weekdays
    // next implementation from
    // https://stackoverflow.com/questions/17006239/whats-the-best-way-to-implement-next-and-previous-on-an-enum-type
    public static enum Weekday {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
        private static Weekday[] vals = values();
        public Weekday next() {
            return vals[(this.ordinal()+1) % vals.length];
        }
    }

    Simulation() {
        weekDay = Weekday.MONDAY;   //set the starting day
        dayCounter = 0;
        store_northside = new Store("North Side");
        store_southside = new Store("South Side");
    }

    void startSim(int days) {
        for (int day = 1; day <= days; day++) {
            out(" ");
            out("*** Simulation day "+day+" ***");
            startDay(day);
        }
    }

    void startDay(int day) {
        if (weekDay == Weekday.SUNDAY) {
            store_northside.closedToday(day);
            store_southside.closedToday(day);
        }
        else {
            store_northside.openToday(day);
            store_southside.openToday(day);
        }
        weekDay = weekDay.next();
        ClerkPool.getInstance().endOfDay(store_northside, store_southside);
    }

    void summary() {
        out("The summary is left as an exercise to the reader :-)");
    }
}
