import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.io.*;
import java.util.*;

/**
 * An implementation of a Crawler which uses recursion to chase links
 * and visit all files linked to the start point.
 */
public class RecursiveCrawler extends Crawler{
  
  /**
   *Create an empty crawler
   */
  public RecursiveCrawler(){
    super();
  }
  
  /**
   * Implementation of crawling.  Visit the given page wich should be
   * valid.  Parse its contents using library functions in the JSoup
   * library.  Examine all links on the page. Any valid page that
   * exists and is unvisited should be visited recursively. By the
   * time crawl(..)  finishes, all pages that can be reached from the
   * start point should be returned by a call to the foundPagesXX()
   * methods.
   * 
   * Psuedo Code
   *  METHOD crawl(PAGE){
   *    add PAGE to the FOUND pages
   *    create a jsoup DOCUMENT from PAGE
   *    extract a list of LINKS from the DOCUMENT
   *     for every ITEM in the LINKS {
   *       extract the LINKHREF from the ITEM
   *       if LINKHREF is not a valid page, add it to the SKIPPED pages
   *          and move on to the next item
   *       use the Util.relativeFileName(..) to get the LINKFILE
   *       if LINKFILE is already in the FOUND or SKIPPED pages
   *          move on to the next item
   *       if LINKFILE does not exist, add it to the SKIPPED pages
   *          and move on to the next item
   *       recursively visit LINKFILE as it is a valid page
   */
  public void crawl(String pageFileName){
    try{
      //add PAGE to the FOUND pages
      foundPages.add(pageFileName);
      File input = new File(pageFileName);
      //create a jsoup DOCUMENT from PAGE
      Document doc = Jsoup.parse(input, "UTF-8");
      //extract a list of LINKS from the DOCUMENT
      ArrayList<Element> links = doc.select("a[href]");
      //for every ITEM in the LINKS
      for(Element element : links){
        //extract the LINKHREF from the ITEM
        String linkedPage = element.attr("href");
        //if LINKHREF is not a valid page
        if (!(validPageLink(linkedPage))){
          //add it to the SKIPPED pages and move on to the next item
          skippedPages.add(linkedPage);
          continue;
        }
        //use the Util.relativeFileName(..) to get the LINKFILE
        String linkFile = Util.relativeFileName(pageFileName,linkedPage);
        //if LINKFILE is already in the FOUND or SKIPPED pages and move on to the next item
        if (foundPages.contains(linkFile) || skippedPages.contains(linkFile)){
          continue;
        }
        //if LINKFILE does not exist
        File inp = new File(linkFile);
        if (!(inp.exists())){
          //add it to the SKIPPED pages and move on to the next item
          skippedPages.add(linkFile);
          continue;
        }
        //recursively visit LINKFILE as it is a valid page
        crawl(linkFile);
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