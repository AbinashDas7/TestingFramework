package stepDefintion;

import config.PageObjectManager;
import config.Setup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import utils.ExtentReport;

public class myLoftStepDefinition {
    private WebDriver driver = Setup.getDriver();
    private PageObjectManager pageObjectManager = new PageObjectManager(driver);

    @Given("user is in myloft page")
    public void user_is_on_the_my_loft_page() {
    	pageObjectManager.getMyLoft().getToMyLoftBrowser();
        ExtentReport.logStep("Navigated to MyLoft Page", true);
    }
    

    @When("User enters valid email address {string} and the password {string}")
    public void user_enters_valid_email_and_password(String email, String password) {
    	pageObjectManager.getMyLoft().addCredentialsForLogin();
        ExtentReport.logStep("User added Email and Password and Login to portal", true);
    }

    @And("User able to click Login")
    public void user_able_to_click_login() {
    	pageObjectManager.getMyLoft().clickIntoLogin();
        ExtentReport.logStep("User clicked on Login button", true);
    }

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() throws InterruptedException {
    	pageObjectManager.getMyLoft().validateUserLoggedIn();
        ExtentReport.logStep("User successfully Logged into the portal", true);
    }
    
    @And("User able to select an Institute {string}")
    public void user_able_to_select_institute(String institute) {
    	String insName = pageObjectManager.getMyLoft().selectInstitute();
        ExtentReport.logStep("User successfully selected the Institute - "+insName, true);
    }

    @And("User able to click on {string} from the left panel")
    public void user_able_to_click_on_left_panel_option(String option) {
    	pageObjectManager.getMyLoft().goToAppSettingsTab();
        ExtentReport.logStep("User successfully clicked on App Settings Tab", true);
    }

    @Then("Verify the number of links available")
    public void verify_the_number_of_links_available() {
    	int totalLinks = pageObjectManager.getMyLoft().checkNumberOfLinksAdded();
        ExtentReport.logStep("User found "+totalLinks+" links available for now", true);
    }

    @When("User click the {string} button")
    public void user_click_add_new_button(String buttonName) {
    	pageObjectManager.getMyLoft().addLinks();
        ExtentReport.logStep("User clicked on add links in Recommended Links section", true);
    }

    @And("User should able to enter the title {string} and URL {string} and save the link")
    public void user_enters_title_url_and_saves(String title, String url) throws InterruptedException {
    	pageObjectManager.getMyLoft().enterDetailsForLinks();
        ExtentReport.logStep("User added Facebook and its url successfully", true);
    }

    @Then("Verify the added link appears at the end of the list")
    public void verify_added_link_appears_at_end() throws InterruptedException {
    	String lastlyAddedLink = pageObjectManager.getMyLoft().updatedLinks();
        ExtentReport.logStep("User found -"+lastlyAddedLink+"- link added in the end", true);
    }

    @When("User delete the newly added link")
    public void user_deletes_newly_added_link() throws InterruptedException {
    	
    	pageObjectManager.getMyLoft().deleteTheNewlyAddedLink();
        ExtentReport.logStep("User click on delete for the newly added link", true);
    }

    @Then("Verify the link is deleted successfully")
    public void verify_link_deleted_successfully() throws InterruptedException {
    	pageObjectManager.getMyLoft().verifyDeletion();
        ExtentReport.logStep("User verified the link is deleted successfully", true);
    }


}
