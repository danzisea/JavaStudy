package youdao;

import java.lang.ref.WeakReference;

class Person{
private String mName;
public Person(String name){
  this.mName = name;
}
public String getName(){
  return this.mName;
}
}
public class WeakReferenceTest {
public static void check(Person person){
  if( person == null){
   System.out.println("Reference invalid");
  }
  else {
   System.out.println("Reference still available");
  }
}
public static void main(String[] args) {
  Person jerry = new Person("Jerry");
  WeakReference<Person> person = new WeakReference<Person>(jerry);
  jerry = null;
  // if you comment out this line, Reference will be available
  System.gc();
  Person restore = person.get();
  check(restore);
}
}
