package abhi.ooad;

import java.util.Random;

public abstract class neck{
	public abstract int getprize();
	public abstract int getRandomInteger(int lowerBound, int upperBound);
	
}
class NeckA extends neck {
public int prize;
public Random rand;
public int getRandomInteger (int lowerBound, int upperBound) {
    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
}
public NeckA()
{
	this.rand=new Random();
	this.prize=getRandomInteger(1,50);
	
}
public int getprize() {
	return this.prize;
	
}
}

class NeckB extends neck {
public int prize;
public Random rand;
public int getRandomInteger (int lowerBound, int upperBound) {
    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
}
public NeckB()
{
	this.rand=new Random();
	this.prize=getRandomInteger(1,50);
	
}
public int getprize() {
	return this.prize;
	
}
}


