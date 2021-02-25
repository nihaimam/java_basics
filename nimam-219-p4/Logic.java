import java.util.*;

//Interface Logic used in abstract class gate
public interface Logic{
  //Logic represents something that can accept inputs, performs some transformation, and
  //yields some outputs. Gates do this, Circuits also do this, and are more likely to have
  //multiple outputs. The functionality is just the sequence of feed, propagate, then read.
  
  //Since this is an interface, its methods are all abstract and not implemented here
  //We describe what eventual implementations will need to do
  
  //Assigns the given signals to the input wires of this thing. For gates, the input
  //wires themselves should be assigned these new Signal values, in the order given in
  //inSignals. For a circuit, these wires will all be attached to the Contact points found
  //in Circuit::inputs, in the order given (same indexes of both lists are used together).
  //In effect, you're zipping together the inSignals values with the inputs values, and
  //updating all the inputs' signal values with those given by inSignals.
  public abstract void feed(List<Signal> inSignals) throws ExceptionLogicParameters;
  
  //Same purpose as feed(List<Signal>). We accept a string that can be converted into a
  //List<Signal> value with help from Signal.fromString.
  public abstract void feed(String inSignals) throws ExceptionLogicParameters;
  
  //Using the current values of our input wires, let all inner components perform their logic
  //and generate their outputs. Be sure that a single call to propagate will make the entire
  //thing's outputs stabilize.
  //(The order in which you visit gates, or how often you (re)visit them might be significant).
  //returning a boolean : if any wires' signals were changed as a result of this propagation,
  //return true. If no changes occurred (if the circuit/gate is stable), return false.
  public abstract boolean propagate() throws ExceptionLogicParameters;
  
  //Read the signal values on the output wires, and return them as a List<Signal> value.
  public abstract List<Signal> read();
  
  //A combination of feeding, propagating, and reading. (these functionalities are all
  //declared here in this interface directly).
  public abstract List<Signal> inspect(List<Signal> inputs) throws ExceptionLogicParameters;
  
  //Also a combination of feeding, propagating, and reading. Should use the fromString
  //and toString functionality of the Signal type.
  public abstract String inspect(String inputs) throws ExceptionLogicParameters;

}