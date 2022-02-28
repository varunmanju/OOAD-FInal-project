package abhi.ooad;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;



public class gigs extends stringdecorator{
	private Random rand;
	private float pp;
	private String newOrUsed;
	private int dayArrived;
	private String condition;
	private boolean electric;
	private float fprize;
	private ArrayList<Item> s;
	private int dayarrived;
	private ArrayList<Item> gigs2 = new ArrayList<>();
	public Map<String, List<Item>> inventory;
	private int getRandomInteger (int lowerBound, int upperBound) {
	    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
	}
	public gigs(ArrayList<Item> s,String name, float purchasePrice, String newOrUsed, int dayArrived,String condition, boolean electric,Map<String, List<Item>> inventory) {
		super(name, purchasePrice,purchasePrice*2, newOrUsed, dayArrived, condition, electric);
		this.s=s;
		this.pp=purchasePrice;
		this.newOrUsed=newOrUsed;
		this.dayArrived=dayArrived;
		this.condition=condition;
		this.electric=electric;
		this.inventory=inventory;
		// TODO Auto-generated constructor stub
	}


	@Override
	public ArrayList<Item> additionalbought(){
		// TODO Auto-generated method stub
		
		
		this.rand = new Random();
		if(electric) {
			if(this.getRandomInteger(1, 100) <= 20) {
				if(this.inventory.get("GigBag")!=null) {
				if(this.inventory.get("GigBag").size()!=0) {
			gigs2.add(this.inventory.get("GigBag").get(0));
				}
				}
			
			}
		}
		else {
			if(this.getRandomInteger(1, 100) <= 10) {
				if(this.inventory.get("GigBag")!=null) {
				if(this.inventory.get("GigBag").size()!=0) {
				gigs2.add(this.inventory.get("GigBag").get(0));
				}
				}
				}
		}
		return gigs2;
	}
	
	
}





