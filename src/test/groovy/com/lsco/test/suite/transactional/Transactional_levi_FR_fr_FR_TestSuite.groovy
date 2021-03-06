
package com.lsco.test.suite.transactional

import groovy.swing.factory.GlueFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber

import org.junit.runner.RunWith

@Cucumber.Options(
	features="src/test/groovy/com/lsco/test/feature",
	glue = "src/test/groovy/com/lsco/test/steps",
	format=["pretty", "html:build/reports/cucumber"],
	strict=true,
	tags = "@Transactional_LE_FR_fr_FR")
@RunWith(Cucumber)
public class Transactional_levi_FR_fr_FR_TestSuite {

}