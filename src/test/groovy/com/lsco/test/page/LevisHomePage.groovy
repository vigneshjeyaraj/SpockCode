package com.lsco.test.page

import geb.Page

import org.apache.commons.lang.RandomStringUtils

import com.lsco.test.PropertyProvider
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.model.ProductDataModelMap
import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.register.RegistrationDataModel
import org.apache.commons.lang.math.RandomUtils


class LevisHomePage extends Page {

	static url = ""
	
	static at = {
		def okBrand = PropertyProvider.getInstance().getLocalizedPropertyValue("brand")
		def okCountry = PropertyProvider.getInstance().getLocalizedPropertyValue("country")
		assert title.toUpperCase().contains(okBrand)
		assert title.toUpperCase().contains(okCountry)
	}

	static content = { pageData { js.pageData } }


	def toMyAccountPage(){
		$("#global-myaccount-cta").click(LevisLoginPage)
	}

	def toRegistrationPage(){
		$("#global-myaccount-cta").click(AccountRegistrationPage)
	}
	
	def toFirstRootCategoryPage() {
		$("body > div.header-fluid > header > header > ul > li:nth-child(1) > h2 > a").click()
	}

	def toWomenCategoryPage() {
//		waitFor(30){ $(".email-lightbox").@style == "display: none;" }

		interact {
			moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(2) > h2"))
		}
		$("#wJeansLink").click()
	}

	def dismissPopup(){
		if ($("div.email-signup > div:nth-child(1) > span:nth-child(1)").isDisplayed()){
			$("div.email-signup > div:nth-child(1) > span:nth-child(1)").click()
		}
	}

	def searchTerm(term){
		$("#input-search").value(term)
		$("#search-bar > a").click()
	}

	def searchRandomTerm(int length){
		searchTerm("jn!t@#"+generateRamdomString(length))
	}

	private String generateRamdomString(int length) {
		RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(length) + 1)
	}

	def verifyPopulation(searchTerm){
		def searchTag = "search:"
		def searchTermTag = "term:"
		def searchResultTag = "result_count:"

		StringBuilder pageDataText = new StringBuilder(pageData.toString());

		def searchTagInitialIndex = pageDataText.indexOf(searchTag)

		//verify that searchTag is present
		assert searchTagInitialIndex != -1

		pageDataText.replace(0, pageDataText.length(),  pageDataText.substring(searchTagInitialIndex))
		def searchFinalIndex =  pageDataText.indexOf("]")
		pageDataText.replace(0, pageDataText.length(), pageDataText.substring(0, searchFinalIndex +1))
		def searchTermTagInitialIndex = pageDataText.indexOf(searchTermTag)
		def searchResultTagInitialIndex = pageDataText.indexOf(searchResultTag)

		//verify that termTag is present
		assert searchTermTagInitialIndex != -1
		//verify that resultTag is present
		assert searchResultTagInitialIndex != -1

		String searchTermTagValue = pageDataText.substring(searchTermTagInitialIndex + searchTermTag.size(), pageDataText.indexOf(","))
		String searchResultTagValue = pageDataText.substring(searchResultTagInitialIndex + searchResultTag.size(), pageDataText.indexOf("]"))

		assert searchTerm == searchTermTagValue
		assert searchResultTagValue.toInteger() > 0
		assert $("span.productCount").text() == searchResultTagValue
	}

	def toAllCollectionsPage(){
		$("a.cta.see-all").click()
		//waitFor(30){ $("#main-container > article:nth-child(1) > header > article > div > h1").text() == "JEANS"}
	}
	
	def createNewEmail(arg1){
		def newEmail = RandomStringUtils.randomAlphanumeric(20)
		RegistrationDataModel.getInstance().email = newEmail
		$("#inboxfield").value(newEmail)
		$("btn.btn.btn-success").click()
	}
	
	def checkIfSignoutIsRequired(){
		if ($("#global-myaccount-signout").isDisplayed()){
			$("#global-myaccount-signout").click()
		}
	}

	def emptyTheBag(){
		def myBagQuantity = $("#cart-container > a > div > h3 > span")
		int bagQuantity = myBagQuantity.text().trim().toInteger()
		while(bagQuantity > 0){
			$("#cart-container > a > div").click()
			$(".actions > li:nth-child(1) > a:nth-child(1)").click()
			$(".logo").click()
			myBagQuantity = $("#cart-container > a > div > h3 > span")
			bagQuantity = myBagQuantity.text().trim().toInteger()
		}
		ProductDataModelMap.getInstance().getProductMap().clear()
	}

	def deleteCookies(){
		getBrowser().clearCookies()
	}
	
	def toMyAccount(){
		$("#global-myaccount-signout").click()
	}
	
}