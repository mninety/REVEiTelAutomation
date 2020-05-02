package MultithreadAPI;

import org.openqa.selenium.WebDriver;

import DevelopedAPI.DevelopedAPIModule;
import MyAction.ActionModule;
import MyPermission.PermissionModule;
import MyVariable.VariableModule;

public class MultithreadAPIModule extends Thread {

    PermissionModule permissiondo = new PermissionModule();
    DevelopedAPIModule apido = new DevelopedAPIModule();
    ActionModule actiondo = new ActionModule();
    
    
    @Override
    public void run() {
	  	
    	WebDriver driver = actiondo.FirefoxdriverAction();
	  	
  		
	    //apido.CallerIDAPIAction(driver);
	    apido.ClientAddAPIAction(driver);
    	apido.ALLCommonAPIAction(driver);
		apido.PakcageAPIAction(driver);
	    apido.HistoryAPIAction(driver);
	    apido.AddFundAPIAction(driver);
        //apido.ReferralAPIAction(driver);
    }
    
}
class MultithreadPermissionModule extends Thread {

    PermissionModule permissiondo = new PermissionModule();
    ActionModule actiondo = new ActionModule();
    VariableModule variabledo = new VariableModule();
    
    @Override
    public void run() {
	  	
  	  WebDriver driver1 = actiondo.ChromedriverAction();
      String APIUrl1 = actiondo.BaseURL().concat("/Login.do?username="+variabledo.pin+"&password="+variabledo.pinPass);
      driver1.get(APIUrl1);
  	  //permissiondo.PermissionAPIAction(driver1);
    }
    
}

class MultithreadJSPAdminModule extends Thread {

    PermissionModule permissiondo = new PermissionModule();
    ActionModule actiondo = new ActionModule();
    
    public void run() {
    	WebDriver driver2 = actiondo.FirefoxdriverAction();
      	
	    //permissiondo.JSPTraverseAdminAction(driver2);
    }
    
}

class MultithreadJSPPINModule extends Thread {

    PermissionModule permissiondo = new PermissionModule();
    ActionModule actiondo = new ActionModule();
    
    public void run() {
	    WebDriver driver3 = actiondo.FirefoxdriverAction();
	    
	    permissiondo.JSPTraversePINAction(driver3);
    }
    
}
