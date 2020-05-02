package Client;


import java.awt.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Login.LoginModule;
import MyAction.ActionModule;
import MyAutomation.MyClass;
import MyVariable.VariableModule;

public class ClientModule {
	static Logger logClientModule = Logger.getLogger(ClientModule.class.getName());
	VariableModule variabledo = new VariableModule();
	ActionModule actiondo = new ActionModule();
	//public void ClientAction(WebDriver driver, String callerIDSet) {

	public static String CreateClient()
	{
		String resellerName="";
		String currentDate = ActionModule.getCurrentDate();
		int randomValue = (int )(Math.random() * 500 + 1);
		resellerName="Reseller-"+currentDate+"-"+randomValue;
		
		return resellerName;
	}
	public static String CreatePIN()
	{
		String resellerName="";
		
		int randomValue = (int )(Math.random() * 100000 + 1);
		resellerName="8801830"+randomValue;
		
		return resellerName;
	}
	public static String CreatePINBatch()
	{
		String resellerName="";
		
		int randomValue = (int )(Math.random() * 100000 + 1);
		resellerName="Batch-"+randomValue;
		
		return resellerName;
	}
	

	public static void UploadPIN(WebDriver driver, String[] planArray, String[] ConfArray, String drive) 
	{
		AdminLogin(driver,ConfArray);
		
		String APIUrl = ConfArray[6].concat("/pin/uploadPin.jsp");
		driver.get(APIUrl);
	    try {
	        Thread.sleep(2000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	    if(driver.getTitle().equals("Upload PIN"))
	    {
		    WebElement uploadElement = driver.findElement(By.name("pinFile"));
		
		    if(drive.equals("1"))
		    {
		    	uploadElement.sendKeys("E:\\Ninety\\Testing\\pinUpload.txt");
		    }
		    else
		    {
		    	uploadElement.sendKeys("/usr/local/iTelTestAutomation/pinUpload.txt");
		    }
		    driver.findElement(By.name("Submit")).click();
		    try {
		        Thread.sleep(2000);                 //1000 milliseconds is one second.
		    } catch(InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
		    
			if(ActionModule.GetCongratulation(driver,"successfully"))
			{
				ActionModule.ExeResultInsertion(planArray[0],"PIN Uploading is successful","1");
				
			}
			else
			{
				ActionModule.ExeResultInsertion(planArray[0],"PIN Uploading is failed","2");
			}
			
	    }
	    else
	    {
	    	ActionModule.ExeResultInsertion(planArray[0],"PIN Uploading is failed","2");
	    }
	}
	public static String ClientAddAction(WebDriver driver, String[] planArray, String[] ConfArray, String drive) {
	
		String resellerName=CreateClient();
		logClientModule.debug("Client Module is started to execute");
		logClientModule.debug("Reseller Name: "+resellerName);
		//System.out.println("Reseller Name: "+resellerName);

		AdminLogin(driver,ConfArray);
		
		String APIUrl = ConfArray[6].concat("/clients/clientAdd.jsp");
		driver.get(APIUrl);
	    try {
	        Thread.sleep(2000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	    
		if(driver.getTitle().equals("Add Client"))
		{
			driver.findElement(By.name("customerID")).sendKeys(resellerName);
			driver.findElement(By.name("billingPassword")).sendKeys("abc1");
			driver.findElement(By.name("confirmBillingPassword")).sendKeys("abc1");
	
			driver.findElement(By.name("B2")).click();
				
				if(drive.equals("1"))
				{
					driver.switchTo().alert().accept();
				    try {
				        Thread.sleep(3000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }
					driver.switchTo().alert().accept();
				}
				else
				{
				    try {
				        Thread.sleep(5000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }
				}
				
	
				if(ActionModule.GetCongratulation(driver,"Congratulations"))
				{
					ActionModule.ExeResultInsertion(planArray[0],"Client:"+resellerName+" Add is successful","1");
					
				}
				else
				{
					ActionModule.ExeResultInsertion(planArray[0],"Client:"+resellerName+" Add is failed","2");
				}
				
		}
		
		else
		{
			ActionModule.ExeResultInsertion(planArray[0],"Client:"+resellerName+" Add is failed","2");
		}

		ClientSearchAction(driver, planArray, ConfArray, drive, resellerName);
		//} //for loop end
		driver.quit();
		return resellerName;
	} //ClientAction end

	public static void ClientSearchAction(WebDriver driver, String[] planArray, String[] ConfArray, String drive, String reseller) {
	
		
		//=====================Search Client==================
		//actiondo.writing("**********Search Client***********");
		logClientModule.debug("**********Search Client***********");
		String APIUrl = "";
		APIUrl = ConfArray[6].concat("/ViewClient.do");
		driver.get(APIUrl);
	    try {
	        Thread.sleep(2000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	    
		if(driver.getTitle().equals("Clients"))
		{
			driver.findElement(By.name("clCustomerID")).sendKeys(reseller);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
		    try {
		        Thread.sleep(2000);                 //1000 milliseconds is one second.
		    } catch(InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
		    
			if(ActionModule.GetCongratulation(driver,reseller))
			{
				ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Search is successful","1");
			}
			else
			{
				ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Search is failed","2");
			}
			
			driver.findElement(By.xpath("//*[@id=\"AutoNumber3\"]/form/div[2]/div/table/tbody/tr[2]/td[7]/a")).click();
		    
			try {
		        Thread.sleep(2000);                 //1000 milliseconds is one second.
		    } catch(InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
			
			//System.out.println("Page Title: "+driver.getTitle());
			if(driver.getTitle().equals("Edit Client"))
			{
				logClientModule.debug("Edit Client Page Found");
				//System.out.println("Edit Client Page Found");

				driver.findElement(By.name("B2")).click();
				try {
			        Thread.sleep(2000);                 //1000 milliseconds is one second.
			    } catch(InterruptedException ex) {
			        Thread.currentThread().interrupt();
			    }
				
				if(driver.getTitle().equals("Clients"))
				{
					ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Edit is successful","1");
					
					driver.findElement(By.xpath("//*[@id=\"AutoNumber3\"]/form/div[2]/div/table/tbody/tr[2]/td[6]/a")).click();
					try {
				        Thread.sleep(2000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }
					
					if(driver.getTitle().equals("Account Recharge"))
					{
						logClientModule.debug("Recharge Page Found");
						//System.out.println("Recharge Page Found");

						//driver.findElement(By.name("RechargeAmount")).clear();
						driver.findElement(By.name("RechargeAmount")).sendKeys("5");
						driver.findElement(By.name("B2")).click();
						try {
					        Thread.sleep(2000);                 //1000 milliseconds is one second.
					    } catch(InterruptedException ex) {
					        Thread.currentThread().interrupt();
					    }


						if(ActionModule.GetCongratulation(driver,"successfully"))
						{
							ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" is recharged successfully","1");
						}
						else
						{
							ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Recharge is failed","2");
						}
						
						
					}
					else
					{
						logClientModule.debug("Recharge Page Not Found");
						//System.out.println("Recharge Page Not Found");
						ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Recharge is failed","2");
					}
					
				}
				else
				{
					ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Edit is failed","2");
					ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Recharge is failed","2");
				}
				
			}
			else
			{
				logClientModule.debug("Edit Client Page Not Found");
				//System.out.println("Edit Client Page Not Found");
				ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Edit is failed","2");
			}
			
			

			
		}
		else
		{
			ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Search is failed","2");
			ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Edit is failed","2");
			ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Recharge is failed","2");
			
		}
	
		
	}

	public static void PinSearchsingleAction(WebDriver driver, String[] planArray, String[] ConfArray, String drive, String SinglePIN)
	{
		
		logClientModule.debug("**********Search PIN***********");
		String APIUrl = "";
		APIUrl = ConfArray[6].concat("/ViewPinBatchDetails.do?PinBatchID=-2");
		driver.get(APIUrl);
	    try {
	        Thread.sleep(2000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	    
		if(driver.getTitle().equals("Pin Batch Details"))
		{
			driver.findElement(By.name("clCustomerID")).sendKeys(SinglePIN);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
		    try {
		        Thread.sleep(2000);                 //1000 milliseconds is one second.
		    } catch(InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
		    
			if(ActionModule.GetCongratulation(driver,SinglePIN))
			{
				ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Search is successful","1");
			}
			else
			{
				ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Search is failed","2");
			}
			
			driver.findElement(By.xpath("//*[@id=\"tablesorter\"]/tbody/tr/td[12]/a")).click();
		    
			try {
		        Thread.sleep(2000);                 //1000 milliseconds is one second.
		    } catch(InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
			
			//System.out.println("Page Title: "+driver.getTitle());
			if(driver.getTitle().equals("Edit Single PIN"))
			{
				logClientModule.debug("Edit PIN Page Found");
				//System.out.println("Edit Client Page Found");

				driver.findElement(By.name("B2")).click();
				try {
			        Thread.sleep(2000);                 //1000 milliseconds is one second.
			    } catch(InterruptedException ex) {
			        Thread.currentThread().interrupt();
			    }
				
				if(driver.getTitle().equals("Pin Batch Details"))
				{
					ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Edit is successful","1");
					
					driver.findElement(By.xpath("//*[@id=\"tablesorter\"]/tbody/tr/td[9]/a")).click();
					try {
				        Thread.sleep(2000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }
					
					if(driver.getTitle().equals("Pin Recharge"))
					{
						logClientModule.debug("PIN Recharge Page Found");
						//System.out.println("Recharge Page Found");

						//driver.findElement(By.name("RechargeAmount")).clear();
						driver.findElement(By.name("RechargeAmount")).sendKeys("5");
						driver.findElement(By.name("B2")).click();
						try {
					        Thread.sleep(2000);                 //1000 milliseconds is one second.
					    } catch(InterruptedException ex) {
					        Thread.currentThread().interrupt();
					    }


						if(ActionModule.GetCongratulation(driver,"successfully"))
						{
							ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" is recharged successfully","1");
						}
						else
						{
							ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Recharge is failed","2");
						}
						
						
					}
					else
					{
						logClientModule.debug("Recharge Page Not Found");
						//System.out.println("Recharge Page Not Found");
						ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Recharge is failed","2");
					}
					
				}
				else
				{
					ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Edit is failed","2");
					ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Recharge is failed","2");
				}
				
			}
			else
			{
				logClientModule.debug("Edit PIN Page Not Found");
				//System.out.println("Edit Client Page Not Found");
				ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Edit is failed","2");
			}
			
			

			
		}
		else
		{
			ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Search is failed","2");
			ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Edit is failed","2");
			ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePIN+" Recharge is failed","2");
			
		}
	
		
	}
	
	public static void PinBatchSearchAction(WebDriver driver, String[] planArray, String[] ConfArray, String drive, String SinglePIN)
	{
		
		logClientModule.debug("**********Search PIN Batch***********");
		String APIUrl = "";
		APIUrl = ConfArray[6].concat("/ViewPinBatch.do");
		driver.get(APIUrl);
	    try {
	        Thread.sleep(2000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	    
		if(driver.getTitle().equals("Pin Batch"))
		{
			driver.findElement(By.name("pbName")).sendKeys(SinglePIN);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
		    try {
		        Thread.sleep(2000);                 //1000 milliseconds is one second.
		    } catch(InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
		    
			if(ActionModule.GetCongratulation(driver,SinglePIN))
			{
				ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" Search is successful","1");
			}
			else
			{
				ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" Search is failed","2");
			}
			
			driver.findElement(By.xpath("//*[@id=\"AutoNumber3\"]/form/div[2]/div/table/tbody/tr[2]/td[6]/a")).click();
		    
			try {
		        Thread.sleep(2000);                 //1000 milliseconds is one second.
		    } catch(InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
			
			//System.out.println("Page Title: "+driver.getTitle());
			if(driver.getTitle().equals("Edit Pin Batch"))
			{
				logClientModule.debug("Edit PIN Batch Page Found");
				//System.out.println("Edit Client Page Found");

				driver.findElement(By.name("B2")).click();
				try {
			        Thread.sleep(2000);                 //1000 milliseconds is one second.
			    } catch(InterruptedException ex) {
			        Thread.currentThread().interrupt();
			    }
				
				if(driver.getTitle().equals("Pin Batch"))
				{
					ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" Edit is successful","1");
					
					if(drive.equals("1"))
					{
					    driver.findElement(By.xpath("//input[@type='checkbox' and @name='deleteIDs']")).click();
				        
				        
				        driver.findElement(By.xpath("//*[@id=\"AutoNumber3\"]/form/div[1]/div/input[3]")).click();
					    
						driver.switchTo().alert().accept();
					    try {
					        Thread.sleep(2000);                 //1000 milliseconds is one second.
					    } catch(InterruptedException ex) {
					        Thread.currentThread().interrupt();
					    }
						
					}
					else
					{
					    driver.findElement(By.xpath("//input[@type='checkbox' and @name='deleteIDs']")).click();
				        
				        
				        driver.findElement(By.xpath("//*[@id=\"AutoNumber3\"]/form/div[1]/div/input[3]")).click();
					    
				        
					    try {
					        Thread.sleep(2000);                 //1000 milliseconds is one second.
					    } catch(InterruptedException ex) {
					        Thread.currentThread().interrupt();
					    }
					}
				    
					if(driver.getTitle().equals("Pin Batch"))
					{
						ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" is deleted successfully","1");
					}
					else
					{
						ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" Delete is failed","2");
					}
					
					
				}
				else
				{
					ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" Edit is failed","2");
					ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" Delete is failed","2");
				}
				
			}
			else
			{
				logClientModule.debug("Edit PIN Batch Page Not Found");
				//System.out.println("Edit Client Page Not Found");
				ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" Edit is failed","2");
			}
			
			

			
		}
		else
		{
			ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" Search is failed","2");
			ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" Edit is failed","2");
			ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+SinglePIN+" Delete is failed","2");
			
		}
	
		
	}
	
	
	public static void AdminLogin(WebDriver driver, String[] ConfArray)
	{
		String Adminlogin = ConfArray[6].concat("/Login.do?username="+ConfArray[12]+"&password="+ConfArray[13]);
		driver.get(Adminlogin);
	    try {
	        Thread.sleep(2000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	}
	
	public static void ClientDeleteAction(WebDriver driver, String[] planArray, String[] ConfArray, String drive, String reseller) {
	//public static void ClientDeleteAction() {
		
		try {
			logClientModule.debug("**********Delete Client***********");
			
			AdminLogin(driver,ConfArray);
			
			String APIUrl = ConfArray[6].concat("/ViewClient.do");
			//APIUrl = actiondo.BaseURL().concat("/ViewClient.do");
			driver.get(APIUrl);

			try {
			    Thread.sleep(2000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			if(driver.getTitle().equals("Clients"))
			{
				
				driver.findElement(By.name("clCustomerID")).sendKeys(reseller);
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				
			    try {
			        Thread.sleep(2000);                 //1000 milliseconds is one second.
			    } catch(InterruptedException ex) {
			        Thread.currentThread().interrupt();
			    }

			    //driver.findElement(By.xpath("//*[@id='AutoNumber3']/form/div[2]/div/table/tbody/tr[2]/td[8]/input")).click();
			    


				if(drive.equals("1"))
				{
				    driver.findElement(By.xpath("//input[@type='checkbox' and @name='deleteIDs']")).click();
			        
			        
			        driver.findElement(By.xpath("//*[@id='AutoNumber3']/form/div[1]/div[1]/input[3]")).click();
				    
					driver.switchTo().alert().accept();
				    try {
				        Thread.sleep(2000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }
					
				}
				else
				{
				    driver.findElement(By.xpath("//input[@type='checkbox' and @name='deleteIDs']")).click();
			        
			        
			        driver.findElement(By.xpath("//*[@id='AutoNumber3']/form/div[1]/div[1]/input[3]")).click();
				    
			        
				    try {
				        Thread.sleep(2000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }
				}
			    
				if(ActionModule.GetCongratulation(driver,"Deleted"))
				{
					ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" is deleted successfully","1");
				}
				else
				{
					ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Delete is failed","2");
				}
				
			}
			else
			{
				ActionModule.ExeResultInsertion(planArray[0],"Client:"+reseller+" Delete is failed","2");
			}

			
			driver.quit();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Client Delete module generates the exception: "+e);
			e.printStackTrace();
		}
	}
	
	
	public static void SinglePINDeleteAction(WebDriver driver, String[] planArray, String[] ConfArray, String drive, String SinglePINName) {
	//public static void ClientDeleteAction() {
		
		try {
			logClientModule.debug("**********Delete PIN***********");
			
			AdminLogin(driver,ConfArray);
			
			String APIUrl = ConfArray[6].concat("/ViewPinBatchDetails.do?PinBatchID=-2");
			//APIUrl = actiondo.BaseURL().concat("/ViewClient.do");
			driver.get(APIUrl);

			try {
			    Thread.sleep(2000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			if(driver.getTitle().equals("Pin Batch Details"))
			{
				
				driver.findElement(By.name("clCustomerID")).sendKeys(SinglePINName);
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				
			    try {
			        Thread.sleep(4000);                 //1000 milliseconds is one second.
			    } catch(InterruptedException ex) {
			        Thread.currentThread().interrupt();
			    }

			    //driver.findElement(By.xpath("//*[@id='AutoNumber3']/form/div[2]/div/table/tbody/tr[2]/td[8]/input")).click();
			    


				if(drive.equals("1"))
				{
				    driver.findElement(By.xpath("//input[@type='checkbox' and @name='deleteIDs']")).click();
			        
			        
			        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/form/div[1]/div[1]/input[3]")).click();
				    
					driver.switchTo().alert().accept();
				    try {
				        Thread.sleep(2000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }
					
				}
				else
				{
				    driver.findElement(By.xpath("//input[@type='checkbox' and @name='deleteIDs']")).click();
			        
			        
			        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/form/div[1]/div[1]/input[3]")).click();
				    
			        
				    try {
				        Thread.sleep(4000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }
				}
			    //System.out.print("Test Delete");
				if(ActionModule.GetCongratulation(driver,"Deleted"))
				{
					ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePINName+" is deleted successfully","1");
				}
				else
				{
					ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePINName+" Delete is failed","2");
				}
				
			}
			else
			{
				ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePINName+" Delete is failed","2");
			}

			
			driver.quit();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("PIN Delete module generates the exception: "+e);
			e.printStackTrace();
		}
	}
	
	public static String SinglePINAddAction(WebDriver driver, String[] planArray, String[] ConfArray, String drive) {
	
		
		String SinglePINName=CreatePIN();
		logClientModule.debug("Pin Module is started to execute");
		logClientModule.debug("Single PIN Name: "+SinglePINName);
		//System.out.println("Reseller Name: "+resellerName);

		AdminLogin(driver,ConfArray);
		
		String APIUrl = ConfArray[6].concat("/pin/pinSingleAdd.jsp");
		driver.get(APIUrl);
	    try {
	        Thread.sleep(2000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	    
		if(driver.getTitle().equals("Generate Single PIN"))
		{
			driver.findElement(By.name("pinNumber")).sendKeys(SinglePINName);
			driver.findElement(By.name("pinPassword")).sendKeys("1");
	
			driver.findElement(By.xpath("//input[@type='submit']")).click();
				
				if(drive.equals("1"))
				{
				    try {
				        Thread.sleep(3000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }

				}
				else
				{
				    try {
				        Thread.sleep(3000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }
				}
				
	
				if(ActionModule.GetCongratulation(driver,"successfully"))
				{
					ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePINName+" Add is successful","1");
					
				}
				else
				{
					ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePINName+" Add is failed","2");
				}
				
		}
		
		else
		{
			ActionModule.ExeResultInsertion(planArray[0],"PIN:"+SinglePINName+" Add is failed","2");
		}

		PinSearchsingleAction(driver, planArray, ConfArray, drive, SinglePINName);
		//} //for loop end
		driver.quit();
		return SinglePINName;

		
	}
	
	public static String PINBatchAddAction(WebDriver driver, String[] planArray, String[] ConfArray, String drive) {
	
		
		String PINBatchName=CreatePINBatch();
		logClientModule.debug("Pin Batch Module is started to execute");
		logClientModule.debug("PIN Batch Name: "+PINBatchName);
		//System.out.println("Reseller Name: "+resellerName);

		AdminLogin(driver,ConfArray);
		
		String APIUrl = ConfArray[6].concat("/pin/pinBatchAdd.jsp");
		driver.get(APIUrl);
	    try {
	        Thread.sleep(2000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	    
	    System.out.println("Current URL: "+driver.getCurrentUrl());
	    
		if(driver.getTitle().equals("Add Pin Batch"))
		{
			driver.findElement(By.name("batchName")).sendKeys(PINBatchName);
			driver.findElement(By.name("pinPrefix")).sendKeys("880");
			driver.findElement(By.name("totalNumberOfPin")).sendKeys("5");
			driver.findElement(By.name("pinDigitLength")).sendKeys("13");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
				
				if(drive.equals("1"))
				{
					driver.switchTo().alert().accept();
				    try {
				        Thread.sleep(3000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }

				}
				else
				{
				    try {
				        Thread.sleep(3000);                 //1000 milliseconds is one second.
				    } catch(InterruptedException ex) {
				        Thread.currentThread().interrupt();
				    }
				}
				
				System.out.println("Current URL: "+driver.getCurrentUrl());
				
				if(ActionModule.GetCongratulation(driver,"successfully"))
				{
					ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+PINBatchName+" Add is successful","1");
					PinBatchSearchAction(driver, planArray, ConfArray, drive, PINBatchName);
				}
				else
				{
					ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+PINBatchName+" Add is failed","2");
					ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+PINBatchName+" Search is failed","2");
					ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+PINBatchName+" Edit is failed","2");
					ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+PINBatchName+" Delete is failed","2");
				}
				
		}
		
		else
		{
			ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+PINBatchName+" Add is failed","2");
			ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+PINBatchName+" Search is failed","2");
			ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+PINBatchName+" Edit is failed","2");
			ActionModule.ExeResultInsertion(planArray[0],"PIN Batch:"+PINBatchName+" Delete is failed","2");
		}

		driver.quit();
		return PINBatchName;

		
	}
	public String PINSignUPAction(WebDriver driver) {
	
		
		//=====================PIN Sign Up==================
		actiondo.writing("**********PIN Sign Up***********");
		String APIUrl = "";
		String currentDate = "";
		currentDate = actiondo.getCurrentDate();
		int randomValue = (int )(Math.random() * 500 + 1);
		APIUrl = actiondo.BaseURL().concat("/pin/pinSingleAdd.jsp");
		driver.get(APIUrl);
		driver.findElement(By.name("pinNumber")).sendKeys(variabledo.clientNameArray[5]+'-'+currentDate+'-'+randomValue);
		String newPIN=variabledo.clientNameArray[5]+'-'+currentDate+'-'+randomValue;
		driver.findElement(By.name("pinPassword")).sendKeys(variabledo.pinPass);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		return newPIN;
		
	}
	
	public void PinRechargeAction(WebDriver driver) {
	
		
		//=====================Recharge PIN==================
		actiondo.writing("**********Recharge PIN***********");
		String APIUrl = "";
		APIUrl = actiondo.BaseURL().concat("/ViewPinRecharge.do?PinBatchID=-2");
		driver.get(APIUrl);
	    try {
	        Thread.sleep(2000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
		String clientID;
		clientID = JOptionPane.showInputDialog("PIN NO");
		//System.out.println("Client ID:"+clientID);
		driver.findElement(By.name("clCustomerID")).clear();
		driver.findElement(By.name("clCustomerID")).sendKeys(clientID);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	    try {
	        Thread.sleep(5000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	    
		java.util.List<WebElement> rows = driver.findElements(By.xpath("//table[@id='myTable']/tbody/tr"));
		System.out.println("Total number of rows :"+ rows.size());
		int randomIPpart = (int )(Math.random() * rows.size() + 1);
		System.out.println("random pin:"+randomIPpart);  //"+(randomIPpart+1)+"
		driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr[4]/td[5]").name("RechargeAmount")).clear();
		driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr[4]/td[5]").name("RechargeAmount")).sendKeys(""+randomIPpart);
		//System.out.println("random pin:"+testdate);
		//driver.findElement(By.xpath(".//table[@id='myTable']/tbody/tr[2]/td[5]"));
		
/*		driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr[1]/td[7]")).clear();
		driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr[1]/td[7]")).sendKeys(""+randomIPpart);
		
		driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr[1]/td[8]")).clear();
		driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr[1]/td[8]")).sendKeys("abc");*/
		driver.findElement(By.name("B2")).click();
		driver.switchTo().alert().accept();
		//wait.until(ExpectedConditions.presenseOfElementLocated(ByLocator)); //in case you want to wait for a particular element to appear on the page.
	}
	
	
} //ClientModule class end

