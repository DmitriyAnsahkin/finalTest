package junit;

import other.ForCheck;
import other.Init;
import steps.OzonCartSteps;
import steps.OzonCatalogSteps;
import steps.OzonMainPageSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OzonPhoneTest {
    @Before
    public void begin(){
        //2.	Развернуть окно на весь экран
        Init.begin(true);
        ForCheck.clearVar();
    }


    @After
    public void end(){
        Init.end(true);
    }

    @Test
    public void ozonPhoneTest(){
        OzonMainPageSteps ozonMainPageSteps = new OzonMainPageSteps();
        OzonCatalogSteps ozonCatalogSteps = new OzonCatalogSteps();
        OzonCartSteps ozonCartSteps = new OzonCartSteps();

        //3.	Выбрать пункт меню – Электроника

        ozonMainPageSteps.selectSubCategory("Электроника");
        //4.	Выбрать категорию – Телефоны
        ozonCatalogSteps.clickCategory("Смартфоны").
                //6.	Заполнить цена от – 50000
                setPriceFrom(50000, "TAB").
                //5.	Выбрать производителя – Apple
                setBrand("Apple").
                //7.	Добавить первый товар в корзину, запомнить название и цену
                buyFirstProdInStock();
        //8.	Перейти в корзину
        ozonCartSteps.clickCart()
                //9.	Проверить, что в корзине есть добавленный товар
                .checkCartProd()
                //10.	Нажать на удалить все
                .deleteAll()
                //11.	Проверить, что корзина пуста.
                .isCartEmpty();

    }
}
