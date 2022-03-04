package abhi.ooad;

import static java.lang.System.out;

import java.util.Random;
public class Electronic implements Tune{
	private Random rand;
	 private void announcement(String methodName, String message) {
	        out.println("Day "+ Store.storeDay + ": " + methodName + " - " + message);
	    }
    @Override
	public int tuning(Item obj,int idx)
	{
			
			this.rand = new Random();
			
			if ((obj.getClass().getSimpleName()).equals("RecordPlayer") || (obj.getClass().getSimpleName()).equals("CDPlayer")|| (obj.getClass().getSimpleName()).equals("MP3Player") || (obj.getClass().getSimpleName()).equals("CassettePlayer"))
			{
				
			if(obj.isEqualized()==false) {
			
				obj.setEqualized(true);
			
				announcement("doInventory", obj.getName() + " "+ idx +" is tuned from false to true" + " Electronically");
			
			}
			}
			if((obj.getClass().getSimpleName()).equals("Guitar") || (obj.getClass().getSimpleName()).equals("Bass") || (obj.getClass().getSimpleName()).equals("Mandolin"))
			{
				
				if(obj.isTuned()==false) {
				
					obj.setTuned(true);
				
					
				}
				announcement("doInventory", obj.getName() + " "+ idx +" is tuned from false to true" + " Electronically");
			}
			if((obj.getClass().getSimpleName()).equals("Flute") || (obj.getClass().getSimpleName()).equals("Harmonica") || (obj.getClass().getSimpleName()).equals("Saxophone"))
			{
				
				if(obj.isAdjusted()==false) {
				
					obj.setAdjusted(true);
					
					
				}
				announcement("doInventory", obj.getName() + " "+ idx +" is tuned from false to true" + " Electronically");
			}
		
			return 0;
		}
    
	}


