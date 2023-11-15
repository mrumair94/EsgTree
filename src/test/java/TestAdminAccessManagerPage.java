import Tests.AdminAccessManagerPage;
import Tests.Login;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TestAdminAccessManagerPage extends TestBase {

    private AdminAccessManagerPage adminAccessManagerPage;
    private Login login;
    private AdminAccessManagerPage getAdminAccessManagerPage() {
        if (this.adminAccessManagerPage == null) {
            this.adminAccessManagerPage = new AdminAccessManagerPage();
        }
        return this.adminAccessManagerPage;
    }
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
   // @Test(priority = 3)

    public void testAddNewContractBtn() throws InterruptedException {
        String contractPageTitle= getAdminAccessManagerPage().clickAddNewContract();
        String currentTitle =getDriver().getTitle();
        Assert.assertEquals(currentTitle,contractPageTitle,"Page Title Does not Match");
        System.out.println("Add new Contract Page "+currentTitle);

    }

    //Check if the "Add new Contract" Button is clickable
   // @Test(priority = 4)
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

   // @Test(priority = 5)
    public void testSearchWithNoDataFound(){
        getDriver().navigate().refresh();
        bodyLoadWait();
        getAdminAccessManagerPage().searchField("abababababbaba");
        dataLoaderWait();
       String result = getAdminAccessManagerPage().checkIfNoDataFound();
       Assert.assertNotNull(result);
    }

    //Verify that user is able to search newly invited GP company
    @Test(priority = 6)
    public void testNewlyInvitedGPCompany() throws InterruptedException {
       // getDriver().navigate().refresh();
        Thread.sleep(1500);
      getAdminAccessManagerPage().itemsPerPage100();
        Thread.sleep(1000);
      dataLoaderWait();
     String companyName = getAdminAccessManagerPage().getCompanyNameByStatus("Pending");
        System.out.println("company Name with pending Status: "+ companyName);
        Assert.assertNotNull(companyName);
    }

    //Check if the Registration link is working
    @Test(priority = 7, dependsOnMethods = "testNewlyInvitedGPCompany")
    public void testRegistrationlinkBtn() throws IOException, InterruptedException, UnsupportedFlavorException {
       // bodyLoadWait();
   getAdminAccessManagerPage().clickCopyLinkButton();
        Thread.sleep(1500);
        clicOKOnSuccess();
        Thread.sleep(1500);
        getlogin().logout();
        bodyLoadWait();
        getAdminAccessManagerPage().openLink();
        String currentPage = getDriver().getTitle();
        Thread.sleep(1000);
        String expectedTitle ="contract-information";
        //Thread.sleep(1500);
       //clicOKOnSuccess();
       // System.out.println("Copied Link: "+copiedLink);
       // System.out.println(pastLink);
        Assert.assertEquals(currentPage,expectedTitle);

    }



}
