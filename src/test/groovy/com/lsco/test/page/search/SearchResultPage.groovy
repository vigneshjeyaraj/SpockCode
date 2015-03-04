package com.lsco.test.page.search

import geb.Page

import com.lsco.test.PropertyProvider

class SearchResultPage extends Page {

	static at = {
		String okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("search.title")
		assert $("h1.section-header.center-header").text().toUpperCase() == okValue
	}
	
}