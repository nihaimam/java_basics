import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import modepkg.*;

public class Project5Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("Project5Tests");
  }
  
  /**
   * Layout of tests
   * @Test public void name_of_method_being tested(){
   *   *code*
   * }
   */
  
  /**
   * for i in {1..13}; do echo "Implementation $i"; 
   * javac -cp .:junit-cs211.jar:imp$i *.java; 
   * java -cp .:junit-cs211.jar:imp$i Project5Tests ;
   * done | grep -e "Tests run" -e "Implementation" -e "OK ("
   */
  
  /**
   * tests of pair constructor
   **/
  
  @Test //tests to see if constuctor is null or not
  public void pair_constructor1(){
    Pair<Integer,String> p = new Pair<Integer,String>(22,"wassup");
    assertTrue(p != null);
    assertFalse(p == null);
  }
  
  @Test //creates a pair object and checks the first and second
  public void pair_constructor2(){ 
    Pair<Integer,String> p = new Pair<Integer,String>(22,"wassup");
    assertEquals((Integer)22,p.getFirst());
    assertEquals("wassup",p.getSecond());
  }
  
  @Test //creates a pair object and checks the first and second
  public void pair_constructor3(){
    Pair<Integer,String> p = new Pair<Integer,String>(null,"wassup");
    assertEquals(null,p.getFirst());
    assertEquals("wassup",p.getSecond());
  }
  
  @Test //creates a pair object and compares the first and second
  public void pair_constructor4(){
    Pair<Integer,String> p = new Pair<Integer,String>(22,"wassup");
    Integer expectFirst = p.getFirst();
    String expectSecond = p.getSecond();
    assertTrue(22 == expectFirst);
    //compares the address, so return false, and assertFalse(false) is passed
    assertTrue("wassup" == expectSecond); 
  }
  
  /**
   * tests for pair getter
   */
  
  @Test //creates a pair object and uses the get methods
  public void pair_getter1(){
    Pair<Integer,String> p = new Pair<Integer,String>(22,"wassup");
    assertEquals((Integer)22,p.getFirst());
    assertEquals("wassup",p.getSecond());
  }
  
  @Test //creates a pair object and uses the get methods
  public void pair_getter2(){
    Pair<Integer,String> p = new Pair<Integer,String>(52,"howdy");
    assertFalse(22 == p.getFirst());
    assertFalse(("hello").equals(p.getSecond()));
  }
  
  @Test //creates a pair object and uses the get methods
  public void pair_getter3(){
    Pair<Integer,String> p = new Pair<Integer,String>(100,"hello world!");
    assertTrue(100 == p.getFirst());
    assertTrue(("hello world!").equals(p.getSecond()));
  }
  
  /**
   * tests for pair setter
   */
  
  @Test //creates a pair object and uses the set methods
  public void pair_setter1(){
    Pair<Integer,String> p = new Pair<Integer,String>(22,"wassup");
    p.setFirst(666);
    p.setSecond("rad");
    assertEquals((Integer)666,p.getFirst());
    assertEquals("rad",p.getSecond());
  }
  
  @Test //creates a pair object and uses the set methods
  public void pair_setter2(){
    Pair<Integer,String> p = new Pair<Integer,String>(99,"rad");
    p.setFirst(88);
    p.setSecond("bye");
    assertTrue(88 == p.getFirst());
    assertTrue("bye".equals(p.getSecond()));
  }
  
  @Test //creates a pair object and uses the set methods when the pair obj is null,null
  public void pair_setter3(){
    Pair<Integer,String> p = new Pair<Integer,String>(null,null);
    p.setFirst(32);
    p.setSecond("Simple");
    assertFalse(666 == p.getFirst());
    assertFalse("rad" == p.getSecond());
  }
  
  @Test //creates a pair object and uses the set methods us assign null to first value
  public void pair_setter4(){
    Pair<Integer,String> p = new Pair<Integer,String>(99,null);
    p.setFirst(null);
    p.setSecond("simple");
    assertEquals(null,p.getFirst());
    assertFalse("rad" == p.getSecond());
    assertTrue("simple" == p.getSecond());
  }
  
  @Test //creates a pair object and uses the set methods us assign null to second value
  public void pair_setter5(){
    Pair<Integer,String> p = new Pair<Integer,String>(null,"hippie");
    p.setFirst(666);
    p.setSecond(null);
    assertTrue(666 == p.getFirst());
    assertEquals(null,p.getSecond());
  }
  
  /**
   * tests for pair indexByFirst
   */
  
  @Test //uses index by first method to check if the list contains the pair object
  public void pair_indexByFirst1(){
    //list is empty
    List<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
    int index = Pair.indexByFirst(pairList, 666); //index should be -1
    assertEquals(-1, index);
  }
  
  @Test //uses index by first method to check if the list contains the pair object
  public void pair_indexByFirst2(){
    //list is empty
    List<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
    pairList.add(null);
    int index = Pair.indexByFirst(pairList, null); //index should be -1
    assertEquals(-1, index);
  }
  
  @Test //uses index by first method to check if the list contains the pair object
  public void pair_indexByFirst3(){
    List<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
    Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(20,22);
    Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(23,45);
    Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(45,47);
    Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(67,69);
    pairList.add(p1);
    pairList.add(p2); 
    pairList.add(p3); 
    pairList.add(p4);
    int index = Pair.indexByFirst(pairList, 45); //index should be 2
    assertEquals(2, index);
  }
  
  @Test //uses index by first method to check if the list contains the pair object
  public void pair_indexByFirst4(){
    List<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
    Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(20,22);
    Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(23,45);
    Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(45,47);
    Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(67,69);
    pairList.add(p1);
    pairList.add(p2); 
    pairList.add(p3); 
    pairList.add(p4);
    int index = Pair.indexByFirst(pairList, 22); //index should be -1
    assertEquals(-1, index);
  }
  
  @Test //uses index by first method to return the first instance of the pair object
  public void pair_indexByFirst5(){
    Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(20,22);
    Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(45,47);
    Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(67,69);
    Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(67,69);
    List<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
    pairList.add(p1); 
    pairList.add(p2); 
    pairList.add(p3); 
    pairList.add(p4);
    int index = Pair.indexByFirst(pairList, 67); //index should be 2
    assertEquals(2,index);
  }
  
  /**
   * tests for pair indexBySecond
   */
  
  @Test //uses index by second method to check if the list contains the pair object
  public void pair_indexBySecond1(){
    //list is empty
    List<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
    int index = Pair.indexBySecond(pairList, 666); //index should be -1
    assertEquals(-1, index);
  }
  
  @Test //uses index by second method to check if the empty list contains the pair object
  public void pair_indexBySecond2(){
    //list is empty
    List<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
    pairList.add(null);
    int index = Pair.indexBySecond(pairList, null); //index should be -1
    assertEquals(-1, index);
  }
  
  @Test //uses index by second method to check if the list contains the pair object
  public void pair_indexBySecond3(){
    List<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
    Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(20,22);
    Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(23,45);
    Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(45,47);
    Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(67,69);
    pairList.add(p1);
    pairList.add(p2); 
    pairList.add(p3); 
    pairList.add(p4);
    int index = Pair.indexBySecond(pairList, 45); //index should be 1
    assertFalse(2 == index);
    assertEquals(1, index);
  }
  
  @Test //uses index by second method to check if the list contains the pair object
  public void pair_indexBySecond4(){
    List<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
    Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(20,22);
    Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(23,45);
    Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(45,47);
    Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(67,69);
    pairList.add(p1);
    pairList.add(p2); 
    pairList.add(p3); 
    pairList.add(p4);
    int index = Pair.indexBySecond(pairList, 222); //index should be -1
    assertEquals(-1, index);
  }
  
  @Test //uses index by second method to check if the list contains the pair object
  public void pair_indexBySecond5(){
    Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(20,22);
    Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(45,69);
    Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(67,69);
    Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(67,69);
    List<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
    pairList.add(p1); 
    pairList.add(p2); 
    pairList.add(p3); 
    pairList.add(p4);
    int index = Pair.indexBySecond(pairList, 69); //index should be 1
    assertFalse(0 == index);
    assertTrue(1 == index);
    assertFalse(2 == index);
    assertFalse(3 == index);
    assertEquals(1,index);
  }
  
  /**
   * tests for pair toString
   */
  
  @Test //uses the to string method with proper parameters
  public void pair_toString1(){
    Pair<Integer,String> p = new Pair<Integer,String>(22,"wassup");
    assertEquals("Pair(22,wassup)",p.toString());
  }
  
  @Test //uses the to string method with null parameters
  public void pair_toString2(){
    Pair<Integer,String> p = new Pair<Integer,String>(null,null);
    assertEquals("Pair(null,null)",p.toString());
  }
  
  @Test //uses the to string method and checks with the equals method
  public void pair_toString3(){
    Pair<Integer,String> p = new Pair<Integer,String>(44,"Hello");
    Pair<Integer,String> p2 = new Pair<Integer,String>(44,"Hello");
    assertEquals(p2.toString(),p.toString());
    assertTrue(p.toString().equals("Pair(44,Hello)"));
  }
  
  /**
   * tests for testme contains
   */
  
  @Test //uses the contains method to check if value is in list
  public void testMe_contains1(){
    List<Integer> myList = new ArrayList<Integer>();
    assertFalse(myList.contains(100));
  }
  
  @Test //uses the contains method to check if value is in list
  public void testMe_contains2(){
    List<Integer> myList = new ArrayList<Integer>();
    myList.add(null);
    assertEquals(true,myList.contains(null));
  }
  
  @Test //uses the contains method to check if value is in list
  public void testMe_contains3(){
    List<Integer> myList = new ArrayList<Integer>();
    myList.add(97); myList.add(98); myList.add(99); myList.add(100);
    List<Integer> l = new ArrayList<Integer>();
    l.add(97); l.add(98); l.add(99);
    assertFalse(TestMe.contains(l,100));
    assertTrue(TestMe.contains(myList,100));
  }
  
  @Test //uses the contains method to check if value is in list
  public void testMe_contains4(){
    List<Integer> myList = new ArrayList<Integer>();
    myList.add(97); myList.add(98);
    myList.add(99); myList.add(100);
    List<Integer> newList = new ArrayList<Integer>();
    newList.add(97); newList.add(98); 
    newList.add(99);
    assertEquals(TestMe.contains(myList,100),true);
    assertEquals(TestMe.contains(newList,653),false);
  }
  
  /**
   * tests for testme uniques
   */
  
  @Test //uses the uniques method to create a list of uniques
  public void testMe_uniques1(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(98); a.add(98); a.add(99); a.add(100);
    List<Integer> b = new ArrayList<Integer>();
    b.add(98); b.add(99); b.add(100);
    assertEquals(b,TestMe.uniques(a));
  }
  
  @Test //uses the uniques method to create a list of uniques
  public void testMe_uniques2(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(2); a.add(2); 
    a.add(2); a.add(3); a.add(4);
    List<Integer> b = new ArrayList<Integer>();
    b.add(1);
    List<Integer> c = new ArrayList<Integer>();
    c.add(1); c.add(2); c.add(3); c.add(4);
    assertFalse(b.equals(TestMe.uniques(a)));
    assertEquals(c,TestMe.uniques(a));
  }
  
  @Test //uses the uniques method to create a list of uniques
  public void testMe_uniques3(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(2); a.add(2);
    a.add(2); a.add(3);
    List<Integer> b = new ArrayList<Integer>();
    b.add(1); b.add(2); b.add(3);
    assertTrue(b.equals(TestMe.uniques(a)));
    assertEquals(b,TestMe.uniques(a));
  }
  
  @Test //uses the uniques method to create a list of uniques
  public void testMe_uniques4(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(2);a.add(3);
    List<Integer> b = new ArrayList<Integer>();
    b.add(1); b.add(2);b.add(3);
    assertEquals(b,TestMe.uniques(a));
  }
  
  @Test
  public void testMe_uniques5(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(1); a.add(null); a.add(null); a.add(4);
    List<Integer> u = TestMe.uniques(a);
    List<Integer> b = new ArrayList<Integer>();
    b.add(1); b.add(null); b.add(4);
    assertEquals(b,TestMe.uniques(a));
  }
  
  @Test
  public void testMe_uniques6(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(null); a.add(null); a.add(null);
    List<Integer> u = TestMe.uniques(a);
    List<Integer> b = new ArrayList<Integer>();
    b.add(null);
    assertEquals(b,TestMe.uniques(a));
  }
  
  /**
   * tests for testme mode
   */
  
  @Test //uses the uniques method to create a list consisting of the mode of the list
  public void testMe_mode1(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(2); a.add(2); a.add(2);
    a.add(3); a.add(3); a.add(4); a.add(3);
    List<Integer> b = new ArrayList<Integer>();
    b.add(2); b.add(3);
    assertTrue(b.equals(TestMe.mode(a)));
  }
  
  @Test //uses the uniques method to create a list consisting of the mode of the list
  public void testMe_mode2(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(2); a.add(2); a.add(2);
    a.add(1); a.add(3); a.add(4); a.add(3);
    a.add(4); a.add(3); a.add(4); a.add(5);
    List<Integer> b = new ArrayList<Integer>();
    b.add(2); b.add(3); b.add(4);
    assertEquals(b,TestMe.mode(a));
  }
  
  @Test //uses the uniques method to create a list consisting of the mode of the list
  public void testMe_mode3(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(2); a.add(2); a.add(2);
    a.add(1); a.add(3); a.add(4); a.add(3); 
    a.add(4); a.add(3); a.add(4); a.add(5);
    List<Integer> b = new ArrayList<Integer>();
    b.add(4);b.add(5);
    assertFalse(b.equals(TestMe.mode(a)));
  }
  
  @Test //uses the uniques method to create a list consisting of the mode of the list
  public void testMe_mode4(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(2); a.add(3); 
    a.add(4); a.add(5); a.add(5);
    List<Integer> b = new ArrayList<Integer>();
    b.add(5);
    assertEquals(b,TestMe.mode(a));
  }
  
  @Test //uses the uniques method to create a list consisting of the mode of the list
  public void testMe_mode5(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(null);
    List<Integer> b = new ArrayList<Integer>();
    b.add(null);
    assertEquals(b,TestMe.mode(a));
  }
  
  @Test
  public void testMe_mode6(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(1); a.add(null); a.add(null); a.add(2);
    List<Integer> u = TestMe.mode(a);
    List<Integer> b = new ArrayList<Integer>();
    b.add(1);  b.add(null);
    assertEquals(b,TestMe.mode(a));
  }
  
  /**
   * combination tests
   */
  
  @Test //a combination of mode and unique method
  public void testMe_mode_and_unique1(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(1); a.add(2); a.add(4); a.add(4);
    List<Integer> m = TestMe.mode(a);
    List<Integer> u = TestMe.uniques(m);
    List<Integer> b = new ArrayList<Integer>();
    b.add(1); b.add(4);
    assertEquals(b,u);
  }
  
  @Test //a combination of mode and unique method using null and using a try catch block!
  public void testMe_mode_and_unique2(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(null); a.add(null); a.add(null); a.add(null);
    try{
      List<Integer> m = TestMe.mode(a);
      List<Integer> u = TestMe.uniques(a);
    List<Integer> bm = new ArrayList<Integer>();
    bm.add(null);
    List<Integer> um = new ArrayList<Integer>();
    um.add(null);
    assertEquals(bm,m);
    assertEquals(bm,u);
    } catch(RuntimeException e){} 
  }
  
  @Test //a combination of mode and unique method with null hidden in the list
  public void testMe_mode_and_unique3(){
    List<Integer> a = new ArrayList<Integer>();
    a.add(1); a.add(1); a.add(null); a.add(4); a.add(4);
    List<Integer> u = TestMe.uniques(a);
    List<Integer> b = new ArrayList<Integer>();
    b.add(1); b.add(null); b.add(4);
    assertEquals(b,TestMe.uniques(a));
  }
  
}