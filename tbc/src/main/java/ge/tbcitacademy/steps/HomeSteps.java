package ge.tbcitacademy.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import ge.tbcitacademy.data.enums.NavElement;
import ge.tbcitacademy.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static ge.tbcitacademy.data.constants.Constants.*;
import static org.testng.Assert.assertEquals;

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
        homePage.emptyResult.shouldBe(visible)
                .shouldHave(Condition.text(WITHOUT_RESULT));
        return this;
    }

    public HomeSteps findNavbarElement(NavElement navElement) {
        $(By.xpath(String.format(homePage.navbarElementXPath, navElement.getValue()))).click();
        return this;
    }

    public HomeSteps validateOfferCardsInGrid(int numberOfCards) {
        List<Integer> yCoordinates = homePage.searchedProduct.stream()
                .limit(numberOfCards)
                .map(el -> el.getLocation().getY())
                .distinct()
                .toList();

        assertEquals(COUNTER, yCoordinates.size(), EXPECTING);
        return this;
    }

    public HomeSteps validateNoHorizontalScroll() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        boolean hasHorizontalScroll = (Boolean) js.executeScript(
                "return document.documentElement.scrollWidth > document.documentElement.clientWidth;"
        );

        Assert.assertFalse(hasHorizontalScroll, HORIZONTAL_IS_ON);
        return this;
    }

    public HomeSteps validateStickyHeader() {
        SelenideElement navBar = homePage.navBar;
        int initialY = navBar.getLocation().getY();

        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript(SCROLL_BY);
        int postScrollY = navBar.getLocation().getY();

        Assert.assertEquals(postScrollY, initialY, HEADER_STICKY);
        return this;
    }
}
