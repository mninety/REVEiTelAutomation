package Login;

import javax.swing.JLabel;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import static com.codeborne.selenide.Selenide.*;
//import static com.codeborne.selenide.Condition.*;

import MyAction.ActionModule;
import MyVariable.*;

public class LoginModule {
	
	static VariableModule variabledo = new VariableModule();
	static ActionModule actiondo = new ActionModule();
	//private static final Logger log = Logger.getLogger(LoginModule.class);
	//Logger log = Logger.getLogger("LoginModule:");
	
	public static void AdminloginAction(WebDriver driver) 
	{
		driver.get(actiondo.BaseURL() );
		//System.out.println("Test Passed!");
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(variabledo.admin);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(variabledo.adminpass);
		//By.className("loginBox-input-group");
		driver.findElement(By.className("btn btn-success btn-lg")).click();//.click();
		//driver.getClass();

		   
		try {
		    Thread.sleep(15000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		variabledo.actualTitle = driver.getTitle();
		if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle)){
		    System.out.println("Test Failed!");
		    actiondo.writing("Test Failed!");
		}else {
		    System.out.println("Test Passed!");
		}

	}  // end of AdminloginAction
	
	public void PinloginAction(WebDriver driver) 
	{
		driver.get(actiondo.BaseURL() );
		//System.out.println("Test Passed!");
		driver.findElement(By.name("username")).sendKeys(variabledo.pin);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(variabledo.pinPass);
		driver.findElement(By.id("sbmtLogin")).click();
		try {
		    Thread.sleep(5000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		variabledo.actualTitle = driver.getTitle();
		if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle)){
		    System.out.println("Test Failed!");
		    actiondo.writing("Test Failed!");
		}else {
		    System.out.println("Test Passed!");
		}

	}  // end of PinloginAction
	
	public void ResellerloginAction(WebDriver driver) 
	{
		driver.get(actiondo.BaseURL() );
		//System.out.println("Test Passed!");
		driver.findElement(By.name("username")).sendKeys(variabledo.reseller);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(variabledo.adminpass);
		driver.findElement(By.id("sbmtLogin")).click();
		try {
		    Thread.sleep(5000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		variabledo.actualTitle = driver.getTitle();
		if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle)){
		    System.out.println("Test Failed!");
		    actiondo.writing("Test Failed!");
		}else {
		    System.out.println("Test Passed!");
		}

	}  // end of ResellerloginAction

	public void AgentloginAction(WebDriver driver) 
	{
		driver.get(actiondo.BaseURL() );
		//System.out.println("Test Passed!");
		driver.findElement(By.name("username")).sendKeys(variabledo.agent);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(variabledo.adminpass);
		driver.findElement(By.id("sbmtLogin")).click();
		try {
		    Thread.sleep(5000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		variabledo.actualTitle = driver.getTitle();
		if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle)){
		    System.out.println("Test Failed!");
		    actiondo.writing("Test Failed!");
		}else {
		    System.out.println("Test Passed!");
		}

	}  // end of AgentloginAction
	
	
	public void loginCasesAction(WebDriver driver) 
	{
/*        JLabel label2 = new JLabel();
        label2.setText("****************Automation Menu******************");
        label2.setHorizontalTextPosition(JLabel.RIGHT);
        label2.setVerticalTextPosition(JLabel.TOP);
        add(label2);*/
        
		//log.debug("Login");
		actiondo.writing("**********Login Module***********");
	    /*==============Invalid User================*/
		//System.out.println("Test");
	    driver.get(actiondo.BaseURL() );
	    driver.findElement(By.name("username")).sendKeys(variabledo.admin + '2');
	    driver.findElement(By.name("password")).sendKeys(variabledo.adminpass);
	    driver.findElement(By.id("sbmtLogin")).click();
	    variabledo.actualTitle = driver.getTitle();
	    if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle)){
	        System.out.println("Test Passed!");
	    }else {
	        System.out.println("Test Failed");
	        actiondo.writing("Test Failed!");
	    }
	    
	    /*==============Invalid Pass================*/
	    driver.get(actiondo.BaseURL() );
	    driver.findElement(By.name("username")).sendKeys(variabledo.admin);
	    driver.findElement(By.name("password")).sendKeys(variabledo.adminpass + '2');
	    driver.findElement(By.id("sbmtLogin")).click();
	    variabledo.actualTitle = driver.getTitle();
	    if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle)){
	        System.out.println("Test Passed!");
	    }else {
	        System.out.println("Test Failed");
	        actiondo.writing("Test Failed!");
	    }
	    /*==============Invalid User/Pass================*/
	    driver.get(actiondo.BaseURL() );
	    driver.findElement(By.name("username")).sendKeys(variabledo.admin + '2');
	    driver.findElement(By.name("password")).sendKeys(variabledo.adminpass + '2');
	    driver.findElement(By.id("sbmtLogin")).click();
	    variabledo.actualTitle = driver.getTitle();
	    if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle)){
	        System.out.println("Test Passed!");
	    }else {
	        System.out.println("Test Failed");
	        actiondo.writing("Test Failed!");
	    }
	}  //end of loginCasesAction
	
	public void forgotPasswordAction(WebDriver driver) 
	{
	    /*============Forgot Password============*/
		actiondo.writing("**********Forgot Password***********");
	    driver.get(actiondo.BaseURL() );
	    driver.findElement(By.linkText("Forgot Password?")).click();
	    
	    //CurrentURL = driver.getCurrentUrl();
	    //System.out.println(CurrentURL);
	    //APIUrl = baseUrl.concat("/home/mailPassword.jsp");
	    //driver.get(APIUrl);
	    
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    driver.findElement(By.name("username")).sendKeys(variabledo.admin);
	    driver.findElement(By.name("mailAddress")).sendKeys(variabledo.mailTo);
	    driver.findElement(By.id("submitBtn")).click();
	
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Password Reset");
	    
	    variabledo.actualTitle = driver.getTitle();
	    if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle)){
	        System.out.println("Test Failed!");
	        actiondo.writing("Test Failed!");
	    }else {
	        System.out.println("Test Passed");
	    }
	} // end of forgotPasswordAction
}
