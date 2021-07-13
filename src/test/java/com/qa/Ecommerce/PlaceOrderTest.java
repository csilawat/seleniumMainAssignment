package com.qa.Ecommerce;

import base.BaseTest;
import models.*;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PlaceOrderTest extends BaseTest {

    private String buyMoisturizersInstruction = "Add two moisturizers to your cart. First, select the least expensive mositurizer that contains Aloe. For your second moisturizer, select the least expensive moisturizer that contains almond. Click on cart when you are done.";
    private String buySunscreensInstruction = "Add two sunscreens to your cart. First, select the least expensive sunscreen that is SPF-50. For your second sunscreen, select the least expensive sunscreen that is SPF-30. Click on the cart when you are done.";
    private String homePageScreen = "homePage";
    private String moisturizersPageScreen = "moisturizersPage";
    private String sunscreensPageScreen = "sunscreensPage";
    private String buyMoisturizersInstructionScreen = "buyMoisturizersInstruction";
    private String buySunscreensInstructionScreen = "buyMoisturizersInstruction";
    private String aloeMoisturizers = "Aloe";
    private String almondMoisturizers = "Almond";
    private String SPF50Sunscreens = "SPF-50";
    private String SPF30Sunscreens = "SPF-30";
    private String cartScreen = "cart";
    private Logger log = Logger.getLogger(PlaceOrderTest.class);

    @Test
    public void verifyBuyProductInstructionsTest() throws Exception {

        int temperature = 0;
        HomePage homePage = new HomePage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);
        CartPage cartPage = new CartPage(driver);
        Map<Object, Object> itemsInCart = null;

        String temp = homePage.getTemperature().getText().replaceAll("[^0-9]", "");

        assertThat(temp, notNullValue());

        try {
            temperature = Integer.parseInt(temp);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }

        assertThat(temperature, is(greaterThan(0)));

        if (temperature >= 34) {
            itemsInCart = buySunscreens();
        }
        if (temperature <= 19) {
            itemsInCart = buyMoisturizers();
        }

        if ((itemsInCart != null)) {
            List<Object> productNameAndPriceInCart = cartPage.verifyItemsInCart((itemsInCart));

            String firstItemInCart = cartPage.getFirstItemInCart().getText();
            String secondItemInCart = cartPage.getSecondItemInCart().getText();
            String itemTotalPrice = cartPage.getCartItemTotal().getText();

            assertThat(productNameAndPriceInCart, hasItem(firstItemInCart));
            assertThat(productNameAndPriceInCart, hasItem(secondItemInCart));
            assertThat(productNameAndPriceInCart, hasItem(itemTotalPrice));

            Thread.sleep(5000);
            takeScreenSHots(cartScreen);
            payment();

            assertThat(paymentPage.getPaymentSuccess().getText(), containsString("PAYMENT SUCCESS"));
            assertThat(paymentPage.getPaymentSuccessMessage().getText(), containsString("Your payment was successful. You should receive a follow-up call from our sales team"));

            log.info("----" + paymentPage.getPaymentSuccessMessage().getText() + "----------");

        } else {
            log.info("............Your cart is Empty....................");
            throw new NullPointerException("******************  Your cart is Empty, please add item in your cart**************************");
        }
    }

    public Map<Object, Object> buyMoisturizers() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        MoisturizersPage moisturizersPage = new MoisturizersPage(driver);
        CartPage cartPage = new CartPage(driver);
        takeScreenSHots(homePageScreen);
        homePage.getMoisturizersButton().click();
        Thread.sleep(2000);
        takeScreenSHots(moisturizersPageScreen);

        String instructionTOBuy = moisturizersPage.getBuyMoisturizersInstruction();
        takeScreenSHots(buyMoisturizersInstructionScreen);
        assertThat(instructionTOBuy, containsString(buyMoisturizersInstruction));
        moisturizersPage.addProduct(aloeMoisturizers);
        takeScreenSHots(aloeMoisturizers);
        Thread.sleep(3000);
        moisturizersPage.addProduct(almondMoisturizers);
        takeScreenSHots(almondMoisturizers);
        Thread.sleep(2000);

        cartPage.cartButtonClick();

        Thread.sleep(2000);
        Map<Object, Object> itemsInCart = moisturizersPage.getAddedProductInCart();

        return itemsInCart;

    }

    public Map<Object, Object> buySunscreens() throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        SunscreensPage sunscreensPage = new SunscreensPage(driver);
        CartPage cartPage = new CartPage(driver);
        takeScreenSHots(homePageScreen);
        homePage.getSunscreensButton().click();
        Thread.sleep(2000);
        takeScreenSHots(sunscreensPageScreen);

        String instructionTOBuy = sunscreensPage.getBuySunscreensInstruction();
        takeScreenSHots(buySunscreensInstructionScreen);
        assertThat(instructionTOBuy, containsString(buySunscreensInstruction));
        sunscreensPage.addProduct(SPF50Sunscreens);
        takeScreenSHots(SPF50Sunscreens);
        Thread.sleep(3000);
        sunscreensPage.addProduct(SPF30Sunscreens);
        takeScreenSHots(SPF30Sunscreens);
        Thread.sleep(2000);
        cartPage.cartButtonClick();

        Thread.sleep(2000);
        Map<Object, Object> itemsInCart = sunscreensPage.getAddedProductInCart();

        return itemsInCart;

    }

    public void payment() throws Exception {

        Thread.sleep(3000);
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.finalPayment();
        Thread.sleep(3000);

    }

}
