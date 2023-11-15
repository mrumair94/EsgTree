package Tests;

import org.openqa.selenium.By;

public class InvitePCFormPage extends TestBase{
    By inviteBtnBy = new By.ByXPath("(//button[normalize-space()='Invite/Add Portfolio Company'])[1]");
    By companyNameBy = new By.ByXPath("(//input[@id='validationCustom01'])[2]");
    By contactNameBy = new By.ByXPath("//div[@id='invit-pc-modal']//input[@id='contact-name']");
    By emailIDBy = new By.ByXPath("(//input[@id='validationCustom03'])[2]");
    By investmentAmountBy = new By.ByXPath("(//input[@id='investment'])[2]");
    By percentageAmountBy = new By.ByXPath("(//input[@id='investment'])[2]");
    By boardSeatNoBy = new By.ByXPath("(//label[@for='bold-no'])[1]");
    By submitBtnBy = new By.ByXPath("(//button[@type='submit'][normalize-space()='Submit'])[2]");
    String name = faker.name().firstName();

    public void clickAddPCBtn(){
        elementToBeClickabledWait(inviteBtnBy);
        getDriver().findElement(inviteBtnBy).click();
    }
    public void typeCompanyName(){
        elementToBeClickabledWait(companyNameBy);
        getDriver().findElement(companyNameBy).sendKeys(name+ " LTD.");
    }
    public void typeContactName(){
        elementToBeClickabledWait(contactNameBy);
        getDriver().findElement(contactNameBy).sendKeys(name+"@gmail.com");
    }
    public void emailIDBy(){
        elementToBeClickabledWait(emailIDBy);
        getDriver().findElement(emailIDBy).sendKeys(name+"85264");
    }
    public void typePercentageAmount(){
        elementToBeClickabledWait(percentageAmountBy);
        getDriver().findElement(percentageAmountBy).sendKeys("85");
    }
    public void selectBoardSeatNo(){
        elementToBeClickabledWait(boardSeatNoBy);
        getDriver().findElement(boardSeatNoBy).click();
    }
    public void clicksubmitBtn(){
        elementToBeClickabledWait(submitBtnBy);
        getDriver().findElement(submitBtnBy).click();
    }
}
