package abhi.ooad;

import java.util.Random;

public abstract class pickguard{
	public abstract int getprize();
	public abstract int getRandomInteger(int lowerBound, int upperBound);
	
}
class PickguardA extends pickguard {
public int prize;
public Random rand;
public int getRandomInteger (int lowerBound, int upperBound) {
    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
}
public PickguardA()
{
	this.rand=new Random();
	this.prize=getRandomInteger(1,50);
	
}
public int getprize() {
	return this.prize;
	
}
}

class PickguardB extends pickguard {
public int prize;
public Random rand;
public int getRandomInteger (int lowerBound, int upperBound) {
    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
}
public PickguardB()
{
	this.rand=new Random();
	this.prize=getRandomInteger(1,50);
	
}
public int getprize() {
	return this.prize;
	
}
}