package com.lsco.test.page.navigation

import geb.Page

import com.lsco.test.PropertyProvider

class WomenCategoryPage extends Page{

	static url = "category/women/jeans"

	
	static content = {
		headline { $(".content > h1")}
	}
	
	static at = {
		String okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("women.jeans")
		assert $(".content > h1").text().toUpperCase() == okValue 
	}

	def toAllCollectionsPage(){
		$("a.cta.see-all").click()
		//waitFor(30){ $("#main-container > article:nth-child(1) > header > article > div > h1").text() == "JEANS"}
	}
	
}
