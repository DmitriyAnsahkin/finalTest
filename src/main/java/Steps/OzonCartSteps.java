package Steps;

import Other.ForCheck;
import Pages.OzonCart;
import cucumber.api.java.uk.Нехай;
import io.qameta.allure.Step;
import org.junit.Assert;

public class OzonCartSteps {
    OzonCart ozonCart = new OzonCart();
    @Step("8. Перейти в корзину")
    public OzonCartSteps clickCart (){
        ozonCart.clickCart();
        return this;
    }
    @Step("9. Проверить, что в корзине есть добавленный товар")
    public OzonCartSteps checkCartProd(){
        ozonCart.readProdInCartAndCheck();
        if (!ForCheck.listProdInCart.equals(ForCheck.getListProdInMind())) {
            Assert.fail("Товары добавляемые в ходе теста не соответсвуют списку товаров в корзине");
        }

        if ((ozonCart.getTotalSumFromCart() != ForCheck.getTotalPrice())){
            Assert.fail("Сумма товаров добавленных в ходе теста не соответсвуют сумме товаров в корзине");
        }

        return this;
    }
    @Step("10. Нажать на удалить все")
    public OzonCartSteps deleteAll (){
        ozonCart.clickDeleteAll();

        return this;
    }
    @Step("11. Проверить, что корзина пуста.")
    public OzonCartSteps isCartEmpty (){

        ozonCart.isCartEmpty();
        return this;
    }
}
