package Tests;
import Utilities.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import Utilities.CustomTestListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

@Listeners(CustomTestListener.class)
public class VerifyBlogLinks extends TestBase {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
   // private ExtentReports extentReports;
  //  private ExtentTest test;
    // Initialize the ExtentTest

   /* private void initExtentTest() {
        extentReports = ExtentReportManager.getInstance();
        test = extentReports.createTest("VerifyBlogLinks", "Verify links on the page");

        *//*test = extentReports.createTest("VerifyBlogLinks", "Verify links on the page");
        test.log(Status.INFO, "<details><summary>Verify Blog Links</summary></details>");*//*
    }*/

    // Initialize the WebDriver
    private String homePage =  "https://esgtree.com/";
    @BeforeClass
    public void setup() {
        getDriver().manage().window().maximize();
        getDriver().get(this.homePage);
      //  testReportSetup();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
       // initExtentTest();
    }
  //  @AfterClass
    public void tearDown(){
      //  extentReports.flush();
        getDriver().quit();
    }
   // @BeforeMethod
   /* private void initExtentTest(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        result.getMethod().setDescription(methodName);

    }*/
    @Test(priority = 1, description="Verifying Home Page")
    public void verifyHomePageLinks() {
         bodyLoadWait();
        checkLink();
       // test = extentReports.createTest("Verify Home Page Links", "Verify links on the page");
       // test.log(Status.INFO, "<details><summary>Summary: Verifying Home Page</summary></details>");
    }
    @Test(priority = 2, description="Verifying Product Page")
    public void verifyProductPageLinks() throws InterruptedException {
        Thread.sleep(3000);
        WebElement productButton= getDriver().findElement(By.xpath("//header/div[1]/section[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/nav[1]/ul[1]/li[2]/a[1]"));
        jsExecutor.executeScript("arguments[0].scrollIntoView();", productButton);
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();",productButton);
        bodyLoadWait();
        System.out.println("Testing Product Page");
        checkLink();

    }
    @Test(priority = 3, description="Verifying About Page")
    public void verifyAboutPageLinks() throws InterruptedException {
        WebElement aboutButton = getDriver().findElement(By.xpath("//header/div[1]/section[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/nav[1]/ul[1]/li[4]/a[1]"));
        jsExecutor.executeScript("arguments[0].scrollIntoView();", aboutButton);
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();",aboutButton);
        bodyLoadWait();
        System.out.println("Testing About Page");
       checkLink();
    }
    @Test(priority = 4, description="Verifying Insights Page")
    public void verifyInsightsPageLinks() throws InterruptedException {
      WebElement insightsButton =  getDriver().findElement(By.xpath("//header/div[1]/section[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/nav[1]/ul[1]/li[5]/a[1]"));
        jsExecutor.executeScript("arguments[0].scrollIntoView();", insightsButton);
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();",insightsButton);
        bodyLoadWait();
        Thread.sleep(3000);
        By viewMore = new By.ByXPath("//span[contains(text(),'View More')]/parent::span/parent::a");
                //"//*[@id=\"page\"]/div[2]/section[6]/div/div/div/div/div/div[3]/a");
        System.out.println("Testing Insights Page");
        for(int i = 0; i < 3; i++) {
                WebElement viewMoreButton = getDriver().findElement(viewMore);
                jsExecutor.executeScript("arguments[0].scrollIntoView();", viewMoreButton);
                Thread.sleep(3000);
                jsExecutor.executeScript("arguments[0].click();",viewMoreButton);
            Thread.sleep(3000);
        }
        List<WebElement> blogs=getDriver().findElements(new By.ByLinkText("Read More"));
        System.out.println("Total Number of Blogs: "+ blogs.size());
        checkLink();
       // test = extentReports.createTest("Verify Insights Links", "Verify links on the page");
       // test.log(Status.INFO, "<details><summary>Summary: Verifying Insights Page</summary></details>");

    }
    @Test(dependsOnMethods = "verifyInsightsPageLinks", description="Verifying Insights Page")
    public void checkLinksOnEachBlog() throws InterruptedException, IOException {
        List<WebElement> readMoreList = getDriver().findElements(new By.ByLinkText("Read More"));
        System.out.println("Total Number of Blogs:" + readMoreList.size());
        By img = By.id("MTA0NDoxOTQ=-1");
        List<WebElement> firstImage = getDriver().findElements(img);
        for (WebElement button : readMoreList) {
            Thread.sleep(3000);
            jsExecutor.executeScript("arguments[0].scrollIntoView();", button);
            jsExecutor.executeScript("arguments[0].click();", button);
            System.out.println("********************");
            System.out.println("Testing of New Blog");
            System.out.println("********************");
            System.out.println("The blog Number: "+ readMoreList.indexOf(button)+1);
            Thread.sleep(2000);
            ArrayList<String> tab = new ArrayList<>(getDriver().getWindowHandles());
           // System.out.println("Size of tabs: "+tab.size());
            getDriver().switchTo().window(tab.get(1));
            Thread.sleep(3000);
            /*System.out.println("Title of the the Blog"+
                    getDriver().findElement(By.xpath("//*[@id=\"page\"]/div[2]/section[1]/div/div[1]/div/div[1]/div/h1")).getText()
            );*/
            checkLink();
            ((JavascriptExecutor) getDriver()).executeScript("window.close();");
            getDriver().switchTo().window(tab.get(0));
            System.out.println("********************");
            System.out.println("End of Blog");
             System.out.println("********************");
        }
        getDriver().quit();
    }
   public void checkLink() {
       ForkJoinPool pool = new ForkJoinPool(); // Create a ForkJoinPool
      // final HttpURLConnection[] huc = {null};
       //final int[] respCode = {200};
       AtomicInteger validLinks = new AtomicInteger(0);
       AtomicInteger otherDomain = new AtomicInteger(0);
       AtomicInteger brokenLinks = new AtomicInteger(0);
       AtomicInteger emptyLinks = new AtomicInteger(0);
       // Find all <a> elements on the page
       List<WebElement> links = getDriver().findElements(By.tagName("a"));
       // Create a recursive action to check links in parallel
       RecursiveAction task = new RecursiveAction() {
           @Override
           protected void compute() {
               links.parallelStream().forEach(link -> {
                   String url = link.getAttribute("href");
                   WebDriver driver = getDriver();
                   //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                   if (url == null || url.isEmpty()) {
                       // Get the XPath of the empty <a> element
                       String elementXPath = getElementXPath((ChromeDriver) getDriver(), link);
                       System.err.println("Empty or null URL found for element with XPath: " + elementXPath);
                       emptyLinks.getAndIncrement();
                       return;
                   } /*else if (!url.startsWith(homePage) || !url.startsWith("http://esgtree.com/") || !url.contains("https://app.esgtree.com/")) {
                       System.out.println(url + "URL belongs to another domain, skipping it.");
                       otherDomain.getAndIncrement();
                       return;
                   }*/ else
                       // int respCode = 0;
                       try {
                           HttpURLConnection huc = (HttpURLConnection) new URL(url).openConnection();
                           huc.setInstanceFollowRedirects(false); // Disable automatic redirects

                           huc.setRequestMethod("HEAD");
                           huc.connect();
                           int respCode = huc.getResponseCode();

                           if (respCode == HttpURLConnection.HTTP_OK) {
                               validLinks.getAndIncrement();
                           } else if (respCode >= 300 && respCode < 400 ||respCode==999 || respCode==403) {
                               // Handle redirects
                               System.out.println(url + " is a redirect (Response Code: " + respCode + ")");
                               String finalUrl = huc.getHeaderField("Location"); // Get the final destination URL
                               System.out.println("Final URL: " + finalUrl);

                               // Check the status of the final URL
                               HttpURLConnection finalHuc = (HttpURLConnection) new URL(finalUrl).openConnection();
                               finalHuc.setRequestMethod("HEAD");
                               finalHuc.connect();
                               int finalRespCode = finalHuc.getResponseCode();

                               if (finalRespCode == HttpURLConnection.HTTP_OK) {
                                   validLinks.getAndIncrement();
                               } else {
                                   System.err.println(finalUrl + " is a broken link (Response Code: " + finalRespCode + ")");
                                   brokenLinks.getAndIncrement();
                               }
                           } else {
                               System.err.println(url + " is a broken link (Response Code: " + respCode + ")");
                               brokenLinks.getAndIncrement();
                           }
                       } catch (IOException e) {
                           System.err.println("IOException while checking link: " + url);
                           // Handle other IOExceptions as needed
                       }

               });
           }
       };
       try {
           Thread.sleep(2000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       pool.invoke(task); // Invoke the recursive action in the pool
       if(links.size()>0) {
           System.out.println("Total Links: " + links.size());
           //test.log(Status.INFO, "Total Links: " + links.size());
               if (otherDomain.get() > 0) {
                   System.out.println("Other Domain links: " + otherDomain.get());
                 //  test.log(Status.INFO, "Other Domain links: " + otherDomain.get());
               }
               if (validLinks.get() > 0) {
                   System.out.println("valid links: " + validLinks.get());
                 //  test.log(Status.INFO, "Valid links: " + validLinks.get());
               }
               if (brokenLinks.get() > 0)
                   //  System.out.println("Invalid links: " + invalidLinks.get());
                   System.out.println("Broken links: " + brokenLinks.get());
           //test.log(Status.INFO, "Broken links: " + brokenLinks.get());
               if (emptyLinks.get() > 0) {
                   System.out.println("Empty links: " + emptyLinks.get());
                  // test.log(Status.INFO, "Empty links: " + emptyLinks.get());
               }
       }else
           System.out.println("no Liks found to verify");
      // test.log(Status.INFO, "No links found to verify");
   }
    // Function to get XPath of an element
    public static String getElementXPath(ChromeDriver driver, WebElement element) {
        return (String) driver.executeScript(
                "function getPathTo(element) {" +
                        "  var result = [];" +
                        "  var parent;" +
                        "  while (element && element.nodeType === 1 && element.tagName !== 'HTML') {" +
                        "    var tagName = element.tagName.toLowerCase();" +
                        "    var index = 1;" +
                        "    if (element.previousElementSibling) {" +
                        "      var prevSibling = element.previousElementSibling;" +
                        "      while (prevSibling) {" +
                        "        if (prevSibling.tagName === tagName) {" +
                        "          index++;" +
                        "        }" +
                        "        prevSibling = prevSibling.previousElementSibling;" +
                        "      }" +
                        "    }" +
                        "    if (index > 1) {" +
                        "      tagName += '[' + index + ']';" +
                        "    }" +
                        "    result.unshift(tagName);" +
                        "    element = element.parentElement;" +
                        "  }" +
                        "  return '/' + result.join('/');" +
                        "}" +
                        "return getPathTo(arguments[0]);", element);
    }
    public void bodyLoadWait() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        System.out.println("waiting for the body");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }
    public void elementToBeClickabledWait(By elem) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        System.out.println("waiting for the body");
        wait.until(ExpectedConditions.elementToBeClickable(elem));
    }
}