import java.util.*;
import java.io.*;

public class GateSim{
  
  //Given a circuit name as args[0] and a string of inputs as args[1]
  //this will look inside the samples/ folder for the corresponding
  //circuit description open the file and create the circuit, feed it
  //the inputs described in args[1] propagate all signals across the
  //circuit, and then print the outputs as a single string on a line by
  //itself
  //Signal.fromString and Signal.toString(List<Signal>) may be useful
  //when any exception was thrown during the activities described above
  //you should catch any and all exceptions, and print "couldn't perform simulation"
  public static void main(String[] args) throws FileNotFoundException{
    if (args.length == 0){
      throw new RuntimeException("couldnt perform simulation");
    }
    String name = args[0];
    String inputs = args[1];
    Scanner sc = new Scanner(new File("samples/" + name + ".txt"));
    System.out.println(Signal.fromString(args[1]));
  }
  
}



    