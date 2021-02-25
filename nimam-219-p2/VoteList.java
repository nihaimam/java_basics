public class VoteList{
  
  public Vote[] votes;
  
  //initializes the internal array to size 0
  public VoteList(){
    votes = new Vote[0];
  }
  
  //initialize the internal with the given array
  public VoteList(Vote[] votes){
    this.votes = votes;
  }
  
  //creates copy of the votelist object
  public VoteList copy(){
    VoteList copy = new VoteList();
    return copy;
  }
  
  //size of votes
  public int size(){
    int size = 0;
    for (int i = 0; i < votes.length; i++){
      size++;
    }
    return size;
  }
  
  //return the value at the provided index
  public Vote get(int index){
    if (index > votes.length){
      //throws an exception when index is out of bounds
      throw new RuntimeException("Array Index Out Of Bounds!");
    }
    else {
      return votes[index];
    }
  }
  
  //changes the value at the given index
  public void set(int index, Vote vote){
    if (index > votes.length){
      //throws an exception when index is out of bounds
      throw new RuntimeException("Array Index Out Of Bounds!");
    }
    else {
      votes[index] = vote;
    }
  }
  
  //index of first occurrence of the given vote
  //return -1 when index out of bound - no exception
  public int indexOf(Vote vote){
    int index = -1;
    for (int i = 0; i < votes.length; i++){
      if (votes[i].equals(vote)){
        index = i;
        break;
      }
    }
    return index;
  }
  
  //add a new value in the end of the array
  public void add(Vote vote){
    if (vote == null){
      throw new RuntimeException("String is null");
    }
    int len = votes.length + 1;
    Vote[] str = new Vote[len];
    for (int i = 0; i < votes.length; i++){
      str[i] = votes[i];
    }
    str[len-1] = vote;
    votes = str;
  }
  
  //add a value at a particular index
  public void addAt(int index, Vote vote){
    if (vote == null){
      throw new RuntimeException("String is null");
    }
    if (index > (votes.length + 1)){
      throw new RuntimeException("Array Index Out Of Bounds!");
    }
    int len = votes.length + 1;
    Vote[] str = new Vote[len];
    for (int i = 0; i < index; i++){
      str[i] = votes[i];
    }
    str[index] = vote;
    for (int i = index + 1; i < str.length; i++){
      str[i] = votes[i - 1];
    }
    votes = str;
  }
  
  //remove the value of a particular index
  public Vote removeAt(int index){
    if (index > (votes.length + 1)){
      throw new RuntimeException("Array Index Out Of Bounds!");
    }
    int len = votes.length - 1;
    Vote[] str = new Vote[len];
    for (int i = 0; i < index; i++){
      str[i] = votes[i];
    }
    Vote s = votes[index];
    for (int i = index + 1; i < votes.length; i++){
      str[i - 1] = votes[i];
    }
    votes = str;
    return s;
  }
  
  //return a string representation
  @Override public String toString(){
    String n = "{";
    for (int i = 0; i < votes.length; i++){
      n = n + "{" + votes[i] + "}" + ", ";
      if (i == votes.length - 1){
        n = n + "{" + votes[i] + "}";
      }
    }
    return n + "}";
  }
}