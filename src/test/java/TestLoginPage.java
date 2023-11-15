import Tests.Login;
import Tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class TestLoginPage extends TestBase {
    private Login login;
    public Login getlogin(){
        if (this.login == null){
            this.login = new Login();
        }
        return this.login;
    }

    @Test(priority = 0)
    public void Setup(){
        getDriver().manage().window().maximize();
        getDriver().get("https://dev-esg.esgtree.com/login");
        bodyLoadWait();
        System.out.println("web Page Loaded Successfully");
    }


     @Test(priority = 1)
    public void testLoginUserDeletedError(){
        String errorMsg = getlogin().userDeletedError("Cory@gmail.com","Admin123!");
      //  WebElement popup = getDriver().findElement(By.cssSelector(".swal2-content #swal2-content"));
     //  String errorMsg = getlogin().loginUserDeletedErrorCheck("abcde@abcde.co","123123");
       String currentErrorMsg = "Error: There is no user record corresponding to this identifier. The user may have been deleted.";
       Assert.assertEquals(currentErrorMsg,errorMsg,"Test Login User Deleted Error:Failed");
         System.out.println("Test Login User Deleted Error : Pass");
    }

    @Test(priority = 2)
    public void testWrongUserPasswordError(){
         // getlogin().clickOKButton();

        String errorMsg = getlogin().wrongUserPasswordError("admin@esgtree.com","2321WrongPassword!");
       // WebElement popup = getDriver().findElement(By.cssSelector(".swal2-content #swal2-content"));
        String currentErrorMsg = "Error: The password is invalid or the user does not have a password.";
        Assert.assertEquals(currentErrorMsg,errorMsg,"Test Login User Deleted Error:Failed");
        System.out.println("Test Wrong User Error : Pass");
    }
    @Test(priority = 3)
    public void testLoginAccessDeniedError() throws InterruptedException {
      //  getlogin().clickOKButton();
        String ErrorMsg = getlogin().accessDeniedError("Hector@gmail.com","123548");
        WebElement popup = getDriver().findElement(By.cssSelector(".swal2-content #swal2-content"));
        String currentErrorMsg = "Error: Access to this account has been temporarily disabled due to many failed login attempts. You can immediately restore it by resetting your password or you can try again later.";
        Assert.assertEquals(currentErrorMsg,ErrorMsg, "Access Denied Test Failed");
        System.out.println("Test Login Access Denied Error: Pass");
    }
    @Test(priority = 4)
    public void testLogin() throws InterruptedException {
      //  getlogin().clickOKButton();
        getlogin().loginAsAdmin();
        Boolean loginCheck = getlogin().loginCheck();
        Assert.assertTrue(loginCheck);
    }
   @Test(priority =5, dependsOnMethods = "testLogin")
    public void testLogout(){
        Boolean logoutCheck = getlogin().logout();
        Assert.assertTrue(logoutCheck);
    }

    @AfterClass
    public void tearDown(){
        getDriver().quit();

    }
}
