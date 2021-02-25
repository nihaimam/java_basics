import java.util.*;

//this gate is answering the question, "are all of my inputs HI?"
public class GateAnd extends Gate{
  
  //constructor
  public GateAnd(List<Wire> ins, Wire out) throws ExceptionLogicParameters{
    super("AND", ins, out);
  }
  
  //this gate will output HI when all of its inputs are HI.
  //if even a single value is LO, the answer is known to be LO
  //when X values make it unclear whether to output LO or HI, the output is X
  @Override public boolean propagate(){
    boolean isX = false;
    Signal outSignal = getOutput().getSignal();
    for (Wire wire : getInputs()){
      if (wire.getSignal() == Signal.LO){
        getOutput().setSignal(Signal.LO);
        if (outSignal != Signal.LO){
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
      getOutput().setSignal(Signal.HI);
      if (outSignal != Signal.HI){
        return true;
      }
      else {
        return false;
      }
    }
  }
  
  //ensures that the inputs, output, and name are the same values
  @Override public boolean equals(Object obj){
    if (obj instanceof GateAnd)
    {
      GateAnd someGate = (GateAnd) obj;
      return (this.getInputs().equals(someGate.getInputs())) && (this.getOutput().equals(someGate.getOutput()));
    }
    else { 
      return false;
    }
  }
  
}