package pages;

import other.ForCheck;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OzonCatalog extends BasePage {


    String categoryItem = "//li[@class='category-item']/a[contains(text(), '%s')]";
    @FindBy(xpath = "//div[@data-test-id='filter-block-price']//input[@data-test-id='range-filter-from-input']")
    WebElement priceFrom;
    @FindBy(xpath = "//div[@data-test-id='filter-block-price']//button[@class='filter-button blue-cream button']")
    WebElement buttonShow;
    @FindBy(xpath = "//div[@data-test-id='filter-block-price']//input[@data-test-id='range-filter-to-input']")
    WebElement priceTo;
    @FindBy (xpath = "//*[@class='five-dots preloader']")
    WebElement fiveDotsPreload;


    private String firstProdInList = "//div[@class='item-view']/div[@data-index='%s']";
    private String buttonToCart = firstProdInList + "//span[@class=contains(text(), 'buy-button')]";
    private String price = firstProdInList + "//span[@class='price-number']/span";
    private String name = firstProdInList + "//p[@class='name']/a";
    private String categotyX = "//div[@class='category-list']//li//li/a[contains(text(), '%s')]";
    private String brandX = "//div[contains(text(),'Бренды')]/..//div[@class='items']//*[contains(text(), '%s')]/../span[@class='checkmark']";

    public OzonCatalog clickCategoryItem(String category) {
        WebElement temp = getWebElement(String.format(categoryItem, category));
        temp.click();
        return this;
    }

    public OzonCatalog setPriceFrom(int from, String... keys) {
        waitVisible(priceFrom);
        priceFrom.click();
        priceFrom.sendKeys(Keys.CONTROL + "a");
        priceFrom.sendKeys(Keys.DELETE);
        priceFrom.sendKeys(String.valueOf(from));
        if (keys.length > 0) {
            for (String key : keys) {
                priceFrom.sendKeys(Keys.valueOf(key));
            }
        }
        priceTo.click();
        return this;
    }

    public OzonCatalog clickButtonShow() {
        waitVisible(buttonShow);
        buttonShow.click();
        return this;
    }

    public OzonCatalog buyProdInStock() {
        waitVisible(String.format(firstProdInList, String.valueOf(0)));
        boolean canBuy = false;
        int count = 0;
        do {
            WebElement temp = driver.findElement(By.xpath(String.format(buttonToCart, String.valueOf(count))));
            scrollTo(temp);
            if (temp.getText().indexOf("корзин") != -1) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", temp);
                ForCheck.AddToListProdInMind(getWebElement(String.format(name, String.valueOf(count))).getText());
                String s = "";
                s = getWebElementVis(String.format(price, String.valueOf(count))).getText().replaceAll(" ", "");
                ForCheck.setTotalPrice(Integer.valueOf(s));
                break;
            }
            count++;
        }
        while (!canBuy);
        return this;
    }

    public void setCategory(String category) {
        WebElement temp = getWebElement(String.format(categotyX, category));
        scrollTo(temp);
        temp.click();
    }


    public OzonCatalog setBrandX(String brand) {
        WebElement temp = getWebElement(String.format(brandX, brand));
        scrollTo(temp);
        temp.click();
        return this;
    }

    public OzonCatalog waitPreload(){
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOf(fiveDotsPreload));
        return this;
    }


    @Override
    public boolean isPageLoaded() {
        return false;
    }
}
