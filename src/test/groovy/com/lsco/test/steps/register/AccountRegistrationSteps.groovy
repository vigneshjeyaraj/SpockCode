package com.lsco.test.steps.register
import geb.*

import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.hac.FlexibleSearchPage
import com.lsco.test.page.hac.HACLoginPage
import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.register.ExternalEmailPage
import com.lsco.test.page.login.LevisMyAccountPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(geb.Browser)

When(~'^creating a new email in "([^"]*)" with domain "([^"]*)"$') { String arg1, String arg2 ->
	go arg1
	at ExternalEmailPage
	createNewEmail(arg2)
}

When(~'^accessing the registration page$') { ->
    to LevisHomePage
	at LevisHomePage
	dismissPopup()
	checkIfSignoutIsRequired()
	toRegistrationPage()
	at AccountRegistrationPage
}

When(~'^enter the email "([^"]*)" and passwords "([^"]*)"$') { String arg1, String arg2 ->
	fillRegistrationFields(arg1,arg2)
	submitRegistrationForm()
	at LevisMyAccountPage
}

When(~'^enter the email and passwords "([^"]*)"$') { String arg1 ->
	fillRegistrationFields(arg1)
	submitRegistrationForm()
	at LevisMyAccountPage
}

Then(~'^I should see my new account page with "([^"]*)" displayed in the header$') {  String arg1 ->
	to LevisMyAccountPage
	at LevisMyAccountPage
	verifyEmailInHeader(arg1)
}


Then(~'^I should see my new account page with the email displayed in the header$') { ->
	to LevisMyAccountPage
	at LevisMyAccountPage
	verifyEmailInHeader()
}

Then(~'^see my new acount created in Hybris$') { ->
	to FlexibleSearchPage
	at FlexibleSearchPage
	checkAccountCreation()
}

Then(~'^receives the registration confirmation email in "([^"]*)".$') { String arg1 ->
	go arg1
	at ExternalEmailPage
	checkExternalEmailInbox()
}