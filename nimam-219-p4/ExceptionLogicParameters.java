//these will be thrown whenever you don't have enough,
//or have too many, parameters for a Gate or Circuit.
public class ExceptionLogicParameters extends RuntimeException{
  
  //fields
  private boolean inputsRelated;  //when true, indicates this parameters issue was some Logic structure
  private int expected, found;    //how many were expected, and how many were found?
  
  //constructor
  public ExceptionLogicParameters(boolean inputsRelated, int expected, int found){
    this.inputsRelated = inputsRelated;
    this.expected = expected;
    this.found = found;
  }
  
  //build up a message of your choosing
  @Override public String toString(){
    return "Logic Parameter Exception";
  }
  
  //getters and setters for private fields
  public boolean getInputsRelated(){
    return inputsRelated;
  }
  
  public int getExpected(){
    return expected;
  }
  
  public int getFound(){
    return found;
  }
  
  public void setInputsRelated(boolean inputsRelated){
    this.inputsRelated = inputsRelated;
  }
  
  public void setExpected(int expected){
    this.expected = expected;
  }
  
  public void setFound(int found){
    this.found = found;
  } 
  
}