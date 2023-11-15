package Tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AdminAccessManagerPage extends TestBase {


    private Login login;
    InviteFormPage igpForm;



    private InviteFormPage getIgpForm() {
        if (this.igpForm == null) {
            this.igpForm = new InviteFormPage();
        }
        return this.igpForm;
    }

    //  @Test (priority = 2)
    public String clickAddNewContract() throws InterruptedException {
        bodyLoadWait();
        By inviteBtnBy = new By.ByXPath("//button[contains(text(),'Add New Contract')]");
        elementToBeClickabledWait(inviteBtnBy);
        getDriver().findElement(inviteBtnBy).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'New Contract')]")));
        // Thread.sleep(1000);
        String contractFormTitle = getDriver().getTitle();
        return contractFormTitle;
    }

    public String searchField(String companyName) {
        By searchFieldBy = new By.ByXPath("//section/div/div/div[1]/div/div[1]/div/div/form/div/input");
        elementToBeClickabledWait(searchFieldBy);
        
        getDriver().findElement((searchFieldBy)).sendKeys(companyName);

        return companyName;
    }
    public String getEmailByStatus(String status) throws InterruptedException {
        Thread.sleep(1500);
        try {
            // Use a more specific XPath to locate the email in the table row
            By emailColBy = new By.ByXPath("//tbody/tr/td[2]");
            String email = getDriver().findElement(emailColBy).getText();
            System.out.println("Get Email: " + email);
            return email;
        } catch (NoSuchElementException e) {
            // Handle the case where the email element is not found
            System.out.println( "No Data Found");
            return "No Data Found";
        }
    }
    public String getCompanyNameByStatus(String status) throws InterruptedException {
        Thread.sleep(1500);
        try {
            // Use a more specific XPath to locate the email in the table row
           // By companyColBy = new By.ByXPath("//tbody/tr/td[7]/span[contains(text(),'"+status+"')]");
            By companyColBy = new By.ByXPath("//td[7]/span[contains(text(),'"+status+"')]/parent::td/preceding-sibling::td[6]");
            /*List<WebElement> list = getDriver().findElements(companyColBy);
            if(list.size()>1){
            }*/
            String cName = getDriver().findElement(companyColBy).getText();
            System.out.println("Get Email: " + cName);
            return cName;
        } catch (NoSuchElementException e) {
            // Handle the case where the email element is not found
            System.out.println( "No Data Found");
            return null;
        }
    }
//https://github.com/mrumair94/EsgTree
  /*  public String searchCompanyWithResults(String companyName) throws InterruptedException {
       *//* bodyLoadWait();
        String result = "";
        Thread.sleep(1000);
        By noDataFoundBy = new By.ByXPath("//div[@class='table-responsive']/p[contains(text(),'No Data Found')]");
        List<WebElement> noDataFound = getDriver().findElements(noDataFoundBy);
        Thread.sleep(1000);*//*
        List<String> companyNames = csvHandler.getCompanyNamesByStatus("Pending");
        for (String singleCompanyNameFromList : companyNames) {
            companyName =singleCompanyNameFromList;
            searchField(companyName);
            By companyNameColBy = new By.ByXPath("//tbody/tr/td[1]");
            List<WebElement> companyNameCol = getDriver().findElements(companyNameColBy);
         *//*   By noDataFoundBy = new By.ByXPath("//div[@class='table-responsive']/p[contains(text(),'No Data Found')]");
            String noDataFound = getDriver().findElement(noDataFoundBy).getText();*//*

            if(companyNameCol.size()>0){
                String status = getDriver().findElement(By.xpath("//tbody/tr[1]/td[7]")).getText();
            if (status.equals("Pending")) {
                System.out.println("company Name Found:"+companyName);
              break;
            } else if (status.equals("Accepted")) {
                csvHandler.updateStatus(companyName, "Approved");
                System.out.println("Updated Company Name status: " + companyName);

            } }else {
                csvHandler.deleteRowByCompanyName(companyName);
                System.out.println("nothing is found!");
            }
        }
        return companyName;
    }*/

    public String searchResultsWithData(){
        bodyLoadWait();
        String result = null;
        By companyNameColBy = new By.ByXPath("//tbody/tr/td[1]");
        List<WebElement> companyNameCol = getDriver().findElements(companyNameColBy);
        if (companyNameCol.size()>0 ) {
            String companyNameColTesxt = getDriver().findElement(companyNameColBy).getText();

            result = companyNameColTesxt;
            System.out.println("Result: " + result);
            return result;
        }
        return result;
    }
   /* public Boolean isDataFound(){
        try {
            By tbodyBy = new By.ByXPath("//tbody");
            getDriver().findElement(tbodyBy);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }*/
   public String checkIfNoDataFound(){
        String result = null;
        By noDataFoundBy = new By.ByXPath("//div[@class='table-responsive']/p[contains(text(),'No Data Found')]");
        List<WebElement> noDataFound = getDriver().findElements(noDataFoundBy);
        dataLoaderWait();
        if (noDataFound.size()>0) {
            String noDataFoundText = getDriver().findElement(noDataFoundBy).getText();
            // String status = getDriver().findElement()
            result = noDataFoundText;
            System.out.println("Result: " + result);
            return result;
        }else
            System.out.println("data Found "+result);
            return result;
    }
    public String searchCompanyName(String companyName) throws InterruptedException {
        bodyLoadWait();
        String result = "";
        getDriver().findElement(By.xpath("//section/div/div/div[1]/div/div[1]/div/div/form/div/input")).sendKeys(companyName);

        Thread.sleep(1000);

        By companyNameColBy = new By.ByXPath("//tbody/tr/td[1]");
        By noDataFoundBy = new By.ByXPath("//div[@class='table-responsive']/p[contains(text(),'No Data Found')]");
        List<WebElement> noDataFound = getDriver().findElements(noDataFoundBy);
        List<WebElement> companyNameCol = getDriver().findElements(companyNameColBy);
        Thread.sleep(1000);

        if (noDataFound.size()>0) {
                String noDataFoundText = getDriver().findElement(noDataFoundBy).getText();
           // String status = getDriver().findElement()
            result = noDataFoundText;
            System.out.println("Result: " + result);
            return result;
        } else if (companyNameCol.size()>0 ) {
            String companyNameColTesxt = getDriver().findElement(companyNameColBy).getText();

            result = companyNameColTesxt;
            System.out.println("Result: " + result);
            return result;
        }
        System.out.println("Nothing Found " + result);

        return result;
    }
    public void clickCopyLinkButton() throws InterruptedException, IOException, UnsupportedFlavorException {
        //Click on copy button
        bodyLoadWait();
        By copyBtnBy = new By.ByXPath("//td/a[@title='copy registration link']");
        elementToBeClickabledWait(copyBtnBy);
        WebElement copyBtn = getDriver().findElement(copyBtnBy);
        try {
            elementToBeClickabledWait(copyBtnBy);
            this.js.executeScript("arguments[0].scrollIntoView(true);", copyBtn);
            this.js.executeScript("arguments[0].click();", copyBtn);
            Thread.sleep(1000);
                clicOKOnSuccess();
           /* WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());*/
            Thread.sleep(1000);
            Alert simpleAlert = getDriver().switchTo().alert();
            simpleAlert.accept();
            System.out.println("Unexpected alert accepted");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        }
   // Return GPContractInvitationPage?, Search Company Name, Copy Link, Logout
  //  @Test(priority = 6)
    public String openLink() throws InterruptedException, IOException, UnsupportedFlavorException {
        //get link which is copied in the Clipboard
        /*Thread.sleep(1500);
        getlogin().logout();
        bodyLoadWait();*/

        String copiedLink;
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        copiedLink = (String)contents.getTransferData(DataFlavor.stringFlavor);
        System.out.println("Copied Link: "+copiedLink);
        System.out.println(copiedLink);
        Thread.sleep(2000);
        //getlogin().logout();
        Thread.sleep(2000);
        getDriver().get(copiedLink);
        Thread.sleep(2000);
        bodyLoadWait();
        return getDriver().getTitle();
    }
    public String openLinkTest() throws InterruptedException, IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);

        String copiedLink = null;

        // Check for plain text flavor
        if (contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                copiedLink = (String) contents.getTransferData(DataFlavor.stringFlavor);
                System.out.println("Link:"+ copiedLink);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace(); // Handle the exception as needed
            }
        }

        return copiedLink;


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
}
