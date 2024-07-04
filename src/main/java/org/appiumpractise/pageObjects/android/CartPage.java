package org.appiumpractise.pageObjects.android;

import java.util.List;

import org.appiumpractise.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement screenTitle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productsPrice;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPrice;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement TermsButton;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement closeButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkBox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private WebElement productName;

	public List<WebElement> getProductPriceList() {
		waitUntilElementTextIsDisplayed(screenTitle, "cart", driver);
		return productsPrice;
	}

	public double getTotalCartValue() {
		int count = productsPrice.size();
		double totalSum = 0;
		for (int i = 0; i < count; i++) {
			String priceText = productsPrice.get(i).getText();
			// $160.97 - subString with index 1 will give 160.97
			// Now the string has to be parsed to Double
			Double priceValue = Double.parseDouble(priceText.substring(1));
			totalSum = totalSum + priceValue;
		}
		return totalSum;
	}

	public Double getTotalPriceValue() {

		return getFormattedAmount(totalPrice.getText());
	}

	public void acceptTnC() {
		LongPressAction(TermsButton, 2000);
		closeButton.click();
	}

	public void proceedOrder() throws InterruptedException {
		checkBox.click();
		proceedButton.click();
		Thread.sleep(6000);
	}

	public String getProductNameText() {

		return productName.getText();
	}
}
