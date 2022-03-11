package abhi.ooad;

import java.util.Random;

public abstract class covers{
	public abstract int getprize();
	public abstract int getRandomInteger(int lowerBound, int upperBound);
	
}
class CoversA extends covers {
public int prize;
public Random rand;
public int getRandomInteger (int lowerBound, int upperBound) {
    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
}
public CoversA()
{
	this.rand=new Random();
	this.prize=getRandomInteger(1,50);
	
}
public int getprize() {
	return this.prize;
	
}
}

class CoversB extends covers {
public int prize;
public Random rand;
public int getRandomInteger (int lowerBound, int upperBound) {
    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
}
public CoversB()
{
	this.rand=new Random();
	this.prize=getRandomInteger(1,50);
	
}
public int getprize() {
	return this.prize;
	
}
}
