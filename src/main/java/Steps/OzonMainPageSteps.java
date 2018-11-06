package Steps;

import Pages.OzonMainPage;
import io.qameta.allure.Step;

public class OzonMainPageSteps {
    OzonMainPage ozonMainPage = new OzonMainPage();
    @Step("3. Выбрать пункт меню – {0}")
    public void selectSubCategory (String categoty){
        ozonMainPage.clickMenuShow().
        clickToCategoryMenu(categoty);

    }
}
