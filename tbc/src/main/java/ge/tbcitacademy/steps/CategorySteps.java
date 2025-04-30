package ge.tbcitacademy.steps;

import ge.tbcitacademy.data.enums.CategoryName;
import ge.tbcitacademy.data.enums.PetSubCategory;
import ge.tbcitacademy.data.enums.RestSubCategory;
import ge.tbcitacademy.pages.HomePage;
import ge.tbcitacademy.pages.CategoryPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static ge.tbcitacademy.data.constants.Constants.CATEGORY;

public class CategorySteps {
    public CategoryPage categoryPage = new CategoryPage();
    HomePage homePage = new HomePage();

    public CategorySteps clickCategoriesButton() {
        homePage.categoryBtn.shouldBe(visible).click();
        return this;
    }

    public CategorySteps findCategoryByName(CategoryName categoryName) {
        $(By.xpath("//div[@data-testid=\"categories-dropdown\"]//h4[contains(text(),'" + categoryName.getValue() + "')]")).hover();
        return this;
    }

    public CategorySteps clickSubCategoryByName(RestSubCategory restSubCategory) {
        $(By.xpath("//div[@data-testid='categories-dropdown']/following-sibling::div//h4[@weight='regular' and contains(text(),'" + restSubCategory.getValue() + "')]"))
                .shouldBe(visible)
                .click();
        return this;
    }

    public CategorySteps clickSubCategoryByName(PetSubCategory petSubCategory) {
        $(By.xpath("//div[@data-testid='categories-dropdown']/following-sibling::div//h4[@weight='regular' and contains(text(),'" + petSubCategory.getValue() + "')]"))
                .shouldBe(visible)
                .click();
        return this;
    }


    public CategorySteps validateUrlContainsCategory() {
        webdriver().shouldHave(urlContaining(CATEGORY));
        return this;
    }
}
