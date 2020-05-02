package MyVariable;

import MyAction.ActionModule;

public class AddRepoModule extends Thread{
		@Override
	    public void run() {
	    	while(true) {

				//CommonOSModule.ConsolPrint("Last Modification Time Checker module is started at "+CommonOSModule.getCurrentDate());
				ActionModule.writing("Add repository module is started at "+ActionModule.getCurrentDate());
				
				ActionModule.AddRepositoryAction("auconfigvar");
/*				long tvmodificationTime=CommonAction.LastModificationChecker("thresholdvalue",CommonOSModule.MyIPAddress);
				long mimodificationTime=CommonAction.LastModificationChecker("mailinfo",CommonOSModule.MyIPAddress);
				long mnmodificationTime=CommonAction.LastModificationChecker("managenotification",CommonOSModule.MyIPAddress);
				long scmodificationTime=CommonAction.LastModificationChecker("serverconfig",CommonOSModule.MyIPAddress);
				
				
				if(CommonOSModule.tvLastModificationTime<tvmodificationTime)
				{
					CommonOSModule.tvLastModificationTime=tvmodificationTime;
					CommonOSModule.writingLog("Updated tv Modification Time: "+CommonOSModule.tvLastModificationTime);
					CommonAction.RepositoryUpdate("thresholdvalue",CommonOSModule.MyIPAddress);
				}*/
				

	    	}
	    	
	    }
	}