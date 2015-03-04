package com.lsco.test.steps.navigation

import geb.*

import com.lsco.test.page.*
import com.lsco.test.page.navigation.WomenAllJeansCategoryPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(geb.Browser)

When(~'^appling filters$') { ->
	at WomenAllJeansCategoryPage
	checkDataBeforeFilter()
	selectFilters()
	checkDataAfterFilter()
}

When(~'^selecting a product from grid$') { ->
	at WomenAllJeansCategoryPage
	chooseFirstProduct()
}

Then(~'^I should be redirected to the corresponing PDP.$') { ->
	at FirstProductPage
	verifyProductPDP()
}
