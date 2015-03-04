import geb.driver.SauceLabsDriverFactory

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.phantomjs.PhantomJSDriverService
import org.openqa.selenium.remote.DesiredCapabilities

import com.lsco.test.PropertyProvider


waiting { timeout = 30 }

if(System.properties.containsKey("springboard.geb.baseUrl") && System.properties.containsKey("springboard.geb.country") && System.properties.containsKey("springboard.geb.locale")) {
	def url = System.properties.get("springboard.geb.baseUrl")+"/"+System.properties.get("springboard.geb.country")+"/"+System.properties.get("springboard.geb.locale")+"/"
	country = System.properties.get("springboard.geb.country")
	locale = System.properties.get("springboard.geb.locale")
	site = System.properties.get("springboard.geb.site")
	basicUrl = System.properties.get("springboard.geb.baseUrl")
	baseUrl = url
} else {
	site = "levi"
//	site = "dockers"
	basicUrl = "http://int-430-web-000." + site + "-site.com"
	country = "DE"
 locale = "de_DE"
//	country = "GB"
//	locale = "en_GB"
//	country = "FR"
//	locale = "fr_FR"
//	country = "DK"
//	locale = "da_DK"
//	country = "NL"
//	locale = "nl_NL"
	baseUrl = basicUrl + "/" + country + "/" + locale + "/"
}

environments {

	// run via “./gradlew chromeTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chrome {
		driver = { new ChromeDriver() }
	}

	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		driver = { new FirefoxDriver() }
	}

    ie {
        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
        capability.setCapability("nativeEvents", false);
        capability.setCapability(InternetExplorerDriver.
                INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        driver = { new InternetExplorerDriver(capability) }
    }



	// run via “./gradlew phantomJs”
	phantomJs {
		DesiredCapabilities caps = new DesiredCapabilities()
		caps.setJavascriptEnabled(true)
		String[] args = [
			"--web-security=no",
			"--ignore-ssl-errors=yes"
		]
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, args)
		driver = { new PhantomJSDriver(caps)}
	}
	// run via ./gradlew clean firefoxSauceTest -PbuildName="<BUILD NAME>" -PbuildNumber="<BUILD NUMBER>" -PbaseUrl="<URL>" -Pcountry="<COUNTRY>" -Plocale="<LOCALE>"
	firefoxSauce {
		driver = {
			new SauceLabsDriverFactory().create("firefox:linux:31","koteswarn", "77ee15ef-1a8b-4e51-b6b9-04f5bae6de97",
					["build":System.properties.get("springboard.geb.build"), "name":System.properties.get("springboard.geb.name"),"tags":[
							"Smoke",
							System.properties.get("springboard.geb.country")+"/"+System.properties.get("springboard.geb.locale")
						]]) }
	}
	// ./gradlew clean sauceLabs -PbuildName="Test Bbuild" -PbuildNumber="001" -PbaseUrl="http://preprod-001.levi-site.com" -Pcountry="GB" -Plocale="en_GB" -PosBrowserVersion="firefox:linux:31" -Pusername="bastianArvatoSystems" -Ppassword="45f66d58-85fe-4c8b-a662-2d8ef8ef0cd3" -Psite="levi"
	sauceLabs {
		driver = {
			new SauceLabsDriverFactory().create(System.properties.get("springboard.saucelabs.osBrowserVersion"),System.properties.get("springboard.saucelabs.username"), System.properties.get("springboard.saucelabs.password"),
					["build":System.properties.get("springboard.geb.build"), "name":System.properties.get("springboard.geb.name"),"tags":[
							"Smoke",
							System.properties.get("springboard.geb.country")+"/"+System.properties.get("springboard.geb.locale")
						],"max-duration":3600,"command-timeout":600]) }
	}

}

PropertyProvider.getInstance().site = site
PropertyProvider.getInstance().country = country
PropertyProvider.getInstance().locale = locale

