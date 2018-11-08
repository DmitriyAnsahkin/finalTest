package stepdefs;

import other.ForCheck;
import other.Init;
import steps.OzonCartSteps;
import steps.OzonCatalogSteps;
import steps.OzonMainPageSteps;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;


public class MyStepdefs {

    OzonMainPageSteps ozonMainPageSteps;
    OzonCatalogSteps ozonCatalogSteps;
    OzonCartSteps ozonCartSteps;

    @Before
    public void begin() {
        //2.	Развернуть окно на весь экран
        Init.begin(true);
        ForCheck.clearVar();
        ozonMainPageSteps = new OzonMainPageSteps();
        ozonCatalogSteps = new OzonCatalogSteps();
        ozonCartSteps = new OzonCartSteps();
    }

    @After
    public void end() {
        Init.end(true);
    }

    @Дано("^открыт сайт ozon и развернут на весь экран$")
    public void openSite() {
    }

    @Когда("^происходит нажатие на кнопку Меню - \"(.*)\"$")
    public void clickMenuCategory(String category) {
        ozonMainPageSteps.selectSubCategory(category);
    }

    @Тогда("^выбираем подкатегорию \"(.*)\"$")
    public void setSubCategory(String category)  {
        ozonCatalogSteps.clickCategory(category);
    }

    @И("^Выбираем производителя \"(.*)\"$")
    public void setBrand(String brand) {
        ozonCatalogSteps.setBrand(brand);
    }

    @И("^Выбираем производителя \"(.*)\" и \"(.*)\"$")
    public void setBrand(String brand, String brand2) {
        ozonCatalogSteps.setBrand(brand, brand2);
    }

    @И("^устанавливаем фильтры цена от - \"(.*)\" рублей$")
    public void priceFrom(int from) {
        ozonCatalogSteps.setPriceFrom(from, "TAB");
    }

    @Когда("^фильтры применятся, добавить первый товар в корзину, запомнить название и цену$")
    public void buyFirstProdInStock() {
        ozonCatalogSteps.buyFirstProdInStock();
    }

    @И("^произвести переход в козину$")
    public void clickCart() {
        ozonCartSteps.clickCart();
    }


    @Когда("^корзина будет открыта, проверить наличие товара и цены$")
    public void checkCartProd() {
        ozonCartSteps.checkCartProd();
    }

    @И("^нажать на кнопку удалить все$")
    public void deleteAll() {
        ozonCartSteps.deleteAll();
    }

    @И("^проверить что корзина пустая$")
    public void isCartEmpty() {
        ozonCartSteps.isCartEmpty();
    }

    @И("^выбираем меню подкатегории \"([^\"]*)\"$")
    public void setCategory(String category) {
        ozonCatalogSteps.setCategory(category);
    }

}

