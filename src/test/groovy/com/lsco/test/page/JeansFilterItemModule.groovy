package com.lsco.test.page

import java.util.Collection
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import geb.Module;
import geb.Page;
import geb.navigator.Navigator;
import groovy.lang.Range;

class JeansFilterItemModule extends Module {

	static content = {
		filter { $("label") }
		detail { filter.find("input") }
	}
	
}
