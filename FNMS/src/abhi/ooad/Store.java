package abhi.ooad;

import java.util.ArrayList;

public class Store implements Logger {
    public ArrayList<Clerk> clerks;
    public Clerk activeClerk;
    public double cashRegister;
    public double cashFromBank;
    public Inventory inventory;
    public int today;

    Store() {
        // initialize the store's starting inventory
        inventory = new Inventory();

        cashRegister = 0;   // cash register is empty to begin
        cashFromBank = 0;   // no cash from bank yet

        // initialize the store's staff
        clerks = new ArrayList<Clerk>();
        clerks.add(new Clerk("Velma",.05, this));
        clerks.add(new Clerk("Shaggy", .20, this));
        clerks.add(new Clerk("Daphne", .10, this));
    }

    void openToday(int day) {
        today = day;
        out("Store opens today, day "+day);
        activeClerk = getValidClerk();
        out(activeClerk.name + " is working today.");
        activeClerk.arriveAtStore();
        activeClerk.checkRegister();
        activeClerk.doInventory();
        activeClerk.openTheStore();
        activeClerk.cleanTheStore();
        activeClerk.leaveTheStore();
    }

    Clerk getValidClerk() {
        ArrayList<Clerk> availableClerks = (ArrayList<Clerk>) clerks.clone();
        Clerk clerk = null;
        boolean canWork = false;
        // pick a random clerk
        while (!canWork) {
            canWork=true;
            if (clerks.size() == 1) {
                clerk = clerks.get(0);
                break;
            }

            clerk = availableClerks.get(Utility.rndFromRange(0, availableClerks.size() - 1));

            if (clerk.daysWorked > 3) {
                out(clerk.name + " cannot work more than 3 days in a row.");
                canWork=false;
                availableClerks.remove(clerk);
            }
            else if (Utility.rndFromRange(1, 10) == 1) {
                out(clerk.name + " is sick today.");
                canWork=false;
                availableClerks.remove(clerk);
            }

        }

        clerk.daysWorked = clerk.daysWorked + 1;
        for (Clerk other: clerks) {
            if (other.name != clerk.name) {
                other.daysWorked = 0;
            }
        }

        return clerk;
    }

    void closedToday(int day) {
        out("Store is closed today, day "+day);
    }
}
