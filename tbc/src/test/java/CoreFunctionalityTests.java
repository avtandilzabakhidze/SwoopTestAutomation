import ge.tbcitacademy.data.models.Deal;
import ge.tbcitacademy.steps.HomePageSteps;
import ge.tbcitacademy.steps.SearchSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static ge.tbcitacademy.data.constants.Constants.RANDOM_WORD;
import static ge.tbcitacademy.data.constants.Constants.WINE;

public class CoreFunctionalityTests extends BaseTest {
    private HomePageSteps homePageSteps;
    private SearchSteps searchSteps;

    @BeforeMethod
    public void setUp() {
        homePageSteps = new HomePageSteps();
        searchSteps = new SearchSteps();
        homePageSteps.openHomePage();
    }

    @Test
    public void searchWithValidKeywordTest() {
        homePageSteps.setSearchInput(WINE)
                .pressSearchBtn()
                .validateUrlContainsSearch();

        List<Deal> searchResults = searchSteps.getSearchResults();
        searchSteps.validateResultsNotEmpty(searchResults)
                .validateResultsContainKeyword(searchResults, WINE);
    }

    @Test
    public void searchWithInvalidKeywordTest() {
        homePageSteps.setSearchInput(RANDOM_WORD)
                .pressSearchBtn()
                .validateUrlContainsSearch()
                .validateEmptyResultMessage();
    }
}
