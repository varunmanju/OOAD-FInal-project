package abhi.ooad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public abstract class Staff {
    String name;    // Velma and Shaggy
	Store store;
}

class Clerk extends Staff implements Logger {
    int daysWorked;
    String workingAtStore;
    double damageChance;    // Velma = .05, Shaggy = .20
    Store store;
    boolean sickToday = false;
    int damage=0;
    public Tune tunealgorithm;
    public static ArrayList<Tune> algos = new ArrayList<Tune>() {{
        add(new Haphazard());
        add(new Manual());
        add(new Electronic());
    }};
   
    
    Clerk(String name, double damageChance) {
         this.name = name;
         this.damageChance = damageChance;
         daysWorked = 0;
         
         
    }
    void setStoreInstance(Store store) {
        this.store = store;
        
    }
    void setalgo()
    {
    	int algo= Utility.rndFromRange(0,2);
        this.tunealgorithm=algos.get(algo);
    }
    void arriveAtStore() {
        out(this.name + " arrives at store.");
        // have to check for any arriving items slated for this day
        out( this.name + " checking for arriving items.");
        // there's a tricky concurrent removal thing that prevents doing this
        // with a simple for loop - you need to use an iterator
        // https://www.java67.com/2014/03/2-ways-to-remove-elementsobjects-from-ArrayList-java.html#:~:text=There%20are%20two%20ways%20to,i.e.%20remove(Object%20obj).
        Iterator<Item> itr = store.inventory.arrivingItems.iterator();
        while (itr.hasNext()) {
            Item item = itr.next();
            if (item.dayArriving == store.today) {
                out( this.name + " putting a " + item.itemType.toString().toLowerCase() + " in inventory.");
                store.inventory.items.add(item);
                itr.remove();
            }
        }
    }

    void checkRegister() {
        out(this.name + " checks: "+Utility.asDollar(store.cashRegister)+" in register.");
        if (store.cashRegister<75) {
            out("Cash register is low on funds.");
            this.goToBank();
        }
    }

    void goToBank() {
        out(this.name + " gets money from the bank.");
        store.cashRegister += 1000;
        store.cashFromBank += 1000;
        this.checkRegister();
    }

    void doInventory() {
        out(this.name + " is doing inventory.");
        int total_damages=this.tune();
        System.out.println(total_damages);
        for (ItemType type: ItemType.values()) {
            int numItems = store.inventory.countByType(store.inventory.items,type);
            out(this.name + " counts "+numItems+" "+type.toString().toLowerCase());
            if (numItems == 0) {
                this.placeAnOrder(type);
            }
        }
        int count = store.inventory.items.size();
        double worth = store.inventory.getValue(store.inventory.items);
        out(this.name + " finds " + count + " items in store, worth "+Utility.asDollar(worth));
    }

    void placeAnOrder(ItemType type) {
        out(this.name + " needs to order "+type.toString().toLowerCase());
        // order 3 more of this item type
        // they arrive in 1 to 3 days
        int arrivalDay = Utility.rndFromRange(1,3);
        // check to see if any are in the arriving queue
        int count = store.inventory.countByType(store.inventory.arrivingItems,type);
        if (count>0) {
            out("There is an order coming for " + type.toString().toLowerCase());
        }
        else {
            // order 3 of the missing items if you have the money to buy them
            for (int i = 0; i < 3; i++) {
                Item item = store.inventory.makeNewItemByType(type);
                if (store.cashRegister > item.purchasePrice) {
                    out(this.name + " ordered a " + item.itemType.toString().toLowerCase());
                    item.dayArriving = store.today + arrivalDay;
                    store.inventory.arrivingItems.add(item);
                }
                else {
                    out("Insufficient funds to order this item.");
                }
            }
        }
    }

    void openTheStore() {
        int buyers = Utility.rndFromRange(4,10);
        int sellers = Utility.rndFromRange(1,4);
        out(buyers + " buyers, "+sellers+" sellers today.");
        for (int i = 1; i <= buyers; i++) this.sellAnItem(i);
        for (int i = 1; i <= sellers; i++) this.buyAnItem(i);
    }

    void sellAnItem(int customer) {
        String custName = "Buyer "+customer;
        out(this.name+" serving "+custName);
        ItemType type = Utility.randomEnum(ItemType.class);
        out(custName + " wants to buy a "+type.toString().toLowerCase());
        int countInStock = store.inventory.countByType(store.inventory.items, type);
        // if no items - bye
        if (countInStock == 0) {
            out (custName + " leaves, no items in stock.");
        }
        else {
            // pick one of the types of items from inventory
            int pickItemIndex = Utility.rndFromRange(1,countInStock);
            Item item = GetItemFromInventoryByCount(countInStock, type);
            out("Item is "+type.toString().toLowerCase()+" in "+item.condition.toString().toLowerCase()+" condition.");
            // 50% chance to buy at listPrice
            out (this.name+" selling at "+Utility.asDollar(item.listPrice));
            if (Utility.rnd()>.5) {
                sellItemtoCustomer(item, custName);
            }
            else {
                // if not, clerk offers 10% off listPrice
                double newListPrice = item.listPrice * .9;
                out (this.name+" selling at "+Utility.asDollar(newListPrice));
                // now 75% chance of buy
                if (Utility.rnd()>.25) {
                    item.listPrice = newListPrice;
                    sellItemtoCustomer(item,custName);
                }
                else {
                    out(custName + " wouldn't buy item.");
                }
            }
        }
    }

    // things we need to do when an item is sold
    void sellItemtoCustomer(Item item,String custName) {
        String itemName = item.itemType.toString().toLowerCase();
        String price = Utility.asDollar(item.listPrice);
        out (this.name + " is selling "+ itemName + " for " + price +" to "+custName);
        // when sold - move item to sold items with daySold and salePrice noted
        out ( "inventory count: "+store.inventory.items.size());
        store.inventory.items.remove(item);
        out ( "inventory count: "+store.inventory.items.size());
        item.salePrice = item.listPrice;
        item.daySold = store.today;
        store.inventory.soldItems.add(item);
        // money for item goes to register
        store.cashRegister += item.listPrice;
    }

    // find a selected item of a certain type from the items
    Item GetItemFromInventoryByCount(int countInStock, ItemType type) {
        int count = 0;
        for(Item item: store.inventory.items) {
            if (item.itemType == type) {
                count += 1;
                if (count == countInStock) return item;
            }
        }
        return null;
    }

    void buyAnItem(int customer) {
        String custName = "Seller "+customer;
        out(this.name+" serving "+custName);
        ItemType type = Utility.randomEnum(ItemType.class);
        out(custName + " wants to sell a "+type.toString().toLowerCase());
        Item item = store.inventory.makeNewItemByType(type);
        // clerk will determine new or used, condition, purchase price (based on condition)
        // we'll take the random isNew, condition from the generated item
        out("Item is "+type.toString().toLowerCase()+" in "+item.condition.toString().toLowerCase()+" condition.");
        item.purchasePrice = getPurchasePriceByCondition(item.condition);
        // seller has 50% chance of selling
        out (this.name+" offers "+Utility.asDollar(item.purchasePrice));
        if (Utility.rnd()>.5) {
            buyItemFromCustomer(item, custName);
        }
        else {
            // if not, clerk will add 10% to purchasePrice
            item.purchasePrice += item.purchasePrice * .10;
            out (this.name+" offers "+Utility.asDollar(item.purchasePrice));
            // seller has 75% chance of selling
            if (Utility.rnd()>.25) {
                buyItemFromCustomer(item, custName);
            }
            else {
                out(custName + " wouldn't sell item.");
            }
        }
    }

    void buyItemFromCustomer(Item item, String custName) {
        String itemName = item.itemType.toString().toLowerCase();
        String price = Utility.asDollar(item.purchasePrice);
        out (this.name + " is buying "+ itemName + " for " + price +" from "+custName);
        if (store.cashRegister>item.purchasePrice) {
            store.cashRegister -= item.purchasePrice;
            item.listPrice = 2 * item.purchasePrice;
            item.dayArriving = store.today;
            store.inventory.items.add(item);
        }
        else {
            out(this.name + "cannot buy item, register only has "+Utility.asDollar(store.cashRegister));
        }
    }


    double getPurchasePriceByCondition(Condition condition) {
        int lowPrice = 2*condition.level;
        int highPrice = 10*condition.level;
        return (double) Utility.rndFromRange(lowPrice,highPrice);
    }


    void cleanTheStore() {
        out(this.name + " is cleaning up the store.");
        if (Utility.rnd()>this.damageChance) {
            out(this.name + " doesn't break anything.");
        }
        else if (store.inventory.items.size() > 0) {
            out(this.name + " breaks something!");
            // reduce the condition for a random item
            int pickItemIndex = Utility.rndFromRange(0,store.inventory.items.size()-1);
            Item item = store.inventory.items.get(pickItemIndex);
            if(item.condition.level > 1) {
                item.damageAnItem(item);
            }
            else {
                store.inventory.discardedItems.add(item);
                store.inventory.items.remove(item);
            }
            // take the item off the main inventory and put it on the broken items ArrayList
            // left as an exercise to the reader :-)
        }
        else {
            out(this.name + " nothing to break. Inventory has no items.");
        }
    }
    void leaveTheStore() {
        out(this.name + " locks up the store and leaves.");
    }
public int dotuning(Item obj,int idx){
    	
        return this.tunealgorithm.tuning(obj,idx,this);
     }
  private int tune() {
	  	ArrayList<Item> items = (ArrayList<Item>)store.inventory.items.clone();
    	int dam=0;
    	this.damage=0;
    	 System.out.println(""+this.name);
    	 
    	  for (Itemtype2 type: Itemtype2.values()) {
    		 
               int numItems = store.inventory.countByType2(items,type);
               
               if(numItems>0) {
            	   int count=0;
            	   for(Item item: items) {
            		   
            		   if(item.itemType.getName()==type.getName()) {
            			   count+=1;
            			   dam=this.dotuning(item,count);
            			   this.damage+=dam;
            		   }
            	   }
               }
    	  }
    	  return damage;
  }
  public class AbstractguitarkitA extends Abstractguitarkit{
  	
  	initialize item=new initialize();
  	public AbstractguitarkitA() {
  		
  		item.populate("Southside");
  	}
  	public GuitarKit createGuitar() {
  		int prize=0;
  		bridge b = null;
  		pickguard g = null;
  		pickups p = null;
  		knobset k = null;
  		covers c = null;
  		neck n = null;
  		System.out.println("Pickup A prize "+ item.p1.get(0).getprize());
  		System.out.println("Pickup B prize "+ item.p1.get(1).getprize());
  		System.out.println("Pickup C prize "+ item.p1.get(2).getprize());
  		System.out.println("enter the choice Southside items for pickups Pickup A or Pickup B or Pickup C");
  		
  		Scanner scan = new Scanner(System.in);
  		String s = scan.nextLine();
  		if(s.equals("Pickup A")) {
  		 p=item.p1.get(0);
  		prize+=p.getprize();
  	}
  		if(s.equals("Pickup B")) {
  			 p=	item.p1.get(1);
  			prize+=p.getprize();
      	}
  		if(s.equals("Pickup C")) {
  			p=item.p1.get(2);
      		prize+=p.getprize();
      	}
  		System.out.println("Knobset A prize "+ item.k1.get(0).getprize());
  		System.out.println("Knobset B prize "+ item.k1.get(1).getprize());
  		System.out.println("Knobset C prize "+ item.k1.get(2).getprize());
  		System.out.println("enter the choice Southside items for knobset Knobset A or Knobset B or Knobset C");
  		Scanner scan1 = new Scanner(System.in);
  		String s1 = scan1.nextLine();
  		if(s1.equals("Knobset A")) {
  			 k=item.k1.get(0);
  			prize+=k.getprize();
  		}
  		if(s1.equals("Knobset B")) {
  			 k=item.k1.get(1);
  			prize+=k.getprize();
      	}
  		if(s1.equals("Knobset C")) {
  			 k=item.k1.get(2);
  			prize+=k.getprize();
      		}
  		System.out.println("Covers A prize "+ item.c1.get(0).getprize());
  		System.out.println("Covers B prize "+ item.c1.get(1).getprize());
  		System.out.println("Covers C prize "+ item.c1.get(2).getprize());
  		System.out.println("enter the choice Southside items for Covers Covers A or Covers B or Covers C");
  		Scanner scan2 = new Scanner(System.in);
  		String s2 = scan2.nextLine();
  		if(s2.equals("Covers A")) {
  			 c=item.c1.get(0);
  			prize+=c.getprize();
  		}
  		if(s2.equals("Covers B")) {
      		 c=item.c1.get(1);
      		prize+=c.getprize();
      	}
  		if(s2.equals("Covers C")) {
  			 c=item.c1.get(2);
  			prize+=c.getprize();
      		}
  		System.out.println("Neck A prize "+ item.n1.get(0).getprize());
  		System.out.println("Neck B prize "+ item.n1.get(1).getprize());
  		System.out.println("Neck C prize "+ item.n1.get(2).getprize());
  		System.out.println("enter the choice Southside items for Neck Neck A or Neck B or Neck C");
  		Scanner scan3 = new Scanner(System.in);
  		String s3 = scan3.nextLine();
  		if(s3.equals("Neck A")) {
  			 n=item.n1.get(0);
  			prize+=n.getprize();
  		}
  		if(s3.equals("Neck B")) {
  			 n=item.n1.get(1);
  			prize+=n.getprize();
      	}
  		if(s3.equals("Neck C")) {
  			 n=item.n1.get(2);
  			prize+=n.getprize();
      		}
  		System.out.println("Pickguard A prize "+ item.g1.get(0).getprize());
  		System.out.println("Pickguard B prize "+ item.g1.get(1).getprize());
  		System.out.println("Pickguard C prize "+ item.g1.get(2).getprize());
  		System.out.println("enter the choice Southside items for Pickguard Pickguard A or Pickguard B or Pickguard C");
  		Scanner scan4 = new Scanner(System.in);
  		String s4 = scan4.nextLine();
  		if(s4.equals("Pickguard A")) {
  			g=item.g1.get(0);
  			prize+=g.getprize();
  		}
  		if(s4.equals("Pickguard B")) {
  			 g=item.g1.get(1);
  			prize+=g.getprize();
      	}
  		if(s4.equals("Pickguard C")) {
  			 g=item.g1.get(2);
  			prize+=g.getprize();
      		}
  		System.out.println("Bridge A prize "+ item.b1.get(0).getprize());
  		System.out.println("Bridge B prize "+ item.b1.get(1).getprize());
  		System.out.println("Bridge C prize "+ item.b1.get(2).getprize());
  		System.out.println("enter the choice Southside items for Bridge Bridge A or Bridge B or Bridge C");
  		Scanner scan5 = new Scanner(System.in);
  		String s5 = scan5.nextLine();
  		if(s5.equals("Bridge A")) {
  			b=item.b1.get(0);
  			prize+=b.getprize();
  		}
  		if(s5.equals("Bridge B")) {
  			b=item.b1.get(1);
  			prize+=b.getprize();
  			}
  		if(s5.equals("Bridge C")) {
  			b=item.b1.get(2);
  			prize+=b.getprize();
      		}
  		
  		
			GuitarKit guitar=new GuitarKit("Guitar",prize,b,k,c,n,g,p);
  		return guitar;
  }
  }
  public class AbstractguitarkitB extends Abstractguitarkit{
  	initialize item=new initialize();
  	public AbstractguitarkitB() {
  		
  		item.populate("Northside");
  	}
  	public GuitarKit createGuitar() {
  		int prize=0;
  		bridge b = null;
  		pickguard g = null;
  		pickups p = null;
  		knobset k = null;
  		covers c = null;
  		neck n = null;
  		System.out.println("enter the choice Northside items for pickups Pickup A or Pickup B or Pickup C");
  		Scanner scan = new Scanner(System.in);
  		System.out.println("Pickup A prize "+ item.p2.get(0));
  		System.out.println("Pickup B prize "+ item.p2.get(1));
  		System.out.println("Pickup C prize "+ item.p2.get(2));
  		String s = scan.nextLine();
  		if(s.equals("Pickup A")) {
  		 p=item.p2.get(0);
  		prize+=p.getprize();
  	}
  		if(s.equals("Pickup B")) {
  			 p=	item.p2.get(1);
  			prize+=p.getprize();
      	}
  		if(s.equals("Pickup C")) {
  			p=item.p2.get(2);
      		prize+=p.getprize();
      	}
  		System.out.println("enter the choice Northside items for knobset Knobset A or Knobset B or Knobset C");
  		System.out.println("Knobset A prize "+ item.k2.get(0).getprize());
  		System.out.println("Knobset B prize "+ item.k2.get(1).getprize());
  		System.out.println("Knobset C prize "+ item.k2.get(2).getprize());
  		Scanner scan1 = new Scanner(System.in);
  		String s1 = scan1.nextLine();
  		if(s1.equals("Knobset A")) {
  			 k=item.k2.get(0);
  			prize+=k.getprize();
  		}
  		if(s1.equals("Knobset B")) {
  			 k=item.k2.get(1);
  			prize+=k.getprize();
      	}
  		if(s1.equals("Knobset C")) {
  			 k=item.k2.get(2);
  			prize+=k.getprize();
      		}
  		System.out.println("enter the choice Northside items for Covers Covers A or Covers B or Covers C");
  		System.out.println("Covers A prize "+ item.c2.get(0).getprize());
  		System.out.println("Covers B prize "+ item.c2.get(1).getprize());
  		System.out.println("Covers C prize "+ item.c2.get(2).getprize());
  		Scanner scan2 = new Scanner(System.in);
  		String s2 = scan2.nextLine();
  		if(s2.equals("Covers A")) {
  			 c=item.c2.get(0);
  			prize+=c.getprize();
  		}
  		if(s2.equals("Covers B")) {
      		 c=item.c2.get(1);
      		prize+=c.getprize();
      	}
  		if(s2.equals("Covers C")) {
  			 c=item.c2.get(2);
  			prize+=c.getprize();
      		}
  		System.out.println("enter the choice Northside items for Neck Neck A or Neck B or Neck C");
  		System.out.println("Neck A prize "+ item.n2.get(0).getprize());
  		System.out.println("Neck B prize "+ item.n2.get(1).getprize());
  		System.out.println("Neck C prize "+ item.n2.get(2).getprize());
  		Scanner scan3 = new Scanner(System.in);
  		String s3 = scan3.nextLine();
  		if(s3.equals("Neck A")) {
  			 n=item.n2.get(0);
  			prize+=n.getprize();
  		}
  		if(s3.equals("Neck B")) {
  			 n=item.n2.get(1);
  			prize+=n.getprize();
      	}
  		if(s3.equals("Neck C")) {
  			 n=item.n2.get(2);
  			prize+=n.getprize();
      		}
  		System.out.println("enter the choice Northside items for Pickguard Pickguard A or Pickguard B or Pickguard C");
  		System.out.println("Pickguard A prize "+ item.g2.get(0).getprize());
  		System.out.println("Pickguard B prize "+ item.g2.get(1).getprize());
  		System.out.println("Pickguard C prize "+ item.g2.get(2).getprize());
  		Scanner scan4 = new Scanner(System.in);
  		String s4 = scan4.nextLine();
  		if(s4.equals("Pickguard A")) {
  			g=item.g2.get(0);
  			prize+=g.getprize();
  		}
  		if(s4.equals("Pickguard B")) {
  			 g=item.g2.get(1);
  			prize+=g.getprize();
      	}
  		if(s4.equals("Pickguard C")) {
  			 g=item.g2.get(2);
  			prize+=g.getprize();
      		}
  		System.out.println("enter the choice Northside items for Bridge Bridge A or Bridge B or Bridge C");
  		System.out.println("Bridge A prize "+ item.b2.get(0).getprize());
  		System.out.println("Bridge B prize "+ item.b2.get(1).getprize());
  		System.out.println("Bridge C prize "+ item.b2.get(2).getprize());
  		Scanner scan5 = new Scanner(System.in);
  		String s5 = scan5.nextLine();
  		if(s5.equals("Bridge A")) {
  			b=item.b2.get(0);
  			prize+=b.getprize();
  		}
  		if(s5.equals("Bridge B")) {
  			b=item.b2.get(1);
  			prize+=b.getprize();
  			}
  		if(s5.equals("Bridge C")) {
  			b=item.b2.get(2);
  			prize+=b.getprize();
      		}
  		
  		
			GuitarKit guitar=new GuitarKit("Guitar",prize,b,k,c,n,g,p);
  		return guitar;
  }
  }
  public void client_code(Abstractguitarkit g) {
  		Item guitar=g.createGuitar();
        store.inventory.soldItems.add(guitar);
        System.out.println(guitar.purchasePrice);
  }
  public void create(){
  	if(store.storeName.equals("Southside")) {
  		System.out.println("Southside uses factory A");
  		client_code(new AbstractguitarkitA());
  	}
  	else {
  		client_code(new AbstractguitarkitB());
  	}
  	
  }


  
}
        
