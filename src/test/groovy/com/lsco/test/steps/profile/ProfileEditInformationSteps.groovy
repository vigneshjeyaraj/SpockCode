package com.lsco.test.steps.profile

import com.lsco.test.page.hmc.HMCHomePage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.register.AccountRegistrationPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(geb.Browser)


When(~'^register a new random user$') { ->
    at AccountRegistrationPage
    registerANewRandomUser()
    submitRegistrationForm()
    logOut()
}

Then(~'^login as random registered user$') { ->
    at LevisLoginPage
    signUpAsRegisteredRandomUser()
    submitLoginForm()
}

Then(~'^udpdate profile info$') {  ->
    to LevisMyAccountPage
    at LevisMyAccountPage
    editProfileRandomUser()
    UpdateData()
    verifyDataUpdateMesage()
    logOut()
}

Then(~'^system logout$') { ->
    to LevisMyAccountPage
    at LevisMyAccountPage
    logOut()
}

Then(~'^verify new random user data$') { ->
    to LevisMyAccountPage
    at LevisMyAccountPage
    verifyNewRandomUserData()

}

Then(~'^delete random user$') { ->
    to HMCHomePage
    at HMCHomePage
    deleteRandomUserInHybris()
}
