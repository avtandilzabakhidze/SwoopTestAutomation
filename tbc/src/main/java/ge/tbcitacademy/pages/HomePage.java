package ge.tbcitacademy.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public final SelenideElement searchInput = $(By.xpath("//div[@data-testid=\"search-input-wrapper\"]//input")),
            searchBtn = $(By.xpath("//div[@data-testid=\"search-input-wrapper\"]//button//img[@alt=\"search\"]")),
             emptyResult= $(By.xpath("//*[contains(@id,'next')]//h2")),
             categoryBtn= $(By.xpath("//button//p[text() ='კატეგორიები']"));
}
