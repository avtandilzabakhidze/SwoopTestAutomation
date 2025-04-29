package ge.tbcitacademy.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;

public class SearchResultsPage {
    public ElementsCollection searchedProduct = $$(xpath("//a[contains(@href,'/offers/')]"));

    public By title = xpath(".//h4"),
            description = xpath(".//div[contains(@class,'text-primary_black-50')]"),
            soldQuantity = xpath(".//div[contains(@class,'text-primary_black-100')]"),
            price = xpath(".//h4[@weight=\"bold\"]");

    public final SelenideElement leftArrow = $(By.xpath("//div[contains(@class,'rounded-lg')]//img[@alt=\"left arrow\"]")),
            rightArrow = $(By.xpath("//div[contains(@class,'rounded-lg')]//img[@alt=\"right arrow\"]")),
            activeArrow = $(By.xpath("//div[contains(@class,'rounded-lg')]//img[@alt=\"right arrow\"]")),
            nonActiveArrow = $(By.xpath("//div[contains(@class,'rounded-lg') and contains(@class,'transition-colors') and contains(@class,'-mb-px')]"));
}
