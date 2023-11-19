package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {
    public static Faker faker = new Faker();

   //  String homePage =  "https://esgtree.com/";
   //"https://esgtree-wp.septemsystems.com/";
   //"https://dev-esg.esgtree.com/login";
   //"https://esgtree.com/";
    public static WebDriver driver;
    public JavascriptExecutor js= (JavascriptExecutor) getDriver();

    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("Driver was Empty");
            WebDriverManager.chromedriver().setup();
            // Set up WebDriver and any necessary configurations
            driver = new ChromeDriver();
        }
        return driver;
    }
    protected void quitDriver() {
        if (this.driver != null) {
            this.driver.quit();
            this.driver = null;
        }
    }
    public void bodyLoadWait() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
      //  System.out.println("waiting for the body");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }
    public void elementToBeClickabledWait(By elem) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
       // System.out.println("waiting for the Element");
        wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
        wait.until(ExpectedConditions.elementToBeClickable(elem));
    }
    public void clicOKOnSuccess() throws InterruptedException {
        By okBtn = new By.ByXPath("//button[@type='button'][contains(text(),'OK')]");
        elementToBeClickabledWait(okBtn);
        WebElement ok = getDriver().findElement(okBtn);
        if(ok.isDisplayed()||ok.isEnabled()) {
            // Thread.sleep(2000);
            this.js.executeScript("arguments[0].scrollIntoView(true);", ok);
            //  Thread.sleep(2000);
            this.js.executeScript("arguments[0].click();", ok);
            //  Thread.sleep(2000);
            bodyLoadWait();
        }
    }
    public void dataLoaderWait(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        By loaderBy = new By.ByCssSelector("#child-data-datatable > tbody > tr > td > div > div");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderBy));
    }
    public void pageLoaderWait(){
        // Define the CSS selector for the loader element
        By loaderSelector = new By.ByXPath("//*[@id=\"topbar\"]/div/div/section/div[2]/div");
        System.out.println("Page Loader");
        // Wait for the loader element to become invisible
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderSelector));
    }
    /*public void testReportSetup() {
        if (extentReports == null) {
            String reportPath = "test-output/extent-report.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            extentReports = new ExtentReports();
             // Customize the report name
            sparkReporter.config().setDocumentTitle("My Custom Test Report");
            // Customize the report title (displayed on the top of the report)
            sparkReporter.config().setReportName("ESG Tree Automation Test Report");
            // Set a custom logo for the report
            sparkReporter.config().setTheme(Theme.STANDARD); // Choose the desired theme
            extentReports.attachReporter(sparkReporter);
        }
    }*/
  /*  protected void logInfo(String message) {
        if (extentReports != null) {
            extentReports.createTest(Thread.currentThread().getStackTrace()[2].getMethodName()).log(Status.INFO, message);
        }
    }*/
  //  public static class login extends TestBase{
    /*
        @Test(priority = 1)
        public void loginaction(){
            driver.findElement(By.id("emailaddress")).sendKeys("admin@esgtree.com");
            driver.findElement(By.id("password")).sendKeys("123123");
            driver.findElement(By.tagName("button")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='account-user-name']")));
            String expected = "contract-manager";
            String actual = driver.getTitle();
            Assert.assertEquals(actual,expected,"Login Failed");
            System.out.println(actual);
        }
       // @Test (priority = 2)
        public void loginSuccess(){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='account-user-name']")));
            String expected = "contract-manager";
            String actual = driver.getTitle();
            Assert.assertEquals(actual,expected,"Login Failed");
            System.out.println(actual);
        }
        @Test (priority = 2)
        public void openAddNewContract(){
            driver.findElement(By.xpath("//button[contains(text(),'Add New Contract')]")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            String expected = "contract-form";
            String actual = driver.getTitle();
            Assert.assertEquals(actual,expected,"Navigation Failed");
            System.out.println(actual);
        }
        @Test(priority = 3)
        public void fillNewContractForm() throws InterruptedException {
            driver.findElement(By.id("validationCustom01")).sendKeys("Test"+ faker.company().industry());
            driver.findElement(By.id("validationCustom02")).sendKeys("Test"+ faker.name().firstName());
            driver.findElement(By.id("validationCustom03")).sendKeys("Test"+ faker.name().firstName()+"@gmail.com");
            driver.findElement(By.id("validationCustom05")).sendKeys("1");
            driver.findElement(By.id("validationCustom06")).sendKeys("3");
            WebElement companyNoRadioButton = driver.findElement(By.id("customRadio1"));
            //WebElement companyNoRadioButtonLabel = driver.findElement(By.cssSelector("label[for='customRadio1']"));
           // companyNoRadioButtonLabel.click();
            Boolean is_selected;
            // Click on the "NO" radio button for Enable Fund(s) Manager
            //Thread.sleep(3000);
           *//* WebElement radio_button = driver.findElement(By.id("bold-no"));
            WebElement label = driver.findElement(By.xpath("//label[@for='bold-yes']"));*//*
            WebElement inputElement = driver.findElement(By.id("bold-no"));
            // Find the label element associated with the input element
            WebElement labelElement = driver.findElement(By.cssSelector("label[for='bold-no']"));
            // Create a WebElement object that includes both the input and label elements
            *//*WebElement combinedElement = inputElement;
            inputElement.click();*//*
            WebElement element = driver.findElement(By.xpath("//input[@id='bold-no']"));
            // Get the X and Y coordinates where you want to click
            int xCoordinate = 559;
            int yCoordinate = 426;
            // Use JavascriptExecutor to click at the specified coordinates
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", element);
        }
        */
//  // public static WebDriver driver;
//   public Faker faker = new Faker();
//    String homePage = "https://esgtree-wp.septemsystems.com/";
//            //"https://dev-esg.esgtree.com/login";;
//        //"https://www.esgtree.com";
//            //"https://esgtree-wp.septemsystems.com/";
}