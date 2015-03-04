
package com.lsco.test.suite.nontransactional

import groovy.swing.factory.GlueFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber

import org.junit.runner.RunWith

@Cucumber.Options(
	features="src/test/groovy/com/lsco/test/feature",
	glue = "src/test/groovy/com/lsco/test/steps",
	format=["pretty", "html:build/reports/cucumber"],
	strict=true,
	tags = "@NonTransactional_DS_FR_fr_FR")
@RunWith(Cucumber)
public class NonTransactional_dockers_FR_fr_FR_TestSuite {

}