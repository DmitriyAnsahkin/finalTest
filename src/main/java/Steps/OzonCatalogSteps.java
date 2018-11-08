package Steps;

import pages.OzonCatalog;
import io.qameta.allure.Step;

public class OzonCatalogSteps {
    OzonCatalog ozonCatalog = new OzonCatalog();
    @Step("4. Выбрать категорию – {0}")
    public OzonCatalogSteps clickCategory(String category) {
        ozonCatalog.clickCategoryItem(category);
        return this;
    }
    @Step("6. Заполнить цена от – {0}")
    public OzonCatalogSteps setPriceFrom(int from, String keys) {
        ozonCatalog.waitPreload()
        .setPriceFrom(from)
        .clickButtonShow();
                //clickOn();

        return this;
    }
    @Step("7. Добавить первый товар в корзину, запомнить название и цену")
    public OzonCatalogSteps buyFirstProdInStock() {
        ozonCatalog.waitPreload()
        .buyProdInStock();
        return this;
    }

    public OzonCatalogSteps setCategory(String category) {
        ozonCatalog.waitPreload()
        .setCategory(category);
        return this;
    }
    @Step("5. Выбрать производителя – {0}")
    public OzonCatalogSteps setBrand(String... brand) {

        if (brand.length > 0) {
            for (String s : brand) {
                ozonCatalog.waitPreload()
                .setBrandX(s);
            }

        }
        return this;
    }
}
