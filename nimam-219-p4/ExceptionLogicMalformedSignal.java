//these exceptions will be thrown when encountered with a String
//value that includes anything other than the characters "10X"
public class ExceptionLogicMalformedSignal extends RuntimeException{
  
  //fields
  private char bad;    //the offending character
  private String msg;  //the entire message you want to convey
  
  //constructor
  public ExceptionLogicMalformedSignal(char bad, String msg){
    this.bad = bad;
    this.msg = msg;
  }
  
  //returns your nessage
  @Override public String toString(){
    return msg;
  }
  
  //getters and setters for private fields
  public char getBad(){
    return bad;
  }
  
  public String getMsg(){
    return msg;
  }
  
  public void setBad(char bad){
    this.bad = bad;
  }
  
  public void setMsg(String msg){
    this.msg = msg;
  }
  
}