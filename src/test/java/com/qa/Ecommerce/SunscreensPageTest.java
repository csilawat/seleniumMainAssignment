package com.qa.Ecommerce;

import base.BaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class SunscreensPageTest extends BaseTest {

    private Logger log = Logger.getLogger(SunscreensPageTest.class);

    @Test
    public void runParallelTest() throws InterruptedException {

        log.info("---------SunscreensPageTest started -----------------");

        Thread.sleep(10000);
        System.out.println("SunscreensPageTest" + " **************");
    }

}
