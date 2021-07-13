package com.qa.Ecommerce;

import base.BaseTest;
import models.PaymentPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class MoisturizersPageTest extends BaseTest {

    private Logger log = Logger.getLogger(MoisturizersPageTest.class);

    @Test
    public void runParallelTest() throws InterruptedException {

        log.info("---------MoisturizersPageTest started -----------------");

        Thread.sleep(10000);
        System.out.println(" MoisturizersPageTest" + "******************");
    }
}
