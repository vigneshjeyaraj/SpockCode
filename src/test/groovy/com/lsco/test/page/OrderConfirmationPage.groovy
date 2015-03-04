package com.lsco.test.page

import geb.Page

import com.lsco.test.PropertyProvider

class OrderConfirmationPage extends Page {

	static content = {
		orderMessage(wait:true) { $("#top > h1")}
	}


	static at = {
		def okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("order.success.message")
		assert orderMessage.text() == okValue
	}
}
