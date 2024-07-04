package org.appiumpractise.TestCases;

import org.appiumpractise.TestComponents.BaseTest;
import org.appiumpractise.pageObjects.android.CartPage;
import org.appiumpractise.pageObjects.android.ProductCatalougePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_tc_2 extends BaseTest{
	
	@Test
	public void FillForm() throws InterruptedException {
		formPage.chooseCountry("Argentina");
		formPage.setNameField("Hannah Mckay");
		formPage.chooseGender("female");
		ProductCatalougePage productCatalougePage = formPage.submitForm();
		productCatalougePage.ScrollUntilElementIsFound("Jordan Lift Off");
		
		//using dynamic xpath
//		driver.findElement(By.xpath("//android.widget.TextView[@text='Jordan Lift Off']"
//				+ "/following-sibling::android.widget.LinearLayout/android.widget.TextView[@text='ADD TO CART']")).click();
		
		//Dynamically selecting product by scanning list based on text
		productCatalougePage.addProductToCart("Jordan Lift Off");		
		CartPage cartPage = productCatalougePage.navigateToCart();
		Assert.assertEquals(cartPage.getProductNameText(), "Jordan Lift Off");
	}

}
