package ge.tbcitacademy.steps;

import com.codeborne.selenide.ElementsCollection;
import ge.tbcitacademy.pages.HomePage;
import org.testng.Assert;

import static ge.tbcitacademy.data.constants.Constants.FOOTER_LINK_NOT_VERTICAL;
import static ge.tbcitacademy.data.constants.Constants.FOOTER_NOT_HORIZONTAL;
import static org.testng.Assert.assertTrue;

public class FooterSteps {
    HomePage homePage = new HomePage();

    public FooterSteps validateFooterLinksHorizontal() {
        int referenceY = homePage.searchedProduct.get(0).getLocation().getY();
        boolean allAligned = homePage.searchedProduct.stream()
                .allMatch(el -> el.getLocation().getY() == referenceY);

        assertTrue(allAligned, FOOTER_NOT_HORIZONTAL);
        return this;
    }

    public FooterSteps validateFooterLinksVertical() {
        ElementsCollection elements = homePage.searchedProduct;

        int previousY = elements.get(0).getLocation().getY();
        for (int i = 1; i < elements.size(); i++) {
            int currentY = elements.get(i).getLocation().getY();
            if (currentY <= previousY) {
                Assert.fail(FOOTER_LINK_NOT_VERTICAL);
            }
            previousY = currentY;
        }
        return this;
    }
}
