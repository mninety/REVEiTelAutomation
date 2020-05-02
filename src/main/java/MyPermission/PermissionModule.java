package MyPermission;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.security.auth.spi.LoginModule;

import org.apache.bcel.verifier.structurals.ExceptionHandler;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.Select;

import MyVariable.*;
import MyAction.*;
import Login.*;

public class PermissionModule {

	//static VariableModule VariableModule = new VariableModule();
	//static ActionModule ActionModule = new ActionModule();
	//LoginModule logindo = new LoginModule();
	
	static boolean c=true;
	static String AccountID1;
	static int totalcases=0;
	static int totalsuccessfulcases=0;
	public static boolean AdminorPIN(WebDriver driver) throws Exception{
		boolean flag;
		if(driver.findElements(By.id("routeModule")).size()!=0) {
			flag=true;
		}
		else
			flag=false;
		//System.out.println("Function Flag: "+flag);
		return flag;
	}
	
      
	public static void SuccessfulCaseCount() 
	{
		totalsuccessfulcases++;
		System.out.println("Total Successful Cases: "+totalsuccessfulcases);
	}
	public static void ViewAPIAction(int j,int count,String query, WebDriver driver) 
	{
		String APIUrl = "";
		String card = "";
		String getnull="";
		String AccountID = null;
		String[] cardInfo;
		//boolean flag;
		//final boolean c;
		
		if(j==0)
		{
			try {
				c = AdminorPIN(driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//System.out.println("Admin or PIN: "+c);
		if(count==1){
			card=ActionModule.MysqlConnectionAction(query, VariableModule.Remoteconn,",");
			cardInfo=ActionModule.TokenizerAction(card);
			AccountID1=cardInfo[0];
			AccountID=AccountID1;
			//System.out.println("AccountID:"+AccountID);
		}
		else
			AccountID=AccountID1;
		
		if(c==false)
		{
			if(j==17){
				
				VariableModule.totalcases++;
				//System.out.println("AccountID1:"+AccountID);
		        APIUrl = ActionModule.BaseURL().concat(VariableModule.APIArray[j]+"CardBatchID="+AccountID+"&CardID="+AccountID);
		        //System.out.println("API:"+APIUrl);
		        ActionModule.writing("API:" + APIUrl);
		        driver.get(APIUrl);
		        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		        VariableModule.actualTitle = driver.getTitle();
		        if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle)){
		        	SuccessfulCaseCount();
		            //System.out.println("Test Passed with redirect to login page");
		            ActionModule.writing("Test Passed with redirect to login page");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with redirect to login page");
		        } else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle1)){
		        	SuccessfulCaseCount();
		            //System.out.println("Test Passed with Failure Message");
		            ActionModule.writing("Test Passed with Failure Message");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with Failure Message");
		        }else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle2)){
		        	SuccessfulCaseCount();
		            //System.out.println("Test Failed with Exception");
		            ActionModule.writing("Test Passed with Exception");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with Exception");
		        }else {
		            //System.out.println("Test Failed:" + j + " " + APIUrl);
		            ActionModule.writing("Test Failed: "+APIUrl);
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed");
		        	ActionModule.PlayAlarmAction();
		        	VariableModule.totalfailedcases++;
		        }
			}
		        else if(j>=62 && j<=71){
		        	
		        	VariableModule.totalcases++;
					//System.out.println("AccountID1:"+AccountID);
			        APIUrl = ActionModule.BaseURL().concat(VariableModule.APIArray[j]);
			        //System.out.println("API:"+APIUrl);
			        driver.get(APIUrl);
			        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
			        VariableModule.actualTitle = driver.getTitle();
			        if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle)){
			        	SuccessfulCaseCount();
			            //System.out.println("Test Passed with redirect to login page");
			            ActionModule.writing("Test Passed with redirect to login page");
			            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with redirect to login page");
			        } else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle1)){
			        	SuccessfulCaseCount();
			            //System.out.println("Test Passed with Failure Message");
			            ActionModule.writing("Test Passed with Failure Message");
			            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with Failure Message");
			        }else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle2)){
			        	SuccessfulCaseCount();
			            //System.out.println("Test Passed with Exception");
			            ActionModule.writing("Test Passed with Exception");
			            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with Exception");
			        }else {
			            //System.out.println("Test Failed:" + j + " " + APIUrl);
			            ActionModule.writing("Test Failed: "+APIUrl);
			            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed");
			        	ActionModule.PlayAlarmAction();
			        	VariableModule.totalfailedcases++;
			        }
			}
			else{
				VariableModule.totalcases++;
				//System.out.println("AccountID1:"+AccountID);
		        APIUrl = ActionModule.BaseURL().concat(VariableModule.APIArray[j]+AccountID);
		        //System.out.println("API:"+APIUrl);
		        driver.get(APIUrl);
		        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		        VariableModule.actualTitle = driver.getTitle();
		        if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle)){
		        	SuccessfulCaseCount();
		            //System.out.println("Test Passed with redirect to login page");
		            ActionModule.writing("Test Passed with redirect to login page");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with redirect to login page");
		        } else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle1)){
		        	SuccessfulCaseCount();
		            //System.out.println("Test Passed with Failure Message");
		            ActionModule.writing("Test Passed with Failure Message");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with Failure Message");
		        }else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle2)){
		        	SuccessfulCaseCount();
		            //System.out.println("Test Passed with Exception");
		            ActionModule.writing("Test Passed with Exception");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with Exception");
		        }else {
		            //System.out.println("Test Failed:" + j + " " + APIUrl);
		            ActionModule.writing("Test Failed: "+APIUrl);
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed");
		        	ActionModule.PlayAlarmAction();
		        	VariableModule.totalfailedcases++;
		        }
			}
		}
		else {
			if(j==17){
				
				VariableModule.totalcases++;
				//System.out.println("AccountID1:"+AccountID);
		        APIUrl = ActionModule.BaseURL().concat(VariableModule.APIArray[j]+"CardBatchID="+AccountID+"&CardID="+AccountID);
		        //System.out.println("API:"+APIUrl);
		        ActionModule.writing("API:" + APIUrl);
		        driver.get(APIUrl);
		        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		        
		        VariableModule.actualTitle = driver.getTitle();
		        if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle)){
		            //System.out.println("Test Passed with redirect to login page");
		            ActionModule.writing("Test Failed with redirect to login page");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with redirect to login page");
		        	ActionModule.PlayAlarmAction();
		        	VariableModule.totalfailedcases++;
		        	
		        } else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle1)){
		            //System.out.println("Test Passed with Failure Message");
		            ActionModule.writing("Test Failed with Failure Message");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Failure Message");
		        	ActionModule.PlayAlarmAction();
		        	VariableModule.totalfailedcases++;
		        	
		        }else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle2)){
		            //System.out.println("Test Failed with Exception");
		            ActionModule.writing("Test Failed with Exception");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
		        	ActionModule.PlayAlarmAction();
		        	VariableModule.totalfailedcases++;
		        	
		        }else {
		        	SuccessfulCaseCount();
		            ActionModule.writing("Test Successful"+APIUrl);
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Successful");
		            
					getnull=driver.findElement(By.tagName("body")).getText();
					Scanner scanner = new Scanner(getnull);
					while (scanner.hasNextLine()) {
					  //String line = scanner.nextLine();
					  Scanner line = new Scanner(scanner.nextLine());
					  // process the line
				        while (line.hasNext()) {
				            String word = line.next();
				            //System.out.println(s);
							if(word.indexOf("null") != -1){
								//System.out.println("Yes  contains word 'null' %n");
								ActionModule.writing(APIUrl+": null found");
								ActionModule.ExcelFileWriteAction(APIUrl,"null found");
					        	ActionModule.PlayAlarmAction();
					        	
							}
				        }

					}
					scanner.close();
		        }
			}
		        else if(j>=62 && j<=71){
		        	
		        	VariableModule.totalcases++;
					//System.out.println("AccountID1:"+AccountID);
			        APIUrl = ActionModule.BaseURL().concat(VariableModule.APIArray[j]);
			        //System.out.println("API:"+APIUrl);
			        driver.get(APIUrl);
			        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
			        
			        VariableModule.actualTitle = driver.getTitle();
			        if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle)){
			            //System.out.println("Test Passed with redirect to login page");
			            ActionModule.writing("Test Failed with redirect to login page");
			            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with redirect to login page");
			        	ActionModule.PlayAlarmAction();
			        	VariableModule.totalfailedcases++;
			        	
			        } else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle1)){
			            //System.out.println("Test Passed with Failure Message");
			            ActionModule.writing("Test Failed with Failure Message");
			            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Failure Message");
			        	ActionModule.PlayAlarmAction();
			        	VariableModule.totalfailedcases++;
			        	
			        }else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle2)){
			            //System.out.println("Test Passed with Exception");
			            ActionModule.writing("Test Failed with Exception");
			            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	ActionModule.PlayAlarmAction();
			        	VariableModule.totalfailedcases++;
			        	
			        }else {
			        	SuccessfulCaseCount();
			            ActionModule.writing("Test Successful"+APIUrl);
			            ActionModule.ExcelFileWriteAction(APIUrl, "Test Successful");
			            
						getnull=driver.findElement(By.tagName("body")).getText();
						Scanner scanner = new Scanner(getnull);
						while (scanner.hasNextLine()) {
						  //String line = scanner.nextLine();
						  Scanner line = new Scanner(scanner.nextLine());
						  // process the line
					        while (line.hasNext()) {
					            String word = line.next();
					            //System.out.println(s);
								if(word.indexOf("null") != -1){
									//System.out.println("Yes  contains word 'null' %n");
									ActionModule.writing(APIUrl+": null found");
									ActionModule.ExcelFileWriteAction(APIUrl,"null found");
						        	ActionModule.PlayAlarmAction();
						        	
								}
					        }

						}
						scanner.close();
			        }
			}
			else{
				
				VariableModule.totalcases++;
				//System.out.println("AccountID1:"+AccountID);
		        APIUrl = ActionModule.BaseURL().concat(VariableModule.APIArray[j]+AccountID);
		        //System.out.println("API:"+APIUrl);
		        driver.get(APIUrl);
		        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		        
		        VariableModule.actualTitle = driver.getTitle();
		        if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle)){
		            //System.out.println("Test Passed with redirect to login page");
		            ActionModule.writing("Test Failed with redirect to login page");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with redirect to login page");
		        	ActionModule.PlayAlarmAction();
		        	VariableModule.totalfailedcases++;
		        	
		        } else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle1)){
		            //System.out.println("Test Passed with Failure Message");
		            ActionModule.writing("Test Failed with Failure Message");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Failure Message");
		        	ActionModule.PlayAlarmAction();
		        	VariableModule.totalfailedcases++;
		        	
		        }else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle2)){
		            //System.out.println("Test Passed with Exception");
		            ActionModule.writing("Test Failed with Exception");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
		        	ActionModule.PlayAlarmAction();
		        	VariableModule.totalfailedcases++;
		        	
		        }else {
		        	SuccessfulCaseCount();
		            ActionModule.writing("Test Successful"+APIUrl);
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Successful");
		            
					getnull=driver.findElement(By.tagName("body")).getText();
					Scanner scanner = new Scanner(getnull);
					while (scanner.hasNextLine()) {
					  //String line = scanner.nextLine();
					  Scanner line = new Scanner(scanner.nextLine());
					  // process the line
				        while (line.hasNext()) {
				            String word = line.next();
				            //System.out.println(s);
							if(word.indexOf("null") != -1){
								//System.out.println("Yes  contains word 'null' %n");
								ActionModule.writing(APIUrl+": null found");
								ActionModule.ExcelFileWriteAction(APIUrl,"null found");
					        	ActionModule.PlayAlarmAction();
					        	
							}
				        }

					}
					scanner.close();
		        }
			}
		}

   }
	
	public static void DeleteAPIAction(int j,String query, WebDriver driver) 
	{
	
		String APIUrl = "";
		String card = "";
		String getnull="";
		String AccountID="";
		//boolean c=false;
		if(j==0)
		{
			try {
				c = AdminorPIN(driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("Admin or PIN: "+c);

    	if(c==false)
    	{
    		VariableModule.totalcases++;
    		String[] cardInfo;
    		
			card=ActionModule.MysqlConnectionAction(query, VariableModule.Remoteconn,",");
			cardInfo=ActionModule.TokenizerAction(card);
			AccountID=cardInfo[0];

		
        APIUrl = ActionModule.BaseURL().concat(VariableModule.APIArray[j]+AccountID);
        System.out.println("API:"+APIUrl);
        driver.get(APIUrl);
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		card=ActionModule.MysqlConnectionAction(query, VariableModule.Remoteconn,",");
    	cardInfo=ActionModule.TokenizerAction(card);
    	System.out.println("Deleted ID:"+cardInfo[0]);
	    	if(cardInfo[0]!=""){
		        VariableModule.actualTitle = driver.getTitle();
		        if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle)){
		        	//SuccessfulCaseCount();
		            System.out.println("Test Passed with redirect to login page");
		            ActionModule.writing("Test Passed with redirect to login page");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with redirect to login page");
		        } else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle1)){
		        	//SuccessfulCaseCount();
		            System.out.println("Test Passed with Failure Message");
		            ActionModule.writing("Test Passed with Failure Message");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with Failure Message");
		        }else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle2)){
		        	//SuccessfulCaseCount();
		            System.out.println("Test Passed with Exception");
		            ActionModule.writing("Test Passed with Exception");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Passed with Exception");
		        }
	    	}
	    	else {
	
		            ActionModule.writing("Test Failed: "+APIUrl);
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed");
		        	ActionModule.PlayAlarmAction();
		        	VariableModule.totalfailedcases++;
	        
	        }
    	}
    	else {
    		
    		VariableModule.totalcases++;
    		//System.out.println("Delete Action API Call:");
    		String[] cardInfo;
    		
			card=ActionModule.MysqlConnectionAction(query, VariableModule.Remoteconn,",");
			cardInfo=ActionModule.TokenizerAction(card);
			AccountID=cardInfo[0];

		
        APIUrl = ActionModule.BaseURL().concat(VariableModule.APIArray[j]+AccountID);
        System.out.println("API:"+APIUrl);
        //driver.get(APIUrl); // delete action stop intentionally for admin
        //driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		card=ActionModule.MysqlConnectionAction(query, VariableModule.Remoteconn,",");
    	cardInfo=ActionModule.TokenizerAction(card);
    	System.out.println("Deleted ID:"+cardInfo[0]);
	    	if(cardInfo[0]!=""){
		        VariableModule.actualTitle = driver.getTitle();
		        if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle)){
		        	//SuccessfulCaseCount();
		            System.out.println("Test Failed with redirect to login page");
		            ActionModule.writing("Test Failed with redirect to login page");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with redirect to login page");
		        } else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle1)){
		        	//SuccessfulCaseCount();
		            System.out.println("Test Failed with Failure Message");
		            ActionModule.writing("Test Failed with Failure Message");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Failure Message");
		        }else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle2)){
		        	//SuccessfulCaseCount();
		            System.out.println("Test Failed with Exception");
		            ActionModule.writing("Test Failed with Exception");
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
		        }
	    	}
	    	else {
	    			SuccessfulCaseCount();
		            ActionModule.writing("Test Successful: "+APIUrl);
		            ActionModule.ExcelFileWriteAction(APIUrl, "Test Successful");
		        	ActionModule.PlayAlarmAction();
		        	VariableModule.totalfailedcases++;
	        
	        }
    	}
    	
	}
	
	public static void PermissionAPIAction(WebDriver driver, String[] ExeArray) 
	{
/*        String APIUrl1 = ActionModule.BaseURL().concat("/Login.do?username=1234529&password=1");
        driver.get(APIUrl1);*/
	    /*============API Permission Test============*/
        ActionModule.writing("============API Permission Test============");
        ActionModule.ExcelFileWriteAction("============API Permission Test============","");
		//String APIUrl = "";
		//String card = "";
		//String AccountID="";
		//String[] cardInfo;
        
		String APIUrl = "";
		//int i=0;
		String Query1="select * from auconfigvar where cvID="+ExeArray[9];
		String executiondata1=ActionModule.MysqlConnectionAction(Query1,VariableModule.Ownconn,",");
		executiondata1=executiondata1.substring(0, executiondata1.length()-1);
		String[] confArray = executiondata1.split(",");
		LoginwithAPI(VariableModule.driver,confArray);
		
		String jspQuery="select jspLink from aujsplink where jspBatchID="+ExeArray[10];
		String jspdata=ActionModule.MysqlConnectionAction(jspQuery,VariableModule.Ownconn,",");
		//jspdata=jspdata.substring(0, jspdata.length()-1);
		String[] jspArray = jspdata.split("\n");
		
        int i=33;
		int countTrace;
	    try {
			for(;i<VariableModule.APIArray.length;i++){
				
				if(i<5){
					if(i==0){
						countTrace=1;
					}
					else{
						countTrace=2;
					}
			    	if(i<4){

			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[0]+VariableModule.clCustomerID,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[0]+VariableModule.clCustomerID,driver);
						

					}
			    
				}//end of client
				
				else if(i>=5 && i<7){

			    	if(i==5){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[1]+VariableModule.gtAccountID,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[1]+VariableModule.gtAccountID,driver);
						

					}
			    
				}//end of gateway
				
				else if(i>=7 && i<12){
					if(i==7){
						countTrace=1;
					}
					else{
						countTrace=2;
					}
			    	if(i>=7 && i<11){

			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[2]+VariableModule.pbName,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[2]+VariableModule.pbName,driver);
						

					}
			    
				}//end of pin batch
				
				else if(i>=12 && i<14){
					countTrace=1;
			    	if(i==12){

			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[3]+VariableModule.clCustomerID,driver);
			    		
					}
					else {

						ViewAPIAction(i,countTrace,VariableModule.SQLArray[3]+VariableModule.clCustomerID,driver);
						

					}
			    
				}//end of client balance
				
				else if(i>=14 && i<19){
					if(i==14){
						countTrace=1;
					}
					else{
						countTrace=2;
					}
			    	if(i>=14 && i<17){

			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[4]+VariableModule.rcbName,driver);
			    		
					}
			    	else if(i>=17 && i<18){
			    		countTrace=2;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[4]+VariableModule.rcbName,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[4]+VariableModule.rcbName,driver);
						

					}
			    
				}//end of recharge card
				
				else if(i>=19 && i<22){
					if(i==19){
						countTrace=1;
					}
					else{
						countTrace=2;
					}
			    	if(i>=19 && i<21){

			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[5]+VariableModule.rpName,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[5]+VariableModule.rpName,driver);
						

					}
			    
				}//end of rate plan
				
				else if(i==22){

						DeleteAPIAction(i,VariableModule.SQLArray[6]+VariableModule.drpRPID,driver);
					
				}//end of rate plan details
				
				else if(i>=23 && i<25){

			    	if(i==23){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[7]+VariableModule.dtDestCode,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[7]+VariableModule.dtDestCode,driver);
						

					}
			    
				}//end of call destination
				
				
				else if(i>=25 && i<27){

			    	if(i==25){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[8]+VariableModule.dccNumber,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[8]+VariableModule.dccNumber,driver);
						

					}
			    
				}//end of calling card DID
				
				else if(i>=27 && i<29){

			    	if(i==27){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[9]+VariableModule.ogNO,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[9]+VariableModule.ogNO,driver);
						

					}
			    
				}//end of Outgoing DID
				
				else if(i>=29 && i<31){

			    	if(i==29){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[10]+VariableModule.didNO,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[10]+VariableModule.didNO,driver);
						

					}
			    
				}//end of Extension DID
				
				
				else if(i>=31 && i<36){
					if(i==31){
						countTrace=1;
					}
					else{
						countTrace=2;
					}
			    	if(i>=31 && i<35){

			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[11]+VariableModule.csName,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[11]+VariableModule.csName,driver);
						

					}
			    
				}//end of CallShop
				
				
				else if(i>=36 && i<38){

			    	if(i==36){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[12]+VariableModule.rtID,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[12]+VariableModule.rtID,driver);
						

					}
			    
				}//end of Route
				
				else if(i>=38 && i<40){

			    	if(i==38){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[13]+VariableModule.raLogin,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[13]+VariableModule.raLogin,driver);
						

					}
			    
				}//end of MTU Agent
				
			
				else if(i>=40 && i<45){

					if(i==40){
						countTrace=1;
					}
					else{
						countTrace=2;
					}
			    	if(i>=40 && i<44){

			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[14]+VariableModule.toprpName,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[14]+VariableModule.toprpName,driver);
						

					}
			    	
			    
				}//end of MTU Rate Plan
				
				
				else if(i>=45 && i<47){
					
			    	if(i==45){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[15],driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[15],driver);
						

					}
			    
				}//end of MTU Route
				
				else if(i>=47 && i<49){
					
			    	if(i==47){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[16]+VariableModule.usUsername,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[16]+VariableModule.usUsername,driver);
						

					}
			    
				}//end of User
				
				
				else if(i>=49 && i<51){
					
			    	if(i==49){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[17]+VariableModule.rlRoleName,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[17]+VariableModule.rlRoleName,driver);
						

					}
			    
				}//end of Role
				
				else if(i>=51 && i<54){
					if(i==51){
						countTrace=1;
					}
					else{
						countTrace=2;
					}
			    	if(i>=51 && i<53){
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[18]+VariableModule.pkgName,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[18]+VariableModule.pkgName,driver);
						

					}
			    
				}//end of Package
				
				else if(i>=54 && i<56){
					
			    	if(i==54){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[19]+VariableModule.languageName,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[19]+VariableModule.languageName,driver);
						

					}
			    
				}//end of Language
				
				else if(i>=56 && i<58){
					
			    	if(i==56){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[20],driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[20],driver);
						

					}
			    
				}//end of Translation
				
				else if(i>=58 && i<60){
					
			    	if(i==58){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[21]+VariableModule.roName,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[21]+VariableModule.roName,driver);
						

					}
			    
				}//end of Referral
				
				else if(i>=60 && i<62){
					
			    	if(i==60){
			    		countTrace=1;
			    		ViewAPIAction(i,countTrace,VariableModule.SQLArray[22]+VariableModule.poName,driver);
			    		
					}
					else {
						
						DeleteAPIAction(i,VariableModule.SQLArray[22]+VariableModule.poName,driver);
						

					}
			    
				}//end of Promotion
				
				
				else {

					countTrace=2;

					ViewAPIAction(i,countTrace,"",driver);
					
			
				}//end of MTU Destination	

			}  //end of for loop
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    totalcases=i;
	    ActionModule.TestSummaryCreator("Permission API", totalcases, totalsuccessfulcases);
	    totalcases=0;
	    totalsuccessfulcases=0;
	}

	public static void LoginwithAPI(WebDriver driver, String[] confArray)
	{
		String APIUrl="";
		APIUrl = confArray[6].concat("/Login.do?username="+confArray[12]+"&password="+confArray[13]);
		System.out.println("Login with API: "+APIUrl);
		driver.get(APIUrl);
	}
	
	public static void JSPTraverseAdminAction(WebDriver driver, String[] ExeplanArray) 
	{
		/*============JSPTraverseAdminAction============*/
		String getnull="";
		ActionModule.writing("**********JSP Traversal for Admin***********");
		ActionModule.ExcelFileWriteAction("**********JSP Traversal for Admin***********","");
		ActionModule.ConsolPrint("**********JSP Traversal for Admin***********","");
		
		String APIUrl = "";
		int i=0;
		String Query1="select * from auconfigvar where cvID="+ExeplanArray[9];
		String executiondata1=ActionModule.MysqlConnectionAction(Query1,VariableModule.Ownconn,",");
		executiondata1=executiondata1.substring(0, executiondata1.length()-1);
		String[] confArray = executiondata1.split(",");
		LoginwithAPI(VariableModule.driver,confArray);
		
		String jspQuery="select jspLink from aujsplink where jspBatchID="+ExeplanArray[10];
		String jspdata=ActionModule.MysqlConnectionAction(jspQuery,VariableModule.Ownconn,",");
		//jspdata=jspdata.substring(0, jspdata.length()-1);
		String[] jspArray = jspdata.split("\n");
		
		

		
	    try {
			for(;i<jspArray.length;i++){ //VariableModule.JSPTraverseAdminArray.length
				APIUrl="";
				VariableModule.totalcases++;
			    APIUrl = confArray[6].concat(jspArray[i]);
			    System.out.println("URL: "+APIUrl);
			    driver.get(APIUrl);

/*	        try {
			        Thread.sleep(2000);                 //1000 milliseconds is one second.
			    } catch(InterruptedException ex) {
			        Thread.currentThread().interrupt();
			    }*/
			    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			    
			    try {
					VariableModule.actualTitle = driver.getTitle();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle)){
			        //System.out.println("Test Failed with redirect to login page:"+APIUrl);
			    	//ActionModulon(ExeplanArray,"Test Failed with redirect to login page","1");
			        ActionModule.writing("Test Failed with redirect to login page:"+APIUrl);
			        ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with redirect to login page");
			        ActionModule.ConsolPrint(APIUrl, "Test Failed with redirect to login page");
			    	ActionModule.PlayAlarmAction();
			    	VariableModule.totalfailedcases++;
			    	
			        
			    } else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle1)){
			        //System.out.println("Test Failed with Failure Message:"+APIUrl);
			    	//ActionModulon(ExeplanArray,"Test Failed with Failure Message","","Failed");
			        ActionModule.writing("Test Failed with Failure Message:"+APIUrl);
			        ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Failure Message");
			        ActionModule.ConsolPrint(APIUrl, "Test Failed with Failure Message");
			    	ActionModule.PlayAlarmAction();
			    	VariableModule.totalfailedcases++;
			    	
			        
			    }else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle2)){
			        //System.out.println("Test Failed with Exception:"+APIUrl);
			    	//ActionModulon(ExeplanArray,"Test Failed with Exception","","Failed");
			        ActionModule.writing("Test Failed with Exception:"+APIUrl);
			        ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        ActionModule.ConsolPrint(APIUrl, "Test Failed with Exception");
			    	ActionModule.PlayAlarmAction();
			    	VariableModule.totalfailedcases++;
			    	
			    }
			    else
			    {
			    	totalsuccessfulcases++;
			    	//ActionModulon(ExeplanArray,"Test Successful","","Passed");
			        ActionModule.writing("Test Successful"+APIUrl);
			        ActionModule.ConsolPrint("Test Successful","");
			        ActionModule.ExcelFileWriteAction(APIUrl, "Test Successful");
			        
/*					getnull=driver.findElement(By.tagName("body")).getText();
					Scanner scanner = new Scanner(getnull);
					while (scanner.hasNextLine()) {
					  //String line = scanner.nextLine();
					  Scanner line = new Scanner(scanner.nextLine());
					  // process the line
				        while (line.hasNext()) {
				            String word = line.next();
				            //System.out.println(s);
							if(word.indexOf("null") != -1){
								//System.out.println("Yes  contains word 'null' %n");
								ActionModule.writing(APIUrl+": null found");
								ActionModule.ExcelFileWriteAction(APIUrl,"null found");
								ActionModule.ConsolPrint(APIUrl,"null found");
					        	ActionModule.PlayAlarmAction();
					        	
								
							}
				        }

					}
					scanner.close();*/

			    }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    totalcases=i;
	    ActionModule.TestSummaryCreator("JSP Travarsal", totalcases, totalsuccessfulcases);
	    totalcases=0;
	    totalsuccessfulcases=0;
	    //PermissionAPIAction(driver);
	}
	
	
	public void JSPTraverseARResellerAction(WebDriver driver) 
	{
	    /*============API Permission Test============*/
		String APIUrl = "";
	    for(int i=0;i<VariableModule.JSPTraverseARResellerArray.length;i++){
	    	
	    	
	        APIUrl = ActionModule.BaseURL().concat(VariableModule.JSPTraverseARResellerArray[i]);
	        driver.get(APIUrl);
	        try {
	            Thread.sleep(3000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }

	    }
	}
	
	
	public void JSPTraverseDRResellerAction(WebDriver driver) 
	{
	    /*============API Permission Test============*/
		String APIUrl = "";
	    for(int i=0;i<VariableModule.JSPTraverseDRResellerArray.length;i++){
	    	
	    	
	        APIUrl = ActionModule.BaseURL().concat(VariableModule.JSPTraverseDRResellerArray[i]);
	        driver.get(APIUrl);
	        try {
	            Thread.sleep(3000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }

	    }
	}
	
	
	public void JSPTraverseAgentAction(WebDriver driver) 
	{
	    /*============API Permission Test============*/
		String APIUrl = "";
	    for(int i=0;i<VariableModule.JSPTraverseAgentArray.length;i++){
	    	
	    	
	        APIUrl = ActionModule.BaseURL().concat(VariableModule.JSPTraverseAgentArray[i]);
	        driver.get(APIUrl);
	        try {
	            Thread.sleep(3000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }

	    }
	}
	
	public void JSPTraversePINAction(WebDriver driver) 
	{
	    /*============PIN Panel JSP Traversal============*/
		String getnull="";
		ActionModule.writing("**********JSP Traversal for PIN***********");
		ActionModule.ExcelFileWriteAction("**********JSP Traversal for PIN***********","");
		ActionModule.ConsolPrint("**********JSP Traversal for PIN***********","");
		
		String APIUrl = "";
		int i=0;
		APIUrl = ActionModule.BaseURL().concat("/Login.do?username="+VariableModule.pin+"&password="+VariableModule.pinPass);
		driver.get(APIUrl);
	    
	    for(;i<VariableModule.JSPTraversePINArray.length;i++){
	    	
	    	VariableModule.totalcases++;
	        APIUrl = ActionModule.BaseURL().concat(VariableModule.JSPTraversePINArray[i]);
	        driver.get(APIUrl);
/*	        try {
	            Thread.sleep(2000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }*/
	        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	        
	        VariableModule.actualTitle = driver.getTitle();
	        if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle)){
	            //System.out.println("Test Failed with redirect to login page:"+APIUrl);
	            ActionModule.writing("Test Failed with redirect to login page:"+APIUrl);
	            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with redirect to login page");
	            ActionModule.ConsolPrint(APIUrl, "Test Failed with redirect to login page");
	        	ActionModule.PlayAlarmAction();
	        	VariableModule.totalfailedcases++;
	            
	        } else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle1)){
	            //System.out.println("Test Failed with Failure Message:"+APIUrl);
	            ActionModule.writing("Test Failed with Failure Message:"+APIUrl);
	            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Failure Message");
	            ActionModule.ConsolPrint(APIUrl, "Test Failed with Failure Message");
	        	ActionModule.PlayAlarmAction();
	        	VariableModule.totalfailedcases++;
	            
	        }else if (VariableModule.actualTitle.contentEquals(VariableModule.expectedTitle2)){
	            //System.out.println("Test Failed with Exception:"+APIUrl);
	            ActionModule.writing("Test Failed with Exception:"+APIUrl);
	            ActionModule.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
	            ActionModule.ConsolPrint(APIUrl, "Test Failed with Exception");
	        	ActionModule.PlayAlarmAction();
	        	VariableModule.totalfailedcases++;
	        }
	        else
	        {
	        	totalsuccessfulcases++;
	            ActionModule.writing("Test Successful"+APIUrl);
	            ActionModule.ExcelFileWriteAction(APIUrl, "Test Successful");
	            ActionModule.ConsolPrint(APIUrl, "Test Successful");
	            
				getnull=driver.findElement(By.tagName("body")).getText();
				Scanner scanner = new Scanner(getnull);
				while (scanner.hasNextLine()) {
				  //String line = scanner.nextLine();
				  Scanner line = new Scanner(scanner.nextLine());
				  // process the line
			        while (line.hasNext()) {
			            String word = line.next();
			            //System.out.println(s);
						if(word.indexOf("null") != -1){
							//System.out.println("Yes  contains word 'null' %n");
							ActionModule.writing(APIUrl+": null found");
							ActionModule.ExcelFileWriteAction(APIUrl,"null found");
							ActionModule.ConsolPrint(APIUrl,"null found");
				        	ActionModule.PlayAlarmAction();
				        	
							
						}
			        }

				}
				scanner.close();

	        }
	    }
	    totalcases=i;
	    ActionModule.TestSummaryCreator("JSP Travarsal", totalcases, totalsuccessfulcases);
	    totalcases=0;
	    totalsuccessfulcases=0;
	}
	
} // end of APIPermission
