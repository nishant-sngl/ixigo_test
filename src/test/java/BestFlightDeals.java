import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SearchListPage;
import utils.Base;
import java.util.Map;

import static utils.Utils.printMap;

public class BestFlightDeals extends Base {

    private static int depDateDiff;
    private static int retDateDiff;
    private static int resultsCount;
    private static String fromCity;
    private static String toCity;

    private static WebDriver driver;

    @BeforeMethod
    private static void setup(){
        driver = getDriverInstance();
        driver.get(baseURL);
        depDateDiff = Integer.parseInt(properties.getProperty("depDateDiff"));
        retDateDiff = Integer.parseInt(properties.getProperty("retDateDiff"));
        resultsCount = Integer.parseInt(properties.getProperty("resultsCount"));
        fromCity = properties.getProperty("fromCity");
        toCity = properties.getProperty("toCity");
    }

    @Test
    private static void flightDeals() throws InterruptedException {
        if (fromCity.equals(toCity)){
            System.out.println("the departure and arrival cities are same. Please correct values");
            return;
        }
        if(depDateDiff>retDateDiff){
            System.out.println("the return date is less then the departure date. Please correct values");
            return;
        }
        if (depDateDiff<0){
            System.out.println("the departure date is of past. Enter a positive date");
            return;
        }

        HomePage homePage = new HomePage();
        SearchListPage searchListPage = homePage.clickFlightsLink()
            .clickRoundTripLink()
            .enterFromCity(fromCity)
            .enterToCity(toCity)
            .selectDepartureDate(depDateDiff)
            .selectReturnDate(retDateDiff)
            .clickSubmit();
        searchListPage.waitForListToLoad();

        Map<String, Integer> dealsMap = searchListPage.getBestDeals(resultsCount);
        printMap(dealsMap);
    }

    @AfterMethod
    public static void closeSessions(){
        driver.quit();
    }
}
