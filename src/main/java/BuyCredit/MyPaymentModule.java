package BuyCredit;

import java.util.ArrayList;
import java.util.Set;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import MyAction.ActionModule;
import MyVariable.VariableModule;
//import sun.util.resources.cldr.bas.CurrencyNames_bas;

public class MyPaymentModule {

	//VariableModule VariableModule = new VariableModule();
	//ActionModule ActionModule = new ActionModule();
	
	
	public void RechargeCardPaymentAction(WebDriver driver)
	{
		
		ActionModule.writing("**********Recharge Card Pyament***********");
		ActionModule.ExcelFileWriteAction("**********Recharge Card Pyament***********","");
		ActionModule.ConsolPrint("**********Recharge Card Pyament***********","");
		
		String APIUrl = "";
		String splitToken="";

		String option=ActionModule.MysqlConnectionAction("select configurationValue from vbRadiusConfiguration where configurationName='Recharge card auth type'", VariableModule.Remoteconn,",");
		
		for(int m=0;m<=5;m++){
		if(m==0)//Card Block
		{
			VariableModule.totalcases++;
			APIUrl = ActionModule.BaseURL().concat("/Login.do?username="+VariableModule.pin+"&password="+VariableModule.pinPass);
			driver.get(APIUrl);
			
			APIUrl = ActionModule.BaseURL().concat("/rechargecard/RechargeAccount.jsp");
			driver.get(APIUrl);
			
			Double curbalance=ActionModule.getCurrentBalance(VariableModule.pin);
			
			String card=ActionModule.MysqlConnectionAction("select rcCardNo,rcSerialNo from vbRechargeCard where rcBatchID="+VariableModule.YESrcBatchID+" and rcStatus=2 and rcAccountID=-1 and rcIsDeleted=0 limit 1", VariableModule.Remoteconn,",");
	    	String[] cardInfo=ActionModule.TokenizerAction(card);
	    	
	    	if(option.equals("1"))
	    	{
				driver.findElement(By.name("cardserial")).sendKeys(cardInfo[1]);
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
	    	else
	    	{
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
			driver.findElement(By.className("col-xxs-12 col-xs-12 col-sm-6 col-md-6 col-lg-6 text-left infoBox_add").id("submit_card")).click();
	        try {
	            Thread.sleep(2000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
	        splitToken=driver.findElement(By.tagName("body")).getText();
	        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin);
	        
				if(splitToken.indexOf("Invalid Recharge Card") != -1 && +curbalance==+afterrechargebalance){
					//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
					ActionModule.writing("Recharge Card Payment with Block card:"+"Test Successful");
					ActionModule.ExcelFileWriteAction("Recharge Card Payment with Block card", "Test Successful");
					ActionModule.ConsolPrint("Recharge Card Payment with Block card", "Test Successful");
					//ActionModule.showNotification(1,"Warning!!!", "Total Test Cases: \t\t"+VariableModule.totalcases+"\nTotal Failed Cases: \t"+VariableModule.totalfailedcases,VariableModule.trayIcon);
				}
				else
				{
					VariableModule.totalfailedcases++;
					ActionModule.writing("Recharge Card Payment with Block card:"+"Test Failed");
					ActionModule.ExcelFileWriteAction("Recharge Card Payment with Block card", "Test Failed");
					ActionModule.ConsolPrint("Recharge Card Payment with Block card", "Test Failed");
					//ActionModule.showNotification(1,"Warning!!!", "Total Test Cases: \t\t"+VariableModule.totalcases+"\nTotal Failed Cases: \t"+VariableModule.totalfailedcases,VariableModule.trayIcon);
					ActionModule.PlayAlarmAction();
				}
		}
		else if(m==1)//Used Card
		{
			VariableModule.totalcases++;
			
			APIUrl = ActionModule.BaseURL().concat("/Login.do?username="+VariableModule.pin+"&password="+VariableModule.pinPass);
			driver.get(APIUrl);
			
			APIUrl = ActionModule.BaseURL().concat("/rechargecard/RechargeAccount.jsp");
			driver.get(APIUrl);
			
			Double curbalance=ActionModule.getCurrentBalance(VariableModule.pin);
			
			String card=ActionModule.MysqlConnectionAction("select rcCardNo,rcSerialNo from vbRechargeCard where rcBatchID="+VariableModule.YESrcBatchID+" and rcStatus=1 and rcAccountID!=-1 and rcIsDeleted=0 limit 1", VariableModule.Remoteconn,",");
	    	String[] cardInfo=ActionModule.TokenizerAction(card);
	    	if(option.equals("1"))
	    	{
				driver.findElement(By.name("cardserial")).sendKeys(cardInfo[1]);
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
	    	else
	    	{
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
			driver.findElement(By.className("col-xxs-12 col-xs-12 col-sm-6 col-md-6 col-lg-6 text-left infoBox_add").id("submit_card")).click();
	        try {
	            Thread.sleep(2000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
	        splitToken=driver.findElement(By.tagName("body")).getText();
	        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin);
	        
			if(splitToken.indexOf("Invalid Recharge Card") != -1 && +curbalance==+afterrechargebalance){
				//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
				ActionModule.writing("Recharge Card Payment with Used card:"+"Test Successful");
				ActionModule.ExcelFileWriteAction("Recharge Card Payment with Used card", "Test Successful");
				ActionModule.ConsolPrint("Recharge Card Payment with Used card", "Test Successful");

			}
			else
			{
				VariableModule.totalfailedcases++;
				ActionModule.writing("Recharge Card Payment with Used card:"+"Test Failed");
				ActionModule.ExcelFileWriteAction("Recharge Card Payment with Used card", "Test Failed");
				ActionModule.ConsolPrint("Recharge Card Payment with Used card", "Test Failed");
				ActionModule.PlayAlarmAction();
			}
		}
		else if(m==2)//Deleted Card
		{
			
			VariableModule.totalcases++;
			APIUrl = ActionModule.BaseURL().concat("/Login.do?username="+VariableModule.pin+"&password="+VariableModule.pinPass);
			driver.get(APIUrl);
			
			APIUrl = ActionModule.BaseURL().concat("/rechargecard/RechargeAccount.jsp");
			driver.get(APIUrl);
			
			Double curbalance=ActionModule.getCurrentBalance(VariableModule.pin);
			
			String card=ActionModule.MysqlConnectionAction("select rcCardNo,rcSerialNo from vbRechargeCard where rcBatchID="+VariableModule.YESrcBatchID+" and rcStatus=1 and rcAccountID=-1 and rcIsDeleted=1 limit 1", VariableModule.Remoteconn,",");
	    	String[] cardInfo=ActionModule.TokenizerAction(card);
	    	if(option.equals("1"))
	    	{
				driver.findElement(By.name("cardserial")).sendKeys(cardInfo[1]);
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
	    	else
	    	{
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
			driver.findElement(By.className("col-xxs-12 col-xs-12 col-sm-6 col-md-6 col-lg-6 text-left infoBox_add").id("submit_card")).click();
	        try {
	            Thread.sleep(2000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
	        splitToken=driver.findElement(By.tagName("body")).getText();
	        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin);
	        
			if(splitToken.indexOf("Invalid Recharge Card") != -1 && +curbalance==+afterrechargebalance){
				//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
				ActionModule.writing("Recharge Card Payment with Deleted card:"+"Test Successful");
				ActionModule.ExcelFileWriteAction("Recharge Card Payment with Deleted card", "Test Successful");
				ActionModule.ConsolPrint("Recharge Card Payment with Deleted card", "Test Successful");
				//ActionModule.PlayAlarmAction();
			}
			else
			{
				VariableModule.totalfailedcases++;
				ActionModule.writing("Recharge Card Payment with Deleted card:"+"Test Failed");
				ActionModule.ExcelFileWriteAction("Recharge Card Payment with Deleted card", "Test Failed");
				ActionModule.ConsolPrint("Recharge Card Payment with Deleted card", "Test Failed");
				ActionModule.PlayAlarmAction();
			}
		}
		else if(m==3)//Card Active but for Own with reseller pin
		{
			
			VariableModule.totalcases++;
			APIUrl = ActionModule.BaseURL().concat("/Login.do?username="+VariableModule.ResellerPIN+"&password="+VariableModule.pinPass);
			driver.get(APIUrl);
			
			APIUrl = ActionModule.BaseURL().concat("/rechargecard/RechargeAccount.jsp");
			driver.get(APIUrl);
			Double curbalance=ActionModule.getCurrentBalance(VariableModule.ResellerPIN);
			
			String card=ActionModule.MysqlConnectionAction("select rcCardNo,rcSerialNo from vbRechargeCard where rcBatchID="+VariableModule.NOrcBatchID+" and rcStatus=1 and rcAccountID=-1 and rcIsDeleted=0 limit 1", VariableModule.Remoteconn,",");
	    	String[] cardInfo=ActionModule.TokenizerAction(card);
	    	if(option.equals("1"))
	    	{
				driver.findElement(By.name("cardserial")).sendKeys(cardInfo[1]);
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
	    	else
	    	{
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
			driver.findElement(By.className("col-xxs-12 col-xs-12 col-sm-6 col-md-6 col-lg-6 text-left infoBox_add").id("submit_card")).click();
	        try {
	            Thread.sleep(2000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
	        splitToken=driver.findElement(By.tagName("body")).getText();
	        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.ResellerPIN);
			if(splitToken.indexOf("Invalid Recharge Card") != -1 && +curbalance==+afterrechargebalance){
				//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
				ActionModule.writing("Recharge Card Payment with Active Card and Allow All Client: NO with reseller pin:"+"Test Successful");
				ActionModule.ExcelFileWriteAction("Recharge Card Payment with Active Card and Allow All Client: NO with reseller pin:", "Test Successful");
				ActionModule.ConsolPrint("Recharge Card Payment with Active Card and Allow All Client: NO with reseller pin:", "Test Successful");
			}
			else
			{
				VariableModule.totalfailedcases++;
				ActionModule.writing("Recharge Card Payment with Active Card and Allow All Client: NO with reseller pin:"+"Test Failed");
				ActionModule.ExcelFileWriteAction("Recharge Card Payment with Active Card and Allow All Client: NO with reseller pin:", "Test Failed");
				ActionModule.ConsolPrint("Recharge Card Payment with Active Card and Allow All Client: NO with reseller pin:", "Test Failed");
				ActionModule.PlayAlarmAction();
			}
		}
		else if(m==4)//Card Active but for Own with admin pin
		{
			
			VariableModule.totalcases++;
			APIUrl = ActionModule.BaseURL().concat("/Login.do?username="+VariableModule.pin+"&password="+VariableModule.pinPass);
			driver.get(APIUrl);
			
			APIUrl = ActionModule.BaseURL().concat("/rechargecard/RechargeAccount.jsp");
			driver.get(APIUrl);
			
			Double curbalance=ActionModule.getCurrentBalance(VariableModule.pin);
			
			String card=ActionModule.MysqlConnectionAction("select rcCardNo,rcSerialNo from vbRechargeCard where rcBatchID="+VariableModule.NOrcBatchID+" and rcStatus=1 and rcAccountID=-1 and rcIsDeleted=0 limit 1", VariableModule.Remoteconn,",");
	    	String[] cardInfo=ActionModule.TokenizerAction(card);
	    	if(option.equals("1"))
	    	{
				driver.findElement(By.name("cardserial")).sendKeys(cardInfo[1]);
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
	    	else
	    	{
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
			driver.findElement(By.className("col-xxs-12 col-xs-12 col-sm-6 col-md-6 col-lg-6 text-left infoBox_add").id("submit_card")).click();
	        try {
	            Thread.sleep(2000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
	        splitToken=driver.findElement(By.tagName("body")).getText();
	        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin);
	        
			if(splitToken.indexOf("Invalid Recharge Card") != -1 && +curbalance==+afterrechargebalance){
				
				VariableModule.totalfailedcases++;
				//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
				ActionModule.writing("Recharge Card Payment with Active Card and Allow All Client: NO with Admin pin:"+"Test Failed");
				ActionModule.ExcelFileWriteAction("Recharge Card Payment with Active Card and Allow All Client: NO with Admin pin:", "Test Failed");
				ActionModule.ConsolPrint("Recharge Card Payment with Active Card and Allow All Client: NO with Admin pin:", "Test Failed");
				ActionModule.PlayAlarmAction();
			}
			else
			{
				ActionModule.writing("Recharge Card Payment with Active Card and Allow All Client: NO with Admin pin:"+"Test Successful");
				ActionModule.ExcelFileWriteAction("Recharge Card Payment with Active Card and Allow All Client: NO with Admin pin:", "Test Successful");
				ActionModule.ConsolPrint("Recharge Card Payment with Active Card and Allow All Client: NO with Admin pin:", "Test Successful");
				
			}
		}
		else //Card Active but for All with Reseller pin 
		{
			
			VariableModule.totalcases++;
			APIUrl = ActionModule.BaseURL().concat("/Login.do?username="+VariableModule.ResellerPIN+"&password="+VariableModule.pinPass);
			driver.get(APIUrl);
			
			APIUrl = ActionModule.BaseURL().concat("/rechargecard/RechargeAccount.jsp");
			driver.get(APIUrl);
			
			Double curbalance=ActionModule.getCurrentBalance(VariableModule.ResellerPIN);
			
			String card=ActionModule.MysqlConnectionAction("select rcCardNo,rcSerialNo from vbRechargeCard where rcBatchID="+VariableModule.YESrcBatchID+" and rcStatus=1 and rcAccountID=-1 and rcIsDeleted=0 limit 1", VariableModule.Remoteconn,",");
	    	String[] cardInfo=ActionModule.TokenizerAction(card);
	    	if(option.equals("1"))
	    	{
				driver.findElement(By.name("cardserial")).sendKeys(cardInfo[1]);
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
	    	else
	    	{
				driver.findElement(By.name("cardno")).sendKeys(cardInfo[0]);
	    	}
			driver.findElement(By.className("col-xxs-12 col-xs-12 col-sm-6 col-md-6 col-lg-6 text-left infoBox_add").id("submit_card")).click();
	        try {
	            Thread.sleep(2000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
	        splitToken=driver.findElement(By.tagName("body")).getText();
	        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.ResellerPIN);
	        
			if(splitToken.indexOf("Invalid Recharge Card") != -1 && +curbalance==+afterrechargebalance){
				
				VariableModule.totalfailedcases++;
				//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
				ActionModule.writing("Recharge Card Payment with Active Card and Allow All Client: YES with Reseller pin:"+"Test Failed");
				ActionModule.ExcelFileWriteAction("Recharge Card Payment with Active Card and Allow All Client: YES with Reseller pin:", "Test Failed");
				ActionModule.ConsolPrint("Recharge Card Payment with Active Card and Allow All Client: YES with Reseller pin:", "Test Failed");
				ActionModule.PlayAlarmAction();
			}
			else
			{
				ActionModule.writing("Recharge Card Payment with Active Card and Allow All Client: YES with Reseller pin:"+"Test Successful");
				ActionModule.ExcelFileWriteAction("Recharge Card Payment with Active Card and Allow All Client: YES with Reseller pin:", "Test Successful");
				ActionModule.ConsolPrint("Recharge Card Payment with Active Card and Allow All Client: YES with Reseller pin:", "Test Successful");
				
			}
		}
	}
		
		System.out.println("Total Test Cases: "+VariableModule.totalcases);
		System.out.println("Total Failed Test Cases: "+VariableModule.totalfailedcases);
		
	}
	public void BuyCreditAction(WebDriver driver) 
	{
		ActionModule.writing("**********Buy Credit***********");
		ActionModule.ExcelFileWriteAction("**********Buy Credit***********","");
		ActionModule.ConsolPrint("**********Buy Credit***********","");
		String APIUrl = "";
		String splitToken="";
		APIUrl = ActionModule.BaseURL().concat("/Login.do?username="+VariableModule.pin+"&password="+VariableModule.pinPass);
		driver.get(APIUrl);
		
		APIUrl = ActionModule.BaseURL().concat("/rechargecard/RechargeAccount.jsp");
		driver.get(APIUrl);
		for(int i=0;i<=0;i++){
			if(i==0){ //By Recharge Card
				RechargeCardPaymentAction(driver);
			}
			else if(i==1){ //Online Payment
				//OnlinePaymentAction(driver);
			}
			else if(i==2){ //By Credit Card
/*				driver.findElement(By.cssSelector("input[name='paymentOption'][value='4']")).click();
				driver.findElement(By.name("cardNumber")).sendKeys("4111111111111111");
				driver.findElement(By.name("cvv")).sendKeys("123");
				driver.findElement(By.name("month")).sendKeys("12");
				driver.findElement(By.name("year")).sendKeys("2018");
				driver.findElement(By.className(" col-xxs-12 col-xs-12 col-sm-6 col-md-6 col-lg-6 ").id("submit")).click();
				//driver.switchTo().alert().accept();
		        try {
		            Thread.sleep(5000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }*/
			}
			else{ //By PIN
				
				//RechargeByPINAction(driver);
				
			}
		}
	}  // end of OnlinePaymentAction
	
	public void OnlinePaymentAction(WebDriver driver)
	{
		
		ActionModule.writing("**********Online Payment***********");
		ActionModule.ExcelFileWriteAction("**********Online Payment***********","");
		ActionModule.ConsolPrint("**********Online Payment***********","");
		String APIUrl = "";
		String splitToken="";
		String card=ActionModule.MysqlConnectionAction("select cpcgatewaytype from vbPaymentGatewayCredentials where cpcAccountID =-1 and cpcgatewaytype<8 and cpcIsLiveAccount=0 order by cpcgatewaytype", VariableModule.Remoteconn,",");
    	String[] cardInfo=ActionModule.TokenizerAction(card);
		driver.findElement(By.cssSelector("input[name='paymentOption'][value='2']")).click();
    	for(int j=0;j<cardInfo.length;j++){
    		
    		if(cardInfo[j].equals("1")){ //Paypal
    			/*System.out.println("Paypal");
				ActionModule.writing("**********Paypal***********");
    			ActionModule.ExcelFileWriteAction("**********Paypal***********","");
    			ActionModule.ConsolPrint("**********Paypal***********","");
				driver.findElement(By.name("submit")).click();
				//driver.switchTo().alert().accept();
		        try {
		            Thread.sleep(30000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		        driver.switchTo().window(tabs2.get(1));
		        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "" + Keys.SHIFT + "" + Keys.TAB);
		        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "" + Keys.TAB);
		        //driver.findElement(By.name("login_button")).click();

		        driver.findElement(By.id("email")).sendKeys("mninety-buyer@yahoo.com");
		        driver.findElement(By.id("password")).sendKeys("Wq125THuy");
		        driver.findElement(By.id("btnLogin")).click();
		        try {
		            Thread.sleep(15000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        driver.findElement(By.id("confirmButtonTop")).click();
		        try {
		            Thread.sleep(20000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        //driver.switchTo().alert().accept();
		        //driver.close();
		        driver.switchTo().window(tabs2.get(0));*/
    		}
    		else if(cardInfo[j].equals("2")){ //Authorized.net OK
    			
    			VariableModule.totalcases++;
    			
    			ActionModule.writing("**********Authorized.net***********");
    			ActionModule.ExcelFileWriteAction("**********Authorized.net***********","");
    			ActionModule.ConsolPrint("**********Authorized.net***********","");
				
    			Double curbalance=ActionModule.getCurrentBalance(VariableModule.pin);
    			
    			driver.findElement(By.cssSelector("input[name='onlinepaymentOption'][value='2']")).click();
				driver.findElement(By.name("submit")).click();
    			System.out.println("Authorized.net");
				ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		        driver.switchTo().window(tabs2.get(1));
				try {
		            Thread.sleep(10000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
				driver.findElement(By.name("x_card_num")).sendKeys("4111111111111111");
				driver.findElement(By.name("x_exp_date")).sendKeys("1218");
				driver.findElement(By.name("x_card_code")).sendKeys("123");
				driver.findElement(By.id("btnSubmit")).click();
				try {
		            Thread.sleep(10000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
				driver.switchTo().alert().accept();
		        //driver.close();
				try {
		            Thread.sleep(3000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        splitToken=driver.findElement(By.tagName("body")).getText();
		        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin);
		        
				if(splitToken.indexOf("Congratulation") != -1 && +curbalance==+afterrechargebalance){
					//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
					ActionModule.writing("Authorized.net Payment:"+"Test Successful");
					ActionModule.ExcelFileWriteAction("Authorized.net Payment", "Test Successful");
					ActionModule.ConsolPrint("Authorized.net Payment", "Test Successful");
				}
				else {
					VariableModule.totalfailedcases++;
					ActionModule.writing("Authorized.net Payment:"+"Test Failed");
					ActionModule.ExcelFileWriteAction("Authorized.net Payment", "Test Failed");
					ActionModule.ConsolPrint("Authorized.net Payment", "Test Failed");
					ActionModule.PlayAlarmAction();
				}
				driver.switchTo().window(tabs2.get(0));
    		}
    		else if(cardInfo[j].equals("4")){ //Cashu OK. Manual input
    			
    			VariableModule.totalcases++;
    			ActionModule.writing("**********CASHU***********");
    			ActionModule.ExcelFileWriteAction("**********CASHU***********","");
    			ActionModule.ConsolPrint("**********CASHU***********","");
				
    			Double curbalance=ActionModule.getCurrentBalance(VariableModule.pin);
    			
    			driver.findElement(By.cssSelector("input[name='onlinepaymentOption'][value='4']")).click();
				driver.findElement(By.name("submit")).click();
    			System.out.println("CashU");
				ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		        driver.switchTo().window(tabs2.get(1));
				try {
		            Thread.sleep(60000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
				driver.switchTo().alert().accept();
		        //driver.close();
				
				try {
		            Thread.sleep(3000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        splitToken=driver.findElement(By.tagName("body")).getText();
		        
		        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin);
		        
				if(splitToken.indexOf("Congratulation") != -1 && +curbalance==+afterrechargebalance){
					//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
					ActionModule.writing("Cashu Payment:"+"Test Successful");
					ActionModule.ExcelFileWriteAction("Cashu Payment", "Test Successful");
					ActionModule.ConsolPrint("Cashu Payment", "Test Successful");
				}
				else {
					VariableModule.totalfailedcases++;
					ActionModule.writing("Cashu Payment:"+"Test Failed");
					ActionModule.ExcelFileWriteAction("Cashu Payment", "Test Failed");
					ActionModule.ConsolPrint("Cashu Payment", "Test Failed");
					ActionModule.PlayAlarmAction();
				}
				
		        driver.switchTo().window(tabs2.get(0));
    		}
    		else if(cardInfo[j].equals("5")){ //PaymentWall OK. Manual Input: need to click on Buy button only
    			
    			VariableModule.totalcases++;
    			//System.out.println("PaymentWall");
    			ActionModule.writing("**********PaymentWall***********");
    			ActionModule.ExcelFileWriteAction("**********PaymentWall***********","");
    			ActionModule.ConsolPrint("**********PaymentWall***********","");
    			
    			Double curbalance=ActionModule.getCurrentBalance(VariableModule.pin);
    			driver.findElement(By.cssSelector("input[name='onlinepaymentOption'][value='5']")).click();
				driver.findElement(By.name("submit")).click();
		        try {
		            Thread.sleep(30000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
				ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		        driver.switchTo().window(tabs2.get(1));
		        //driver.findElement(By.xpath("//td[#id='ps_psb_wrapper']@a[#id='ps_psb']")).click();
				//driver.findElement(By.className("button buy")).click();
		        driver.findElement(By.id("thankyou_payment_success_link")).click();
		        //driver.close();
				APIUrl = ActionModule.BaseURL().concat("/payment/paymentView.jsp?showMenu=true");
				driver.get(APIUrl);
				driver.findElement(By.name("RECORDS_PER_PAGE")).clear();
				driver.findElement(By.name("RECORDS_PER_PAGE")).sendKeys("5");
				driver.findElement(By.className("mtop10-xxs mtop10-xs  mtop10-md col-xs-offset-2 col-sm-offset-5 col-md-offset-5 col-lg-offset-4 col-xxs-5 col-xs-8 col-sm-6 col-md-5 col-lg-5 pull-auto").cssSelector("input[type='submit']")).click();
				try {
		            Thread.sleep(20000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        splitToken=driver.findElement(By.tagName("body")).getText();
		        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin);
		        
				if(splitToken.indexOf("PaymentWall") != -1 && +curbalance==+afterrechargebalance){
					//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
					ActionModule.writing("PaymentWall Payment:"+"Test Successful");
					ActionModule.ExcelFileWriteAction("PaymentWall Payment", "Test Successful");
					ActionModule.ConsolPrint("PaymentWall Payment", "Test Successful");
				}
				else {
					VariableModule.totalfailedcases++;
					ActionModule.writing("PaymentWall Payment:"+"Test Failed");
					ActionModule.ExcelFileWriteAction("PaymentWall Payment", "Test Failed");
					ActionModule.ConsolPrint("PaymentWall Payment", "Test Failed");
					ActionModule.PlayAlarmAction();
				}

		        driver.switchTo().window(tabs2.get(0));
    		}
    		else if(cardInfo[j].equals("6")){ //Ingenico OK
    			
    			VariableModule.totalcases++;
    			//System.out.println("Ingenico");
    			ActionModule.writing("**********Ingenico***********");
    			ActionModule.ExcelFileWriteAction("**********Ingenico***********","");
    			ActionModule.ConsolPrint("**********Ingenico***********","");
    			
    			Double curbalance=ActionModule.getCurrentBalance(VariableModule.pin);
				driver.findElement(By.cssSelector("input[name='onlinepaymentOption'][value='6']")).click();
				driver.findElement(By.name("submit")).click();
		        try {
		            Thread.sleep(20000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		        driver.switchTo().window(tabs2.get(1));
				driver.findElement(By.name("Ecom_Payment_Card_Name")).sendKeys("Test");
				driver.findElement(By.name("Ecom_Payment_Card_Number")).sendKeys("4111111111111111");
				Select month = new Select(driver.findElement(By.name("Ecom_Payment_Card_ExpDate_Month")));
				month.selectByVisibleText("12");
				Select year = new Select(driver.findElement(By.name("Ecom_Payment_Card_ExpDate_Year")));
				year.selectByVisibleText("2018");
				driver.findElement(By.name("Ecom_Payment_Card_Verification")).sendKeys("123");
				driver.findElement(By.name("payment")).click();

		        try {
		            Thread.sleep(10000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        splitToken=driver.findElement(By.tagName("body")).getText();
		        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin);
		        
				if(splitToken.indexOf("Congratulation") != -1 && +curbalance==+afterrechargebalance){
					//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
					ActionModule.writing("Ingenico Payment:"+"Test Successful");
					ActionModule.ExcelFileWriteAction("Ingenico Payment", "Test Successful");
					ActionModule.ConsolPrint("Ingenico Payment", "Test Successful");
				}
				else {
					VariableModule.totalfailedcases++;
					ActionModule.writing("Ingenico Payment:"+"Test Failed");
					ActionModule.ExcelFileWriteAction("Ingenico Payment", "Test Failed");
					ActionModule.ConsolPrint("Ingenico Payment", "Test Failed");
					ActionModule.PlayAlarmAction();
				}

		        //driver.close();
		        driver.switchTo().window(tabs2.get(0));
    		}
    		else if(cardInfo[j].equals("7")){ //Braintree OK. manual input. Need to put card info.
    			
    			VariableModule.totalcases++;
    			//System.out.println("Braintree");
    			ActionModule.writing("**********Braintree***********");
    			ActionModule.ExcelFileWriteAction("**********Braintree***********","");
    			ActionModule.ConsolPrint("**********Braintree***********","");
    			
    			Double curbalance=ActionModule.getCurrentBalance(VariableModule.pin);
    			driver.findElement(By.cssSelector("input[name='onlinepaymentOption'][value='7']")).click();
				driver.findElement(By.name("submit")).click();
		        try {
		            Thread.sleep(5000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
				ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		        driver.switchTo().window(tabs2.get(1));
//				driver.findElement(By.className("card-field").id("credit-card-number")).sendKeys("4111111111111111");
//				driver.findElement(By.className("expiration%20card-field").id("expiration")).sendKeys("1218");
//				driver.findElement(By.className("cvv%20card-field").id("cvv")).sendKeys("123");
//				driver.findElement(By.className("pay").id("myForm")).click();
				try {
		            Thread.sleep(30000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        splitToken=driver.findElement(By.tagName("body")).getText();
		        Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin);
		        
				if(splitToken.indexOf("Congratulation") != -1 && +curbalance==+afterrechargebalance){
					//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
					ActionModule.writing("Braintree Payment:"+"Test Successful");
					ActionModule.ExcelFileWriteAction("Braintree Payment", "Test Successful");
					ActionModule.ConsolPrint("Braintree Payment", "Test Successful");
				}
				else {
					
					VariableModule.totalfailedcases++;
					ActionModule.writing("Braintree Payment:"+"Test Failed");
					ActionModule.ExcelFileWriteAction("Braintree Payment", "Test Failed");
					ActionModule.ConsolPrint("Braintree Payment", "Test Failed");
					ActionModule.PlayAlarmAction();
				}
		        //driver.close();
		        driver.switchTo().window(tabs2.get(0));
    		}
    		
    		
    	}
	}
	
	public void RechargeByPINAction(WebDriver driver)
	{
		String APIUrl = "";
		String splitToken="";
		ActionModule.writing("**********Recharge By PIN***********");
		ActionModule.ExcelFileWriteAction("**********Recharge By PIN***********","");
		ActionModule.ConsolPrint("**********Recharge By PIN***********","");
		
		String clientID="";
		APIUrl = ActionModule.BaseURL().concat("/Login.do?username="+VariableModule.pin1+"&password="+VariableModule.pinPass);
		driver.get(APIUrl);
		
        try {
            Thread.sleep(5000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
		APIUrl = ActionModule.BaseURL().concat("/rechargecard/RechargeAccount.jsp");
		driver.get(APIUrl);
        try {
            Thread.sleep(5000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
		
        driver.findElement(By.cssSelector("input[name='paymentOption'][value='3']")).click();
		
		for(int i=0;i<8;i++) {

				if(i==0) { //Different Parent
				clientID=VariableModule.ResellerPIN;
				}
				else if(i==1) { //Different Rate Plan
					clientID=VariableModule.pin;
				}
				else if(i==2) { //Deleted PIN
					clientID=VariableModule.DeletedPIN;
				}
				else if(i==3) { //Blocked PIN
					clientID=VariableModule.BlockedPIN;
				}
				else if(i==4) { //Expired PIN
					clientID=VariableModule.ExpiredPIN;
				}
				else if(i==5) { //Successful Case
					clientID=VariableModule.PIN4;
				}
				else if(i==6) { //Zero Balance PIN
					clientID=VariableModule.PIN4;
				}
				else {//Same PIN
					clientID=VariableModule.pin1;
				}
				Double curbalance=ActionModule.getCurrentBalance(clientID);
				
				driver.findElement(By.name("pinNo")).sendKeys(clientID);
				driver.findElement(By.name("password")).sendKeys("1");
				driver.findElement(By.className(" col-xxs-12 col-xs-12 col-sm-6 col-md-6 col-lg-6 ").cssSelector("input[id='submit_card']")).click();
				//driver.findElement(By.name("rechargeByPIN").id("recharge_option_rechargeByPIN").id("submit_card")).click();
		        try {
		            Thread.sleep(5000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        
				//splitToken=driver.findElement(By.className("div_error_message")).getText();
				splitToken=driver.findElement(By.tagName("body")).getText();
				Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin);

				if(i==0) { //Different Parent
					
					VariableModule.totalcases++;
					
					if(splitToken.indexOf("Invalid PIN") != -1 && +curbalance==+afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Trying to recharge with PIN whose Parent is Different:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Trying to recharge with PIN whose Parent is Different", "Test Successful");
						ActionModule.ConsolPrint("Trying to recharge with PIN whose Parent is Different", "Test Successful");
						
/*					    int total=0;
					    int successful=0;
					    ActionModule.showNotification(1,"Warning!!!", "Total Test Cases: \t"+total+"\nTotal Successful Cases: \t"+successful,trayIcon);*/
					    
					    
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Trying to recharge with PIN whose Parent is Different:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Trying to recharge with PIN whose Parent is Different", "Test Failed");
						ActionModule.ConsolPrint("Trying to recharge with PIN whose Parent is Different", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				else if(i==1){ //Different Rate Plan
					VariableModule.totalcases++;
					if(splitToken.indexOf("Invalid PIN") != -1 && +curbalance==+afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Trying to recharge with PIN whose Rate is Different:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Trying to recharge with PIN whose Rate is Different", "Test Successful");
						ActionModule.ConsolPrint("Trying to recharge with PIN whose Rate is Different", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Trying to recharge with PIN whose Rate is Different:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Trying to recharge with PIN whose Rate is Different", "Test Failed");
						ActionModule.ConsolPrint("Trying to recharge with PIN whose Rate is Differentt", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				else if(i==2){ //Deleted PIN
					VariableModule.totalcases++;
					if(splitToken.indexOf("error.CardRecharge.invalidSerialCardNo") != -1 && +curbalance==+afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Trying to recharge with Deleted PIN:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Trying to recharge with Deleted PIN", "Test Successful");
						ActionModule.ConsolPrint("Trying to recharge with Deleted PIN", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Trying to recharge with Deleted PIN:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Trying to recharge with Deleted PIN", "Test Failed");
						ActionModule.ConsolPrint("Trying to recharge with Deleted PIN", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				else if(i==3) { //Blocked PIN
					VariableModule.totalcases++;
					if(splitToken.indexOf("Blocked PIN") != -1 && +curbalance==+afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Trying to recharge with Blocked PIN:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Trying to recharge with Blocked PIN", "Test Successful");
						ActionModule.ConsolPrint("Trying to recharge with Blocked PIN", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Trying to recharge with Blocked PIN:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Trying to recharge with Blocked PIN", "Test Failed");
						ActionModule.ConsolPrint("Trying to recharge with Blocked PIN", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				
				else if(i==4) { //Expired PIN
					VariableModule.totalcases++;
					if(splitToken.indexOf("Account Recharged Successfully.") != -1 && curbalance<afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Trying to recharge with Expired PIN:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Trying to recharge with Expired PIN", "Test Successful");
						ActionModule.ConsolPrint("Trying to recharge with Expired PIN", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Trying to recharge with Expired PIN:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Trying to recharge with Expired PIN", "Test Failed");
						ActionModule.ConsolPrint("Trying to recharge with Expired PIN", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				

				
				else if(i==5) { //Successful Case
					VariableModule.totalcases++;
					if(splitToken.indexOf("Account Recharged Successfully.") != -1 && curbalance<afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Trying to recharge with PIN whose Rate and Parent is Different:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Trying to recharge with PIN whose Rate and Parent is Different", "Test Successful");
						ActionModule.ConsolPrint("Trying to recharge with PIN whose Rate and Parent is Different", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Trying to recharge with PIN whose Rate and Parent is Different:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Trying to recharge with PIN whose Rate and Parent is Different", "Test Failed");
						ActionModule.ConsolPrint("Trying to recharge with PIN whose Rate and Parent is Different", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				
				else if(i==6) { //Zero Balance PIN
					VariableModule.totalcases++;
					if(splitToken.indexOf("Balance Zero or Negative") != -1 && +curbalance==+afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Trying to recharge with PIN whose Balance is Zero:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Trying to recharge with PIN whose Balance is Zero", "Test Successful");
						ActionModule.ConsolPrint("Trying to recharge with PIN whose Balance is Zero", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Trying to recharge with PIN whose Balance is Zero:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Trying to recharge with PIN whose Balance is Zero", "Test Failed");
						ActionModule.ConsolPrint("Trying to recharge with PIN whose Balance is Zero", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				
				else { //Same PIN
					VariableModule.totalcases++;
					if(splitToken.indexOf("Trying to recharge with logged in pin. ignoring it") != -1 && +curbalance==+afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Trying to recharge with logged in pin. ignoring it:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Trying to recharge with logged in pin. ignoring it", "Test Successful");
						ActionModule.ConsolPrint("Trying to recharge with logged in pin. ignoring it", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Trying to recharge with logged in pin. ignoring it:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Trying to recharge with logged in pin. ignoring it", "Test Failed");
						ActionModule.ConsolPrint("Trying to recharge with logged in pin. ignoring it", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				
		}
	}
	
	public void BalanceTransferAction(WebDriver driver) 
	{
		ActionModule.writing("**********Balance Transfer***********");
		ActionModule.ExcelFileWriteAction("**********Balance Transfer***********","");
		ActionModule.ConsolPrint("**********Balance Transfer***********","");
		
		String APIUrl = "";
		String splitToken="";
		String clientID="";
		APIUrl = ActionModule.BaseURL().concat("/Login.do?username="+VariableModule.pin1+"&password="+VariableModule.pinPass);
		driver.get(APIUrl);
		

		for(int i=0;i<8;i++) {
			APIUrl = ActionModule.BaseURL().concat("/rechargecard/balanceTransfer.jsp");
			driver.get(APIUrl);
			
			Double curbalance=ActionModule.getCurrentBalance(VariableModule.pin1);
			
				if(i==0) { //Different Parent
				clientID=VariableModule.ResellerPIN;
				}
				else if(i==1) { //Different Rate Plan
					clientID=VariableModule.DRatewithzeroBPIN;
				}
				else if(i==2) { //Deleted PIN
					clientID=VariableModule.DeletedPIN;
				}
				else if(i==3) { //Blocked PIN
					clientID=VariableModule.BlockedPIN;
				}
				else if(i==4) { //Expired PIN
					clientID=VariableModule.ExpiredPIN;
				}
				else if(i==5) { //Successful Case
					clientID=VariableModule.PIN4;
				}
				else if(i==6) { //Zero Balance PIN
					clientID=VariableModule.PIN4;
					Double curbalance1=curbalance+5.0;				
					driver.findElement(By.name("transferAmount")).sendKeys(Double.toString(curbalance1));
				}
				else {//Same PIN
					clientID=VariableModule.pin1;
				}
				//clientID = JOptionPane.showInputDialog("Target Pin Number");
				driver.findElement(By.name("pinNo")).sendKeys(clientID);
				//String card=ActionModule.MysqlConnectionAction("select clBillingPassword from vbClient where clCustomerID='"+clientID+"'");
		    	//String[] cardInfo=ActionModule.TokenizerAction(card);
				if(i!=6) {
					driver.findElement(By.name("transferAmount")).sendKeys("2");
				}
				driver.findElement(By.name("password")).sendKeys(VariableModule.pinPass);
				driver.findElement(By.className("col-xxs-12 col-xs-12 col-sm-3 col-md-3 col-lg-3 pull-auto").cssSelector("input[value='Transfer ']")).click();
				
		        try {
		            Thread.sleep(5000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        
				//splitToken=driver.findElement(By.className("div_error_message")).getText();
				splitToken=driver.findElement(By.tagName("body")).getText();

				Double afterrechargebalance=ActionModule.getCurrentBalance(VariableModule.pin1);
				System.out.println("CurrentBalance: "+curbalance+"\tAfter Recharge Balance: "+afterrechargebalance);
				if(i==0) { //Different Parent
					VariableModule.totalcases++;
					if(splitToken.indexOf("Target Client Parent is Different") != -1 && +curbalance==+afterrechargebalance){ //splitToken.indexOf("Target Client Parent is Different") != -1 
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Target PIN Parent is Different:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Target PIN Parent is Different", "Test Successful");
						ActionModule.ConsolPrint("Target PIN Parent is Different","Test Successful");
						//System.out.println("Target PIN Parent is Different: "+"Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Target PIN Parent is Different:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Target PIN Parent is Different", "Test Failed");
						ActionModule.ConsolPrint("Target PIN Parent is Different", "Test Failed");
						ActionModule.PlayAlarmAction();
						//System.out.println("Target PIN Parent is Different: "+"Test Failed");
					}
				}
				
				else if(i==1) { //Different Rate Plan
					VariableModule.totalcases++;
					if(splitToken.indexOf("Target Client Rate is Different") != -1 && +curbalance==+afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Target PIN Rate is Different:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Target PIN Rate is Different", "Test Successful");
						ActionModule.ConsolPrint("Target PIN Rate is Different", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Target PIN Rate is Different:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Target PIN Rate is Different", "Test Failed");
						ActionModule.ConsolPrint("Target PIN Rate is Different", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				
				else if(i==2) { //Deleted PIN
					VariableModule.totalcases++;
					if(splitToken.indexOf("Invalid Targeted Client") != -1 && +curbalance==+afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Target PIN is Deleted:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Target PIN is Deleted", "Test Successful");
						ActionModule.ConsolPrint("Target PIN is Deleted", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Target PIN is Deleted:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Target PIN is Deleted", "Test Failed");
						ActionModule.ConsolPrint("Target PIN is Deleted", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				
				else if(i==3) { //Blocked PIN
					VariableModule.totalcases++;
					if(splitToken.indexOf("Balance Transferred Successfully.") != -1 && curbalance>afterrechargebalance){
						VariableModule.totalfailedcases++;
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Target PIN is Blocked:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Target PIN is Blocked", "Test Failed");
						ActionModule.ConsolPrint("Target PIN is Blocked", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
					else
					{
						ActionModule.writing("Target PIN is Blocked:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Target PIN is Blocked", "Test Successful");
						ActionModule.ConsolPrint("Target PIN is Blocked", "Test Successful");
					}
				}
				
				else if(i==4) { //Expired PIN
					VariableModule.totalcases++;
					if(splitToken.indexOf("Expired Targeted Client.Can't transfer") != -1 && +curbalance==+afterrechargebalance){
						VariableModule.totalfailedcases++;
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Target PIN is Expired:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Target PIN is Expired", "Test Successful");
						ActionModule.ConsolPrint("Target PIN is Expired", "Test Successful");
					}
					else
					{
						ActionModule.writing("Target PIN is Expired:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Target PIN is Expired", "Test Failed");
						ActionModule.ConsolPrint("Target PIN is Expired", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				
				else if(i==5) { //Successful Case
					VariableModule.totalcases++;
					if(splitToken.indexOf("Balance Transferred Successfully") != -1 && curbalance>afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Target PIN Rate and Parent is Different:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Target PIN Rate and Parent is Different", "Test Successful");
						ActionModule.ConsolPrint("Target PIN Rate and Parent is Different", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Target PIN Rate and Parent is Different:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Target PIN Rate and Parent is Different", "Test Failed");
						ActionModule.ConsolPrint("Target PIN Rate and Parent is Different", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				
				else if(i==6) { //Zero Balance PIN
					VariableModule.totalcases++;
					if(splitToken.indexOf("Do not have Enough Balance") != -1 && +curbalance==+afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Target PIN has no sufficient balance to transfer:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Target PIN has no sufficient balance to transfer", "Test Successful");
						ActionModule.ConsolPrint("Target PIN has no sufficient balance to transfer", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Target PIN has no sufficient balance to transfer:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Target PIN has no sufficient balance to transfer", "Test Failed");
						ActionModule.ConsolPrint("Target PIN has no sufficient balance to transfer", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
				
				else {
					VariableModule.totalcases++;
					if(splitToken.indexOf("Trying to Transfer balance to own account") != -1 && +curbalance==+afterrechargebalance){
						//System.err.printf("Yes '%s' contains word 'Successfully' %n" , splitToken);
						ActionModule.writing("Trying to Transfer balance to own account:"+"Test Successful");
						ActionModule.ExcelFileWriteAction("Trying to Transfer balance to own account", "Test Successful");
						ActionModule.ConsolPrint("Trying to Transfer balance to own account", "Test Successful");
					}
					else
					{
						VariableModule.totalfailedcases++;
						ActionModule.writing("Trying to Transfer balance to own account:"+"Test Failed");
						ActionModule.ExcelFileWriteAction("Trying to Transfer balance to own account", "Test Failed");
						ActionModule.ConsolPrint("Trying to Transfer balance to own account", "Test Failed");
						ActionModule.PlayAlarmAction();
					}
				}
		}
		System.out.println("Total Test Cases: "+VariableModule.totalcases);
		System.out.println("Total Failed Test Cases: "+VariableModule.totalfailedcases);
	}

}