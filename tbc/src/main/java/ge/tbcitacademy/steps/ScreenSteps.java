package ge.tbcitacademy.steps;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Dimension;

public class ScreenSteps {
    public void setViewportSize(int width, int height) {
        WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(width, height));
    }
}
