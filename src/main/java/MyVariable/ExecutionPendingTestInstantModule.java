package MyVariable;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import MyAction.ActionModule;
import MyVariable.VariableModule;



public class ExecutionPendingTestInstantModule extends Thread {
	static Logger logExecutionPendingTestInstantModule = Logger.getLogger(ExecutionPendingTestInstantModule.class.getName());
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
				//ActionModule.ConsolPrint("Execution Thread is going to start-"+ActionModule.getCurrentDate(),"");
				String[] QueueArray = null;
				//String[] QueueArray1 = null;
				
/*				WebDriver driver=ActionModule.ChromedriverAction();
				driver.get("https://facebook.com");
				driver.findElement(By.name("email")).sendKeys("mninety@gmail.com");
				driver.findElement(By.name("pass")).sendKeys("y");
				driver.findElement(By.id("u_0_2")).click();
			    try {
			        Thread.sleep(2000);                 //1000 milliseconds is one second.
			    } catch(InterruptedException ex) {
			        Thread.currentThread().interrupt();
			    }
			    driver.switchTo().alert().accept();
			    driver.findElement(By.className("linkWrap noCount")).click();
			    
			    //driver.findElement(By.xpath("//*[@id=\"js_ly\"]/div[1]/div/div[1]/div[2]/div/div/div/div/div/div/div[2]/div/div/div/div")).sendKeys("Test Post");;
			    //driver.findElement(By.className("_1mf7 _4r1q _4jy0 _4jy3 _4jy1 _51sy selected _42ft")).click();
			    //driver.quit();
*/			    
				if(!VariableModule.InstantpendingQueue.isEmpty())
				{

					ActionModule.OngoingstatusUpdateAction("2");
					ActionModule.OngoingplanstatusUpdateAction("2");
					
					Thread[] threads = new Thread[VariableModule.InstantpendingplanQueue.size()];
					//System.out.println("Thread Size: "+threads.length);
					//int k=0;
					 for (int i = 0; i < threads.length; i++) {
					 

					     threads[i] = new Thread(new Runnable() {
					         public void run() {
					        	 String[] QueueArray2=null;
					        	 
					             // some code to run in parallel
					             // this could also be another class that implements Runnable
									if(!VariableModule.InstantpendingplanQueue.isEmpty())
									{
										//System.out.println("Current Thread: "+Thread.currentThread().getName()+" At: "+ActionModule.getCurrentDate()+" is started");
										logExecutionPendingTestInstantModule.debug("Current Thread: "+Thread.currentThread().getName()+" At: "+ActionModule.getCurrentDate()+" is started");
							        	 String temp=VariableModule.InstantpendingplanQueue.poll();
										 logExecutionPendingTestInstantModule.debug("InstantpendingplanQueue poll");
										 //System.out.println("POLL Plan Queue: "+temp);
										 logExecutionPendingTestInstantModule.debug("POLL Plan Queue: "+temp);
										 QueueArray2 = temp.split(",");
										 ActionModule.TestExecution(QueueArray2);
										 try {
											Thread.sleep(50000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

										 //ActionModule.ExeResultInsertion(QueueArray2,"","");
										 //System.out.println("Current Thread: "+Thread.currentThread().getName()+" At: "+ActionModule.getCurrentDate()+" is finished");
										 logExecutionPendingTestInstantModule.debug("Current Thread: "+Thread.currentThread().getName()+" At: "+ActionModule.getCurrentDate()+" is finished");
										 //ActionModule.ConsolPrint("Execution Thread is going to start-"+ActionModule.getCurrentDate(),"");
									}
									else
									{
										//System.out.println("Empty PLAN QUEUE: ");
										logExecutionPendingTestInstantModule.debug("Empty PLAN QUEUE: ");
									}
					         }
					     });

					     threads[i].start();
/*					     if(boolean done=threads[i].isAlive())
					     {
					    	 
					     }*/
					 }
					
					 try {
						Thread.sleep(50000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

						 ActionModule.ExecutionDoneAction("3","batch","0");
					 

				}
				else
				{
					//System.out.println("Empty EXE QUEUE: ");
					logExecutionPendingTestInstantModule.debug("Empty EXE QUEUE: ");
				}
				

				//System.out.println("Current Thread: "+Thread.currentThread().getName());
				//Thread.sleep(Integer.parseInt(VariableModule.sleepTime)*60000);
				Thread.sleep(10000);
				

				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			
    	}
    }
    
}
