package com.lsco.test.page

import geb.Page
import geb.navigator.Navigator

import org.apache.commons.lang.math.RandomUtils

import com.lsco.test.PropertyProvider
import com.lsco.test.page.model.ProductDataModel
import com.lsco.test.page.model.ProductDataModelMap

class FirstProductPage extends Page {

	static content = {
		productForm (wait: true) { $("#pdp-buystack-form") }

		waistValuesContainer { $("#pdp-buystack-waist-values") }
		lenghthValuesContainer { $("#pdp-buystack-length-values") }

		availableWaistValues(required: false) { moduleList ProductModule, waistValuesContainer.find("li") }
		//li.size-swatch-wrapper
		availableLengthValues(required: false) { moduleList ProductModule, lenghthValuesContainer.find("li") }
		//li.size-swatch-wrapper

		productRecommendationsContainer { $("#product1_rr ul") }
		productRecommendationsItems(required: false) {
			moduleList ProductRecommendationItemModule, productRecommendationsContainer.find("li")
		}
		pageData { js.pageData }

		productRecommendationsGoesWellWithContainer { $("#product2_rr ul") }
		productRecommendationsGoesWellWithItems(required: false) {
			moduleList ProductRecommendationItemModule, productRecommendationsGoesWellWithContainer.find("li")
		}

		sizeValuesContainer { $("#pdp-buystack-size-values") }
		availableSizeValues(required: false) { moduleList ProductModule, sizeValuesContainer.find("li") }
	}

	static at = { productForm }

	def selectMeasures() {
		String brand = PropertyProvider.getInstance().getLocalizedPropertyValue("brand")
		StringBuilder productData = new StringBuilder();
		def product = new ProductDataModel()
		//Unselect any previos selection due to a previous failed test
		while ($(".size-swatch-wrapper.notouch-device.selected").isDisplayed()) {
			$(".size-swatch-wrapper.notouch-device.selected").click()
		}

		if (brand == "LEVI") {
			assert availableWaistValues.size() > 0
			assert availableLengthValues.size() > 0
			def addedToBag = false
			for (ProductModule productsWaistValues : availableWaistValues(0)) {
				productsWaistValues.waist.click()
				for (ProductModule productsLengthValues : availableLengthValues(0)) {
					productsLengthValues.length.click()
					if (!($("div.button-large.btn-gray.out-of-stock").isDisplayed())) {
						//$(".button-large.btn-highlight.add-to-bag").click()
						productData.append("Waist: " + productsWaistValues.waist.text())
						productData.append(" Length: " + productsLengthValues.length.text())
						product.productWaist = productsWaistValues.waist.text()
						product.productLength = productsLengthValues.length.text()
						addedToBag = true
						break
					}
				}
				if (addedToBag) {
					break
				}
			}
		} else {
			assert availableSizeValues.size() > 0
			def addedToBag = false
			for (ProductModule productsSizeValue : availableSizeValues(0)) {
				productsSizeValue.size.click()
				if (!($("div.button-large.btn-gray.out-of-stock").isDisplayed())) {
					//$(".button-large.btn-highlight.add-to-bag").click()
					productData.append("Size: " + productsSizeValue.size.text())
					product.productSize = productsSizeValue.size.text()
					break
				}

			}
		}
		productData.append(" Id: " + $(".style-no").text())
		product.productId = $(".style-no").text()
		productData.append(" Name: " + $(".title").text())
		product.productName = $(".title").text()
		ProductDataModelMap.getInstance().productMap.put("SELECTED_PRODUCT", product)
		println "Selected product: " + productData
	}


	def selectDifferentMeasures() {
		String brand = PropertyProvider.getInstance().getLocalizedPropertyValue("brand")
		StringBuilder productData = new StringBuilder();
		def product = new ProductDataModel()
		//Unselect any previos selection due to a previous failed test
		while ($(".size-swatch-wrapper.notouch-device.selected").isDisplayed()) {
			$(".size-swatch-wrapper.notouch-device.selected").click()
		}

		int waistIndex = 0
		if (brand == "LEVI") {

			assert availableWaistValues.size() > 0
			assert availableLengthValues.size() > 0
			def addedToBag = false
			for (ProductModule productsWaistValues : availableWaistValues(0)) {
				productsWaistValues.waist.click()
				for (ProductModule productsLengthValues : availableLengthValues(0)) {
					productsLengthValues.length.click()
					if (!($("div.button-large.btn-gray.out-of-stock").isDisplayed()) && ProductDataModelMap.getInstance().getProductMap().get("SELECTED_PRODUCT").getProductWaist().compareTo(productsWaistValues.waist.text()) < 0) {
						productData.append("Waist: " + productsWaistValues.waist.text())
						productData.append(" Length: " + productsLengthValues.length.text())
						product.productWaist = productsWaistValues.waist.text()
						product.productLength = productsLengthValues.length.text()
						addedToBag = true
						break
					}
					waistIndex++
				}
				if (addedToBag) {
					break
				}
			}
		} else {


			assert availableSizeValues.size() > 0
			def addedToBag = false
			for (ProductModule productsSizeValue : availableSizeValues(0)) {
				productsSizeValue.size.click()
				if (!($("div.button-large.btn-gray.out-of-stock").isDisplayed()) && DockersSize.getValue(productsSizeValue.size.text()) > DockersSize.getValue(ProductDataModelMap.getInstance().getProductMap().get("SELECTED_PRODUCT").getProductSize())) {
					//$(".button-large.btn-highlight.add-to-bag").click()
					productData.append("Size: " + productsSizeValue.size.text())
					product.productSize = productsSizeValue.size.text()
					break
				}

			}
		}
		productData.append(" Id: " + $(".style-no").text())
		product.productId = $(".style-no").text()
		productData.append(" Name: " + $(".title").text())
		product.productName = $(".title").text()
		ProductDataModelMap.getInstance().productMap.put("SELECTED_PRODUCT", product)
		println "Selected product: " + productData
	}


	def selectProductQuantityInPDPCombo(String index) {
		$(".select-arrow-box").click()
		$(".faux-select-group >li:nth-child(" + index + ")").click()
	}

	def addProductToBagInPDPSection() {
		$("a.button-large.btn-highlight.add-to-bag").click()

	}

	def getBagQuantity() {
		$("#cart-container > a > div > h3 > span").text().trim().toInteger()
	}

	def addProductToBag() {
		$("a.button-large.btn-highlight.add-to-bag").click()
		Thread.sleep(5000)
		def myBagQuantity = $("#cart-container > a > div > h3 > span")
		int bagQuantity = myBagQuantity.text().trim().toInteger()
		StringBuilder message = new StringBuilder()
		message.append("My bag quantity: " + bagQuantity)
		//Check if the product was added or no due to stock issues.
		if (bagQuantity == 0) {
			message.append(" -> Product OUT OF STOCK")
            selectMeasures()
            addProductToBag()
        }
		println message
	}

	def addProductToBag(String index) {
		selectProductQuantityInPDPCombo(index)
		addProductToBagInPDPSection()
		Thread.sleep(5000)
		StringBuilder message = new StringBuilder()
		message.append("My bag quantity: " + getBagQuantity())
		//Check if the product was added or no due to stock issues.
		if (getBagQuantity() == 0) {
			message.append(" -> Product OUT OF STOCK")
			selectMeasures()
			addProductToBag(index)
		}
		println message
	}

	def verifyRecommendationsSectionData(String possibleMessage1, String possibleMessage2) {
		String sectionTitle = $("#product1_rr h2").text().trim().toUpperCase()

		String okMessage1 = PropertyProvider.getInstance().getLocalizedPropertyValue(possibleMessage1)
		String okMessage2 = PropertyProvider.getInstance().getLocalizedPropertyValue(possibleMessage2)

		assert sectionTitle == okMessage1 || sectionTitle == okMessage2

		assert productRecommendationsItems.size() > 0

		def products = productRecommendationsItems(0)
		boolean first = true
		for (ProductRecommendationItemModule product : products) {
			//This modifications were made because in qa and int enviroments the missing informtion is a common issue. So we just log the missimg data.
			if ((!product.detail.text() || !product.price.text() || !product.image.@complete || product.image.@naturalWidth == "undefined" || product.image.@naturalWidth.toInteger() == 0)) {
				println "*********************** MISSING PRODUCT INFORMATION ***********************"
				println " - Product detail:" + product.detail.text()
				println " - Product price:" + product.price.text()
				println " - Product image scr:" + product.image.@src
				println " - Product image complete:" + product.image.@complete
				println " - Product image natural width:" + product.image.@naturalWidth
				println "***************************************************************************"
			} else {
				//verify description exists
				assert product.detail.text()

				assert product.price.text()

				//verify the image src attribute
				assert (product.image.@src)

				//verify that images are loaded properly
				assert product.image.@complete && product.image.@naturalWidth != "undefined" && product.image.@naturalWidth.toInteger() > 0
			}
		}
	}

	def verifyRecommendationsGoesWellWithSectionData(String message) {
		String sectionTitle = $("#product2_rr h2").text().trim().toUpperCase()
		String okMessage = PropertyProvider.getInstance().getLocalizedPropertyValue(message)
		assert sectionTitle == okMessage

		def bigItem = "#product2_rr > div > div > div.outfit-hero > a"
		def bigItemImage = $(bigItem + " > img")
		assert bigItemImage.@src && bigItemImage.@complete && bigItemImage.@naturalWidth != "undefined" && bigItemImage.@naturalWidth.toInteger() > 0
		def bigItemDescription = $(bigItem + " > div > h4")
		assert bigItemDescription.text()
		def bigItemColor = $(bigItem + " > div > p.color-name")
		assert bigItemColor.text()
		def bigItemPrice = $(bigItem + " > div > p.price > span > span")
		assert bigItemPrice.text()

		def form = $("#product2_rr > div > div > div.outfit-elements > div > a")
		form.each { Navigator element ->
			def image = element.find("img")
			//This modifications were made because in qa and int enviroments the missing informtion is a common issue. So we just log the missimg data.
			if ((!element.find("h4").text() || !element.find("p.color-name").text() || !element.find("span.now-price").text() || !image.@complete || image.@naturalWidth == "undefined" || image.@naturalWidth.toInteger() == 0)) {
				println "*********************** MISSING PRODUCT INFORMATION ***********************"
				println " - Product detail:" + element.find("h4").text()
				println " - Product price:" + element.find("span.now-price").text()
				println " - Product color:" + element.find("p.color-name").text()
				println " - Product image scr:" + image.@src
				println " - Product image complete:" + image.@complete
				println " - Product image natural width:" + image.@naturalWidth
				println "***************************************************************************"
			} else {
				assert element.find("h4").text()
				assert element.find("p.color-name").text()
				assert element.find("span.now-price").text()
				assert image.@src && image.@complete && image.@naturalWidth != "undefined" && image.@naturalWidth.toInteger() > 0
			}
		}
	}


	def toProductRecommendationItemPage() {
		ProductRecommendationItemModule[] products = productRecommendationsItems(0)
		int productIndex = RandomUtils.nextInt(products.length)


		ProductRecommendationItemModule product = products[productIndex]

		println product.detail.text()

		ProductDataModel productDataModel = new ProductDataModel()
		productDataModel.productName = product.detail.text()
		productDataModel.productPrice = product.price.text()

		ProductDataModelMap.getInstance().getProductMap().put("RECOMMENDED_PRODUCT", productDataModel)

		println productDataModel.productName
		println productDataModel.productPrice
		product.link.click()
	}

	def toProductRecommendationGoesWellWithItemPage() {
		def bigItem = "#product2_rr > div > div > div.outfit-hero > a"
		def bigItemImage = $(bigItem + " > img")
		def bigItemDescription = $(bigItem + " > div > h4")
		def bigItemPrice = $(bigItem + " > div > p.price > span > span")


		ProductDataModel productDataModel = new ProductDataModel()
		productDataModel.productName = bigItemDescription.text()
		productDataModel.productPrice = bigItemPrice.text()

		ProductDataModelMap.getInstance().getProductMap().putAt("RECOMMENDED_PRODUCT", productDataModel)

		bigItemImage.click()
	}


	def verifyCorrectPDPData() {
		ProductDataModel productDataModel = ProductDataModelMap.getInstance().getProductMap().get("RECOMMENDED_PRODUCT")

		println $("div.buystack-prices:nth-child(9) > span:nth-child(2)").text()
		println productDataModel.productPrice
		println $("h1.title").text()
		println productDataModel.productName

		assert $("div.buystack-prices:nth-child(9) > span:nth-child(2)").text() == productDataModel.productPrice
		assert $("h1.title").text() == productDataModel.productName

		ProductDataModelMap.getInstance().getProductMap().clear()
	}

	def verifyProductDetailInformation() {
		def productTag = "product:"
		def productIdTag = "product_id:"
		def productGroupTag = "product_group:"

		StringBuilder pageDataText = new StringBuilder(pageData.toString());
		ProductDataModel productDataModel = new ProductDataModel()

		//verify that productTag is present
		def productTagIndex = pageDataText.indexOf(productTag)
		assert productTagIndex != -1

		//verify that productIdTag is present
		def productIdTagIndex = pageDataText.indexOf(productIdTag)
		assert productIdTagIndex != -1
		String productId = pageDataText.substring(productIdTagIndex)
		String productIdValue = productId.substring(productIdTag.size(), productId.indexOf(","))

		assert !productIdValue.isEmpty()
		productDataModel.productId = productIdValue

		//verify that productIdTag is present
		def productGroupTagIndex = pageDataText.indexOf(productGroupTag)
		assert productGroupTagIndex != -1
		String productGroup = pageDataText.substring(productGroupTagIndex)

		String productGroupValue = productGroup.substring(productGroupTag.size(), productGroup.indexOf("]"))

		if (productGroupValue.contains(",")) {
			productGroupValue = productGroup.substring(productGroupTag.size(), productGroup.indexOf(","))
		}

		assert !productGroupValue.isEmpty()

		productDataModel.productGroup = productGroupValue

		ProductDataModelMap.getInstance().getProductMap().put("PRODUCT_TO_VERIFY", productDataModel)
	}

	def verifyProductPDP() {
		ProductDataModel productDataModel = ProductDataModelMap.getInstance().getProductMap().get("SELECTED_PRODUCT")
		assert $(".title").text() == productDataModel.productName
	}

	def verifyHighestPriorityOrderLevelPromotion() {
		assert !($("#pdp-buystack-form > div.cart-promo > div > h4").text().toUpperCase().contains("LOW"))
		assert $("#pdp-buystack-form > div.cart-promo > div > h4").text().toUpperCase().contains("HIGH")
	}

	def checkProductQuantityInBag(String expectedQuantity) {
		String productsInBag = $(".mini-cart > h3:nth-child(1) > span:nth-child(1)").text()
		assert productsInBag == expectedQuantity
	}

	def CheckErrorsInBagItems(String property) {
		String errorMEssage = PropertyProvider.getInstance().getLocalizedPropertyValue(property)
		interact {
			moveToElement($(".mini-cart"))
		}
		waitFor(60, 3) { $(".cartError > span:nth-child(1)").isDisplayed() }
		assert $(".cartError > span:nth-child(1)").text().toLowerCase() == errorMEssage.toLowerCase()
	}


}