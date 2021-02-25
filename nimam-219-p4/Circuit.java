import java.io.*;
import java.util.*;

//a Circuit is an example of a Logic structure
//it contains other Logic components that are all wired together
//it contains multiple Gate values wired together, but they are capable of wiring together entire other circuits too
public class Circuit implements Logic{
  
  //fields
  private List<Logic> components;         //the list of logical blocks that are wired together in this circuit
  private List<Contact> inputs, outputs;  //the connections to the outside world.
  private List<Wire> innerWires;          //a convenient place to store all known wires while constructing a circuit
  private List<String> importables;       // the names of circuits that were announced on the IMPORT line, if any
  private String name;                    // the circuit's name (part of the file name)
  
  //constructor that plugs in all arguments to the fields
  public Circuit (String circuitName, List<Logic> components, List<Contact> inputs,
                  List<Contact> outputs, List<Wire> innerWires, List<String> importables){
    this.name = circuitName;
    this.components = components;
    this.inputs = inputs;
    this.outputs = outputs;
    this.innerWires = innerWires;
    this.importables = importables;
  }
  
  //involved constructor. 
  //open the file indicated (assume it's in the folder named samples/ with a .txt extension),
  //parse its contents, and initialize all fields  accordingly.
  public Circuit(String circuitName) throws IOException{
    Scanner input = getCircuitScanner(circuitName);
    String line = input.nextLine();//first line
    if (line.toUpperCase().contains("IMPORT")){
      parseImportLine(input.nextLine()); //first line 
      line = input.nextLine();//blank line
    }
    parseContactsLine(input.nextLine());
    line = input.nextLine();//blank line
    while (input.hasNext()){
      line = input.nextLine();
      parseComponentLine(line);
    }
  }
  
  //helper for the Constructor
  //this converts something like "halfadder" into the filename "samples/halfadder.txt"
  //, opens the file, creates a Scanner attached to that file, and returns the Scanner
  public Scanner getCircuitScanner(String circuitName) throws IOException {
    return new Scanner(new File("samples/" + circuitName + ".txt")); 
  }
  
  
  //helper for the Constructor
  //given the entire line from the file that definitely contains the IMPORT keyword and one or more
  //circuit names after it (all separated by single spaces), you must update the importables field.
  public void parseImportLine(String line){
    String[] words = line.split(" ");
    for (int x = 1; x < words.length; x++){
      importables.add(words[x]);
    }
  }
  
  //helper for the Constructor
  //given the entire line from the file that names the input and output wires, create those new wires
  //and add them to innerWires; for each one, create a Contact and appropriately add it to inputs or outputs.
  //circuit names after it (all separated by single spaces), you must update the importables field.
  public void parseContactsLine(String line){
    String[] inputWireNames = line.split("->")[0].split(" ");
    String[] outputWireNames = line.split("->")[1].split(" ");
    for (String inputWireName : inputWireNames){
      Wire inputWire = new Wire(inputWireName);
      innerWires.add(inputWire);
      inputs.add(new Contact(inputWire, inputWire, true));
    }
    for (String outputWireName : outputWireNames){
      Wire outputWire = new Wire(outputWireName);
      innerWires.add(outputWire);
      outputs.add(new Contact(outputWire, outputWire, false));
    }
  }
  
  //helper for the Constructor and the parseComponent Line
  //useful for finding a wire anywhere inside your circuit
  //given the string argument, find the one inner wire of this circuit that goes by that name.
  //(Hint, you won't need to look inside of any sub-circuits here). if you don't find it, you must return null.
  public Wire findWire(String name){
    for (Wire wire : innerWires){
      if (wire.getName().equals(name)){
        return wire;
      }
    }
    return null;
  }
  
  //helper for the Constructor
  //given complete lists of Wire references, completely replace the outer wires of all your Contacts (found in inputs and outputs)
  //you're replacing the wires that are found on the outside of the circuit, so that's not automatically the out wire of the Components!
  //if the wrong number of wires were given for either inputs or outputs, you must raise an ExceptionLogicParameters value.
  public void hookUp(List<Wire> inWires, List<Wire> outWires) throws ExceptionLogicParameters{
    if (inWires.size() != inputs.size()){
      throw new ExceptionLogicParameters(true, inputs.size(), inWires.size());
    }
    if (outWires.size() != outputs.size()){
      throw new ExceptionLogicParameters(false, outputs.size(), outWires.size());
    }
    for (int i = 0; i < outputs.size(); i++){
      outputs.get(i).setOut(outWires.get(i));
    }
    for (int i = 0; i < inputs.size(); i++){
      inputs.get(i).setIn(inWires.get(i));
    }
  }
  
  //helper for the Constructor
  //given the entire line of either a Gate or sub-circuit, create the Gate object or Circuit object, ensuring you re-use any known wires
  //(findWire to find them), hookUp sub-circuits to their specified wires, and add this new component to the components field.
  //if the wrong number of inputs were provided (hiding in the string), you must raise an ExceptionLogicParameters value.
  //if the circuit's file can't be found at samples/<circuitname>.txt, you must raise a plain old FileNotFoundException.
  public void parseComponentLine(String line) throws IOException{
    String[] array = line.split("->");
  }
  
  //Show the name, a colon, and the inputs, an arrow ->, and the outputs on the first line of the returned string.
  //Then on subsequent lines, represent each sub-circuit or gate, indenting all components' string representations by two spaces
  @Override  public String toString(){
    String str = name + ":";
    for (Contact input : inputs){
      str += input.toString() + ", ";
    }
    str += "->";
    for (Contact output : outputs){
      str += output.toString();
    }
    return str;
  }
  
  //helper to the override to string method
  //given a string that assumedly contains multiple lines
  //return a string that has added exactly two spaces at the front of each line that is in the string
  public static String indent(String s){
    String a = "  ";
    for (int i = 0; i < s.length(); i++){
      if (s.charAt(i)=='\n'){
        a = a  + "\n  ";
      }
      a = a + s.charAt(i);
    }
    return a;
  }
  
  //methods inherited from interface
  //feed these signals to the input wires
  //if the wrong number of signals has been provided an ExceptionLogicParameters will be raised
  @Override public void feed(List<Signal> inSignals)throws ExceptionLogicParameters{
    if(inSignals.size() != innerWires.size()){
      throw new ExceptionLogicParameters(false, innerWires.size(), inSignals.size());
    }
    else {
      for (int i = 0; i < inSignals.size(); i++){
        innerWires.get(i).setSignal(inSignals.get(i));
      }
    }
  }
  
  //same notion as feed above, but obtains the Signal values out of this string via Signal.fromString
  @Override public void feed(String inSignals) throws ExceptionLogicParameters{
    if (inSignals.length() != innerWires.size()){
      throw new ExceptionLogicParameters(false, innerWires.size(),inSignals.length());
    }
    else {
      for (int i = 0; i < inSignals.length(); i++){
        Signal signalValue = Signal.fromString(inSignals.charAt(i));
        innerWires.get(i).setSignal(signalValue);
      }
    }
  }
  
  //fully update all outputs of all components in this circuit so that all signal values are stable
  @Override public boolean propagate() {
    return false;
  }
  
  //read the single signal value from the output wire, and return it as a single value in a List
  @Override public List<Signal> read(){
    List<Signal> signals = new ArrayList<>();
    for (int i = 0; i < innerWires.size(); i++){
      signals.add(innerWires.get(i).getSignal());
    }
    return signals;
  }
  
  //this combines the Logic methods into a convenient single call
  //feed the given inSigs, propagate them through, and return the results of read
  @Override public List<Signal> inspect(List<Signal> inputs) throws ExceptionLogicParameters{
    feed(inputs);
    propagate();
    return read();
  }
  
  //similar to the previous inspect() method but pass a String of signals in rather than a list
  @Override public String inspect(String inputs) throws ExceptionLogicParameters{
    return Signal.toString(inspect(Signal.fromString(inputs)));
  }
  
  //getters and setters for private fields
  public void setComponents(List<Logic> components){
    this.components = components;
  }
  
  public List<Logic> getComponents(){
    return components;
  }
  
  public void setInputs(List<Contact> inputs){
    this.inputs = inputs;
  }
  
  public List<Contact> getInputs(){
    return inputs;
  }
  
  public void setOutputs(List<Contact> outputs){
    this.outputs = outputs;
  }
  
  public List<Contact> getOutputs(){
    return outputs;
  }
  
  public void setInnerWires(List<Wire> innerWires){
    this.innerWires = innerWires;
  }
  
  public List<Wire> getInnerWires(){
    return innerWires;
  }
  
  public void setImportables(List<String> importables){
    this.importables = importables;
  }
  
  public List<String> getImportables(){
    return importables;
  }
  
  public void setName(String name){
    this.name = name;
  }
  
  public String getName(){
    return name;
  }
  
}