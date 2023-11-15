package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GPAccessManagerPage extends TestBase  {
    private Login login;
    private InviteFormPage igpForm;
    private AdminAccessManagerPage adminAccessManagerPage;
    private GPonboardingPage gPonboardingPage;

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
    public String searchGPCompany(){
        String email = null;
        bodyLoadWait();

        getAdminBPPage().searchField("SepAuto");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#child-data-datatable > tbody > tr > td > div > div")));

        itemsPerPage100();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#child-data-datatable > tbody > tr > td > div > div")));
        By tableBy = new By.ByXPath("//tbody/tr/td[7]");
        elementToBeClickabledWait(tableBy);

        List<WebElement> rowList = getDriver().findElements(tableBy);

        for(WebElement status : rowList){

            String statusText = status.getText();
            By companyNameBy = new By.ByXPath("//tbody/tr/td[7]");
            elementToBeClickabledWait(companyNameBy);
            System.out.println("All Status :"+statusText);
            System.out.println(statusText);
            if(statusText.equals("Accepted")){
                email = getDriver().findElement(By.xpath("//tbody/tr/td[2]")).getText();
                System.out.println("Accepted Company Name:"+getDriver().findElement(companyNameBy).getText());
                System.out.println("email Address: "+email);
              //  getDriver().findElement(companyNameBy).click();
                break;
            }
        }
        return email;

    }
    public void itemsPerPage100(){
       /* WebElement dropdown = driver.findElement(By.className("form-control"));
        dropdown.click();

        // Select the "100" option
        WebElement option100 = dropdown.findElement(By.cssSelector(".//option[@value='100']"));
        option100.click();*/
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#child-data-datatable > tbody > tr > td > div > div")));
            // Find the select element by its <select> tag
            By intemsPerPagedropdownBy = new By.ByTagName("select");
            WebElement intemsPerPagedropdown = driver.findElement(intemsPerPagedropdownBy);

            js.executeScript("arguments[0].scrollIntoView(true);", intemsPerPagedropdown);
            //  Thread.sleep(2000);
            js.executeScript("arguments[0].click();", intemsPerPagedropdown);
            // Create a Select object for the select element
            Select select = new Select(intemsPerPagedropdown);

            // Select the option with value "100"
            select.selectByValue("100");

            // You can perform additional actions here if needed

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void clickOnNextPage(){

        WebElement nextPageLink = driver.findElement(By.xpath("//ul[@class='pagination']//li[@class='page-item']/a[@aria-label='Next']"));
        nextPageLink.click();

        // Wait for the next page to load (replace with appropriate condition)
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#child-data-datatable > tbody > tr > td > div > div")));

    }
}
