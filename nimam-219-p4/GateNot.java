import java.util.*;

//GateNot inverts its only input
public class GateNot extends Gate{
  
  //fields
  Wire in;
  Wire out;
  
  //constructor
  //uses helper method from gate so that the super(...) can be the first thing
  public GateNot(Wire in, Wire out) throws ExceptionLogicParameters{
    super("NOT", Gate.one(in), out);
    this.in = in;
    this.out = out;
  }
  
  //this gate accepts exactly one signal, and will invert() the value
  @Override public boolean propagate() {
    Signal outSignal = out.getSignal();
    out.setSignal(in.getSignal().invert());
    return outSignal != out.getSignal();
  }
  
  //ensures that the inputs, output, and name are the same values
  @Override public boolean equals(Object obj){
    if (obj instanceof GateNot){
      GateNot someGate = (GateNot) obj;
      return (this.in.equals(someGate.in)) && (this.out.equals(someGate.out));
    }
    else { 
      return false;
    }
  }
  
}