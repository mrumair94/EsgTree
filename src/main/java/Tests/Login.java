package Tests;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Login extends TestBase{
    private GPonboardingPage gPonboardingPage;
   private AdminAccessManagerPage AdminAccessManagerPage;

   private GPonboardingPage getGPonboardingPage(){
       if (this.gPonboardingPage == null){
           this.gPonboardingPage = new GPonboardingPage();
       }
       return this.gPonboardingPage;
   }
    private AdminAccessManagerPage getAdminDBPage(){
        if (this.AdminAccessManagerPage == null){
            this.AdminAccessManagerPage = new AdminAccessManagerPage();
        }
        return this.AdminAccessManagerPage;
    }
       // getDriver().get("https://dev-esg.esgtree.com/login");
       // getDriver().get("https://dev-esg.esgtree.com/contract-information?id=U2FsdGVkX19iTfkXEU%20eg0y3qKssDiCJba7PXwRPrNc%3D");

    By emailFieldBy = new By.ById("emailaddress");
    WebElement emailField = getDriver().findElement(emailFieldBy);
    By passwordFieldBy = new By.ById("password");
    WebElement passwordField = getDriver().findElement(passwordFieldBy);

    public void stateElementReferenceHandle(By elementBy) {
        try {
            WebElement element = getDriver().findElement(elementBy);
            element.isEnabled();
        } catch (StaleElementReferenceException e) {
            // Element is stale, re-locate it
            WebElement element = getDriver().findElement(elementBy);
            element.isEnabled();
        }
    }
        public void loginFunction(String emailID, String password) {
                // Perform an action that triggers the StaleElementReferenceException
                By emailFieldBy = new By.ById("emailaddress");

                stateElementReferenceHandle(emailFieldBy);
                elementToBeClickabledWait(emailFieldBy);
                WebElement emailField = getDriver().findElement(emailFieldBy);
                 emailField.clear();
                emailField.sendKeys(emailID);

                By passwordFieldBy = new By.ById("password");
                stateElementReferenceHandle(passwordFieldBy);
                WebElement passwordField = getDriver().findElement(passwordFieldBy);
                passwordField.clear();
                passwordField.sendKeys(password);

                By loginBtnBy = By.tagName("button");
                stateElementReferenceHandle(loginBtnBy);
                elementToBeClickabledWait(loginBtnBy);
                getDriver().findElement(By.tagName("button")).click();
                bodyLoadWait();
            loginCheck();
        }
        public Boolean loginCheck(){
            if (waitForElement(By.xpath("//span[@class='account-user-name']"))) {
                System.out.println("Login was successful.");
                return true;
            } else if (waitForElement( By.cssSelector(".swal2-content #swal2-content"))) {
                System.out.println("Login failed.");
                return true;
            } else {
                System.out.println("Login status unknown or timed out.");
                return true;
            }
        }

    public static boolean waitForElement( By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.presenceOfElementLocated(by)) != null;
        } catch (Exception e) {
            return false;
        }
    }
        public String loginAsAdmin() throws InterruptedException {
            loginFunction("admin@esgtree.com", "123123" );
            By userBtn = new By.ByXPath("//span[@class='account-user-name']");
            elementToBeClickabledWait(userBtn);
        bodyLoadWait();
            Thread.sleep(1000);
        String dashboardPageTitle= getDriver().getTitle();
      //  loginCheck();
        return dashboardPageTitle;
    }
        public Boolean logout(){
            bodyLoadWait();
        By admin = new By.ByXPath("//li/a/span/span[contains(text(),'Administrator')]");
            elementToBeClickabledWait(admin);
        getDriver().findElement(admin).click();
            elementToBeClickabledWait(admin);
            By logout = new By.ByXPath("//a/span[contains(text(),'Logout')]");
            getDriver().findElement(logout).click();
            bodyLoadWait();
           By emailFieldby = By.id("emailaddress");
           elementToBeClickabledWait(emailFieldby);
           if(getDriver().findElement(emailFieldby).isDisplayed()){
               System.out.println("User is logout successfully");
               return true;
           }else {
               System.out.println("User logout is Unsuccessful");
               return false;
           }

        }
        public boolean loginSuccess(){
            bodyLoadWait();
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='account-user-name']")));
            String expected = "contract-manager";
            String actual = getDriver().getTitle();
            Assert.assertEquals(actual,expected,"Login Failed");
            System.out.println(actual);
            return true;
        }
       /* public void loginPasswordErrorCheck(String email, String password) {
            String errorWrongPassword = "Error: The password is invalid or the user does not have a password.";
            By error = new By.ByCssSelector(".swal2-content #swal2-content");
            loginFunction(email, password);
            bodyLoadWait();
            WebElement popup = getDriver().findElement(error);
            String errMsg = popup.getText();
            if (errMsg.contains(errorWrongPassword)) {
                System.out.println(errorWrongPassword + ": " + email);
                Assert.assertEquals(errMsg,errorWrongPassword);
            }
        }*/

    public String checkForErrors( ) {
      WebDriver driver = getDriver(); // Get the WebDriver instance from the Singleton
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the wait time as needed
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".swal2-content #swal2-content")));
            try {
                if (popup.isDisplayed()) {
                    String errorMessage = popup.getText();
                    return errorMessage;
                }
            } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
                // Handle the exception, e.g., log a message or continue without error
            }
        return null;
    }
    public void CheckLogin() {

        String errorMessage = checkForErrors();

        if (errorMessage != null) {
            System.out.println("Login Failed: " + errorMessage);
            Assert.fail("Login Failed: " + errorMessage);
        } else {
            WebDriver driver = getDriver(); // Get the WebDriver instance from the Singleton
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the wait time as needed
            By webElementLocator = By.xpath("//span[@class='account-user-name']");
            try {
                WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(webElementLocator));
                if (webElement != null) {
                    System.out.println("Login Passed");
                }
            } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
                System.out.println("Login Failed: Web element not found");
                Assert.fail("Login Failed: Web element not found");
            }
        }
    }

     public String userDeletedError(String Wrongemail,String password) {
      //  String errorUserDeleted = "Error: There is no user record corresponding to this identifier. The user may have been deleted.";
      //  By error = new By.ByCssSelector(".swal2-content #swal2-content");
        loginFunction(Wrongemail, password);
        bodyLoadWait();
         String errMsg = checkForErrors();
        /*if (errMsg.contains(errorUserDeleted)) {
            System.out.println(errorUserDeleted + ": " + Wrongemail);
            Assert.assertEquals(errMsg, errorUserDeleted);
        }*/
         clickOKButton();

         return  errMsg;
    }

    public String wrongUserPasswordError(String emailID, String password){
        loginFunction(emailID,password);
        String errMsg = checkForErrors();
        clickOKButton();
        return errMsg;
    }
   public String accessDeniedError(String email, String password){
       String errorAccessDenied = "Error: Access to this account has been temporarily disabled due to many failed login attempts. You can immediately restore it by resetting your password or you can try again later.";

       for (int i = 0; i < 5; i++) {
           loginFunction(email, password);
           String errmsg = checkForErrors();
          // By pupup = new By.ByCssSelector(".swal2-content #swal2-content");
          // waitForElement(pupup);
           WebElement popup = getDriver().findElement(By.cssSelector(".swal2-content #swal2-content"));
           if (!errmsg.contains(errorAccessDenied)) {
               By okButtonLocator = By.xpath("//button[contains(text(),'OK')]");
               elementToBeClickabledWait(okButtonLocator);
               WebElement okButton = driver.findElement(okButtonLocator);
               Actions act = new Actions(getDriver());
               act.moveToElement(okButton);
               okButton.click();
               emailField.clear();
               passwordField.clear();
               System.out.println("Trying to Login Again");
           } else {
               System.out.println("Test Successful");
               clickOKButton();
               return errmsg;
           }
       }
       clickOKButton();
       return null;
   }

    public void clickOKButton() {
        try{ By popup = new By.ByCssSelector(".swal2-content #swal2-content");
            waitForElement(popup);
            if(getDriver().findElements(popup).size()>0) {
                By okButtonLocator = By.xpath("//button[contains(text(),'OK')]");
                elementToBeClickabledWait(okButtonLocator);
                WebElement okButton = driver.findElement(okButtonLocator);
                Actions act = new Actions(getDriver());
                act.moveToElement(okButton);
                okButton.click();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}

