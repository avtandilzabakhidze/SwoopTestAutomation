package ge.tbcitacademy.steps;

import ge.tbcitacademy.data.enums.NumberOfGuest;
import ge.tbcitacademy.pages.FoodPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FoodSteps {
    FoodPage foodPage = new FoodPage();

    public FoodSteps chooseNumberOfGuests(NumberOfGuest numberOfGuest) {
        $(By.xpath("//input[contains(@id,\"radio\")]/following-sibling::span[contains(text(),'" + numberOfGuest.getValue() + "')]")).click();
        return this;
    }
}
