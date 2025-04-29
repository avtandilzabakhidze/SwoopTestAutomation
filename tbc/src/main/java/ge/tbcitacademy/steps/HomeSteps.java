package ge.tbcitacademy.steps;

import com.codeborne.selenide.Condition;
import ge.tbcitacademy.data.enums.NavElement;
import ge.tbcitacademy.pages.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static ge.tbcitacademy.data.constants.Constants.*;

public class HomeSteps {
    BasePage basePage = new BasePage();

    public HomeSteps openHomePage() {
        open(BASE_URL);
        return this;
    }

    public HomeSteps setSearchInput(String input) {
        basePage.searchInput.clear();
        basePage.searchInput.setValue(input);
        return this;
    }

    public HomeSteps pressSearchBtn() {
        basePage.searchBtn.click();
        return this;
    }

    public HomeSteps validateUrlContainsSearch() {
        webdriver().shouldHave(urlContaining(SEARCH));
        return this;
    }

    public HomeSteps validateEmptyResultMessage() {
        basePage.emptyResult.shouldBe(Condition.visible)
                .shouldHave(Condition.text(WITHOUT_RESULT));
        return this;
    }

    public HomeSteps findNavbarElement(NavElement navElement) {
        $(By.xpath("//a//img/following-sibling::p[contains(text(),'" + navElement.getValue() + "')]")).click();
        return this;
    }
}
