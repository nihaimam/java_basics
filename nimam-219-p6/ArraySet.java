import java.util.*;
import java.io.*;

/**
 * A simple implementation of a Set backed by an array. As a Set,
 * instances track *unique* items so that no duplicates occur. This
 * implementation should keep the underlying array sorted and use
 * binary search to quickly identify if items are present or absent
 * to maintain uniqueness. To maintain this, items that go into the set
 * must implement the Comparable interface so that they have a
 * compareTo(..) method and are compatible with library search and
 * sort methods.
 */
public class ArraySet<T extends Comparable<T>>{
  
  protected List<T> myList;  // ArrayList to manage the array size
  protected int size;        // Size of myList
  
  /**
   * Create an empty ArraySet
   */
  public ArraySet(){
    this.myList = new ArrayList<T>();
    this.size = 0;
  }
  
  /**
   * Return the size of the set, the number of unique items in it
   */
  public int size(){
    return myList.size();
  }
  
  /**
   * Return the contents of the set as a list
   */
  public List<T> asList(){
    return myList;
  }
  
  /**
   * Return true if the query item is present in the set and false otherwise
   * This method should use binary search to determine presence or absence
   */
  public boolean contains(T query){
    int index = Collections.binarySearch(myList, query);
    return index >= 0;
  }
  
  /**
   * Ensure the specified item is present in the set.
   * Maintain the uniqueness of items in the set by not adding duplicates
   * If the given item is added to the set, return true
   * If the item is already present , return false
   * Throw a RuntimeException in the event a item is null
   */
  public boolean add(T item){
    if (item == null)
      throw new RuntimeException("Item cannot be null");
    if (this.contains(item))
      return false;
    int index = Collections.binarySearch(myList, item);
    index = ((index*-1)-1);
    myList.add(index,item);
    this.size++;
    return true;
  }
  
  /**
   * Retrieve an item in the set that is equal to the query item
   * If no item in the set is equal to the query, return null
   */
  public T get(T query){
    int index = Collections.binarySearch(myList, query);
    if (index < 0){
      return null;
    }
    return myList.get(index);
  }
  
  /**
   * Return a string representation of the set and its contents
   * The string should be identical in format to Lists making use the toString()
   * Examples:
   * [1, 3, 5, 9, 20, 27]
   * ["A", "B", "F", "R", "V"]
   */
  public String toString(){
    if(this.size()<=0){return "[]";}
    return myList.toString();
  }
  
}