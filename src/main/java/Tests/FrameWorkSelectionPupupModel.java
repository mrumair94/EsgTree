package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class FrameWorkSelectionPupupModel  extends TestBase {
    private Login login;
    private AdminAccessManagerPage adminAccessManagerPage;

    private Login getlogin() {
        if (this.login == null) {
            this.login = new Login();
        }
        return this.login;
    }

    private AdminAccessManagerPage getAdminDashBoardPage() {
        if (this.adminAccessManagerPage == null) {
            this.adminAccessManagerPage = new AdminAccessManagerPage();
        }
        return this.adminAccessManagerPage;
    }

    //Select Framework
    By frameworkPupupBy = new By.ByCssSelector("#data-modal > div > div>div>form");
    By quarterlyradioaBtnBy = new By.ByCssSelector("label[for='quarterly']");
    //By esgTreeRadioBy = new By.ByCssSelector("label[for='0[object Object]']");
    By submitBtnBy = new By.ByCssSelector("#data-modal > div > div > div > form > div.form-group.text-center > button");
    By okBtbBy = new By.ByCssSelector("button[class='swal2-confirm swal2-styled']");

    //   SepAuto Katlyn Ltd.
    //Login
    @Test(priority = 1)
    public void setup() {
        getDriver().manage().window().maximize();
        getDriver().get("https://dev-esg.esgtree.com/login");
    }
    //   @Test(priority = 2)

    public void loginAsAdmin() throws InterruptedException {
        bodyLoadWait();
        getlogin().loginAsAdmin();
        getlogin().CheckLogin();
    }

    //Search company
    String email = "";

    // @Test(priority = 3)
    public void seachGPAcceptedCompany() throws InterruptedException {
        bodyLoadWait();
        getAdminDashBoardPage().searchField("SepAuto Katlyn Ltd.");
        dataLoaderWait();
        email = getAdminDashBoardPage().getEmailByStatus("Accepted");
        Thread.sleep(3000);
        getlogin().logout();
        bodyLoadWait();
    }

   // @Test(priority = 4)
    public void loginWithNewCompany() {
        bodyLoadWait();
        getlogin().loginFunction("AutomaitonFrankie@gmail.com", "Adam123!");
        getlogin().CheckLogin();
        bodyLoadWait();
    }

  //  @Test(priority = 5)
    public void selectFramework(String frameworkName) throws InterruptedException {
        Thread.sleep(4000);
       // bodyLoadWait();
        pageLoaderWait();

        if (checkFrameWorkPopupIsVisible() == true) {
            System.out.println("frameWork Popup Appears");
            try{
                selectquartly();
                selectESGTree(frameworkName);
                ckickSubmitBtn();
            }
          catch(NoSuchElementException e){
              System.out.println("Element does not appear");
          }
            dataLoaderWait();
            bodyLoadWait();
        } else System.out.println("Please Configure the Indicator, Framework is already selected");
    }
    public void stateElementReferenceHandle(By elementBy) {
        try {
            WebElement element = getDriver().findElement(elementBy);
            element.isEnabled();
        } catch (StaleElementReferenceException e) {
            WebElement element = getDriver().findElement(elementBy);
            element.isEnabled();
        }
    }

    public Boolean selectquartly() throws InterruptedException {
        elementToBeClickabledWait(quarterlyradioaBtnBy);
        stateElementReferenceHandle(quarterlyradioaBtnBy);
        WebElement quartlyRadioBtn = getDriver().findElement(quarterlyradioaBtnBy);
        if (quartlyRadioBtn.isSelected() == false) {
            quartlyRadioBtn.click();
           /*this.js.executeScript("arguments[0].scrollIntoView(true);", quartlyRadioBtn);
           Thread.sleep(500);
           this.js.executeScript("arguments[0].click();", quartlyRadioBtn);*/
            System.out.println("Quarterly is Selected");
            return true;
        } else
            System.out.println("Quarterly is not selected");
        return false;
    }

    public Boolean selectESGTree(String frameWork) {
        By frameworkRadioBy = new By.ByXPath("//label[contains(text(),'" + frameWork + "')]");
        elementToBeClickabledWait(frameworkRadioBy);
        WebElement frameworkRadio = getDriver().findElement(frameworkRadioBy);

        if (frameworkRadio.isDisplayed()) {

            if (frameworkRadio.isSelected() == false) {
                frameworkRadio.click();
                System.out.println(frameWork + " is Selected");
                return true;
            } else {
                System.out.println(frameWork + " Framework is Already Selected");
                return false;
            }

        }else {
            System.out.println("Framework Popup is not appear");
            return null;
        }
    }

    public void ckickSubmitBtn() {
        elementToBeClickabledWait(submitBtnBy);
        getDriver().findElement(submitBtnBy).click();
        elementToBeClickabledWait(okBtbBy);
        getDriver().findElement(okBtbBy).click();
        System.out.println("Successfully submitted");
        bodyLoadWait();
    }


    public Boolean checkFrameWorkPopupIsVisible() {
/*
        List<WebElement> frameWorkPopupList = getDriver().findElements(frameworkPupupBy);
        try{elementToBeClickabledWait(frameworkPupupBy);}
        catch (Exception e) {
            System.out.println(e);
        }*/

        /*List<WebElement> frameworkPopup = getDriver().findElements(frameworkPupupBy);
        if(frameworkPopup.size()==1){*/
        try {

            if (getDriver().findElement(frameworkPupupBy).isDisplayed()) {
                System.out.println("Framework is not Selected Yet");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Framework is Already Selected");
            return false;
        }
        return false;
    }

   /* public boolean checkESGTreeIsSelected(){
        try{elementToBeClickabledWait(esgTreeRadioBy);}
        catch (Exception e) {
            System.out.println(e);
        }

        elementToBeClickabledWait(esgTreeRadioBy);
        WebElement esgTreeRadio = getDriver().findElement(esgTreeRadioBy);
        if(esgTreeRadio.isSelected()) {
            return true;
        }else
            return false;
    }*/
    public Boolean checkQuarterlyIsSelected(){
        try{elementToBeClickabledWait(quarterlyradioaBtnBy);}
        catch (Exception e) {
            System.out.println(e);
        }
        elementToBeClickabledWait(quarterlyradioaBtnBy);
        WebElement quartly = getDriver().findElement(quarterlyradioaBtnBy);
        if(quartly.isSelected()){
            return  true;
        }else
            return false;
    }
}
