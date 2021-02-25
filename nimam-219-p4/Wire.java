import java.util.*;

//wires represent the connectors between different gates and circuits
public class Wire{
  
  //fields
  private Signal signal;
  private String name;
  
  //constructor
  //uses X as the starting Signal value
  public Wire(String name){
    this.name = name;
    this.signal = Signal.X;
  }
  
  //create and return the string of name, colon, signal value
  //use the toString method of enum signal
  @Override public String toString(){
    String str = name + ":" + signal;
    return str;
  }
  
  //ensures that the name and signal both match
  @Override public boolean equals(Object other){
    if (other instanceof Wire){
      Wire someWire = (Wire) other;
        return (this.getSignal().equals(someWire.getSignal())) && (this.getName().equals(someWire.getName()));
    }
    else { 
      return false;
    }
  }
  
  
  //getters and setters for private fields
  public Signal getSignal(){
    return signal;
  }
  
  public String getName(){
    return name;
  }
  
  public void setSignal(Signal signal){
    this.signal = signal;
  }
  
  public void setName (String name){
    this.name = name;
  }
  
}