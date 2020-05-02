package MultithreadPermission;

import org.openqa.selenium.WebDriver;

import MyAction.ActionModule;
import MyPermission.PermissionModule;

public class MultithreadPermissionModule extends Thread{

    PermissionModule permissiondo = new PermissionModule();
    ActionModule actiondo = new ActionModule();
    
    @Override
    public void run() {
	  	
  	  WebDriver driver1 = actiondo.ChromedriverAction();
		
  	  //permissiondo.PermissionAPIAction(driver1);
    }
}
