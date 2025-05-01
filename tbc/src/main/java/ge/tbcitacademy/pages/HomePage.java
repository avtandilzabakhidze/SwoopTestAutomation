package ge.tbcitacademy.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {
    public final SelenideElement searchInput = $(By.xpath("//div[@data-testid=\"search-input-wrapper\"]//input")),
            searchBtn = $(By.xpath("//div[@data-testid=\"search-input-wrapper\"]//button//img[@alt=\"search\"]")),
            searchIcon = $(By.xpath("//img[@src=\"/icons/search-light.svg\"]")),
            emptyResult = $(By.xpath("//*[contains(@id,'next')]//h2")),
            categoryBtn = $(By.xpath("//button//p[text() ='კატეგორიები']")),
            burgerMenu = $(By.xpath("//img[@src=\"/icons/segment.svg\"]")),
            navBar = $(By.xpath("//div[contains(@class,\"overflow-x-scroll\")]//div"));

    public ElementsCollection searchedProduct= $$(By.xpath("//a[contains(@href,\"https://auth.tnet.ge/ka/\")]//p[contains(@class,'underline ')]"));
    public final String navbarElementXPath = "//a//img/following-sibling::p[contains(text(),'%s')]";
}
