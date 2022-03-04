package abhi.ooad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class practiceamp extends stringdecorator{
	private Random rand;
	private float pp;
	private String newOrUsed;
	private int dayArrived;
	private String condition;
	private boolean electric;
	private ArrayList<Item> s;
	private int dayarrived;
	private ArrayList<Item> pamps2 = new ArrayList<>();
	public Map<String, List<Item>> inventory;
	private int getRandomInteger (int lowerBound, int upperBound) {
	    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
	}
	public practiceamp(ArrayList<Item> s,String name, float purchasePrice, String newOrUsed, int dayArrived,String condition, boolean electric,Map<String, List<Item>> inventory) {
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
			if(this.getRandomInteger(1, 100) <= 25) {
				if(this.inventory.get("PracticeAmps")!=null) {
				if(this.inventory.get("PracticeAmps").size()!=0) {
			pamps2.add(this.inventory.get("PracticeAmps").get(0));
				}
			}
			}
		}
		else {
			if(this.getRandomInteger(1, 100) <= 15) {
				if(this.inventory.get("PracticeAmps")!=null) {
				if(this.inventory.get("PracticeAmps").size()!=0) {
				pamps2.add(this.inventory.get("PracticeAmps").get(0));
				}
				}
				}
		}
		return pamps2;
	}
	
}
