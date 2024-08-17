package lecture5practice;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Lecture5Class {

	WebDriver driver = new ChromeDriver();
	String webSite1 = "https://magento.softwaretestingboard.com/";
	Random rand = new Random();
	String Password = "P@ssword4ever123456";
	String LogOut = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String PublicEmailAdress = "";
    Scanner scanner = new Scanner(System.in);

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.get(webSite1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	@Test(priority = 1,enabled = true)
	public void createAcoountTest() {
		WebElement CreateAccountLink = driver.findElement(
				By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']"));
		CreateAccountLink.click();
		// =====================================================================================================
		WebElement FirstNameInput = driver.findElement(By.id("firstname"));
		WebElement LastNameInput = driver.findElement(By.id("lastname"));
		WebElement EmailAddressInput = driver.findElement(By.id("email_address"));
		WebElement PasswordInput = driver.findElement(By.id("password"));
		WebElement PasswordConfirmationInput = driver.findElement(By.id("password-confirmation"));
		WebElement CreateAccountButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));

		String[] FirstNamesArray = { "Noor", "Sara", "Rana", "Aseel", "Manar", "Suad" };
		String[] LastNamesArray = { "Jaradat", "Ababneh", "AL-Ali", "zyad", "Alawneh", "Odat" };
		String[] EmailDomainArray = { "@gmail.com", "@outlook.com", "@yahoo.com" };

		int RandomFirstNameIndex = rand.nextInt(FirstNamesArray.length);
		int RandomLasttNameIndex = rand.nextInt(LastNamesArray.length);
		int RandomEmailDomainIndex = rand.nextInt(EmailDomainArray.length);

		String RandomFirstName = FirstNamesArray[RandomFirstNameIndex];
		String RandomLastName = LastNamesArray[RandomLasttNameIndex];
		int RandomNumberforEmail = rand.nextInt(2147483647);
		String RandomEmailDomain = EmailDomainArray[RandomEmailDomainIndex];
		// ===================================================================================================
		FirstNameInput.sendKeys(RandomFirstName);
		LastNameInput.sendKeys(RandomLastName);
		EmailAddressInput.sendKeys(RandomFirstName + RandomLastName + RandomNumberforEmail + RandomEmailDomain);
		PasswordInput.sendKeys(Password);
		PasswordConfirmationInput.sendKeys(Password);
		PublicEmailAdress = RandomFirstName + RandomLastName + RandomNumberforEmail + RandomEmailDomain;
		CreateAccountButton.click();

	}
	// ===================================================================================================
	// ===================================================================================================

	@Test(priority = 2,enabled = true)
	public void LogOutTest() {
		// driver.get(LogOut);
		WebElement arrow = driver.findElement(By.cssSelector("div[class='panel header'] button[type='button']"));
		arrow.click();
		WebElement SignOutButton = driver.findElement(By.cssSelector("div[aria-hidden='false'] li[data-label='or'] a"));
		SignOutButton.click();
		
		WebElement LogOutMessage = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
		String ActualLogOutMessage = LogOutMessage.getText();
		String ExpectedLogOutMessage ="You are signed out";
		Assert.assertEquals(ActualLogOutMessage, ExpectedLogOutMessage);
	}
	// ===================================================================================================
	// ===================================================================================================
	@Test(priority = 3,enabled = true)
	public void SignInTest() {
		WebElement LogInLink = driver.findElement(By.linkText("Sign In"));
		LogInLink.click();
		WebElement EmailforLogIn = driver.findElement(By.id("email"));
		WebElement PasswordforLogIn = driver.findElement(By.id("pass"));
		WebElement SignInButton = driver.findElement(By.id("send2"));
		EmailforLogIn.sendKeys(PublicEmailAdress);
		PasswordforLogIn.sendKeys(Password);
		SignInButton.click();
		
		String SignIntMessage = driver.findElement(By.className("logged-in")).getText();
		Assert.assertEquals(SignIntMessage.contains("Welcome"), true);
	}
	// ===================================================================================================
	// ===================================================================================================
	@Test(priority = 4,enabled = true)
	public void MenSectionTest() {
		WebElement MensSectionClick = driver.findElement(By.cssSelector("a[id='ui-id-5'] span:nth-child(2)"));
		MensSectionClick.click();
		//==================================
		WebElement MenItemsContainer = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));
		List<WebElement> MenItems = MenItemsContainer.findElements(By.tagName("li"));
		MenItems.get(rand.nextInt(MenItems.size())).click();
		//==================================
		WebElement SizesSectionContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> SizesSection = SizesSectionContainer.findElements(By.tagName("div"));

		SizesSection.get(rand.nextInt(SizesSection.size())).click();
		
		//===========================
         WebElement ColorsContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
         List <WebElement> Colors = ColorsContainer.findElements(By.tagName("div"));
         Colors.get(rand.nextInt(Colors.size())).click();
         //===========================
         WebElement Qty = driver.findElement(By.id("qty"));
         Qty.clear();
         int randoo = rand.nextInt(10);
         String randooString = String.valueOf(randoo);
         Qty.sendKeys(randooString);
         //============================
         WebElement AddToCart = driver.findElement(By.cssSelector("#product-addtocart-button"));
         AddToCart.click();
         //============================
         WebElement Message = driver.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
         boolean ActualMessage = Message.getText().contains("You added");
         Assert.assertEquals(ActualMessage, true);
	}
	// ===================================================================================================
	// ===================================================================================================
	@Test (priority = 5,enabled = true)
	public void AddReview() {
		WebElement WomenPageLink = driver.findElement(By.id("ui-id-4"));
		WomenPageLink.click();
		WebElement WomenItemsContainer = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));
		List<WebElement> WomenItems =WomenItemsContainer.findElements(By.tagName("li"));
		int RandomWomenItemNumber =rand.nextInt(WomenItems.size());
		WebElement RandomWomenItem = WomenItems.get(RandomWomenItemNumber);
		RandomWomenItem.click();
		// ==================================
		WebElement WomenSizesContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> WomenSizes = WomenSizesContainer.findElements(By.className("swatch-option"));
		int RandomWomenSizeNumber =rand.nextInt(WomenSizes.size());
		WebElement RandomWomenSize = WomenSizes.get(RandomWomenSizeNumber);
		RandomWomenSize.click();
		// ==================================
		WebElement WomenColorContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> WomenColors = WomenColorContainer.findElements(By.tagName("div"));
		int RandomColorNumber = rand.nextInt(WomenColors.size());
		WebElement TheRandomColor = WomenColors.get(RandomColorNumber);
		TheRandomColor.click();
		// ==================================
		WebElement AddWomenItemToCartButton =driver.findElement(By.id("product-addtocart-button"));
		AddWomenItemToCartButton.click();
		// ==================================
		WebElement WomenItemAddingMessage = driver.findElement(By.className("message-success"));
		boolean ActualWomenItemAddingMessage= WomenItemAddingMessage.getText().contains("You added");
		Assert.assertEquals(ActualWomenItemAddingMessage, true);
		// ==================================
		WebElement ReviewsButton = driver.findElement(By.id("tab-label-reviews-title"));
		ReviewsButton.click();
		
		JavascriptExecutor JS = (JavascriptExecutor)driver;
		JS.executeScript("window.scrollTo(0,1300)" );
		// ==================================
        String starId = "Rating_" + rand.nextInt(5);
        WebElement element = driver.findElement(By.id(starId));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		WebElement NickName = driver.findElement(By.id("nickname_field"));
		WebElement Summary = driver.findElement(By.id("summary_field"));
		WebElement ReviewInput = driver.findElement(By.id("review_field"));
		WebElement SubmitButton = driver.findElement(By.cssSelector("button[class='action submit primary']"));
		NickName.sendKeys("Test");
		Summary.sendKeys("Test");
		ReviewInput.sendKeys("This Is a Test");
		SubmitButton.click();
		WebElement SubmitMessage = driver.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		boolean ActualSubmitMessage= SubmitMessage.getText().contains("You submitted your review");
		Assert.assertEquals(ActualSubmitMessage, true);
	}
	

}
