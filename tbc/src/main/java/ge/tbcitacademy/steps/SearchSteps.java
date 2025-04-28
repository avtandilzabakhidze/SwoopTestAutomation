package ge.tbcitacademy.steps;

import ge.tbcitacademy.data.models.Deal;
import ge.tbcitacademy.pages.SearchResultsPage;

import java.util.List;
import java.util.stream.Collectors;

import static ge.tbcitacademy.data.constants.Constants.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SearchSteps {
    SearchResultsPage searchResultsPage = new SearchResultsPage();

    public List<Deal> getSearchResults() {
        return searchResultsPage.searchedProduct.stream()
                .map(product -> {
                    String title = product.find(searchResultsPage.title).getText();
                    String description = product.find(searchResultsPage.description).getText();
                    String price = product.find(searchResultsPage.price).getText();
                    String sold = product.find(searchResultsPage.soldQuantity).getText();
                    return new Deal(title, description, price, sold);
                })
                .collect(Collectors.toList());
    }

    public SearchSteps validateResultsNotEmpty(List<Deal> deals) {
        assertFalse(deals.isEmpty(), SEARCH_RESULT_SHOULD_NOT_EMPTY);
        return this;
    }

    public SearchSteps validateResultsContainKeyword(List<Deal> deals, String keyword) {
        for (Deal deal : deals) {
            boolean found = deal.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || deal.getDescription().toLowerCase().contains(keyword.toLowerCase());
            assertTrue(found, KEYWORD_NOT_FOUND);
        }
        return this;
    }
}
