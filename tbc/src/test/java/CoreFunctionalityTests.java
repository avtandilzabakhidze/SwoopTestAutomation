import com.codeborne.selenide.Condition;
import ge.tbcitacademy.data.enums.*;
import ge.tbcitacademy.data.models.Deal;
import ge.tbcitacademy.steps.CategorySteps;
import ge.tbcitacademy.steps.FoodSteps;
import ge.tbcitacademy.steps.HomeSteps;
import ge.tbcitacademy.steps.SearchSteps;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static ge.tbcitacademy.data.constants.Constants.RANDOM_WORD;
import static ge.tbcitacademy.data.constants.Constants.WINE;

public class CoreFunctionalityTests extends BaseTest {
    private HomeSteps homeSteps;
    private SearchSteps searchSteps;
    private CategorySteps categoriesSteps;
    private FoodSteps foodSteps;

    @BeforeMethod
    public void setUp() {
        homeSteps = new HomeSteps();
        searchSteps = new SearchSteps();
        categoriesSteps = new CategorySteps();
        foodSteps = new FoodSteps();
        homeSteps.openHomePage();
    }

    @Test
    public void searchTest() {
        homeSteps.setSearchInput(WINE)
                .pressSearchBtn()
                .validateUrlContainsSearch();

        List<Deal> searchResults = searchSteps.getSearchResults();
        searchSteps.validateResultsNotEmpty(searchResults)
                .validateResultsContainKeyword(searchResults, WINE);

        homeSteps.clearSearchInput()
                .setSearchInput(RANDOM_WORD)
                .pressSearchBtn()
                .validateEmptyResultMessage()
                .validateUrlContainsSearch();
    }

    @Test
    public void paginationTest() {
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.VACATION)
                .clickSubCategoryByName(RestSubCategory.KAKHETI)
                .validateUrlContainsCategory();

        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        $(By.xpath("//div[contains(@class,'items-baseline')]//h3")).shouldHave(Condition.exist, Duration.ofSeconds(10));
        List<Deal> page1Deals = searchSteps.getSearchResults();

        searchSteps.setPageNumber("2");
        $(By.xpath("//div[contains(@class,'items-baseline')]//h3")).shouldHave(Condition.exist, Duration.ofSeconds(10));
        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        List<Deal> page2Deals = searchSteps.getSearchResults();
        System.out.println(page2Deals);
        searchSteps.validateDealsAreDifferent(page1Deals, page2Deals);

        searchSteps.setPageNumber("3");
        $(By.xpath("//div[contains(@class,'items-baseline')]//h3")).shouldHave(Condition.exist, Duration.ofSeconds(10));
        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        List<Deal> page3Deals = searchSteps.getSearchResults();
        searchSteps.validateDealsAreDifferent(page2Deals, page3Deals);

        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        searchSteps.setPageNumber("1")
                .validateActivePageNumber(1)
                .paginateThroughAllPagesAndBack();
    }

    @Test
    public void numberOfGuestsTest() {
        homeSteps.findNavbarElement(NavElement.FOOD);
        foodSteps.chooseNumberOfGuests(NumberOfGuest.TWO_TO_FIVE);
        searchSteps.validateSelectedCategory(NavElement.FOOD.getValue());
        List<Deal> searchResults = searchSteps.getSearchResults();
        searchSteps.validateGuestCountInDeals(searchResults, NumberOfGuest.TWO_TO_FIVE);
    }

    @Test
    public void offerDetailsConsistencyTest() {
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.PETS)
                .clickSubCategoryByName(PetSubCategory.ANIMAL_CARE);
        Deal first = searchSteps.findFirst();

        searchSteps.openFirst();
        Deal deal = searchSteps.grabDetails();
        searchSteps.validateDealInfoConsistentIgnoringSoldAndPriceFormat(first, deal);
    }


    @Test
    public void filterPersistenceTest() {
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.PETS)
                .clickSubCategoryByName(PetSubCategory.ANIMAL_CARE);

    }
}
