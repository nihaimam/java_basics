import java.io.*;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;

/**
 * An implementation of a crawler which does not use recursion
 * Use internal storage to track the pages that have yet to be visited
 */
public class IterativeCrawler extends Crawler{
  
  protected ArraySet<String> pendingPages;  // A list of pages that remain to be visited
  
  /**
   * Create an empty crawler
   */
  public IterativeCrawler(){
    pendingPages = new ArraySet<>();
  }
  
  /**
   * Master crawl method which
   * It will start at the given page and visit all reachable pages
   * This method calls crawlRemaining() method as its last action
   */
  public void crawl(String pageFileName){
    addPendingPage(pageFileName);
    crawlRemaining();
  }
  
  /**
   * A loop that crawls individual pages until there are no pending pages remaining
   */
  public void crawlRemaining(){
    while (pendingPagesSize() != 0){
      crawlNextPage();
    }
  }
  
  /**
   * Add the given page to the list of of pending pages to be visited
   * It is assumed that that page is valid and exists
   */
  public void addPendingPage(String pageFileName){
    pendingPages.add(pageFileName);
  }
  
  /**
   * Return the number of pages remaining to visit
   */
  public int pendingPagesSize(){
    return pendingPages.size();
  }
  
  /**
   * Return a string with each pending page to visit on its own line
   */
  public String pendingPagesString(){
    StringBuilder builder = new StringBuilder();
    for (String pages : pendingPages.asList()){
      builder.append(pages).append("\n");
    }
    return builder.toString();
  }
  
  /***/
  // Crawl a single page which is retrieved and removed from the list
  // of pending pages. Parse the retrieved page's contents using
  // library functions in the JSoup library. Examine all links on the
  // page. Any valid page that exists and is unvisited should be added
  // to the pending list. By the time crawlNextPage() finishes, one
  // additional page should be returned the foundPagesXX() methods
  // while the pending pages list may have grown substantially.
  //
  // See the spec for additional implementation details.
  public void crawlNextPage(){
    try {
      //select last file from pending pages to visit
      String lastPendingPage = pendingPages.asList().get(pendingPagesSize() - 1);
      //remove the last page from pending pages size
      pendingPages.asList().remove(pendingPagesSize() - 1);
      //successfully parsed this page, so add it to foundPages
      foundPages.add(lastPendingPage);
      //selected a file to parse, now create a File from it
      File input = new File(lastPendingPage);
      //parse the HTML file using Jsoup, a Document results
      Document doc = Jsoup.parse(input, "UTF-8");
      ArrayList<Element> links = doc.select("a[href]");
      for (Element element : links){
        String linkedPage = element.attr("href"); // Extract the linked page
        if (validPageLink(linkedPage)){
          //use the Util.relativeFileName(..) to get the LINKFILE
          String linkFile = Util.relativeFileName(lastPendingPage, linkedPage);
          //if LINKFILE is already in the FOUND or SKIPPED pages
          //then do nothing, otherwise, check if link file exists
          //if linkFile exists, add it to pending page, otherwise skip it
          if (!(foundPages.contains(linkFile)) && !(skippedPages.contains(linkFile))){
            if (new File(linkFile).exists()){
              addPendingPage(linkFile);
            }
            else {
              skippedPages.add(linkFile);
            }
          }
        }
        else {
          skippedPages.add(linkedPage);
        }
      }
    }
    catch (Exception e){
      return;
    }
  }
  
  /**
   * Optional main method for your own testing
   */
  public static void main(String args[]) throws Exception{
    return;
  }
  
}