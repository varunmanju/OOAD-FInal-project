package abhi.ooad;

import java.util.*;

enum eventType{
    SOLD, PURCHASED, DAMAGED
}

public class Tracker extends Subscriber{
    subType type;
    Hashtable<String, Hashtable<eventType, Integer> > staffData;
    
    public Tracker(ArrayList<String> names){
    	staffData = new Hashtable<String, Hashtable<eventType, Integer>>();
        type = subType.TRACKER;
        Hashtable<eventType, Integer> input;
        for(String name: names){
            input = new Hashtable<eventType, Integer>();
            input.put(eventType.PURCHASED, 0);
            input.put(eventType.SOLD, 0);
            input.put(eventType.DAMAGED, 0);
            // System.out.println(input);
            staffData.put(name, input);
        }
    }

    //update clerk data
    public void update(String clerk, eventType e, Integer data){
        Integer newValue = data + staffData.get(clerk).get(e);
        staffData.get(clerk).put(e, newValue);
    }

    public void clerkDaySummary(){
        Staff clerk;
        Enumeration names = staffData.keys();
        Object curr;
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%11s %12s %17s %15s", "Clerk", "Items Sold", "Items Purchased", "Items Damaged");
        System.out.println();
        System.out.println("-------------------------------------------------------------------");
        while(names.hasMoreElements()){
            curr = names.nextElement();
            System.out.printf("%11s %12d %17d %15d", curr, staffData.get(curr).get(eventType.SOLD), staffData.get(curr).get(eventType.PURCHASED), staffData.get(curr).get(eventType.DAMAGED));
            System.out.println();
        }

        System.out.println("-------------------------------------------------------------------");
    }
}