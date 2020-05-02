package MyAction;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;

import javax.mail.internet.AddressException;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.text.AbstractDocument.Content;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFBorderFormatting;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sun.javafx.geom.transform.GeneralTransform3D;
/*import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeMultipart;
import com.sun.xml.internal.ws.wsdl.writer.document.Message;*/

import Client.ClientModule;
import DevelopedAPI.DevelopedAPIModule;
import MyEmail.EmailModule;
import MyPermission.PermissionModule;
import MyVariable.VariableModule;
import javafx.scene.control.Cell;
/*import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.rmi.transport.Transport;*/
import net.bytebuddy.dynamic.loading.ClassInjector;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
 

public class ActionModule {
	static Logger logActionModule = Logger.getLogger(ActionModule.class.getName());
	static VariableModule variabledo = new VariableModule();
	static int RowCount=1;
	
	
/*	public static void EmailAction() {
		

	}*/
	
	public void PushRepoAction(String dbnewdata)
	{
		//repodata

/*	    for (int i = 0; i < data.length; ++i) {
	        for (int j = 0; j < data[i].length; ++j)
	            System.out.print(data[i][j]+" ");
	        System.out.println();
	    }*/
	    
	}
	
	public void UpdateRepoAction(String dbupdateddata)
	{
		//repodata

/*	    for (int i = 0; i < data.length; ++i) {
	        for (int j = 0; j < data[i].length; ++j)
	            System.out.print(data[i][j]+" ");
	        System.out.println();
	    }*/
	    
	}
	
	public String PulldateforUserIDfromRepoAction(String userID)
	{
		//repodata

/*	    for (int i = 0; i < data.length; ++i) {
	        for (int j = 0; j < data[i].length; ++j)
	            System.out.print(data[i][j]+" ");
	        System.out.println();
	    }*/
	    return "";
	}
	
	public static void MysqlConnectionActionUpdate(String Myquery)
	{

	    java.sql.Statement stmt = null;
		    
	    try {
	    	//CommonOSModule.conn=MysqlConnection();
	        stmt = VariableModule.Ownconn.createStatement();
	        stmt.executeUpdate(Myquery);
	        //ConsolPrint("User is updated");
		    stmt.close();
		    //connection.close();
	} catch (SQLException e) {
	    throw new IllegalStateException("Cannot connect the database!", e);
	}
	    
	}
	
	public WebDriver FirefoxdriverAction() {
		System.setProperty("webdriver.gecko.driver",variabledo.firefoxdriver);
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setCapability("marionette",true);
		//capability.setCapability("platform", Platform.ANY);
		//capability.setCapability("binary", "/usr/bin/geckodriver");
		WebDriver driver = new FirefoxDriver(capability);

		return driver;
	}
	
    public static String getNonce(String clientID) {
    	String[] MemArray = null;
    	try {
			URL yahoo = new URL("http://162.222.186.235/billing/profilePictureHandler.do?requesttype=getNonce&username="+clientID);
			//APIUrl = actiondo.BaseURL().concat("http://162.222.186.235/billing/profilePictureHandler.do?requesttype=getNonce&username=1234521");
			URLConnection yc = yahoo.openConnection();
			BufferedReader in = new BufferedReader(
			                        new InputStreamReader(
			                        yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
			    //System.out.println(inputLine);
				MemArray = inputLine.split("nonce=");
			  }
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return MemArray[1];
    }
    

	public static String GetDateList(String startDate, String endDate)
    {

    	
		String DateListArray = "";
		if(!startDate.equals(endDate))
		{
    	try {
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date firstDate = sdf.parse(startDate);
			Date secondDate = sdf.parse(endDate);
			String tempDate=startDate;
			

			long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			Calendar c = Calendar.getInstance();
			//System.out.println("Date: "+diff);
			
			
			for(int i=0;i<= (int) diff;i++)
			{
				//System.out.println("TempDate: "+tempDate);
				if(i==0)
				{
					continue;
				}
				else if(i==1)
				{
					c.setTime(sdf.parse(tempDate));
					c.add(Calendar.DATE, 1);  // number of days to add
					tempDate = sdf.format(c.getTime());  // tempDate is now the new date
					String dayofWeek=getDayofWeek(tempDate);
					//System.out.println("DayofWeek: "+dayofWeek);
					if(dayofWeek.equals("Fri") || dayofWeek.equals("Sat"))
					{
						
						continue;
					}
					else
					{
						DateListArray=DateListArray+tempDate.concat(",");
						
					}
	

				}
				else
				{
					c.setTime(sdf.parse(tempDate));
					c.add(Calendar.DATE, 1);  // number of days to add
					tempDate = sdf.format(c.getTime());  // tempDate is now the new date
					String dayofWeek=getDayofWeek(tempDate);
					if(dayofWeek.equals("Fri") || dayofWeek.equals("Sat"))
					{
						
						continue;
					}
					else
					{
						DateListArray=DateListArray+tempDate.concat(",");
						
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	DateListArray=DateListArray.substring(0, DateListArray.length()-1);
		}
    	return DateListArray;
    }
    
    public static String formatSeconds(int timeInSeconds){
        int hours = timeInSeconds / 3600;
        int secondsLeft = timeInSeconds - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String formattedTime = "";
        if (hours < 10)
            formattedTime += "0";
        formattedTime += hours + ":";

        if (minutes < 10)
            formattedTime += "0";
        formattedTime += minutes + ":";

        if (seconds < 10)
            formattedTime += "0";
        formattedTime += seconds ;

        return formattedTime;
    }
    
	public static String MysqlConnectionAction(String Myquery, Connection conn, String character)
	{

		String columnValue1="";
		String columnValue="";
		String[] URL=null;
	    //Connection connection = null;
	    java.sql.Statement stmt = null;
		    
	    try {
	    	//connection=CommonOSModule.MysqlConnection();
	    	stmt = conn.createStatement();
		    ResultSet rs=stmt.executeQuery(Myquery);
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int columnsNumber = rsmd.getColumnCount();
		    int i;
		    while (rs.next()) {

		        for (i = 1; i <= columnsNumber; i++) {
		            //if (i > 1) System.out.print("\n");
		            columnValue = rs.getString(i);
		            if(columnValue==null)
		            {
		            	columnValue="";
		            }
		            if(columnValue.equals("NULL"))
		            {
		            	//System.out.print("NULL found:");
		            	columnValue="";
		            }
		            //DBValues[i]=columnValue;
		            //System.out.print("Test:"+columnValue);
		            //System.out.print(columnValue + " " + rsmd.getColumnName(i));
		            if(i<columnsNumber)
		            {
		            	columnValue1=columnValue1.concat(columnValue+character);
		            }
		            else
		            {

		            	columnValue1=columnValue1.concat(columnValue);

		            }
		        }
		        i=1;
		        if(!columnValue1.equals(""))
		        {
		        	columnValue1=columnValue1.concat("\n");
		        }
		    }
		    //System.out.print("Test:"+columnValue1);
		    stmt.close();
		    //connection.close();
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		return columnValue1;
	}
	
    public static void MysqlConnectOwn(Connection connect)
    {
    	if(connect==null)
    	{
    		VariableModule.Ownconn=MysqlConnectionOwn();
    		ConsolPrint("Mysql Connection is getting: "+VariableModule.Ownconn,"");
    		writing("Mysql Connection is getting: "+VariableModule.Ownconn);
/*	    	if(VariableModule.Ownconn==null)
	    	{
	    		try {
					EmailModule.sendEmailWithAttachments("nafiul@revesoft.com", "MySQL connection is null", "Dear Team,\n\n"+VariableModule.server_IP+" server MySQL connection is getting null.\nPlease fix the MySQL connection issue otherwise Mail thread will be failed to send SQA mail.","5");
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (javax.mail.MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}*/
    	}
    }
    
    public static void MysqlConnectRemote(Connection connect)
    {
    	if(connect==null)
    	{
    		VariableModule.Remoteconn=MysqlConnectionRemote();
    		//System.out.println("Mysql Connection: "+CommonOSModule.conn);
    	}
    }
    
    public static Connection MysqlConnectionOwn()
    {
    	//long startTime = System.currentTimeMillis();
    	Connection connt = null;
	    try {
	        // STEP 2: Register JDBC driver
	        Class.forName("com.mysql.jdbc.Driver");


	        connt = DriverManager.getConnection("jdbc:mysql://"+VariableModule.MySQLIP+"/"+VariableModule.DBName, VariableModule.DBUser, VariableModule.DBPass);
	        writing(" Own Database Connected!\n");
	    }catch(Exception e) {
	        e.printStackTrace();
	    }

	    
/*	    public void close() throws SQLException {
	        databaseConnection.close();
	    }*/
	    //CommonAction.ExecuteTimeMeasure(startTime,"ProcessParserforCPU");
		return connt;
    }
    
    public static Connection MysqlConnectionRemote()
    {
    	//long startTime = System.currentTimeMillis();
    	Connection connt = null;
	    try {
	        // STEP 2: Register JDBC driver
	        Class.forName("com.mysql.jdbc.Driver");


	        connt = DriverManager.getConnection("jdbc:mysql://"+VariableModule.MySQLRemoteIP+"/"+VariableModule.RemoteDBName, VariableModule.dbusername, VariableModule.dbpassword);
	        writing(" Remote Database Connected!\n");
	    }catch(Exception e) {
	        e.printStackTrace();
	    }

	    
/*	    public void close() throws SQLException {
	        databaseConnection.close();
	    }*/
	    //CommonAction.ExecuteTimeMeasure(startTime,"ProcessParserforCPU");
		return connt;
    }
    
	public static void AddRepositoryAction(String tablename)
	{
		int i;
		int j;
		String[] VarArrayLoc;
		String query="select * from "+tablename+" where cvIsNew<3";
		//ActionModule.ConsolPrint("Query: "+query,"Test");
		String dblasttime=ActionModule.MysqlConnectionAction(query,VariableModule.Ownconn, ",");
		//dblasttime = dblasttime.replace(",", "");
		ActionModule.ConsolPrint("Table Name: "+tablename+" Modif Time: "+dblasttime, "");
		String[] VarArray = dblasttime.split("\n");
		//ActionModule.ConsolPrint("Lenght: "+VarArray.length,"Test");
		for(i=0;i<VarArray.length;i++)
		{
			VarArrayLoc = VarArray[i].split(",");
			//ActionModule.ConsolPrint("Lenght1: "+VarArrayLoc.length,"Test");
			//ActionModule.ConsolPrint("Value0: "+VarArrayLoc[16],"Test");
			//ActionModule.ConsolPrint("Value1: "+VarArrayLoc[18],"Test");
			for(j=0;j<VarArrayLoc.length;j++)
			{
				VariableModule.repolist[i][j].add(VarArrayLoc[j]);
				//String test=VarArrayLoc[0];
			}
			j=0;
			Arrays.fill(VarArrayLoc, null);
			//VarArrayLoc= {""};
		}
		
		for(i=0;i<VariableModule.repolist.length;i++)
		{
			
			for(j=0;j<=17;j++)
			{
				System.out.println("Array"+i+":"+j+"="+VariableModule.repolist[i][j]);
			}
			j=0;
		}
		//CommonAction.ExecuteTimeMeasure(startTime,"CommonAction.LastModificationChecker");
		//return Long.valueOf(dblasttime);
		//return Long.parseLong(dblasttime);
	}
	
	public static void AddPendingQueueInstantAction(String queuedata)
	{
		int i;

		String[] QueueArray = queuedata.split("\n");
		//String[] TimeArray=CheckingTimetoaddinqueueAction(QueueArray);
		
		//String[] ExeTimeArray=CheckingTimewithsystemtimeAction(TimeArray);
		for(i=0;i<QueueArray.length;i++)
		{
			//ActionModule.ConsolPrint("Add exe: "+QueueArray[i],"Test");
			VariableModule.InstantpendingQueue.add(QueueArray[i]);
			logActionModule.debug("Added in InstantpendingQueue");
		}
		
		//System.out.println("Top Element of queue-"+VariableModule.InstantpendingQueue.peek());
		//System.out.println("Size of queue-"+VariableModule.InstantpendingQueue.size());
		

/*		Iterator itr=VariableModule.pendingQueue.iterator();  
		while(itr.hasNext())
		{  
			System.out.println(itr.next());
		}*/
		
	}
	public static void AddPendingQueueScheduleAction(String queuedata)
	{
		int i;

		String[] QueueArray = queuedata.split("\n");
		//String[] TimeArray=CheckingTimetoaddinqueueAction(QueueArray);
		//ActionModule.ConsolPrint("Lenght: "+VarArray.length,"Test");
		//String[] ExeTimeArray=CheckingTimewithsystemtimeAction(TimeArray);
		for(i=0;i<QueueArray.length;i++)
		{

				VariableModule.SchedulependingQueue.add(QueueArray[i]);

		}
		
		//System.out.println("Top Element of queue-"+VariableModule.InstantpendingQueue.peek());
		//System.out.println("Size of queue-"+VariableModule.InstantpendingQueue.size());
		

/*		Iterator itr=VariableModule.pendingQueue.iterator();  
		while(itr.hasNext())
		{  
			System.out.println(itr.next());
		}*/
		
	}
	
	public static void AddPendingplanQueueInstantAction(String executionID)
	{
		int i;
		String[] PlanArray = null;
		//String[] PlanArray1 = null;
		String query="select * from auexecutionplan where expType=1 and expPlanBatchID="+executionID;
		
		//ActionModule.ConsolPrint("Query: "+query,"Test");
		String testPlan=ActionModule.MysqlConnectionAction(query, VariableModule.Ownconn,",");
		//ActionModule.ConsolPrint("Test Plan: "+testPlan,"Test");
		PlanArray = testPlan.split("\n");
		for(i=0;i<PlanArray.length;i++)
		{
			if(!PlanArray[i].equals(""))
			{
				ActionModule.ConsolPrint("Plan Add: "+PlanArray[i],"Test");
				VariableModule.InstantpendingplanQueue.add(PlanArray[i]);
			}

		}
		
/*		String queryforAPI="select expID from auexecutionplan where expType=1 and expMenuID=1 and expPlanBatchID="+executionID;
		String testPlan1=ActionModule.MysqlConnectionAction(queryforAPI, VariableModule.Ownconn,",");
		//ActionModule.ConsolPrint("Test Plan: "+testPlan,"Test");
		PlanArray1 = testPlan.split("\n");
		for(i=0;i<PlanArray1.length;i++)
		{
			if(!PlanArray1[i].equals(""))
			{
				VariableModule.EXEIDQueue.add(PlanArray1[i]);
			}

		}*/
		//System.out.println("Top Element of queue-"+VariableModule.InstantpendingQueue.peek());
		//System.out.println("Size of queue-"+VariableModule.InstantpendingQueue.size());
		

/*		Iterator itr=VariableModule.InstantpendingplanQueue.iterator();  
		while(itr.hasNext())
		{  
			System.out.println("ID"+executionID+": "+itr.next());
		}*/
		
	}
	
	public static void OngoingstatusUpdateAction(String status)
	{
		String pendingqueue="";
		String exeIDmerge="";
		//ActionModule.ConsolPrint("Lenght: "+VariableModule.InstantpendingQueue.size(),"Test");
		Iterator itr=VariableModule.InstantpendingQueue.iterator();  
		while(itr.hasNext())
		{
			String tempArray=itr.next().toString();
			pendingqueue=tempArray+"@";
			//System.out.println("Exe: "+tempArray);
			String[] QueueArray = tempArray.split(",");
			exeIDmerge=exeIDmerge.concat(QueueArray[0]+",");
		}
		exeIDmerge=exeIDmerge.substring(0, exeIDmerge.length()-1);
		pendingqueue=pendingqueue.substring(0, pendingqueue.length()-1);
		//ActionModule.ConsolPrint("Merge: "+exeIDmerge,"Test");
		if(!exeIDmerge.equals(""))
		{
			String Myquery="update auexecutionplanbatch set expbExeType="+status+",expbTime="+System.currentTimeMillis()+" where expbExeType=1 and expbID in ("+exeIDmerge+")";
			
			MysqlConnectionActionUpdate(Myquery);
			logActionModule.debug("Updated to ongoing batch");
		}
		
		
/*		Iterator itr1=VariableModule.InstantpendingQueue.iterator();
		String[] QueueArray = pendingqueue.split("@");
		for(int t=0;t<QueueArray.length;t++)
		{
			logActionModule.debug("QueueArray: "+QueueArray[t]);
			while(itr.hasNext())
			{
				if(QueueArray[t].equals(itr1.next().toString()))
				{
					logActionModule.debug("InstantpendingQueue matched"+itr1.next().toString());
					VariableModule.InstantpendingQueue.poll();
					logActionModule.debug("InstantpendingQueue polled");
					String[] QueueArray1 = QueueArray[t].split(",");
					AddPendingplanQueueInstantAction(QueueArray1[0]);
					break;
				}
			}
		}*/
		
		
		//ActionModule.ConsolPrint("exe Lenght: "+VariableModule.InstantpendingQueue.size(),"Test");
		
		//int sizependingqueue=VariableModule.InstantpendingQueue.size();
		 for (int k = 0; k < VariableModule.InstantpendingQueue.size(); k++) {
			String exepoll=VariableModule.InstantpendingQueue.poll();
			//System.out.println("Exe POLL: "+exepoll);
			String[] QueueArray1 = exepoll.split(",");
			AddPendingplanQueueInstantAction(QueueArray1[0]);
		}
		 //System.out.println("Exe POLL1: ");
		
	}
	public static boolean APIExecutionAction(String apilist, String[] ExeArray1, String[] ConfigArray)
	{
		boolean done;
		int item;
		//WebDriver driver = ChromedriverAction();
		String[] apiID=apilist.split("\n");
		DevelopedAPIModule api = new DevelopedAPIModule();
		for(int i=0; i<apiID.length;i++)
		{
			String[] apiIDsplit=apiID[i].split("[$]");
			item=Integer.parseInt(apiIDsplit[0]);
			if(item==0) //Caller ID API
			{
/*				//System.out.println("Item: "+item);
				int r=1;
				for(; r<=3;r++)
				{
					api.CallerIDAPIAction(Integer.toString(r),ExeArray1, ConfigArray);
					//System.out.println("R: "+r);
				}*/
			}
			else if(item>=1 && item<=3)
			{
				api.CallerIDAPIAction(apiID[i],ExeArray1, ConfigArray);
			}
			else if(item>=4 && item>=4)
			{
				
			}
			else if(item>=4 && item>=4)
			{
				
			}
			else
			{
				
			}
		}
		done=true;
		return done;

	}
	
	//@SuppressWarnings("null")
	public static void TestExecution(String[] ExeArray)
	{
		//System.out.println("Test***");
		boolean done=false;
		if(ExeArray[3].equals("1")) //ModuleID=1=API
		{
			logActionModule.debug("API Module is executing");
			String Query="select * from auconfigvar where cvID in(select expbConfigVarID from auexecutionplanbatch where expbID="+ExeArray[1]+")";
			String executiondata=ActionModule.MysqlConnectionAction(Query,VariableModule.Ownconn,",");
			String[] ConfigArray = executiondata.split(",");
			
			String apisql="";
			if(ExeArray[5].indexOf("@") != -1)
			{
				apisql= ExeArray[5].replaceAll("@", ",");
			}
			else
			{
				
				apisql= ExeArray[5];
			}
			
			//System.out.println("apisql: "+apisql);
			String Queryapi="select * from auapilist where apiID in("+apisql+")";
			String getdata=ActionModule.MysqlConnectionAction(Queryapi,VariableModule.Ownconn,"$");
			//ActionModule.AddPendingAPIQueueAction(getdata, ExeArray[0]);
			//System.out.println("getdata: "+getdata);
			logActionModule.debug("getdata: "+getdata);
			done=APIExecutionAction(getdata, ExeArray, ConfigArray);
			
			 if(done)
			 {
				 ActionModule.ExecutionDoneAction("3","plan",ExeArray[0]);
			 }
		} // API
		else if(ExeArray[3].equals("2")) //Client Module
		{
			logActionModule.debug("Client Module is executing");
			String Query="select * from auconfigvar where cvID in(select expbConfigVarID from auexecutionplanbatch where expbID="+ExeArray[1]+")";
			String executiondata=ActionModule.MysqlConnectionAction(Query,VariableModule.Ownconn,",");
			String[] ConfigArray = executiondata.split(",");
			String res=ClientModule.ClientAddAction(VariableModule.HtmlUnitDriver(),ExeArray,ConfigArray,"2"); //ActionModule.ChromedriverAction(); //VariableModule.driver
			ClientModule.ClientDeleteAction(VariableModule.HtmlUnitDriver(),ExeArray,ConfigArray,"2", res);

			ActionModule.ExecutionDoneAction("3","plan",ExeArray[0]);
			 
			 
		}
		else if(ExeArray[3].equals("3")) //PIN Module
		{
			logActionModule.debug("PIN Module is executing");
			String Query="select * from auconfigvar where cvID in(select expbConfigVarID from auexecutionplanbatch where expbID="+ExeArray[1]+")";
			String executiondata=ActionModule.MysqlConnectionAction(Query,VariableModule.Ownconn,",");
			String[] ConfigArray = executiondata.split(",");
			//String pin=ClientModule.SinglePINAddAction(VariableModule.HtmlUnitDriver(),ExeArray,ConfigArray,"2"); //ActionModule.ChromedriverAction() //VariableModule.HtmlUnitDriver()
			//ClientModule.SinglePINDeleteAction(VariableModule.HtmlUnitDriver(),ExeArray,ConfigArray,"2", pin);
			String pinbatch=ClientModule.PINBatchAddAction(VariableModule.HtmlUnitDriver(),ExeArray,ConfigArray,"2");
			
			
			//ClientModule.UploadPIN(ActionModule.ChromedriverAction(),ExeArray,ConfigArray,"1");
			
			ActionModule.ExecutionDoneAction("3","plan",ExeArray[0]);
		}
		else if(ExeArray[3].equals("4"))
		{
			//PermissionModule.JSPTraverseAdminAction(VariableModule.driver, ExeArray);
			//PermissionModule.PermissionAPIAction(VariableModule.driver,ExeArray);
		}
		else
		{
			
		}
		//System.out.println("DONE: "+ExeArray[4]);
		
		
		
	}
	public static void AddPendingAPIQueueAction(String APIdetails, String exeplanID)
	{
		int i;

		String[] QueueArray = APIdetails.split("\n");
		//String[] TimeArray=CheckingTimetoaddinqueueAction(QueueArray);
		//ActionModule.ConsolPrint("Lenght: "+VarArray.length,"Test");
		//String[] ExeTimeArray=CheckingTimewithsystemtimeAction(TimeArray);
		for(i=0;i<QueueArray.length;i++)
		{
			VariableModule.APIpendingQueue.add(exeplanID+"*"+QueueArray[i]);

		}
	}
	public static void OngoingplanstatusUpdateAction(String status)
	{
		
		String exeIDmerge="";
		//ActionModule.ConsolPrint("Lenght: "+VariableModule.InstantpendingQueue.size(),"Test");
		Iterator itr=VariableModule.InstantpendingplanQueue.iterator();  
		while(itr.hasNext())
		{
			
			String tempArray=itr.next().toString();
			//System.out.println("Plan: "+tempArray);
			String[] QueueArray = tempArray.split(",");
			exeIDmerge=exeIDmerge.concat(QueueArray[0]+",");
		}

		//ActionModule.ConsolPrint("Merge: "+exeIDmerge,"Test");
		if(!exeIDmerge.equals(""))
		{
			exeIDmerge=exeIDmerge.substring(0, exeIDmerge.length()-1);
			String Myquery="update auexecutionplan set expType="+status+" where expType=1 and expID in ("+exeIDmerge+")";
			//ActionModule.ConsolPrint("Update exe plan: "+Myquery,"Test");
			MysqlConnectionActionUpdate(Myquery);
			logActionModule.debug("Updated to ongoing plan");
		}
		
		
	}
	
	public static void ExecutionDoneAction(String status, String mode, String ID)
	{
		
		if(mode.equals("batch"))
		{
			String Myquery="update auexecutionplanbatch set expbExeType="+status+",expbTime="+System.currentTimeMillis()+" where expbExeType=2";
			//ActionModule.ConsolPrint("Update exe plan: "+Myquery,"Test");
			MysqlConnectionActionUpdate(Myquery);
			//VariableModule.InstantpendingQueue.clear();
		}
		else if(mode.equals("plan"))
		{
			String Myquery1="update auexecutionplan set expType="+status+" where expType=2 and expID="+ID;
			//ActionModule.ConsolPrint("Update exe plan: "+Myquery,"Test");
			MysqlConnectionActionUpdate(Myquery1);
			//VariableModule.InstantpendingplanQueue.clear();
		}
	}
	
	//@SuppressWarnings("null")
	public static String[] CheckingTimetoaddinqueueAction(String[] executiontime)
	{
		String[] TimeArray = new String[executiontime.length];
		int i;
		for(i=0;i<executiontime.length;i++)
		{
			String[] TimeArray1 = executiontime[i].split(",");
			ActionModule.ConsolPrint("Time: "+TimeArray1[8],"Test");
			TimeArray[i]=TimeArray1[8];
		}
		
/*		for(i=0;i<TimeArray.length;i++)
		{
			ActionModule.ConsolPrint("Lenght: "+TimeArray.length,"Test");
			ActionModule.ConsolPrint("Element: "+TimeArray[i],"Test");
		}*/
		
		//ActionModule.ConsolPrint("Lenght: "+VarArray.length,"Test");

		return TimeArray;
	}
	
	public static String[] CheckingTimewithsystemtimeAction(String[] executiontime)
	{
		String[] TimeArray = new String[executiontime.length];
		int i;
		for(i=0;i<executiontime.length;i++)
		{
			long Time=System.currentTimeMillis();
			if(Time>Long.parseLong(TimeArray[i]))
			{
				
			}
			String[] TimeArray1 = executiontime[i].split(",");
			ActionModule.ConsolPrint("Time: "+TimeArray1[8],"Test");
			TimeArray[i]=TimeArray1[8];
		}
		
/*		for(i=0;i<TimeArray.length;i++)
		{
			ActionModule.ConsolPrint("Lenght: "+TimeArray.length,"Test");
			ActionModule.ConsolPrint("Element: "+TimeArray[i],"Test");
		}*/
		
		//ActionModule.ConsolPrint("Lenght: "+VarArray.length,"Test");

		return TimeArray;
	}
	
	public static void ExecWebRequest(int flag) {
		try {
			String clientID;
			clientID = JOptionPane.showInputDialog("Client ID");
        //URL yahoo = new URL("https://script.googleusercontent.com/macros/echo?user_content_key=y8yCqBm1LZRCnmIXm5f7nRb8oYAK47xClv0AydFootWMkEp9nY15btjwNcO854IWcbZ0afk9-WgXFY_sc7NhEFVmLTc8-4d2m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnJ9GRkcRevgjTvo8Dc32iw_BLJPcPfRdVKhJT5HNzQuXEeN3QFwl2n0M6ZmO-h7C6bwVq0tbM60-u75L0xvd6HiaTderZSNV8A&lib=MwxUjRcLr2qLlnVOLh12wSNkqcO1Ikdrk");
        URL yahoo = new URL("http://162.222.186.235/billing/api/referralAPI.jsp?user="+clientID+"&password=25839d484968f1037581b4bc8bcc7b8e&nonce="+getNonce(clientID));
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
          }
        in.close();
          
        //StandardTimeParser(in,flag);

		}
		catch (IOException e) {
            System.err.println("Problem in executing web url");
        }
	}
	
	public static WebDriver ChromedriverAction() {
	    System.setProperty("webdriver.chrome.driver","/usr/local/iTelTestAutomation/chromedriver");
	    WebDriver driver = new ChromeDriver();
	    return driver;
	}
	
/*	public WebDriver HtmlUnitDriverAction() {

	driver.get("http://softwaretestingbooks.com/");
	System.out.println(driver.getTitle());
	driver.quit();
	    return driver;
	}*/
	
	public static void FolderCreateAction(File T) {
        if(!T.exists()){  
        	if(T.mkdir()){ 
        		//System.out.println("directory is  created"); 
        		}
        	}  
        else { 
        	//System.out.println("directory exist");  
        	}
	}
	
	public void FileBackupAction() {
        

		File filelog = new File(VariableModule.driverlog+"/WebDriver/Logs");
        File fileexcel = new File(VariableModule.driverlog+"/WebDriver/Reports");
        File summaryfile = new File(VariableModule.driverlog+"/WebDriver/Summary");
        
        FolderCreateAction(filelog);
		String currentDate = ActionModule.getCurrentDate();
/*		int randomValue = (int )(Math.random() * 500 + 1);
        File oldName = new File(VariableModule.driverlog+"/WebDriver/Logs/Output.txt");
        File newName = new File(VariableModule.driverlog+"/WebDriver/Logs/Output-"+currentDate+".txt");
        oldName.renameTo(newName);*/
        
        FolderCreateAction(summaryfile);
/*        File oldSummaryName = new File(VariableModule.driverlog+"/WebDriver/Summary/TestSummary.txt");
        File newSummaryName = new File(VariableModule.driverlog+"/WebDriver/Summary/TestSummary-"+currentDate+".txt");
        oldSummaryName.renameTo(newSummaryName);*/
        
        FolderCreateAction(fileexcel);
        ExcelFileCreateAction();
	}
	
	public static void TestSummaryCreator(String ModuleName, int totalcases, int totalsuccessfulcases) {
		int totalfailedcases=totalcases-totalsuccessfulcases;
		int failedpercentage=(totalfailedcases*100)/totalcases;
		System.out.println("Summary Creator");
        try {
            //Whatever the file path is.
        	File WFile = new File(VariableModule.driverlog+"/WebDriver/Summary/TestSummary.txt");
        	//File WFile = new File(file_name);
        	FileWriter fw = new FileWriter(WFile,true);
/*            FileOutputStream is = new FileOutputStream(WFile);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            Writer w = new BufferedWriter(osw);*/
        	
        	if(ModuleName!=null)
        	{
        	fw.write("**************"+ModuleName+"*************"+"\n");
        	fw.write("Module Name: "+ModuleName+"\n");
        	fw.write("Total Test Cases: "+totalcases+"\n");
        	fw.write("Total Successful Cases: "+totalsuccessfulcases+"\n");
        	fw.write("Total Failed Cases: "+totalfailedcases+"\n");
        	fw.write("Percentage of Failed Cases: "+failedpercentage+" %"+"\n");
        	}
            //fw.write(wget);
            //fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file TestSummary.txt");
        }
	}
	
	
/*	private void setRegionBorderWithMedium(CellRangeAddress region, Sheet sheet) {
        Workbook wb = ((XSSFSheet) sheet).getWorkbook();
        RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, region, (org.apache.poi.ss.usermodel.Sheet) sheet, wb);
        RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, region, (org.apache.poi.ss.usermodel.Sheet) sheet, wb);
        RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, region, (org.apache.poi.ss.usermodel.Sheet) sheet, wb);
        RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, region, (org.apache.poi.ss.usermodel.Sheet) sheet, wb);
    }*/
	public static void ExcelFileCreateAction() {
			 try {
					String currentDate = getCurrentDate();
					int randomValue = (int )(Math.random() * 500 + 1);
			        File oldName = new File(VariableModule.driverlog+"/WebDriverReports/Report.xlsx");
			        File newName = new File(VariableModule.driverlog+"/WebDriver/Reports/Report"+'-'+currentDate+'-'+randomValue+".xlsx");
			        oldName.renameTo(newName);
				XSSFWorkbook workbook = new XSSFWorkbook();
				FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/WebDriver/Reports/Report.xlsx"));
				XSSFSheet Spreadsheet = workbook.createSheet("Report-"+currentDate);
				XSSFRow header = Spreadsheet.createRow(0);
				//XSSFCellStyle style = workbook.createCellStyle();
			    header.createCell(0).setCellValue("Test Case");
			    header.createCell(1).setCellValue("Testing Result");
	/*			    style.setBorderTop(BorderStyle.THIN);
			    style.setBorderBottom(BorderStyle.MEDIUM);
			    style.setBorderLeft(BorderStyle.MEDIUM);
			    style.setBorderRight(BorderStyle.MEDIUM);*/
				workbook.write(out);
				out.close();
			}
			catch(Exception e) {
				System.out.println(e);
				
			}
		}
	
	@SuppressWarnings("null")
	public static void ExeResultInsertion(String planID, String result, String status)
	{
		String sql=null;
	    //Connection conn = null;
	    
	    java.sql.Statement stmt = null;

	    try {
	    	//conn=MysqlConnection();
	        stmt = VariableModule.Ownconn.createStatement();

		        sql = "INSERT INTO auexecutionresult (erExePlanID,erResult,erStatus,erExeTime)" +
			            " VALUES ("+planID+",'"+result+"',"+ status +","+System.currentTimeMillis()+")";
		        //ConsolPrint("SQL: "+sql,"");
		        logActionModule.debug("SQL: "+sql);


	        stmt.executeUpdate(sql);

	        //ConsolPrint(" Result Inserted Successfully!","");
	        logActionModule.debug(" Result Inserted Successfully!");
	        stmt.close();
	    } catch(SQLException se) {
	        se.printStackTrace();
	    } catch(Exception e) {
	        e.printStackTrace();
	    } 
	    
	}
	
	public static void ExcelFileWriteAction(String testcase, String result) {
		
		if(result!="") { //Cases added
		       

			XSSFCell cell = null; 
		
				//System.out.println("Writer Enabled"+RowCount);
				try {
					FileInputStream fIPS= new FileInputStream(VariableModule.driverlog+"/WebDriver/Reports/Report.xlsx");
					XSSFWorkbook workbook = new XSSFWorkbook(fIPS);
					XSSFSheet worksheet = workbook.getSheetAt(0);
					
					fIPS.close();
					FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/WebDriver/Reports/Report.xlsx"));
					//XSSFSheet worksheet = workbook.getSheetAt(0);
			        XSSFCellStyle headerStyle = workbook.createCellStyle();
			        Font headerFont = workbook.createFont();
					XSSFRow row = worksheet.createRow(RowCount);

				       //indexOf return -1 if String does not contain specified word
				       if(result.indexOf("Failed") != -1){
				           //System.err.printf("Yes '%s' contains word 'Failed' %n" , result);
					        //font.setColor(IndexedColors.RED.getIndex());
					        //style.setFont(font);
							row.createCell(0).setCellValue(testcase);
						    //header.createCell(1).setCellValue(result);
						    cell = row.createCell(1);
						    cell.setCellValue(result);

					        //headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					        headerFont.setColor(IndexedColors.RED.getIndex());
					        //headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					        headerStyle.setFont(headerFont);
					        
					        
				       }
				       else if(result.indexOf("null") != -1){
				           //System.err.printf("Yes '%s' contains word 'null' %n" , result);
					        //font.setColor(IndexedColors.RED.getIndex());
					        //style.setFont(font);
							row.createCell(0).setCellValue("");
						    //header.createCell(1).setCellValue(result);
						    cell = row.createCell(1);
						    cell.setCellValue(result);

					        //headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					        headerFont.setColor(IndexedColors.BLUE.getIndex());
					        //headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					        headerStyle.setFont(headerFont);
					        
					        
				       }
				       else {
							row.createCell(0).setCellValue(testcase);
						    //header.createCell(1).setCellValue(result);
						    cell = row.createCell(1);
						    cell.setCellValue(result);
				       }
				    cell.setCellStyle(headerStyle);
					workbook.write(out);
					out.close();
					
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}
				RowCount++;
		}
		else //Module name added
		{
			
			RowCount=RowCount+3;
			//System.out.println("Writer Enabled with null"+RowCount);
			XSSFCell cell = null; 
			try {
				FileInputStream fIPS= new FileInputStream(VariableModule.driverlog+"/WebDriver/Reports/Report.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fIPS);
				XSSFSheet worksheet = workbook.getSheetAt(0);
				
				fIPS.close();
				
				FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/WebDriver/Reports/Report.xlsx"));
				XSSFRow row = worksheet.createRow(RowCount);
				cell = row.createCell(0);
				//cell = row.createCell(1);

				worksheet.addMergedRegion(new CellRangeAddress(RowCount,RowCount,0,1));
			    cell.setCellValue(testcase);
				workbook.write(out);
				out.close();
				
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			RowCount=RowCount+3;
		}
	}
	
/*	public void ExcelFileWriteHeaderAction(String testheader) {
		RowCount=RowCount+3;
		//System.out.println("Writer Enabled");
		try {
			FileInputStream fIPS= new FileInputStream("C:\\WebDriver\\Reports\\Report.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fIPS);
			XSSFSheet worksheet = workbook.getSheetAt(0);
			
			fIPS.close();
			
			FileOutputStream out = new FileOutputStream(new File("C:\\WebDriver\\Reports\\Report.xlsx"));
			XSSFRow header = worksheet.createRow(RowCount);
			header.createCell(0).setCellValue(testheader);
			worksheet.addMergedRegion(new CellRangeAddress(RowCount,RowCount,0,1));
		    //header.createCell(0).setCellValue(testheader);
			workbook.write(out);
			out.close();
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		RowCount=RowCount+3;
	}
	*/
	

	
    public static void writing(String wget) {
        try {
            //Whatever the file path is.
        	File WFile = new File(VariableModule.driverlog+"/WebDriver/Logs/Output.txt");
        	//File WFile = new File(file_name);
        	FileWriter fw = new FileWriter(WFile,true);
/*            FileOutputStream is = new FileOutputStream(WFile);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            Writer w = new BufferedWriter(osw);*/
        	
        	if(wget!=null)
        	{
        	fw.write("\n"+wget+"\n");
        	}
            //fw.write(wget);
            //fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file Output.txt");
        }
    }
    
    
    
    public static void writinginFile(String replaceWith, String newthreshold) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(VariableModule.driverlog+"/WebDriver/Config/Variable.txt"));
            String line;
            StringBuffer inputBuffer = new StringBuffer();
            String matchstr = null;
            while ((line = file.readLine()) != null) {
                if (line.contains(replaceWith)) {
                    matchstr=line;
                    
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            String inputStr = inputBuffer.toString();

            file.close();

            //System.out.println("Replace Threshold: \n"+inputStr); // check that it's inputted right

            // this if structure determines whether or not to replace "0" or "1"
            
            //System.out.printf("%.2f",newthreshold);
            //newthreshold = String.format("%.2f");
            if (inputStr.contains(replaceWith)) {
                inputStr = inputStr.replace(matchstr, replaceWith+newthreshold+";"); 
                ActionModule.ConsolPrint("Date is updated with "+newthreshold,"");
                ActionModule.writing("Date Value is updated with "+newthreshold);
            }

            
            // check if the new input is right
            //System.out.println("----------------------------------\n"  + inputStr);

            // write the new String with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(VariableModule.driverlog+"/WebDriver/Config/Variable.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
    
    public static void ConsolPrint(String wget, String testresult) {

    	if(VariableModule.isWindows.equals("1"))
    	{
    	System.out.println(wget+"\n");
    	}
    }
    
	public static String BaseURL() 
	{
		
		//String url = variabledo.baseUrl;
		return variabledo.baseUrl;
		
	}
	
/*	public void DriverAction(String take_driver) 
	{
		if(take_driver=="firefox"){
			System.setProperty("webdriver.gecko.driver","E:\\Automation\\geckodriver-v0.13.0-win64\\geckodriver.exe");
		    WebDriver driver = new FirefoxDriver();
		}else if(take_driver=="chrome"){
		    System.setProperty("webdriver.chrome.driver","E:/Automation/chromedriver_win32/chromedriver.exe");
		    WebDriver driver = new ChromeDriver();
		}else {
			
		}
	}
	
	public void DriverCloseAction(String close_driver) 
	{
			//close_driver.close();
	}*/
	public static String[] TokenizerAction(String splitString) //http://crunchify.com/java-stringtokenizer-and-string-split-example/
	{
		
		String delims = "=|,|:";
		//String delims = "[=+]";
		String[] tokens=splitString.split(delims);
/*	    for (int i=0; i< tokens.length; i++){
	      System.out.println("StringTokenizer Output: " +tokens[i]);
	    }*/
	    //System.out.println("StringTokenizer: " +tokens[3]);
	    return tokens;
	}
	
	
	public String getMD5(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
    public static String getCurrentHour() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateobject = new SimpleDateFormat("HH");
        return dateobject.format(date);
    }
    
	public String xorEncrypt(String pass, String xnonce) {
		String xoredPass = "";        
		StringBuilder builder = new StringBuilder();        
		for (int i = 0; i < pass.length(); i++) {            
			builder.append((char) (pass.charAt(i) ^ xnonce.charAt(i % xnonce.length())));            
			xoredPass = builder.toString();        
		}        
		//System.out.println("XOR Encoded Value:"+xoredPass);
		return xoredPass;    
	}

    public static String getCurrentDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateobject = new SimpleDateFormat("ddMMyyyy-HHmmss");
        return dateobject.format(date);
    }
    
    public static String subjectDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateobject = new SimpleDateFormat("dd-MMM-yyyy");
        return dateobject.format(date);
    }
    
    public static String getDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateobject = new SimpleDateFormat("yyyy-MM-dd");//'2018-04-04 00:00:00
        return dateobject.format(date);
    }
    public static String getDayofWeek(String inputdate) {
        Date date = Calendar.getInstance().getTime();
        //SimpleDateFormat dateobject = new SimpleDateFormat("yyyy-MM-dd");//'2018-04-04 00:00:00
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(inputdate, formatter); // LocalDate = 2010-02-23
        DayOfWeek dow = date1.getDayOfWeek();  // Extracts a `DayOfWeek` enum object.
        String output = dow.getDisplayName(TextStyle.SHORT, Locale.US);
        //System.out.println("Day of Week: "+output);
        return output;
    }
    public static Double getCurrentBalance(String pin) {
		String[] cardInfo=TokenizerAction(MysqlConnectionAction("select cbCreditLimit,cbBalance from vbClientBalance where cbAccountID in(select clAccountID from vbClient where clCustomerID='"+pin+"')", VariableModule.Remoteconn,","));
		//System.out.println("Current Balance: "+cardInfo[0]+"\t"+cardInfo[1]);
		double curbalance=Double.parseDouble(cardInfo[0]);
		curbalance=curbalance+Double.parseDouble(cardInfo[1]);
        return curbalance;
    }
    
    public static String randomIPGenerator() {
    	int[] IPnums = new int[5];
        for(int i=0;i<=3;i++){
        int randomIPpart = (int )(Math.random() * 250 + 1);
        IPnums[i]=randomIPpart;
        }
        String randIP="";
        randIP=IPnums[0]+"."+IPnums[1]+"."+IPnums[2]+"."+IPnums[3];
        //System.out.println(randIP);
		return randIP;
    }

    public static String callerIDset() {
        int callerIDGenerator = (int )(Math.random() * 9999999 + 100000);
        String callerIDGen=""+callerIDGenerator;
        //System.out.println(callerIDGen);
		return callerIDGen;
    }
    
    public static String EmailIDgenerator() {
        String emailIDGen=RandomStringUtils.randomAlphabetic(5)+"@yahoo.com";
        //System.out.println(emailIDGen);
		return emailIDGen;
    }

    public static void PlayAlarmAction()
    {
        // open the sound file as a Java input stream
        String alarmFile = VariableModule.driverlog+"/WebDriver/MyAlarm.wav";
        
/*		try {
			in = new FileInputStream(alarmFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

        // create an audiostream from the inputstream
/*        InputStream in;
        AudioStream audioStream = null;
		try {
			in = new FileInputStream(alarmFile);
			audioStream = new AudioStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);*/
    }
    
    public static void TrayAction() {
	   if(!SystemTray.isSupported()){
	        System.out.println("System tray is not supported !!! ");
	        //return ;
	    }
	   

	    Image image = Toolkit.getDefaultToolkit().getImage(VariableModule.driverlog+"/WebDriver/MyIcon.gif");
	    PopupMenu trayPopupMenu = new PopupMenu();
	    final TrayIcon trayIcon = new TrayIcon(image, "iTel Test Automation Tool", trayPopupMenu);
	    SystemTray systemTray = SystemTray.getSystemTray();


	    showNotification(1,"Warning!!!", "Total Test Cases: \t\t"+variabledo.totalcases+"\nTotal Failed Cases: \t"+variabledo.totalfailedcases,trayIcon);
	    
	    MenuItem close = new MenuItem("Close");
	    close.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0);             
	        }
	    });
	    trayPopupMenu.add(close);

	    trayIcon.setImageAutoSize(true);

	    try{
	        systemTray.add(trayIcon);
	    }catch(AWTException awtException){
	        awtException.printStackTrace();
	    }
	    //return trayIcon;
    }
    
    public static void showNotification(final int flag, final String title, final String msg, final TrayIcon trayIcon) {


	    trayIcon.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 1) {
	                // single click
	            	trayIcon.displayMessage(title, "Total Test Cases: \t\t"+variabledo.totalcases+"\nTotal Failed Cases: \t"+variabledo.totalfailedcases, TrayIcon.MessageType.INFO);
	            }
	        }
	        
	    });
	    
    	//trayIcon.setToolTip(msg);
    	
    	
	    
	    
}
    
   public static boolean GetCongratulation(WebDriver driver, String matchstr)
   {
	   boolean flag = false;
		String getCongrats=driver.findElement(By.tagName("body")).getText();
		System.out.println("Body: \n"+getCongrats);
		Scanner scanner = new Scanner(getCongrats);
		while (scanner.hasNextLine()) {
		  //String line = scanner.nextLine();
		  @SuppressWarnings("resource")
		Scanner line = new Scanner(scanner.nextLine());
		  // process the line
	        while (line.hasNext()) {
	            String word = line.next();
	            //System.out.println("Line: "+word);
				if(word.indexOf(matchstr) != -1){
					System.out.println(matchstr+" Found");
					logActionModule.debug(matchstr+" Found");
					flag=true;
		        	
				}

	        }

		}
		scanner.close();
		return flag;
   }

}
