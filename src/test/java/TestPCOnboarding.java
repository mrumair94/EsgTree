import Tests.GPAccessManagerPage;
import Tests.Login;
import Tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class TestPCOnboarding extends TestBase {
    private Login login;
    private GPAccessManagerPage gpAccessManagerPage;

    public Login getlogin(){
        if (this.login == null){
            this.login = new Login();
        }
        return this.login;
    }
    public GPAccessManagerPage getgpDashboardPage(){
        if (this.gpAccessManagerPage == null){
            this.gpAccessManagerPage = new GPAccessManagerPage();
        }
        return this.gpAccessManagerPage;
    }
    @Test(priority = 1)
    public void setup(){
        getDriver().manage().window().maximize();
        getDriver().get("https://dev-esg.esgtree.com/login");
    }
    @Test(priority = 2)
    public void loginasAdmin() throws InterruptedException {
        getlogin().loginAsAdmin();
        bodyLoadWait();
    }
    String gpEmail;
    @Test(priority = 3)
    public void searchAcceptedGPCompany(){
        gpEmail = getgpDashboardPage().searchGPCompany();

    }
    @Test(priority = 4)
    public void loginwithGPEmail(){
        getlogin().logout();
        bodyLoadWait();
        getlogin().loginFunction(gpEmail,"Adam123");
       // String errorWrongPassword = "Error: The password is invalid or the user does not have a password.";
        By errorPupupBy = new By.ByCssSelector(".swal2-content #swal2-content");
        By adminNameBy = new By.ByXPath( "//span[@class='account-user-name']");
        List<WebElement> errorPopupList = getDriver().findElements(errorPupupBy);
        List<WebElement> adminName = getDriver().findElements(adminNameBy);
        if(adminName.size()>0){
            getlogin().loginSuccess();
        }else if(errorPopupList.size()>0){
            By passwordFieldBy = new By.ById("password");
            getlogin().stateElementReferenceHandle(passwordFieldBy);
            WebElement passwordField = getDriver().findElement(passwordFieldBy);
            passwordField.clear();
            passwordField.sendKeys("Admin123!");
        }else
            System.out.println("login Failed");
    }
}
