package linkInNewWindow;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.BrowserDriverUtility;

public class OpenPageInNewTab {
	static WebDriver dr;
	static WebElement ele;
	
	public static void startBrowser() {
		dr = BrowserDriverUtility.InvokeBrowser("webdriver.chrome.driver", "./webdrivers/chromedriver.exe",
				"http://www.google.ca");
	}
	
	public static void ClickOnLink() throws InterruptedException {
		//Get address of parent window as a string
		String parentWindow = dr.getWindowHandle();

		ele = dr.findElement(By.linkText("Gmail"));

		// Method-1:
		JavascriptExecutor js = (JavascriptExecutor) dr;
		js.executeScript("return arguments[0].target='_blank'", ele);
		ele.click();
		Thread.sleep(3000);

		//Navigate to tabs left to right and reverse.
        dr.switchTo().window(parentWindow);
        dr.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("Chetan Kanani");
        Thread.sleep(3000);
          
		/*//Method-2:
		Actions action = new Actions(dr);
		action.keyDown(Keys.CONTROL).click(ele).keyUp(Keys.CONTROL).build().perform();
		Thread.sleep(3000);

		//for navigating left to right side:
		action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
		Thread.sleep(3000);
		
		//For navigating right to left:
		action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys(Keys.TAB).build().perform();
		*/
        Thread.sleep(3000);
	}

	private static void closeBrowser() {
		dr.quit();
	}
	public static void main(String[] args) throws InterruptedException {
		startBrowser();
		ClickOnLink();
		closeBrowser();
	}


}
