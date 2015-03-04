package com.lsco.test.page.login

import geb.Page

class FacebookLoginPage extends Page {
	
	static at = { 
		//$("#h2.uiHeaderTitle").text() == "Facebook Login"
		$("#content > div > div > div.uiHeader.uiHeaderBottomBorder.mhl.mts.uiHeaderPage.interstitialHeader > div > div:nth-child(2) > h2").text() == "Facebook Login"
	}
	
	def fillFacebookLogin(email, password){
		$("#email").value(email)
		$("#pass").value(password)
		$("#u_0_1").click()
	}

}
