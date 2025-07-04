package ge.tbcitacademy.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;

public class SearchResultsPage {
    public ElementsCollection searchedProduct = $$(xpath("//a[contains(@href,'/offers/')]")),
            choseData = $$(By.xpath("//p[@class=\"text-primary_black-100-value text-md leading-5 font-tbcx-medium\"]"));
    public By searchedFilters = xpath("//p[contains(@class,\"text-primary_black-100-value text-md leading-5 font-tbcx-medium\")]");

    public By title = xpath(".//h4"),
            description = xpath(".//div[contains(@class,'text-primary_black-50')]"),
            soldQuantity = xpath(".//div[contains(@class,'text-primary_black-100')]"),
            price = xpath(".//h4[@weight=\"bold\"]"),
            activePage = By.xpath("//div[contains(@class,'rounded-lg') and contains(@class,'primary_green')]");

    public final SelenideElement leftArrow = $(By.xpath("//div[contains(@class,'items-center justify-center rounded-lg')]//img[@alt=\"left arrow\"]")),
            rightArrow = $(By.xpath("//div[contains(@class,'items-center justify-center rounded-lg')]//img[@alt=\"right arrow\"]"));

    public final String right_Active = "//div[contains(@class,'items-center justify-center rounded-lg') and not(contains(@class,'opacity-50'))]//img[@alt='right arrow']",
            left_Active = "//div[contains(@class,'items-center justify-center rounded-lg') and not(contains(@class,'opacity-50'))]//img[@alt='left arrow']",
            pageNumberXPath = "//div[contains(@class,'rounded-lg') and contains(text(),'%s')]";
}
