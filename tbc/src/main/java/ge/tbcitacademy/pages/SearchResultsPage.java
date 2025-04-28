package ge.tbcitacademy.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;

public class SearchResultsPage {
    public ElementsCollection searchedProduct = $$(xpath("//a[contains(@href,'/offers/')]"));

    public By title = xpath(".//h4"),
            description = xpath(".//div[contains(@class,'text-primary_black-50')]"),
            soldQuantity = xpath(".//div[contains(@class,'text-primary_black-100')]"),
            price = xpath(".//h4[@weight=\"bold\"]");
}
