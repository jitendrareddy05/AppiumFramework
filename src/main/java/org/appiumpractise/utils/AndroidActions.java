package org.appiumpractise.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {
	AppiumDriver driver;

	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
	}

	public void LongPressAction(WebElement longPressElement, int TimeDuration) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) longPressElement).getId(), "duration", TimeDuration));
	}

	public void ScrollToBottomAction() {
		Boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 1000, "direction", "down", "percent", 5.0));
		} while (canScrollMore);
	}

	public void ScrollUntilElementIsFound(String textToFind) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + textToFind + "\"));"));
	}

	public void SwipeAction(WebElement focusElement, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) focusElement).getId(), "direction", direction, "percent", 0.25));
	}

}
