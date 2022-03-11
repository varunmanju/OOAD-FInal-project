package abhi.ooad;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class Manual implements Tune{
	private Random rand;
	public int damages=0;
	Store store1;
	public Map<String, List<Item>> inventory;
	public Manual(Map<String, List<Item>> inventory,Store store1) {
		this.inventory=inventory;
		this.store1=store1;
		
	}
	private int getRandomInteger (int lowerBound, int upperBound) {
	    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
	}
	private int getConditionLevel(String conditionText) {
        switch (conditionText) {
            case "Poor": return 1;
            case "Fair": return 2;
            case "Good": return 3;
            case "Very Good": return 4;
            case "Excellent": return 5;
            default: return 5;
        }
    }
	private String getConditionText(int conditionLevel) {
        switch (conditionLevel) {
            case 1: return "Poor";
            case 2: return "Fair";
            case 3: return "Good";
            case 4: return "Very Good";
            case 5: return "Excellent";
            default: return "Excellent";
        }
    }
	private void announcement(String methodName, String message) {
        out.println("Day "+ store1.storeDay + ": " + methodName + " - " + message);
    }
	private void remove(String selectedItem,Item it) {
		ArrayList<Item> item = new ArrayList<>();
		
		if (this.inventory.get(selectedItem) != null) {
          for (Object obj: this.inventory.get(selectedItem)) {
              item.add((Item) obj);
          }
         
         
          item.remove(it);
          this.inventory.put(selectedItem, (List<Item>) item.clone());
          
         
      }
	}
    private int cleanTheStore(Item obj){
        //Take damage route only if inventory is not empty
    	int damage=0;
        if (!this.inventory.isEmpty()) {
            //Damage route: Get damage frequency of the clerk who is working today
            //              Determine if there will be a damage based on the damage frequency of the clerk
            //              Randomly select an item from the inventory that is to be damaged
            //              Lower the condition by one stage if the item to be damaged is not in poor condition
            //              Remove the item from inventory if item to be damaged is already in poor condition
        		
                
                Item selectedItem;
        
                
				selectedItem = obj;
				
                if (getConditionLevel(obj.getCondition()) == 1) {
                    if (this.inventory.get(obj.getClass().getSimpleName()).size() > 1) {
        
                        this.remove(obj.getClass().getSimpleName(), selectedItem);
                        
                    }
                    else {
                        this.inventory.remove(obj.getClass().getSimpleName());
                       
                    announcement("doInventory" ,obj.getClass().getSimpleName() + " is damaged " + " while tuning. Instrument is removed from the inventory.");
                    
                } 
                }else {
                    obj.setCondition(getConditionText(getConditionLevel(selectedItem.getCondition())-1));
                    obj.setListPrice(selectedItem.getListPrice() * 0.80F);
                    obj.setSalePrice(obj.getListPrice());
                    announcement("doInventory",obj.getClass().getSimpleName() + " is damaged " +  " while tuning. Instrument condition is lowered and list price is reduced.");
                    
                }
            
        }
        return damage;
    }
	@Override
	public int tuning(Item obj,int idx)
	{	
		damages=0;
		this.rand = new Random();
		
		if(this.getRandomInteger(1, 100) <= 80)
		{	
			
			if (obj.getClass().getSimpleName() == "RecordPlayer" || obj.getClass().getSimpleName() == "CDPlayer" || obj.getClass().getSimpleName() == "MP3Player" || obj.getClass().getSimpleName() == "CassettePlayer")
			{
			if(obj.isEqualized()==false) {
				
				obj.setEqualized(true);
				
			}
			
			}
			if(obj.getClass().getSimpleName() == "Guitar" || obj.getClass().getSimpleName() == "Bass" || obj.getClass().getSimpleName() == "Mandolin")
			{
				
				if(obj.isTuned()==false) {
					
					obj.setTuned(true);
					
				}
				
			}
			if(obj.getClass().getSimpleName()=="Flute" || obj.getClass().getSimpleName()=="Harmonica" || obj.getClass().getSimpleName() == "Saxophone")
			{
				
				if(obj.isAdjusted()==false) {
					
					obj.setAdjusted(true);
					
				}
		
			}
			announcement("doInventory", obj.getName() + " "+ idx + " is tuned from false to true" + " Manually");
		}
		if(this.getRandomInteger(1, 100) <= 20)
		{	
			if (obj.getClass().getSimpleName() == "RecordPlayer" || obj.getClass().getSimpleName() == "CDPlayer" || obj.getClass().getSimpleName() == "MP3Player" || obj.getClass().getSimpleName() == "CassettePlayer")
			{
				
			if(obj.isEqualized()==true) {
				
				obj.setEqualized(false);
				
				if(getRandomInteger(1, 100) <= 10)
				{
				
					cleanTheStore(obj);
					damages+=1;
				}
			}
			
			}
			if(obj.getClass().getSimpleName() == "Guitar" || obj.getClass().getSimpleName() == "Bass" || obj.getClass().getSimpleName() == "Mandolin")
			{	
				if(obj.isTuned()==true) {
					
					obj.setTuned(false);
			
					if(getRandomInteger(1, 100) <= 10)
					{
					
						cleanTheStore(obj);
						damages+=1;
					}
				}
				
			}
			if(obj.getClass().getSimpleName()=="Flute" || obj.getClass().getSimpleName()=="Harmonica" || obj.getClass().getSimpleName() == "Saxophone")
			{	
				if(obj.isAdjusted()==true) {
					
					obj.setAdjusted(false);
					
					if(getRandomInteger(1, 100) <= 10)
					{
					
						cleanTheStore(obj);
						damages+=1;
					}
				}
		
			}
			announcement("doInventory", obj.getName() + " "+ idx +" is tuned from true to false" + " Manually");
		}
		return damages;
	}
	
}
