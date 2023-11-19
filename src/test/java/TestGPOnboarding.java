import Tests.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TestGPOnboarding extends TestBase {
    private Login login;
   private InviteFormPage igpForm;
   private AdminAccessManagerPage adminAccessManagerPage;
   private GPonboardingPage gPonboardingPage;
   private  FrameWorkSelectionPupupModel frameWorkSelectionPupupModel;

    public Login getlogin(){
        if (this.login == null){
            this.login = new Login();
        }
        return this.login;
    }
    private InviteFormPage getInvitegpForm(){
        if (this.igpForm == null){
            this.igpForm = new InviteFormPage();
        }
        return this.igpForm;
    }
    private AdminAccessManagerPage getAdminBPPage(){
        if (this.adminAccessManagerPage == null){
            this.adminAccessManagerPage = new AdminAccessManagerPage();
        }
        return this.adminAccessManagerPage;
    }
    private GPonboardingPage getGPonboardingPage(){
        if (this.gPonboardingPage == null){
            this.gPonboardingPage = new GPonboardingPage();
        }
        return this.gPonboardingPage;
    }
    public FrameWorkSelectionPupupModel getFrameWorkSelectionPupupModel(){
        if (this.frameWorkSelectionPupupModel == null){
            this.frameWorkSelectionPupupModel = new FrameWorkSelectionPupupModel();
        }
        return this.frameWorkSelectionPupupModel;
    }


    @Test(priority = 0)
    public void Setup(){
        getDriver().manage().window().maximize();
        getDriver().get("https://dev-esg.esgtree.com/login");

    }
   @Test(priority = 1)
    public void testAdminLogin() throws InterruptedException {
        bodyLoadWait();
       String dashboardPageTitle = getlogin().loginAsAdmin();
        String currentPageTitle = getDriver().getTitle();
       Assert.assertEquals(currentPageTitle,dashboardPageTitle,"Current Page Title: " +currentPageTitle);
    }
//  @Test(priority = 2)
    public void testOpenContractFormPage() throws InterruptedException {
      pageLoaderWait();
        bodyLoadWait();
       String contractFormPageTitle = getAdminBPPage().clickAddNewContract();
        bodyLoadWait();
        String currentPageTitle = getDriver().getTitle();
        Assert.assertEquals( currentPageTitle,contractFormPageTitle,"Failed! Current Page title: "+currentPageTitle);
    }
    String companyName;
    String email;
  // @Test(priority =3 )
    public void testFillGPInviteForm() throws InterruptedException {
        companyName = getInvitegpForm().typeCompanyNAme();
       getInvitegpForm().typeContactName();
       getInvitegpForm().typeEmail();
       getInvitegpForm().typePortfilioCompanies();
       getInvitegpForm().typeTeamMembers();
       getInvitegpForm().selectEnableFundsManager();

    }
 // @Test(priority = 4, dependsOnMethods = "testFillGPInviteForm")
    public void testSuccessfullySubmittedGPInvitation() throws InterruptedException {
       Thread.sleep(1500);
       String dashboardPAgeTitle =  getInvitegpForm().clickInviteBtn();
       String CurrentPageTitle = getDriver().getTitle();
       Assert.assertEquals(dashboardPAgeTitle,CurrentPageTitle, "Wrong Current Page is: "+CurrentPageTitle);
       Thread.sleep(1500);
    }
    @Test(priority = 5)
    public void testSearchCompanyName() throws IOException, InterruptedException, UnsupportedFlavorException {
       //String companyName=csvHandler.getCompanyNameWithPendingStatus();
         getAdminBPPage().searchField("AutoTestMarketing and Advertising");
       // getAdminBPPage().searchField("SepAuto Patrice Ltd.");
         Thread.sleep(1500);
        email = getAdminBPPage().getEmailByStatus("Accepted");
        Assert.assertNotNull(email,"Email is empty");

    }
    String copiedLink;
  @Test(priority = 6, dependsOnMethods = "testSearchCompanyName")
    public void testCopyLinkButton() throws IOException, InterruptedException, UnsupportedFlavorException {
      getAdminBPPage().clickCopyLinkButton();
      Thread.sleep(1500);

      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      Transferable contents = clipboard.getContents(null);
      this.copiedLink = (String) contents.getTransferData(DataFlavor.stringFlavor);

      Boolean linkIsNotEmpty;
      if (this.copiedLink != null || this.copiedLink != "") {
          linkIsNotEmpty = true;
          System.out.println(this.copiedLink);
          Assert.assertTrue(linkIsNotEmpty, this.copiedLink);
      }else
          System.out.println("link is empty");
  }
  // dependsOnMethods = "testCopyLinkButton"
    @Test(priority = 7)
    public void testLogout() throws InterruptedException {
        Thread.sleep(1500);
        getlogin().logout();
        Thread.sleep(1000);
        String logOutPageTitle = getDriver().getTitle();
        Assert.assertEquals(logOutPageTitle,"login");
    }
    @Test(priority = 8, dependsOnMethods = "testLogout")
    public void testOpenLink() throws IOException, InterruptedException, UnsupportedFlavorException {
        bodyLoadWait();
       getDriver().get(this.copiedLink);
        String expectedTitle=  getAdminBPPage().openLink();
        String companyOnboardingPageTitle = getDriver().getTitle();
        bodyLoadWait();
        Assert.assertEquals(companyOnboardingPageTitle,expectedTitle);
    }
    //@Test(priority = 9, dependsOnMethods = "testOpenLink")
    public void testverifyCompanyForm() throws InterruptedException {
        String title = getGPonboardingPage().verifyCompanyForm();
        bodyLoadWait();
        String loginPAgeTitle = getDriver().getTitle();
        Assert.assertEquals(loginPAgeTitle,title);
    }
   // @Test(priority = 10)
    public void loginwithVerifiedCompany() throws InterruptedException {
        bodyLoadWait();
        System.out.println(email);
        String password ="Admin123!";
        getlogin().loginFunction(email,password);
        Thread.sleep(6000);
       // pageLoaderWait();
        String dashboardTitle = getDriver().getTitle();
        Assert.assertEquals(dashboardTitle,"access-manager");
    }
    //@Test(priority = 11)
    public void TestESGTreeFrameWorkSelected() throws InterruptedException {
        pageLoaderWait();
        // TCFD
        // PCAF
        //EDCI
        //DDM
        //Old TCFD
        //SFDR
        getFrameWorkSelectionPupupModel().selectFramework("TCFD");
        By linkTextConfigIndicatorBy;
        WebElement configIndicator = null;
        try {
            linkTextConfigIndicatorBy = new By.ByXPath("//a/u[contains(text(),' Configure Indicators ')]");
            elementToBeClickabledWait(linkTextConfigIndicatorBy);
            configIndicator = getDriver().findElement(linkTextConfigIndicatorBy);
        } catch (Exception e) {
            System.out.println(e);
        }

        Assert.assertTrue(configIndicator.isDisplayed());

    }

}
