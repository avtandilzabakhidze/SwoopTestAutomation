package ge.tbcitacademy.steps;

import com.codeborne.selenide.Condition;
import ge.tbcitacademy.data.enums.Address;
import ge.tbcitacademy.data.enums.NumberOfGuest;
import ge.tbcitacademy.data.enums.PriceRange;
import ge.tbcitacademy.pages.FoodPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class FoodSteps {
    FoodPage foodPage = new FoodPage();

    public FoodSteps chooseNumberOfGuests(NumberOfGuest numberOfGuest) {
        $(By.xpath("//input[contains(@id,\"radio\")]/following-sibling::span[contains(text(),'" + numberOfGuest.getValue() + "')]")).click();
        return this;
    }

    public FoodSteps chooseAddress(Address address) {
        $(By.xpath("//input[contains(@id,\"checkbox-მდებარეობა\")]/following-sibling::span[contains(text(),'" + address.getValue() + "')]")).click();
        return this;
    }

    public FoodSteps choosePriceRange(PriceRange priceRange) {
        $(By.xpath("//div[contains(@id,\"headlessui-radiogroup-option\")]//p[contains(text(),'" + priceRange.getValue() + "')]")).click();
        return this;
    }
}
