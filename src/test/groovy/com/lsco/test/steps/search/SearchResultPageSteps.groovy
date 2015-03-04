package com.lsco.test.steps.search

import com.lsco.test.page.search.FailSearchResultPage
import geb.*
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.search.SearchResultPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(geb.Browser)


When(~'^searching the term "([^"]*)"$') { String arg1 ->
	to LevisHomePage
	at LevisHomePage
	searchTerm(arg1)
}

When(~'^searching a random term with length (\\d+)$') { int length ->
	to LevisHomePage
	at LevisHomePage
	searchRandomTerm(length)
}

Then(~'^the search page is populated with "([^"]*)" result.$') { String arg1 ->
	at SearchResultPage
	verifyPopulation(arg1)
}

Then(~'^I should be redirected to the Fail Search Page.$') { ->
	at FailSearchResultPage
}

Then(~'^I see the Other Items you migth like section with products quantity more than (\\d+)$') { int quantity->
	at FailSearchResultPage
	checkOtherItemsSection(quantity)
}
