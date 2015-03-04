#SPRING-15519
Feature: Login into my account using a Facebook account

@Active
Scenario: Login into my account using a Facebook account
Given an guest user 
When accessing the homepage
And accessing the login page
And signing in with the facebook acount "qa2014testautomation@gmail.com" and password "pass123456" 
Then I should see the my account page with "JOSE PEREZ" displayed in the header. 
