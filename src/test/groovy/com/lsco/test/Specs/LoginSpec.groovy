package com.lsco.test.Specs

import com.lsco.test.Specs.Grouping.basicDockersDE
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.LevisMyAccountPage
import groovy.util.logging.Slf4j

@Slf4j
class LoginSpec extends BaseSpec {

    def setup(){
        waitFor {at LevisLoginPage}
    }


    def "Login to My Account with invalid Email and password"(){

    }
    /**
     * Test to Login to Lewis page with a valid Username and Password.
     * @return
     */
    @basicDockersDE
    def "Login into My Account using Valid EMail and Password"(){

        expect:
        at  LevisLoginPage

        when: "Enter a valid Email and Password."
        fillLoginFields(userName,password)
        submitLoginForm()
        waitFor {at LevisMyAccountPage}

        then: "Validate the User is able to Login."
        verifyEmailInHeader(emailHeader)

        where:
        userName | password | emailHeader
        "qa2014testautomation@mailinator.com" | "123456Mb" | "JOHN QA"

    }
    /**
     * Test to Login to Lewis page with a valid Username and Password.
     * @return
     */
    def "Login into My Account using Valid facebook Account"(){

        expect:
        at  LevisLoginPage

        when: "Enter a valid Email and Password."
        fillLoginFields(userName,password)
        submitLoginForm()
        waitFor {at LevisMyAccountPage}

        then: "Validate the User is able to Login."
        verifyEmailInHeader(emailHeader)

        where:
        userName | password | emailHeader
        "qa2014testautomation@gmail.com" | "pass123456" | "JOSE PEREZ"     //Facebook account with valid Credentials.
    }

    def cleanup() {
        //Need to add the code for Logout and Navigate to the Levis Login Page.
    }


}
