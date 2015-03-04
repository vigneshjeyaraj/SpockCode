package com.lsco.test.steps.navigation

import geb.*

import com.lsco.test.page.*
import com.lsco.test.page.navigation.FirstCategoryPage
import com.lsco.test.page.navigation.FirstRootCategoryPage
import com.lsco.test.page.FirstProductPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(geb.Browser)

When(~'^accessing some product detail page$') { ->
	to LevisHomePage
	at LevisHomePage
	toFirstRootCategoryPage()
	at FirstRootCategoryPage
	toFirstCategoryPage()
	at FirstCategoryPage
	checkDataRetrieved()
	toRandomProductPage()
	//toFirstProductPage()
	at FirstProductPage
}

Then(~'^I should see the "([^"]*)" or "([^"]*)" section and access to one of the products.$') { String arg1, String arg2 ->
	verifyRecommendationsSectionData(arg1, arg2)
	toProductRecommendationItemPage()
	verifyCorrectPDPData()
}

Then(~'^I should see the "([^"]*)" section and access to one of the products.$') { String arg1 ->
	verifyRecommendationsGoesWellWithSectionData(arg1)
	toProductRecommendationGoesWellWithItemPage()
	verifyCorrectPDPData()
}




