package ge.tbcitacademy.steps;

import com.codeborne.selenide.Condition;
import ge.tbcitacademy.data.enums.CategoryName;
import ge.tbcitacademy.data.enums.PetSubCategory;
import ge.tbcitacademy.data.enums.RestSubCategory;
import ge.tbcitacademy.pages.CategoryPage;
import ge.tbcitacademy.pages.HomePage;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static ge.tbcitacademy.data.constants.Constants.CATEGORY;
import static ge.tbcitacademy.data.constants.Constants.NUMBER_OF_SECONDS;

public class CategorySteps {
    CategoryPage categoryPage = new CategoryPage();
    HomePage homePage = new HomePage();

    public CategorySteps clickCategoriesButton() {
        homePage.categoryBtn.shouldBe(visible).click();
        return this;
    }

    public CategorySteps findCategoryByName(CategoryName categoryName) {
        $(By.xpath(String.format(categoryPage.categoryByNameXPath, categoryName.getValue()))).hover();
        return this;
    }

    public CategorySteps clickSubCategoryByName(RestSubCategory restSubCategory) {
        $(By.xpath(String.format(categoryPage.subCategoryByNameXPath, restSubCategory.getValue())))
                .shouldBe(visible)
                .click();
        return this;
    }

    public CategorySteps clickSubCategoryByName(PetSubCategory petSubCategory) {
        $(By.xpath(String.format(categoryPage.subCategoryByNameXPath, petSubCategory.getValue())))
                .shouldBe(visible)
                .click();
        return this;
    }

    public CategorySteps validateUrlContainsCategory() {
        webdriver().shouldHave(urlContaining(CATEGORY));
        return this;
    }

    public CategorySteps searchedPageIsOpened() {
        $(categoryPage.resultTitle).shouldHave(Condition.exist, Duration.ofSeconds(NUMBER_OF_SECONDS));
        return this;
    }
}
