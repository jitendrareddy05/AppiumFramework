package org.appiumpractise.TestCases;

import org.appiumpractise.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_tc_1 extends BaseTest{
	
	@Test
	public void FillForm() throws InterruptedException {
		formPage.chooseCountry("Argentina");
		formPage.chooseGender("female");
		formPage.submitForm();
		String toastMessage=formPage.getToasterMessage();
		Assert.assertEquals(toastMessage, "Please enter ysour name");
		if(toastMessage!=null) {
			formPage.setNameField("Hannah Mckay");
			formPage.submitForm();
		}
	}

}
