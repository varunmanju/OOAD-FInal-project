package abhi.ooad;

public abstract class Item implements Logger {
    String name;            // I didn't implement a naming scheme - mostly ignoring this - how would you?
    double purchasePrice;   // $1 to $50
    double listPrice;       // purchasePrice x 2
    boolean isNew;          // set by constructor randomly
    int dayArriving;        // 0 at initialization, otherwise set at delivery
    Condition condition;    // set by constructor randomly
    double salePrice;       // set when sold
    int daySold;            // set when sold
    ItemType itemType;      // set by subclass constructors

    void damageAnItem(Item i) {
    }

    Item() {
        // common initialization of a new instance
        name = itemType.name;
        purchasePrice = Utility.rndFromRange(1,50);
        listPrice = 2 * purchasePrice;
        isNew = (Utility.rnd() > .5);  // coin flip for new or used
        dayArriving = 0;
        condition = Utility.randomEnum(Condition.class);
        salePrice = 0;
        daySold = 0;
    };
}

////////////////////////////////////////////////////////////
//Music class and subclasses
////////////////////////////////////////////////////////////
abstract class Music extends Item {
    String band;
    String album;
    String[] bands = {"Yes","Jethro Tull","Rush","Genesis","ELP","Enya"};
    String[] albums = {"Fragile","Stormwatch","2112","Abacab","Tarkus","The Memory of Trees"};
    Music() {
        super();
        band = bands[Utility.rndFromRange(0,bands.length-1)];
        album = albums[Utility.rndFromRange(0,albums.length-1)];
    }
}

class PaperScore extends Music {
    PaperScore() {
        super();
        itemType = ItemType.PAPERSCORE;
    }
}

class CD extends Music {
    CD() {
        super();
        itemType = ItemType.CD;
    }
}

class Vinyl extends Music {
    Vinyl() {
        super();
        itemType = ItemType.VINYL;
    }
}

class Cassette extends Music {
    Cassette() {
        super();
        itemType = ItemType.CASSETTE;
    }
}

////////////////////////////////////////////////////////////
//Players class and subclasses
////////////////////////////////////////////////////////////
abstract class Players extends Item {
    boolean equalized;
    Players(){
        super();
        equalized = false;
    }
}

class CDPlayer extends Players{
    public CDPlayer() {
        super();
        itemType = CDPLAYER;
    }
}

class MP3Player extends Players{
    public MP3Player() {
        super();
        itemType = MP3PLAYER;
    }
}

class CassettePlayer extends Players{
    public CassettePlayer() {
        super();
        itemType = CASSETTEPLAYER;
    }
}

class RecordPlayer extends Players{
    public RecordPlayer() {
        super();
        itemType = RECORDPLAYER;
    }
}

////////////////////////////////////////////////////////////
//Instrument class and subclasses
////////////////////////////////////////////////////////////
abstract class Instrument extends Item {
    public Instrument(){
        super();
    }
}

//Stringed classes and subclasses
abstract class Stringed extends Instrument {
    boolean isElectric;
    boolean isTuned;

    Stringed() {
        super();
        isElectric = (Utility.rnd()>.5); // coin flip for electric or acoustic
        isTuned = false;
    }
}

class Guitar extends Stringed {
    Guitar() {
        super();
        itemType = ItemType.GUITAR;
    }
}
class Bass extends Stringed {
    Bass() {
        super();
        itemType = ItemType.BASS;
    }
}
class Mandolin extends Stringed {
    Mandolin() {
        super();
        itemType = ItemType.MANDOLIN;
    }
}

//Wind classes and subclasses
abstract class Wind extends Instrument {
    Wind() {
        super();
    }
}

class Flute extends Wind {
    String type;
    String[] types = {"Piccolo","Alto","Bass","Tierce","Concert","Plastic"};
    
    Flute() {
        super();
        type = types[Utility.rndFromRange(0,types.length-1)];
        itemType = ItemType.FLUTE;
    }
}

class Harmonica extends Wind {
    String key;
    String keys[] = {"E","A","G","C","D"};
    Harmonica() {
        super();
        key = keys[Utility.rndFromRange(0,keys.length-1)];
        itemType = ItemType.HARMONICA;
    }
}

class Saxophone extends Wind {
    String type;
    String[] types = {"Sopranino","Soprano","Alto","C-Melody","Tenor","Baritone","Bass","Contrabase"};
    
    Saxophone() {
        super();
        type = types[Utility.rndFromRange(0,types.length-1)];
        itemType = ItemType.SAXOPHONE;
    }
}

////////////////////////////////////////////////////////////
//Clothing class and subclasses
////////////////////////////////////////////////////////////
abstract class Clothing extends Item {
    public Clothing(){
        super();
    }
}

class Hat extends Clothing{
    String hatSize;
    String[] sizes = {"Small", "Medium", "Large"};

    Hat(){
        super();
        hatSize = sizes[Utility.rndFromRange(0, sizes.length-1)];
        itemType = itemType.HAT;
    }
}

class Shirt extends Clothing{
    String shirtSize;
    String[] sizes = {"Small", "Medium", "Large"};

    Shirt(){
        super();
        hatSize = sizes[Utility.rndFromRange(0, sizes.length-1)];
        itemType = itemType.SHIRT;
    }
}

class Hat extends Clothing{
    String hatSize;
    String[] sizes = {"Small", "Medium", "Large"};

    Hat(){
        super();
        hatSize = sizes[Utility.rndFromRange(0, sizes.length-1)];
        itemType = itemType.HAT;
    }
}

class Bandana extends Clothing{
    Bandana(){
        super();
        itemType = itemType.BANDANA;
    }
}

////////////////////////////////////////////////////////////
//Accessories class and subclasses
////////////////////////////////////////////////////////////
abstract class Accessories extends Item {
    public Accessories(){
        super();
    }
}

class PracticeAmp extends Accessories{
    String wattage;
    String[] watOptions = {"100W", "160W", "200W", "500W"};

    PracticeAmp() {
        super();
        wattage = watOptions[Utility.rndFromRange(0, watOptions.length-1)];
        itemType = itemType.PRACTICEAMP;
    }
}

class Cable extends Accessories{
    private Integer length;
    String[] lenOptions = {"10", "20", "50", "100"};

    public Cable() {
        super();
        this.length = lenOptions[Utility.rndFromRange(0,lenOptions.length-1)];
        itemType = itemType.CABLE;
    }
}

class Strings extends Accessories{
    String type;
    String[] types = {"Electric","Acoustic","Plastic","Metal"};
    
    Strings() {
        super();
        type = types[Utility.rndFromRange(0,types.length-1)];
        itemType = ItemType.STRINGS;
    }
}

class GigBag extends Accessories{
    public GigBag() {
        super();
        itemType = itemType.GIGBAG;
    }
}
