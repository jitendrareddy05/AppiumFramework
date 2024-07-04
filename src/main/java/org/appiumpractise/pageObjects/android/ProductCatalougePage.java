package org.appiumpractise.pageObjects.android;

import java.util.List;

import org.appiumpractise.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalougePage extends AndroidActions {

	AndroidDriver driver;

	public ProductCatalougePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

//	driver.findElement(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).click();
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private WebElement addToCartButton;

//	driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> productNameList;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> addToCartList;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement screenTitle;

	public void addFirstItemToCart() {
		addToCartButton.click();
	}

	public CartPage navigateToCart() {
		cartButton.click();
		waitUntilElementTextIsDisplayed(screenTitle, "Cart", driver);
		return new CartPage(driver);
	}

	public void addProductToCart(String productName) {
		int totalProductsCount = productNameList.size();

		for (int i = 0; i < totalProductsCount; i++) {
			String actualProductName = productNameList.get(i).getText();

			if (actualProductName.equalsIgnoreCase(productName)) {
				addToCartList.get(i).click();
			}
		}
	}
}
