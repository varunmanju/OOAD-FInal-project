package abhi.ooad;

public class GuitarKit extends Item{
	 public GuitarKit(String name,float prize,bridge b,knobset k,covers c, neck n,pickguard g,pickups p) {
		 name="Guitar";
		 purchasePrice=prize;
		 listPrice=prize*2;
		 isNew = (Utility.rnd() > .5); 
		 dayArriving=0;
		 condition=Utility.randomEnum(Condition.class);
		 bridge b1=b;
		 knobset k1=k;
		 covers c1=c;
		 neck n1=n;
		 pickguard g1=g;
		 pickups p1=p;
	 }
 }