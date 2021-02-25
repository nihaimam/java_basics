public class Partition{
  public static void partitionOddEven(int[] xs){
    if (xs == null){
      throw new RuntimeException("null array.");
    }
    int ctr = 0; 
    for (int n = 0; n < xs.length; n++){
      if (xs[n] % 2 == 0){
        //swap the numbers
        int temp = xs[n];
        xs[n] = xs[ctr];
        xs[ctr] = temp;
        ctr++;
      }
    }
  }
}