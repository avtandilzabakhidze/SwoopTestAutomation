import ge.tbcitacademy.data.enums.*;
import ge.tbcitacademy.data.models.Deal;
import ge.tbcitacademy.steps.CategorySteps;
import ge.tbcitacademy.steps.FilterSteps;
import ge.tbcitacademy.steps.HomeSteps;
import ge.tbcitacademy.steps.SearchSteps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static ge.tbcitacademy.data.constants.Constants.*;

public class CoreFunctionalityTests extends BaseTest {
    private HomeSteps homeSteps;
    private SearchSteps searchSteps;
    private CategorySteps categoriesSteps;
    private FilterSteps filterSteps;

    @BeforeMethod
    public void setUp() {
        homeSteps = new HomeSteps();
        searchSteps = new SearchSteps();
        categoriesSteps = new CategorySteps();
        filterSteps = new FilterSteps();
        homeSteps.openHomePage();
    }

    @Test(priority = 1)
    public void searchTest() {
        homeSteps.setSearchInput(SWIM)
                .pressSearchBtn()
                .validateUrlContainsSearch();

        categoriesSteps.searchedPageIsOpened();
        searchSteps.validateResultsNotEmpty(searchSteps.getSearchResults())
                .validateResultsContainKeyword(searchSteps.getSearchResults(), SWIM);

        homeSteps.clearSearchInput()
                .setSearchInput(RANDOM_WORD)
                .pressSearchBtn()
                .validateEmptyResultMessage()
                .validateUrlContainsSearch();
    }

    @Test(priority = 2)
    public void paginationTest() {
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.VACATION)
                .clickSubCategoryByName(RestSubCategory.KAKHETI)
                .validateUrlContainsCategory();

        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        categoriesSteps.searchedPageIsOpened();
        List<Deal> page1Deals = searchSteps.getSearchResults();

        searchSteps.setPageNumber(TWO);
        categoriesSteps.searchedPageIsOpened();
        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        List<Deal> page2Deals = searchSteps.getSearchResults();
        searchSteps.validateDealsAreDifferent(page1Deals, page2Deals);

        searchSteps.setPageNumber(THREE);
        categoriesSteps.searchedPageIsOpened();
        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        List<Deal> page3Deals = searchSteps.getSearchResults();
        searchSteps.validateDealsAreDifferent(page2Deals, page3Deals);

        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        searchSteps.setPageNumber(ONE)
                .validateActivePageNumber(COUNTER)
                .paginateThroughAllPagesAndBack();
    }

    @Test(priority = 3)
    public void numberOfGuestsTest() {
        homeSteps.findNavbarElement(NavElement.FOOD);
        filterSteps.chooseNumberOfGuests(NumberOfGuest.TWO_TO_FIVE);
        searchSteps.validateSelectedCategory(NavElement.FOOD.getValue());
        searchSteps.validateGuestCountInDeals(searchSteps.getSearchResults(), NumberOfGuest.TWO_TO_FIVE);
    }

    @Test(priority = 4)
    public void offerDetailsConsistencyTest() {
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.PETS)
                .clickSubCategoryByName(PetSubCategory.ANIMAL_CARE);
        Deal first = searchSteps.findFirst();

        searchSteps.openFirst();
        searchSteps.validateDealInfoConsistentIgnoringSoldAndPriceFormat(first, searchSteps.grabDetails());
    }

    @Test(priority = 5)
    public void filterPersistenceTest() {
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.PETS)
                .clickSubCategoryByName(PetSubCategory.ANIMAL_CARE);

        filterSteps.chooseAddress(Address.SABURTALO);
        filterSteps.choosePriceRange(PriceRange.ZERO_TO_HUNDRED);
        categoriesSteps.searchedPageIsOpened();

        List<Deal> beforeNavigationDeals = searchSteps.getSearchResults();
        searchSteps.validateResultsWithinPriceRange(beforeNavigationDeals, PriceRange.ZERO_TO_HUNDRED);

        searchSteps.openFirst();
        searchSteps.backBrowser();
        categoriesSteps.searchedPageIsOpened();
        searchSteps.filterKeywordsIsDisplay();
    }
}
