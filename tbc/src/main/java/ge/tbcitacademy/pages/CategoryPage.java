package ge.tbcitacademy.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class CategoryPage {
    public final ElementsCollection categories = $$(By.xpath("//div[@data-testid=\"categories-dropdown\"]//h4"));
    public final ElementsCollection subCategories = $$(By.xpath("//div[@data-testid=\"categories-dropdown\"]/following-sibling::div//h4[@weight=\"regular\"]"));

}
