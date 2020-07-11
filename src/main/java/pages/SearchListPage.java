package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Base;

import java.util.*;

public class SearchListPage extends Base {

    WebDriverWait wait;
    public SearchListPage(){
        wait = new WebDriverWait(driver,30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='poll-runway']")
    private WebElement loaderTop;

    @FindBy(xpath = "//div[contains(@class,'more-fltrs')]")
    private WebElement moreFilters;

    @FindBy(xpath = "//div[@class='result-col outr']//div[@class='u-text-ellipsis']")
    private List<WebElement> departureFlights;

    @FindBy(xpath = "//div[@class='result-col outr']//div[@class='price']//span[2]")
    private List<WebElement> detarturePrices;

    @FindBy(xpath = "//div[@class='result-col']//div[@class='u-text-ellipsis']")
    private List<WebElement> returnFlights;

    @FindBy(xpath = "//div[@class='result-col']//div[@class='price']//span[2]")
    private List<WebElement> returnPrices;

    public SearchListPage waitForListToLoad(){
        wait.until(ExpectedConditions.visibilityOf(loaderTop));
        wait.until(ExpectedConditions.visibilityOf(moreFilters));
        return this;
    }

    public Map<String, Integer> getPriceMappingForDeparture(){
        Map<String, Integer> map = new LinkedHashMap<>();
        for(int i=0;i<departureFlights.size();i++){
            map.put(departureFlights.get(i).getText(), Integer.parseInt(detarturePrices.get(i).getText()));
        }
        return map;
    }

    public Map<String, Integer> getPriceMappingForReturn(){
        Map<String, Integer> map = new LinkedHashMap<>();
        for(int i=0;i<returnFlights.size();i++){
            map.put(returnFlights.get(i).getText(), Integer.parseInt(returnPrices.get(i).getText()));
        }
        return map;
    }

    public Map<String, Integer> getBestDeals(int count){
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i=0;i<departureFlights.size();i++){
            for(int j=0;j<returnFlights.size();j++){
                String flights = departureFlights.get(i).getText() + " -+- " + returnFlights.get(j).getText();
                int sum = Integer.parseInt(detarturePrices.get(i).getText()) + Integer.parseInt(returnPrices.get(j).getText());
                map.put(flights, sum);
            }
        }

        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        int c=1;
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
            if(c++ ==count) break;
        }
        return temp;
    }

}
