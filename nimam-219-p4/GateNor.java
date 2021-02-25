import java.util.*;

//this gate is answering the question, "are all my inputs LO?"
//a NOR gate is logically equivalent to an OR gate followed by a NOT gate
public class GateNor extends Gate{
  
  //constructor
  public GateNor(List<Wire> ins, Wire out) throws ExceptionLogicParameters{
    super("NOR", ins, out);
  }
  
  //if any input is HI then output is LO)
  //if all inputs are LO, then output is HI
  //if the inputs are a combination of LO and X then output is X
  @Override public boolean propagate() throws ExceptionLogicParameters{
    boolean isX = false;
    Signal outSignal = getOutput().getSignal();
    for (Wire wire : getInputs()){
      if (wire.getSignal() == Signal.HI){
        getOutput().setSignal(Signal.LO);
        return outSignal != getOutput().getSignal();
      }
      if (wire.getSignal() == Signal.X){
        isX = true;
      }
    }
    if (isX){
      getOutput().setSignal(Signal.X);
    } 
    else {
      getOutput().setSignal(Signal.HI);
    }
    return outSignal != getOutput().getSignal();
  }
  
  //ensures that the inputs, output, and name are the same values
  @Override public boolean equals(Object obj){
    if (obj instanceof GateNor){
      GateNor someGate = (GateNor) obj;
      return (this.getInputs().equals(someGate.getInputs())) && (this.getOutput().equals(someGate.getOutput()));
    }
    else { 
      return false;
    }
  }
  
}