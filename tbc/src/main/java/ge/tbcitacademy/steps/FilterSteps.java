package ge.tbcitacademy.steps;

import ge.tbcitacademy.data.enums.Address;
import ge.tbcitacademy.data.enums.NumberOfGuest;
import ge.tbcitacademy.data.enums.PriceRange;
import ge.tbcitacademy.pages.FilterPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FilterSteps {
    FilterPage filterPage = new FilterPage();

    public FilterSteps chooseNumberOfGuests(NumberOfGuest numberOfGuest) {
        $(By.xpath(String.format(filterPage.guestOptionXPath, numberOfGuest.getValue()))).click();
        return this;
    }

    public FilterSteps chooseAddress(Address address) {
        $(By.xpath(String.format(filterPage.addressOptionXPath, address.getValue()))).click();
        return this;
    }

    public FilterSteps choosePriceRange(PriceRange priceRange) {
        $(By.xpath(String.format(filterPage.priceRangeXPath, priceRange.getValue()))).click();
        return this;
    }
}
