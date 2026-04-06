package basav.start;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SimpleApplication {

	public static void main(String[] args) throws InterruptedException {

		String itemName = "ZARA COAT 3";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.findElement(By.id("userEmail")).sendKeys("Ganesh1511@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ganesh1511@gmail.com");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".offset-sm-1")));

		List<WebElement> elements = driver.findElements(By.cssSelector(".offset-sm-1"));

		WebElement element = elements.stream().filter(e -> e.findElement(By.tagName("b")).getText().contains(itemName))
				.findFirst().orElseThrow();

		element.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ngx-spinner-overlay")));

		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

		List<WebElement> itemList = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean result = itemList.stream().anyMatch(e -> e.getText().contains(itemName));

		Assert.assertTrue(result);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Buy Now')]")));

		driver.findElement(By.xpath("//button[contains(.,'Buy Now')]")).click();

		WebElement el = driver.findElement(By.cssSelector("*[placeholder='Select Country']"));

		Actions actions = new Actions(driver);
		actions.sendKeys(el, "india").build().perform();

		driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
		driver.findElement(By.cssSelector(".btnn ")).click();

		String text = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));

		Thread.sleep(5000);
		System.out.println("✅ Test Passed: All interactions successful!");
		driver.quit();

	}

}
