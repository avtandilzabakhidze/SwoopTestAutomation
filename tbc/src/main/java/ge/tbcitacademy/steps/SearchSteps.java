package ge.tbcitacademy.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.enums.Address;
import ge.tbcitacademy.data.enums.NumberOfGuest;
import ge.tbcitacademy.data.enums.PriceRange;
import ge.tbcitacademy.data.models.Deal;
import ge.tbcitacademy.pages.CategoryPage;
import ge.tbcitacademy.pages.ProductDetailsPage;
import ge.tbcitacademy.pages.SearchResultsPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static ge.tbcitacademy.data.constants.Constants.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SearchSteps {
    SearchResultsPage searchResultsPage = new SearchResultsPage();
    ProductDetailsPage productDetail = new ProductDetailsPage();
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

    public SearchSteps backBrowser(){
        Selenide.back();
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
            String combinedText = deal.getTitle() + EMPTY + deal.getDescription();

            Pattern pattern = Pattern.compile(REGX_QUAN);
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
        List<Integer> activePages = new ArrayList<>();

        while (isRightArrowClickable()) {
            scrollToPagination();
            searchResultsPage.rightArrow.click();

            activePages.add(currentPage);
            currentPage++;
        }

        while (isLeftArrowClickable()) {
            scrollToPagination();
            searchResultsPage.leftArrow.click();

            activePages.add(currentPage);
            currentPage--;
        }

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
        Assert.assertEquals(activePage.getText(), String.valueOf(expectedPageNumber), ACTIVATE_PAGE_NUMBER);
        return this;
    }

    public SearchSteps validateDealInfoConsistentIgnoringSoldAndPriceFormat(Deal deal1, Deal deal2) {
        String normalizedPrice1 = deal1.getPrice().replaceAll(REGX, EMPTY);
        String normalizedPrice2 = deal2.getPrice().replaceAll(REGX, EMPTY);

        Assert.assertEquals(deal1.getTitle(), deal2.getTitle(), TEXT_MISMATCH);
        Assert.assertEquals(deal1.getDescription(), deal2.getDescription(), DESC_MISMATCH);
        Assert.assertEquals(normalizedPrice1, normalizedPrice2, PRICE_MISMATCH);
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

    public SearchSteps validateSelectedAddress(Address address) {
        return this;
    }

    public SearchSteps validateSelectedPriceRange(PriceRange priceRange) {
        return this;
    }

    public SearchSteps validateResultsWithinPriceRange(List<Deal> deals, PriceRange priceRange) {
        for (Deal deal : deals) {
            String price = deal.getPrice();
        }
        return this;
    }
}
