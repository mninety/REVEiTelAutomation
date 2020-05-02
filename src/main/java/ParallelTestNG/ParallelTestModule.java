package ParallelTestNG;

import org.openqa.selenium.WebDriver;

import BuyCredit.MyPaymentModule;
import DevelopedAPI.DevelopedAPIModule;
import MyAction.ActionModule;
import MyEmail.EmailModule;
import MyPermission.PermissionModule;
import MyVariable.VariableModule;

public class ParallelTestModule {
	
	ActionModule actiondo = new ActionModule();
	VariableModule variabledo = new VariableModule();
    PermissionModule permissiondo = new PermissionModule();
    DevelopedAPIModule apido = new DevelopedAPIModule();
    MyPaymentModule paymentdo = new MyPaymentModule();
    EmailModule emaildo = new EmailModule();
    
    
/*    public void ParallelTestAction() {
    	ParallelTestAPIAction();
    	ParallelTestPermissionAction();
    	ParallelTestJSPAdminAction();
    	ParallelTestJSPPINAction();
    }*/
  public void ParallelTestAPIAction() {
	  	
	  	WebDriver driver = actiondo.FirefoxdriverAction();
  	
  		apido.ALLCommonAPIAction(driver);
  		apido.HistoryAPIAction(driver);
  		apido.ClientAddAPIAction(driver);
  		apido.PakcageAPIAction(driver);
  		//apido.CallerIDAPIAction(driver);
  		//apido.ReferralAPIAction(driver);
  		
  		
      	

	    

	    //paymentdo.BalanceTransferAction(driver);
	    //paymentdo.BuyCreditAction(driver);
	    
	    

	  
  }
  

  public void ParallelTestPermissionAction() {
	  WebDriver driver1 = actiondo.ChromedriverAction();
		
    	//permissiondo.PermissionAPIAction(driver1);
  }
  

  public void ParallelTestJSPAdminAction() {
    	WebDriver driver2 = actiondo.FirefoxdriverAction();
      	
	    //permissiondo.JSPTraverseAdminAction(driver2);
  }
  

  public void ParallelTestJSPPINAction() {
	    WebDriver driver3 = actiondo.FirefoxdriverAction();
	    
	    permissiondo.JSPTraversePINAction(driver3);
  }

 
  
}
