package ActionsPackages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class MouseClick {

	public WebDriver driver;

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void RightClick() throws InterruptedException {
		driver.get("http://demo.guru99.com/test/simple_context_menu.html");
		Actions action = new Actions(driver);
		WebElement RightClickButton = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(RightClickButton).build().perform();
		Thread.sleep(2000);
		WebElement EditButton = driver
				.findElement(By.xpath("//div[@id='context-menu-layer']/following-sibling::ul/li[1]"));
		EditButton.click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
