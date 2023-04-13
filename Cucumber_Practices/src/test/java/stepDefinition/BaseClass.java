package stepDefinition;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCustomer;
	public static Logger logger;
	public Properties configProp;

//Created for generating random string for unique email
public static String randomestring() {
	String generatedString1 = RandomStringUtils.randomAlphanumeric(5);
	return(generatedString1);
}
}