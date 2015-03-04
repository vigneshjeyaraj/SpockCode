package com.lsco.test.page

import java.util.Collection
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import geb.Module;
import geb.Page;
import geb.navigator.Navigator;
import groovy.lang.Range;

class ProductModule extends Module {

	static content = {
		waist { $("a.size-swatch") }
		length { $("a.size-swatch") }
		size { $("a.size-swatch") }
	}
	
}
