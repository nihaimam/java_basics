public class Vote{
  
  //instance variables
  public CandidateList candidates = new CandidateList();
  //string[] of candidates from CandidateList class
  public String[] contestants = candidates.candidates;
  public int[] selections;
  
  //initializes the candidates list to the given value
  //creates the selections array, filled with zeroes
  public Vote(CandidateList candidates){
    this.candidates = candidates;
    int[] selections = new int[0];
  }
  
  //initializes both instance variables with given values
  public Vote(CandidateList candidates, int[] selections){
    this.candidates = candidates;
    this.selections = selections;
  }
  
  //returns the value of index given
  //throws an exception when rank is not present
  public int indexOfRank(int rank){
    int answer = -1;
    for (int i = 0; i < selections.length; i++){
      if (selections[i] == rank){
        answer = i;
      }
    }
    if (answer == -1){
      throw new RuntimeException("don't let indexOfRank return when rank not found");
    }
    return answer;
  }
  
  //find the indicated rank is, return the string
  //throws an exception when rank is not present
  public String getRankedCandidate(int rank){
    int answer = -1;
    for (int i = 0; i < selections.length; i++){
      if (selections[i] == rank){
        answer = i;
      }
    }
    if (answer == -1){
      throw new RuntimeException("don't let indexOfRank return when rank not found");
    }
    return contestants[answer];
  }
  
  //record the rank at the given index
  //throws an exception when rank or index is not valid
  public void recordChoice(int rank, int index){
    if (index > selections.length){
      throw new RuntimeException("index is not valid");
    }
    if (rank != 1 || rank != 2 || rank != 2 || rank != 4){
      throw new RuntimeException("rank is not valid");
    }
    selections[index] = rank;
  }
  
  //records the rank at the right index for the given candidate
  //throws an exception when index or candidate is not valid
  public void recordChoice(int rank, String candidate){
    if (rank != 1 || rank != 2 || rank != 2 || rank != 4){
      throw new RuntimeException("rank is not valid");
    }
    int index = -1;
    for (int i = 0; i < contestants.length; i++){
      if (contestants[i] == candidate){
        index = i;
      }
    }
    if (index == -1){
      throw new RuntimeException("index is not valid");
    }
    selections[index] = rank;
  }
  
  //returns the best option - the one with the lowest rank
  //throws an exception when there are no options
  public String bestChoice(CandidateList options){
    String[] cs = options.candidates;
    boolean answer = true;
    for (int m = 0; m < contestants.length; m++){
      for (int n = 0; n < cs.length; n++){
        if (contestants[m] == cs[n]){
          answer = true;
        }    
      }
    }
    if (answer == false){
      return null;
    }
    int index = 0;
    int min = selections[0];
    for (int i = 0; i < selections.length; i++){
      if (selections[i] < min){
        min = selections[i];
        index = i;
      }
    }
    if (cs == null){
      return null;
    }
    return cs[index];
  }
  
  //return a copy of the list of candidates
  public CandidateList copyOfCandidateList(){
    CandidateList copy = new CandidateList();
    return copy;
  }
  
  //must be a list of candidates present
  //must be an array of selections present
  ////of the same size as the candidates list
  //all selections must be valid ranks
  //each rank must appear exactly once.
  //USE A HELPER METHOD
  public boolean validateVote(){
    boolean answer = false;
    int len1 = selections.length;
    int len2 = contestants.length;
    if (selections == null){return false;}
    if (contestants == null){return false;}
    if (len1 == len2){return true;}
    return false;
    
  }
  
  //Returns a string representation that pairs
  //candidate strings with their rankings
  @Override public String toString(){
    String s = "{";
    for (int i = 0; i < contestants.length; i++){
      s = s + contestants[i] + ":" + selections[i] + ", ";
      if (i < contestants.length - 1){
        s = s + contestants[i] + ":" + selections[i];
      }
    }
    return s + "}";
  }
  
  //compares this vote to the other vote
  //check if it actually is a vote object
  @Override public boolean equals(Object other){
    if ( ! ( other instanceof Vote) ) {
      return false;
    }
    Vote otherVote = (Vote) other;
    return true;
  }
}