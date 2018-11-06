package Pages;

import Other.ForCheck;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OzonCart extends BasePage {
    @FindBy(xpath = "//span[@class='eMyOzon_Item_Bottom' and contains(text(), 'Корзина')]")
    WebElement cart;

    @FindBy(xpath = "//div[@class='eCartTotal_summPrice ']")
    WebElement totalPrice;

    @FindBy (xpath = "//div[@class='bIconButton mRemove mGray jsRemoveAll']")
    WebElement deleteAll;


    String status = "//*[@class='jsInnerContentpage_title']";
    String prodInCart = "//div[@class='jsViewCollection jsChild_DOM_items']//span[@class='eCartItem_nameValue']";



    public OzonCart clickCart() {
        click(cart);
        return this;
    }

    public OzonCart readProdInCartAndCheck() {
        List<WebElement> webElements = driver.findElements(By.xpath(prodInCart));

        if (webElements.size() > 0){
            for (WebElement webElement : webElements) {
                String s = webElement.getText();
                ForCheck.listProdInCart.add(s);
            }
        }
            return this;
    }

    public int getTotalSumFromCart (){
        waitVisible(totalPrice);
        return Integer.valueOf(totalPrice.getText().replaceAll("[^0-9]", ""));
    }

    public void clickDeleteAll (){
        waitVisible(deleteAll);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", deleteAll);
    }

    public OzonCart isCartEmpty(){
        WebElement temp = getWebElement(status);
//        if (temp.getText().replaceAll("", "") == "Корзина"){
//            int count = 0;
//            do {
//                if (temp.getText().replaceAll("", "") != "Корзина") break;
//                count ++;
//                if (count == 1000) Assert.fail("Таймаут. Козина не пуста");
//            } while (true);
//        }
//        if (!((temp.getText().replaceAll(" ", "")).equals("Корзинапуста"))) Assert.fail("Корзина не пустая!");
        return this;
    }
    @Override
    public boolean isPageLoaded() {
        return false;
    }

}
