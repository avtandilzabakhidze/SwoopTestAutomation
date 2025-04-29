package ge.tbcitacademy.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class FoodPage {
    public final SelenideElement  numberOfGuests=  $(xpath("//input[contains(@id,\"radio\")]/following-sibling::span[contains(text(),'2-5')]"));
}
