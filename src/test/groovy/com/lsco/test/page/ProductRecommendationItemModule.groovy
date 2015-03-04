package com.lsco.test.page

import java.util.Collection
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import geb.Module;
import geb.Page;
import geb.navigator.Navigator;
import groovy.lang.Range;

class ProductRecommendationItemModule extends Module {

	static content = {
//		productDetails { $("div.product-details") }
//		productImages { $("div.product-images") }
//		detail { productDetails.find("a > p.name") }
//		price { productDetails.find("a > p.pricing") }
//		link { productDetails.find("a") }
//		image { productImages.find("a > img")}
//		imageAux {$("a > img")}
		productDetails { $("a") }
		//productImages { $("div.product-images") }
		detail { productDetails.find("div > h4") }
		price { productDetails.find("div > p.price") }
		link { productDetails }
		image { productDetails.find("img")}
		//imageAux {$("a > img")}
	}
	
}