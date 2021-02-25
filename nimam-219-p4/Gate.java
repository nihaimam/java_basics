import java.util.*;
import java.io.*;

//gates are the smallest units of logic
//though Gate is abstract, each of its concrete child classes represents a specific kind of logic, such as AND, OR, or NOT
//the child classes are only responsible for overriding the propagate() method inherited all the way from Logic
public abstract class Gate implements Logic {
  
  //fields
  private String name;          //name of this kind of gate in all caps, such as "AND", "OR", etc
  private List<Wire> inputs;    //list of (one or more) input wires.
  private Wire output;          //solitary output wire
  
  //constructor
  //if ins is an empty list an ExceptionLogicParameters will be raised
  public Gate(String name, List<Wire> ins, Wire out){
    this.name = name;
    this.output = out;
    if (ins.size() == 0 || ins.isEmpty()){
      throw new ExceptionLogicParameters(false, 1, 0);
    } else {
      this.inputs = ins;
    }
  }
  
  //feed these signals to the input wires
  //if the wrong number of signals has been provided an ExceptionLogicParameters will be raised
  @Override public void feed(List<Signal> inSigs){
    if(inSigs.size() != inputs.size()){
      throw new ExceptionLogicParameters(false, inputs.size(), inSigs.size());
    }
    else {
      for (int i = 0; i < inSigs.size(); i++){
        inputs.get(i).setSignal(inSigs.get(i));
      }
    }
  }
  
  //same notion as feed above, but obtains the Signal values out of this string via Signal.fromString
  @Override public void feed(String signalsStr){
    if (signalsStr.length() != inputs.size()){
      throw new ExceptionLogicParameters(false, inputs.size(),signalsStr.length());
    }
    else {
      for (int i = 0; i < signalsStr.length(); i++){
        Signal signalValue = Signal.fromString(signalsStr.charAt(i));
        inputs.get(i).setSignal(signalValue);
      }
    }
  }
  
  //propagate method, we got from the interface logic, has been left abstract
  //since this is an abstract class wel will push it down to the child classes
  
  //read the single signal value from the output wire, and return it as a single value in a List
  @Override public List<Signal> read(){
    List<Signal> signals = new ArrayList<>();
    signals.add(output.getSignal());
    return signals;
  }
  
  //this combines the Logic methods into a convenient single call
  //feed the given inSigs, propagate them through, and return the results of read
  @Override public List<Signal> inspect(List<Signal> inSigs) throws ExceptionLogicParameters{
    feed(inSigs);
    propagate();
    return read();
  }
  
  //similar to the previous inspect() method but pass a String of signals in rather than a list
  @Override public String inspect(String inStr) throws ExceptionLogicParameters{
    return Signal.toString(inspect(Signal.fromString(inStr)));
  }
  
  //return a string containing the name, then use the toString method to include the inputs and output values
  //example -> "AND( [A:X, B:X] | carry:X )"
  @Override public String toString(){
    return this.name + "( " + inputs.toString() + " | " + output.toString() + " )";
  }
  
  //ensures that the inputs, output, and name are the same values
  @Override public boolean equals(Object other){
    if (other instanceof Gate){
      Gate someGate = (Gate) other;
        return (this.getInputs().equals(someGate.getInputs())) && (this.getOutput().equals(someGate.getOutput()))
          && (this.getName().equals(someGate.getName()));
    }
    else { 
      return false;
    }
  }
  
  // Helper method to create an ArrayList of one thing so that Java's
  //stupid "super() must be first line" rule can be honored
  public static <T> ArrayList<T> one(T x){
    ArrayList<T> a = new ArrayList<T>();
    a.add(x);
    return a;
  }
  
  ////getters and setters for private fields
  public List<Wire>getInputs(){
    return this.inputs;
  }
  
  public Wire getOutput(){
    return this.output;
  }
  
  public String getName(){
    return this.name;
  }
  
  public void setInputs(List<Wire> inputs){
    this.inputs = inputs;
  }
  
  public void setOutput(Wire output){
    this.output = output;
  }
  
  public void setName(String name){
    this.name = name;
  }
  
}