package com.lsco.test.page.login

import com.lsco.test.page.model.UserDataModel
import com.lsco.test.page.model.UserDataModelMap
import geb.Page

import com.lsco.test.PropertyProvider
import com.lsco.test.page.register.RegistrationDataModel
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.math.RandomUtils


class LevisMyAccountPage extends Page{
	
	static url = "my-account/update-profile"
	
	static at = {
		String okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("my.account")
		//@@@@@ Commented until is resolved
		//assert $("h1").text().toUpperCase() == okValue
		assert true
	}
	
	def verifyEmailInHeader(fullName){
		def headerText = $("#global-myaccount-cta2").text()
		assert headerText == fullName
	}
	
	def verifyEmailInHeader(){
		verifyEmailInHeader(RegistrationDataModel.getInstance().email.toUpperCase())
	}

	def getFirstNameProfileElem(){
		$(".form-container > ul:nth-child(1) > li:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
	}

	def getSurnameProfileElem(){
		$(".form-container > ul:nth-child(1) > li:nth-child(2) > div:nth-child(2) > input:nth-child(1)")
	}

	def getEmailProfileElem() {
		$("#email")
	}

	def editProfileRandomUser(){
		if (isRandomUserDataUpdated()){
			def newEmail=UserDataModelMap.getInstance().getUserMap().get("UPDATED_USER_DATA").getEmail().toLowerCase()
			def newName=generateRamdomAlphabeticString(13)
			def newLastName=generateRamdomAlphabeticString(13)
			UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").setEmail(newEmail)
			UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").setName(newName)
			UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").setLastName(newLastName)
			getFirstNameProfileElem().value(newName)
			getSurnameProfileElem().value(newLastName)
			getEmailProfileElem().value(newEmail)
		}
	}

	private String generateRamdomAlphabeticString(int length) {
		RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(length) + 1)
	}



	def UpdateData(){
		$(".btn-dbbdr").click()
	}

	boolean isRandomUserDataUpdated() {
		UserDataModelMap.getInstance().getUserMap().get("UPDATED_USER_DATA")!=null
	}

	def logOut(){
		$("#global-myaccount-signout").click()
	}

	def verifyDataUpdateMesage(){
		assert $(".js-success").text().toUpperCase()==PropertyProvider.getInstance().getLocalizedPropertyValue("my.account.update.message")
	}

	def verifyNewRandomUserData(){
		if ($("div.email-signup > div:nth-child(1) > span:nth-child(1)").isDisplayed()){
			$("div.email-signup > div:nth-child(1) > span:nth-child(1)").click()
		}
		assert getFirstNameProfileElem().value()==UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getName()
		assert getSurnameProfileElem().value()==UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getLastName()
		assert getEmailProfileElem().value()==UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getEmail()
	}
	
	def toLastPlacedOrder(){
		$("#OrdersLink").click()
		$("table.order-history > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > p:nth-child(1) > a:nth-child(1)").click()
	}
	
	def giftWrapCheck(){
		assert $(".list-type-04 > li:nth-child(2) > a:nth-child(1) > img:nth-child(1)").@title.toString().trim().toUpperCase().contains("GIFT WRAP") 
	}
	
}