package abhi.ooad;

import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.in;

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
        store_northside = new Store("Northside");
        store_southside = new Store("Southside");
    }

    void startSim(int days) {
        for (int day = 1; day <= days; day++) {
            out(" ");
            out("*** Simulation day "+day+" ***");
            startDay(day);
        }
        Scanner myObj = new Scanner(in);
        out("Choose a store: 1->FNMS Northside    2->FNMS Southside");
        int storeNum = myObj.nextInt();
        Store store = (storeNum == 1) ? store_northside : store_southside;
        out("Welcome to Store FNMS " + store.storeName);
        store.interactiveUser(days+1);
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
        //TODO fill this in
//        out("##########################################################");
//        out("                          Summary:                        ");
//        out("##########################################################");
//        out("Items In Inventory:");
//        for(Item i: store.inventory.items){
//            out(i.itemType.getName());
//        }
//        out("Total Inventory Value: " + store.inventory.getValue(store.inventory.items));
//        out("");
//        out("Items Sold During Simulation:");
//        for(Item i: store.inventory.soldItems){
//            out("Name: " + i.itemType.getName() + " Day Sold: " + i.daySold + " Sale Price: " + i.salePrice);
//        }
//        out("Total Sales Value: " + store.inventory.getValue(store.inventory.soldItems));
//        out("");
//        out("Total Value In Cash Register: " + store.cashRegister);
//        out("Total $ Added from goToBank: " + store.cashFromBank);
    }
}
