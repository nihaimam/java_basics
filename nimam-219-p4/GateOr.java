import java.util.*;

////this gate is answering the question, "are any of my inputs HI?"
public class GateOr extends Gate{
  
  //constructor
  public GateOr(List<Wire> ins, Wire out) throws ExceptionLogicParameters{
    super("OR", ins, out);
  }
  
  //if at least one HI, the output is HI
  //if all the inputs are LO, the output is LO    
  //if an X input makes it unclear, the output is X
  @Override public boolean propagate(){
    boolean isX = false;
    Signal outSignal = getOutput().getSignal();
    for (Wire wire : getInputs()){
      if (wire.getSignal() == Signal.HI){
        getOutput().setSignal(Signal.HI);
        if (outSignal != Signal.HI){
          return true;
        }
        else {
          return false;
        }
      }
      if (wire.getSignal() == Signal.X){
        isX = true;
      }
    }
    if (isX){
      getOutput().setSignal(Signal.X);
      if (outSignal != Signal.X){
        return true;
      } 
      else {
        return false;
      }
    } 
    else {
      getOutput().setSignal(Signal.LO);
      if (outSignal != Signal.LO){
        return true;
      }
      else {
        return false;
      }
    }
  }
  
  //ensures that the inputs, output, and name are the same values
  @Override public boolean equals(Object obj){
    if (obj instanceof GateOr){
      GateOr someGate = (GateOr) obj;
      return (this.getInputs().equals(someGate.getInputs())) && (this.getOutput().equals(someGate.getOutput()));
    }
    else { 
      return false;
    }
  }
  
}