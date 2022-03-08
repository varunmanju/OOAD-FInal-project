package abhi.ooad;

// top level object to run the simulation
public class Simulation implements Logger {
    Store store;
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
        store = new Store();
    }

    void startSim(int days) {
        for (int day = 1; day <= days; day++) {
            out(" ");
            out("*** Simulation day "+day+" ***");
            startDay(day);
        }
    }

    void startDay(int day) {
        if (weekDay == Weekday.SUNDAY) store.closedToday(day);
        else store.openToday(day);
        weekDay = weekDay.next();
    }

    void summary() {
        //TODO fill this in
        out("##########################################################");
        out("                          Summary:                        ");
        out("##########################################################");
        out("Items In Inventory:");
        for(Item i: store.inventory.items){
            out(i.itemType.getName());
        }
        out("Total Inventory Value: " + store.inventory.getValue(store.inventory.items));
        out("");
        out("Items Sold During Simulation:");
        for(Item i: store.inventory.soldItems){
            out("Name: " + i.itemType.getName() + " Day Sold: " + i.daySold + " Sale Price: " + i.salePrice);
        }
        out("Total Sales Value: " + store.inventory.getValue(store.inventory.soldItems));
        out("");
        out("Total Value In Cash Register: " + store.cashRegister);
        out("Total $ Added from goToBank: " + store.cashFromBank);
    }
}
