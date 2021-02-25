import java.util.*;

public class Contact implements Logic{
  
  //fields
  public Wire in, out;
  public boolean inbound;  //true when this is a circuit's input, false otherwise
  
  //constructor
  public Contact(Wire in, Wire out, boolean inbound){
    this.in = in;
    this.out = out;
    this.inbound = inbound;
  }
  
  //this will indicate the wire names and the value currently on it
  //when the two wires of this Contact have the same name, you must construct a string
  //showing the name, a colon, and the signal value that the wires are currently sharing,
  //such as "A:0", "temp:X", "Cout:1"
  //when the two wires (in, out) have different names, you must show the two wire names,
  //and parenthesize the one that is inside the circuit then include a colon and the current signal value
  @Override public String toString(){
    if (in.getName().equals(out.getName())){
      return in.getName() + ":" + in.getSignal().toString();
    }
    if (inbound == true){
      return in.getName() + "(" + out.getName() + "):" + in.getSignal();
    }
    else {
      return "(" + in.getName() + ")" + out.getName() + ":" + in.getSignal();
    }
  }
  
  //ensures that the inputs, output, and name are the same values
  @Override public boolean equals(Object other){
    if (other instanceof Contact){
      Contact newContact = (Contact) other;
      return (this.getIn().equals(newContact.getIn())) && (this.getOut().equals(newContact.getOut()))
        && (this.getInbound() == (newContact.getInbound()));
    }
    else { 
      return false;
    }
  }
  
  //methods inherited from logic
  
  //feed these signals to the input wires
  //if the wrong number of signals has been provided an ExceptionLogicParameters will be raised
  @Override public void feed(List<Signal> inSignals)throws ExceptionLogicParameters{
    if (inSignals.size() > 1){
      throw new ExceptionLogicParameters(false, 1, inSignals.size());
    }
    else {
      for(int i = 0; i < inSignals.size(); i++){
        in.setSignal(inSignals.get(i));
      }
    }
  }
  
  //same notion as feed above, but obtains the Signal values out of this string via Signal.fromString
  @Override public void feed(String inSignals) throws ExceptionLogicParameters{
    if (inSignals.length() > 1){
      throw new ExceptionLogicParameters(false, 1, inSignals.length());
    }
    else {
      for(int i = 0; i < inSignals.length(); i++) {
        Signal signalValue = Signal.fromString(inSignals.charAt(i));
        in.setSignal(signalValue);;
      }
    }
  }
  
  //Using the current values of input wires, let all inner components perform their logic and generate their outputs
  //not used in contact
  @Override public boolean propagate() {
    return false;
  }
  
  //read the single signal value from the output wire, and return it as a single value in a List
  @Override public List<Signal> read(){
    List<Signal> signals = new ArrayList<>();
    signals.add(out.getSignal());
    return signals;
  }
  
  //this combines the Logic methods into a convenient single call
  //feed the given inSigs, propagate them through, and return the results of read
  @Override public List<Signal> inspect(List<Signal> inputs) throws ExceptionLogicParameters{
    feed(inputs);
    propagate();
    return read();
  }
  
  //similar to the previous inspect() method but pass a String of signals in rather than a list
  @Override public String inspect(String inputs) throws ExceptionLogicParameters{
    return Signal.toString(inspect(Signal.fromString(inputs)));
  }
  
  //getters and setters for private fields
  public Wire getIn(){
    return in;
  }
  
  public void setIn(Wire in){
    this.in = in;
  }
  
  public Wire getOut(){
    return out;
  }
  
  public void setOut(Wire out){
    this.out = out;
  }
  
  public boolean getInbound(){
    return inbound;
  }
  
  public void setInbound(boolean inbound){
    this.inbound = inbound;
  }
  
}