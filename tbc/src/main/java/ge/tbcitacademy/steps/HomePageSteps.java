package ge.tbcitacademy.steps;

import com.codeborne.selenide.Condition;
import ge.tbcitacademy.pages.BasePage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.Selenide.webdriver;
import static ge.tbcitacademy.data.constants.Constants.BASE_URL;
import static ge.tbcitacademy.data.constants.Constants.SEARCH;
import static ge.tbcitacademy.data.constants.Constants.WITHOUT_RESULT;

public class HomePageSteps {
    BasePage basePage = new BasePage();

    public HomePageSteps openHomePage() {
        open(BASE_URL);
        return this;
    }

    public HomePageSteps setSearchInput(String input) {
        basePage.searchInput.clear();
        basePage.searchInput.setValue(input);
        return this;
    }

    public HomePageSteps pressSearchBtn() {
        basePage.searchBtn.click();
        return this;
    }

    public HomePageSteps validateUrlContainsSearch() {
        webdriver().shouldHave(urlContaining(SEARCH));
        return this;
    }

    public HomePageSteps validateEmptyResultMessage() {
        basePage.emptyResult.shouldBe(Condition.visible)
                .shouldHave(Condition.text(WITHOUT_RESULT));
        return this;
    }
}
