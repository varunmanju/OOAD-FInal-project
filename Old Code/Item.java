package abhi.ooad;

import java.util.ArrayList;

public class Item {
    protected String name;
    protected float purchasePrice;
    private float listPrice;
    private String newOrUsed;
    private int dayArrived;
    private String condition;
    private float salePrice;
    private int daySold;

    public Item(String name, float purchasePrice, float listPrice, String newOrUsed, int dayArrived, String condition) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.listPrice = listPrice;
        this.newOrUsed = newOrUsed;
        this.dayArrived = dayArrived;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public float getListPrice() {
        return listPrice;
    }

    public String getNewOrUsed() {
        return newOrUsed;
    }

    public int getDayArrived() {
        return dayArrived;
    }

    public String getCondition() {
        return condition;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public Integer getDaySold() {
        return daySold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }

    public void setNewOrUsed(String newOrUsed) {
        this.newOrUsed = newOrUsed;
    }

    public void setDayArrived(int dayArrived) {
        this.dayArrived = dayArrived;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public void setDaySold(Integer daySold) {
        this.daySold = daySold;
    }

	public float setPrizes(float prize) {
		return 0;
	}

	public boolean isEqualized() {
		return false;
	}

	public void setEqualized(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public boolean isTuned() {
		return false;
	}

	public void setTuned(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public boolean isAdjusted() {
		return false;
	}

	public void setAdjusted(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public boolean isElectric() {
		return false;
	}
	public void setElectric(boolean electric) {
        // TODO Auto-generated method stub

    }


	
	
}

abstract class Music extends Item{
    private String band;
    private String album;

    public Music(String name, float purchasePrice, float listPrice, String newOrUsed, int dayArrived, String condition, String band, String album) {
        super(name, purchasePrice, listPrice, newOrUsed, dayArrived, condition);
        this.band = band;
        this.album = album;
    }

    public String getBand() {
        return band;
    }

    public String getAlbum() {
        return album;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}

class PaperScore extends Music{
    public PaperScore(float purchasePrice, String newOrUsed, int dayArrived, String condition, String band, String album) {
        super("PaperScore", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition, band, album);
    }
}

class CD extends Music{
    public CD(float purchasePrice, String newOrUsed, int dayArrived, String condition, String band, String album) {
        super("CD", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition, band, album);
    }
}

class Vinyl extends Music{
    public Vinyl(float purchasePrice, String newOrUsed, int dayArrived, String condition, String band, String album) {
        super("Vinyl", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition, band, album);
    }
}

class Cassette extends Music{
    public Cassette(float purchasePrice, String newOrUsed, int dayArrived, String condition, String band, String album) {
        super("Cassette", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition, band, album);
    }
}

abstract class Players extends Item{
    private boolean equalized;

    public Players(String name, float purchasePrice, float listPrice, String newOrUsed, int dayArrived, String condition) {
        super(name, purchasePrice, listPrice, newOrUsed, dayArrived, condition);
        this.equalized = false;
    }

    public boolean isEqualized() {
        return equalized;
    }

    public void setEqualized(boolean equalized) {
        this.equalized = equalized;
    }
}

class RecordPlayer extends Players{
    public RecordPlayer(float purchasePrice, String newOrUsed, int dayArrived, String condition) {
        super("RecordPlayer", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
    }
}

class CDPlayer extends Players{
    public CDPlayer(float purchasePrice, String newOrUsed, int dayArrived, String condition) {
        super("CDPlayer", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
    }
}

class MP3Player extends Players{
    public MP3Player(float purchasePrice, String newOrUsed, int dayArrived, String condition) {
        super("MP3Player", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
    }
}

class CassettePlayer extends Players{
    public CassettePlayer(float purchasePrice, String newOrUsed, int dayArrived, String condition) {
        super("CassettePlayer", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
    }
}

abstract class Instruments extends Item{
    public Instruments(String name, float purchasePrice, float listPrice, String newOrUsed, int dayArrived, String condition) {
        super(name, purchasePrice, listPrice, newOrUsed, dayArrived, condition);
    }

	
	public float setPrizes(float prize) {
		// TODO Auto-generated method stub
		return 0;
	}
}

class Stringed extends Instruments{
    private boolean electric;
    private boolean tuned;

    public Stringed(String name, float purchasePrice, float listPrice, String newOrUsed, int dayArrived, String condition, boolean electric) {
        super(name, purchasePrice, listPrice, newOrUsed, dayArrived, condition);
        this.electric = electric;
        this.tuned = false;
    }

    public boolean isElectric() {
        return electric;
    }

    public void setElectric(boolean electric) {
        this.electric = electric;
    }

    public boolean isTuned() {
        return tuned;
    }

    public void setTuned(boolean tuned) {
        this.tuned = tuned;
    }

	public float setPrizes(float prize) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Item> additionalbought() {
		// TODO Auto-generated method stub
		return null;
	}
}

class Guitar extends Stringed{
    public Guitar(float purchasePrice, String newOrUsed, int dayArrived, String condition, boolean electric) {
        super("Guitar", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition, electric);
    }
}

class Bass extends Stringed{
    public Bass(float purchasePrice, String newOrUsed, int dayArrived, String condition, boolean electric) {
        super("Bass", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition, electric);
    }
}

class Mandolin extends Stringed{
    public Mandolin(float purchasePrice, String newOrUsed, int dayArrived, String condition, boolean electric) {
        super("Mandolin", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition, electric);
    }
}

class Wind extends Instruments{
    private boolean adjusted;

    public Wind(String name, float purchasePrice, float listPrice, String newOrUsed, int dayArrived, String condition) {
        super(name, purchasePrice, listPrice, newOrUsed, dayArrived, condition);
        this.adjusted = false;
    }

    public boolean isAdjusted() {
        return adjusted;
    }

    public void setAdjusted(boolean adjusted) {
        this.adjusted = adjusted;
    }
}

class Flute extends Wind{
    private String type;

    public Flute(float purchasePrice, String newOrUsed, int dayArrived, String condition, String type) {
        super("Flute", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
        this.type = type;
    }

    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }
}

class Harmonica extends Wind{
    private String key;

    public Harmonica(float purchasePrice, String newOrUsed, int dayArrived, String condition, String key) {
        super("Harmonica", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
        this.key = key;
    }

    public String getKey () {
        return key;
    }

    public void setKey (String key) {
        this.key = key;
    }
}

class Saxophone extends Wind{
    private String type;

    public Saxophone(float purchasePrice, String newOrUsed, int dayArrived, String condition, String type) {
        super("Saxophone", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

abstract class Clothing extends Item{
    public Clothing(String name, float purchasePrice, float listPrice, String newOrUsed, int dayArrived, String condition) {
        super(name, purchasePrice, listPrice, newOrUsed, dayArrived, condition);
    }
}

class Hat extends Clothing{
    private String hatSize;

    public Hat(float purchasePrice, String newOrUsed, int dayArrived, String condition, String hatSize) {
        super("Hat", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
        this.hatSize = hatSize;
    }

    public String getHatSize() {
        return hatSize;
    }

    public void setHatSize(String hatSize) {
        this.hatSize = hatSize;
    }
}

class Shirt extends Clothing{
    private String shirtSize;

    public Shirt(float purchasePrice, String newOrUsed, int dayArrived, String condition, String shirtSize) {
        super("Shirt", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
        this.shirtSize = shirtSize;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }
}

class Bandana extends Clothing{
    public Bandana(float purchasePrice, String newOrUsed, int dayArrived, String condition) {
        super("Bandana", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
    }
}

abstract class Accessories extends Item{
    public Accessories(String name, float purchasePrice, float listPrice, String newOrUsed, int dayArrived, String condition) {
        super(name, purchasePrice, listPrice, newOrUsed, dayArrived, condition);
    }
}

class PracticeAmps extends Accessories{
    private String wattage;

    public PracticeAmps(float purchasePrice, String newOrUsed, int dayArrived, String condition, String wattage) {
        super("PracticeAmps", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
        this.wattage = wattage;
    }

    public String getWattage() {
        return wattage;
    }

    public void setWattage(String wattage) {
        this.wattage = wattage;
    }
}

class Cables extends Accessories{
    private Integer length;

    public Cables(float purchasePrice, String newOrUsed, int dayArrived, String condition, Integer length) {
        super("Cables", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}

class Strings extends Accessories{
    private String type;

    public Strings(float purchasePrice, String newOrUsed, int dayArrived, String condition, String type) {
        super("Strings", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class GigBag extends Accessories{
    public GigBag(float purchasePrice, String newOrUsed, int dayArrived, String condition) {
        super("GigBag", purchasePrice, purchasePrice * 2, newOrUsed, dayArrived, condition);
    }

	public void add(GigBag gigs) {
		// TODO Auto-generated method stub
		
	}
}




