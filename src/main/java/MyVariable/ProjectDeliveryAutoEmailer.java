package MyVariable;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

//import com.sun.corba.se.spi.orbutil.fsm.Action;

import MyAction.ActionModule;
import MyAutomation.MyClass;
import MyEmail.EmailModule;

public class ProjectDeliveryAutoEmailer extends Thread {
	static Logger logProjectDeliveryAutoEmailer = Logger.getLogger(ProjectDeliveryAutoEmailer.class.getName());

	@Override
    public void run() {
    	while(true) {

    			mailFunction();

    		//ActionModule.ConsolPrint("Query: ");
    	}
    }

	public void mailFunction()
	{
		
			//System.out.println("Error in: " + this.getClass().getName());
			
			logProjectDeliveryAutoEmailer.debug("Project Delivery Emailer Module is going to start");
			
			String[] splitArray=null;
			String[] splitArray1=null;
			String subject="Project Deadline of ";
    		String hourTime=ActionModule.getCurrentHour();
    		ActionModule.ConsolPrint("Time: "+hourTime,"");
    		
    		if(hourTime.equals(VariableModule.summarymailtime))
    		{
    			try {
    			
    			String queryformem="select projectId,projectName,projectType,projectManager,startDate,pdtDeliveryDate,salesDeliveryDate,phaseClosingDate from sqaMemberControl where pdtDeliveryDate<'"+ActionModule.getDate()+" 00:00:00'  and projectId in(select distinct(projectId) from sqaAssignTaskMobile where status='Assigned') order by projectId";
    			
    			logProjectDeliveryAutoEmailer.debug("Query: "+queryformem);
    			ActionModule.writing("Query: "+queryformem);
    			String qeuryresult=ActionModule.MysqlConnectionAction(queryformem, VariableModule.Ownconn, "@");
    			
    			logProjectDeliveryAutoEmailer.debug("Query Result: "+qeuryresult);
    			
    			if(!qeuryresult.equals(""))
    			{
    				qeuryresult=qeuryresult.substring(0, qeuryresult.length()-1);
	    			String[] projectArray = qeuryresult.split("\n");
	    			
        			String queryformem1="select taggedProjectId,androidMembers,iosMembers,webMembers,erpMembers,salesMembers from sqaTaggedMembers where taggedProjectId in(select projectId from sqaMemberControl where pdtDeliveryDate<'"+ActionModule.getDate()+" 00:00:00'  and projectId in(select distinct(projectId) from sqaAssignTaskMobile where status='Assigned') order by projectId) order by taggedProjectId";

        			logProjectDeliveryAutoEmailer.debug("Query1: "+queryformem1);
        			String qeuryresult1=ActionModule.MysqlConnectionAction(queryformem1, VariableModule.Ownconn, "@");

        			logProjectDeliveryAutoEmailer.debug("Query Result1: "+qeuryresult1);
        			qeuryresult1=qeuryresult1.substring(0, qeuryresult1.length()-1);
        			String[] projectArray1 = qeuryresult1.split("\n");
        			
	    			for(int i=0;i<projectArray.length;i++)
	    			{

	    				String tableHeader="<html><body style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px;'>Dear Team <br>We had planned to deliver the application as per below schedule. We are running out of time as per our planned schedule. Lets take care of it a bit urgently. Let us know if we could help you. We will love to assist.<br><br>";
	    				
	    				splitArray = projectArray[i].split("@");
	    				splitArray1 = projectArray1[i].split("@");
	    				

	        			logProjectDeliveryAutoEmailer.debug("**************Project Delivery Notification for : "+splitArray[1]+"="+splitArray[0]+"****************");
	    				String combinememid="";
		    			for(int k=1;k<splitArray1.length;k++)
		    			{
		    				if(splitArray1[k].equals("") || splitArray1[k].equals("NULL") || splitArray1[k].equals("null"))
		    				{
		    					continue;
		    				}

		    				else
		    				{
		    					combinememid=combinememid.concat(splitArray1[k]+",");
		    				}
		    				//ActionModule.ConsolPrint("combinememid1: "+combinememid,"");
		    			}// 2nd for end
		    			
		    			String useridresult="";
		    			if(!combinememid.equals(""))
		    			{
		    				combinememid=combinememid.substring(0, combinememid.length()-1);
			    			//ActionModule.ConsolPrint("combinememid: "+combinememid,"");
			    			String getuserid="select email from users where username in(select userId from sqaMemberList where mId in("+combinememid+"))";
			    			
			    			logProjectDeliveryAutoEmailer.debug("getuserid: "+getuserid);
			    			useridresult=ActionModule.MysqlConnectionAction(getuserid, VariableModule.Ownconn, ",");
			    			
			    			if(!useridresult.equals(""))
			    			{
				    			useridresult = useridresult.replace("\n", ",");
				    			useridresult = useridresult.replace(";", ",");
				    			useridresult=useridresult.substring(0, useridresult.length()-1);
				    			
				    			logProjectDeliveryAutoEmailer.debug("useridresult1: "+useridresult);
			    			}
		    			}

		    			
	    				tableHeader=tableHeader+"<b>Project Name: </b><b style='color: green;'>"+splitArray[1]+"</b><br>";
	    				tableHeader=tableHeader+"<b>Project Manager: </b>"+splitArray[3]+"<br>";
	    				
	    				
	        			if(splitArray1.length==6)
	        			{
		        			String zone="select memberName from sqaMemberList where mId="+splitArray1[5];
		        			//ActionModule.ConsolPrint("Zone Query: "+zone,"");
		        			String zoneresult=ActionModule.MysqlConnectionAction(zone, VariableModule.Ownconn, "#");
		        			//ActionModule.writing("Zone: "+zoneresult);
		        			//ActionModule.ConsolPrint("Zone: "+zoneresult,"");
		        			
		        			tableHeader=tableHeader+"<b>Sales Zone     : </b>"+zoneresult+"<br>";
	        			}
	        			else
	        			{
	        				tableHeader=tableHeader+"<b>Sales Zone     : </b><br>";
	        			}
	        			tableHeader=tableHeader+"<br><b>Approximate Development Start Date: </b>"+splitArray[4];
	        			tableHeader=tableHeader+"<br><b>Approximate QA Delivery Date: </b><b style='color: red;'>"+splitArray[5]+"</b>";
	        			tableHeader=tableHeader+"<br><b>Approximate Client Delivery Date: </b>"+splitArray[6];
	        			tableHeader=tableHeader+"<br><b>Approximate Phase Closing Date: </b>"+splitArray[7]+"<br><br><br>Regards<br>REVE SQA</body></html>";
	    			
	        			//useridresult
	        			EmailModule.sendEmailWithAttachments(useridresult, subject+splitArray[1]+" getting close", tableHeader,"6");
	    			}
    			}
    			
    		}catch(Exception e3) {
    			logProjectDeliveryAutoEmailer.debug("Project Delivery Emailer Module Exception:"+e3);
    				
    				e3.printStackTrace();
    			}finally {
    				logProjectDeliveryAutoEmailer.debug("Project Delivery Emailer Module is going to sleep for "+VariableModule.sleepTime+" minute");
	    			
	    			
	    			try {
	    				Thread.sleep(Long.parseLong(VariableModule.sleepTime)*60000); // 22 hour=79200000 waiting time
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}//finally block end
    		} //hourTime if end
    		
    		logProjectDeliveryAutoEmailer.debug("Project Delivery Emailer Module is going to sleep for 5 minute");
			
			//ActionModule.ConsolPrint("Current Thread: "+Thread.currentThread().getName());
			try {
				Thread.sleep(300000);  // 5 min waiting time
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
}