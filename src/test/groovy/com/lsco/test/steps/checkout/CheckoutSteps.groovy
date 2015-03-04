package com.lsco.test.steps.checkout

import geb.*

import com.lsco.test.page.CartPage
import com.lsco.test.page.FirstProductPage
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.OrderConfirmationPage
import com.lsco.test.page.ProductDetailPage
import com.lsco.test.page.checkout.CheckoutPage
import com.lsco.test.page.checkout.PayPalPage
import com.lsco.test.page.globalcollect.GlobalCollectSearchPage
import com.lsco.test.page.hac.FlexibleSearchPage
import com.lsco.test.page.navigation.FirstCategoryPage
import com.lsco.test.page.navigation.FirstRootCategoryPage
import com.lsco.test.page.register.ExternalEmailPage


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(geb.Browser)

When(~'^adding "([^"]*)" product to the shopping bag$') { String quantity ->
	to LevisHomePage
	at LevisHomePage
	toFirstRootCategoryPage()
	at FirstRootCategoryPage
	toFirstCategoryPage()
	at FirstCategoryPage
	checkDataRetrieved()
	toRandomDifferentProductPage()
	//toFirstProductPage()
	selectMeasures()
	addProductToBag(quantity)
}

When(~'^accesing a product detail page$') { ->  to ProductDetailPage }

When(~'^adding the product to the shopping bag$') {	->
	at FirstProductPage
	selectMeasures()
	addProductToBag()
}

When(~'^accessing the bag page$') {
	->
	to CartPage
	at CartPage
}

When(~'^apply the promo coupon "([^"]*)" on the shopping bag$') { String arg1 ->
	at CartPage
	applyCoupon(arg1)
}

When(~'^accessing the checkout page$') {
	->
	at CartPage
	toCheckoutPage()
}

Then(~'^enter the shipping information for random user with: address "([^"]*)", city "([^"]*)" and postcode "([^"]*)"$') { String address, String city, String postcode ->
	fillShippingDataForRandomUser(address,city,postcode)}

Then(~'^enter the contact information for random user with: phone "([^"]*)"$') { String phone ->
	fillContactDataRandomUser(phone)
}

When(~'^enter the shipping information: name "([^"]*)", surname "([^"]*)", address "([^"]*)", city "([^"]*)" and postcode "([^"]*)"$') { String arg1, String arg2, String arg3, String arg4, String arg5 ->
	fillShippingData(arg1, arg2, arg3, arg4, arg5)
}

When(~'^enter the paypal username "([^"]*)" and the password "([^"]*)"$') { String arg1, String arg2 ->

	choosePayPal()
	at PayPalPage
	payPalLogin(arg1, arg2)
	confirmPayPalPayment()
}

When(~'^enter the contact information: email "([^"]*)" and phone "([^"]*)"$') { String arg1, String arg2 ->
	fillContactData(arg1, arg2)
}

When(~'^enter the contact information: email and phone "([^"]*)"$') { String arg1 ->
	fillContactData(arg1)
}

When(~'^check the age restriction$') { -> checkAgeCheckbox() }

When(~'^check the gift wrap option$') { -> checkGiftWrapCheckbox() }

When(~'^submit the information$') { -> submitData() }

When(~'^enter the credit card number "([^"]*)", the security code "([^"]*)", the expiration month "([^"]*)" and expiration year "([^"]*)"$') { String arg1, String arg2, String arg3, String arg4 ->
	fillCreditCardData(arg1, arg2, arg3, arg4)
}

When(~'^choose Visa credit card$') { -> chooseVisa() }

When(~'^choose MasterCard credit card$') { -> chooseMasterCard() }

When(~'^choose Amex credit card$') { -> chooseAmex() }

When(~'^choose Delta credit card$') { -> chooseDelta() }

When(~'^choose Maestro credit card$') { -> chooseMaestro() }

When(~'^choose Dankort credit card$') { -> chooseDankort() }

When(~'^choose Carte Bleue credit card$') { -> chooseCarteBleue() }

When(~'^choose IDeal payment$') { -> chooseIDeal() }

When(~'^choose "([^"]*)" bank and confirm$') { String  arg1 ->
	chooseBankAndConfirm(arg1)
}

Then(~'^I should get the order placed and see the order confirmation page for email "([^"]*)"$'){ String arg1 ->
	at OrderConfirmationPage
	verifyOrder(arg1)
}



Then(~'^I should get the order placed and see the order confirmation page for email$'){
	->
	at OrderConfirmationPage
	verifyOrder()
}

Then(~'^with the corresponding discounts$'){ ->
	at OrderConfirmationPage
	checkSavings()
}

Then(~'^with the discounts "([^"]*)"$'){ String arg1 ->
	at OrderConfirmationPage
	compareSavings(arg1)
}

Then(~'^I should see the order in Global Collect$'){
	->
	to GlobalCollectSearchPage
	at GlobalCollectSearchPage
	searchTab()
	searchOrder()
	verifyOrderPlaced()
}

Then(~'^also the "([^"]*)" payment$') { String arg1 ->
	verifyPaymentPlaced(arg1)
}

Then(~'^also the "([^"]*)" payment with the same total amount$') { String arg1 ->
	verifyPaymentAmountPlaced(arg1)
}

Then(~'^receive the order confirmation email in "([^"]*)" with username "([^"]*)"$') { String arg1, String username ->
	go arg1
	at ExternalEmailPage
	checkOrderConfirmationEmail(username)
}

Then(~'^receive the order confirmation email in "([^"]*)"$') { String arg1 ->
	go arg1
	at ExternalEmailPage
	checkOrderConfirmationEmail()
}

Then(~'^receive the order confirmation email in "([^"]*)" with the discounts "([^"]*)"$') { String arg1, String arg2 ->
	go arg1
	at ExternalEmailPage
	checkSavingsExternalEmailInbox(arg2)
}



Then(~'^the order is created in Hybris with the same total amount$') {
	->
	to FlexibleSearchPage
	at FlexibleSearchPage
	checkOrderInformation()
}

Then(~'^receive the order confirmation email in my inbox at "([^"]*)"$') { String arg1 ->
	go arg1
	at ExternalEmailPage
	checkOrderConfirmationEmail()
}

When(~'^login on checkout with username "([^"]*)" and password "([^"]*)"$') { String username, String password ->
	at CheckoutPage
	toLoginForm()
	checkoutLogin(username, password)
}

Then(~'^accessing to customer order history and see the order placed$') {
	->
	toOrderTrack()
	checkOrderInHistory()
}

Then(~'^accessing the homepage from checkout$') { ->
	toLevisHomePage()
}

