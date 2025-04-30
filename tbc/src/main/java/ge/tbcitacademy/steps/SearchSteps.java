package ge.tbcitacademy.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.enums.NumberOfGuest;
import ge.tbcitacademy.data.models.Deal;
import ge.tbcitacademy.pages.CategoryPage;
import ge.tbcitacademy.pages.ProductDetails;
import ge.tbcitacademy.pages.SearchResultsPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ge.tbcitacademy.data.constants.Constants.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SearchSteps {
    SearchResultsPage searchResultsPage = new SearchResultsPage();
    ProductDetails productDetail = new ProductDetails();
    CategoryPage categoryPage = new CategoryPage();

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

    public Deal findFirst() {
        SelenideElement product = searchResultsPage.searchedProduct.first();

        String title = product.find(searchResultsPage.title).getText();
        String description = product.find(searchResultsPage.description).getText();
        String price = product.find(searchResultsPage.price).getText();
        String sold = product.find(searchResultsPage.soldQuantity).getText();

        return new Deal(title, description, price, sold);
    }

    public SearchSteps openFirst(){
        searchResultsPage.searchedProduct.first().click();
        return this;
    }

    public Deal grabDetails(){
        String title = productDetail.title.getText();
        String description =productDetail.description.getText();
        String price = productDetail.price.getText();
        String sold = productDetail.soldQuantity.getText();

        return new Deal(title, description, price, sold);
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

    public SearchSteps validateSelectedCategory(String expectedCategory) {
        SelenideElement selectedCategory = categoryPage.selectedCategory;
        Assert.assertEquals(selectedCategory.getText(),expectedCategory, SELECTED_CATEGORY);
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

    public SearchSteps paginateThroughAllPagesAndBack() {
        int currentPage = 1;
        // Store initial active page numbers if necessary
        List<Integer> activePages = new ArrayList<>();

        while (isRightArrowClickable()) {
            scrollToPagination();
            searchResultsPage.rightArrow.click();

            // Wait for new page to be loaded
            activePages.add(currentPage);
//            validateActivePageNumber(currentPage);
            currentPage++;
        }

        while (isLeftArrowClickable()) {
            System.out.println(currentPage);
            scrollToPagination();
            searchResultsPage.leftArrow.click();

            // Wait for the page to be correctly loaded
//            validateActivePageNumber(currentPage);
            activePages.add(currentPage);

            currentPage--;
        }

        // Optionally print or assert on active pages
        activePages.forEach(page -> System.out.println("Active page: " + page));

        return this;
    }

    private boolean isRightArrowClickable() {
        return $x(searchResultsPage.right_Active).exists();
    }

    private boolean isLeftArrowClickable() {
        return $x(searchResultsPage.left_Active).exists();
    }

    public SearchSteps validateActivePageNumber(int expectedPageNumber) {
        SelenideElement activePage = $(By.xpath("//div[contains(@class,'rounded-lg') and contains(@class,'primary_green')]"));
        Assert.assertEquals(activePage.getText(), String.valueOf(expectedPageNumber), "Active page number mismatch");
        return this;
    }

    public SearchSteps validateDealInfoConsistentIgnoringSoldAndPriceFormat(Deal deal1, Deal deal2) {
        String normalizedPrice1 = deal1.getPrice().replaceAll("[^\\d.]", "");
        String normalizedPrice2 = deal2.getPrice().replaceAll("[^\\d.]", "");

        Assert.assertEquals(deal1.getTitle(), deal2.getTitle(), "Deal title mismatch");
        Assert.assertEquals(deal1.getDescription(), deal2.getDescription(), "Deal description mismatch");

        Assert.assertEquals(normalizedPrice1, normalizedPrice2, "Deal price mismatch");

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
