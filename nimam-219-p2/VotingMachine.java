public class VotingMachine{
  
  public CandidateList candidates; //candidates running for office
  public VoteList votes;           //votes cast for the elction
  public String office;            //office candidates are running for
  public Log log;                  //for recording messages
  
  //initializes all fields using given arguements
  public VotingMachine(String office){
    this.office = office;
  }
  
  //initializes all fields using given arguements
  public VotingMachine(String office, CandidateList candidates){
    this.office = office;
    this.candidates = candidates;
  }
  
  //initializes all fields using given arguements
  public VotingMachine(String office, CandidateList candidates, VoteList votes){
    this.office = office;
    this.candidates = candidates;
    this.votes = votes;
  }
  
  //records the given vote to the appropriate list
  //throws a RuntimeException when the vote is null
  public void addVote(Vote vote){
    if (vote == null){
      throw new RuntimeException("String is null");
    }
    Vote[] votess = votes.votes;
    int len = votess.length + 1;
    Vote[] str = new Vote[len];
    for (int i = 0; i < votess.length; i++){
      str[i] = votess[i];
    }
    str[len-1] = vote;
    votess = str;
  }
  
  //records the given candidate to the appropriate list
  //throws a RuntimeException when the candidate is null
  public void addCandidate(String candidate){
    if (candidate == null){
      throw new RuntimeException("Candidate is null!");
    }
    String[] candidatess = candidates.candidates;
    int len = candidatess.length + 1;
    String[] str = new String[len];
    for (int i = 0; i < candidatess.length; i++){
    str[i] = candidatess[i];}
    str[len-1] = candidate;
    candidatess = str;
  }
  
  //returns a reference to a COPY of the given list of votes
  public VoteList reportVotes(){
    VoteList copy = new VoteList();
    return copy;
  }
  
  //returns a refernece to a COPY of the given list of candidates
  public CandidateList reportCandidates(){
    CandidateList copy = new CandidateList();
    return copy;
  }
  
  //return a string value of the office being voted upon
  public String reportOffice(){
    return office;
  }
  
  //validate the votes
  //check if votes are not null
  public boolean validateVotes(){
    String[] allCands = candidates.candidates;
    Vote[] curVotes = votes.votes;
    boolean answer = false;
    if (votes == null){
      answer = false;
    }
    return answer;
  }
  
  //a copy of the internally stored log
  public Log copyLog(){
    Log copy = new Log();
    return copy;
  }
  
  //tally up the votes of the candidatea
  public int[] tally(CandidateList viables){
    String[] allCands = candidates.candidates;
    String[] curCands = viables.candidates;
    if (curCands == null){
      throw new RuntimeException("Current Candidates are null");
    }
    Vote[] curVotes = votes.votes;
    if (curVotes == null){
      throw new RuntimeException("Current Votes are null");
    }
    int[] tallies = new int[curCands.length];
    Vote[] tally = new Vote[curCands.length];
    for (int i = 0; i < curCands.length; i++){
      for (int j = 0; j < allCands.length; j++){
        if (curCands[i] == allCands[j]){
          int index = j;
          curVotes[j] = tally[i];
        }
      }
    }
    /////turn tally from vot[] to int[] tallies
    return tallies;
  }
  
  //create a new Candidate List with correct candidates in it
  public CandidateList thinHerd(CandidateList viables, int[] tallies){
    String[] curCand = viables.candidates;
    if (curCand == null){
      throw new RuntimeException("Candidates are null");
    }
    if (tallies == null){
      throw new RuntimeException("Tallies are null");
    }
    int min = tallies[0];
    int max = tallies[0];
    for (int i = 0; i < tallies.length; i++){
      if (min < tallies[i]){
        min = tallies[i];
      }
    }
    for (int i = 0; i < tallies.length; i++){
      if (max > tallies[i]){
        max = tallies[i];
      }
    }
    String[] copy = viables.candidates; //copy of cur_Cands
    if (min == max){
      return viables;
    }
    CandidateList nexCand = new CandidateList();
    String[] nex_Cand = nexCand.candidates; //nex_Cands copy of cur_Cands
    for (int i = tallies.length - 1; i < tallies.length; i--){
      if (tallies[i] == min){
        if (i > (nex_Cand.length + 1)){
      throw new RuntimeException("Array Index Out Of Bounds!");
    }
        int len = nex_Cand.length - 1;
        String[] str = new String[len];
        for (int j = 0; j < i; j++){
          str[j] = nex_Cand[j];
        }
        String s = nex_Cand[i];
        for (int j = i + 1; j < nex_Cand.length; j++){
          str[j - 1] = nex_Cand[j];
        }
        nex_Cand = str;
      }
    }
    return nexCand;
  }
  
  //check if there is a tie
  public boolean isCompleteTie(int[] tallies){
    boolean answer = false;
    for (int i = 0; i < tallies.length; i++){
      for (int j = 0; j < tallies.length; j++){
        if (tallies[i] == tallies[j]){
          answer = true;
        }
      }
    } 
    return answer;
  }
  
  //to string method
  public String roundResultString(CandidateList viables, int[] tallies){
    String[] people = viables.candidates;
    String s = "";
    for (int i = 0; i < people.length & i < tallies.length; i++){
      if (i == people.length - 1 && i == tallies.length - 1){
        s = people[i] + ":" + tallies[i];
      }
      s = people[i] + ":" + tallies[i] + ", ";
    }
    return s;
  }
  
  //use tally, thinHerd, isCompleteTie and copyLog methods
  public Log tabulate(){
    Log copy = new Log();
    copy.record("Begin Log");
    if (validateVotes() == false){
      copy.record("Votes are Invalid");
    }
    copy.record("End Log");
    return copy;
  }
}