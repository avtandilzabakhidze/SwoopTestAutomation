package ge.tbcitacademy.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.enums.NumberOfGuest;
import ge.tbcitacademy.data.enums.PriceRange;
import ge.tbcitacademy.data.models.Offer;
import ge.tbcitacademy.pages.CategoryPage;
import ge.tbcitacademy.pages.ProductDetailsPage;
import ge.tbcitacademy.pages.SearchResultsPage;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static ge.tbcitacademy.data.constants.Constants.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SearchSteps {
    private static final Logger logger = LoggerFactory.getLogger(SearchSteps.class);
    SearchResultsPage searchResultsPage = new SearchResultsPage();
    ProductDetailsPage productDetail = new ProductDetailsPage();
    CategoryPage categoryPage = new CategoryPage();

    private Offer parseDealFrom(SelenideElement element) {
        String title = element.find(searchResultsPage.title).getText();
        String description = element.find(searchResultsPage.description).getText();
        String price = element.find(searchResultsPage.price).getText();
        String sold = element.find(searchResultsPage.soldQuantity).getText();
        return new Offer(title, description, price, sold);
    }

    private Offer parseDealFromProductDetails() {
        String title = productDetail.title.getText();
        String description = productDetail.description.getText();
        String price = productDetail.price.getText();
        String sold = productDetail.soldQuantity.getText();
        return new Offer(title, description, price, sold);
    }

    public List<Offer> getSearchResults() {
        return searchResultsPage.searchedProduct.stream()
                .map(this::parseDealFrom)
                .collect(Collectors.toList());
    }

    public Offer findFirst() {
        return parseDealFrom(searchResultsPage.searchedProduct.first());
    }

    public SearchSteps openFirst() {
        searchResultsPage.searchedProduct.first().click();
        return this;
    }

    public SearchSteps backBrowser() {
        Selenide.back();
        return this;
    }

    public Offer grabDetails() {
        return parseDealFromProductDetails();
    }

    public SearchSteps validateGuestCountInDeals(List<Offer> offers, NumberOfGuest guestRange) {
        for (Offer offer : offers) {
            String combinedText = offer.getTitle() + EMPTY + offer.getDescription();
            Pattern pattern = Pattern.compile(REGX_QUAN);
            Matcher matcher = pattern.matcher(combinedText);

            boolean matchFound = IS_FALSE;
            while (matcher.find()) {
                int guestCount = Integer.parseInt(matcher.group(1));
                if (guestCount >= guestRange.getMin() && guestCount <= guestRange.getMax()) {
                    matchFound = IS_TRUE;
                    break;
                }
            }
            assertTrue(matchFound, OFFER_NOT_RANGE);
        }
        return this;
    }

    public SearchSteps validateResultsNotEmpty(List<Offer> offers) {
        logger.debug(LOGGER_DATA, offers.size());
        assertFalse(offers.isEmpty(), SEARCH_RESULT_SHOULD_NOT_EMPTY);
        return this;
    }

    public SearchSteps validateSelectedCategory(String expectedCategory) {
        SelenideElement selectedCategory = categoryPage.selectedCategory;
        Assert.assertEquals(selectedCategory.getText(), expectedCategory, SELECTED_CATEGORY);
        return this;
    }

    public SearchSteps validateResultsContainKeyword(List<Offer> offers, String keyword) {
        for (Offer offer : offers) {
            boolean found = offer.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || offer.getDescription().toLowerCase().contains(keyword.toLowerCase());
            assertTrue(found, KEYWORD_NOT_FOUND);
        }
        return this;
    }

    public SearchSteps scrollToPagination() {
        searchResultsPage.rightArrow.scrollIntoView(IS_TRUE);
        return this;
    }

    public SearchSteps paginateThroughAllPagesAndBack() {
        int currentPage = COUNTER;
        List<Integer> activePages = new ArrayList<>();

        while (isRightArrowClickable()) {
            scrollToPagination();
            searchResultsPage.rightArrow.click();
            activePages.add(currentPage++);
        }

        while (isLeftArrowClickable()) {
            scrollToPagination();
            searchResultsPage.leftArrow.click();
            activePages.add(currentPage--);
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
        SelenideElement activePage = $(searchResultsPage.activePage);
        Assert.assertEquals(activePage.getText(), String.valueOf(expectedPageNumber), ACTIVATE_PAGE_NUMBER);
        return this;
    }

    public SearchSteps validateDealInfoConsistentIgnoringSoldAndPriceFormat(Offer offer1, Offer offer2) {
        String normalizedPrice1 = offer1.getPrice().replaceAll(REGX, EMPTY);
        String normalizedPrice2 = offer2.getPrice().replaceAll(REGX, EMPTY);

        Assert.assertEquals(offer1.getTitle(), offer2.getTitle(), TEXT_MISMATCH);
        Assert.assertEquals(offer1.getDescription(), offer2.getDescription(), DESC_MISMATCH);
        Assert.assertEquals(normalizedPrice1, normalizedPrice2, PRICE_MISMATCH);
        return this;
    }

    public SearchSteps validateDealsAreDifferent(List<Offer> page1, List<Offer> page2) {
        assertFalse(page1.equals(page2), SAME_RESULT);
        return this;
    }

    public SearchSteps setPageNumber(String num) {
        String formattedXPath = String.format(searchResultsPage.pageNumberXPath, num);
        $(By.xpath(formattedXPath)).click();
        return this;
    }

    public SearchSteps filterKeywordsIsDisplay() {
        $$(searchResultsPage.searchedFilters).shouldHave(sizeGreaterThan(1));
        return this;
    }

    public SearchSteps validateResultsWithinPriceRange(List<Offer> offers, PriceRange priceRange) {
        int min = priceRange.getMin();
        int max = priceRange.getMax();

        for (Offer offer : offers) {
            String priceText = offer.getPrice();
            String numberPart = priceText.replaceAll(ZERO_TO_NINE, EMPTY);
            int price = (int) Double.parseDouble(numberPart);
            assertTrue(price >= min && price <= max, PRICE_NOT_RANGE);
        }
        return this;
    }
}
