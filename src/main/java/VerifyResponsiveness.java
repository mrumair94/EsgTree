import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.List;



//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.net.HttpURLConnection;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//
//
//    @BeforeClass
//    public void setup() {
//        getDriver().manage().window().maximize();
//        getDriver().get(this.homePage);
//        bodyLoadWait();
//    }
//
//    //  @Test (priority = 1)
//    public void verifyHomePageLinks() {
//        checkBrokenLinks();
//    }
//
//    @Test(priority = 2)
//    //@Async
//    public void verifyProductPageLinks() {
//        getDriver().findElement(By.linkText("Product")).click();
//        bodyLoadWait();
//        links = getDriver().findElements(By.tagName("a"));
//        // Create an ExecutorService with a fixed number of threads (adjust as needed)
//      //  int numThreads = 2; // You can change this to control the number of parallel threads
//       // ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
//
//        for (WebElement link : links) {
//            String url = link.getAttribute("href");
//           // executorService.execute(() ->
//                    checkLink(url);
//        }
//        // Shutdown the ExecutorService and wait for all tasks to complete
//       // executorService.shutdown();
//       // while (!executorService.isTerminated()) {
//            // Wait for all tasks to complete
//        //}
//    }
//
//
//    @Test(priority = 3)
//    public void verifyAboutPageLinks() {
//        getDriver().findElement(By.linkText("About")).click();
//        bodyLoadWait();
//        links = getDriver().findElements(By.tagName("a"));
//        // Create an ExecutorService with a fixed number of threads (adjust as needed)
//       // int numThreads = 5; // You can change this to control the number of parallel threads
//       // ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
//        for (WebElement link : links) {
//            String url = link.getAttribute("href");
//           // executorService.execute(() -> {
//                checkLink(url);
//            //});
//        }
//    }
//
//    List<WebElement> links;
//    //@Test
//    public void checkBrokenLinks() {
//
//
//    }
//    public void checkLink(String url) {
//        HttpURLConnection huc = null;
//        int respCode = 200;
//        ArrayList<String> anotherLinkArray = new ArrayList<>();
//        ArrayList<String> emptyLinkArray = new ArrayList<>();
//        ArrayList<String> validLinkArray = new ArrayList<>();
//        ArrayList<String> inValidLinkArray = new ArrayList<>();
//        int emptyLinks = 0;
//        int anotherdomainLink = 0;
//        int invalidLink = 0;
//        int validLink = 0;
//        if (url == null || url.isEmpty()) {
//            String elementXPath = "";
//            elementXPath = this.getElementXPath((ChromeDriver) getDriver(), (WebElement) links);
//            System.err.println("Empty or null URL found for element with XPath: " + elementXPath);
//            emptyLinks += 1;
//        } else {
//            if (!url.startsWith(homePage)) {
//                //System.out.println(this.url + "URL belongs to another domain, skipping it.");
//                anotherdomainLink += 1;
//                anotherLinkArray.add(url);
//            }
//            try {
//                HttpResponse<String> response = Unirest.get(url).asStringAsync().get();
//               /* huc = (HttpURLConnection) (new URL(url).openConnection());
//                huc.setRequestMethod("HEAD");
//                huc.connect();
//                respCode = huc.getResponseCode();*/
//                respCode=response.getStatus();
//                if (respCode >= 400) {
//                    System.out.println(url + " is a broken link");
//                    invalidLink += 1;
//                    inValidLinkArray.add(url);
//                } else {
//                    System.out.println(url + " is a valid link");
//                    validLink += 1;
//                    // validLinkArray.add(url);
//                }
//
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        int total = emptyLinks + invalidLink + validLink + anotherdomainLink;
//        System.out.println("Total Links: " + total);
//        System.out.println("" + emptyLinkArray);
//
//        System.out.println("Total Empty Links: " + emptyLinks);
//        System.out.println("" + emptyLinkArray);
//
//        System.out.println("\n");
//        System.out.println("Total Invalid Links: " + invalidLink);
//        System.out.println("" + inValidLinkArray);
//
//        System.out.println("\n");
//        System.out.println("Total Valid Links: " + validLink);
//        System.out.println("" + validLinkArray);
//        System.out.println("\n");
//
//
//        System.out.println("Total Links Belongs to other domain: " + anotherdomainLink);
//        System.out.println("" + anotherLinkArray);
//        System.out.println("\n");
//    }
//    public void bodyLoadWait() {
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
//    }
//
//}



import org.openqa.selenium.By;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.Test;

        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.UnknownHostException;
        import java.time.Duration;
        import java.util.List;
        import java.util.concurrent.ForkJoinPool;
        import java.util.concurrent.RecursiveAction;
/*
public class VerifyResponsiveness extends Base {
    List<WebElement> links;
    */
/* ArrayList<String> anotherLinkArray = new ArrayList<>();
     ArrayList<String> emptyLinkArray = new ArrayList<>();
     ArrayList<String> validLinkArray = new ArrayList<>();
     ArrayList<String> inValidLinkArray = new ArrayList<>();*//*

    int emptyLinks = 0;
    int anotherdomainLink = 0;
    int invalidLink = 0;
    int validLink = 0;
    @BeforeClass
    public void setup() {
        getDriver().manage().window().maximize();
        getDriver().get(this.homePage);
        bodyLoadWait();
    }
*/
    // @Test(priority = 2)
   /* public void verifyProductPageLinks() {
        getDriver().findElement(By.linkText("Product")).click();
        bodyLoadWait();
      //  links = getDriver().findElements(By.tagName("a"));

        List<WebElement> links = getDriver().findElements(By.tagName("a"));
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            checkLink(url);
        }
    }*/
    // @Test(priority = 3)
   /* public void verifyAboutPageLinks() {
        getDriver().findElement(By.linkText("About")).click();
        bodyLoadWait();
        List<WebElement> links = getDriver().findElements(By.tagName("a"));
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            checkLink(url);
        }
        System.out.println(links.size());*/
   /*     getDriver().findElement(By.linkText("About")).click();
        bodyLoadWait();
        links = getDriver().findElements(By.tagName("a"));

        ForkJoinPool pool = new ForkJoinPool(); // Create a ForkJoinPool

        // Create a recursive action to check links in parallel
        RecursiveAction task = new RecursiveAction() {


            @Override
            protected void compute() {
                links.parallelStream().forEach(link -> {
                    String url = link.getAttribute("href");
                    checkLink(url);
                });
            }
        };*/

       /* int total = this.emptyLinks + this.invalidLink + this.validLink + this.anotherdomainLink;
        System.out.println("Total Links: " + total);

        System.out.println("\n");
        System.out.println("Total Invalid Links: " + this.invalidLink);
        System.out.println("" + inValidLinkArray);

        System.out.println("\n");
        System.out.println("Total Valid Links: " + this.validLink);
        System.out.println("" + validLinkArray);
        System.out.println("\n");


        System.out.println("Total Links Belongs to other domain: " + this.anotherdomainLink);
        System.out.println("" + anotherLinkArray);
        System.out.println("\n");*/

    // pool.invoke(task); // Invoke the recursive action in the pool
    //}

    // Rest of your code...
   /* @Test
    public void checkLink() {
        HttpURLConnection huc = null;
        int respCode = 200;
        // Find all <a> elements on the page
        List<WebElement> links = getDriver().findElements(By.tagName("a"));

        for (WebElement link : links) {
            String url = link.getAttribute("href");

            if (url == null || url.isEmpty()) {

                String elementXPath = getElementXPath((ChromeDriver) getDriver(), link);
                System.err.println("Empty or null URL found for element with XPath: " + elementXPath);

                this.emptyLinks++;
                return;
            } else {
                if (!url.startsWith(homePage)) {
                    System.out.println(url + "URL belongs to another domain, skipping it.");
                    this.anotherdomainLink++;
                    // this.anotherLinkArray.add(url);
                    return;

                }
                try {
                    huc = (HttpURLConnection) (new URL(url).openConnection());
                    huc.setRequestMethod("HEAD");
                    huc.connect();
                    respCode = huc.getResponseCode();
                    if (respCode == HttpURLConnection.HTTP_OK) {
                        System.out.println(url + " is a valid link");
                        return;
                    } else {
                        System.err.println(url + " is a broken link (Response Code: " + respCode + ")");
                        return;
                    }

                } catch (UnknownHostException e) {
                    System.err.println("Unknown host exception for URL: " + url);
                    // Handle the exception, e.g., print an error message or log it
                    return;
                } catch (Exception e) {
                    System.err.println("Exception while checking link: " + url);
                    e.printStackTrace(); // Print the stack trace for other exceptions
                    return;
                }
            }

        }
    }
    public void bodyLoadWait() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }
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


}*/
/****************************************************/
/*import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.List;

public class bVerifyBlogLinks extends Base {
        @BeforeClass
        public void setup() {
                getDriver().manage().window().maximize();
                getDriver().get(this.homePage);
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        }
        @Test(priority = 1)
        public void verifyProductPageLinks() throws InterruptedException {
                getDriver().findElement(By.linkText("Product")).click();
                // bodyLoadWait();
                // Thread.sleep(3000);
                checkLink();
                System.out.println("test:");
        }
        //@Test
        public void checkLink() {
                HttpURLConnection huc = null;
                int respCode = 200;
                System.out.println("test2");
                // Find all <a> elements on the page
                List<WebElement> links = getDriver().findElements(By.tagName("a"));
                System.out.println("Size of a: "+links.size());
                for (WebElement link : links) {
                        String url = link.getAttribute("href");
                        WebDriver driver = getDriver();
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        if (url == null || url.isEmpty()) {
                                // Get the XPath of the empty <a> element
                                String elementXPath = getElementXPath((ChromeDriver) getDriver(), link);
                                System.err.println("Empty or null URL found for element with XPath: " + elementXPath);
                                continue;
                        }
                        else {
                                if (!url.startsWith(homePage)) {
                                        System.out.println(url + "URL belongs to another domain, skipping it.");
                                        // this.anotherdomainLink++;
                                        // this.anotherLinkArray.add(url);
                                        continue;
                                }
                                try {
                                        huc = (HttpURLConnection) (new URL(url).openConnection());
                                        huc.setRequestMethod("HEAD");
                                        huc.connect();
                                        respCode = huc.getResponseCode();
                                        if (respCode == HttpURLConnection.HTTP_OK) {
                                                System.out.println(url + " is a valid link");

                                        } else {
                                                System.err.println(url + " is a broken link (Response Code: " + respCode + ")");

                                        }
                                } catch (UnknownHostException e) {
                                        System.err.println("Unknown host exception for URL: " + url);
                                        // Handle the exception, e.g., print an error message or log it

                                } catch (Exception e) {
                                        System.err.println("Exception while checking link: " + url);
                                        e.printStackTrace(); // Print the stack trace for other exceptions

                                }
                        }
                }


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
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        }
}*/
