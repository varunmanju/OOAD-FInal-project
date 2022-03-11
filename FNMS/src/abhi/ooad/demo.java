package abhi.ooad;

public abstract class demo {
int i;
abstract void change();
}
class d extends demo{
	int i;
	public void change() {
		i=1;
		System.out.println(i);
	}
	
}