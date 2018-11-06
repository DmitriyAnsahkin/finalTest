package Pages;

import Other.ForCheck;
import org.junit.Assert;
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
    @FindBy(xpath = "//*[@class='item-wrapper']")
    WebElement itemWrapper;
    @FindBy (xpath = "//h1")
    WebElement h1;
    @FindBy (xpath = "//*[@class='search-title']/span")
    WebElement countProd;

    private String firstProdInList = "//div[@class='item-view']/div[@data-index='%s']";
    private String buttonToCart = firstProdInList + "//span[@class=contains(text(), 'buy-button')]";
    private String price = firstProdInList + "//span[@class='price-number']/span";
    private String name = firstProdInList + "//p[@class='name']/a";
    //div[@class='item-view']/div[@data-index='0']//span[@class=contains(text(), 'buy-button')]
    private String categotyX = "//div[@class='category-list']//li//li/a[contains(text(), '%s')]";
    private String brandX = "//div[contains(text(),'Бренды')]/..//div[@class='items']//*[contains(text(), '%s')]/../span[@class='checkmark']";
    private int testFiltres;

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

    public OzonCatalog waitResult() {
        new WebDriverWait(driver, 5).
                until(ExpectedConditions.invisibilityOf(buttonShow));

        new WebDriverWait(driver, 5).
                until(ExpectedConditions.elementToBeClickable(By.xpath((String.format(firstProdInList, "0")))));
        waitVisible(itemWrapper);
        new WebDriverWait(driver, 5).
                until(ExpectedConditions.elementToBeClickable(By.xpath((String.format(firstProdInList, "0")))));

        return this;
    }

    public OzonCatalog clickButtonShow() {
        waitVisible(buttonShow);
        buttonShow.click();
        return this;
    }


    public OzonCatalog buyFirstProdInStock() {
        waitVisible(String.format(firstProdInList, String.valueOf(0)));
        boolean canBuy = false;
        int count = 0;
        do {
            WebElement temp = driver.findElement(By.xpath(String.format(buttonToCart, String.valueOf(count))));
            scrollTo(temp);
            if (temp.getText().indexOf("корзин") != -1) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", temp);
                ForCheck.AddToListProdInMind(getWebElement(String.format(name, String.valueOf(count))).getText());
                String s = getWebElement(String.format(price, String.valueOf(count))).getText().replaceAll(" ", "");
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
        //вот тут и на 121 строчке кода два вынужденных костыля, т.к. элементы проверяются до обновления
        //т.е товар выбирается пока не успели примениться фильтры
        //Я не хотел делать thread.sleep, и понимаю, что и это не лучший вариант, но времени не хватает
        int count = 0;
        do {
            if (h1.getText().equals(category)) {
                break;
            }
            count++;
            System.out.println(count);
            if (count == 1000) Assert.fail("Таймаут. Нужная категория не была выбрана");
        }
        while (true|| count ==1000);

        testFiltres = Integer.valueOf(countProd.getText()
                .replaceAll("[^0-9]", ""));
        System.out.println(testFiltres);

    }

    public OzonCatalog waitLoadFilters() {
        int count = 0;
        do {
            int a = Integer.valueOf(countProd.getText().replaceAll("[^0-9]", ""));
            if (a != testFiltres) break;
            count++;
            System.out.println(count);
            if (count == 1000) Assert.fail("Таймаут. Фильры не сработали.");
        } while (true||count==1000);

        return this;
    }

    public OzonCatalog setBrandX(String brand) {
        WebElement temp = getWebElement(String.format(brandX, brand));
        scrollTo(temp);
        temp.click();
        return this;
    }

    @Override
    public boolean isPageLoaded() {
        return false;
    }
}
