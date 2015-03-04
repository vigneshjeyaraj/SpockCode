package com.lsco.test.steps.navigation

import geb.*

import com.lsco.test.page.*
import com.lsco.test.page.navigation.WomenAllJeansCategoryPage
import com.lsco.test.page.navigation.WomenCategoryPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(geb.Browser)



When(~'^accessing the women jeans category page$') {
	->
	to LevisHomePage
	dismissPopup()
	at LevisHomePage
	toWomenCategoryPage()
	at WomenCategoryPage
	//toWomenJeansCategoryPage()
	//at WomenJeansCategoryPage
	toAllCollectionsPage()
	at WomenAllJeansCategoryPage
}

Then(~'^I should see the products information and images of women jeans category.$') {
	->
	at WomenAllJeansCategoryPage
	checkAllDataRetrieved()
}
