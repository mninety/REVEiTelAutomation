package MultithreadJSPAdmin;

import org.openqa.selenium.WebDriver;

import MyAction.ActionModule;
import MyPermission.PermissionModule;

public class MultithreadJSPAdminModule extends Thread{

    PermissionModule permissiondo = new PermissionModule();
    ActionModule actiondo = new ActionModule();
    
    @Override
    public void run() {
    	WebDriver driver2 = actiondo.FirefoxdriverAction();
      	
	    //permissiondo.JSPTraverseAdminAction(driver2);
	    permissiondo.JSPTraversePINAction(driver2);
    }
}