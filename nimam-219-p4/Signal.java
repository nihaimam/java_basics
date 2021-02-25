import java.util.*;

//signals represent the value on a wire
public enum Signal{
  
  //signals can be high (HI), low (LO), or unknown (X)
  HI('1'), LO('0'), X('X');
  
  //value of the signal
  private int val;
  
  //constructor
  Signal(int val) {
    this.val = val;
  }
  
  //returns the inversion of this signal
  //HI and LO return each other, and X returns itself
  public Signal invert(){
    switch(this){
      case HI: return LO;
      case LO: return HI;
      case X: return X;
      default: throw new RuntimeException("uh oh");
    }
  }
  
  //returns a Signal representation from the given char
  // HI = '1'   LO = '0'    X = 'X'    X = 'x'
  //any other characters will raise an ExceptionLogicMalformedSignal
  public static Signal fromString(char c){
    switch(c){
      case '1': return HI;
      case '0': return LO;
      case 'X': return X;
      case 'x': return X;
      default: throw new ExceptionLogicMalformedSignal(c,"nope from signal!");
    }
  }
  
  //return the List of Signal values found in the input string
  //characters other than "01xX \t" will raise an ExceptionLogicMalformedSignal
  public static List<Signal> fromString(String inps){
    List<Signal> sigs = new ArrayList<Signal>();
    for (int i = 0; i < inps.length(); i++){
      if (inps.charAt(i) == ' ' || inps.charAt(i) == '\t'){
        i++;
      }
      if (inps.charAt(i) != ' ' && inps.charAt(i) != '\t'){
        sigs.add(fromString(inps.charAt(i)));
      }
    }
    return sigs;
  }
  
  //returns a Signal representation from the given char
  // HI = "1"   LO = "0"    X = "X"    X = "x"
  //any other characters will raise an Exception
  @Override public String toString(){
    switch(this){
      case HI: return "1";
      case LO: return "0";
      case X: return "X";
      default: throw new RuntimeException("uh oh");
    }
  }
  
  //converts each signal in the List via toString
  //combine them into a single string, and returns it
  public static String toString(List<Signal> sig){
    String str = "";
    for (int i = 0; i < sig.size(); i++){
      str += (sig.get(i)).toString();
    }
    return str;
  }
  
}