package Tests;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class LoginCopied extends TestBase{
       // ExcelDataHandler dataHandler = new ExcelDataHandler("src/main/resources/data.xlsx", "Sheet1");
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
    public LoginCopied() throws IOException {
    }
   // @BeforeClass
        public void setup() {
            getDriver().manage().window().maximize();
            // getDriver().get(this.homePage);
            getDriver().get("https://dev-esg.esgtree.com/contract-information?id=U2FsdGVkX19iTfkXEU%20eg0y3qKssDiCJba7PXwRPrNc%3D");
        }
        //@Test(priority = 1)
        public void loginaction() {
            bodyLoadWait();
            getDriver().findElement(By.id("emailaddress")).sendKeys("admin@esgtree.com");
            getDriver().findElement(By.id("password")).sendKeys("123123");
            getDriver().findElement(By.tagName("button")).click();
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='account-user-name']")));
            String expected = "contract-manager";
            String actual = getDriver().getTitle();
            Assert.assertEquals(actual,expected,"Login Failed");
            System.out.println(actual);
        }

        // @Test(priority = 2)
        public void logout(){

            bodyLoadWait();
            By admin = new By.ByXPath("//li/a/span/span[contains(text(),'Administrator')]");
            elementToBeClickabledWait(admin);
            getDriver().findElement(admin).click();
            elementToBeClickabledWait(admin);
            By logout = new By.ByXPath("//a/span[contains(text(),'Logout')]");
            getDriver().findElement(logout).click();
            bodyLoadWait();
            By emailField = By.id("emailaddress");
            elementToBeClickabledWait(emailField);


        }
        // @Test (priority = 2)
        public void loginSuccess(){
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='account-user-name']")));
            String expected = "contract-manager";
            String actual = getDriver().getTitle();
            Assert.assertEquals(actual,expected,"Login Failed");
            System.out.println(actual);
        }
    //    @Test (priority = 2)
        public void openAddNewContract(){
            getDriver().findElement(By.xpath("//button[contains(text(),'Add New Contract')]")).click();
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            String expected = "contract-form";
            String actual = getDriver().getTitle();
            Assert.assertEquals(actual,expected,"Navigation Failed");
            System.out.println(actual);
        }
      //  @Test(priority = 3)
        public void fillNewContractForm() throws InterruptedException {
            //write date to excel sheet
            String compName="SepAutoTest " + faker.company().industry();;
            String emailID="Automaiton"+ faker.name().firstName()+"@gmail.com";
            Thread.sleep(3000);
         //   dataHandler.writeToExcel("Company Name",compName);
            Thread.sleep(3000);
          //  dataHandler.writeToExcel("Email ID",emailID);
            //filling the form
            getDriver().findElement(By.id("validationCustom01")).sendKeys(compName);
            getDriver().findElement(By.id("validationCustom02")).sendKeys("SepAuto"+ faker.name().firstName());
            getDriver().findElement(By.id("validationCustom03")).sendKeys(emailID);
            getDriver().findElement(By.id("validationCustom05")).sendKeys("1");
            getDriver().findElement(By.id("validationCustom06")).sendKeys("3");
            WebElement companyNoRadioButton = getDriver().findElement(By.id("customRadio1"));
            WebElement element = getDriver().findElement(By.id("bold-no")); // Replace with your element locator
            try {
                // Scroll the element into view
                js.executeScript("arguments[0].scrollIntoView(true);", element);

                // Wait for a short time to ensure any animations or transitions are complete
                Thread.sleep(1000); // Adjust the sleep time as needed

                // Click the element using JavaScript
                js.executeScript("arguments[0].click();", element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getDriver().findElement(By.xpath("//button[@type='submit'][contains(text(),'Send Invite')]")).click();
        }
      //  @Test(priority = 4)
        public void succfullySubmitted() throws InterruptedException {
            By okBtn = new By.ByXPath("//button[@type='button'][contains(text(),'OK')]");
            elementToBeClickabledWait(okBtn);
            String expected = "Success";
            String actual = getDriver().findElement(By.xpath("//h2[contains(text(),'Success')]")).getText();
            Assert.assertEquals(expected, actual);
            WebElement ok = getDriver().findElement(okBtn);
            js.executeScript("arguments[0].scrollIntoView(true);", ok);
            ok.click();
        }
       // @Test(priority = 5)
        public void searchingCompany() throws InterruptedException, IOException, UnsupportedFlavorException {
            //Search Company Name

          //  String companyName = dataHandler.searchCompanyForVerification("Company Name");
         //   getDriver().findElement(By.xpath("//section/div/div/div[1]/div/div[1]/div/div/form/div/input")).sendKeys(companyName);
            Thread.sleep(2000);

            //Click on copy button
            By CopyBtn = new By.ByXPath ("//td/a[@title='copy registration link']");
            elementToBeClickabledWait(CopyBtn);
            WebElement link= getDriver().findElement(CopyBtn);
            js.executeScript("arguments[0].scrollIntoView(true);", link);
            js.executeScript("arguments[0].click();", link);

            Thread.sleep(4000);
            //Click on the success copied popup OK button
            By okBtn = new By.ByXPath("//button[@type='button'][contains(text(),'OK')]");
            WebElement ok = getDriver().findElement(okBtn);
            //Click to copy the registration
            Thread.sleep(2000);
            By pending = new By.ByXPath("//tr/td/span[contains(text(),'Pending')]");
            elementToBeClickabledWait(pending);
            if(getDriver().findElement(pending).isDisplayed()) {
                js.executeScript("arguments[0].scrollIntoView(true);", ok);
                Thread.sleep(2000);
                js.executeScript("arguments[0].click();", ok);
                Thread.sleep(2000);
                loginSuccess();
                bodyLoadWait();
            }
            Thread.sleep(2000);
            //get link which is copied in the Clipboard
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);
            String copiedLink = (String)contents.getTransferData(DataFlavor.stringFlavor);
            System.out.println("Copied Link: "+copiedLink);
            // String copiedLink = Keys.CONTROL + "v";
            System.out.println(copiedLink);
            Thread.sleep(2000);
            logout();
            Thread.sleep(2000);
            ((JavascriptExecutor)getDriver()).executeScript("window.open()");
            ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
            getDriver().switchTo().window(tabs.get(tabs.size()-1));
            Thread.sleep(2000);
            getDriver().get(copiedLink);
            Thread.sleep(2000);
            bodyLoadWait();
            bodyLoadWait();
        }
       // @Test(priority = 6)
        public void verifyCompany() throws InterruptedException {
            Thread.sleep(3000);
            By nextBtn = new By.ByLinkText("Next");
            getDriver().findElement(nextBtn).click();
            Thread.sleep(3000);
            WebElement dropdownElement = getDriver().findElement(By.name("Investor type"));
            // Create a Select object
            js.executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
            Select dropdown = new Select(dropdownElement);
            dropdown.selectByVisibleText("Endowment");
            Thread.sleep(3000);

            Thread.sleep(1500);
            By addressPath = new By.ById("address");
            WebElement addressInput = getDriver().findElement(addressPath);
            Thread.sleep(2000);
            addressInput.sendKeys("Lahore");
            Thread.sleep(2000);
            addressInput.sendKeys(Keys.chord(Keys.ARROW_DOWN));
            Thread.sleep(2000);
            addressInput.sendKeys(Keys.chord(Keys.ENTER));

            Thread.sleep(2000);
            String address = addressInput.getAttribute("value");
            System.out.println(address);
            Thread.sleep(2000);
            By investmentPath = new By.ById("investment");
            WebElement investmentInput = getDriver().findElement(investmentPath);
            investmentInput.sendKeys(address);

            int lowerBound = 1000;
            int upperBound = 24000000;
            // Initialize the Random object
            Random random = new Random();
            // Generate a random number within the range
            String randomNumber = String.valueOf(random.nextInt(upperBound - lowerBound + 1) + lowerBound);
            // Print the random number
            System.out.println("Random Number: " + randomNumber);
            Thread.sleep(1500);
            By assetstPath = new By.ByName("Assets Under Management");
            WebElement assetsInput = getDriver().findElement(assetstPath);
            assetsInput.sendKeys(randomNumber);

            By passwordPath = new By.ById("password");
            WebElement passwordInput = getDriver().findElement(passwordPath);
            passwordInput.sendKeys("Adam123!");

            By confirm_passwordPath = new By.ById("confirm_password");
            WebElement confirm_passwordInput = getDriver().findElement(confirm_passwordPath);
            confirm_passwordInput.sendKeys("Adam123!");

            By createBtnPath = new By.ByXPath("//button[@type='submit']");
            WebElement createBtn = getDriver().findElement(createBtnPath);
            createBtn.click();

        }
        public void bodyLoadWait() {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            System.out.println("waiting for the body");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        }
        public void elementToBeClickabledWait(By elem) {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            System.out.println("waiting for the body");
            wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
            wait.until(ExpectedConditions.elementToBeClickable(elem));
        }
    }