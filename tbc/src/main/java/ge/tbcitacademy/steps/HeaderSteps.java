package ge.tbcitacademy.steps;

import ge.tbcitacademy.pages.HomePage;

import static com.codeborne.selenide.Condition.visible;

public class HeaderSteps {
    HomePage homePage = new HomePage();

    public HeaderSteps validateSearchBarVisible() {
        homePage.searchInput.shouldBe(visible);
        return this;
    }

    public HeaderSteps validateSearchBarIconVisible() {
        homePage.searchIcon.shouldBe(visible);
        return this;
    }

    public HeaderSteps visibleBurgerMenu() {
        homePage.burgerMenu.should(visible);
        return this;
    }

    public HeaderSteps validateNavBarAndBurgerMenuIsVisible() {
        homePage.navBar.shouldBe(visible);
        homePage.burgerMenu.shouldBe(visible);
        return this;
    }

    public HeaderSteps validateNavBarAndBurgerMenu() {
        homePage.navBar.shouldBe(visible);
        homePage.burgerMenu.shouldNotBe(visible);
        return this;
    }
}
