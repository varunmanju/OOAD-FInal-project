package abhi.ooad;

import java.util.ArrayList;
import java.util.Iterator;

public class ClerkPool {
    private static ClerkPool uniqueInstance;
    ArrayList<Clerk> clerks;

    private ClerkPool() {
        if (this.clerks == null) {
            this.clerks = new ArrayList<Clerk>();
            this.clerks.add(new Clerk("Velma", .05));
            this.clerks.add(new Clerk("Shaggy", .20));
            this.clerks.add(new Clerk("Daphne", .10));
            this.clerks.add(new Clerk("Fred", .15));
            this.clerks.add(new Clerk("Scooby", .00));
            this.clerks.add(new Clerk("Abhi", .25));
            this.clerks.add(new Clerk("Alexa", .10));
            this.clerks.add(new Clerk("Varun", .15));
        }
    }

    public static ClerkPool getInstance () {
        if (uniqueInstance == null) {
            uniqueInstance = new ClerkPool();
        }
        return uniqueInstance;
    }

    public void endOfDay(Store store_northside, Store store_southside) {
        Iterator<Clerk> itr = clerks.iterator();
        while (itr.hasNext()) {
            Clerk clerk = itr.next();
            if(store_northside.activeClerk.name == clerk.name || store_southside.activeClerk.name == clerk.name){
                //Do not reset daysWorked
            }
            else {
                clerk.daysWorked = 0;
            }
            clerk.workingAtStore = null;
        }
    }
}
