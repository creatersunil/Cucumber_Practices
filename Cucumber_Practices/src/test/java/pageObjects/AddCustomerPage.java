package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class AddCustomerPage {
public WebDriver ldriver;

	public AddCustomerPage(WebDriver rdriver)
	{
		
		PageFactory.initElements(rdriver, this);
		ldriver=rdriver;
	}

	By lnkCustomers_menu=By.xpath("//a[@href='#']/child::p[contains(text(),'Customers')]");
	By lnkCustomers_menuitem=By.xpath("//a[@href='/Admin/Customer/List']");
	
	By btnAddnew=By.xpath("//a[@class='btn btn-primary' and @href='/Admin/Customer/Create']");
	
	By txtEmail=By.xpath("//input[@id='Email' and @type='email']");
	By txtPassword=By.xpath("//input[@id='Password' and @type='password']");
	
	By txtCustomerRoles=By.xpath("//input[@aria-describedby='SelectedCustomerRoleIds_taglist']");
	By lstitemAdministrators=By.xpath("//select[@id='SelectedCustomerRoleIds']//option[@value='1']");
	By lstitemForumModerators=By.xpath("//select[@id='SelectedCustomerRoleIds']//option[@value='2']");
	By lstitemRegitered=By.xpath("//select[@id='SelectedCustomerRoleIds']//option[@value='3']");
	By lstitemGuests=By.xpath("//select[@id='SelectedCustomerRoleIds']//option[@value='4']");
	By lstitemVendors=By.xpath("//select[@id='SelectedCustomerRoleIds']//option[@value='5']");
	
	By drpmgrOfVendor=By.xpath("//*[@id='VendorId']");
	By rdMaleGender=By.xpath("//input[@id='Gender_Male']");
	By rdFemaleGender=By.xpath("//input[@id='Gender_Female']");
	
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	
	By txtCompanyName=By.xpath("//input[@id='Company']");
	By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave=By.xpath("//button[@name='save']");
	
	//Action Methods
	
	public String getPageTitle() 
	{
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu()
	{
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	
	public void clickOnCustomersMenuItem()
	{
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAdd() 
	{
		ldriver.findElement(btnAddnew).click();
		
	}
	
	public void setEmail(String email)
	{
		ldriver.findElement(txtEmail).sendKeys(email);
	}

	public void setPassword(String password)
	{
		ldriver.findElement(txtPassword).sendKeys(password);	}

	public void setFirstName(String firstName)
	{
		ldriver.findElement(txtFirstName).sendKeys(firstName);
	}

	public void setTxtLastName(String lastName) 
	{
		ldriver.findElement(txtLastName).sendKeys(lastName);
	
	}
	
	public void setCustomersRole(String role) throws InterruptedException {
		if(!role.equals("Vendors"))
		{
			ldriver.findElement(By.xpath("//*[@id=\"customer-info\"]/div[2]/div[10]/div[2]/div/div[1]/div/div/input"));
		}
		ldriver.findElement(txtCustomerRoles).click();
		
		WebElement listitem;
		
		Thread.sleep(3000);
		if(role.equals("Administrators"))
		{
			listitem=ldriver.findElement(lstitemAdministrators);
		}
		else if(role.equals("Forum Moderators"))
		{
			listitem=ldriver.findElement(lstitemForumModerators);
		}
		else if(role.equals("Registered"))
		{
			listitem=ldriver.findElement(lstitemRegitered);
		}
		else if(role.equals("Guests"))
		{
			listitem=ldriver.findElement(lstitemGuests);
		}
		else if(role.equals("Vendors"))
		{
			listitem=ldriver.findElement(lstitemVendors);
		}
		else
		{
			listitem=ldriver.findElement(lstitemGuests);
		}
		
//		listitem.click();
		
		JavascriptExecutor js= (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", listitem);
	}
	
	public void setManagerOfVendor(String value) 
	{
	Select drop=new Select(ldriver.findElement(drpmgrOfVendor));
	drop.selectByVisibleText(value);
	}
	
	public void setGender(String gender) 
	{
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFemaleGender).click();
		}
		else
		{
			ldriver.findElement(rdMaleGender).click();//default
		}	
	}
	
	public void setDob(String dob)
	{
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String company) 
	{
		ldriver.findElement(txtCompanyName).sendKeys(company);
		
	}
	
	public void setAdminContent(String content) 
	{
		ldriver.findElement(txtAdminContent).sendKeys(content);
	 	
	}
	
	public void clickOnSave() 
	{
		ldriver.findElement(btnSave).click();
		
	}

}

