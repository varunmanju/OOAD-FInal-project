package abhi.ooad;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class cables1 extends stringdecorator{
	private Random rand;
	private float pp;
	private String newOrUsed;
	private int dayArrived;
	private String condition;
	private boolean electric;
	private float fprize;
	private ArrayList<Item> s;
	private int dayarrived;
	private ArrayList<Item> cables1 = new ArrayList<>();
	public Map<String, List<Item>> inventory;
	private int getRandomInteger (int lowerBound, int upperBound) {
	    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
	}
	public cables1(ArrayList<Item> s,String name, float purchasePrice, String newOrUsed, int dayArrived,String condition, boolean electric,Map<String, List<Item>> inventory) {
		super(condition, purchasePrice, purchasePrice, condition, dayArrived, condition, electric);
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
		
		Item item=null;
		if(electric) {
			if(this.getRandomInteger(1, 100) <= 30)
			{	
				int count=this.getRandomInteger(0, 1);
				for (int i = 0; i <=count; i++) {
					if(this.inventory.get("Cables")!=null) {
						if(this.inventory.get("Cables").size()>=i+1) {
					  cables1.add(this.inventory.get("Cables").get(i));
					}
				}
				}
					
		
			
			}
		}
		else {
			if(this.getRandomInteger(1, 100) <= 20) {
				int count=this.getRandomInteger(0, 1);
				for (int i = 0; i <=count; i++) {
					if(this.inventory.get("Cables")!=null) {
						if(this.inventory.get("Cables").size()>=i+1) {
					  cables1.add(this.inventory.get("Cables").get(i));
					}
				}
				}
				}
		}
		return cables1;
	}
}