package MyVariable;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

//import com.sun.corba.se.spi.orbutil.fsm.Action;

import MyAction.ActionModule;
import MyEmail.EmailModule;

public class TodayBugSummaryAutoEmailer extends Thread {
	static Logger logTodayBugSummaryAutoEmailer = Logger.getLogger(TodayBugSummaryAutoEmailer.class.getName());

	@Override
    public void run() {
    	while(true) {

    			mailFunction();

    		//ActionModule.ConsolPrint("Query: ");
    	}
    }
    
	@SuppressWarnings("null")
	public void mailFunction()
	{

			logTodayBugSummaryAutoEmailer.debug("Today Bug Summary Emailer Module is going to start");
			
			String[] splitArray=null;
			String subject="QA Summary Report";
    		String hourTime=ActionModule.getCurrentHour();
    		logTodayBugSummaryAutoEmailer.debug("Time: "+hourTime);
    		
    		if(hourTime.equals(VariableModule.summarymailtime))
    		{
    			try {
    			String queryformem="select taggedProjectId,androidMembers,iosMembers,webMembers,erpMembers,salesMembers from sqaTaggedMembers where taggedProjectId in(select distinct(projectId) from sqaBugHistory where lastActivityDate between  '"+ActionModule.getDate()+" 00:00:00"+"' AND  '"+ActionModule.getDate()+" "+(Integer.parseInt(VariableModule.summarymailtime)-1)+":59:59"+"' AND projectId in(select projectId from sqaProjectBoard where isMailSend=1) and modifiedBy in(select username from users where role like 'qa%'))";
    			logTodayBugSummaryAutoEmailer.debug("Query: "+queryformem);
    			String qeuryresult=ActionModule.MysqlConnectionAction(queryformem, VariableModule.Ownconn, "@");
    			logTodayBugSummaryAutoEmailer.debug("Query Result: "+qeuryresult);
    			
    			String[] resultArray = qeuryresult.split("\n");
    			for(int i=0;i<resultArray.length;i++)
    			{
    				//ActionModule.ConsolPrint("resultArray: "+resultArray[i],"");
    				splitArray = resultArray[i].split("@");
    				
	    			String projectname="select name,project_category from projects where id="+splitArray[0];
	    			//ActionModule.ConsolPrint("projectname: "+projectname,"");
	    			String projectresult=ActionModule.MysqlConnectionAction(projectname, VariableModule.Ownconn, ",");
	    			projectresult=projectresult.substring(0, projectresult.length()-1);
	    			//ActionModule.ConsolPrint("projectresult: "+projectresult,"");
	    			String[] projectArray = projectresult.split(",");
	    			//ActionModule.ConsolPrint("projectArray0: "+projectArray[0],"");
	    			//ActionModule.ConsolPrint("projectArray1: "+projectArray[1],"");
	    			//ActionModule.writing("projectresult: "+projectresult);
	    			logTodayBugSummaryAutoEmailer.debug("\n********************"+projectArray[0]+"="+splitArray[0]+"******************\n");
	    			
	    			
    				String combinememid="";
	    			for(int k=1;k<splitArray.length;k++)
	    			{
	    				if(splitArray[k].equals("") || splitArray[k].equals("NULL") || splitArray[k].equals("null"))
	    				{
	    					continue;
	    				}

	    				else
	    				{
	    					combinememid=combinememid.concat(splitArray[k]+",");
	    				}
	    				//ActionModule.ConsolPrint("combinememid1: "+combinememid,"");
	    			}// 2nd for end
	    			
	    			String useridresult="";
	    			if(!combinememid.equals(""))
	    			{
		    			combinememid=combinememid.substring(0, combinememid.length()-1);
		    			//ActionModule.ConsolPrint("combinememid: "+combinememid,"");
		    			String getuserid="select email from users where username in(select userId from sqaMemberList where mId in("+combinememid+"))";
		    			logTodayBugSummaryAutoEmailer.debug("getuserid: "+getuserid);
		    			useridresult=ActionModule.MysqlConnectionAction(getuserid, VariableModule.Ownconn, ",");
		    			
		    			if(!useridresult.equals(""))
		    			{
			    			useridresult = useridresult.replace("\n", ",");
			    			useridresult = useridresult.replace(";", ",");
			    			useridresult=useridresult.substring(0, useridresult.length()-1);
			    			logTodayBugSummaryAutoEmailer.debug("useridresult1: "+useridresult);
		    			}
	    			}

	    			if(!projectArray[1].equals("3"))
	    			{ //Not Mobile Application
	    				
		    			String getsummaryquery="SELECT SUM(CASE WHEN `qaStatus` = 'New_Issue'  THEN 1 ELSE 0 END) as NewIssue,SUM(CASE WHEN `qaStatus` != 'New_Issue' and `qaStatus` != 'please_verify' THEN 1 ELSE 0 END) as VerifiedbyQA,SUM(CASE WHEN `devStatus` = 'Solved' THEN 1 ELSE 0 END) as SolvedbyDev FROM sqaBug WHERE isDeleted = 0 AND projectId = "+splitArray[0]+" and lastModificationDate between '"+ActionModule.getDate()+" 00:00:00"+"' AND  '"+ActionModule.getDate()+" "+(Integer.parseInt(VariableModule.summarymailtime))+":59:59'";
		    			//ActionModule.ConsolPrint("getsummaryquery: "+getsummaryquery,"");
		    			String summaryresult=ActionModule.MysqlConnectionAction(getsummaryquery, VariableModule.Ownconn, ",");
		    			logTodayBugSummaryAutoEmailer.debug("summaryresult: for "+projectArray[1]+" : "+summaryresult);
		    			summaryresult=summaryresult.substring(0, summaryresult.length()-1);
		    			String[] SummaryArray = summaryresult.split(",");

		    			
		    			String getactivebugquery="SELECT COUNT(*) FROM sqaBug WHERE isDeleted= 0 AND projectId = "+splitArray[0]+" AND `qaStatus` NOT IN('Solved', 'Closed')";
		    			//ActionModule.ConsolPrint("getactivebugquery: "+getactivebugquery,"");
		    			String activebugresult=ActionModule.MysqlConnectionAction(getactivebugquery, VariableModule.Ownconn, ",");
		    			activebugresult=activebugresult.substring(0, activebugresult.length()-1);
		    			//ActionModule.ConsolPrint("activebugresult: "+activebugresult,"");
		    			
		    			//ActionModule.ConsolPrint("SummaryArray[7]: "+Integer.parseInt(SummaryArray[7]));
		    			//String mailformat="Dear Concerns,<br><br>We have reported few points under below project at SQA portal. Please check and change the status of each bug.<br>Project Name: '<b style='color: green;'>"+projectArray[0]+"</b>'<br><br>Today's Summary:<br><table border=\"1\" style=\"text-align:center\"><thead style='background-color: "+VariableModule.ColorCode+";'><tr><th>High</th><th>Medium</th><th>Low</th><th>Today's Total Modified Bug(QA)</th><th>Today's Total Solved Bug(Developer)</th><th>Total Active Bug</th></tr></thead><tbody><tr><td>"+SummaryArray[2]+"</td><td>"+SummaryArray[3]+"</td><td>"+SummaryArray[4]+"</td><td>"+SummaryArray[1]+"</td><td>"+solved+"</td><td style='color: red;'><b>"+activebugresult+"</b></td></tr></tbody></table><br><br>Thanks With Regards---<br>SQA Auto Emailer";
		    			String mail = "<html>" + "<body style='font-family:"+VariableModule.FontFamily+";font-size:"+VariableModule.FontSize+"px;'>"
		    					+ "<style>"
		    					+ "html {font-family:"+VariableModule.FontFamily+";font-size:"+VariableModule.FontSize+"px;} "
		    	                + "table, td, th {border: 1px solid #000;text-align: center;font-family:"+VariableModule.FontFamily+";font-size:"+VariableModule.FontSize+"px;} "
		    	                + "table {border-collapse: collapse;width: 100%;}	th, td {padding: 15px;} </style>"
		    	                + "Dear Team,<br><br>We have reported few points under below project at SQA portal. We would like to draw your kind attention to check it at your earliest convenient and change the bug status.<br><br>Project Name: <b style='color: green;'>"+projectArray[0]+"</b><br><br>Today's Summary:<br><br><table border=\"1\" style=\"text-align:center\"><thead style='background-color: "+VariableModule.ColorCode+";'><tr><th>Total New Reported Bugs</th><th>Total Verified Bugs by QA</th><th>Total Solved Bugs by Developer</th><th>Total Active Bugs Till Now</th></tr></thead><tbody><tr><td>"+SummaryArray[0]+"</td><td>"+SummaryArray[1]+"</td><td>"+SummaryArray[2]+"</td><td style='color: red;'><b>"+activebugresult+"</b></td></tr></tbody></table><br><br>Regards<br>REVE SQA"
		    	                + "</body> </html>";
		    			//ActionModule.ConsolPrint("mail3: "+mail);
		    			//ActionModule.ConsolPrint("mailformat: "+mailformat); useridresult

		    				EmailModule.sendEmailWithAttachments(useridresult, subject+" on "+projectArray[0]+" of "+ActionModule.subjectDate(), mail,"1");
		    			
		    		}//Not Mobile Application if end
	    			else
	    			{ //Mobile Application
	    				String AndroidActiveBug = "";
	    				String iOSActiveBug = "";
		    			String getsummaryquery="SELECT os,SUM(CASE WHEN `qaStatus` = 'New_Issue'  THEN 1 ELSE 0 END) as NewIssue,SUM(CASE WHEN `qaStatus` != 'New_Issue' and `qaStatus` != 'please_verify' THEN 1 ELSE 0 END) as VerifiedbyQA,SUM(CASE WHEN `devStatus` = 'Solved' THEN 1 ELSE 0 END) as SolvedbyDev FROM sqaBug WHERE isDeleted = 0 AND projectId = "+splitArray[0]+" and lastModificationDate between '"+ActionModule.getDate()+" 00:00:00"+"' AND  '"+ActionModule.getDate()+" "+(Integer.parseInt(VariableModule.summarymailtime))+":59:59' group by os";
		    			//ActionModule.ConsolPrint("Project Name: "+splitArray[0]);
		    			//ActionModule.ConsolPrint("getsummaryquery: "+getsummaryquery,"");
		    			String summaryresult=ActionModule.MysqlConnectionAction(getsummaryquery, VariableModule.Ownconn, ",");
		    			//logTodayBugSummaryAutoEmailer.debug("summaryresult: for "+projectArray[1]+" : "+summaryresult);
		    			//ActionModule.ConsolPrint("summaryresult: for "+projectArray[1]+" : "+summaryresult,"");
		    			//ActionModule.ConsolPrint("summaryresult: for "+projectArray[1]+" : "+summaryresult,"");
		    			summaryresult=summaryresult.substring(0, summaryresult.length()-1);
		    			//ActionModule.ConsolPrint("summaryresult: "+summaryresult);
		    			//summaryresult = summaryresult.replace("\n", "$");
		    			//ActionModule.ConsolPrint("summaryresult: "+summaryresult);
		    			String[] SummaryArrayos = summaryresult.split("\n");
		    			//ActionModule.ConsolPrint("SummaryArrayos.length: "+SummaryArrayos.length);
		    			if(SummaryArrayos.length==2)
		    			{
		    				String[] activebugoswise = null;
			    			String[] SummaryArray = SummaryArrayos[0].split(",");
			    			//ActionModule.ConsolPrint("SummaryArrayos[1]: "+SummaryArrayos[1]);
			    			String[] SummaryArray1 = SummaryArrayos[1].split(",");
			    			//int solved=Integer.parseInt(SummaryArray[5])+Integer.parseInt(SummaryArray[6])+Integer.parseInt(SummaryArray[7]);
			    			//int solved1=Integer.parseInt(SummaryArray1[5])+Integer.parseInt(SummaryArray1[6])+Integer.parseInt(SummaryArray1[7]);
			    			//ActionModule.ConsolPrint("solved: "+solved);

			    			
			    			String getactivebugquery="SELECT os,COUNT(*) FROM sqaBug WHERE isDeleted= 0 AND projectId = "+splitArray[0]+" AND `qaStatus` NOT IN('Solved', 'Closed') group by os";
			    			logTodayBugSummaryAutoEmailer.debug("getactivebugquery: "+getactivebugquery);
			    			
			    			String activebugresult=ActionModule.MysqlConnectionAction(getactivebugquery, VariableModule.Ownconn, ",");
			    			logTodayBugSummaryAutoEmailer.debug("activebugresult: "+activebugresult);
			    			
			    			//activebugresult="iOS,9\n";
			    			if(activebugresult.equals(""))
			    			{
			    				AndroidActiveBug="0";
			    				iOSActiveBug="0";
			    			}
			    			else
			    			{
			    				int count = StringUtils.countMatches(activebugresult, ",");
				    			if(count==1)
				    			{
					    			activebugresult=activebugresult.substring(0, activebugresult.length()-1);
					    			activebugoswise = activebugresult.split(",");
					    			logTodayBugSummaryAutoEmailer.debug("count: "+count);
					    			
					    			if(activebugoswise[0].equals("android"))
					    			{
					    				AndroidActiveBug=activebugoswise[1];
					    				iOSActiveBug="0";
					    			}
					    			else
					    			{
					    				AndroidActiveBug="0";
					    				iOSActiveBug=activebugoswise[1];
					    			}
				    			}
				    			else
				    			{
					    			activebugresult=activebugresult.substring(0, activebugresult.length()-1);
					    			activebugoswise = activebugresult.split("\n");
					    			logTodayBugSummaryAutoEmailer.debug("count: "+count);
					    			
					    			for(int t=0;t<activebugoswise.length;t++)
					    			{
					    				String[] activebugoswise1 = activebugoswise[t].split(",");
						    			if(activebugoswise1[0].equals("android"))
						    			{
						    				AndroidActiveBug=activebugoswise1[1];
						    				logTodayBugSummaryAutoEmailer.debug("AndroidActiveBug: "+AndroidActiveBug);
							    			
						    				
						    			}
						    			else
						    			{
						    				
						    				iOSActiveBug=activebugoswise1[1];
						    				logTodayBugSummaryAutoEmailer.debug("iOSActiveBug: "+iOSActiveBug);
							    			
						    			}
					    			}
				    			}
			    			}
			    			if(SummaryArray[0].equals("android"))
			    			{
			    				SummaryArray[0]="Android";
			    			}
			    			
			    			if(SummaryArray1[0].equals("android"))
			    			{
			    				SummaryArray1[0]="Android";
			    			}
			    			
			    			//String mailformat="Dear Concerns,<br><br>We have reported few points under below project at SQA portal. Please check and change the status of each bug.<br>Project Name: '<b style='color: green;'>"+projectArray[0]+"</b>'<br><br>Today's Summary:<br><table style='text-align:center;border-width: 1px;border-style: solid;border-color: #729ea5;'><thead style='background-color: "+VariableModule.ColorCode+";'><tr><th>OS Type</th><th>High</th><th>Medium</th><th>Low</th><th>Today's Total Modified Bug(QA)</th><th>Today's Total Solved Bug(Developer)</th><th>Total Active Bug</th></tr></thead><tbody><tr><td>"+SummaryArray[0]+"</td><td>"+SummaryArray[2]+"</td><td>"+SummaryArray[3]+"</td><td>"+SummaryArray[4]+"</td><td>"+SummaryArray[1]+"</td><td>"+solved+"</td><td style='color: red;'><b>"+activebugoswise[0]+"</b></td></tr><tr><td>"+SummaryArray1[0]+"</td><td>"+SummaryArray1[2]+"</td><td>"+SummaryArray1[3]+"</td><td>"+SummaryArray1[4]+"</td><td>"+SummaryArray1[1]+"</td><td>"+solved1+"</td><td style='color: red;'><b>"+activebugoswise[1]+"</b></td></tr></tbody></table><br><br>Thanks With Regards---<br>SQA Auto Emailer";
			    			String mail = "<html>" + "<body style='font-family:"+VariableModule.FontFamily+";font-size:"+VariableModule.FontSize+"px;'>"
			    					+ "<style>"
			    					+ "html {font-family:"+VariableModule.FontFamily+";font-size:"+VariableModule.FontSize+"px;} "
			    	                + "table, td, th {border: 1px solid #000;text-align: center;font-family:Calibri;} "
			    	                + "table {border-collapse: collapse;width: 100%;}	th, td {padding: 15px;} </style>"
			    	                + "Dear Team,<br><br>We have reported few points under below project at SQA portal. We would like to draw your kind attention to check it at your earliest convenient and change the bug status.<br><br>Project Name: <b style='color: green;'>"+projectArray[0]+"</b><br><br>Today's Summary:<br><br><table style='text-align:center;border-width: 1px;border-style: solid;border-color: #729ea5;'><thead style='background-color: "+VariableModule.ColorCode+";'><tr><th>OS Type</th><th>Total New Reported Bugs</th><th>Total Verified Bugs by QA</th><th>Total Solved Bugs by Developer</th><th>Total Active Bugs Till Now</th></tr></thead><tbody><tr><td>"+SummaryArray[0]+"</td><td>"+SummaryArray[1]+"</td><td>"+SummaryArray[2]+"</td><td>"+SummaryArray[3]+"</td><td style='color: red;'><b>"+AndroidActiveBug+"</b></td></tr><tr><td>"+SummaryArray1[0]+"</td><td>"+SummaryArray1[1]+"</td><td>"+SummaryArray1[2]+"</td><td>"+SummaryArray1[3]+"</td><td style='color: red;'><b>"+iOSActiveBug+"</b></td></tr></tbody></table><br><br>Regards<br>REVE SQA"		
			    	                + "</body> </html>";
			    			//ActionModule.ConsolPrint("mail2: "+mail);
			    			//ActionModule.ConsolPrint("mailformat: "+mailformat); useridresult

			    				EmailModule.sendEmailWithAttachments(useridresult, subject+" on "+projectArray[0]+" of "+ActionModule.subjectDate(), mail,"1");
			    			
		    			} // SummaryArrayos.length==2 if end
		    			else
		    			{
		    				String[] activebugoswise=null;
			    			String[] SummaryArray = SummaryArrayos[0].split(",");
			    			//ActionModule.ConsolPrint("SummaryArrayos[1]: "+SummaryArrayos[1]);
			    			//int solved=Integer.parseInt(SummaryArray[5])+Integer.parseInt(SummaryArray[6])+Integer.parseInt(SummaryArray[7]);
			    			//ActionModule.ConsolPrint("solved: "+solved);

			    			
			    			String getactivebugquery="SELECT COUNT(*) FROM sqaBug WHERE isDeleted= 0 AND projectId = "+splitArray[0]+" AND `qaStatus` NOT IN('Solved', 'Closed') and os='"+SummaryArray[0]+"'";
			    			//ActionModule.ConsolPrint("getactivebugquery: "+getactivebugquery);
			    			String activebugresult=ActionModule.MysqlConnectionAction(getactivebugquery, VariableModule.Ownconn, ",");
			    			//activebugresult=activebugresult.substring(0, activebugresult.length()-1);
			    			logTodayBugSummaryAutoEmailer.debug("AndroidActiveBug1: "+activebugresult);
			    			
			    			if(activebugresult.equals(""))
			    			{
			    				activebugoswise[0]="0";
			    				
			    			}
			    			else
			    			{
			    				activebugoswise = activebugresult.split("\n");
			    				//activebugoswise[0]=activebugresult.substring(0, activebugresult.length()-1);
			    			}
			    			
			    			logTodayBugSummaryAutoEmailer.debug("activebugoswise[0]: "+activebugoswise[0]);
			    			
			    			if(SummaryArray[0].equals("android"))
			    			{
			    				SummaryArray[0]="Android";
			    			}

			    			
			    			//String mailformat="Dear Concerns,<br><br>We have reported few points under below project at SQA portal. Please check and change the status of each bug.<br>Project Name: '<b style='color: green;'>"+projectArray[0]+"</b>'<br><br>Today's Summary:<br><table style='text-align:center;border-width: 1px;border-style: solid;border-color: #729ea5;'><thead style='background-color: "+VariableModule.ColorCode+";'><tr><th>OS Type</th><th>High</th><th>Medium</th><th>Low</th><th>Today's Total Modified Bug(QA)</th><th>Today's Total Solved Bug(Developer)</th><th>Total Active Bug</th></tr></thead><tbody><tr><td>"+SummaryArray[0]+"</td><td>"+SummaryArray[2]+"</td><td>"+SummaryArray[3]+"</td><td>"+SummaryArray[4]+"</td><td>"+SummaryArray[1]+"</td><td>"+solved+"</td><td style='color: red;'><b>"+activebugoswise[0]+"</b></td></tr><tr><td>"+SummaryArray1[0]+"</td><td>"+SummaryArray1[2]+"</td><td>"+SummaryArray1[3]+"</td><td>"+SummaryArray1[4]+"</td><td>"+SummaryArray1[1]+"</td><td>"+solved1+"</td><td style='color: red;'><b>"+activebugoswise[1]+"</b></td></tr></tbody></table><br><br>Thanks With Regards---<br>SQA Auto Emailer";
			    			String mail = "<html>" + "<body style='font-family:"+VariableModule.FontFamily+";font-size:"+VariableModule.FontSize+"px;'>"
			    					+ "<style>"
			    					//+ "html {font-family:"+VariableModule.FontFamily+";font-size:"+VariableModule.FontSize+"px;} "
			    	                + "table, td, th {border: 1px solid #000;text-align: center; } "
			    	                + "table {border-collapse: collapse;width: 100%;}	th, td {padding: 15px;} </style>"
			    	                + "Dear Team,<br><br>We have reported few points under below project at SQA portal. We would like to draw your kind attention to check it at your earliest convenient and change the bug status.<br><br>Project Name: <b style='color: green;'>"+projectArray[0]+"</b><br><br>Today's Summary:<br><br><table style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px;text-align:center;border-width: 1px;border-style: solid;border-color: #729ea5;'><thead style='background-color: "+VariableModule.ColorCode+";'><tr><th>OS Type</th><th>Total New Reported Bugs</th><th>Total Verified Bugs by QA</th><th>Total Solved Bugs by Developer</th><th>Total Active Bugs Till Now</th></tr></thead><tbody><tr><td>"+SummaryArray[0]+"</td><td>"+SummaryArray[1]+"</td><td>"+SummaryArray[2]+"</td><td>"+SummaryArray[3]+"</td><td style='color: red;'><b>"+activebugoswise[0]+"</b></td></tr></tbody></table><br><br>Regards<br>REVE SQA"		
			    	                + "</body> </html>";
			    			//ActionModule.ConsolPrint("mail1: "+mail);
			    			//ActionModule.ConsolPrint("mailformat: "+mailformat); useridresult
			    			EmailModule.sendEmailWithAttachments(useridresult, subject+" on "+projectArray[0]+" of "+ActionModule.subjectDate(), mail,"1");
		    			
		    			} //SummaryArrayos.length!=2 else end
	    			}//Mobile Application else end
    			
    		} //1st for end
    			
    		} catch (MessagingException e) { //try end
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			catch(Exception e3) {
    				logTodayBugSummaryAutoEmailer.debug("Today Bug Summary Emailer Module Exception:"+e3);
    				
    				e3.printStackTrace();
    			}finally {
    				logTodayBugSummaryAutoEmailer.debug("Today Bug Summary Emailer Module is going to sleep for "+VariableModule.sleepTime+" minute");
	    			
	    			
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
    		
    		logTodayBugSummaryAutoEmailer.debug("Today Bug Summary Emailer Module is going to sleep for 5 minute");
			
			//ActionModule.ConsolPrint("Current Thread: "+Thread.currentThread().getName());
			try {
				Thread.sleep(300000);  // 5 min waiting time
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
}