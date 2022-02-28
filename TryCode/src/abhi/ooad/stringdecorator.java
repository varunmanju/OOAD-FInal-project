package abhi.ooad;

import java.util.ArrayList;

public abstract class stringdecorator extends Stringed{
	
	public stringdecorator(String name, float purchasePrice, float listPrice, String newOrUsed, int dayArrived,
			String condition, boolean electric) {
		super(name, purchasePrice, listPrice, newOrUsed, dayArrived, condition, electric);
		// TODO Auto-generated constructor stub
	}
	Stringed s;
	
	public abstract ArrayList<Item> additionalbought();
	
}