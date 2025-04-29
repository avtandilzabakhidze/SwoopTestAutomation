package ge.tbcitacademy.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductDetails {
    public final SelenideElement title = $(By.xpath("//h2")),
            description = $(By.xpath("//a[contains(@href,'/merchant/')]//p")),
            soldQuantity = $(By.xpath("//img[@src=\"/icons/Ellipse.svg\"]/following-sibling::div//p[2]")),
            price = $(By.xpath("//a[contains(@href,'/merchant/')]//p"));
}
