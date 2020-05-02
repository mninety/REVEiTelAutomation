package MyVariable;

import org.apache.log4j.Logger;

import MyAction.ActionModule;
import MyVariable.VariableModule;



public class PendingTestInstantModule extends Thread {
	static Logger logPendingTestInstantModule = Logger.getLogger(PendingTestInstantModule.class.getName());
/*	 Thread[] threads = new Thread[NUM_JOBS_TO_CREATE];
	 for (int i = 0; i < threads.length; i++) {
	     threads[i] = new Thread(new Runnable() {
	         public void run() {
	             // some code to run in parallel
	             // this could also be another class that implements Runnable
	         }
	     });
	     threads[i].start();
	 }*/
	 
	@Override
    public void run() {
    	while(true) {
			try {
				String executiondata="";
				String Query="select * from auexecutionplanbatch where expbExeType=1 order by expbTime";
				executiondata=ActionModule.MysqlConnectionAction(Query,VariableModule.Ownconn,",");
				//ActionModule.ConsolPrint("Modif Time: "+executiondata, "");
				logPendingTestInstantModule.debug("Batch List: "+executiondata);
				if(!executiondata.equals(""))
				{
					ActionModule.AddPendingQueueInstantAction(executiondata);
				}
				//System.out.println("Current Thread: "+Thread.currentThread().getName());
				Thread.sleep(VariableModule.sleepvalue+60000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			
    	}
    }
    
}
