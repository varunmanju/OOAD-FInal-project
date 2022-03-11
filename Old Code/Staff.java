package abhi.ooad;


import java.util.ArrayList;

public class Staff {

    private String name;
    private ArrayList<Integer> workingDays;
    private int workingDayCount;
    private int leaveDayCount;
    private int bankWithdrawValue;
    private int damageFrequency;
    private int itemsDamaged;
    private int itemsPurchased;
    private int itemsSold;
    private float itemsDamagedValue;
    private float itemsPurchasedValue;
    private float itemsSoldValue;
    public Tune tunealgorithm;
    public int damage=0;
    public Staff(String name, int damageFrequency,Tune tunealgorithm) {
        this.name = name;
        this.damageFrequency = damageFrequency;
        this.workingDays = new ArrayList<>();
        this.tunealgorithm=tunealgorithm;
        itemsDamaged = 0;
        itemsPurchased = 0;
        itemsSold = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkingDayCount() {
        return workingDayCount;
    }

    public void setWorkingDayCount(int workingDayCount) {
        this.workingDayCount = workingDayCount;
    }

    public int getLeaveDay() {
        return leaveDayCount;
    }

    public void setLeaveDay(int leaveDayCount) {
        this.leaveDayCount = leaveDayCount;
    }

    public int getBankWithdrawValue() {
        return bankWithdrawValue;
    }

    public void setBankWithdrawValue(int bankWithdrawValue) {
        this.bankWithdrawValue = bankWithdrawValue;
    }

    public int getDamageFrequency() {
        return damageFrequency;
    }

    public void setDamageFrequency(int damageFrequency) {
        this.damageFrequency = damageFrequency;
    }

    public int getItemsDamaged() {
        return itemsDamaged;
    }

    public void setItemsDamaged(int itemsDamaged) {
        this.itemsDamaged = itemsDamaged;
    }

    public int getItemsPurchased() {
        return itemsPurchased;
    }

    public void setItemsPurchased(int itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }

    public int getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(int itemsSold) {
        this.itemsSold = itemsSold;
    }

    public float getItemsDamagedValue() {
        return itemsDamagedValue;
    }

    public void setItemsDamagedValue(float itemsDamagedValue) {
        this.itemsDamagedValue = itemsDamagedValue;
    }

    public float getItemsPurchasedValue() {
        return itemsPurchasedValue;
    }

    public void setItemsPurchasedValue(float itemsPurchasedValue) {
        this.itemsPurchasedValue = itemsPurchasedValue;
    }

    public float getItemsSoldValue() {
        return itemsSoldValue;
    }

    public void setItemsSoldValue(float itemsSoldValue) {
        this.itemsSoldValue = itemsSoldValue;
    }

    public ArrayList<Integer> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(ArrayList<Integer> workingDays) {
        this.workingDays = workingDays;
    }

    public void addWorkingDays(int day){
        this.workingDays.add(day);
    }
   
    public int dotuning(Item obj,int idx){
    	
        return this.tunealgorithm.tuning(obj,idx);
     }
}
