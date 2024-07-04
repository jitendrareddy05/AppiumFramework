package org.appiumpractise.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.appiumpractise.TestComponents.BaseTest;
import org.appiumpractise.pageObjects.android.CartPage;
import org.appiumpractise.pageObjects.android.ProductCatalougePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class eCommerce_tc_4_Hybrid extends BaseTest {

	@Test(dataProvider = "getData", groups={"Smoke"})
	public void E2E_TC(HashMap<String,String> input) throws InterruptedException {

		// Filling the form
		formPage.chooseCountry(input.get("countryName"));
		formPage.setNameField(input.get("name"));
		formPage.chooseGender(input.get("gender"));
		ProductCatalougePage productCatalougePage = formPage.submitForm();

		// Add products to the cart
		productCatalougePage.addFirstItemToCart();
		productCatalougePage.addFirstItemToCart();
		CartPage cartPage = productCatalougePage.navigateToCart();

		double totalSum = cartPage.getTotalCartValue();
		Double totalPriceValue = cartPage.getTotalPriceValue();
		Assert.assertEquals(totalSum, totalPriceValue);

		cartPage.acceptTnC();
		cartPage.proceedOrder();

//		Set<String> contexts = driver.getContextHandles();
//		for (String contextName : contexts) {
//			System.out.println(contextName);
//		}
//		driver.context("WEBVIEW_com.androidsample.generalstore");
//		driver.findElement(By.name("q")).sendKeys("Jitendra", Keys.ENTER);
//		Thread.sleep(3000);
//		driver.pressKey(new KeyEvent(AndroidKey.BACK));
//
//		driver.context("NATIVE_APP");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\org\\appiumpractise\\TestData\\ecommerce.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	@BeforeMethod (alwaysRun=true)
	public void preSetup() throws InterruptedException {
//		formPage.setActivity();
//		Thread.sleep(5000);
		driver.navigate().back();
	}
}
