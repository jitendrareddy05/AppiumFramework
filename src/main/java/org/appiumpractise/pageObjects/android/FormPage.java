package org.appiumpractise.pageObjects.android;

import org.appiumpractise.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {

	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

//	driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;

//	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Jitendra");
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameTextField;

//	driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleRadioButton;

//	driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement maleRadioButton;

//	driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopButton;

//	driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
	@AndroidFindBy(xpath = "//android.widget.Toast")
	private WebElement errorToaster;

	public void chooseCountry(String countryName) {
		countryDropdown.click();
		ScrollUntilElementIsFound(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
	}

	public void setNameField(String name) {
		nameTextField.sendKeys(name);
	}

	public void chooseGender(String gender) {
		if (gender.contains("female"))
			femaleRadioButton.click();
		else
			maleRadioButton.click();
	}

	public ProductCatalougePage submitForm() {
		letsShopButton.click();
		return new ProductCatalougePage(driver);
	}

	public String getToasterMessage() {
		
		return errorToaster.getAttribute("name");
	}
	
	public void setActivity() {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
                "com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
	}
}
