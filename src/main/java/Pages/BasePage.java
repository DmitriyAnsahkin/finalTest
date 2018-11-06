package Pages;

import Other.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.function.Function;

public abstract class BasePage {
    public abstract boolean isPageLoaded();

    WebDriver driver;

    public BasePage() {
        this.driver = Init.getDriver();
        PageFactory.initElements(getDriver(), this);
    }

    public void click(WebElement element) {
        new WebDriverWait(driver, 8).until(ExpectedConditions.visibilityOf(element));
        scrollTo(element);
        element.click();
//        WebElement webElement = Init.getDriver().findElement(By.xpath("//*[@id='desktopMenuMain']//a[contains(text(), 'Меню доставки')]"));
//        webElement.click();
    }

    public void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public void waitVisible(WebElement webElement) {
        //new WebDriverWait(driver, 8).until(ExpectedConditions.visibilityOf(webElement));
        new WebDriverWait(driver, 8).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitVisible(String xpath) {
        new WebDriverWait(driver, 8).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
    }

    public WebElement getWebElement(String xpath) {
        return new WebDriverWait(driver, 8).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
    }

    public WebElement getWebElement(WebElement webElement) {
        return new WebDriverWait(driver, 8).until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitOf(Function func) {
        new WebDriverWait(driver, 5).until(func);
    }

    public void setText(String text) {
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(String string) {
        try {
            driver.findElement(By.xpath(string));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}

