package ge.tbcitacademy.pages;

public class FilterPage {
    public final String guestOptionXPath = "//input[contains(@id,'radio')]/following-sibling::span[contains(text(),'%s')]",
            addressOptionXPath = "//input[contains(@id,'checkbox-მდებარეობა')]/following-sibling::span[contains(text(),'%s')]",
            priceRangeXPath = "//div[contains(@id,'headlessui-radiogroup-option')]//p[contains(text(),'%s')]";
}
