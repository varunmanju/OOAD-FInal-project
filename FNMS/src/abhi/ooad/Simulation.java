package abhi.ooad;

import java.util.Iterator;

import java.util.Scanner;

import static java.lang.System.in;


// top level object to run the simulation
public class Simulation implements Subscriber {
    Store store_northside;
    Store store_southside;
    int dayCounter;
    Weekday weekDay;

    // enum for Weekdays
    // next implementation from
    // https://stackoverflow.com/questions/17006239/whats-the-best-way-to-implement-next-and-previous-on-an-enum-type
    public enum Weekday {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
        private static final Weekday[] vals = values();
        public Weekday next() {
            return vals[(this.ordinal()+1) % vals.length];
        }
    }

    Simulation() {
        Logger.getInstance().deleteOldFiles(50, "Northside");
        Logger.getInstance().deleteOldFiles(50, "Southside");
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
        while(true) {
            myObj = new Scanner(in);
            String userInput = myObj.nextLine();
            if (userInput.contains("Name")) {
                store.interactiveUserClerkName();
            } else if (userInput.contains("Time")) {
                store.interactiveUserClerkTime(days + 1);
            } else if (userInput.contains("Sell")) {
                store.interactiveUserBuy();
            } else if (userInput.contains("Buy")) {
                store.interactiveUserSell();
            } else if (userInput.contains("Guitar Kit")) {
                store.interactiveUserGuitarKit();
            } else if (userInput.contains("Toggle")) {
                store = (store.storeName == "Southside") ? store_northside : store_southside;
                out("Welcome to Store FNMS " + store.storeName);
            } else {
                break;
            }
        }
        store_northside.interactiveUserEndOfDay();
        store_southside.interactiveUserEndOfDay();
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
