package abhi.ooad;

import java.util.Random;

public abstract class knobset{
	public abstract int getprize();
	public abstract int getRandomInteger(int lowerBound, int upperBound);
	
}
class KnobsetA extends knobset {
public int prize;
public Random rand;
public int getRandomInteger (int lowerBound, int upperBound) {
    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
}
public KnobsetA()
{
	this.rand=new Random();
	this.prize=getRandomInteger(1,50);
	
}
public int getprize() {
	return this.prize;
	
}
}

class KnobsetB extends knobset {
public int prize;
public Random rand;
public int getRandomInteger (int lowerBound, int upperBound) {
    return this.rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
}
public KnobsetB()
{
	this.rand=new Random();
	this.prize=getRandomInteger(1,50);
	
}
public int getprize() {
	return this.prize;
	
}
}