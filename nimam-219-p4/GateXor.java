import java.util.*;

//this gate is answering the question, "are exactly one of my inputs HI?"
public class GateXor extends Gate{
  
  //constructor
  public GateXor(List<Wire> ins, Wire out) throws ExceptionLogicParameters {
    super("XOR", ins, out);
  }
  
  //if exactly one of them is HI, then the output is HI
  //if there are too many HI signals, or zero HI signals, the output is LO
  //if any X values keep us from knowing how many signals are going to be HI or LO, the output is X
  @Override public boolean propagate(){
    int hiCount = 0;
    int xCount = 0;
    int loCount = 0;
    Signal outSignal = getOutput().getSignal();
    for (Wire w : getInputs()){
      if (w.getSignal() == Signal.HI){
        hiCount++;
      }
      if (w.getSignal() == Signal.X){
        xCount++;
      }
      if (w.getSignal() == Signal.LO){
        loCount++;
      }
    }
    if (xCount > 0){
      getOutput().setSignal(Signal.X);
      return outSignal != Signal.X;
    }
    if ((hiCount > loCount) && (hiCount-loCount) >= 2 || hiCount == 0){
      getOutput().setSignal(Signal.LO);
      return outSignal != Signal.LO;
    }
    if (xCount == 0){
      if (hiCount == 1){
        getOutput().setSignal(Signal.HI);
      }
      else {
        getOutput().setSignal(Signal.LO);
      }
      return outSignal != Signal.HI;
    }
    return true;
  }
  
  //ensures that the inputs, output, and name are the same values
  @Override public boolean equals(Object obj){
    if (obj instanceof GateXor){
      GateXor someGate = (GateXor) obj;
      return (this.getInputs().equals( someGate.getInputs())) && (this.getOutput().equals(someGate.getOutput()));
    }
    else { 
      return false;
    }
  }
  
}