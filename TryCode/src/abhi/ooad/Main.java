
/*
Demonstration of Object Oriented Concepts
Inheritance - Class CD inherits from Class Music
Polymorphism - We didn't need it with the current design. Will add in future requirements.
Abstraction - Class Item is abstract
Encapsulation - All the attributes in Class Item are private and
                public getter/setter methods have been provided to access the private attributes
Cohesion - All the Item related method are in the Item Class and are accessed in Store Class.
Identity - Lowest level classes in Item have been instantiated multiple times with a different id for each object
 */

package abhi.ooad;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the number of days for simulation run: ");
        int days = myObj.nextInt();
        Store.simulation(days);
        myObj.close();
    }
}

