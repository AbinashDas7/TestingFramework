package pages;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.BaseObject;
import utils.ExcelReader;
import utils.propertiesUtils;

public class myLoft extends BaseObject {
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	 String filePath = "src/main/resources/TestData.xlsx"; // Provide correct file path
	 ExcelReader excelReader = new ExcelReader(filePath); // Initialize ExcelReader

	@FindBy(xpath = "//input[@id='mat-input-0']")
	private WebElement emailField;

	@FindBy(xpath = "//span[@class='mat-button-wrapper']")
	private WebElement nextButton;

	@FindBy(xpath = "//input[@id='mat-input-1']")
	private WebElement passwordField;

	@FindBy(xpath = "//*[contains(text(),'LOGIN')]")
	private WebElement loginButton;

	@FindBy(xpath = "//span[@class='sidebar-logo-text']")
	private WebElement dashBoardLogo;

	@FindBy(xpath = "//button[@class='driver-close-btn']")
	private WebElement closePopUps;

	@FindBy(xpath = "//span[@class='ml-8']")
	private WebElement institute;

	@FindBy(xpath = "//*[@class='mat-focus-indicator mat-menu-trigger mat-icon-button mat-button-base']")
	private WebElement insPath;

	@FindBy(xpath = "//*[@class='mat-form-field-infix ng-tns-c25-13']")
	private WebElement searchIns;

	@FindBy(xpath = "//*[@class='mat-line']")
	private WebElement firstList;

	@FindBy(xpath = "//div[@class = 'fuse-vertical-navigation-item-title']/child::span[contains(text(),'App Settings')]")
	private WebElement appSettingsPath;

	@FindBy(xpath = "//h1[contains(text(),' Recommended Links')]/following-sibling::button/span/mat-icon")
	private WebElement addLinksPath;

	@FindBy(xpath = "//div[@class='mat-form-field-flex ng-tns-c25-18']/div/input")
	private WebElement titlePath;

	@FindBy(xpath = "//div[@class='mat-form-field-flex ng-tns-c25-21']/div/input")
	private WebElement urlPath;

	@FindBy(xpath = "//button[@aria-label='SAVE']")
	private WebElement saveUrlPath;

	@FindBy(xpath = "//span[@class='mat-button-wrapper' and contains(text(),'Yes')]")
	private WebElement confirmYesDelete;

	public myLoft(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void getToMyLoftBrowser() {
		System.out.println("Entering getToMyLoftBrowser()...");
	    driver.get(propertiesUtils.getProperty("url"));
	}


	public void addCredentialsForLogin() {
		System.out.println("Entering addCredentialsForLogin()...");
		waitForElementVisible(emailField, 30);
		String Url = driver.getCurrentUrl();
	    Assert.assertEquals(driver.getCurrentUrl(), propertiesUtils.getProperty("url") + "/user/login", "User is on the correct page");

		addValue(emailField,propertiesUtils.getProperty("email"));
		//check if next button is visible
		isElementEnabled(nextButton);
		clickElement(nextButton);

		waitForElementVisible(passwordField, 30);
		addValue(passwordField,propertiesUtils.getProperty("password"));

	}

	public void clickIntoLogin() {
		System.out.println("Entering clickIntoLogin()...");
		isElementEnabled(loginButton);
		clickElement(loginButton);
	}

	public void validateUserLoggedIn() throws InterruptedException {
		System.out.println("Entering validateUserLoggedIn()...");
		Thread.sleep(6000);
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
	}

	public void handlePopupIfExists() {
		try {
			wait.until(ExpectedConditions.visibilityOf(closePopUps));
			clickElement(closePopUps);
		} catch (TimeoutException | NoSuchElementException e) {
			System.out.println("No popup appeared, continue execution"); 
		}
	}

	public String selectInstitute() {
		System.out.println("Entering selectInstitute()...");
		handlePopupIfExists();
		//check the selected institute
		String instituteName = excelReader.getData(1, 0);
		if(institute.getText().equals(instituteName)) {
			System.out.println("we are selected "+instituteName);
		}else {
			clickElement(insPath);
			addValue(searchIns, instituteName);
			searchIns.sendKeys(Keys.ENTER);
			mouseOverElement(firstList);
		}
		return instituteName;
	}

	public void goToAppSettingsTab() {
		System.out.println("Entering goToAppSettingsTab()...");
		clickElement(appSettingsPath);
	}
	public int checkNumberOfLinksAdded() {
		System.out.println("Entering checkNumberOfLinksAdded()...");
		List<WebElement> links = driver.findElements(By.xpath("//*[@class='py-8 ng-tns-c381-14']/ancestor::div[@class='recommended-link-container ng-tns-c381-14 ng-star-inserted']"));
		int totalLinks = links.size();
		return totalLinks;
	}

	public void addLinks() {
		System.out.println("Entering addLinks()...");
		clickElement(addLinksPath);
		waitForElementVisible(titlePath, 30);
	}

	public void enterDetailsForLinks() throws InterruptedException {
		System.out.println("Entering enterDetailsForLinks()...");
        String title = excelReader.getData(1, 1); 
        String url = excelReader.getData(1, 2);
		addValue(titlePath, title);
		Thread.sleep(2000);
		addValue(urlPath, url);
		waitForElementClickable(saveUrlPath, 30);
		clickElement(saveUrlPath);
	}
	WebElement lastElement;
	int countLinks;
	public String updatedLinks() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Entering updatedLinks()...");
		List<WebElement> links = driver.findElements(By.xpath("//div[@class='recommended-link-content ng-tns-c381-14']/div/span"));
		countLinks = links.size();
		String lastElementTitle = null;
		if (!links.isEmpty()) {
			lastElement = links.get(links.size() - 1);
			lastElementTitle = lastElement.getText();
			System.out.println("Last Name: " + lastElementTitle);
		}
		return lastElementTitle;
	}

	public void deleteTheNewlyAddedLink() throws InterruptedException {
		System.out.println("Entering deleteTheNewlyAddedLink()...");
		
		WebElement lastElementDel = driver.findElement(By.xpath("((//div[@class='recommended-link-content ng-tns-c381-14']/div/span)["+countLinks+"]/parent::div/following-sibling::button)[2]"));	
		waitForElementClickable(lastElementDel,30);
		clickElement(lastElementDel);
		waitForElementClickable(confirmYesDelete, 30);
		confirmYesDelete.click();
	}
	
	public void verifyDeletion() throws InterruptedException {
		System.out.println("Entering verifyDeletion()...");
		waitForElementClickable(lastElement, 30);
		Assert.assertNotEquals(lastElement, "facebook");
		Thread.sleep(4000);
		
		
	}

}
