// Interface aid numunedir

interface Animal {
  public void animalSound(); 
  public void sleep(); 
}

class Pig implements Animal {
  public void animalSound() {
    System.out.println("The pig says: wee wee");
  }
  public void sleep() {
    System.out.println("Zzz");
  }
}

class Main {
  public static void main(String[] args) {
    Pig myPig = new Pig();
    myPig.animalSound();
    myPig.sleep();
  }
}


// Encapsulation dese bunu yaz aq
public class Person {
   private String name;

   // Getter
   public String getName() {
     return name;
   }

   // Setter
   public void setName(String newName) {
     this.name = newName;
   }
}
public class Main {
  public static void main(String[] args) {
    Person myObj = new Person();
    myObj.setName("John");
    System.out.println(myObj.getName());
  }
}


// Polymorphism aid numuneeee

class Animal {
  public void animalSound() {
    System.out.println("The animal makes a sound");
  }
}

class Pig extends Animal {
  public void animalSound() {
    System.out.println("The pig says: wee wee");
  }
}

class Dog extends Animal {
  public void animalSound() {
    System.out.println("The dog says: bow wow");
  }
}

class Main {
  public static void main(String[] args) {
    Animal myAnimal = new Animal();
    Animal myPig = new Pig();
    Animal myDog = new Dog();
        
    myAnimal.animalSound();
    myPig.animalSound();
    myDog.animalSound();
  }
}

// objectlere aid numune budur

public class Main {
  int x = 5;

  public static void main(String[] args) {
    Main myObj = new Main();
    System.out.println(myObj.x);
  }
}



// Abstraction haqqinda numune 


abstract class Animal {
  
  public abstract void animalSound();
  
  public void sleep() {
    System.out.println("Zzz");
  }
}


class Pig extends Animal {
  public void animalSound() {
    
    System.out.println("The pig says: wee wee");
  }
}

class Main {
  public static void main(String[] args) {
    Pig myPig = new Pig(); 
    myPig.animalSound();
    myPig.sleep();
  }
}

// Constructors aid numune

public class Main {
  int x;
  public Main() {
    x = 5;
  }
  public static void main(String[] args) {
    Main myObj = new Main();
    System.out.println(myObj.x);
  }
}

// Overriding vs Overloading

// 1ci Overloading metod 

public class Test{
public static int func(int a ){
       return 100;
}
public static char func(int a , int b){
      return "edureka";
}
public static void main(String args[]){
System.out.println(func(1));
System.out.println(func(1,3));
}
}
// 2ci overriding metod
class Parent{
void view(){
System.out.println("this is a parent class method);
}}
class Child extends Parent{
void view(){
System.out.println("this is a child class method);
}}
public static void main(String args[]){
Parent ob = new Parent();
ob.view();
Parent ob1 = new Child();
ob1.view();

// Primitive  Type

public static void main(String[] args){
    int i= 23;
    char c='d';
}

// referans type

public static void main(String[] args)(
    String str = "sw iwjofhnien";
)

// ArrayList numunesi

import java.util.ArrayList;

public class Main { 
  public static void main(String[] args) { 
    ArrayList<String> cars = new ArrayList<String>();
    cars.add("Volvo");
    cars.add("BMW");
    cars.add("Ford");
    cars.add("Mazda");
    System.out.println(cars);
  } 
}

// "pass-by-reference"
public static void main(String[] args) {
    Dog aDog = new Dog("Max");
    Dog oldDog = aDog;

    foo(aDog);
    aDog.getName().equals("Fifi"); 
    
    aDog == oldDog; 
}

public static void foo(Dog d) {
    d.getName().equals("Max");
    d.setName("Fifi");
}


// Java static METOD

public class CrewMember {

    public static void introduce() {
        System.out.println("Hi, my name is John.");
    }

    public static void describe() {
        System.out.println("I am a production assistant.");
    }
}

// Exceptions - Try...Catch numuneleri

public class Main {
  public static void main(String[] args) {
    try {
      int[] myNumbers = {1, 2, 3};
      System.out.println(myNumbers[10]);
    } catch (Exception e) {
      System.out.println("Something went wrong.");
    }
  }
}

// collections 
import java.io.*; 
import java.util.*; 
  
class CollectionDemo { 
  
    public static void main(String[] args) 
    { 
        int arr[] = new int[] { 1, 2, 3, 4 }; 
        Vector<Integer> v = new Vector(); 
        Hashtable<Integer, String> h = new Hashtable(); 
  
        v.addElement(1); 
        v.addElement(2); 
  
        h.put(1, "geeks"); 
        h.put(2, "4geeks"); 
  
        System.out.println(arr[0]); 
        System.out.println(v.elementAt(0)); 
        System.out.println(h.get(1)); 

    } 
}  

