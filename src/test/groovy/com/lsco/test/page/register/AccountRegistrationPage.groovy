package com.lsco.test.page.register

import com.lsco.test.page.model.UserDataModel
import com.lsco.test.page.model.UserDataModelMap
import geb.Page
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.math.RandomUtils
import org.openqa.selenium.JavascriptExecutor

import com.lsco.test.PropertyProvider
import com.lsco.test.page.login.LevisMyAccountPage

class AccountRegistrationPage extends Page {
	
	//static url = "login"
	
	static content = {
		registerForm  { $("#registerForm") }
	}
	
	static at = {
		def okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("register")
		$("section.register > h2").text().toUpperCase() == okValue
	}
	
	def fillRegistrationFields(password){
		fillRegistrationFields(RegistrationDataModel.getInstance().email, password)
	}



	
	def fillRegistrationFields(username,password) {
		registerForm.email = username
		registerForm.find("input", id: "password-new").value(password)
		registerForm.find("input", id: "password-confirm").value(password)
		
		js( ' jQuery( "#age-chkbox" ).css("opacity", "100"); ' )
		js( ' jQuery( "#age-chkbox" ).css("left", "0px"); ' )
		
		$("#age-chkbox").value(true)

		assert registerForm.email == username
		assert registerForm.find("input", id: "password-new").value() == password
		assert registerForm.find("input", id: "password-confirm").value() == password
		assert $("#age-chkbox").value() == "on"
	}
	
	def js( String script ){
		(driver as JavascriptExecutor).executeScript( script )
   }
	
	def submitRegistrationForm() {
		$("#registerForm .btn-dbbdr").click()
	}


	private String generateRamdomAlphabeticString(int length) {
		RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(length) + 9)
	}

	private String generateRamdomAlphanumericString(int length) {
		RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(length) + 9)
	}

	def registerANewRandomUser(){
		UserDataModel userDataModel= UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA")
		userDataModel.setPassword(generateRamdomAlphanumericString(8)+"1Av#!")
		userDataModel.setName(generateRamdomAlphabeticString(10))
		userDataModel.setLastName(generateRamdomAlphabeticString(10))
		fillRegistrationFields(userDataModel.getEmail().toLowerCase(),userDataModel.getPassword())
	}

	def logOut(){
		$("#global-myaccount-signout").click()
	}
}
