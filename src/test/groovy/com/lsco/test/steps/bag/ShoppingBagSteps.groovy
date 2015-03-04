package com.lsco.test.steps.bag

import com.lsco.test.page.FirstProductPage
import geb.*

import com.lsco.test.page.CartPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(geb.Browser)

When(~'^accesing the shopping bag page$') {
	->
	to CartPage
	at CartPage
}

When(~'^adding "([^"]*)" product with the same PC9$') { String quantity ->
	at FirstProductPage
	selectDifferentMeasures()
	addProductToBag(quantity)
}

When(~'^adding "([^"]*)" product with the same SKU$') { String quantity ->
	at FirstProductPage
	addProductToBag(quantity)
}

When(~'^items in my bag must be "([^"]*)"$') { String quantity ->
	at FirstProductPage
	checkProductQuantityInBag(quantity)
}

Then(~'^I should be able to see a error message "([^"]*)"$') { String property ->
	at FirstProductPage
	CheckErrorsInBagItems(property)
}
Then(~'^I should be able to change the product quantity$') { -> updateQuantity() }

Then(~'^I should see "([^"]*)" product in the shopping bag.$') { String quantity ->
	to CartPage
	at CartPage
	checkProduct(quantity)
}

Then(~'^I should be able to remove a product and quantity must be updated$') { -> removeItems("1")  }

Then(~'^I should be able to see a message that the user can add only 6 PC9 products$') { ->



}