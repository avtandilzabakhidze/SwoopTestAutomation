import ge.tbcitacademy.steps.FooterSteps;
import ge.tbcitacademy.steps.HeaderSteps;
import ge.tbcitacademy.steps.HomeSteps;
import ge.tbcitacademy.steps.ScreenSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static ge.tbcitacademy.data.constants.Constants.*;

public class UIResponsivenessTests {
    HomeSteps homeSteps;
    FooterSteps footerSteps;
    ScreenSteps screenSteps;
    HeaderSteps headerSteps;

    @BeforeMethod
    public void setup() {
        open(BASE_URL);
        footerSteps = new FooterSteps();
        homeSteps = new HomeSteps();
        screenSteps = new ScreenSteps();
        headerSteps = new HeaderSteps();
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    public void mobileResponsiveTest1920x1080() {
        screenSteps.setViewportSize(SIZE_1920, SIZE_1080);

        headerSteps.validateSearchBarVisible()
                .validateNavBarAndBurgerMenu();
        homeSteps.validateOfferCardsInGrid(THREE_INT);
        footerSteps.validateFooterLinksHorizontal();
    }

    @Test
    public void tabletResponsiveTest768x1024() {
        screenSteps.setViewportSize(SIZE_768, SIZE_1024);
        headerSteps.validateSearchBarIconVisible()
                .validateNavBarAndBurgerMenuIsVisible();
        homeSteps.validateOfferCardsInGrid(COUNTER);

        footerSteps.validateFooterLinksVertical();
        homeSteps.validateNoHorizontalScroll();
    }

    @Test
    public void mobileResponsiveTest375x667() {
        screenSteps.setViewportSize(SIZE_375, SIZE_667);
        headerSteps.validateSearchBarIconVisible()
                .visibleBurgerMenu();
        homeSteps.validateOfferCardsInGrid(COUNTER);
        footerSteps.validateFooterLinksVertical();
        homeSteps.validateNoHorizontalScroll();
    }
}
