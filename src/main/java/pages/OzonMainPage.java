package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OzonMainPage extends BasePage {

    @FindBy(xpath = "//div[@class='bHeaderCatalogButton ']")
    WebElement menuShow;



    protected final String categoryMenu = "//span[@class='eOzonCatalog_1LevelLinkText' and contains(text(), '%s')]";
    protected final String subCategory = "//div[@class='links-block-wrap']//a[contains(text(),'%s')]";
    protected final String brand = "//div[contains(text(),'Бренды')]/..//span[@class='label-text' " +
            "and contains(text(),'%s')]/../span[@class='checkmark']";


    public OzonMainPage clickMenuShow (){
        click(menuShow);
        return this;
    }

    public OzonMainPage clickToCategoryMenu (String category){
        WebElement temp = getWebElement(String.format(categoryMenu, category));
        temp.click();
        return this;
    }

    @Override
    public boolean isPageLoaded() {
        return false;
    }
    //div[contains(text(),'Бренды')]/..//span[@class='label-text' and contains(text(),'Apple')]/../span[@class='checkmark']

}
