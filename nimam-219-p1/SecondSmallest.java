public class SecondSmallest{
  public static int secondSmallest(int[] xs){
    if (xs == null){
      throw new RuntimeException("array is null.");
    }
    if (xs.length < 2){
      throw new RuntimeException("array is too short.");
    }
    int small = Integer.MAX_VALUE;
    int secondsmall = Integer.MAX_VALUE;
    int len = xs.length;
    for (int n = 0; n < len; n++){
      if (xs[n] <= small){
        secondsmall = small;
        small = xs[n];
      }
      else if (xs[n] <= secondsmall && xs[n] != small){
        secondsmall = xs[n];
      }
    }
    return secondsmall;
  }
}