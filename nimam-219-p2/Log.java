public class Log{
  
  //a string of message
  public String message;
  
  //creates a new and empty log
  public Log(){
  }
  
  //creates a copy of this the log and returns it
  public Log copy(){
    Log copy = new Log();
    return copy;
  }
  
  //returns an array of strings where each item in the array
  //is the next line of recorded content
  public String[] read(){
    String[] s = new String[1];
    s[0] = message;
    System.out.print(s);
    return s;
  }
  
  //records the given message as the next line in the log file
  //assume there are no newline characters in the message
    public void record(String msg){
    message = message + msg + "\n";
  }
  
}
  


   
