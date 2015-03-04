package com.lsco.test.page

import java.util.Collection
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import geb.Module;
import geb.Page;
import geb.navigator.Navigator;
import groovy.lang.Range;

class CategoryItemModule extends Module {

	static content = {
		productDetails { $("div.product-details") }
		productImages { $("div.product-images") }
		detail { productDetails.find("a") }
		image { productImages.find("a").find("img") }
	}
}
