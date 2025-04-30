package ge.tbcitacademy.steps;

import com.codeborne.selenide.Condition;
import ge.tbcitacademy.data.enums.NavElement;
import ge.tbcitacademy.pages.HomePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static ge.tbcitacademy.data.constants.Constants.*;

public class HomeSteps {
    HomePage homePage = new HomePage();

    public HomeSteps openHomePage() {
        open(BASE_URL);
        return this;
    }

    public HomeSteps clearSearchInput() {
        homePage.searchInput.clear();
        return this;
    }

    public HomeSteps setSearchInput(String input) {
        homePage.searchInput.setValue(input);
        return this;
    }

    public HomeSteps pressSearchBtn() {
        homePage.searchBtn.click();
        return this;
    }

    public HomeSteps validateUrlContainsSearch() {
        webdriver().shouldHave(urlContaining(SEARCH));
        return this;
    }

    public HomeSteps validateEmptyResultMessage() {
        homePage.emptyResult.shouldBe(Condition.visible)
                .shouldHave(Condition.text(WITHOUT_RESULT));
        return this;
    }

    public HomeSteps findNavbarElement(NavElement navElement) {
        $(By.xpath(String.format(homePage.navbarElementXPath, navElement.getValue()))).click();
        return this;
    }
}
