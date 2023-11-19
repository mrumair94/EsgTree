import Tests.AdminAccessManagerPage;
import Tests.Login;
import Tests.TestBase;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TestAdminAccessManagerPage extends TestBase {

    private TestGPOnboarding testGPOnboarding;
    private AdminAccessManagerPage adminAccessManagerPage;
    private Login login;
    private AdminAccessManagerPage getAdminAccessManagerPage() {
        if (this.adminAccessManagerPage == null) {
            this.adminAccessManagerPage = new AdminAccessManagerPage();
        }
        return this.adminAccessManagerPage;
    }
    /*private TestGPOnboarding getTestGPOnboarding() {
        if (this.testGPOnboarding == null) {
            this.testGPOnboarding = new TestGPOnboarding();
        }
        return this.testGPOnboarding;
    }*/
    private Login getlogin() {
        if (this.login == null) {
            this.login = new Login();
        }
        return this.login;
    }

    @Test(priority = 1)
    public void setup(){
        getDriver().manage().window().maximize();
        getDriver().get("https://dev-esg.esgtree.com/login");
    }
    @Test(priority = 2)

    public void Login() throws InterruptedException {
        getlogin().loginAsAdmin();
        getlogin().loginCheck();
    }
     @Test(priority = 3)
    public void testAddNewContractBtn() throws InterruptedException {
        String contractPageTitle= getAdminAccessManagerPage().clickAddNewContract();
        String currentTitle =getDriver().getTitle();
        Assert.assertEquals(currentTitle,contractPageTitle,"Page Title Does not Match");
        System.out.println("Add new Contract Page "+currentTitle);
    }
    //Check if the "Add new Contract" Button is clickable
     @Test(priority = 4)
    public void testSearchingWithData() throws InterruptedException {
        bodyLoadWait();
        getDriver().navigate().back();
        bodyLoadWait();
        getAdminAccessManagerPage().searchField("SepAuto");
        dataLoaderWait();
        String companyName=  getAdminAccessManagerPage().searchResultsWithData();
        Assert.assertNotNull(companyName);
        System.out.println("Company Name: "+companyName);
    }
     @Test(priority = 5)
    public void testSearchWithNoDataFound() throws InterruptedException {
       // getDriver().navigate().refresh();
        bodyLoadWait();
         getAdminAccessManagerPage().searchField(Keys.chord(Keys.CONTROL,"a"));
        getAdminAccessManagerPage().searchField("abababababbaba");
        dataLoaderWait();
        String result = getAdminAccessManagerPage().checkIfNoDataFound();
         System.out.println(result);
        Assert.assertNotNull(result);
    }
    //Verify that user is able to search newly invited GP company
    @Test(priority = 6)
    public void testNewlyInvitedGPCompany() throws InterruptedException {
        getAdminAccessManagerPage().searchField(Keys.chord(Keys.CONTROL,"a"));
        getAdminAccessManagerPage().searchField(Keys.chord(Keys.BACK_SPACE));

        Thread.sleep(1500);
        getAdminAccessManagerPage().itemsPerPage100();
        Thread.sleep(1000);
        dataLoaderWait();
        String companyName = getAdminAccessManagerPage().getCompanyNameByStatus("Pending");
        System.out.println("company Name with pending Status: "+ companyName);
        Assert.assertNotNull(companyName);
    }
    @Test(priority = 7)
    public void testCopyLinkButton() throws IOException, InterruptedException, UnsupportedFlavorException {
        getAdminAccessManagerPage().itemsPerPage100();
        Thread.sleep(2000);
       Boolean clickCheck = getAdminAccessManagerPage().clickCopyLinkButton();
        Thread.sleep(2000);
        Assert.assertTrue(clickCheck,"Could not click on the Copy button");
               // System.out.println("Copied link: "+Keys.chord(Keys.CONTROL,"v"));
    }
    // dependsOnMethods = "testCopyLinkButton"
    @Test(priority = 8,dependsOnMethods = "testCopyLinkButton")
    public void testOpenLink() throws InterruptedException, IOException, UnsupportedFlavorException {
       String url = getAdminAccessManagerPage().copyLink();
       getlogin().logout();
       bodyLoadWait();
       getDriver().get(url);
    Thread.sleep(2000);
      String currentTitle = getDriver().getTitle();
      String expectedTitle =  "contract-information";
      Assert.assertEquals(currentTitle,expectedTitle);
        System.out.println("Opened the Link: "+currentTitle );
    }

}