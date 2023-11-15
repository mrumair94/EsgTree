package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class EsgTreeFrameworkConfig extends TestBase{


    // Confuger Indicator
    By linktextConfigIndicatorBy = new By.ByXPath("//u[contains(text(),'Configure Indicators')]");
    By configureBtnBy = new By.ByXPath("//button[contains(text(),'Configure')]");

    //Frameworks Page >Industries Section
    By addIndustriesBtnBy = new By.ByXPath("//button[contains(text(),'Add Industries')]");
       //Add Industires Model
    By sectorDropDownBy =  new By.ByXPath("//input[@placeholder='Select Sector']");
    By sectorsListBy = new By.ByXPath("//div/div[1]/div/div[@class='multiselect__content-wrapper']/ul[1]/li");
    By industriesDropDownBy =  new By.ByXPath("//input[@placeholder='Select Industries']");
    By IndustriesListBy = new By.ByXPath("//div/div[2]/div/div[@class='multiselect__content-wrapper']/ul[1]/li");
    By saveBtnBy = new By.ByXPath("//button[contains(text(),'Save')]");

       //Action Button in Industires Table
    By configureIndustryBtnBy = new By.ByXPath("//td/span[@type='button'][contains(text(),'Configure')]");

       //Industry Indicator Setting Model
    By dimensionListBy = new By.ByXPath("//div[@id='links-buttons-preview']//div[@class='list-group']");
    By gicListBy = new By.ByXPath("//div[@id='links-buttons-preview2']//div[@class='list-group']");
    By industryIndicatorCheckBoxListBy = new By.ByXPath("//div[@class='list-group']/div/input[@type='checkbox']");
    By reviewBtnBy = new By.ByXPath("//div[@class='modal-footer']/div[1]/button[contains(text(),'Review')]");

       // Review Industry Indicators Model
    By submitReviewBtnBy = new By.ByXPath("//div[@class='modal-content']/div/div[2]/button[contains(text(),'Submit')]");
    By popupYesBtn = new By.ByXPath("//button[contains(text(),'Yes, submit it!')]");
    By okBtnSuccessPopup = new By.ByXPath("//button[contains(text(),'OK')]");
    public void checklinkTextConfuger(){
        try{
            WebElement linktextConfigIndicator = getDriver().findElement(linktextConfigIndicatorBy);
            if(linktextConfigIndicator.isDisplayed()) {
                System.out.println("Centrifuge Indicators Link text is appeared");

                linktextConfigIndicator.click();
            }
        }catch(Exception e){
            // add industries
        }
    }
    // get email
    //login with GP Email
    //Check if the Popup Appear
    //if yes performe Popup select ESGTree
    // if No Open Framework and selectFrameWork check if ESG Tree is selected is selected pass if not Select ESGTree unckeck all other
    //Configure ESGTree Industrial
    //Confuger indicator setting
    //Add New PC Company
    }