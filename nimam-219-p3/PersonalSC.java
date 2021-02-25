// Required packages for file reading
import java.io.*;
import java.util.*;

// A spell checker which allows use of a personal dictionary
public class PersonalSC extends InteractiveSC {
  
  protected String personalDictFilename;      // Name of the file for the personal dictionary
  protected String [] personalDictWords;      // Personal dictionary words
  
  // Construct a spell checker with a personal, modifiable dictionary
  public PersonalSC(String dictFilename, boolean ignoreCase, Scanner input, PrintWriter output, String personalDictFilename){
    super(dictFilename, ignoreCase, input, output); // Calling parent class constructor by using the super(..) method
    this.personalDictFilename = personalDictFilename; // Initializing personal dict filename
    this.personalDictWords = readAllLines(personalDictFilename); // Using parent class method read all lines from personal dict file
  }
  
  // Return the size of the personalDictWords array
  public int personalDictSize(){
    return personalDictWords.length; //return length of personal dictionary
  }
  
  // Check if the word is correct according to the same methodology as the parent class
  // If not, check whether the word appears in the personal dictionary
  @Override public boolean isCorrect(String word){
    boolean flag = false;
    for (int i = 0; i < dictWords.length; i++){ // Loop through all dictionary words
      if (ignoreCase && dictWords[i].equalsIgnoreCase(word)){ // Compare by ignoring case
        flag = true;
      } 
      if (!ignoreCase && dictWords[i].equals(word)){ // Compare by exact case
        flag = true;
      }
    }
    if (!flag){ // If flag is not true meaning the word is not in the dictionary
      for (int i = 0; i < personalDictWords.length; i++){ // Loop through all personal dictionary words
        if (ignoreCase && personalDictWords[i].equalsIgnoreCase(word)){ // Compare by ignoring case
          flag = true;
        }
        if (!ignoreCase && personalDictWords[i].equals(word)){ // Compare by exact case
          flag = true;
        }
      }
    }
    return flag; // Finally return true or false
  }
  
  // If the parameter word is not dictionary, prompt the user on whether they would like to add it
  // If the response is "yes", append it to the personalDictWords
  // If the response is "no", prompt the user for a correction like the parent class does
  // Using library methods from java.util.Arrays is allowed
  @Override public String correctWord(String word){
    String response;
    String answer = word;
    if (!isCorrect(word)){ //If the word not found in any dictionary
      System.out.println("**" + word + "** not in dictionary add it?(yes/no) "); //print the prompt
      Scanner input = new Scanner(System.in); // Create reading object scanner to read yes and no
      response = input.next(); // Read response yes or no
      if (response.equals("yes")){ // If yes add the word to the personal dictionary
        addAtEnd(word);
        answer = word;
      }
      else {
        word = super.correctWord(word); // Call the parent class correct word method
        answer = word;
      }
    }
    return answer;
  }
  
  //helper method to add the word to the end of the personal dictionary array
  public String[] addAtEnd(String word){
    if (word == null){
      throw new RuntimeException("Word is null");
    }
    //create new array from old array and allocate one more element
    String[] pDict = Arrays.copyOf(personalDictWords, personalDictWords.length + 1); 
    pDict[pDict.length - 1] = word; // Tack on one more word on to the end of the array
    personalDictWords = pDict; 
    return personalDictWords;
  }
  
  // Return a string showing all words currently in the personal dictionary
  public String getAllPersonalDictWords(){
    String words = ""; // Empty string
    for (int i = 0; i < personalDictWords.length; i++){ // Loop through the personal dictionary
      words = words + personalDictWords[i] + "\n"; //adding each word to words and newline after each word
    }
    return words;
  }
  
  // Write the contents of personalDictWords to the file from which they were initially read (personalDictFilename)
  // Write one word per line
  public void savePersonalDict() throws Exception {
    String words = getAllPersonalDictWords(); // Get all personal dict words
    Scanner input = new Scanner(words); // Input the words from the string using a scanner
    PrintWriter output = new PrintWriter("personal-dict.txt"); // Open file for writing
    while (input.hasNextLine()) { // While the string has next line
      String s = input.nextLine();
      output.println(s);
    }
    output.close(); // Close the printwriter
    input.close(); // Close the scanner
    output.println("@ Personal dictionary written to file personal-dict.txt"); // Print the message that the file is written
  }
}