import java.util.*;

/**
 * Abstract class to crawl linked pages. Descendent crawlers should
 * override the crawl(page) method to initiate a crawllestof linked
 * pages. The class is intended only to work for locally stored files
 * for whic validPageLink(page) should return true while non-local and
 * web links will return false. This class makes use of the ArraySet
 * class.
 */
public abstract class Crawler {
  
  protected ArraySet<String> foundPages;  // Sets of pages that have been found
  protected ArraySet<String> skippedPages;// Sets of pages that have been skipped
  
  /**
   * Public constructor that creates an empty crawler
   */
  public Crawler(){
    this.foundPages = new ArraySet<String>();
    this.skippedPages = new ArraySet<String>();
  }
  
  /**
   * Initiate a crawl on the given page
   * Left abstract  so the hild classes would override this
   */
  public abstract void crawl(String pageFileName);
  
  /**
   * Return the unique pages that have been found so far
   */
  public List<String> foundPagesList(){
    return foundPages.asList();
  }
  
  /**
   * Return the unique pages that have been skipped so far
   */
  public List<String> skippedPagesList(){
    return skippedPages.asList();
  }
  
  /**
   * Return a string of pages that have been found so far
   * Each page is shown on its own line terminated with a \n
   */
  public String foundPagesString(){
    StringBuffer sb = new StringBuffer();
    for(int i=0; i<foundPages.size(); i++){
      sb.append(foundPages.asList().get(i).toString());
      sb.append("\n");
    }
    return sb.toString();
  }
  
  /**
   * Return a string of pages that have been skipped so far
   * Each page is shown on its own line terminated with a \n
   */
  public String skippedPagesString(){
    StringBuffer sb = new StringBuffer();
    for(int i=0; i<skippedPages.size(); i++){
      sb.append(skippedPages.asList().get(i).toString());
      sb.append("\n");
    }
    return sb.toString();
  }
  
  /**
   * Return true if the given pageFileName is valid and false otherwise
   * - Do not start with http://, https://, or file://
   * - Do not start with .jpg, .jpeg, or .png
   * - End with the extension .html or .HTML
   * Any file not meeting the above criteria should return false
   */
  public static boolean validPageLink(String pageFileName){
    if (pageFileName.startsWith("http://")){
      return false;
    }
    else if (pageFileName.startsWith("https://")){
      return false;
    }
    else if (pageFileName.startsWith("file://")){
      return false;
    }
    else if (pageFileName.endsWith("jpg")){
      return false;
    }
    else if (pageFileName.endsWith("png")){
      return false;
    }
    else if (pageFileName.endsWith("jpeg")){
      return false;
    }
    else if (pageFileName.endsWith(".html")){
      return true;
    }
    else if (pageFileName.endsWith(".HTML")){
      return true;
    }
    return false;
  }
  
}