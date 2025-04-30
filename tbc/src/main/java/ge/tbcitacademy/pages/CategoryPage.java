package ge.tbcitacademy.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CategoryPage {
    public final SelenideElement selectedCategory = $(By.xpath("//div[contains(@class,'items-baseline')]//h3"));
    public final String categoryByNameXPath = "//div[@data-testid='categories-dropdown']//h4[contains(text(),'%s')]",
            subCategoryByNameXPath = "//div[@data-testid='categories-dropdown']/following-sibling::div//h4[@weight='regular' and contains(text(),'%s')]";
}
