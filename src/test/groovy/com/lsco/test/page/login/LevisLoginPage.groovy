package com.lsco.test.page.login

import com.lsco.test.page.model.UserDataModel
import com.lsco.test.page.model.UserDataModelMap
import geb.Page

import com.lsco.test.PropertyProvider
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.math.RandomUtils

class LevisLoginPage extends Page {

	//static url = "login"

	static at = {
		def okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("my.account")
		$("#main-container > div > article > h1").text().toUpperCase() == okValue
	}
	
	def fillLoginFields(username,password) {
		$("#loginForm").j_username = username
		$("#loginForm").j_password = password
		
		assert $("#loginForm").j_username == username
		assert $("#loginForm").j_password == password
	}
	
	def submitLoginForm() {
		$("#link-signin").click()
	}
	
	def signInWithFacebook() {
		$("button", text:'Sign in with Facebook').click()
	}

	def signUpAsRegisteredRandomUser(){
		def email=UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getEmail().toLowerCase()
		def password=UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getPassword()
		fillLoginFields(email,password)
	}

}
