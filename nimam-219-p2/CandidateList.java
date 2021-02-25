public class CandidateList{
  
  //instance variable
  public String[] candidates;
  
  //initialize the instance variable to size zero
  public CandidateList(){
    candidates = new String[0];
  }
  
  //initialize the internal array with the given array
  public CandidateList(String[] candidates){
    this.candidates = candidates;
  }
  
  //creates a different CandidateList object - no aliases
  public CandidateList copy(){
    CandidateList copy = new CandidateList(candidates);
    return copy;
  }
  
  //how many items are currently in the list
  public int size(){
    int size = 0;
    for (int i = 0; i < candidates.length; i++){
      size++;
    }
    return size;
  }
  
  //return the value at the provided index
  //throws an exception when index is out of bounds
  public String get(int index){
    if (index > candidates.length){
      throw new RuntimeException("Array Index Out Of Bounds!");
    }
    else {
      return candidates[index];
    }
  }
  
  //change the value at the given index to the given sting value
  //throw an exception when index is out of bounds
  public void set(int index, String candidate){
    if (index > candidates.length){
      throw new RuntimeException("Array Index Out Of Bounds!");
    }
    else {
      candidates[index] = candidate;
    }
  }
  
  //find the first occurrence and returns the index value
  //returns -1 when the value is not found - no exception
  public int indexOf(String candidate){
    int index = -1;
    for (int i = 0; i < candidates.length; i++){
      if (candidates[i].equals(candidate)){
        index = i;
        break;
      }
    }
    return index;
  }
  
  //add a new value tacked onto the end of the internal array
  //throws an exception if the given string is null
  public void add(String candidate){
    if (candidate == null){
      throw new RuntimeException("String is null");
    }
    int len = candidates.length + 1;
    String[] str = new String[len];
    for (int i = 0; i < candidates.length; i++){
      str[i] = candidates[i];
    }
    str[len-1] = candidate;
    candidates = str;
  }
  
  //insert the candidate is at new index value and push
  //all the values been pushed to the right by one
  //throws an exception when the candidate is null
  //throws an exception when index is out of bounds
  public void addAt(int index, String candidate){
    if (candidate == null){
      throw new RuntimeException("String is null");
    }
    if (index > (candidates.length + 1)){
      throw new RuntimeException("Array Index Out Of Bounds!");
    }
    int len = candidates.length + 1;
    String[] str = new String[len];
    for (int i = 0; i < index; i++){
      str[i] = candidates[i];
    }
    str[index] = candidate;
    for (int i = index + 1; i < str.length; i++){
      str[i] = candidates[i - 1];
    }
    candidates = str;
  }
  
  //removes the value at the index being removed and
  //all values after it shift one spot to the left
  //returns the value that was removed
  //throws an exception when index is out of bounds
  public String removeAt(int index){
    if (index > (candidates.length + 1)){
      throw new RuntimeException("Array Index Out Of Bounds!");
    }
    int len = candidates.length - 1;
    String[] str = new String[len];
    for (int i = 0; i < index; i++){
      str[i] = candidates[i];
    }
    String s = candidates[index];
    for (int i = index + 1; i < candidates.length; i++){
      str[i - 1] = candidates[i];
    }
    candidates = str;
    return s;
  }
  
  //If candidates were named "A", "B", and "C",
  //then the string would return "{A, B, C}"
  @Override public String toString(){
    String n = "{";
    for (int i = 0; i < candidates.length; i++){
      n = n + candidates[i] + ", ";
      if (i == candidates.length - 1){
        n = n + candidates[i];
      }
    }
    return n + "}";
  }
}