public class Balanced{
  public static boolean isBalanced(String s){
    if (s.matches(".*[a-zA-Z]+.*") || s.matches(".*[1-9\n\t]+.*") || s.contains(" ")){
      throw new RuntimeException("only parenthesis characters");
    }
    int ub = 0; //unbalanced brackets
    boolean answer = true;
    if (s.length() % 2 == 0){
      answer = true;
    }
    else{
      answer = false;
    }
    for (char ch:s.toCharArray()){
      if (ch == '('){
        ub++;
      }
      else if (ch == ')'){
        ub--;
      }
      else{
        answer = false;
      }
      if (ub < 0){
        answer = false;
      }
    }
    return answer;
  }
}