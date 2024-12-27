import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

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
	
	
	@Test (priority = 8,enabled = false)
	public void RandomlyChangeTheLanguage () throws InterruptedException {
		
		String [] EnglishCitiesNames = {"Dobai","Jeddah","Riyadh"};
		String [] ArabicCitiesNames = {"جدة","دبي"};
		
		int randomArabicCity = rand.nextInt(ArabicCitiesNames.length);
		int randomEnglishCity = rand.nextInt(EnglishCitiesNames.length);
		
		String [] MyWebSites = {"https://global.almosafer.com/en","https://global.almosafer.com/ar"};
		
		int randomIndex = rand.nextInt(MyWebSites.length);
		
		driver.get(MyWebSites[randomIndex]);
		
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
		
		WebElement HotelSearchBar = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		
		if (driver.getCurrentUrl().contains("ar")) {
			
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "ar";
			Assert.assertEquals(ActualLanguage,ExpectedLanguage);
			HotelSearchBar.sendKeys(ArabicCitiesNames[randomArabicCity]);
			
		}else {
			
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "en";
			Assert.assertEquals(ActualLanguage,ExpectedLanguage);
			HotelSearchBar.sendKeys(EnglishCitiesNames[randomEnglishCity]);
		}
		
		Thread.sleep(2000);
		
		WebElement CitiesList = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		CitiesList.findElements(By.tagName("li")).get(0).click();
		
		WebElement SelectNumbersOfVisitor = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		
		Select select = new Select(SelectNumbersOfVisitor);
		
		int RandomVisitorNumber = rand.nextInt(2);
		
		select.selectByIndex(RandomVisitorNumber);
		
		WebElement SearchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchButton.click();
		
		Thread.sleep(35000);
		}
	
	@Test (priority = 9,enabled = false)
	public void CheckThatThePageIsFullyLoaded() {
		
        WebElement SearchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
		
		boolean ActualResult = SearchResult.getText().contains("found") || SearchResult.getText().contains("مكان");
		
		boolean ExpectedResult = true;
		
		Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
	@Test (priority = 10)
	public void CheckTheSortOption() throws InterruptedException {
		
		Thread.sleep(35000);
		
		driver.get("https://global.almosafer.com/ar/hotels/%D8%AC%D8%AF%D8%A9/10-01-2025/11-01-2025/2_adult?sortBy=LOWEST_PRICE");
		
		WebElement LowestPriceButton = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		
		LowestPriceButton.click();
		
		List<WebElement> allPrices = driver.findElements(By.xpath(".__ds__comp.undefined.MuiBox-root.muiltr-lnylpq2"));
		
		System.out.println(allPrices.size());
	}
	}

