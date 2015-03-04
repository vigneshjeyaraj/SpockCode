package com.lsco.test.Specs

import com.lsco.test.page.LevisHomePage
import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec

abstract class BaseSpec extends GebReportingSpec{

    def setupSpec() {
        CachingDriverFactory.clearCacheCache()
        driver.manage().window().maximize()
        println("The System property value is -----"+System.getProperty("test.grouping"))
    }

    def setup() {
        to LevisHomePage
        waitFor {at LevisHomePage}
        dismissPopup()
        checkIfSignoutIsRequired()
        toMyAccountPage()
    }
}
