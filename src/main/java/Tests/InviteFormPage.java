package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InviteFormPage extends TestBase {

   static String  name =faker.name().firstName();

    public String getCname(){
        String compName="SepAuto " + name +" Ltd.";
        return compName;
    }
    public String getEmailId(){
       String emailID=name+"@gmail.com";
       return  emailID;
    }
    public String typeCompanyNAme() throws InterruptedException {
        Thread.sleep(3000);
        By companyNameBy = new By.ById("validationCustom01");
        elementToBeClickabledWait(companyNameBy);
        String companyName = getCname();
        getDriver().findElement(companyNameBy).sendKeys(companyName);
        return companyName;

    }
    public void typeContactName() {
        By contactNameFieldBy = new By.ById("validationCustom02");
        elementToBeClickabledWait(contactNameFieldBy);
        getDriver().findElement(contactNameFieldBy).sendKeys("SepAuto" + faker.name().firstName());
    }
    public String typeEmail(){
        By emailFiledBy = new By.ById("validationCustom03");
        elementToBeClickabledWait(emailFiledBy);
        String email = getEmailId();
        getDriver().findElement(emailFiledBy).sendKeys(email);
        return email;
    }
    public void typePortfilioCompanies() {
        By emailFiledBy = new By.ById("validationCustom05");
        elementToBeClickabledWait(emailFiledBy);
        getDriver().findElement(emailFiledBy).sendKeys("1");
    }
    public void typeTeamMembers() {
        By emailFiledBy = new By.ById("validationCustom06");
        elementToBeClickabledWait(emailFiledBy);
        getDriver().findElement(emailFiledBy).sendKeys("3");
    }
    public void selectEnableFundsManager() {
        By radioBtnNoBy = new By.ById("bold-no");
        WebElement radioBtn = getDriver().findElement(radioBtnNoBy);
        try {
            this.js.executeScript("arguments[0].scrollIntoView(true);", radioBtn);
            Thread.sleep(1000);
            this.js.executeScript("arguments[0].click();", radioBtn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String clickInviteBtn() throws InterruptedException {
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//button[@type='submit'][contains(text(),'Send Invite')]")).click();

        Thread.sleep(1000);
        //csvHandler.writeData(getCname(),getEmailId());
        System.out.println("typed company name: " +getCname());
        System.out.println("Typed Email: "+getEmailId());

        By okBtn = new By.ByXPath("//button[@type='button'][contains(text(),'OK')]");
        elementToBeClickabledWait(okBtn);
        Thread.sleep(2000);
        String expected = "Success";
        By succussPopup = new By.ByXPath("//h2[contains(text(),'Success')]");
        elementToBeClickabledWait(succussPopup);

        clicOKOnSuccess();
        Thread.sleep(1500);
        String DashboardPageTitle = getDriver().getTitle();
        return DashboardPageTitle;

    }
}
