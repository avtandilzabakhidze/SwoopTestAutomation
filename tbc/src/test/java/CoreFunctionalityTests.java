import ge.tbcitacademy.data.enums.*;
import ge.tbcitacademy.data.models.Offer;
import ge.tbcitacademy.steps.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static ge.tbcitacademy.data.constants.Constants.*;

public class CoreFunctionalityTests extends BaseTest {
    private HomeSteps homeSteps;
    private SearchSteps searchSteps;
    private CategorySteps categoriesSteps;
    private FilterSteps filterSteps;
    private PaginationSteps paginationSteps;

    @BeforeMethod
    public void setUp() {
        homeSteps = new HomeSteps();
        searchSteps = new SearchSteps();
        categoriesSteps = new CategorySteps();
        filterSteps = new FilterSteps();
        paginationSteps = new PaginationSteps();
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
                .validateEmptyResultMessage();
    }

    @Test(priority = 2)
    public void paginationTest() {
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.VACATION)
                .clickSubCategoryByName(RestSubCategory.KAKHETI)
                .validateUrlContainsCategory();

        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        categoriesSteps.searchedPageIsOpened();
        List<Offer> page1Offers = searchSteps.getSearchResults();

        searchSteps.setPageNumber(TWO);
        categoriesSteps.searchedPageIsOpened();
        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        List<Offer> page2Offers = searchSteps.getSearchResults();
        searchSteps.validateDealsAreDifferent(page1Offers, page2Offers)
                .setPageNumber(THREE);
        categoriesSteps.searchedPageIsOpened();
        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue());
        List<Offer> page3Offers = searchSteps.getSearchResults();
        searchSteps.validateDealsAreDifferent(page2Offers, page3Offers);

        searchSteps.validateSelectedCategory(RestSubCategory.KAKHETI.getValue())
                .setPageNumber(ONE);
        paginationSteps.validateActivePageNumber(COUNTER)
                .paginateThroughAllPagesAndBack()
                .validateActivePageNumber(COUNTER);
    }

    @Test(priority = 3)
    public void numberOfGuestsTest() {
        homeSteps.findNavbarElement(NavElement.FOOD);
        filterSteps.chooseNumberOfGuests(NumberOfGuest.TWO_TO_FIVE);
        searchSteps.validateSelectedCategory(NavElement.FOOD.getValue())
                .validateGuestCountInDeals(searchSteps.getSearchResults(), NumberOfGuest.TWO_TO_FIVE);
    }

    @Test(priority = 4)
    public void offerDetailsConsistencyTest() {
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.PETS)
                .clickSubCategoryByName(PetSubCategory.ANIMAL_CARE);
        Offer first = searchSteps.findFirst();

        searchSteps.openFirst()
                .validateDealInfoConsistentIgnoringSoldAndPriceFormat(first, searchSteps.grabDetails());
    }

    @Test(priority = 5)
    public void filterPersistenceTest() {
        categoriesSteps.clickCategoriesButton()
                .findCategoryByName(CategoryName.PETS)
                .clickSubCategoryByName(PetSubCategory.ANIMAL_CARE);

        filterSteps.chooseAddress(Address.SABURTALO)
                .choosePriceRange(PriceRange.ZERO_TO_HUNDRED);
        categoriesSteps.searchedPageIsOpened();

        List<Offer> beforeNavigationOffers = searchSteps.getSearchResults();
        searchSteps.validateResultsWithinPriceRange(beforeNavigationOffers, PriceRange.ZERO_TO_HUNDRED);

        searchSteps.openFirst()
                .backBrowser();
        searchSteps.filterKeywordsIsDisplay();
    }
}
