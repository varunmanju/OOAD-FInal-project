package abhi.ooad;


import java.text.DecimalFormat;
import java.util.*;

import static java.lang.System.out;

class Store {
    //Global attributes
    public  Map<String, List<Item>> inventory = new HashMap<>();
    private Map<String, List<Item>> yetToArriveItems = new HashMap<>();
    protected Map<String, List<Item>> boughtItems = new HashMap<>();
    protected Map<String, List<Item>> soldItems = new HashMap<>();
    private Map<Integer, List<String>> orderedItems = new HashMap<>();
    protected ArrayList<Staff> clerkRecord = new ArrayList<>();
    private ArrayList<String> availableClerk = new ArrayList<>();
    protected Staff activeClerk;
    private ArrayList<String> listOfItems = new ArrayList<>();
    protected float registerValue;
    public static int storeDay;
    private Random rand;
    private DecimalFormat df;
    private ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>(); 
    public int damage=0;
    //Constructor - Private - Instantiation required only from simulation method
    public Store()  {
        this.registerValue = 0.00F;
        this.storeDay = 1;

        // Assign a tune Algorithm to each clerk, Shaggy,Velma and Daphne.
        this.clerkRecord.add(new Staff("Shaggy", 20,new Haphazard(inventory)));
        this.clerkRecord.add(new Staff("Velma", 5,new Electronic()));
        this.clerkRecord.add(new Staff("Daphne", 10, new Manual(inventory)));
        for(Staff obj:this.clerkRecord){
            this.availableClerk.add(obj.getName());
        }

        this.rand = new Random();
        this.df = new DecimalFormat("0.00");

        //Create an array to store list of all the items
        this.listOfItems.add("PaperScore");
        this.listOfItems.add("CD");
        this.listOfItems.add("Vinyl");
        this.listOfItems.add("Cassette");
        this.listOfItems.add("RecordPlayer");
        this.listOfItems.add("CDPlayer");
        this.listOfItems.add("MP3Player");
        this.listOfItems.add("CassettePlayer");
        this.listOfItems.add("Guitar");
        this.listOfItems.add("Bass");
        this.listOfItems.add("Mandolin");
        this.listOfItems.add("Flute");
        this.listOfItems.add("Harmonica");
        this.listOfItems.add("Saxophone");
        this.listOfItems.add("Hat");
        this.listOfItems.add("Shirt");
        this.listOfItems.add("Bandana");
        this.listOfItems.add("PracticeAmps");
        this.listOfItems.add("Cables");
        this.listOfItems.add("Strings");
        this.listOfItems.add("GigBag");
        //Add items to the inventory (3 of each type)
        this.addItems(this.listOfItems, false, 0, "New", -1.00F, 5);
    }

    //Parameters: onHold - placed an order; yet to arrive
    //            dayArrived - day of arrival for ordered items
    //            newOrUsed - "new" when an order is placed; could be "new/used" when bought from a customer
    //            purchasePrice - a negative value when order is placed; a positive value when bought from a customer
    //            conditionLevel - condition status
    //Inventory will have items currently available to sell
    //yetToArriveItems will have items for which orders have been placed and are yet to arrive
    protected float addItems(ArrayList<String> items, boolean yetToArrive, int dayOfArrival, String newOrUsed, float purchasePrice, int conditionLevel) {
        ArrayList<Item> item = new ArrayList<>();
        String condition = getConditionText(conditionLevel);
        float totalPurchasePrice = 0.00F;
        int numberOfItems;
        boolean boughtItem;

        //Create only 1 item if bought from a customer, else create 3 items
        if (purchasePrice > 0){
            numberOfItems = 1;
            boughtItem = true;
        }
        else {
            numberOfItems = 3;
            boughtItem = false;
        }

        for (String str:items) {
            if (inventory.containsKey(str) && yetToArrive == false) {
                item = (ArrayList<Item>) inventory.get(str);
            }

            for (int i = 0; i < numberOfItems; i++) {
                if (boughtItem != true) {
                    purchasePrice = this.getRandomFloat(1,50);
                }
                totalPurchasePrice += purchasePrice;

                switch (str) {
                    case "PaperScore":
                        item.add(new PaperScore(purchasePrice, newOrUsed, dayOfArrival, condition, "", ""));
                        break;

                    case "CD":
                        item.add(new CD(purchasePrice, newOrUsed, dayOfArrival, condition, "", ""));
                        break;

                    case "Vinyl":
                        item.add(new Vinyl(purchasePrice, newOrUsed, dayOfArrival, condition, "", ""));
                        break;

                    case "Cassette":
                        item.add(new Cassette(purchasePrice, newOrUsed, dayOfArrival, condition, "", ""));
                        break;

                    case "RecordPlayer":
                        item.add(new RecordPlayer(purchasePrice, newOrUsed, dayOfArrival, condition));
                        break;

                    case "CDPlayer":
                        item.add(new CDPlayer(purchasePrice, newOrUsed, dayOfArrival, condition));
                        break;

                    case "MP3Player":
                        item.add(new MP3Player(purchasePrice, newOrUsed, dayOfArrival, condition));
                        break;

                    case "CassettePlayer":
                        item.add(new CassettePlayer(purchasePrice, newOrUsed, dayOfArrival, condition));
                        break;

                    case "Guitar":
                        item.add(new Guitar(purchasePrice, newOrUsed, dayOfArrival, condition, true));
                        break;

                    case "Bass":
                        item.add(new Bass(purchasePrice, newOrUsed, dayOfArrival, condition, true));
                        break;

                    case "Mandolin":
                        item.add(new Mandolin(purchasePrice, newOrUsed, dayOfArrival, condition, true));
                        break;

                    case "Flute":
                        item.add(new Flute(purchasePrice, newOrUsed, dayOfArrival, condition, ""));
                        break;

                    case "Harmonica":
                        item.add(new Harmonica(purchasePrice, newOrUsed, dayOfArrival, condition, ""));
                        break;

                    case "Saxophone":
                        item.add(new Saxophone(purchasePrice, newOrUsed, dayOfArrival, condition, ""));
                        break;

                    case "Hat":
                        item.add(new Hat(purchasePrice, newOrUsed, dayOfArrival, condition, ""));
                        break;

                    case "Shirt":
                        item.add(new Shirt(purchasePrice, newOrUsed, dayOfArrival, condition, ""));
                        break;

                    case "Bandana":
                        item.add(new Bandana(purchasePrice, newOrUsed, dayOfArrival, condition));
                        break;

                    case "PracticeAmps":
                        item.add(new PracticeAmps(purchasePrice, newOrUsed, dayOfArrival, condition, ""));
                        break;

                    case "Cables":
                        item.add(new Cables(purchasePrice, newOrUsed, dayOfArrival, condition,0));
                        break;

                    case "Strings":
                        item.add(new Strings(purchasePrice, newOrUsed, dayOfArrival, condition,""));
                        break;

                    case "GigBag":
                        item.add(new GigBag(purchasePrice, newOrUsed, dayOfArrival, condition));
                        break;
                }
            }

            if (yetToArrive == true) {
                if(this.registerValue >= totalPurchasePrice) {
                    this.yetToArriveItems.put(str, (List<Item>) item.clone());
                    this.announcement("addItems", "An order of " + this.df.format(totalPurchasePrice) + " has been made. Items of type " + str + " will arrive on Day " + dayOfArrival + ".");
                }
                else {
                    for(int i=0; i<item.size(); i++){
                        totalPurchasePrice -= item.get(i).getPurchasePrice();
                    }
                    this.announcement("addItems", "Not enough money in the Cash Register to buy " + str + ".");
                }
            }
            else {
                inventory.put(str, (List<Item>) item.clone());
            }

            if(boughtItem == true) {
                ArrayList<Item> itemTemp = new ArrayList<>();
                if(this.boughtItems.get(str) != null){
                    itemTemp = (ArrayList<Item>) this.boughtItems.get(str);
                }
                itemTemp.add(item.get(item.size()-1));
                this.boughtItems.put(str, (List<Item>) itemTemp.clone());
            }

            item.clear();
        }

        //Return price of all the items added to inventory/yetToArriveItems
        return totalPurchasePrice;
    }

    //Generate a random integer within the given range
    private int getRandomInteger (int lowerBound, int upperBound) {
        return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }

    //Generate a random float number within the given range
    private float getRandomFloat (float lowerBound, float upperBound) {
        return (float) (Math.round((lowerBound + this.rand.nextFloat() * upperBound) * 100.0) / 100.0);
    }

    //Get purchase price for all the items that are currently in the inventory
    // private float getInventoryPurchasePrice () {
    //     float totalPurchasePrice = 0.00F;
    //     for (Object obj : inventory.values()) {
    //         for (int i = 0; i < ((ArrayList) obj).size(); i++) {
    //             totalPurchasePrice += ((ArrayList<Item>) obj).get(i).getPurchasePrice();
    //         }
    //     }
    //     return totalPurchasePrice;
    // }

    //Increase day by 1
    private void increaseDay() {
        this.storeDay += 1;
        this.availableClerk.clear();
        for(Staff obj:this.clerkRecord){
            this.availableClerk.add(obj.getName());
        }
    }

    //Get condition text for the corresponding integer
    public String getConditionText(int conditionLevel) {
        switch (conditionLevel) {
            case 1: return "Poor";
            case 2: return "Fair";
            case 3: return "Good";
            case 4: return "Very Good";
            case 5: return "Excellent";
            default: return "Excellent";
        }
    }

    //Get condition integer for the corresponding text
    public int getConditionLevel(String conditionText) {
        switch (conditionText) {
            case "Poor": return 1;
            case "Fair": return 2;
            case "Good": return 3;
            case "Very Good": return 4;
            case "Excellent": return 5;
            default: return 5;
        }
    }
	
    private Subscriber registerSubscriber(subType t) {
        switch (t) {
            case LOGGER:
                Logger l = new Logger("Logger-" + storeDay + ".txt");
                subscribers.add(l);
                return l;
            default:
                Tracker tr = new Tracker(this.availableClerk);
                subscribers.add(tr);
                return tr;
        }
    }

    private void removeSubscriber(Subscriber s) {
        subscribers.remove(s);
    }

    //Print day, calling method & message
    private void announcement(String methodName, String message) {
        out.println("Day "+ this.storeDay + ": " + methodName + " - " + message);
    }
	
    // notify subscriber & announce
    private void publish(Staff current, String methodName, String message) {
        announcement(methodName, message);
        Subscriber c;
        Logger l;
        for (int i = 0; i < subscribers.size(); i++) {
            c = subscribers.get(i);
            if (c.type == subType.LOGGER) {
                l = (Logger) c;
                l.update(current.getName(), methodName, message);
            }
        }
    }

    // notify subscriber & announce
    private void publish(Staff current, String methodName, String message, int data, eventType e) {
        announcement(methodName, message);
        Subscriber c;
        Logger l;
        Tracker t;
        for (int i = 0; i < subscribers.size(); i++) {
            c = subscribers.get(i);
            if (c.type == subType.LOGGER) {
                l = (Logger) c;
                c.update(current.getName(), methodName, message);
            } else {
                t = (Tracker) c;
                t.update(current.getName(), e, data);
            }
        }
    }

    //Poisson distribution (Source - https://stackoverflow.com/questions/9832919/generate-poisson-arrival-in-java)
    private int getPoissonRandom(int mean) {
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * this.rand.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    //Arrive at Store
    protected void arriveAtStore() {
        String selectedClerk;
        //Determine who is working today
        while(true) {
            if(this.availableClerk.size() == 1){
                for(int i=0; i<this.clerkRecord.size(); i++){
                    if(this.clerkRecord.get(i).getName() == this.availableClerk.get(0)){
                        this.activeClerk = this.clerkRecord.get(i);
                        break;
                    }
                }
                break;
            }

            selectedClerk = this.availableClerk.get(this.getRandomInteger(0, this.availableClerk.size() - 1));

            //Sick leave
            if(this.getRandomInteger(1, 100) <= 10){
                this.availableClerk.remove(selectedClerk);
                for(int i=0; i<this.clerkRecord.size(); i++){
                    if(this.clerkRecord.get(i).getName() == selectedClerk){
                        this.clerkRecord.get(i).setLeaveDay(this.clerkRecord.get(i).getLeaveDay() + 1);
                        break;
                    }
                }
                this.announcement("arriveAtStore", selectedClerk + " is sick on day " + this.storeDay);
                continue;
            }

            for(int i=0; i<this.clerkRecord.size(); i++){
                if(this.clerkRecord.get(i).getName() == selectedClerk){
                    this.activeClerk = this.clerkRecord.get(i);
                    break;
                }
            }
            if(this.activeClerk.getWorkingDays()!= null &&
                    this.activeClerk.getWorkingDays().size() >= 2 &&
                    this.activeClerk.getWorkingDays().get(this.activeClerk.getWorkingDays().size() - 1) == this.storeDay-1 &&
                    this.activeClerk.getWorkingDays().get(this.activeClerk.getWorkingDays().size() - 2) == this.storeDay-2 ){
                this.availableClerk.remove(selectedClerk);
                this.activeClerk = null;
                this.announcement("arriveAtStore", selectedClerk + " cannot work 3 days in a row.");
                continue;
            }
            else break;
        }
        this.announcement("arriveAtStore", this.activeClerk.getName() + " arrives at the store on Day " + this.storeDay);

        //Keep track of working days for each clerk
        this.activeClerk.setWorkingDayCount(this.activeClerk.getWorkingDayCount() + 1);
        this.activeClerk.addWorkingDays(this.storeDay);

        //Check if any items have arrived
        if (!yetToArriveItems.isEmpty()) {
            boolean inventoryFound = false;
	    int itemCount = 0;
            
            //Check if any of the placed orders have arrived
            //If yes, then move the items from yetToArriveItems to inventory
            Object[] keys = this.yetToArriveItems.keySet().toArray();
            for (Object key: keys) {
                ArrayList<Item> item = new ArrayList<>();
                inventoryFound = false;
                if (inventory.get(key) != null) {
                    item = (ArrayList<Item>) inventory.get(key);
                }
                for (int j = 0; j < this.yetToArriveItems.get(key).size(); j++) {
                    if ((this.yetToArriveItems.get(key).get(j).getDayArrived() <= this.storeDay)) {
                        inventoryFound = true;
                        item.add(this.yetToArriveItems.get(key).get(j));
                        this.announcement("arriveAtStore", "Item " + key + " has arrived.");
			itemCount++;
                    }
                }
                if (inventoryFound == true) {
                    this.yetToArriveItems.remove(key);
                }
                item = null;
		this.publish(activeClerk, "arriveAtStore", itemCount + "Items were added to inventory.");
            }
        }
    }

    //Check Cash Register
    private void checkRegister() {
        //If required, go to bank before checking the inventory
        if (this.registerValue < 75) {
            this.publish(activeClerk, "checkRegister", "Cash Register value: " + this.registerValue);
            this.goToBank();
        }
        this.doInventory();
    }

    //Go to bank
    protected void goToBank() {
        float amount = 1000;
        this.registerValue += amount;
        this.activeClerk.setBankWithdrawValue(this.activeClerk.getBankWithdrawValue() + (int) amount);
        this.publish(activeClerk, "goToBank", amount + " has been added to the Cash Register on Day "
                + this.storeDay + ". New value: " + this.registerValue);
    }
    // Function tune implements the strategy pattern.We are delegating the responsibility of tuning via the Tune interface.The tune interface has three classes that implements 
    // it.Manual,Electronic and Haphazard.
    private int tune() {
    	
    	int dam=0;
	this.activeClerk.damage=0;
    	 System.out.println(""+this.activeClerk.getName());
         if(inventory.get("RecordPlayer") != null) {
         	for(Item obj:inventory.get("RecordPlayer")) {
         	int idx=inventory.get(obj.getName()).indexOf(obj);
         	dam=this.activeClerk.dotuning(obj,idx);
         	this.activeClerk.damage+=dam;
         	
         	}
         	

         }
         if(inventory.get("CDPlayer") != null) {
         	for(Item obj:inventory.get("CDPlayer")) {
         	int idx=inventory.get(obj.getName()).indexOf(obj);
         	dam=this.activeClerk.dotuning(obj,idx);
         	this.activeClerk.damage+=dam;
         	}
         	

         }
         if(inventory.get("MP3Player") != null) {
         	for(Item obj:inventory.get("MP3Player")) {
         	int idx=inventory.get(obj.getName()).indexOf(obj);
         	dam=this.activeClerk.dotuning(obj,idx);
         	this.activeClerk.damage+=dam;
         	}
         	

         }
         if(inventory.get("CassettePlayer") != null) {
         	for(Item obj:inventory.get("CassettePlayer")) {
         	int idx=inventory.get(obj.getName()).indexOf(obj);
         	dam=this.activeClerk.dotuning(obj,idx);
         	this.activeClerk.damage+=dam;
         	}
         	

         }
         if(inventory.get("Guitar") != null) {
         	for(Item obj:inventory.get("Guitar")) {
         	int idx=inventory.get(obj.getName()).indexOf(obj);
         	dam=this.activeClerk.dotuning(obj,idx);
         	this.activeClerk.damage+=dam;
         	}
         	

         }
         if(inventory.get("Bass") != null) {
         	for(Item obj:inventory.get("Bass")) {
         	int idx=inventory.get(obj.getName()).indexOf(obj);
         	dam=this.activeClerk.dotuning(obj,idx);
         	this.activeClerk.damage+=dam;
         	}
         	

         }
         if(inventory.get("Mandolin") != null) {
         	for(Item obj:inventory.get("Mandolin")) {
         	int idx=inventory.get(obj.getName()).indexOf(obj);
         	dam=this.activeClerk.dotuning(obj,idx);
         	this.activeClerk.damage+=dam;
         	}
         	

         }
         if(inventory.get("Flute") != null) {
         	for(Item obj:inventory.get("Flute")) {
         	int idx=inventory.get(obj.getName()).indexOf(obj);
         	dam=this.activeClerk.dotuning(obj,idx);
         	this.activeClerk.damage+=dam;
         	}
         	

         }
         if(inventory.get("Harmonica") != null) {
         	for(Item obj:inventory.get("Harmonica")) {
         	int idx=inventory.get(obj.getName()).indexOf(obj);
         	dam=this.activeClerk.dotuning(obj,idx);
         	this.activeClerk.damage+=dam;
         	}
         

         }
         if(inventory.get("Saxophone") != null) {
         	for(Item obj:inventory.get("Saxophone")) {
         	int idx=inventory.get(obj.getName()).indexOf(obj);
         	dam=this.activeClerk.dotuning(obj,idx);
         	this.activeClerk.damage+=dam;
         	}
         	

         }
         return damage;
    }

    
    private void doInventory(){
	//implement the strategy pattern method dotuning.
    	int numDamaged = this.tune();
    	System.out.println(numDamaged);
    	this.publish(activeClerk, "doInventory", "The total number of items damaged is " + this.activeClerk.damage, this.activeClerk.damage, eventType.DAMAGED);
        // Check inventory value and display
        // Get purchase price for all the items that are currently in the inventory
        float totalPurchasePrice = 0.00F;
        int numItems = 0;
        for (Object obj : inventory.values()) {
            for (int i = 0; i < ((ArrayList) obj).size(); i++) {
                totalPurchasePrice += ((ArrayList<Item>) obj).get(i).getPurchasePrice();
                numItems++;
            }
            
        }
        this.publish(activeClerk, "doInventory", "The total number of items in inventory is " + numItems);
        this.announcement("doInventory", "Cash Register value: " + this.df.format(this.registerValue));
        this.publish(activeClerk, "doInventory",
                "Total purchase value of all the inventory items is " + this.df.format(totalPurchasePrice));

        //Place an order for items that are out of stock
        ArrayList<String> orderItems = new ArrayList<>();
        try {
            for (String str : this.listOfItems) {
                if (inventory.get(str) != null && inventory.get(str).get(0).getClass().getSuperclass().getName() == "Clothing") {
                    continue;
                }
                if (inventory.get(str) == null && !this.yetToArriveItems.containsKey(str)) {
                    orderItems.add(str);
                }
            }
        }catch(NullPointerException error){ }
        if (!orderItems.isEmpty()) {
            this.placeAnOrder(orderItems);
        }
    }

    //Place an order
    private void placeAnOrder(ArrayList<String> items){
        //Determine value of all the items for which the order has been placed and reduce it from the Cash Register
        int dayOfArrival = this.getRandomInteger(this.storeDay+1,this.storeDay+3);
        this.orderedItems.put(dayOfArrival, items);
        float totalPurchasePrice = this.addItems(items, true, dayOfArrival, "New", -1.00F, 5);
        if (totalPurchasePrice > 0) {
            this.announcement("placeAnOrder", "Cash Register value before placing the order: " + this.df.format(this.registerValue));
            this.registerValue -= totalPurchasePrice;
            this.announcement("placeAnOrder", "Cash Register value after placing the order: " + this.df.format(this.registerValue));
        }
	this.publish(activeClerk, "placeAnOrder", items.size() + " have been purchased", items.size(),
                eventType.PURCHASED);
    }

    //Get customers
    protected ArrayList<String> getCustomers() {
        ArrayList<String> customers = new ArrayList<>();

        //Customers - to buy an item
        int numberOfCustomers = 2 + this.getPoissonRandom(3);
        for (int i = 0; i < numberOfCustomers; i++) {
            customers.add("B");
        }

        //Customers - to sell and item
        numberOfCustomers = this.getRandomInteger(1,4);
        for (int i = 0; i < numberOfCustomers; i++) {
            customers.add("S");
        }

        Collections.shuffle(customers);
        return customers;
    }

    //Randomly select an item
    protected Item selectItem() throws NoSuchFieldException {
        Item item = null;
        Map<Float, Item> itemProbability = new TreeMap<>();
        float probability = 0.00F;
        for(String str:inventory.keySet()){
            for(Item itm:inventory.get(str)){
                if(itm.getClass().getSuperclass().getName().contains("Players") && ((Players)itm).isEqualized() == true) probability += 1.10F;
                else if(itm.getClass().getSuperclass().getName().contains("Stringed") && ((Stringed)itm).isTuned() == true) probability += 1.15F;
                else if(itm.getClass().getSuperclass().getName().contains("Wind") && ((Wind)itm).isAdjusted() == true) probability += 1.20F;
                else probability += 1.00F;
                itemProbability.put(probability, itm);
            }
        }
        float num = this.getRandomFloat(1.00F, probability-1);
        for (Map.Entry<Float, Item> entry : itemProbability.entrySet()) {
            if(entry.getKey() >= num){
                item = entry.getValue();
                break;
            }
        }
        return item;
    }
    
    private void remove(String selectedItem,Item it) {
		ArrayList<Item> item = new ArrayList<>();
		 if(this.inventory.get(it.getName()).size() == 1){
             this.inventory.remove(it.getName());
         }
		if (this.inventory.get(selectedItem) != null) {
          for (Object obj: this.inventory.get(selectedItem)) {
              item.add((Item) obj);
          }
         
          item.remove(it);
          this.inventory.put(selectedItem, (List<Item>) item.clone());
          
      }
	}
    //Implement the decorator pattern.gigs,practiceamp, cables and strings are the classes that implement the stringdecorator abstract class.
    public ArrayList<Item> processStringed( String selectedItem,Item its, int countCustomer)
    {
    		
    		ArrayList<Item> item = new ArrayList<>();
    		ArrayList<Item> stringed = new ArrayList<>();
            ArrayList<Item> stringed1 = new ArrayList<>();
           
            int count=0;
        	Item i =  its;
        	stringed.add(i);
        	stringed1.add(i);
        	gigs s= new gigs(stringed1,"GigBag", 1000, "New", 3 ,"Excellent", i.isElectric(),inventory);
        	ArrayList<Item> a= s.additionalbought();
        	practiceamp c=new practiceamp(a,"PracticeAmps",1000, "New", 3 ,"Excellent", i.isElectric(),inventory);
        	ArrayList<Item> b= c.additionalbought();
        	cables1 d=new cables1(b,"Cables",1000, "New", 3 ,"Excellent", i.isElectric(),inventory);
        	ArrayList<Item> e= d.additionalbought();
        	string1 f=new string1(b,"Strings",1000, "New", 3 ,"Excellent", i.isElectric(),inventory);
        	ArrayList<Item> h= f.additionalbought();
        	if(!a.isEmpty()) {
        	for(Item it: a)
        	{
        		stringed.add(it);
        	}
        	}
        	if(!b.isEmpty()) {
            	for(Item it: b)
            	{
            		stringed.add(it);
            	}
            	}
        	if(!e.isEmpty()) {
            	for(Item it: e)
            	{
            		stringed.add(it);
            	}
            	}
        	if(!h.isEmpty()) {
            	for(Item it: h)
            	{
            		stringed.add(it);
            	}
            	}
        	if(!stringed.isEmpty()) {
        	for(Item it:stringed)
        	{	
        	if(count==1) {
        		System.out.println("Additional items bought are");	
        	}
        	
        	System.out.println(it.getName());
        	it.setSalePrice(it.getListPrice());
        	it.setDaySold(Store.storeDay);
        	this.registerValue += it.getSalePrice();
        	this.activeClerk.setItemsSold(this.activeClerk.getItemsSold() + 1);
            this.activeClerk.setItemsSoldValue(this.activeClerk.getItemsSoldValue() + it.getSalePrice());
        	remove(it.getName(),it);
        	announcement("openTheStore", "Customer" +  countCustomer + " bought a " + it.getName() + " for " + it.getSalePrice() + "!");
        	count++;
        	}
        		
        	}
        	
        	return stringed;
        	
            }
    //Customer to buy a product
    protected int processBuyingCustomer(int countCustomer) throws NoSuchFieldException {
        ArrayList<Item> item = new ArrayList<>();
        Item selectedItemRef = null;
        boolean bought = false;
        boolean boughtClothingItem = false;
        int numberofbought=0;
        //Randomly select a type of item for customer to buy or sell
        Item selectedItem = this.selectItem();
        selectedItemRef = inventory.get(selectedItem.getName()).get(inventory.get(selectedItem.getName()).indexOf(selectedItem));
      
        if (getRandomInteger(1, 100) <= 50) {
        	selectedItemRef.setSalePrice(selectedItemRef.getListPrice());
            bought = true;
        	if(selectedItemRef.getName()=="Guitar" || selectedItemRef.getName()=="Bass" || selectedItemRef.getName()=="Mandolin") {
        		ArrayList<Item> stringed=processStringed(selectedItemRef.getName(),selectedItemRef,countCustomer);
        		for(Item obj:stringed) {
        			numberofbought++;
        		}
        		return numberofbought;
        	}
            
        } else if (getRandomInteger(1, 100) <= 75) {
            selectedItemRef.setSalePrice(selectedItemRef.getListPrice() * 0.90F);
            bought = true;
            if(selectedItemRef.getName()=="Guitar" || selectedItemRef.getName()=="Bass" || selectedItemRef.getName()=="Mandolin") {
            	ArrayList<Item> stringed=processStringed(selectedItemRef.getName(),selectedItemRef,countCustomer);
            	for(Item obj:stringed) {
        			numberofbought++;
        		}
            	return numberofbought;
      	}
        }

        //If an item is bought, remove it from the inventory and add the money to the Cash Register
        if (bought == true) {
        	numberofbought++;
            selectedItemRef.setDaySold(this.storeDay);
            this.registerValue += selectedItemRef.getSalePrice();

            if (this.soldItems.get(selectedItem.getName()) != null) {
                item = (ArrayList<Item>) this.soldItems.get(selectedItem.getName());
            }
            item.add(selectedItemRef);
            this.soldItems.put(selectedItemRef.getName(), (List<Item>) item.clone());
            item = null;

            this.activeClerk.setItemsSold(this.activeClerk.getItemsSold() + 1);
            this.activeClerk.setItemsSoldValue(this.activeClerk.getItemsSoldValue() + selectedItemRef.getSalePrice());

            if(selectedItemRef.getClass().getSuperclass().getName().contains("Clothing")){
                boughtClothingItem = true;
            }

            this.announcement("openTheStore", "Customer" + countCustomer + " bought a " + selectedItem.getName() + " for " + this.df.format(selectedItemRef.getSalePrice()) + "!");
            if(inventory.get(selectedItem.getName()).size() == 1){
                inventory.remove(selectedItem.getName());
            }
            else {
                inventory.get(selectedItem.getName()).remove(selectedItemRef);
            }

            if(boughtClothingItem == true) {
                if (inventory.get("Hat") == null && inventory.get("Shirt") == null && inventory.get("Bandana") == null) {
                    this.listOfItems.remove("Hat");
                    this.listOfItems.remove("Shirt");
                    this.listOfItems.remove("Bandana");
                }
            }
        } else {
            this.announcement("openTheStore", "Customer" + countCustomer + " left without buying a " + selectedItem.getName() + "!");
        }
	return numberofbought;
    }

    protected Boolean processSellingCustomer(int countCustomer) throws NoSuchFieldException {
        ArrayList<String> item = new ArrayList<>();
        boolean sold = false;

        //Randomly select a type of item for customer to buy or sell
        String selectedItem = listOfItems.get(getRandomInteger(0, listOfItems.size()-1));

        float purchasePrice = this.getRandomFloat(1,50);
        int condition = this.getRandomInteger(1,5);
        purchasePrice = purchasePrice - ((0.10F * (5 - condition)) * purchasePrice);

        if (this.getRandomInteger(1, 100) <= 50) {
            sold = true;
        }
        else if (this.getRandomInteger(1, 100) <= 75) {
            purchasePrice = purchasePrice * 1.10F;
            sold = true;
        }

        //If an item is sold, add it to the inventory and reduce the money from the Cash Register
        if (sold == true) {
            String newOrUsed;
            if (this.getRandomInteger(0,1) == 0) {
                newOrUsed = "New";
            }
            else {
                newOrUsed = "Used";
            }
            item.add(selectedItem);
            this.addItems(item, false, this.storeDay, newOrUsed, purchasePrice, condition);
            item.clear();
            this.registerValue -= purchasePrice;
            this.activeClerk.setItemsPurchased(this.activeClerk.getItemsPurchased() + 1);
            this.activeClerk.setItemsPurchasedValue(this.activeClerk.getItemsPurchasedValue() + purchasePrice);
            this.announcement("openTheStore", "Customer" + countCustomer + " sold a " + selectedItem + " for " + this.df.format(purchasePrice) + "!");
        }
        else {
            this.announcement("openTheStore", "Customer" + countCustomer + " left without selling a " + selectedItem + "!");
        }
	return sold;
    }

    //Open the Store
    private void openTheStore() throws NoSuchFieldException {
        ArrayList<String> customers = new ArrayList<>();
        customers = getCustomers();
        this.announcement("openTheStore", "We have " + customers.size() + " customers today.");
	int numPurchased = 0;
	int sum=0;
        int numSold = 0;
        for (int i = 0; i < customers.size(); i++) {
            //If customer wants to buy:
            //Item is available - offer a price - customer might or might not buy
            //Item is not available - customer goes empty hand
            if (customers.get(i) == "B") {
                if(inventory.isEmpty()){
                    out.println("Inventory is empty at the moment. " + "Customer" + i + " is leaving without buying anything.");
                    continue;
                }
                sum=processBuyingCustomer(i + 1);
                numPurchased=numPurchased+sum;
                   
            }

            //If customer wants to sell:
            //Offer a price based on the condition of the item - customer might or might not sell
            else {
                if (processSellingCustomer(i + 1))
                    numSold++;
            }
        }
	publish(activeClerk, "openTheStore", numSold + " Items were sold today.", numSold, eventType.SOLD);
        publish(activeClerk, "openTheStore", numPurchased + " Items were purchased today.", numPurchased,
                eventType.PURCHASED);
    }

    //Clean the store
    private void cleanTheStore(){
	int totalDamaged = 0;
        //Take damage route only if inventory is not empty
        if (!inventory.isEmpty()) {
            //Damage route: Get damage frequency of the clerk who is working today
            //              Determine if there will be a damage based on the damage frequency of the clerk
            //              Randomly select an item from the inventory that is to be damaged
            //              Lower the condition by one stage if the item to be damaged is not in poor condition
            //              Remove the item from inventory if item to be damaged is already in poor condition
            if (this.activeClerk.getDamageFrequency() <= getRandomInteger(1, 100)) {
                String selectedItemKey;
                Item selectedItem = null;
                selectedItemKey = (String) inventory.keySet().toArray()[this.getRandomInteger(0, inventory.keySet().size()-1)];
                selectedItem = inventory.get(selectedItemKey).get(this.getRandomInteger(0, inventory.get(selectedItemKey).size() - 1));
                if (this.getConditionLevel(selectedItem.getCondition()) == 1) {
                    this.activeClerk.setItemsDamaged(this.activeClerk.getItemsDamaged() + 1);
                    this.activeClerk.setItemsDamagedValue(this.activeClerk.getItemsDamagedValue() + selectedItem.getListPrice());
                    if (inventory.get(selectedItemKey).size() > 1) {
                        inventory.remove(selectedItemKey).remove(selectedItem);
                    }
                    else {
                        inventory.remove(selectedItemKey);
                    }
                    this.announcement("cleanTheStore", this.activeClerk.getName() + "has damaged a " + selectedItemKey + " while cleaning. Item is removed from the inventory.");
                } else {
                    selectedItem.setCondition(this.getConditionText(this.getConditionLevel(selectedItem.getCondition())-1));
                    selectedItem.setListPrice(selectedItem.getListPrice() * 0.80F);
                    this.activeClerk.setItemsDamaged(this.activeClerk.getItemsDamaged() + 1);
                    this.activeClerk.setItemsDamagedValue(this.activeClerk.getItemsDamagedValue() + selectedItem.getListPrice());
                    this.announcement("cleanTheStore", this.activeClerk.getName() + "has damaged a " + selectedItemKey + " while cleaning. Item condition is lowered and list price is reduced.");
                }
                totalDamaged+=1;
                
		publish(activeClerk, "cleanTheStore", totalDamaged + " items were damaged while cleaning the store.",
				totalDamaged, eventType.DAMAGED);
		
            }
        }
    }

    //Leave the Store
    private void leaveTheStore(){
        publish(activeClerk, "leaveTheStore", "Closing time! " + this.activeClerk.getName() + " left the store.");
    }

    //Store is closed
    private void storeIsClosed () {
        this.announcement("storeIsClosed", "It's a Sunday. Store is closed today.");
    }

    //Print inventory details at the end of simulation
    private void endOfSimulationLog () {
        out.println();
        out.println("Inventory at the end of simulation");
        out.println("----------------------------------");
        for (int i = 0; i < inventory.keySet().size(); i++) {
            for (int j = 0; j < inventory.get(inventory.keySet().toArray()[i]).size(); j++) {
                out.println("Item: " + inventory.get(inventory.keySet().toArray()[i]).get(j).getName() + "  Purchase Price: " + this.df.format(inventory.get(inventory.keySet().toArray()[i]).get(j).getPurchasePrice()));
            }
        }

        out.println();
        out.println("Sold Items' details at the end of simulation");
        out.println("--------------------------------------------");
        float totalSalePrice = 0.00F;
        for (int i = 0; i < this.soldItems.keySet().size(); i++) {
            for (int j = 0; j < this.soldItems.get(this.soldItems.keySet().toArray()[i]).size(); j++) {
                out.println("Item " + this.soldItems.get(this.soldItems.keySet().toArray()[i]).get(j).getName() + " was sold on day " + this.soldItems.get(this.soldItems.keySet().toArray()[i]).get(j).getDaySold() + " for price " + this.df.format(this.soldItems.get(this.soldItems.keySet().toArray()[i]).get(j).getSalePrice()));
                totalSalePrice += this.soldItems.get(this.soldItems.keySet().toArray()[i]).get(j).getSalePrice();
            }
        }

        out.println();
        out.println("Bought Items' details at the end of simulation");
        out.println("--------------------------------------------");
        float totalBuyPrice = 0.00F;
        for (int i = 0; i < this.boughtItems.keySet().size(); i++) {
            for (int j = 0; j < this.boughtItems.get(this.boughtItems.keySet().toArray()[i]).size(); j++) {
                out.println("Item " + this.boughtItems.get(this.boughtItems.keySet().toArray()[i]).get(j).getName() + " was bought on day " + this.boughtItems.get(this.boughtItems.keySet().toArray()[i]).get(j).getDayArrived() + " for price " + this.df.format(this.boughtItems.get(this.boughtItems.keySet().toArray()[i]).get(j).getPurchasePrice()));
                totalBuyPrice += this.boughtItems.get(this.boughtItems.keySet().toArray()[i]).get(j).getPurchasePrice();
            }
        }

        out.println();
        out.println("Ordered Items' details at the end of simulation");
        out.println("--------------------------------------------");
        float totalOrderPrice = 0.00F;
        for (int i = 0; i < this.yetToArriveItems.keySet().size(); i++) {
            for (int j = 0; j < this.yetToArriveItems.get(this.yetToArriveItems.keySet().toArray()[i]).size(); j++) {
                out.println("Item " + this.yetToArriveItems.get(this.yetToArriveItems.keySet().toArray()[i]).get(j).getName() + " is set to arrive on day " + this.yetToArriveItems.get(this.yetToArriveItems.keySet().toArray()[i]).get(j).getDayArrived() + " for price " + this.df.format(this.yetToArriveItems.get(this.yetToArriveItems.keySet().toArray()[i]).get(j).getPurchasePrice()));
                totalOrderPrice += this.yetToArriveItems.get(this.yetToArriveItems.keySet().toArray()[i]).get(j).getPurchasePrice();
            }
        }

        out.println();
        out.println("Total Sale Price at the end of " + (this.storeDay-1) + " days: " + this.df.format(totalSalePrice));
        out.println("Total Buy Price at the end of " + (this.storeDay-1) + " days: " + this.df.format(totalBuyPrice));
        out.println("Total Ordered Items' Price at the end of " + (this.storeDay-1) + " days: " + this.df.format(totalOrderPrice));
        out.println("Cash Register value at the end of " + (this.storeDay-1) + " days: " + this.df.format(this.registerValue));

        float sumBankWithdrawn = 0.00F;
        for (Staff obj: this.clerkRecord) {
            sumBankWithdrawn += obj.getBankWithdrawValue();
        }
        out.println("Total money withdrawn from the bank at the end of " + (this.storeDay-1) + " days: " + this.df.format(sumBankWithdrawn));
    }

    //Simulation
    public static void simulation (int days) throws NoSuchFieldException {
        Store objStore = new Store();       //Instantiate Store class
        Tracker tracker = (Tracker) objStore.registerSubscriber(subType.TRACKER); // register tracker
	    Subscriber dailyLogger;
        for (int i = 1; i <= days; i++) {
            //Store should be closed on every 7th day
            if (i % 7 == 0 && i != 1){
                objStore.storeIsClosed();
            }
            //Perform relevant actions
            else {
                dailyLogger = objStore.registerSubscriber(subType.LOGGER); // register daily logger
                objStore.arriveAtStore();
                objStore.checkRegister();
                objStore.openTheStore();
                objStore.cleanTheStore();
                objStore.leaveTheStore();
                tracker.clerkDaySummary();
                objStore.removeSubscriber(dailyLogger); // remove daily logger
            }
            objStore.increaseDay();     //Next day
        }
        objStore.endOfSimulationLog();
    }
}
