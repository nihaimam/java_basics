public class HighlyComposite{
  public static int numDivisors(int n){
    if (n < 0){
      throw new RuntimeException("only positive integers allowed.");
    }
    if (n == 0){
      throw new RuntimeException("no zeros allowed.");
    }
    int nod = 0; //number of divisors
    for (int i = n; i > 0; i--){
      if (n % i == 0){
        nod++;
      }
    }
    return nod;
  }
  public static boolean highlyComposite(int n){
    if (n < 0){
      throw new RuntimeException("only positive integers allowed.");
    }
    if (n == 0){
      throw new RuntimeException("no zeros allowed.");
    }
    int ab = 0;
    ab = numDivisors(n); //ab = num of divisors n has
    for (int i = n - 1; i > 0; i--){
      int cd = 0;
      cd = numDivisors(i);
      if (cd >= ab){
        return false;
      }
    }
    return true;
  }
}