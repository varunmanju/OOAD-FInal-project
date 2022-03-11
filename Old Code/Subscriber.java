package abhi.ooad;

// import java.util.*;

enum subType{
    TRACKER, LOGGER
}

abstract class Subscriber{
    subType type;

    public void update(String clerk, eventType e, Integer data){
        System.out.println("you are so `````````````````````````````DUMB````````````````````````");
    }
    public void update(String clerk, String info, String data){

    }
}