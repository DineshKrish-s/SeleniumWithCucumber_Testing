package PageFiles;

import org.openqa.selenium.By;

public class CreateAccPageLUMA {

	public By createBtn 	= By.xpath("//button[@title='Create an Account']");
	public By firstNameBox  = By.xpath("//input[@id='firstname']");
	public By lastNameBox   = By.xpath("//input[@id='lastname']");
	public By emailBox      = By.xpath("//input[@name='email']");
	public By passBox		= By.xpath("//input[@name='password']");
	public By repassBox 	= By.xpath("//input[@name='password_confirmation']");
	public By errPass   	= By.xpath("//div[@id='password-error']");
	public By errMail   	= By.xpath("//div[@id='email_address-error']");
	
	//Please enter a valid email address (Ex: johndoe@domain.com).

	//Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.
}
