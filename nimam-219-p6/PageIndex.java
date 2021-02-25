import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.io.*;
import java.util.*;

/**
 * Index of terms found on pages and the associated pages on which
 * they were found
 */
public class PageIndex{
  
  // The set of IndexEntries which tracks terms and pages
  protected ArraySet<IndexEntry> entries;
  
  /**
   * Create an empty PageIndex
   */
  public PageIndex(){
    this.entries = new ArraySet<IndexEntry>();
  }
  
  /**
   * Return the number of terms in IndexEntries
   */
  public int size(){
    return entries.size();
  }
  
  /**
   * Return a string representation of the indexed terms and pages
   * 
   * EXAMPLE:
   * INDEX: #### entries
   * --------------------
   * @ entry1
   * entry1-file1
   * entry1-file2
   * entry1-file3
   * ...
   * 
   * Creates a header and then iterates through all IndexEntries
   * appending their toString()
   */
  public String toString(){
    String s = "INDEX: " + entries.size() + " entries\n--------------------\n";
    for (IndexEntry entry : entries.asList()) {
      s += entry;
    }
    return s;
  }
  
  /**
   * Determine if the term given is valid
   * Valid terms do not appear in the sorted array Util.STOP_WORDS
   * Use binary search to efficiently determine if the given term
   * is in STOP_WORDS; return false if it is and true otherwise.
   */
  public boolean validTerm(String term){
    if (term == null || term.isEmpty())
      return false;
    if (term.length() <= 1)
      return false;
    int index = Arrays.binarySearch(Util.STOP_WORDS, term.toLowerCase(), null);
    return index < 0; 
  }
  
  /**
   * Return true if the IndexContains the given term and some pages
   * associated with it and false otherwise.
   */
  public boolean containsTerm(String term){
    term = term.toLowerCase();
    //Iterate over all the entries
    for (IndexEntry entry : entries.asList()){
      // Check the term associated with this entry
      if (entry.getTerm().equals(term) && entry.getPages().size() > 0) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Return a list of the pages associated with the given term.If
   * there are no pages associated with the given term, return and
   * empty list.
   */
  public List<String> getPagesWithTerm(String term){
    term = term.toLowerCase();
    // Iterate over all the entries
    for (IndexEntry entry : entries.asList()){
      // Return the list of pags associated with this term
      if (entry.getTerm().equals(term) && entry.getPages().size() > 0){
        return entry.getPages();
      }
    }
    // Term does not exists in the entries array. so return an empty array
    return new ArrayList<>();
  }
  
  /**
   * Add the given term found in the given page to the index.  If the
   * term is not valid as per the validTerm() method, do not add it
   * and return false.  Valid terms should be added along with the
   * page on which they occurred to an IndexEntry.  Return true if the
   * term is new to the index or if the term exists but the page is
   * new for that term.  Otherwise return false.
   */
  public boolean addTermAndPage(String term, String page){
    term = term.toLowerCase();
    // If term is not valid then return false
    if (validTerm(term)){
      //If entries contains the given term
      if (containsTerm(term)){
        // Iterate over all the entries and find the entry with given term
        for (IndexEntry entry : entries.asList()) {
          if (entry.getTerm().equals(term)){
            // If entry already has the page, then return false otherwise
            // add the page to the entry
            if (entry.containsPage(page)){
              return false;
            }
            else {
              entry.addPage(page);
              return true;
            }
          }
        }   
      }
      else {
        // Entries does not have term, so add the new entry with term and page
        IndexEntry entry = new IndexEntry(term);
        entry.addPage(page);
        entries.add(entry);
        return true;
      }
    }
    return false;
  }
  
  /**
   * For each page in the given crawler's foundPageList(), open and
   * parse the page using the JSoup library.  Examine the body text of
   * the page which is a string.  Use a Scanner on this string and set
   * the delimiter to
   * 
   *     scan.useDelimiter("(\\p{Space}|\\p{Punct}|\\xA0)+");
   * 
   * which will parse through words on the page skipping most
   * punctuation. Add this page to the entries in the index associated
   * with each term in the body. Individual pages may generate
   * exceptions while reading which the this method should ignore and
   * continue to the next page. This may happen for the pages which
   * use HTML frames.
   */
  public void addCrawledPages(Crawler crawler){
    try {
      for (int i = 0; i < crawler.foundPagesList().size(); i++){
        String page = crawler.foundPagesList().get(i);
        // Get the page from the crawler found page list and  
        File input = new File(page);
        // Open the document using jsoup
        Document doc = Jsoup.parse(input, "UTF-8");
        // Get the text of the document
        String text = doc.body().text();
        // Read the text word by word using the scanner configured to use given delimiter
        Scanner scan = new Scanner(new StringReader(text));
        scan.useDelimiter("(\\p{Space}|\\p{Punct}|\\xA0)+");
        while (scan.hasNext()){
          String term = scan.next();
          addTermAndPage(term, page);         
        }
        scan.close();
      }
    }
    catch(Exception e){
      return;
    }
  }
  
  /**
   * Find the intersection (common elements) of x and y. Assume that x and
   * y are sorted.  Use this fact to efficiently build up a list of the
   * common elements with a single loop through the lists x and y.
   */
  public static List<String> intersectionOfSorted(List<String> x, List<String> y){
    List<String> intersection = new ArrayList<>();
    int i = 0, j = 0;
    // Like merge algorithm in merge sort
    while (i < x.size() && j < y.size()){
      int cmp = x.get(i).compareTo(y.get(j));
      if (cmp < 0) { //x[i] < y[j], increment the index with smaller element
        i++;
      }
      else if (cmp > 0){ //x[i] > y[j]
        j++;
      }
      else { // Both are equal so add them to the intersection list
        intersection.add(x.get(i));
        i++;
        j++;
      }
    }
    return intersection;
  }
  
  /**
   * Return a list of pages in the index which match the given
   * query. The query may be several space-separated words such as
   * "robotics artificial intelligence".  These should be separated
   * and used to retrieve lists of pages matching the words. Make use
   * of the insertionOfSorted(x,y) method to efficiently combine lists
   * of pages repeatedly in a loop to produce the end results.
   */
  public List<String> query(String queryString){
    // Split the query at spaces
    String[] queryTerms = queryString.split(" ");
    // If there are exactly one query terms in the string the return the list of pages 
    // with that query term
    if (queryTerms.length == 1){
      return getPagesWithTerm(queryTerms[0]);
    }
    // Find the list of pages for the term at index 0
    List<String> pagesForFirstTerm = getPagesWithTerm(queryTerms[0]);
    // Fine the list of pages for the term at index 1
    List<String> pagesForSecondTerm = getPagesWithTerm(queryTerms[1]);
    // Find the itersection of list of pages
    List<String> resultPages = intersectionOfSorted(pagesForFirstTerm, pagesForSecondTerm);
    // Iterate over the remaining terms to find the list of pages and
    // perform tintersectoin with the result set of pages
    for (int i = 2; i < queryTerms.length; ++i) {
      List<String> pages = getPagesWithTerm(queryTerms[i]);
      resultPages = intersectionOfSorted(resultPages, pages);
    }
    return resultPages; 
  }
  
  /**
   * Optional main method for your own testing.
   */
  public static void main(String args[]){
    return;
  } 
  
}










/*import org.jsoup.nodes.*;
 import org.jsoup.*;
 import org.jsoup.select.*;
 import java.io.*;
 import java.util.*;
 
 /**
 * Index of terms found on pages and the associated pages on which
 * they were found
 *
 public class PageIndex{
 
 // The set of IndexEntries which tracks terms and pages
 protected ArraySet<IndexEntry> entries;
 
 /**
 * Create an empty PageIndex
 *
 public PageIndex(){
 this.entries = new ArraySet<IndexEntry>();
 }
 
 /**
 * Return the number of terms in IndexEntries
 *
 public int size(){
 return entries.size();
 }
 
 /**
 * Return a string representation of the indexed terms and pages
 * 
 * EXAMPLE:
 * INDEX: #### entries
 * --------------------
 * @ entry1
 * entry1-file1
 * entry1-file2
 * entry1-file3
 * ...
 * 
 * Creates a header and then iterates through all IndexEntries
 * appending their toString()
 *
 public String toString(){
 String s = "INDEX: " + entries.size() + " entries\n--------------------\n";
 
 
 
 return s;
 }
 
 /**
 * Determine if the term given is valid
 * Valid terms do not appear in the sorted array Util.STOP_WORDS
 * Use binary search to efficiently determine if the given term
 * is in STOP_WORDS; return false if it is and true otherwise.
 *
 public boolean validTerm(String term){
 if (term.isEmpty())
 return false;
 if (term.length() <= 1)
 return false;
 int index = Arrays.binarySearch(Util.STOP_WORDS, term,null);
 return index >= 0; 
 }
 
 /**
 * Return true if the IndexContains the given term and some pages
 * associated with it and false otherwise.
 *
 public boolean containsTerm(String term){
 return true;
 
 
 
 
 
 }
 
 /**
 * Return a list of the pages associated with the given term.If
 * there are no pages associated with the given term, return and
 * empty list.
 *
 public List<String> getPagesWithTerm(String term){
 
 
 throw new RuntimeException();
 
 
 }
 
 /**
 * Add the given term found in the given page to the index.  If the
 * term is not valid as per the validTerm() method, do not add it
 * and return false.  Valid terms should be added along with the
 * page on which they occurred to an IndexEntry.  Return true if the
 * term is new to the index or if the term exists but the page is
 * new for that term.  Otherwise return false.
 *
 public boolean addTermAndPage(String term, String page){
 
 
 throw new RuntimeException();
 
 
 }
 
 /**
 * For each page in the given crawler's foundPageList(), open and
 * parse the page using the JSoup library.  Examine the body text of
 * the page which is a string.  Use a Scanner on this string and set
 * the delimiter to
 * 
 *     scan.useDelimiter("(\\p{Space}|\\p{Punct}|\\xA0)+");
 * 
 * which will parse through words on the page skipping most
 * punctuation. Add this page to the entries in the index associated
 * with each term in the body. Individual pages may generate
 * exceptions while reading which the this method should ignore and
 * continue to the next page. This may happen for the pages which
 * use HTML frames.
 *
 public void addCrawledPages(Crawler crawler){
 try {
 for (int i = 0; i < crawler.foundPagesList().size(); i++){
 File input = new File(crawler.foundPagesList().get(i));
 Document doc = Jsoup.parse(input, "UTF-8");
 String text = doc.body().text();
 Scanner scan = new Scanner(new File(crawler.foundPagesList().get(i)));
 scan.useDelimiter("(\\p{Space}|\\p{Punct}|\\xA0)+");
 ///not done yet
 }
 }
 catch(Exception e){
 
 }
 }
 
 /**
 * Find the intersection (common elements) of x and y. Assume that x and
 * y are sorted.  Use this fact to efficiently build up a list of the
 * common elements with a single loop through the lists x and y.
 *
 public static List<String> intersectionOfSorted(List<String> x, List<String> y){
 
 
 throw new RuntimeException();
 
 
 }
 
 /**
 * Return a list of pages in the index which match the given
 * query. The query may be several space-separated words such as
 * "robotics artificial intelligence".  These should be separated
 * and used to retrieve lists of pages matching the words. Make use
 * of the insertionOfSorted(x,y) method to efficiently combine lists
 * of pages repeatedly in a loop to produce the end results.
 *
 public List<String> query(String queryString){
 
 
 throw new RuntimeException();
 
 
 }
 
 /**
 * Optional main method for your own testing.
 *
 public static void main(String args[]){
 return;
 } 
 
 }*/