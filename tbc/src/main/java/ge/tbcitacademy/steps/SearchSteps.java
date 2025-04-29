package ge.tbcitacademy.steps;

import ge.tbcitacademy.data.enums.NumberOfGuest;
import ge.tbcitacademy.data.models.Deal;
import ge.tbcitacademy.pages.SearchResultsPage;
import org.openqa.selenium.By;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
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

    public SearchSteps validateGuestCountInDeals(List<Deal> deals, NumberOfGuest guestRange) {
        for (Deal deal : deals) {
            String combinedText = deal.getTitle() + " " + deal.getDescription();

            //  4 სტუმარზე, 5+2 ადამიანზე, 3-4 ადამიანზე
            Pattern pattern = Pattern.compile("(\\d+)([-+]?\\d*)\\s*(ადამიანზე|სტუმარზე)");
            Matcher matcher = pattern.matcher(combinedText);

            boolean matchFound = false;

            while (matcher.find()) {
                int guestCount = Integer.parseInt(matcher.group(1));
                if (guestCount >= guestRange.getMin() && guestCount <= guestRange.getMax()) {
                    matchFound = true;
                    break;
                }
            }
            assertTrue(matchFound, OFFER_NOT_RANGE);
        }
        return this;
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

    public SearchSteps scrollToPagination() {
        searchResultsPage.rightArrow.scrollIntoView(true);
        return this;
    }

    public SearchSteps clickNext() {
        searchResultsPage.rightArrow.click();
        return this;
    }

    public SearchSteps clickPrevious() {
        searchResultsPage.leftArrow.click();
        return this;
    }

    public SearchSteps validateDealsAreDifferent(List<Deal> page1, List<Deal> page2) {
        assertFalse(page1.equals(page2), SAME_RESULT);
        return this;
    }

    public SearchSteps setPageNumber(String num) {
        $(By.xpath("//div[contains(@class,'rounded-lg') and contains(text(),'" + num + "')]")).click();
        return this;
    }
}
