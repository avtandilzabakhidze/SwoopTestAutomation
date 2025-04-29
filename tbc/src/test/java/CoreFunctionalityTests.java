import ge.tbcitacademy.data.enums.*;
import ge.tbcitacademy.data.models.Deal;
import ge.tbcitacademy.steps.CategorySteps;
import ge.tbcitacademy.steps.FoodSteps;
import ge.tbcitacademy.steps.HomeSteps;
import ge.tbcitacademy.steps.SearchSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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
    public void searchWithValidKeywordTest() {
        homeSteps.setSearchInput(WINE)
                .pressSearchBtn()
                .validateUrlContainsSearch();

        List<Deal> searchResults = searchSteps.getSearchResults();
        searchSteps.validateResultsNotEmpty(searchResults)
                .validateResultsContainKeyword(searchResults, WINE);
    }

    @Test
    public void searchWithInvalidKeywordTest() {
        homeSteps.setSearchInput(RANDOM_WORD)
                .pressSearchBtn()
                .validateUrlContainsSearch()
                .validateEmptyResultMessage();
    }

    @Test
    public void categoryTest() {
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.VACATION)
                .clickSubCategoryByName(RestSubCategory.KAKHETI)
                .validateUrlContainsCategory();

        List<Deal> page1Deals = searchSteps.getSearchResults();
        searchSteps.validateResultsNotEmpty(page1Deals);

        searchSteps.setPageNumber("2");
        List<Deal> page2Deals = searchSteps.getSearchResults();
        searchSteps.validateDealsAreDifferent(page1Deals, page2Deals)
                .validateResultsNotEmpty(page2Deals);

        searchSteps.setPageNumber("3");
        List<Deal> page3Deals = searchSteps.getSearchResults();
        searchSteps.validateDealsAreDifferent(page2Deals, page3Deals)
                .validateResultsNotEmpty(page3Deals);
    }

    @Test
    public void numberOfGuestsTest(){
        homeSteps.findNavbarElement(NavElement.FOOD);
        foodSteps.chooseNumberOfGuests(NumberOfGuest.TWO_TO_FIVE);
        List<Deal> searchResults = searchSteps.getSearchResults();
        searchSteps.validateGuestCountInDeals(searchResults, NumberOfGuest.TWO_TO_FIVE);
    }

    @Test
    public void offerDetailsConsistencyTest(){
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.PETS)
                .clickSubCategoryByName(PetSubCategory.ANIMAL_CARE);
        Deal first = searchSteps.findFirst();
        System.out.println(first);
        searchSteps.openFirst();
        Deal deal = searchSteps.grabDetails();
        System.out.println(deal);
    }

    @Test
    public void filterPersistenceTest(){
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.PETS)
                .clickSubCategoryByName(PetSubCategory.ANIMAL_CARE);

    }
}
