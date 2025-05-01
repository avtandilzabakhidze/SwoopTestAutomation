package ge.tbcitacademy.steps;

import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.pages.SearchResultsPage;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ge.tbcitacademy.data.constants.Constants.*;

public class PaginationSteps {
    SearchResultsPage searchResultsPage = new SearchResultsPage();

    public PaginationSteps scrollToPagination() {
        searchResultsPage.rightArrow.scrollIntoView(IS_TRUE);
        return this;
    }

    public PaginationSteps paginateThroughAllPagesAndBack() {
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

    public PaginationSteps validateActivePageNumber(int expectedPageNumber) {
        SelenideElement activePage = $(searchResultsPage.activePage);
        Assert.assertEquals(activePage.getText(), String.valueOf(expectedPageNumber), ACTIVATE_PAGE_NUMBER);
        return this;
    }
}
