package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class GPonboardingPage extends TestBase {
   private Login login;
    private InviteFormPage igpForm;
    private Login getlogin(){
        if (this.login == null){
            this.login = new Login();
        }
            return this.login;
    }
    private InviteFormPage getIgpForm(){
        if (this.igpForm == null){
            this.igpForm = new InviteFormPage();
        }
        return this.igpForm;
    }


  //  @Test(priority = 7)
    public String verifyCompanyForm() throws InterruptedException {
        Thread.sleep(3000);
        By nextBtn = new By.ByLinkText("Next");
        getDriver().findElement(nextBtn).click();
        Thread.sleep(3000);
        WebElement dropdownElement = getDriver().findElement(By.name("Investor type"));
        // Create a Select object
        this.js.executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
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
        passwordInput.sendKeys("Admin123!");
        By confirm_passwordPath = new By.ById("confirm_password");
        WebElement confirm_passwordInput = getDriver().findElement(confirm_passwordPath);
        confirm_passwordInput.sendKeys("Admin123!");

        Thread.sleep(1000);
        String cname =getIgpForm().getCname();
        csvHandler.updateStatus(cname, "Approved");
        System.out.println("Updated status for Company: "+cname);

        Thread.sleep(1000);
        By createBtnPath = new By.ByXPath("//button[@type='submit']");
        WebElement createBtn = getDriver().findElement(createBtnPath);
        Thread.sleep(1000);

        createBtn.click();
        Thread.sleep(1000);

        clicOKOnSuccess();
        Thread.sleep(2000);
        bodyLoadWait();

        return getDriver().getTitle();
    }
   // @Test(priority = 8)
    public void loginWithNewCompany(){
        bodyLoadWait();
        getlogin().loginFunction(csvHandler.getEmailWithApprovedStatus(),"Admin123!");
    }
}
