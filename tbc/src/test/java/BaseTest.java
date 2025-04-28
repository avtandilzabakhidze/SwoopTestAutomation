import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}