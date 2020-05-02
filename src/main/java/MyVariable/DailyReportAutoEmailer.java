package MyVariable;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;

import MyAction.ActionModule;
import MyEmail.EmailModule;

public class DailyReportAutoEmailer extends Thread {
	static Logger logDailyReportAutoEmailer = Logger.getLogger(DailyReportAutoEmailer.class.getName());
	 
	@Override
    public void run() {
    	while(true) {
			try {
				logDailyReportAutoEmailer.debug("Daily Report Emailer Module is going to start");
				
				String tableHeader="<html><body style='font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px;'>Dear Team, <br><br> Below is our today's task list and corresponding status by all QA members.<br><br><table  style='border-collapse: collapse; font-family: "+VariableModule.FontFamily+"; font-size:"+VariableModule.FontSize+"px; border: 1px solid #000; width:850px; margin: auto;'><thead style='background-color: "+VariableModule.ColorCode+";height:40px;'>\r\n" + 
						"    <tr><th style='border: 1px solid #000;'>QA Name</th><th style='border: 1px solid #000;'>Task Name</th><th style='border: 1px solid #000;'>Today's Testing Time</th><th style='border: 1px solid #000;'>Current Status</th></tr></thead>\r\n" + 
						"    <tbody>";
				int flag=0;
				int m=1;
				int counttask=0;
				String time="";
				String tableHeader1="";
				HashMap<String,Integer> hm=new HashMap<String,Integer>();
				String userbody="";
				String hourTime=ActionModule.getCurrentHour();
				String dayofWeek=ActionModule.getDayofWeek(ActionModule.getDate());
				logDailyReportAutoEmailer.debug("dayofWeek: "+dayofWeek);
				logDailyReportAutoEmailer.debug("LastDailyReportMailDay: "+VariableModule.LastDailyReportMailDay);
				String datelist=ActionModule.GetDateList(VariableModule.LastDailyReportMailDay, ActionModule.getDate());
				logDailyReportAutoEmailer.debug("datelist Size: "+datelist);
				if(!datelist.equals(""))
				{
					String[] dateArray = datelist.split(",");
					if(dateArray.length>1)
					{
						
						logDailyReportAutoEmailer.debug("dateArray Size: "+dateArray.length);
						for(int p=0;p<dateArray.length-1;p++)
						{
						

			    			//System.out.println("Daily Report: ");
							String subject="Daily Report of ";
							//String[] AssignerArray= {"Sharmila","Sujan","Hasan","Tarik","Palash","Musrat","Mizan"};
							//String[] AssignerfullnameArray= {"Sharmila Taluckder","Sujan Bhowmik","Mahmudul Hasan","Tarik Adnan","Palash Ghosh","Musrat Nahar","Mizanur Rahman"};
							String[] StatusArray= {"New Create","Start","Pause","Resume","Complete"};
							
							String temp="";
							for(int t=0;t<VariableModule.AssignerArray1.length;t++)
							{
								temp=temp+"\'"+VariableModule.AssignerArray1[t]+"\',";
							}
							temp=temp.substring(0, temp.length()-1);
							
							String getsummaryquery="select owner,projectName,module,status,id from sqaTask where status!=0 and owner in("+temp+") and id in(select taskId from sqaTaskHistory where lastModificationTime>'"+dateArray[p]+" 00:00:00' and lastModificationTime<'"+dateArray[p]+" "+VariableModule.summarymailtime+":00:00' and status in(1,3)) order by owner";
							logDailyReportAutoEmailer.debug("getsummaryquery: "+getsummaryquery);
			    			
			    			String summaryresult=ActionModule.MysqlConnectionAction(getsummaryquery, VariableModule.Ownconn, "%");
			    			logDailyReportAutoEmailer.debug("summaryresult: "+summaryresult);
			    			
			    			if(!summaryresult.equals(""))
			    			{
			    			summaryresult=summaryresult.substring(0, summaryresult.length()-1);
			    			String[] reportArray = summaryresult.split("\n");
			    			
			    			
		    				for(int k=0;k<VariableModule.AssignerArray1.length;k++)
		    				{
				    			for(int r=0;r<reportArray.length;r++)
				    			{
				    				String[] reportlistArray = reportArray[r].split("%");
				    				if(VariableModule.AssignerArray1[k].equals(reportlistArray[0]))
				    				{
				    					counttask++;
				    				}
				    				

				    			}
				    			    
			    				  hm.put(VariableModule.AssignerArray1[k],counttask);   
			    				  counttask=0;
		    				}
		    				
		/*  				  for(Map.Entry n:hm.entrySet())
		  				  {  
		  					  System.out.println(n.getKey()+" "+n.getValue());  
		   				  }*/
		  				  
		    				for(int k=0;k<VariableModule.AssignerArray1.length;k++)
		    				{
		    					//tableHeader=tableHeader.concat();

		    					//userbody=userbody.concat("<br><br><b>"+user+" Report:</b><br>");
		    					
					        	Integer a = hm.get(VariableModule.AssignerArray1[k]);
					        	tableHeader1=tableHeader1.concat(" <tr><td  style='border: 1px solid #000; width: 150px; text-align: left;' rowspan=\""+a+"\">"+VariableModule.AssignerfullnameArray1[k]);
					        	
				    			for(int i=0;i<reportArray.length;i++)
				    			{
				    				String[] reportlistArray = reportArray[i].split("%");
				    				if(VariableModule.AssignerArray1[k].equals(reportlistArray[0]))
				    				{
				    					int second=0;
				    					time="";
				    					String gettestingtimequery="SELECT duration FROM sqaTaskHistory WHERE lastModificationTime>'"+dateArray[p]+" 00:00:00' and lastModificationTime<'"+dateArray[p]+" "+VariableModule.summarymailtime+":59:00' and  status not in(0,1,3) and taskId ="+reportlistArray[4];
				    	    			//ActionModule.writing("gettestingtimequery: "+gettestingtimequery);
				    	    			ActionModule.ConsolPrint("gettestingtimequery: "+gettestingtimequery,"");
				    	    			String durationresult=ActionModule.MysqlConnectionAction(gettestingtimequery, VariableModule.Ownconn, ",");
				    	    			//ActionModule.writing("durationresult: "+durationresult);
				    	    			//ActionModule.ConsolPrint("durationresult: "+durationresult,"");
				    	    			//logDailyReportAutoEmailer.debug("durationresult: "+durationresult);
				    	    			
				    	    			if(!durationresult.equals(""))
				    	    			{
				    	    				durationresult=durationresult.substring(0, durationresult.length()-1);
				    	    				//logDailyReportAutoEmailer.debug("durationresult: "+durationresult+"Test1");
											if(durationresult.indexOf("\n") != -1){
												//logDailyReportAutoEmailer.debug("Test11: "+"Test11");
											}
					    	    			String[] durationlistArray = durationresult.split("\n");
					    	    			int sum=0;
					    	    			for(int t=0;t<durationlistArray.length;t++)
					    	    			{
					    	    				int time1 = Integer.parseInt(durationlistArray[t]); //second
					    	    				sum=sum+time1;
					    	    			}
					    	    			time = ActionModule.formatSeconds(sum);
		/*			    	    			Date d = new Date(second * 1000L);
					    	    			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); // HH for 0-23
					    	    			//df.setTimeZone(TimeZone.getTimeZone("GMT"));
					    	    			time = df.format(d);*/
				    	    			}
				    					flag=1;
				    					if(m==1)
				    					{
				    				        if (hm.containsKey(VariableModule.AssignerArray1[k])) 
				    				        {

				    				        	tableHeader1=tableHeader1.concat("</td><td style='color: black; border: 1px solid #000; text-align: left;'><b>Project Name: </b>"+reportlistArray[1]+"<br><b>Module Name: </b> "+reportlistArray[2]+"</td><td style='border: 1px solid #000; text-align: center;'>"+time+"</td><td style='border: 1px solid #000; text-align: center;'>"+StatusArray[Integer.parseInt(reportlistArray[3])]+"</td></tr>");
				    				        	
				    				        	//System.out.println("tableHeader: "+tableHeader);  
				    				        }
				    					}
				    					else
				    					{	
				    						tableHeader1=tableHeader1.concat("<tr><!--< less than 1 cell<td> previous --><td style='color: black; border: 1px solid #000; text-align: left;'><b>Project Name: </b>"+reportlistArray[1]+"<br><b>Module Name: </b> "+reportlistArray[2]+"</td><td style='border: 1px solid #000; text-align: center;'>"+time+"</td><td style='border: 1px solid #000; text-align: center;'>"+StatusArray[Integer.parseInt(reportlistArray[3])]+"</td></tr>");
				    					}
				    					//userbody=userbody.concat("&emsp;&emsp;&emsp;"+m+".&emsp;&emsp;"+reportlistArray[1]+"<br>");
				    					m++;
				    					//second=0;
				    				}

				    			}
				    			if(flag==0)
				    			{
				    				tableHeader1=tableHeader1.concat("</td><td style='color: black; border: 1px solid #000; text-align: center;'>No Task found</td>");
				    			}
				    			flag=0;
				    			m=1;
				    			
		/*		    			if(flag!=1)
				    			{
				    				
				    			}*/
			    			}
		    				//System.out.println("tableHeader: "+tableHeader);
			    			String mail = tableHeader+tableHeader1+"</tbody></table>"+" <br><br>Regards<br>REVE SQA</body> </html>";
			    			//System.out.println("mailformat: "+mail);
			    			//logDailyReportAutoEmailer.debug("Mail Format: "+mail);
			    			try {
								EmailModule.sendEmailWithAttachments("", subject+"_"+dateArray[p], mail,"2");
								
								//logDailyReportAutoEmailer.debug("Daily Report Sent!!");
								

							} catch (AddressException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								logDailyReportAutoEmailer.debug("Daily Report Exception1: "+e1);
							} finally {
			    			
								hm.clear();
								
							}
			    		}

			    	    	logDailyReportAutoEmailer.debug("Daily Report Sent for: "+dateArray[p]);
			    		    
			    	    	if(p==(dateArray.length-2))
			    		    	{
			    		    		VariableModule.LastDailyReportMailDay=dateArray[p];
			    					ActionModule.writinginFile("LastDailyReportMailDay=",VariableModule.LastDailyReportMailDay);
			    		    	}
			    	    	tableHeader1="";
					} //Top for end

						logDailyReportAutoEmailer.debug("Daily Report Emailer Module is going to sleep for "+VariableModule.sleepTime+" minute");
						Thread.sleep(Long.parseLong(VariableModule.sleepTime)*60000); //22 hour=79200000 milisec
						
						
					}
					
					else
					{
						
				    	if(hourTime.equals(VariableModule.summarymailtime))
				    	{
				    		
						
						logDailyReportAutoEmailer.debug("dateArray Size: "+dateArray.length);
						for(int p=0;p<dateArray.length;p++)
						{
						

			    			//System.out.println("Daily Report: ");
							String subject="Daily Report of ";
							//String[] AssignerArray= {"Sharmila","Sujan","Hasan","Tarik","Palash","Musrat","Mizan"};
							//String[] AssignerfullnameArray= {"Sharmila Taluckder","Sujan Bhowmik","Mahmudul Hasan","Tarik Adnan","Palash Ghosh","Musrat Nahar","Mizanur Rahman"};
							String[] StatusArray= {"New Create","Start","Pause","Resume","Complete"};
							
							String temp="";
							for(int t=0;t<VariableModule.AssignerArray1.length;t++)
							{
								temp=temp+"\'"+VariableModule.AssignerArray1[t]+"\',";
							}
							temp=temp.substring(0, temp.length()-1);
							
							String getsummaryquery="select owner,projectName,module,status,id from sqaTask where status!=0 and owner in("+temp+") and id in(select taskId from sqaTaskHistory where lastModificationTime>'"+dateArray[p]+" 00:00:00' and lastModificationTime<'"+dateArray[p]+" "+VariableModule.summarymailtime+":00:00' and status in(1,3)) order by owner";
							logDailyReportAutoEmailer.debug("getsummaryquery: "+getsummaryquery);
			    			
			    			String summaryresult=ActionModule.MysqlConnectionAction(getsummaryquery, VariableModule.Ownconn, "%");
			    			logDailyReportAutoEmailer.debug("summaryresult: "+summaryresult);
			    			
			    			if(!summaryresult.equals(""))
			    			{
			    			summaryresult=summaryresult.substring(0, summaryresult.length()-1);
			    			String[] reportArray = summaryresult.split("\n");
			    			
			    			
		    				for(int k=0;k<VariableModule.AssignerArray1.length;k++)
		    				{
				    			for(int r=0;r<reportArray.length;r++)
				    			{
				    				String[] reportlistArray = reportArray[r].split("%");
				    				if(VariableModule.AssignerArray1[k].equals(reportlistArray[0]))
				    				{
				    					counttask++;
				    				}
				    				

				    			}
				    			    
			    				  hm.put(VariableModule.AssignerArray1[k],counttask);   
			    				  counttask=0;
		    				}
		    				
		/*  				  for(Map.Entry n:hm.entrySet())
		  				  {  
		  					  System.out.println(n.getKey()+" "+n.getValue());  
		   				  }*/
		  				  
		    				for(int k=0;k<VariableModule.AssignerArray1.length;k++)
		    				{
		    					//tableHeader=tableHeader.concat();

		    					//userbody=userbody.concat("<br><br><b>"+user+" Report:</b><br>");
		    					
					        	Integer a = hm.get(VariableModule.AssignerArray1[k]);
					        	tableHeader1=tableHeader1.concat(" <tr><td  style='border: 1px solid #000; width: 150px; text-align: left;' rowspan=\""+a+"\">"+VariableModule.AssignerfullnameArray1[k]);
					        	
				    			for(int i=0;i<reportArray.length;i++)
				    			{
				    				String[] reportlistArray = reportArray[i].split("%");
				    				if(VariableModule.AssignerArray1[k].equals(reportlistArray[0]))
				    				{
				    					int second=0;
				    					time="";
				    					String gettestingtimequery="SELECT duration FROM sqaTaskHistory WHERE lastModificationTime>'"+dateArray[p]+" 00:00:00' and lastModificationTime<'"+dateArray[p]+" "+VariableModule.summarymailtime+":59:00' and  status not in(0,1,3) and taskId ="+reportlistArray[4];
				    	    			//ActionModule.writing("gettestingtimequery: "+gettestingtimequery);
				    	    			ActionModule.ConsolPrint("gettestingtimequery: "+gettestingtimequery,"");
				    	    			String durationresult=ActionModule.MysqlConnectionAction(gettestingtimequery, VariableModule.Ownconn, ",");
				    	    			//ActionModule.writing("durationresult: "+durationresult);
				    	    			//ActionModule.ConsolPrint("durationresult: "+durationresult,"");
				    	    			//logDailyReportAutoEmailer.debug("durationresult: "+durationresult);
				    	    			
				    	    			if(!durationresult.equals(""))
				    	    			{
				    	    				durationresult=durationresult.substring(0, durationresult.length()-1);
				    	    				//logDailyReportAutoEmailer.debug("durationresult: "+durationresult+"Test1");
											if(durationresult.indexOf("\n") != -1){
												//logDailyReportAutoEmailer.debug("Test11: "+"Test11");
											}
					    	    			String[] durationlistArray = durationresult.split("\n");
					    	    			int sum=0;
					    	    			for(int t=0;t<durationlistArray.length;t++)
					    	    			{
					    	    				int time1 = Integer.parseInt(durationlistArray[t]); //second
					    	    				sum=sum+time1;
					    	    			}
					    	    			time = ActionModule.formatSeconds(sum);
		/*			    	    			Date d = new Date(second * 1000L);
					    	    			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); // HH for 0-23
					    	    			//df.setTimeZone(TimeZone.getTimeZone("GMT"));
					    	    			time = df.format(d);*/
				    	    			}
				    					flag=1;
				    					if(m==1)
				    					{
				    				        if (hm.containsKey(VariableModule.AssignerArray1[k])) 
				    				        {

				    				        	tableHeader1=tableHeader1.concat("</td><td style='color: black; border: 1px solid #000; text-align: left;'><b>Project Name: </b>"+reportlistArray[1]+"<br><b>Module Name: </b> "+reportlistArray[2]+"</td><td style='border: 1px solid #000; text-align: center;'>"+time+"</td><td style='border: 1px solid #000; text-align: center;'>"+StatusArray[Integer.parseInt(reportlistArray[3])]+"</td></tr>");
				    				        	
				    				        	//System.out.println("tableHeader: "+tableHeader);  
				    				        }
				    					}
				    					else
				    					{	
				    						tableHeader1=tableHeader1.concat("<tr><!--< less than 1 cell<td> previous --><td style='color: black; border: 1px solid #000; text-align: left;'><b>Project Name: </b>"+reportlistArray[1]+"<br><b>Module Name: </b> "+reportlistArray[2]+"</td><td style='border: 1px solid #000; text-align: center;'>"+time+"</td><td style='border: 1px solid #000; text-align: center;'>"+StatusArray[Integer.parseInt(reportlistArray[3])]+"</td></tr>");
				    					}
				    					//userbody=userbody.concat("&emsp;&emsp;&emsp;"+m+".&emsp;&emsp;"+reportlistArray[1]+"<br>");
				    					m++;
				    					//second=0;
				    				}

				    			}
				    			if(flag==0)
				    			{
				    				tableHeader1=tableHeader1.concat("</td><td style='color: black; border: 1px solid #000; text-align: center;'>No Task found</td>");
				    			}
				    			flag=0;
				    			m=1;
				    			
		/*		    			if(flag!=1)
				    			{
				    				
				    			}*/
			    			}
		    				//System.out.println("tableHeader: "+tableHeader);
			    			String mail = tableHeader+tableHeader1+"</tbody></table>"+" <br><br>Regards<br>REVE SQA</body> </html>";
			    			//System.out.println("mailformat: "+mail);
			    			//logDailyReportAutoEmailer.debug("Mail Format: "+mail);
			    			try {
								EmailModule.sendEmailWithAttachments("", subject+"_"+dateArray[p], mail,"2");
								
								//logDailyReportAutoEmailer.debug("Daily Report Sent!!");
								

							} catch (AddressException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								logDailyReportAutoEmailer.debug("Daily Report Exception1: "+e1);
							} finally {
			    			
								hm.clear();
								
							}
			    		}

			    	    	logDailyReportAutoEmailer.debug("Daily Report Sent for: "+dateArray[p]);
			    		    
			    	    	if(p==(dateArray.length-1))
			    		    	{
			    		    		VariableModule.LastDailyReportMailDay=dateArray[p];
			    					ActionModule.writinginFile("LastDailyReportMailDay=",VariableModule.LastDailyReportMailDay);
			    		    	}
			    	    	tableHeader1="";
					} //Top for end

						logDailyReportAutoEmailer.debug("Daily Report Emailer Module is going to sleep for "+VariableModule.sleepTime+" minute");
						Thread.sleep(Long.parseLong(VariableModule.sleepTime)*60000); //22 hour=79200000 milisec
						
					}//Top 2nd if end
						
					}

			
				

				}//Top if end
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				logDailyReportAutoEmailer.debug("Daily Report Exception2: "+e2);
			}finally {
    			
					try {
						logDailyReportAutoEmailer.debug("Daily Report Emailer Module is going to sleep for 5 minute");
						
						Thread.sleep(120000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}


			
    	}
    }
    
}