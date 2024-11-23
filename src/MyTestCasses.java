import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCasses {
	
	WebDriver driver = new ChromeDriver();
	
	String WebSiteURL = "https://global.almosafer.com/en";
	
	Random rand = new Random();
	
	@BeforeTest
	public void Setup () throws InterruptedException {
		
		driver.manage().window().maximize();
		driver.get(WebSiteURL);
		
		Thread.sleep(1000);
		
		WebElement ButtonForTheCurrency = driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		ButtonForTheCurrency.click();
		
	}
	
	@Test(priority = 1)
	public void CheckTheEnglishLanguageIsDefault () {
		
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedLanguage = "en";
		Assert.assertEquals(ActualLanguage,ExpectedLanguage);
		
	}
	
	@Test(priority = 2,enabled = false)
	public void CheckDefaultTheCurrencyIsSAR () {
		
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
		String ExpectedCurrency = "SAR";
		Assert.assertEquals(ActualCurrency,ExpectedCurrency);
		
	}
	
	@Test (priority = 3,enabled = false)
	public void ContactNumber () {
		
		String ActualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String ExpectedNumber = "+966554400000";
		Assert.assertEquals(ActualNumber,ExpectedNumber);
		
	}
	
	@Test (priority = 4,enabled = false)
	public void CheckQitafLogoIsThereInTheFooter () {
		
		Boolean ActualLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		Boolean ExpectLogo = true;
		Assert.assertEquals(ActualLogo,ExpectLogo);
		
	}
	
	@Test (priority = 5,enabled = false)
	public void CheckHotelTabIsNotSelected () {
		
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualValue = HotelTab.getAttribute("aria-selected");
		String ExpectedValue = "false";
		Assert.assertEquals(ActualValue,ExpectedValue);
		
	}
	
	
	@Test (priority = 6,enabled = false)
	public void CheckDepartureDate () {
		
		int Today =LocalDate.now().getDayOfMonth();
		int Tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
		int DayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
		
		String ActualDepartureDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']")).getText();
		String ExpectedDepartureDate = Integer.toString(Tomorrow);
		Assert.assertEquals(ActualDepartureDate,ExpectedDepartureDate);
		
	}
	
	
	@Test (priority = 7,enabled = false)
	public void CheckReturnDate () {
		
		int Today =LocalDate.now().getDayOfMonth();
		int Tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
		int DayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
		
		String ActualReturnDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']")).getText();
		String ExpectedReturnDate = Integer.toString(DayAfterTomorrow);
		Assert.assertEquals(ActualReturnDate,ExpectedReturnDate);
		
	}
	
	
	@Test (priority = 8)
	public void RandomlyChangeTheLanguage () {
		
		String [] MyWebSites = {"https://global.almosafer.com/en","https://global.almosafer.com/ar"};
		
		int randomIndex = rand.nextInt(MyWebSites.length);
		
		driver.get(MyWebSites[randomIndex]);
		
		if (driver.getCurrentUrl().contains("ar")) {
			
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "ar";
			Assert.assertEquals(ActualLanguage,ExpectedLanguage);
			
		}else {
			
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "en";
			Assert.assertEquals(ActualLanguage,ExpectedLanguage);
		}
	}


}
