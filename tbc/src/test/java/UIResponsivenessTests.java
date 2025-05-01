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
    ScreenSteps screenSteps;

    @BeforeMethod
    public void setup() {
        open(BASE_URL);
        homeSteps = new HomeSteps();
        screenSteps = new ScreenSteps();
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    public void mobileResponsiveTest1920x1080() {
        screenSteps.setViewportSize(SIZE_1920, SIZE_1080);

        homeSteps.validateSearchBarVisible()
                .validateNavBarAndBurgerMenu()
                .validateOfferCardsInGrid()
                .validateFooterLinksAlignment();
    }

    @Test
    public void tabletResponsiveTest768x1024() {
        screenSteps.setViewportSize(SIZE_768, SIZE_1024);
    }

    @Test
    public void mobileResponsiveTest375x667() {
        screenSteps.setViewportSize(SIZE_375, SIZE_667);
    }
}
