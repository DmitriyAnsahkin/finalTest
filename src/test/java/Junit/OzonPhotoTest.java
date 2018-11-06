package Junit;

import Other.ForCheck;
import Other.Init;
import Steps.OzonCartSteps;
import Steps.OzonCatalogSteps;
import Steps.OzonMainPageSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OzonPhotoTest {
    @Before
    public void begin() {
        //2.	Развернуть окно на весь экран
        Init.begin(true);
        ForCheck.clearVar();
    }

    @After
    public void end() {
        Init.end(false);
    }

    @Test
    public void ozonPhotoTest() {
        OzonMainPageSteps ozonMainPageSteps = new OzonMainPageSteps();
        OzonCatalogSteps ozonCatalogSteps = new OzonCatalogSteps();
        OzonCartSteps ozonCartSteps = new OzonCartSteps();
        //3.	Выбрать пункт меню – Электроника
        ozonMainPageSteps.selectSubCategory("Электроника");
        //4.	Выбрать категорию – Зеркальные фотоаппараты
        ozonCatalogSteps.clickCategory("Фотокамеры").
                setCategory("Зеркальные фотокамеры").
                //6.	Заполнить цена от – 80000
                setPriceFrom(80000, "TAB").
                //5.	Выбрать производителя – Nikon, Canon
                setBrand("Nikon", "Canon").
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
