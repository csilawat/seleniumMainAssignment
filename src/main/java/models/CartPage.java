package models;

import basePage.BasePage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

@Setter
@Getter
@ToString
public class CartPage extends BasePage {

    @FindBy(xpath = "//button[@onclick='goToCart()']")
    private WebElement cartButton;

    @FindBy(xpath = "//table[@class=\"table table-striped\"]/tbody/tr[1]/td[1]")
    private WebElement firstItemInCart;

    @FindBy(xpath = "//table[@class=\"table table-striped\"]/tbody/tr[1]/td[2]")
    private WebElement firstItemCost;

    @FindBy(xpath = "//table[@class=\"table table-striped\"]/tbody/tr[2]/td[1]")
    private WebElement secondItemInCart;

    @FindBy(xpath = "//table[@class=\"table table-striped\"]/tbody/tr[2]/td[2]")
    private WebElement secondItemCost;

    @FindBy(xpath = "//p[contains(text(),'Total: Rupees')]")
    private WebElement cartItemTotal;

    private List<Object> productsNameWithPriceAndTotalPrice = new ArrayList<>();

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void cartButtonClick() {

        elementWait(cartButton);
        elementToBeClickable(cartButton);
        cartButton.click();
    }

    public List<Object> verifyItemsInCart(Map<Object, Object> allItemsInCart) {

        Integer cartTotal = 0;
        if (!(allItemsInCart.equals(null))) {
            for (Map.Entry entry : allItemsInCart.entrySet()) {

                productsNameWithPriceAndTotalPrice.add((String) entry.getValue());
                Integer s = (Integer) entry.getKey();
                cartTotal += s;
            }
            productsNameWithPriceAndTotalPrice.add(2, "Total: Rupees " + cartTotal);
        }
        return productsNameWithPriceAndTotalPrice;

    }
}
