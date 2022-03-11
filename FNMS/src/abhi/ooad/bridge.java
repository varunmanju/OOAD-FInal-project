package abhi.ooad;

import java.util.Random;

public abstract class bridge{
	public abstract int getprize();
	public abstract int getRandomInteger(int lowerBound, int upperBound);
	
}
class BridgeA extends bridge {
public int prize;
public Random rand;
public int getRandomInteger (int lowerBound, int upperBound) {
    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
}
public BridgeA()
{
	this.rand=new Random();
	this.prize=getRandomInteger(1,50);
	
}
public int getprize() {
	return this.prize;
	
}
}

class BridgeB extends bridge {
public int prize;
public Random rand;
public int getRandomInteger (int lowerBound, int upperBound) {
    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
}
public BridgeB()
{
	this.rand=new Random();
	this.prize=getRandomInteger(1,50);
	
}
public int getprize() {
	return this.prize;
	
}
}
