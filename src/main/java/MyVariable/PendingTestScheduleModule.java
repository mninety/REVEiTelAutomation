package MyVariable;

import java.util.Iterator;

import MyAction.ActionModule;
import MyVariable.VariableModule;



public class PendingTestScheduleModule extends Thread {

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
				String[] tempArray=null;
				String tempstr="";
				String Query="select * from auexecutionplanbatch where expbExeType=0 and expbType=2 order by expbTime";
				String executiondata=ActionModule.MysqlConnectionAction(Query,VariableModule.Ownconn,",");
				//ActionModule.ConsolPrint("Modif Time: "+executiondata, "");
				if(!executiondata.equals(""))
				{
					ActionModule.AddPendingQueueScheduleAction(executiondata);
					Thread.sleep(1000);
					Iterator itr=VariableModule.SchedulependingQueue.iterator(); 
					//int k=0;
					while(itr.hasNext())
					{
						tempstr=VariableModule.SchedulependingQueue.poll();
						//tempstr=itr.next().toString();
						tempArray = tempstr.split(",");
						if(System.currentTimeMillis()>Long.parseLong(tempArray[7]))
						{
							String Queryup="update auexecutionplanbatch set expbExeType=1 where expbID="+tempArray[0];
							ActionModule.MysqlConnectionActionUpdate(Queryup);
						}
						//System.out.println(tempstr);
						//k++;
					}
				}
				//System.out.println("Current Thread: "+Thread.currentThread().getName());
				Thread.sleep(VariableModule.sleepvalue);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			
    	}
    }
    
}
