package ActionsPackages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragandDrop {

	public WebDriver driver;

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void TestcaseOne() throws InterruptedException {
		driver.get("https://jqueryui.com/droppable/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement FrameOne = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(FrameOne);
		Actions action = new Actions(driver);
		WebElement PickTheElement = driver.findElement(By.id("draggable"));
		WebElement DropZone = driver.findElement(By.id("droppable"));
		js.executeScript("arguments[0].scrollIntoView();", PickTheElement);
		Thread.sleep(2000);
		action.clickAndHold(PickTheElement).moveToElement(DropZone).release().build().perform();
		Thread.sleep(2000);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
