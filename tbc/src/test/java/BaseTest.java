import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000; // 10 seconds
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}