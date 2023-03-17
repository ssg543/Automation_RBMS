package Pages;

import org.openqa.selenium.By;

import BaseFramework.BaseClass;

public class mainPage extends BaseClass
{
	public static void clickForms()
	{
		driver.findElement(By.xpath(ObjectConstants.ObjectXpath.STP_BUTTON)).click();
		driver.findElement(By.xpath(ObjectConstants.ObjectXpath.CREATE_NEW_RULES)).click();
		driver.findElement(By.xpath(ObjectConstants.ObjectXpath.RULES_GROUP)).click();
	}
}
