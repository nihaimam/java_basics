import java.util.*;

//this gate is answering the question, "are any of my inputs LO?"
//a NAND gate is logically equivalent to an AND gate followed by a NOT gate
public class GateNand extends Gate{
  
  //constructor
  public GateNand(List<Wire> ins, Wire out) throws ExceptionLogicParameters{
    super("NAND", ins, out);
  }
  
  //if any signals are known and are LO, the output is HI
  //if all signals are HI, the output must be LO
  //if the inputs are a combination of HI and some X (but no LO), the output is still unknown: X
  @Override public boolean propagate(){
    boolean isX = false;
    Signal outSignal = getOutput().getSignal();
    for (Wire wire : getInputs()){
      if (wire.getSignal() == Signal.LO){
        getOutput().setSignal(Signal.HI);
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
      getOutput().setSignal(Signal.LO);
    }
    return outSignal != getOutput().getSignal();
  }
  
  //ensures that the inputs, output, and name are the same values
  @Override public boolean equals(Object obj){
    if (obj instanceof GateNand){
      GateNand someGate = (GateNand) obj;
      return (this.getInputs().equals(someGate.getInputs())) && (this.getOutput().equals(someGate.getOutput()));
    }
    else { 
      return false;
    }
  }
  
}