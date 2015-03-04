package com.lsco.test.page

import com.lsco.test.page.model.UserDataModelMap
import geb.Page

import org.openqa.selenium.JavascriptExecutor

import com.lsco.test.PropertyProvider
import com.lsco.test.page.checkout.OrderDataModel
import com.lsco.test.page.model.ProductDataModel
import com.lsco.test.page.model.ProductDataModelMap
import com.lsco.test.page.register.RegistrationDataModel

class CartPage extends Page {

	static url = "cart"
	
	static content = {
		checkoutButton { $("#main-container > aside > section > a.btn-dbbdr.top-btn")}
		myFrame(page: FramePage) { $('#PaymentMethodForm > fieldset > iframe') }
		bagProductsConteiner {$("ul.list-type-04")}
		bagProducts(required: false) { moduleList ProductModule, bagProductsConteiner.find("li.product-tile")}
	}

	static at = {
		def okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("in.your.bag")
		assert $("#main-container > article > header > h1").text().trim().toUpperCase() == okValue
	}

	def toCheckoutPage() {
		checkoutButton.click()
	}

	def fillShippingData(firstName, surname, address, city, postcode){
		String validAddress = PropertyProvider.getInstance().getLocalizedPropertyValue(address)
		String validCity = PropertyProvider.getInstance().getLocalizedPropertyValue(city)
		String validPostcode = PropertyProvider.getInstance().getLocalizedPropertyValue(postcode)

		$("#shipping-firstname").value(firstName)
		$("#shipping-lastname").value(surname)
		$("#shipping-address-1").value(validAddress)
		$("#shipping-city").value(validCity)
		$("#shipping-postal").value(validPostcode)

		assert $("#shipping-firstname").value() == firstName
		assert $("#shipping-lastname").value() == surname
		assert $("#shipping-address-1").value() == validAddress
		assert $("#shipping-city").value() == validCity
		assert $("#shipping-postal").value() == validPostcode
	}

	def fillShippingDataForRandomUser(address, city, postcode){
		String firstName=UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getName()
		String surname=UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getLastName()
		fillShippingData(firstName, surname, address, city, postcode)
	}


	def fillContactData(phone){
		String email = RegistrationDataModel.getInstance().email
		fillContactData(email, phone)
	}

	def fillContactData(email, phone){
		String validPhone = PropertyProvider.getInstance().getLocalizedPropertyValue(phone)

		$("#contact-email").value(email)
		$("#contact-phone").value(validPhone)

		assert $("#contact-email").value() == email
		assert $("#contact-phone").value() == validPhone
	}

	def fillContactDataRandomUser(phone) {
		def email=UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getEmail().toLowerCase()
		fillContactData(email,phone)
	}

	def checkAgeCheckbox(){
		js( ' jQuery( "#age-chkbox" ).css("opacity", "100"); ' )
		js( ' jQuery( "#age-chkbox" ).css("left", "0px"); ' )
		$("#age-chkbox").value(true)

		assert $("#age-chkbox").value() == "true"
	}

	def checkGiftWrapCheckbox(){
		js( ' jQuery( "#giftwrap-chkbox" ).css("opacity", "100"); ' )
		js( ' jQuery( "#giftwrap-chkbox" ).css("left", "0px"); ' )
		$("#giftwrap-chkbox").value(true)

		assert $("#giftwrap-chkbox").value() == "true"

		//Commented because is no longer used
		//		waitFor(30, 10){
		//			$("#giftwrap-from").isDisplayed()
		//		}
		//		$("#giftwrap-from").value("Martin")
		//		$("#giftwrap-to").value("Peter")
		//		$("#giftwrap-note1").value("Happy birthday!!!")
		//
		//		assert $("#giftwrap-from").value() == "Martin"
		//		assert $("#giftwrap-to").value() == "Peter"
		//		assert $("#giftwrap-note1").value() == "Happy birthday!!!"
	}

	def js( String script ){
		(driver as JavascriptExecutor).executeScript( script )
	}

	def submitData() {
		$("#link-next").click()
	}

	def fillCreditCardData(creditCardNumber, securityCode, expirationMonth, expirationYear){
		waitFor(240, 10){ myFrame.@style == "display: block;" }

		Thread.sleep(5000)
		
		withFrame(myFrame) {
			$("#F1009").value(creditCardNumber)
			$("#F1136").value(securityCode)

			assert $("#F1009").value() == creditCardNumber
			$("#F1010_MM").value(expirationMonth)
			$("#F1010_YY").value(expirationYear)
			$("#btnSubmit").click()
		}
	}

	def chooseMaestro(){
		$("input", name: "method").value("117-K")
	}
	
	def chooseDankort(){
		$("input", name: "method").value("123-K")
	}


	def chooseCarteBleue(){
		$("input", name: "method").value("130-K")
	}

	def chooseIDeal(){
		$("input", name: "method").value("809-R")
	}

	def chooseMasterCard(){
		$("input", name: "method").value("3-K")
	}

	def chooseAmex(){
		$("input", name: "method").value("2-K")
	}
	
	def chooseDelta(){
		$("input", name: "method").value("111-K")
	}
	
	def chooseVisa(){
		$("input", name: "method").value("1-K")
	}

	def choosePayPal(){
		$("input", name: "method").value("840-R")
		waitFor(120, 10){
			$("#loginPageTitle").isDisplayed()
		}
	}

	def checkProduct(quantity){
		ProductDataModel productDataModel = ProductDataModelMap.getInstance().getProductMap().get("SELECTED_PRODUCT")
		waitFor(120,4){$(".prod-name-descript > p:nth-child(1)").isDisplayed()}
		assert $(".prod-name-descript > p:nth-child(1)").text() == productDataModel.productName
		assert $(".sku > span:nth-child(1)").text() == productDataModel.productId
		assert $("#quantityCombo").value() == quantity
		ProductDataModelMap.getInstance().getProductMap().clear()
	}

	def updateQuantity(){
		String currencySymbol = PropertyProvider.getInstance().getLocalizedPropertyValue("currency.symbol")
		String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")

		def orderPricing = $("li.data:nth-child(2)")
		def orderPrice = orderPricing.text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
		
		$(".select2-choice").click();
		def quantityCombo = $("#quantityCombo")
		
		quantityCombo.value("2")
		quantityCombo = $("#quantityCombo")
		assert quantityCombo.value() == "2"
		
		orderPricing = $("li.data:nth-child(2)")
		def newOrderPrice = orderPricing.text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
		
		println "New calculated price: " + (orderPrice * quantityCombo.value().toDouble())
		println "New order price: " + newOrderPrice

		assert orderPrice * quantityCombo.value().toDouble() == newOrderPrice
	}

	def payPalLogin(username, password){
		String usernameValue = PropertyProvider.getInstance().getLocalizedPropertyValue(username)
		String passwordValue = PropertyProvider.getInstance().getLocalizedPropertyValue(password)

		$("#login_email").value(usernameValue)
		$("#login_password").value(passwordValue)
		$("#submitLogin").click()
	}

	def confirmPayPalPayment(){
		$("#continue_abovefold").click()
	}

	def verifyOrder(){
		String email = RegistrationDataModel.getInstance().email
		verifyOrder(email)
	}

	def verifyOrder(String email){
		assert $("#left-col > p:nth-child(1) > span").text()
		assert $("#left-col > p.data").text().toUpperCase() == email.toUpperCase()

		OrderDataModel.getInstance().orderNumber = $('#left-col > p > span').text()

		String currencySymbol = PropertyProvider.getInstance().getLocalizedPropertyValue("currency.symbol")
		String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")

		Double price = $('li.data span').text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
		OrderDataModel.getInstance().totalPrice = price
		
		println "Order number: " + OrderDataModel.getInstance().orderNumber + " - Price: " + OrderDataModel.getInstance().totalPrice
	}

	def chooseBankAndConfirm(String bank){

		waitFor(30, 5){
			$("#F8045").isDisplayed()
		}

		$("#F8045").value("INGBNL2A")
		$("#btnSubmit").click()

		waitFor(30, 5){
			$("input", name: "button.edit").isDisplayed()
		}
		$("input", name: "button.edit").click()
	}
	
	def checkSavings(){
		String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")

		double subtotal = $("ul.cost-summary:nth-child(2) > li:nth-child(2)").text().replaceAll(decimalSeparator, ".").find(/\d+(\.\d)/).toDouble().value
		double savings = $(".data-inner").text().replaceAll(decimalSeparator, ".").find(/\d+(\.\d{2})/).toDouble().value

		println "Subtotal: " + subtotal
		println "Savings: " + savings
	
		subtotal = (subtotal* 10)
		savings = (savings * 100)
			
		println "Calculated Subtotal: " + subtotal
		println "Calculated Savings: " + savings
		
		assert savings == subtotal
	}
	
	def compareSavings(expectedSavingsAmount){
		String currencySymbol = PropertyProvider.getInstance().getLocalizedPropertyValue("currency.symbol")
		String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")
				
		String expectedSavings1 = "(" + currencySymbol + expectedSavingsAmount + ")"
		String expectedSavings2 = "(" + expectedSavingsAmount + currencySymbol + ")"
		String newexpectedSavingsAmount = ((String)expectedSavingsAmount).replace(".", decimalSeparator)
		String expectedSavings3 = "(" + currencySymbol + newexpectedSavingsAmount + ")"
		String expectedSavings4 = "(" + newexpectedSavingsAmount + currencySymbol + ")"

		println "Posible expected savings: " + expectedSavings1 + " " + expectedSavings2 + " " + expectedSavings3 + " " + expectedSavings4   
		
		println $("li", text: expectedSavings1).text()
		println $("li", text: expectedSavings2).text()
		println $("li", text: expectedSavings3).text()
		println $("li", text: expectedSavings4).text()

		assert $("li", text: expectedSavings1).isDisplayed() || $("li", text: expectedSavings2).isDisplayed() || $("li", text: expectedSavings3).isDisplayed() || $("li", text: expectedSavings4).isDisplayed()
	}

	def applyCoupon(couponCode){
		$("#promo-input").value(couponCode)
		$("#promo-btn").click()
	}
	
	def getMyBagQuantityElement(){
		$("#main-container > article:nth-child(3) > header:nth-child(1) > p:nth-child(2)")
	}

	String getMyBagQuantityText(){
		getMyBagQuantityElement().text()
	}

	int getMyBagQuantityItems(){
		getMyBagQuantityText().find(/(\d+)/).toInteger()
	}

	def removeItems(String quantity){
		assert getMyBagQuantityText().toUpperCase()== 2+" "+PropertyProvider.getInstance().getLocalizedPropertyValue("bag.items")
		$(".actions > li:nth-child(1) > a:nth-child(1)").click()
		assert getMyBagQuantityText().toUpperCase()== 1+" "+PropertyProvider.getInstance().getLocalizedPropertyValue("bag.item")
	}

	def toLoginForm(){
		$("#signed-in-subheader > a").click()
	}

	def checkoutLogin(username, password){
		$("#j_username").value(username)
		$("#j_password").value(password)
		$("#link-signin").click()
	}
	
	def toOrderTrack(){
		$("#left-col > p:nth-child(2) > a:nth-child(1)").click()
	}
	
	def checkOrderInHistory(){
		assert $("p.title:nth-child(1) > span:nth-child(1)").text() == OrderDataModel.getInstance().orderNumber
		$("#OrdersLink").click()
		assert $("table.order-history > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > p:nth-child(1) > a:nth-child(1)").text() == OrderDataModel.getInstance().orderNumber
	}
	
	def toLevisHomePage(){
		$(".logo").click()
	}
	
}
