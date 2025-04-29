import ge.tbcitacademy.data.enums.CategoryName;
import ge.tbcitacademy.data.enums.NavElement;
import ge.tbcitacademy.data.enums.NumberOfGuest;
import ge.tbcitacademy.data.enums.PetSubCategory;
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
                .findCategoryByName(CategoryName.PETS)
                .clickSubCategoryByName(PetSubCategory.ANIMAL_CARE)
                .validateUrlContainsCategory();

        List<Deal> page1Deals = searchSteps.getSearchResults();
        System.out.println(page1Deals);
    }

    @Test
    public void numberOfGuestsTest(){
        homeSteps.findNavbarElement(NavElement.FOOD);
        foodSteps.chooseNumberOfGuests(NumberOfGuest.ELEVEN_TO_FIFTEEN);
    }
}
