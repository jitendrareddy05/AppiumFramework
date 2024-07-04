package org.appiumpractise.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.appiumpractise.pageObjects.android.FormPage;
import org.appiumpractise.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest extends AppiumUtils {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws URISyntaxException, IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\org\\appiumpractise\\resources\\data.properties"));
		prop.load(fis);
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
				: prop.getProperty("ipAddress");
//		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String androidDeviceName = prop.getProperty("AndroidDeviceName");
		String platformName = prop.getProperty("platformName");

		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName(platformName);
		options.setDeviceName(androidDeviceName);
		options.setApp(System.getProperty("user.dir")
				+ "\\src\\test\\java\\org\\appiumpractise\\TestResources\\General-Store.apk");

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		formPage = new FormPage(driver);
	}

	@AfterClass(alwaysRun = true)
	public void TearDown() {
		driver.quit();
		service.stop();
	}
}
