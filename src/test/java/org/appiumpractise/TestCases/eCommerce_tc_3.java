package org.appiumpractise.TestCases;


import org.appiumpractise.TestComponents.BaseTest;
import org.appiumpractise.pageObjects.android.CartPage;
import org.appiumpractise.pageObjects.android.ProductCatalougePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_tc_3 extends BaseTest{
	
	@Test
	public void FillForm() throws InterruptedException {
		formPage.chooseCountry("Argentina");
		formPage.setNameField("Hannah Mckay");
		formPage.chooseGender("female");
		ProductCatalougePage productCatalougePage=formPage.submitForm();
		
		productCatalougePage.addFirstItemToCart();
		productCatalougePage.addFirstItemToCart();
		CartPage cartPage=productCatalougePage.navigateToCart();
		
		double totalSum = cartPage.getTotalCartValue();
		Double totalPriceValue = cartPage.getTotalPriceValue();
		Assert.assertEquals(totalSum, totalPriceValue);
		cartPage.acceptTnC();
		cartPage.proceedOrder();
	}

}
