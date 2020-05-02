package MyVariable;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

//import com.sun.corba.se.spi.orbutil.fsm.Action;

import MyAction.ActionModule;
import MyEmail.EmailModule;

public class ProjectCreationAutoEmailer extends Thread {
	static Logger logProjectCreationAutoEmailer = Logger.getLogger(ProjectCreationAutoEmailer.class.getName());

	@Override
    public void run() {
    	while(true) {

    			mailFunction();

    		//ActionModule.ConsolPrint("Query: ");
    	}
    }

	public void mailFunction()
	{

			logProjectCreationAutoEmailer.debug("Project Creation Emailer Module is going to start");
			
			
			
			String[] splitArray=null;
			String[] splitArray1=null;
			String subject="Project Team Orientation and Planning of ";
    		String hourTime=ActionModule.getCurrentHour();
    		logProjectCreationAutoEmailer.debug("Time: "+hourTime);
    		
    		if(hourTime.equals(VariableModule.summarymailtime))
    		{
    			try {
    			
    			String queryformem="select projectId,projectName,projectType,projectManager,startDate,pdtDeliveryDate,salesDeliveryDate,phaseClosingDate from sqaMemberControl where creationDate>'"+ActionModule.getDate()+" 00:00:00' order by projectId";
    			
    			logProjectCreationAutoEmailer.debug("Query: "+queryformem);
    			String qeuryresult=ActionModule.MysqlConnectionAction(queryformem, VariableModule.Ownconn, "@");
    			logProjectCreationAutoEmailer.debug("Query Result: "+qeuryresult);
    			
    			if(!qeuryresult.equals(""))
    			{
    				qeuryresult=qeuryresult.substring(0, qeuryresult.length()-1);
	    			String[] resultArray = qeuryresult.split("\n");
	    			
        			String queryformem1="select taggedProjectId,androidMembers,iosMembers,webMembers,erpMembers,salesMembers from sqaTaggedMembers where taggedProjectId in(select projectId from sqaMemberControl where creationDate>'"+ActionModule.getDate()+" 00:00:00') order by taggedProjectId";
        			
        			logProjectCreationAutoEmailer.debug("Query1: "+queryformem1);
        			String qeuryresult1=ActionModule.MysqlConnectionAction(queryformem1, VariableModule.Ownconn, "@");
        			logProjectCreationAutoEmailer.debug("Query Result1: "+qeuryresult1);
        			
        			qeuryresult1=qeuryresult1.substring(0, qeuryresult1.length()-1);
        			String[] resultArray1 = qeuryresult1.split("\n");
        			
	    			for(int i=0;i<resultArray.length;i++)
	    			{
	    				

	    				splitArray = resultArray[i].split("@");
	    				splitArray1 = resultArray1[i].split("@");
	    				
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
			    			
			    			logProjectCreationAutoEmailer.debug("getuserid: "+getuserid);
			    			useridresult=ActionModule.MysqlConnectionAction(getuserid, VariableModule.Ownconn, ",");
			    			
			    			if(!useridresult.equals(""))
			    			{
				    			useridresult = useridresult.replace("\n", ",");
				    			useridresult = useridresult.replace(";", ",");
				    			useridresult=useridresult.substring(0, useridresult.length()-1);
				    			
				    			logProjectCreationAutoEmailer.debug("useridresult1: "+useridresult);
			    			}
		    			}
	    				if(!splitArray[2].equals("3"))
	    				{
		    				String tableHeader="<html><body style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px;'>Dear Team <br>We are planing to work on a new project. Below colleagues will take care of it and we need to deliver it as per mentioned schedule.<br><br>";
		        			String tabledata="<table style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px;border-collapse: collapse; border: 1px solid #000; text-align: center;width:100%;margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";height:40px;'><tr><th>Server Members</th><th style='border: 1px solid #000;'>Assigned QA Members</th></tr></thead><tbody><tr><td style='border: 1px solid #000;'>";
		    				
		        			logProjectCreationAutoEmailer.debug("**************Project Creation Notification for : "+splitArray[1]+"="+splitArray[0]+"****************");
		        			
		        			tableHeader=tableHeader+"<b>Project Name   : </b><b style='color: green;'>"+splitArray[1]+"</b><br>";
		        			tableHeader=tableHeader+"<b>Project Manager: </b>"+splitArray[3]+"<br>";
		        			//ActionModule.ConsolPrint("splitArray1 Length: "+splitArray1.length,"");
		        			
		        			String getname="select memberName from sqaMemberList where mId in("+combinememid+")";

		        			//ActionModule.ConsolPrint("Server Name Query: "+getname,"");
		        			String nameresult=ActionModule.MysqlConnectionAction(getname, VariableModule.Ownconn, ",");
		        			nameresult = nameresult.replace("\n", ",");
		        			logProjectCreationAutoEmailer.debug("Server Name: "+nameresult);
		        			
		        			
		        			
		        			if(splitArray1.length==6)
		        			{
			        			String zone="select memberName from sqaMemberList where mId="+splitArray1[5];
			        			//ActionModule.ConsolPrint("Zone Query: "+zone,"");
			        			String zoneresult=ActionModule.MysqlConnectionAction(zone, VariableModule.Ownconn, "#");
			        			//ActionModule.writing("Zone: "+zoneresult);
			        			//ActionModule.ConsolPrint("Zone: "+zoneresult,"");
			        			
			        			tableHeader=tableHeader+"<b>Sales Zone     : </b>"+zoneresult+"<br><br>Project Members:<br>";
		        			}
		        			else
		        			{
		        				tableHeader=tableHeader+"<b>Sales Zone     : </b><br><br>Project Members:<br>";
		        			}
		        			tabledata=tabledata+"<table style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; text-align: center;width:100%;margin: auto;'>";
		        			
		        			if(nameresult.indexOf(",") != -1)
		        			{
		        				String[] userID = nameresult.split(",");
		        				//ActionModule.ConsolPrint("User Length: "+userID.length,"");
		        				for(int r=0;r<userID.length;r++)
		        				{
		        					tabledata=tabledata+"<tr><td style='border: 1px solid #000;width:250px;'>"+userID[r]+"</td></tr>";
		        				}
		        			}
		        			else
		        			{
		        				tabledata=tabledata+"<tr><td style='border: 1px solid #000;width:250px;'>"+nameresult+"</td></tr>";
		        			}
		        			
		        			String qauser="select full_name from users where username in(select assignee from sqaAssignTaskMobile where projectId="+splitArray[0]+")";
		        			//ActionModule.ConsolPrint("QA Name Query: "+qauser,"");
		        			String qaresult=ActionModule.MysqlConnectionAction(qauser, VariableModule.Ownconn, "#");
		        			logProjectCreationAutoEmailer.debug("QA User: "+qaresult);
		        			
		        			
		        			if(!qaresult.equals(""))
		        			{
		        				tabledata=tabledata+"</table><td style='border: 1px solid #000;width:250px;'>"+qaresult+"</td></tr></tbody></table>";
		        			}
		        			else
		        			{
		        				tabledata=tabledata+"</table><td>QA Member is not assigned yet</td></tr></tbody></table>";
		        			}
		        			
		        			//tabledata=tabledata+"</table><td style='border: 1px solid #000;'>QA</td></tr></tbody></table>";
		        			tableHeader=tableHeader+tabledata;
		        			tableHeader=tableHeader+"<br><br><b>Approximate Development Start Date : </b>"+splitArray[4]+"<br>";
		        			tableHeader=tableHeader+"<b>Approximate QA Delivery Date       : </b>"+splitArray[5]+"<br>";
		        			tableHeader=tableHeader+"<b>Approximate Client Delivery Date   : </b>"+splitArray[6]+"<br>";
		        			tableHeader=tableHeader+"<b>Approximate Phase Closing Date     : </b>"+splitArray[7]+"<br><br><br>Regards<br>REVE SQA\n";
		        			
		        			//ActionModule.ConsolPrint("Project Name: "+splitArray[1],"");
		        			//ActionModule.ConsolPrint("Project Manager: "+splitArray[3],"");
	
		    				//ActionModule.ConsolPrint("resultArray: "+resultArray[i],""); //useridresult
		
		        			EmailModule.sendEmailWithAttachments(useridresult, subject+splitArray[1]+" project", tableHeader,"4");

	    				}
	    				else
	    				{ //Mobile Application
		    				String tableHeader="<html><body style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px;'>Dear Team <br>We are planing to work on a new project. Below colleagues will take care of it and we need to deliver it as per mentioned schedule.<br><br>";
		        			String tabledata="<table style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px;border-collapse: collapse; border: 1px solid #000; text-align: center;width:100%;margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";height:40px;'><tr><th>Android Members</th><th style='border: 1px solid #000;'>iOS Members</th><th style='border: 1px solid #000;'>Assigned QA Members</th></tr></thead><tbody><tr><td style='border: 1px solid #000;'>";
		    				
		        			logProjectCreationAutoEmailer.debug("**************Project Creation Notification for : "+splitArray[1]+"="+splitArray[0]+"****************");
		        			
		        			tableHeader=tableHeader+"<b>Project Name   : </b><b style='color: green;'>"+splitArray[1]+"</b><br>";
		        			tableHeader=tableHeader+"<b>Project Manager: </b>"+splitArray[3]+"<br>";
		        			
		        			String getandroidname="select memberName from sqaMemberList where mId in("+splitArray1[1]+")";
		        			String getiosname="select memberName from sqaMemberList where mId in("+splitArray1[2]+")";
		        			//ActionModule.ConsolPrint("Android Name Query: "+getandroidname,"");
		        			//ActionModule.ConsolPrint("Android Name Query: "+getiosname,"");
		        			String androidnameresult=ActionModule.MysqlConnectionAction(getandroidname, VariableModule.Ownconn, "#");
		        			String iosnameresult=ActionModule.MysqlConnectionAction(getiosname, VariableModule.Ownconn, "#");
		        			androidnameresult = androidnameresult.replace("\n", "#");
		        			iosnameresult = iosnameresult.replace("\n", "#");
		        			
		        			logProjectCreationAutoEmailer.debug("Android Name: "+getandroidname);
		        			logProjectCreationAutoEmailer.debug("iOS Name: "+getiosname);
		        			
		        			if(splitArray1.length==6)
		        			{
			        			String zone="select memberName from sqaMemberList where mId="+splitArray1[5];
			        			//ActionModule.ConsolPrint("Zone Query: "+zone,"");
			        			String zoneresult=ActionModule.MysqlConnectionAction(zone, VariableModule.Ownconn, "#");
			        			//ActionModule.writing("Zone: "+zoneresult);
			        			//ActionModule.ConsolPrint("Zone: "+zoneresult,"");
			        			
			        			tableHeader=tableHeader+"<b>Sales Zone     : </b>"+zoneresult+"<br><br>Project Members:<br>";
		        			}
		        			else
		        			{
		        				tableHeader=tableHeader+"<b>Sales Zone     : </b><br><br>Project Members:<br>";
		        			}
		        			
		        			tabledata=tabledata+"<table style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; text-align: center;width:100%;margin: auto;'>";
		        			
		        			if(androidnameresult.indexOf("#") != -1)
		        			{
		        				String[] userandID = androidnameresult.split("#");
		        				//ActionModule.ConsolPrint("Android User Length: "+userandID.length,"");
		        				for(int r=0;r<userandID.length;r++)
		        				{
		        					tabledata=tabledata+"<tr><td style='border: 1px solid #000;width:250px;'>"+userandID[r]+"</td></tr>";
		        				}
		        			}
		        			else
		        			{
		        				tabledata=tabledata+"<tr><td style='border: 1px solid #000;width:250px;'>"+androidnameresult+"</td></tr>";
		        			}
		        			
		        			tabledata=tabledata+"</table></td><td style='border: 1px solid #000;'><table style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; text-align: center;width:100%;margin: auto;'>";
		        			
		        			if(iosnameresult.indexOf("#") != -1)
		        			{
		        				String[] useriosID = iosnameresult.split("#");
		        				//ActionModule.ConsolPrint("iOS User Length: "+useriosID.length,"");
		        				for(int r=0;r<useriosID.length;r++)
		        				{
		        					tabledata=tabledata+"<tr><td style='border: 1px solid #000;width:250px;'>"+useriosID[r]+"</td></tr>";
		        				}
		        			}
		        			else
		        			{
		        				tabledata=tabledata+"<tr><td style='border: 1px solid #000;width:250px;'>"+iosnameresult+"</td></tr>";
		        			}
		        			
		        			String qauser="select full_name from users where username in(select assignee from sqaAssignTaskMobile where projectId="+splitArray[0]+")";
		        			//ActionModule.ConsolPrint("QA Name Query: "+qauser,"");
		        			String qaresult=ActionModule.MysqlConnectionAction(qauser, VariableModule.Ownconn, "#");
		        			logProjectCreationAutoEmailer.debug("QA User: "+qaresult);
		        			
		        			
		        			if(!qaresult.equals(""))
		        			{
		        				tabledata=tabledata+"</table><td style='border: 1px solid #000;width:250px;'>"+qaresult+"</td></tr></tbody></table>";
		        			}
		        			else
		        			{
		        				tabledata=tabledata+"</table><td style='border: 1px solid #000;'>QA Member is not assigned yet</td></tr></tbody></table>";
		        			}
		        			
		        			//tabledata=tabledata+"</table></td><td style='border: 1px solid #000;'>QA</td></tr></tbody></table>";
		        			tableHeader=tableHeader+tabledata;
		        			tableHeader=tableHeader+"<br><br><b>Approximate Development Start Date : </b>"+splitArray[4]+"<br>";
		        			tableHeader=tableHeader+"<b>Approximate QA Delivery Date       : </b>"+splitArray[5]+"<br>";
		        			tableHeader=tableHeader+"<b>Approximate Client Delivery Date   : </b>"+splitArray[6]+"<br>";
		        			tableHeader=tableHeader+"<b>Approximate Phase Closing Date     : </b>"+splitArray[7]+"<br><br><br>Regards<br>REVE SQA\n";
		        			
		        			//ActionModule.ConsolPrint("Project Name: "+splitArray[1],"");
		        			//ActionModule.ConsolPrint("Project Manager: "+splitArray[3],"");
	
		    				//ActionModule.ConsolPrint("resultArray: "+resultArray[i],""); //useridresult
		
		        			EmailModule.sendEmailWithAttachments(useridresult, subject+splitArray[1]+" project", tableHeader,"4");

	    				}
	    			} //1st for end

        			//ActionModule.ConsolPrint("Mail Body: "+tableHeader,"\n");
        			
    		}
    			
    		}catch(Exception e3) {
    				logProjectCreationAutoEmailer.debug("Project Creation Emailer Module Exception:"+e3);
    				
    				e3.printStackTrace();
    			}finally {
    				logProjectCreationAutoEmailer.debug("Project Creation Emailer Module is going to sleep for "+VariableModule.sleepTime+" minute");
	    			
	    			
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
    		
    		logProjectCreationAutoEmailer.debug("Project Creation Emailer Module is going to sleep for 5 minute");
			
			//ActionModule.ConsolPrint("Current Thread: "+Thread.currentThread().getName());
			try {
				Thread.sleep(300000);  // 5 min waiting time
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
}