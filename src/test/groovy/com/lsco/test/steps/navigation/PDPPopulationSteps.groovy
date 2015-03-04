package com.lsco.test.steps.navigation

import geb.*

import com.lsco.test.page.*
import com.lsco.test.page.hac.FlexibleSearchPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(geb.Browser)

Then(~'^I should see the product detail information$') { ->
	at FirstProductPage
	verifyProductDetailInformation()
}

Then(~'^I should see the highest priority order level promotion displayed in the buy stack$') { ->
	at FirstProductPage
	verifyHighestPriorityOrderLevelPromotion()
}

Then(~'^I should see the product detail information in Hybris$') { ->
	to FlexibleSearchPage
	at FlexibleSearchPage
	checkProductDetailInformation()
}
