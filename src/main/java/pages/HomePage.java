package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Base;
import utils.Utils;

public class HomePage extends Base {

    public HomePage(){
        wait = new WebDriverWait(driver, 30000);
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait;

    @FindBy(xpath = "//a[@href='/flights']")
    private WebElement flightsLink;

    @FindBy(xpath = "//div[text()='Round Trip']")
    private WebElement roundTripLink;

    @FindBy(xpath = "//div[text()='From']/following-sibling::input")
    private WebElement fromText;

    @FindBy(xpath = "//div[text()='To']/following-sibling::input")
    private WebElement toText;

    @FindBy(xpath = "//div[contains(@class,'orgn')]//div[@data-acindex='0']")
    private WebElement fromCitySelect;

    @FindBy(xpath = "//div[contains(@class,'dstn')]//div[@data-acindex='0']")
    private WebElement toCitySelect;

    @FindBy(xpath = "//input[@placeholder='Depart']")
    private WebElement fromDateText;

    @FindBy(xpath = "//input[@placeholder='Return']")
    private WebElement returnDateText;

    @FindBy(xpath = "//div[contains(@class,'flight-dep-cal')]//button[contains(@class,'rd-next')]")
    private WebElement departureNextArrow;

    @FindBy(xpath = "//div[contains(@class,'flight-ret-cal')]//button[contains(@class,'rd-next')]")
    private WebElement returnNextArrow;

    @FindBy(xpath = "//button[@class='c-btn u-link enabled' and text()='Search']")
    private WebElement searchButton;

    String s1 = "//div[contains(@class,'flight-dep-cal')]//div[contains(@class,'rd-month-label') and contains(text(),'";
    String s2 = "')]";
    String s3 = "')]//following-sibling::table//div[@class='day has-info' and text()='";
    String s4 = "//div[contains(@class,'flight-ret-cal')]//div[contains(@class,'rd-month-label') and contains(text(),'";
    String s5 = "']";


    public HomePage clickFlightsLink(){
        flightsLink.click();
        return this;
    }

    public HomePage clickRoundTripLink(){
        roundTripLink.click();
        return this;
    }

    public HomePage enterFromCity(String city) throws InterruptedException {
        fromText.clear();
        Thread.sleep(2000);
        fromText.sendKeys(city);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(fromCitySelect));
        fromCitySelect.click();
        return this;
    }

    public HomePage enterToCity(String city){
        toText.clear();
        toText.sendKeys(city);
        wait.until(ExpectedConditions.visibilityOf(toCitySelect));
        toCitySelect.click();
        return this;
    }

    public HomePage selectDepartureDate(int diff){
        String curr = Utils.getDateByAddingDays(properties.getProperty("dateFormat"), diff);
        String[] arr = curr.split("-");
        String currDate = arr[2];
        String currMonth = arr[1];
        fromDateText.click();
        setImplicitWait(1);
        int flag = driver.findElements(By.xpath(s1+currMonth+s2)).size();
        while (flag==0){
            departureNextArrow.click();
            flag = driver.findElements(By.xpath(s1+currMonth+s2)).size();
        }
        setImplicitWait(30);
        driver.findElement(By.xpath(s1+currMonth+s3+currDate+s5)).click();
        return this;
    }

    public HomePage selectReturnDate(int diff){
        String curr = Utils.getDateByAddingDays(properties.getProperty("dateFormat"), diff);
        String[] arr = curr.split("-");
        String currDate = arr[2];
        String currMonth = arr[1];
        returnDateText.click();
        setImplicitWait(1);
        int flag = driver.findElements(By.xpath(s4+currMonth+s2)).size();
        while (flag==0){
            returnNextArrow.click();
            flag = driver.findElements(By.xpath(s4+currMonth+s2)).size();
        }
        setImplicitWait(30);
        driver.findElement(By.xpath(s4+currMonth+s3+currDate+s5)).click();
        return this;
    }

    public SearchListPage clickSubmit(){
        searchButton.click();
        return new SearchListPage();
    }



}
