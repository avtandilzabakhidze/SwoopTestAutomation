import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static ge.tbcitacademy.data.constants.Constants.COUNTER_SEC;
import static ge.tbcitacademy.data.constants.Constants.SCREEN_SIZE;

public class BaseTest {
    @BeforeMethod
    public void setUpBuild() {
        Configuration.timeout = COUNTER_SEC;
        Configuration.browserSize = SCREEN_SIZE;
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}