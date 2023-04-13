package stepDefinition;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass{
	
	@Before
	public void setUp() throws IOException {
		
		//Reading Properties file
		configProp=new Properties();
		FileInputStream configPropFile=new FileInputStream("config.properties");
		configProp.load(configPropFile);
		
		logger=Logger.getLogger("Cucumber_Practices");
		PropertyConfigurator.configure("log4j.properties");
		//logger.setLevel(Level.DEBUG);
		
		String br=configProp.getProperty("browser");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options); 
			
		}
		else if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			driver=new InternetExplorerDriver();
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver=new FirefoxDriver();
		}
		logger.info("************Luanching browser **********");
	}
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		lp=new LoginPage(driver);
		driver.manage().window().maximize();
	   
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
	    driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
	    lp.setUserName(email);
	    lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() {
	   lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
	    
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue(false);
		}
		else {
			Assert.assertEquals(title, driver.getTitle());
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
	    lp.clickLogout();
	    Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
	   driver.quit();
	}

	
	// Customers feature setps Definition.....
	
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
	   addCust=new AddCustomerPage(driver);
	   Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}
	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
	 Thread.sleep(2000);
	  addCust.clickOnCustomersMenu();
	}
	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
	  addCust.clickOnCustomersMenuItem();
	}
	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
	   addCust.clickOnAdd();
	   Thread.sleep(2000);
	}
	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
	   Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}
	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
	  String email = randomestring()+"@gmail.com";
	  //Registered -default
	  //The customer cannot be in both Guests and Registered customer role
	  //Add the customer to Guests or Registered customer role
	  addCust.setCustomersRole("Administrators");
	  Thread.sleep(3000);
	  addCust.setEmail(email);
	  addCust.setPassword("test123");
	  addCust.setManagerOfVendor("Vendor 2");
	  addCust.setGender("Male");
	  addCust.setFirstName("Sunil");
	  addCust.setTxtLastName("Kumar");
	  addCust.setDob("7/05/1885");
	  addCust.setCompanyName("Abcd");
	  addCust.setAdminContent("This is testing.....");
	  
	}
	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		
		addCust.clickOnSave();
		 Thread.sleep(3000);
	}
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
	  
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully."));
	}
	
	//********Steps for searching a customer using email id ************/
	
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
	  
		searchCustomer =new SearchCustomerPage(driver);
		searchCustomer.setEmail("victoria_victoria@nopCommerce.com");
	}
	@When("click on search button")
	public void click_on_search_button() throws InterruptedException {
	  searchCustomer.clickSearch();
	  Thread.sleep(3000);
		
	}
	@Then("User should found Email in the search table")
	public void user_should_found_email_in_the_search_table() {
	boolean status = searchCustomer.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	Assert.assertEquals(true, status);
	}
	
	
	//********Steps for searching a customer using Name ************/
	
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		searchCustomer =new SearchCustomerPage(driver);
		searchCustomer.setFirstName("Victoria");
	}
	@When("Enter customer LastName")
	public void enter_customer_last_name() {
	  searchCustomer.setLastName("Terces");
	}
	@Then("User should found Name in the search table")
	public void user_should_found_name_in_the_search_table() {
		boolean status = searchCustomer.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}
}
