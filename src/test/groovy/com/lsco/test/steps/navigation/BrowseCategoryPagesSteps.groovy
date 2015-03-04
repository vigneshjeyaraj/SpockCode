package com.lsco.test.steps.navigation

import geb.*

import com.lsco.test.page.*
import com.lsco.test.page.navigation.FirstCategoryPage;
import com.lsco.test.page.navigation.FirstRootCategoryPage;

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(geb.Browser)

When(~'^accessing the first catalog and the first category page$') { ->
	to LevisHomePage
	at LevisHomePage
	toFirstRootCategoryPage()
	at FirstRootCategoryPage
	toFirstCategoryPage()
	at FirstCategoryPage
}

Then(~'^I should see the products information and images.$') { ->
	at FirstCategoryPage
	checkAllDataRetrieved()
}

