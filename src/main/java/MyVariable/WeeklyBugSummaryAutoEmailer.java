package MyVariable;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

//import com.sun.corba.se.spi.orbutil.fsm.Action;

import MyAction.ActionModule;
import MyEmail.EmailModule;

public class WeeklyBugSummaryAutoEmailer extends Thread {
	static Logger logWeeklyBugSummaryAutoEmailer = Logger.getLogger(WeeklyBugSummaryAutoEmailer.class.getName());

	@Override
    public void run() {
    	while(true) {

    			mailFunction();

    		//ActionModule.ConsolPrint("Query: ");
    	}
    }
    //SELECT os,bugTitle,priority FROM sqaBug WHERE projectId = 19 AND isDeleted = 0 AND (qaStatus != 'Solved' AND qaStatus !='Closed');
	@SuppressWarnings("null")
	public void mailFunction()
	{

		logWeeklyBugSummaryAutoEmailer.debug("Weekly Bug Summary Emailer Module is going to start");
		//ActionModule.ConsolPrint("Weekly Bug Summary Emailer Module is going to start","");
			
			
			String[] splitArray=null;
			String subject="Current Status of ";
    		String hourTime=ActionModule.getCurrentHour();
    		logWeeklyBugSummaryAutoEmailer.debug("Time: "+hourTime);
    		String dayofWeek=ActionModule.getDayofWeek(ActionModule.getDate());
    		if(dayofWeek.equals(VariableModule.WeeklyMailDay) && hourTime.equals(VariableModule.weeklysummarymailtime))
    		{
    			try {
    			String queryformem="select taggedProjectId,androidMembers,iosMembers,webMembers,erpMembers,salesMembers from sqaTaggedMembers where taggedProjectId in(select distinct(projectId) from sqaBug where qaStatus!='Solved' AND qaStatus!='Closed' AND isDeleted=0 and lastModificationDate between  '"+VariableModule.LastWeekDay+" "+VariableModule.weeklysummarymailtime+":00:00"+"' AND  '"+ActionModule.getDate()+" "+(Integer.parseInt(VariableModule.weeklysummarymailtime)-1)+":59:59"+"' AND projectId in(select projectId from sqaProjectBoard where isMailSend=1)) order by taggedProjectId";
    			logWeeklyBugSummaryAutoEmailer.debug("Query: "+queryformem);
    			String qeuryresult=ActionModule.MysqlConnectionAction(queryformem, VariableModule.Ownconn, "@");
    			logWeeklyBugSummaryAutoEmailer.debug("Query Result: "+qeuryresult);
    			
    			String[] resultArray = qeuryresult.split("\n");
    			logWeeklyBugSummaryAutoEmailer.debug("Total Project with Active Bugs: "+resultArray.length);
    			
    			for(int i=0;i<resultArray.length;i++)
    			{

    				//ActionModule.ConsolPrint("resultArray: "+resultArray[i],"");
    				splitArray = resultArray[i].split("@");
    				
	    			String projectname="select name,project_category from projects where id="+splitArray[0];
	    			//ActionModule.ConsolPrint("projectname: "+projectname,"");
	    			String projectresult=ActionModule.MysqlConnectionAction(projectname, VariableModule.Ownconn, ",");
	    			projectresult=projectresult.substring(0, projectresult.length()-1);
	    			String[] projectArray = projectresult.split(",");
	    			//logWeeklyBugSummaryAutoEmailer.debug("projectresult: "+projectresult);
	    			logWeeklyBugSummaryAutoEmailer.debug("\n********************"+projectArray[0]+"="+splitArray[0]+"******************\n");
	    			
	    			
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
		    			logWeeklyBugSummaryAutoEmailer.debug("getuserid: "+getuserid);
		    			useridresult=ActionModule.MysqlConnectionAction(getuserid, VariableModule.Ownconn, ",");
		    			//ActionModule.ConsolPrint("useridresult: "+useridresult,"");
		    			if(!useridresult.equals(""))
		    			{
			    			useridresult = useridresult.replace("\n", ",");
			    			useridresult = useridresult.replace(";", ",");
			    			useridresult=useridresult.substring(0, useridresult.length()-1);
			    			logWeeklyBugSummaryAutoEmailer.debug("useridresult1: "+useridresult);
		    			}
		    		}

	    			if(!projectArray[1].equals("3"))
	    			{ //Not Mobile Application
	    				int high=0;
	    				int medium=0;
	    				int low=0;
	    				
	    				String tableHeader="<html><body style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px;'>Dear Team <br>Thanks for your nice effort. We have reported few points throughout the entire week under below project at REVE SQA portal. Please find the priority wise bug status,Bug Trend and Active bug list(Today till now) in this mail. Please let us know for any further help. We will love to assist. Please login to the SQA portal and change the developer status of the corresponding Bug so that QA can verify it.<br><br>";
	    				String tableHeaderHigh="High Priority Issues: <br><table  style='border-collapse: collapse; font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; border: 1px solid #000; width:850px; margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";height:40px;'>\r\n" + 
	    						"    <tr><th style='border: 1px solid #000; width: 80px;'>Serial No.</th><th style='border: 1px solid #000; width: 100px;'>Bug ID</th><th style='border: 1px solid #000;'>Bug Title</th></tr></thead>\r\n" + 
	    						"    <tbody>";
	    				
	    				String tableHeaderMedium="<br><br><br>Medium Priority Issues: <br><table  style='border-collapse: collapse; font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; border: 1px solid #000; width:850px; margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";height:40px;'>\r\n" + 
	    						"    <tr><th style='border: 1px solid #000; width: 80px;'>Serial No.</th><th style='border: 1px solid #000; width: 100px;'>Bug ID</th><th style='border: 1px solid #000;'>Bug Title</th></tr></thead>\r\n" +  
	    						"    <tbody>";
	    				
	    				String tableHeaderLow="<br><br><br>Low Priority Issues: <br><table  style='border-collapse: collapse; font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; border: 1px solid #000; width:850px; margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";height:40px;'>\r\n" + 
	    						"    <tr><th style='border: 1px solid #000; width: 80px;'>Serial No.</th><th style='border: 1px solid #000; width: 100px;'>Bug ID</th><th style='border: 1px solid #000;'>Bug Title</th></tr></thead>\r\n" +  
	    						"    <tbody>";
	    				
	    				//SELECT bugTitle,priority FROM sqaBug WHERE isDeleted = 0 AND projectId = AND (qaStatus != 'Solved' AND qaStatus !='Closed');
		    			String getsummaryquery="SELECT bugId,bugTitle,priority FROM sqaBug WHERE isDeleted = 0 AND projectId = "+splitArray[0]+" AND (qaStatus != 'Solved' AND qaStatus !='Closed')";
		    			logWeeklyBugSummaryAutoEmailer.debug("getsummaryquery: "+getsummaryquery);
		    			String summaryresult=ActionModule.MysqlConnectionAction(getsummaryquery, VariableModule.Ownconn, "%");
		    			logWeeklyBugSummaryAutoEmailer.debug("summaryresult: for "+projectArray[1]+" : "+summaryresult);
		    			
		    			if(!summaryresult.equals(""))
		    			{
		    				summaryresult=summaryresult.substring(0, summaryresult.length()-1);
			    			String[] SummaryArray = summaryresult.split("\n");
			    			logWeeklyBugSummaryAutoEmailer.debug("Bug Length: "+SummaryArray.length);
			    			String ProjectName="<br><b>Project Name:</b> <b style='color: green;'>"+projectArray[0]+"</b><br><br>Weekly Summary Report:<br>";
			    			for(int r=0;r<SummaryArray.length;r++)
			    			{
			    				String[] SummaryArray1 = SummaryArray[r].split("%");
			    				if(SummaryArray1[2].equals("High"))
			    				{
			    					high++;
			    					tableHeaderHigh=tableHeaderHigh.concat("<tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+high+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+SummaryArray1[0]+"</td><td style='border: 1px solid #000; text-align: left;'>"+SummaryArray1[1]+"</td></tr>");
			    					
			    				}
			    				else if(SummaryArray1[2].equals("Medium"))
			    				{
			    					medium++;
			    					tableHeaderMedium=tableHeaderMedium.concat("<tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+medium+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+SummaryArray1[0]+"</td><td style='border: 1px solid #000; text-align: left;'>"+SummaryArray1[1]+"</td></tr>");
			    					
			    				}
			    				else
			    				{
			    					low++;
			    					tableHeaderLow=tableHeaderLow.concat("<tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+low+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+SummaryArray1[0]+"</td><td style='border: 1px solid #000; text-align: left;'>"+SummaryArray1[1]+"</td></tr>");
			    					
			    				}
			    			}
			    			String summarysection="<table  style='border-collapse: collapse; font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; border: 1px solid #000; width:850px; margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";'><tr><th style='border: 1px solid #000;'>High Priority</th><th style='border: 1px solid #000;'>Medium Priority</th><th style='border: 1px solid #000;'>Low Priority</th><th style='border: 1px solid #000;'>Total Active Bugs Till Now</th></tr></thead><tbody><tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+high+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+medium+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+low+"</td><td style='color: red; text-align: center;'><b>"+SummaryArray.length+"</b></td></tr></tbody></table><br><br>";
			    			
			    			logWeeklyBugSummaryAutoEmailer.debug("High Bug: "+high);
			    			logWeeklyBugSummaryAutoEmailer.debug("Medium Bug: "+medium);
			    			logWeeklyBugSummaryAutoEmailer.debug("Low Bug: "+low);
			    			
			    			tableHeaderHigh=tableHeaderHigh+"</tbody></table>";
			    			tableHeaderMedium=tableHeaderMedium+"</tbody></table>";
			    			tableHeaderLow=tableHeaderLow+"</tbody></table>";
			    			
			    			String mail=tableHeader+ProjectName+summarysection;
			    			if(high!=0)
			    			{
			    				mail=mail+tableHeaderHigh;
			    			}
			    			if(medium!=0)
			    			{
			    				mail=mail+tableHeaderMedium;
			    			}
			    			if(low!=0)
			    			{
			    				mail=mail+tableHeaderLow;
			    			}
			    			
			    			mail=mail+"<br><br>Regards<br>REVE SQA"; //useridresult
			    			
			    				EmailModule.sendEmailWithAttachments(useridresult, subject+projectArray[0]+" after the end of the last week", mail,"3");
			    			
		    			}
		    			else
		    				continue;

		    		}//Not Mobile Application if end
	    			else
	    			{ //Mobile Application
	    				
	    				int high=0;
	    				int medium=0;
	    				int low=0;
	    				
	    				String tableHeader="<html><body style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px;'>Dear Team <br>Thanks for your nice effort. We have reported few points throughout the entire week under below project at REVE SQA portal. Please find the priority wise bug status,Bug Trend and Active bug list(Today till now) in this mail. Please let us know for any further help. We will love to assist. Please login to the SQA portal and change the developer status of the corresponding Bug so that QA can verify it.<br><br>";
	    				String tableHeaderHigh="High Priority Issues: <br><table  style='border-collapse: collapse; font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; border: 1px solid #000; width:850px; margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";height:40px;'>\r\n" + 
	    						"    <tr><th style='border: 1px solid #000; width: 80px;'>Serial No.</th><th style='border: 1px solid #000; width: 100px;'>Bug ID</th><th style='border: 1px solid #000; width: 100px; text-align:center;'>OS Type</th><th style='border: 1px solid #000;'>Bug Title</th></tr></thead>\r\n" + 
	    						"    <tbody>";
	    				
	    				String tableHeaderMedium="<br><br><br>Medium Priority Issues: <br><table  style='border-collapse: collapse; font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; border: 1px solid #000; width:850px; margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";height:40px;'>\r\n" + 
	    						"    <tr><th style='border: 1px solid #000; width: 80px;'>Serial No.</th><th style='border: 1px solid #000; width: 100px;'>Bug ID</th><th style='border: 1px solid #000; width: 100px; text-align:center;'>OS Type</th><th style='border: 1px solid #000;'>Bug Title</th></tr></thead>\r\n" +  
	    						"    <tbody>";
	    				
	    				String tableHeaderLow="<br><br><br>Low Priority Issues: <br><table  style='border-collapse: collapse; font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; border: 1px solid #000; width:850px; margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";height:40px;'>\r\n" + 
	    						"    <tr><th style='border: 1px solid #000; width: 80px;'>Serial No.</th><th style='border: 1px solid #000; width: 100px;'>Bug ID</th><th style='border: 1px solid #000; width: 100px; text-align:center;'>OS Type</th><th style='border: 1px solid #000;'>Bug Title</th></tr></thead>\r\n" +  
	    						"    <tbody>";
	    				
	    				//SELECT bugTitle,priority FROM sqaBug WHERE isDeleted = 0 AND projectId = AND (qaStatus != 'Solved' AND qaStatus !='Closed');
		    			String getsummaryquery="SELECT os,bugId,bugTitle,priority FROM sqaBug WHERE isDeleted = 0 AND projectId = "+splitArray[0]+" AND (qaStatus != 'Solved' AND qaStatus !='Closed') order by os";
		    			logWeeklyBugSummaryAutoEmailer.debug("getsummaryquery: "+getsummaryquery);
		    			String summaryresult=ActionModule.MysqlConnectionAction(getsummaryquery, VariableModule.Ownconn, "%");
		    			logWeeklyBugSummaryAutoEmailer.debug("summaryresult: for "+projectArray[1]+" : "+summaryresult);
		    			
	    				
		    			String getsummaryquery1="SELECT os,count(*) FROM sqaBug WHERE isDeleted = 0 AND projectId = "+splitArray[0]+" AND (qaStatus != 'Solved' AND qaStatus !='Closed') group by os";
		    			String summaryresult1=ActionModule.MysqlConnectionAction(getsummaryquery1, VariableModule.Ownconn, ",");
		    			logWeeklyBugSummaryAutoEmailer.debug("summaryresult1: for "+projectArray[1]+" : "+summaryresult);
		    			summaryresult1=summaryresult1.substring(0, summaryresult1.length()-1);
		    			String[] SummaryArrayos1 = summaryresult1.split("\n");
		    			
		    			if(SummaryArrayos1.length==2)//SummaryArrayos
		    			{
		    				
			    			if(!summaryresult.equals(""))
			    			{
			    				int ahigh=0;
			    				int amedium=0;
			    				int alow=0;
			    				int ihigh=0;
			    				int imedium=0;
			    				int ilow=0;
			    				String[] SummaryArray1=null;
			    				summaryresult=summaryresult.substring(0, summaryresult.length()-1);
				    			String[] SummaryArray = summaryresult.split("\n");
				    			logWeeklyBugSummaryAutoEmailer.debug("Bug Length: "+SummaryArray.length);
				    			String ProjectName="<br><b>Project Name:</b> <b style='color: green;'>"+projectArray[0]+"</b><br><br>Weekly Summary Report:<br>";
				    			for(int r=0;r<SummaryArray.length;r++)
				    			{
				    				SummaryArray1 = SummaryArray[r].split("%");
					    			if(SummaryArray1[0].equals("android"))
					    			{
					    				SummaryArray1[0]="Android";
					    			}
					    			
				    				if(SummaryArray1[3].equals("High"))
				    				{
						    			if(SummaryArray1[0].equals("Android"))
						    			{
						    				ahigh++;
						    			}
						    			else
						    				ihigh++;
						    			
				    					high++;
				    					tableHeaderHigh=tableHeaderHigh.concat("<tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+high+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[1]+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[0]+"</td><td style='border: 1px solid #000; text-align: left;'>"+SummaryArray1[2]+"</td></tr>");
				    					
				    				}
				    				else if(SummaryArray1[3].equals("Medium"))
				    				{
						    			if(SummaryArray1[0].equals("Android"))
						    			{
						    				amedium++;
						    			}
						    			else
						    				imedium++;
						    			
				    					medium++;
				    					tableHeaderMedium=tableHeaderMedium.concat("<tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+medium+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[1]+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[0]+"</td><td style='border: 1px solid #000; text-align: left;'>"+SummaryArray1[2]+"</td></tr>");
				    					
				    				}
				    				else
				    				{
						    			if(SummaryArray1[0].equals("Android"))
						    			{
						    				alow++;
						    			}
						    			else
						    				ilow++;
						    			
				    					low++;
				    					tableHeaderLow=tableHeaderLow.concat("<tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+low+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[1]+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[0]+"</td><td style='border: 1px solid #000; text-align: left;'>"+SummaryArray1[2]+"</td></tr>");
				    					
				    				}
				    			}
				    			String summarysection="<table  style='border-collapse: collapse; font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; border: 1px solid #000; width:850px; margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";'><tr><th style='border: 1px solid #000;'>OS Type</th><th style='border: 1px solid #000;'>High Priority</th><th style='border: 1px solid #000;'>Medium Priority</th><th style='border: 1px solid #000;'>Low Priority</th><th style='border: 1px solid #000;'>Total Active Bugs Till Now</th></tr></thead><tbody><tr><td style='color: black; border: 1px solid #000; text-align: center;'>Android</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+ahigh+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+amedium+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+alow+"</td><td style='color: red; text-align: center; border: 1px solid #000;'><b>"+Integer.toString(ahigh+amedium+alow)+"</b></td></tr> <tr><td style='color: black; border: 1px solid #000; text-align: center;'>iOS</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+ihigh+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+imedium+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+ilow+"</td><td style='color: red; text-align: center; border: 1px solid #000;'><b>"+Integer.toString(ihigh+imedium+ilow)+"</b></td> </tr></tbody></table><br><br>";
				    			
				    			logWeeklyBugSummaryAutoEmailer.debug("Android High Bug: "+ahigh);
				    			logWeeklyBugSummaryAutoEmailer.debug("Android Medium Bug: "+amedium);
				    			logWeeklyBugSummaryAutoEmailer.debug("Android Low Bug: "+alow);
				    			
				    			logWeeklyBugSummaryAutoEmailer.debug("iOS High Bug: "+ihigh);
				    			logWeeklyBugSummaryAutoEmailer.debug("iOS Medium Bug: "+imedium);
				    			logWeeklyBugSummaryAutoEmailer.debug("iOS Low Bug: "+ilow);
				    			
				    			tableHeaderHigh=tableHeaderHigh+"</tbody></table>";
				    			tableHeaderMedium=tableHeaderMedium+"</tbody></table>";
				    			tableHeaderLow=tableHeaderLow+"</tbody></table>";
				    			
				    			String mail=tableHeader+ProjectName+summarysection;
				    			if(high!=0)
				    			{
				    				mail=mail+tableHeaderHigh;
				    			}
				    			if(medium!=0)
				    			{
				    				mail=mail+tableHeaderMedium;
				    			}
				    			if(low!=0)
				    			{
				    				mail=mail+tableHeaderLow;
				    			}
				    			
				    			mail=mail+"<br><br>Regards<br>REVE SQA"; //useridresult
				    			
				    			EmailModule.sendEmailWithAttachments(useridresult, subject+projectArray[0]+" after the end of the last week", mail,"3");
				    			
			    			}
			    			else
			    				continue;
		    			} // SummaryArrayos.length==2 if end
		    			else
		    			{
			    			if(!summaryresult.equals(""))
			    			{
			    				String[] SummaryArray1=null;
			    				summaryresult=summaryresult.substring(0, summaryresult.length()-1);
				    			String[] SummaryArray = summaryresult.split("\n");
				    			logWeeklyBugSummaryAutoEmailer.debug("Bug Length: "+SummaryArray.length);
				    			String ProjectName="<br><b>Project Name:</b> <b style='color: green;'>"+projectArray[0]+"</b><br><br>Weekly Summary Report:<br>";
				    			for(int r=0;r<SummaryArray.length;r++)
				    			{
				    				SummaryArray1 = SummaryArray[r].split("%");
					    			if(SummaryArray1[0].equals("android"))
					    			{
					    				SummaryArray1[0]="Android";
					    			}
					    			
				    				if(SummaryArray1[3].equals("High"))
				    				{
				    					high++;
				    					tableHeaderHigh=tableHeaderHigh.concat("<tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+high+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[1]+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[0]+"</td><td style='border: 1px solid #000; text-align: left;'>"+SummaryArray1[2]+"</td></tr>");
				    					
				    				}
				    				else if(SummaryArray1[3].equals("Medium"))
				    				{
				    					medium++;
				    					tableHeaderMedium=tableHeaderMedium.concat("<tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+medium+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[1]+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[0]+"</td><td style='border: 1px solid #000; text-align: left;'>"+SummaryArray1[2]+"</td></tr>");
				    					
				    				}
				    				else
				    				{
				    					low++;
				    					tableHeaderLow=tableHeaderLow.concat("<tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+low+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[1]+"</td><td style='border: 1px solid #000; text-align: center;'>"+SummaryArray1[0]+"</td><td style='border: 1px solid #000; text-align: left;'>"+SummaryArray1[2]+"</td></tr>");
				    					
				    				}
				    			}
				    			String summarysection="<table  style='border-collapse: collapse; font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; border: 1px solid #000; width:850px; margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";'><tr><th style='border: 1px solid #000;'>OS Type</th><th style='border: 1px solid #000;'>High Priority</th><th style='border: 1px solid #000;'>Medium Priority</th><th style='border: 1px solid #000;'>Low Priority</th><th style='border: 1px solid #000;'>Total Active Bugs Till Now</th></tr></thead><tbody><tr><td style='color: black; border: 1px solid #000; text-align: center;'>"+SummaryArray1[0]+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+high+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+medium+"</td><td style='color: black; border: 1px solid #000; text-align: center;'>"+low+"</td><td style='color: red; text-align: center;'><b>"+SummaryArray.length+"</b></td></tr></tbody></table><br><br>";
				    			logWeeklyBugSummaryAutoEmailer.debug("High Bug: "+high);
				    			logWeeklyBugSummaryAutoEmailer.debug("Medium Bug: "+medium);
				    			logWeeklyBugSummaryAutoEmailer.debug("Low Bug: "+low);
				    			tableHeaderHigh=tableHeaderHigh+"</tbody></table>";
				    			tableHeaderMedium=tableHeaderMedium+"</tbody></table>";
				    			tableHeaderLow=tableHeaderLow+"</tbody></table>";
				    			String mail=tableHeader+ProjectName+summarysection;
				    			if(high!=0)
				    			{
				    				mail=mail+tableHeaderHigh;
				    			}
				    			if(medium!=0)
				    			{
				    				mail=mail+tableHeaderMedium;
				    			}
				    			if(low!=0)
				    			{
				    				mail=mail+tableHeaderLow;
				    			}
				    			
				    			mail=mail+"<br><br>Regards<br>REVE SQA"; //useridresult
				    			
				    			EmailModule.sendEmailWithAttachments(useridresult, subject+projectArray[0]+" after the end of the last week", mail,"3");
				    			
			    			}
			    			else
			    				continue;
			    		} //SummaryArrayos.length!=2 else end
	    			}//Mobile Application else end
    			
    		} //1st for end
    			
    			VariableModule.LastWeekDay=ActionModule.getDate();
    			ActionModule.writinginFile("LastWeekDay=",VariableModule.LastWeekDay);
    			
    		} catch (MessagingException e) { //try end
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			catch(Exception e3) {
    				logWeeklyBugSummaryAutoEmailer.debug("Weekly Bug Summary Emailer Module Exception:"+e3);
    				
    				e3.printStackTrace();
    			}finally {
	    			logWeeklyBugSummaryAutoEmailer.debug("Weekly Bug Summary Emailer Module is going to sleep for 7 Days");
	    			
	    			
	    			try {
						Thread.sleep(597600000); // 6days 22 hour=597600000 waiting time
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}//finally block end
    		} //hourTime if end
    		
    		logWeeklyBugSummaryAutoEmailer.debug("Weekly Bug Summary Emailer Module is going to sleep for 5 min");
			
			//ActionModule.ConsolPrint("Current Thread: "+Thread.currentThread().getName());
			try {
				Thread.sleep(300000);  // 5 min waiting time
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
}