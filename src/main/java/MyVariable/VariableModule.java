package MyVariable;
import Login.LoginModule; 
import MyAction.ActionModule;
import MyAutomation.MyClass;
import MyEmail.EmailModule;
import MyPermission.PermissionModule;
import Socket.Server;
import org.apache.log4j.Logger;
import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

//import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;



public class VariableModule {
	//ActionModule actiondo = new ActionModule();
	static Logger logVariableModule = Logger.getLogger(VariableModule.class.getName());
	
	//log.info("Error in: " + this.getClass.getName() + "at line #"+ this.getClass.getActualLine());
	
	static PendingTestInstantModule Threadpendingtestinstantdo = new PendingTestInstantModule();
	static PendingTestScheduleModule Threadpendingtestscheduledo = new PendingTestScheduleModule();
	static ExecutionPendingTestInstantModule Threadexetestinstantdo = new ExecutionPendingTestInstantModule();
	static TodayBugSummaryAutoEmailer Threadtodaybugmailerdo = new TodayBugSummaryAutoEmailer();
	static MySQLPINGModule ThreadMysqlPingdo = new MySQLPINGModule();
	static DailyReportAutoEmailer Threaddailyreportmailerdo = new DailyReportAutoEmailer();
	static WeeklyBugSummaryAutoEmailer Threadweeklyreportmailerdo = new WeeklyBugSummaryAutoEmailer();
	static ProjectCreationAutoEmailer Threadprojectcreationdo = new ProjectCreationAutoEmailer();
	static ProjectDeliveryAutoEmailer Threadprojectdeliverydatedo = new ProjectDeliveryAutoEmailer();
	
	//public Static String file_name="E:/Automation/Logs/Output.txt";
	public static String driverlog="/usr/local/iTelTestAutomation"; // linux=/usr/local/iTelTestAutomation // Windows=G:/WorkStation/iTelTestAutomation
	public static String isWindows=readVariable("isWindows",1); //1 for windows, 2 for linux
	public static String isGUI="2"; // 1 for GUI, 2 for non GUI
	public static String server_IP="localhost"; //162.222.186.235
	private static int server_Port=5000;
	public static long sleepvalue=30000;
	public static String ColorCode = readVariable("ColorCode",1);
	public static String sleepTime=readVariable("sleepTime",1); //in minute
	public static String LastWeekDay = readVariable("LastWeekDay",1);
	public static String LastDailyReportMailDay = readVariable("LastDailyReportMailDay",1);
	public static String WeeklyMailDay = readVariable("WeeklyMailDay",1);
	//public static String isDB="0";
	public static WebDriver driver=new HtmlUnitDriver();
	
	public static String[][] repodata=new String[3][18];//{{}};
	public static ArrayList<String>[][] repolist = new ArrayList[][] {};
	public static Queue<String> InstantpendingQueue = new LinkedList<>();
	public static Queue<String> InstantpendingplanQueue = new LinkedList<>();
	public static Queue<String> SchedulependingQueue = new LinkedList<>();
	
	public static Queue<String> APIpendingQueue = new LinkedList<>();
	public static Queue<String> EXEIDQueue = new LinkedList<>();
	
	public static Connection Ownconn = null;
	public static Connection Remoteconn = null;
	
	public static String expectedTitle = readVariable("expectedTitle",1);
	public static String expectedTitle1 = readVariable("expectedTitle1",1);
	public static String expectedTitle2 = readVariable("expectedTitle2",1);
	public static String actualTitle = "";
	public static String mysqlurl = readVariable("mysqlurl",1);
	//public static String chromedriver = "G:/WorkStation/WebDriver/chromedriver_new.exe";
	public static String firefoxdriver = readVariable("firefoxdriver",1);
	public static String baseUrl = readVariable("baseUrl",1);
    public static String MySQLRemoteIP = "149.20.188.8";//"162.222.186.235";
    public static String RemoteDBName = "iTelBillingfresh705"; //iTelBillingfresh705 //iTelBillingrelease705
	public static String dbusername = readVariable("dbusername",1);
	public static String dbpassword = readVariable("dbpassword",1);
	//driverlog = readVariable("driverlog",1);
    // DB info
    public static String MySQLIP = readVariable("MySQLIP",1); //"149.20.188.8";
    public static String DBName = readVariable("DBName",1);//testautomation //SQADatabase
    public static String DBUser = readVariable("dbusername",1);
    public static String DBPass = readVariable("dbpassword",1);
    
    
    public static String FontFamily = readVariable("FontFamily",1); //"Calibri";
    public static String FontSize = readVariable("FontSize",1); //"15px";
	
    public static String admin = readVariable("admin",1);
    public static String reseller = readVariable("ctg",2);
    public static String agent = readVariable("Agent-10",2);
    public static String adminpass = readVariable("adminpass",1);
    public static String pin = readVariable("pin",1);
    public static String pin1 = readVariable("pin1",1);
    public static String PIN4 = readVariable("PIN4",2);
    public static String ResellerPIN = readVariable("ResellerPIN",2);
    public static String DRatewithzeroBPIN = readVariable("DRatewithzeroBPIN",2);
    public static String DeletedPIN = readVariable("DeletedPIN",2);
    public static String BlockedPIN = readVariable("BlockedPIN",2);
    public static String ExpiredPIN = readVariable("ExpiredPIN",2);
    public static String ZeroBalancePIN = readVariable("ZeroBalancePIN",2);
    
    public static String pinPass = readVariable("pinPass",1);
    public static String RatePlanID = readVariable("RatePlanID",2);
    public static String NOrcBatchID = readVariable("NOrcBatchID",2);
    public static String YESrcBatchID = readVariable("YESrcBatchID",2);
    public static String DIDPIN = readVariable("DIDPIN",2);
    public static String DIDPINPass = readVariable("DIDPINPass",2);
    
    public static String nonce = "";
    public static String hashValue = "";
    public static String strforMD5 = "";
    //System.out.println(commonAPIUrl);
	//ActionModule actiondo = new ActionModule();
    
    // SMTP info
    public static String mailhost = readVariable("mailhost",1);
    public static String mailport = readVariable("mailport",1);
    public static String mailToB = readVariable("mailToB",1);
    public static String mailToC = readVariable("mailToC",1);
    public static String mailFrom = readVariable("mailFrom",1);
    public static String mailpassword = readVariable("mailpassword",1);
    public static String summarymailtime = readVariable("summarymailtime",1);
    public static String weeklysummarymailtime = readVariable("weeklysummarymailtime",1);
    public static String TestReport = readVariable("TestReport",1);
    
    public static String AssignerArray = readVariable("AssignerArray",1);
    public static String[] AssignerArray1 = AssignerArray.split(",");

    public static String AssignerfullnameArray = readVariable("AssignerfullnameArray",1);
    public static String[] AssignerfullnameArray1 = AssignerfullnameArray.split(",");
    
    // permission sql variable
    public static String mailTo = readVariable("mailTo",1);
    public static String mailsubject = readVariable("subject",1);
    public static String mailmessage = readVariable("mailmessage",1);

    public static String clCustomerID = readVariable("clCustomerID",2);
    public static String gtAccountID = readVariable("gtAccountID",2);
    public static String pbName = readVariable("pbName",2);
    public static String rcbName = readVariable("rcbName",2);
    public static String rpName = readVariable("rpName",2);
    public static String drpRPID = readVariable("drpRPID",2);
    public static String dtDestCode = readVariable("dtDestCode",2);
    public static String dccNumber = readVariable("dccNumber",2);
    public static String ogNO = readVariable("ogNO",2);
    public static String didNO = readVariable("didNO",2);
    public static String csName = readVariable("csName",2);
    public static String rtID = readVariable("rtID",2);
    public static String raLogin = readVariable("raLogin",2);
    public static String toprpName = readVariable("toprpName",2);
    public static String usUsername = readVariable("usUsername",2);
    public static String rlRoleName = readVariable("rlRoleName",2);
    public static String pkgName = readVariable("pkgName",2);
    public static String languageName = readVariable("languageName",2);
    public static String roName = readVariable("roName",2);
    public static String poName = readVariable("poName",2);
    
    public static int totalcases=0;
    public static int totalfailedcases=0;
    
    //public TrayIcon trayIcon=ActionModule.TrayAction();
    // attachments
    public static String[] attachFiles = {
			//"G:\\WorkStation/WebDriver/Reports/Report.xlsx",
			//"/GetClient.do?id=",
			//"/ViewAccountRecharge.do?clCustomerID="
    };
    
    //attachFiles[0] = "C:\\WebDriver\\Reports\\Report.xlsx";
    //attachFiles[1] = "e:/Test/Music.mp3";
    //attachFiles[2] = "e:/Test/Video.mp4";
    
    public static void StartModule()
    {
    	//log.info("Test");
    	//System.out.println("Test");
/*    	try {
			Socket socket = new Socket(server_IP,server_Port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	//CommonThread();
    	
    	
    	try {
			if(VariableModule.isWindows.equals("2")) //Linux
			{
				driver = new HtmlUnitDriver();
				CommonThread();
				//PermissionModule.JSPTraverseAdminAction(driver);
			}
			else //Windows
			{
				if(VariableModule.isGUI.equals("1"))
				{
					SwingUtilities.invokeLater(new Runnable() {

			            @Override
			            public void run() {
			                new MyClass().setVisible(true);
			                
			            }
					});
				}
				else // non GUI
				{
					//DBCompare();
					
					driver = new HtmlUnitDriver();
					CommonThread();
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    }
    public static void DBCompare()
    {
    	//String[] columnName720= {""};
    	//String[] columnName705= {""};
    	String[] missmatchTable = new String[300];
    	int m=0;
    	ActionModule.MysqlConnectOwn(Ownconn);
    	ActionModule.MysqlConnectRemote(Remoteconn);
    	String db720=ActionModule.MysqlConnectionAction("show tables",Ownconn,",");
    	//String db705=ActionModule.MysqlConnectionAction("show tables",Remoteconn,",");
    	//System.out.println("DB720: "+db720);
    	//System.out.println("DB705: "+db705);
    	String[] tablenameArray720 = db720.split("\n");
    	//System.out.println("Lenght: "+tablenameArray720.length);
    	for(int i=0;i<tablenameArray720.length;i++) //tablenameArray720.length
    	{
    		try {
				System.out.println("TableName: "+tablenameArray720[i]);
				String tablestructure720=ActionModule.MysqlConnectionAction("desc "+tablenameArray720[i],Ownconn,",");
				String tablestructure705=ActionModule.MysqlConnectionAction("desc "+tablenameArray720[i],Remoteconn,",");
				//System.out.println("Structure: "+tablestructure720);
				if(!tablestructure720.equals(""))
				{
					if(!tablestructure705.equals(""))
					{
						String[] tablestructureArray720 = tablestructure720.split("\n");
						String[] tablestructureArray705 = tablestructure705.split("\n");
						System.out.println("column Lenght720: "+tablestructureArray720.length);
						System.out.println("column Lenght705: "+tablestructureArray705.length);
						if(tablestructureArray720.length==tablestructureArray705.length)
						{
				    		String[] columnName720 = new String[tablestructureArray720.length];
				    		String[] columnName705 = new String[tablestructureArray705.length];
				    		for(int j=0;j<tablestructureArray720.length;j++)
				    		{
				    			String[] temp720 = tablestructureArray720[j].split(",");
				    			columnName720[j]=temp720[0];
				    			String[] temp705 = tablestructureArray705[j].split(",");
				    			columnName705[j]=temp705[0];
				    			//System.out.println("columnName720: "+columnName720[j]);
				    			//System.out.println("columnName705: "+columnName705[j]);
				    		}
				    		
				    		for(int k=0;k<columnName720.length;k++)
				    		{
				    			for(int p=0;p<columnName705.length;p++)
				    			{
					    			if(!columnName720[k].equals(columnName705[p]))
					    			{
					    				if(p<columnName705.length-1)
					    				{
					    					continue;
					    				}
					    				else
					    				{
						    				missmatchTable[m]=tablenameArray720[i];
						    				System.out.println("Column Name Missmatch Found");
						    				m++;
						    				break;
					    				}
					    			}
					    			else
					    			{
					    				break;
					    			}
				    			}

				    		}
				    		System.out.println("==========================");
				    		
				    		
						}
						else
						{
							missmatchTable[m]=tablenameArray720[i];
							System.out.println("Column Missmatch Found");
							m++;
						}
					}

				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	System.out.println("MissMatch Tables: ");
    	for(int n=0;n<m;n++)
    	{
    		System.out.println((n+1)+". "+missmatchTable[n]);
    	}
    }
    public static void CommonThread()
    {

    	//System.out.println("Current Thread: "+Thread.currentThread().getName());
    	
    	ActionModule.MysqlConnectOwn(Ownconn);
    	
    	
    	ThreadMysqlPingdo.setPriority(Thread.MAX_PRIORITY);
    	ThreadMysqlPingdo.start();
    	
    	Threadpendingtestscheduledo.setPriority(ThreadMysqlPingdo.getPriority()-1);
    	Threadpendingtestscheduledo.start();
    	
    	Threadpendingtestinstantdo.setPriority(Threadpendingtestscheduledo.getPriority()-1);
    	Threadpendingtestinstantdo.start();
    	
    	Threadexetestinstantdo.setPriority(Threadpendingtestinstantdo.getPriority()-1);
    	Threadexetestinstantdo.start();
    	
    	
    	
    	Threadtodaybugmailerdo.setPriority(Threadexetestinstantdo.getPriority()-1);
	    Threadtodaybugmailerdo.start();
    	
    	Threaddailyreportmailerdo.setPriority(Threadtodaybugmailerdo.getPriority()-1);
    	Threaddailyreportmailerdo.start();
    	
    	Threadweeklyreportmailerdo.setPriority(Threaddailyreportmailerdo.getPriority()-1);
    	Threadweeklyreportmailerdo.start();
    	
    	Threadprojectcreationdo.setPriority(Threadweeklyreportmailerdo.getPriority()-1);
    	Threadprojectcreationdo.start();
    	
    	//Threadprojectdeliverydatedo.setPriority(Threadprojectcreationdo.getPriority()-1);
    	//Threadprojectdeliverydatedo.start();
    	
    	

    	
    }
    
	public static String readVariable(String var, int configflag)
	{
		int flag=0;
		int gotvar=0;
		Character ch = new Character('a');
		StringBuilder Test = new StringBuilder("");
		StringBuilder tempvar = new StringBuilder("");
		String path;
		int data;
		try {
			if(configflag==1)
			{
				path=VariableModule.driverlog+"/WebDriver/Config/Variable.txt";
			}
			else
			{
				path=VariableModule.driverlog+"/WebDriver/Config/API-Config.txt";

			}
			Reader fileReader = new FileReader(path);
			data = fileReader.read();
			while(data != -1) {
				ch=(char)data;
				//System.out.println("String: "+ch+flag+gotvar);
				if(flag==0 && ch!='=') {
					Test.append(ch);
				}
				else if(flag==1 && gotvar==1) {
					if(ch!='=' && ch!=';') {
						tempvar.append(ch);
						
					}
				}
				if(ch=='\n') {
					flag=0;
					Test = new StringBuilder("");
				}
				else if(ch=='='){
					flag=1;
					String Test1=Test.toString();
					//System.out.println("Flag:"+Test1);
					//System.out.println("Actual:"+var);
					if(Test1.equals(var))
					{
						//System.out.println("Match Equal"+Test);
						gotvar=1;
					}
				}
				else if(ch==';' && gotvar==1) {
					//System.out.println("Variable Found:"+tempvar);
					break;
				}

				data = fileReader.read();
			  //System.out.println((char)data);
			}
			//String tempvar1=tempvar.toString();
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempvar.toString();
	}
	
	public static WebDriver HtmlUnitDriver()
	{
		WebDriver drive;
		drive = new HtmlUnitDriver();
		return drive;
	}
	
	public static int readArraySize(String var)
	{
		//System.out.println("Read Array Size Call");
		int flag=0;
		int gotvar=0;
		int index=0;
		int item=0;
		String Test1="";
		String[] array = new String[200];
		Character ch = new Character('a');
		Character ch1 = new Character('b');
		StringBuilder Test = new StringBuilder("");
		StringBuilder tempvar = new StringBuilder("\n");

		try {
			Reader fileReader = new FileReader(VariableModule.driverlog+"/WebDriver/Config/Array.txt");

			int data = fileReader.read();
			while(data != -1) {
				ch=(char)data;

				if(flag==0 && ch!='=') {
					//System.out.println("IF1: ");
					Test.append(ch);
					//System.out.println("String: "+Test);
					
				}
				else if(flag==1 && gotvar==1) {
					//System.out.println("IF2: ");
					if(ch!='\n') {
						if(ch!=',') {
							tempvar.append(ch);
						}
						else
						{
							array[index]=tempvar.toString().substring(1);
							//System.out.println("Array Value: "+index+" : "+array[index]);
							//System.out.println("Index: "+index);
							tempvar = new StringBuilder("");
						}
						
					}
					else if (ch=='\n') {
						data=fileReader.read();
						//System.out.println("Data1: "+(char)data);
						ch=(char)data;
						if(ch=='/')
						{
							index++;
							tempvar.append(ch);
							//System.out.println("Next Array: "+tempvar);
						}
						else
						{
							item=1;
							//System.out.println("Break: "+ch);
							break;
						//tempvar.append((char)fileReader.read());
						}
					}

				}
				else if(flag==2) {
					//System.out.println("IF3: ");
					if (ch=='\n') {
						//data=fileReader.read();
						data=fileReader.read();
						ch=(char)data;
						if(ch!='/')
						{
							flag=0;
							Test.append(ch);
							//System.out.println("TempVar:"+Test);
						}
					}
				}
				

				if(flag==0 && ch=='\n'){
					//System.out.println("Actual:"+Test);
					Test1=Test.toString().substring(0, Test.toString().length() - 2);
					//System.out.println("After Actual:"+Test1);
					//System.out.println("IF4: ");
					flag=1;
					//String Test1=Test.toString();
					//System.out.println("New line: :"+ch);
					//System.out.println("Actual:"+var);
					if(Test1.equals(var))
					{
						//System.out.println("Test Value: "+Test1);
						gotvar=1;
					}
					else
					{
						flag=2;
						Test = new StringBuilder("");
					}
				}
				//System.out.println("Flag: "+flag);
				//System.out.println("Index: "+index+"\nChar: "+ch);
				if(item==0) {
					data = fileReader.read();
				}
				else
				{
					data=-1;
					//System.out.println("Data: "+data);
				}
			    //System.out.println("Charact: "+(char)data);
			}
			//String tempvar1=tempvar.toString();
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
/*    	for (int i=0; i< array.length; i++){
    		System.out.println("Array Output: " +array[i]);
    	}*/
		//System.out.println("Index: "+index);
		return index;
	}
	
	public String SHAhash(String password)
	{
        
            MessageDigest sha256 = null;
			try {
				sha256 = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String salt = "some_random_salt1";
            String passWithSalt = "Test" + salt;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] passHash = sha256.digest(passBytes);             
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< passHash.length ;i++) 
            {
                sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));         
            }
            String generatedPassword = sb.toString();
            logVariableModule.debug("GeneratedPassword: "+generatedPassword);
            return generatedPassword;
        
	}
	
	
	public static String[] readArray(String var, int Arraysize)
	{
		//System.out.println("Read Array Call");
		int flag=0;
		int gotvar=0;
		int index=0;
		int item=0;
		String Test1="";
		String[] array = new String[Arraysize+1];
		Character ch = new Character('a');
		Character ch1 = new Character('b');
		StringBuilder Test = new StringBuilder("");
		StringBuilder tempvar = new StringBuilder("\n");

		try {
			Reader fileReader = new FileReader(VariableModule.driverlog+"/WebDriver/Config/Array.txt");

			int data = fileReader.read();
			while(data != -1) {
				ch=(char)data;

				if(flag==0 && ch!='=') {
					Test.append(ch);
					//System.out.println("String: "+Test);
					
				}
				else if(flag==1 && gotvar==1) {
					//System.out.println("IF2: ");
					if(ch!='\n') {
						if(ch!=',') {
							tempvar.append(ch);
						}
						else
						{
							array[index]=tempvar.toString().substring(1);
							//System.out.println("Array Value: "+index+" : "+array[index]);
							//System.out.println("Index: "+index);
							tempvar = new StringBuilder("");
						}
						
					}
					else if (ch=='\n') {
						data=fileReader.read();
						//System.out.println("Data1: "+(char)data);
						ch=(char)data;
						if(ch=='/')
						{
							index++;
							tempvar.append(ch);
							//System.out.println("Next Array: "+tempvar);
						}
						else
						{
							item=1;
							//System.out.println("Break: "+ch);
							break;
						//tempvar.append((char)fileReader.read());
						}
					}

				}
				else if(flag==2) {
					//System.out.println("IF3: ");
					if (ch=='\n') {
						//data=fileReader.read();
						data=fileReader.read();
						ch=(char)data;
						if(ch!='/')
						{
							flag=0;
							Test.append(ch);
							//System.out.println("TempVar:"+Test);
						}
					}
				}
				

				if(flag==0 && ch=='\n'){
					//System.out.println("Actual:"+Test);
					Test1=Test.toString().substring(0, Test.toString().length() - 2);
					//System.out.println("After Actual:"+Test1);
					//System.out.println("IF4: ");
					flag=1;
					//String Test1=Test.toString();
					//System.out.println("New line: :"+ch);
					//System.out.println("Actual:"+Test);
					if(Test1.equals(var))
					{
						//System.out.println("Test Value: "+Test1);
						gotvar=1;
					}
					else
					{
						flag=2;
						Test = new StringBuilder("");
					}
				}
				//System.out.println("Flag: "+flag);
				//System.out.println("Index: "+index+"\nChar: "+ch);
				if(item==0) {
					data = fileReader.read();
				}
				else
				{
					data=-1;
					//System.out.println("Data: "+data);
				}
			    //System.out.println("Charact: "+(char)data);
			}
			//String tempvar1=tempvar.toString();
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
/*    	for (int i=0; i< array.length; i++){
    		System.out.println("Array Output: " +array[i]);
    	}*/
		return array;
	}
	
	//public static String[] DeleteAPIArray= new String[readArraySize("DeleteAPIArray")];
	//public static String[] DeleteAPIArray=readArray("DeleteAPIArray");

	//readArray("DeleteAPIArray");
	//int size=readArraySize("CallerIDAPIArray");
	//public static String[] CallerIDAPIArray= new String[size];

	public static String[] APIArray=readArray("APIArray",readArraySize("APIArray"));
/*    public String[] APIArray = {
			"/ViewClient.do?parent=",
			"/GetClient.do?id=",
			"/ViewAccountRecharge.do?clCustomerID=",
			"/ViewAccountRecharge.do?parent=",
			"/DropClient.do?deleteIDs=",
			//"/RecoverClient.do?deleteIDs=",
			
			"/GetGateway.do?id=",
			"/DropGateway.do?deleteIDs=",
			
			"/pin/pinBatchView.jsp?id=",
			"/pin/pinBatchEdit.jsp?id=",
			"/ViewpinBatchDetails.do?pinBatchID=",
			"/pin/blockUnblockPINSeries.jsp?id=",
			"/DropPinBatch.do?deleteIDs=",
			
			"/ViewAccountRecharge.do?search=yes&clCustomerID=",
			"/ViewPinRecharge.do?search=yes&clCustomerID=",
			
			"/ViewRechargeCardBatchDetails.do?RechargeCardBatchID=",
			"/rechargecard/rechargeCardBatchView.jsp?id=",
			"/rechargecard/blockUnblockCardSeries.jsp?id=",
			"/rechargecard/rechargeCardBatchDetailsEdit.jsp?",
			"/DropRechargeCardBatch.do?deleteIDs=",
			
			"/GetRatePlan.do?id=",
			"/ViewRatePlanDetails.do?RatePlanID=",
			"/DropRatePlan.do?deleteIDs=",
			
			"/DropRatePlanDetails.do?deleteIDs=",
			
			"/GetCallDest.do?id=",
			"/DropCallDest.do?deleteIDs=",
			
			"/GetDIDCallingCard.do?didCallignCardID=",
			"/DeleteDIDCallingCard.do?deleteIDs=",
			
			"/GetDIDOutgoing.do?didID=",
			"/DeleteOutgoingDID.do?deleteIDs=",
			
			"/GetDIDExtension.do?didID=",
			"/DeleteDIDExtension.do?deleteIDs=",
			
			"/OperatorStatusListView.do?cid=",
			"/callshop/boothLabeling.jsp?id=",
			"/GetCallshop.do?id=",
			"/monitoring/CallShopReportParams.jsp?showMenu=false&cid=",
			"/DropCallshop.do?deleteIDs=",
			
			"/GetRoute.do?routeID=",
			"/DeleteRoute.do?deleteIDs=",
			
			"/GetRechargeAgent.do?id=",
			"/DropRechargeAgent.do?deleteIDs=",
			
			"/ViewTopUpRatePlanDetails.do?TopUpRatePlanID=",
			"/GetTopUpRatePlan.do?id=",
			"/topuprateplan/topUpDestRatePlanAdd.jsp?TopUpRatePlanID=",
			"/topuprateplan/topUpDestRatePlanUpload.jsp?TopUpRatePlanID=",
			"/DropTopUpRatePlan.do?deleteIDs=",

			
			"/GetTopUpRoutePlan.do?id=",
			"/DeleteTopUpRoutePlan.do?deleteIDs=",
			
			"/GetUser.do?id=",
			"/DropUser.do?deleteIDs=",
			
			"/GetRole.do?id=",
			"/DropRole.do?deleteIDs=",
			
			"/GetPackage.do?id=",
			"/package/packageUserReport.jsp?packageID=",
			"/DropPackage.do?deleteIDs=",
			
			"/GetLanguage.do?id=",
			"/DropLanguage.do?deleteIDs=",
			
			"/GetPrefixTranslation.do?id=",
			"/DropPrefixTranslation.do?deleteIDs=",
			
			"/getreferral.do?id=",
			"/dropreferral.do?deletedIDs=",
			
			"/GetPromotionalOffer.do?poID=",
			"/DropPromotionalOffer.do?deleteIDs=",
			
			
			"/GetPartition.do?partitionID=",
			"/DeleteResellerPartition.do?deleteIDs=",
			"/GetPartition.do?partitionID=",
			"/DeleteSwitchPartition.do?deleteIDs=",
			
			"/GetTopUpDest.do?id=3002",
			"/DeleteTopUpAction.do?deleteIDs=3002",
			"/DropTopUpRateDestinationPlan.do?deleteIDs=63002",
			"/dashboard/Dashboard.jsp",
			"/GetPaymentDetails.do?id=648003&payID=39001&showMenu=false",
			"/DeleteIt.do?deleteIDs=5701",
			"/DropRestrictedIPs.do?deleteIDs=39001",
			"/dropIpName.do?deleteIDs=4004",
			"/payment/paymentView.jsp?reportType=2&clientAccountID=281002&showMenu=false",
			
			"/GetPackageGroup.do?pgID=2001",
			"/DropPackageGroup.do?deleteIDs=2001",
			//"/pin/pinDetailsDownload.jsp?batchID=77001",
			
			//"/RecoverpinClient.do?deleteIDs=780009",

			//"/DownloadDestinationTopUpRatePlanAction.do?TopUpRatePlanID=17002",

			//"/topupdestination/topUPDestAdd.jsp?countryID=-1",


			//"/DownloadBackUp.do?fileSessionID=162.222.186.235:iTelBilling600_Successful600_Successful600_09_Jan_2017_11_53_45.tar.gz:/home/databackup",
//			"/BalanceTransfer.do?pinNo=1234523&transferAmount=10&password=1", //balance transfer
//			"/RechargeAccountByCard.do?paymentOption=1&cardserial=2118&cardno=49198",//recharge by recharge card
//			"/RechargeAccountByPIN.do?paymentOption=3&pinNo=1234523&password=1",//recharge by pin
			
			};
*/
    
	public static String[] SQLArray = {
			"select clAccountID from vbClient where clCustomerID=",
			"select * from vbGtway where gtIsDeleted=0 and gtAccountID=",
			"select pbID from vbPinBatch where pbName=",
			"select clCustomerID from vbClient where clCustomerID=",
			"select rcbID from vbRechargeCardBatch where rcbName=",
			"select rpID from vbRatePlan where rpName=",
			"select drpID from vbDestinationRatePlan where drpIsDeleted=0 and drpDtDestID=1 and drpRPID=",
			"select dtDestID from vbDestination where dtDestCode=",
			"select dccID from vbDIDCallingCard where dccNumber=",
			"select ogID from vbDIDOutgoing where ogNO=",
			"select didID from vbDID where didNO=",
			"select csID from vbCallShop where csName=",
			"select rtID from vbRoute where rtID=",
			"select raID from vbRechargeAgent where raLogin=",
			"select toprpID from vbTopUpRatePlan where toprpName=",
			"select MTURouteId from vbMTURoutePlan where MTUGatewayID=36001 and MTURouteIsDeleted=0 limit 1",
			"select usUserID from vbUser where usUsername=",
			"select rlRoleID from vbRole where rlRoleName=",
			"select pkgID from vbPackage where pkgName=",
			"select languageID from vbLanguage where languageName=",
			"select rtTranslationID from vbRoutingTranslation limit 1",
			"select roID from vbReferralOffer where roName=",
			"select poID from vbPromotionOffer where poName=",
			
			};
	

	
	public String[] DeleteAPIArray = {
			"/api/callerIDAPI.jsp?type=get&",
			"/api/callerIDAPI.jsp?type=add&callerID=",
			"/api/callerIDAPI.jsp?type=remove&callerID=",
			"/api/rechargeByAPI.jsp?CustomerID=1234529&paymentType=paypal&Amount=5&Currency=USD&Transaction_Ref=",
			"/api/autoSignup.jsp?type=signUp&callerIDs=",

			};
    
	public String[] CallerIDAPIArray = {
			"/api/callerIDAPI.jsp?type=get&",
			"/api/callerIDAPI.jsp?type=add&callerID=",
			"/api/callerIDAPI.jsp?type=remove&callerID=",
			"/api/rechargeByAPI.jsp?paymentType=paypal&Amount=5&Currency=USD&Transaction_Ref=TAF56512314545233&CustomerID=",
			"/api/autoSignup.jsp?type=signUp&callerIDs=",

			};
	
	public String[] ClientAddAPIArray = {
			"/api/signupClient.jsp?type=verify&username=",
			"/api/signupClient.jsp?type=signUp&username=",
			"/api/didApi.jsp?service_type=1&request_type=1&user=",
			};
	
	public String[] ReferralAPIArray = {
			"/api/referralAPI.jsp?actionType=GetRunningReferral&",
			"/api/referralAPI.jsp?actionType=GetReferURL&mediaID=-1&",
			"/api/referralAPI.jsp?actionType=ConfirmInvite&referrer=",
			"/api/referralAPI.jsp?actionType=RecordReferredSignUp&referrer=",
			"/api/referralAPI.jsp?actionType=GetReferralReport&",

			};
	
	public String[] AddFundAPIArray = {
			"/api/addFundAPI.jsp?type=balanceTransfer&transferAmount=2&pinNoToRecharge=", //Balance Transfer API
			"/api/addFundAPI.jsp?type=recharge&paymentType=3&pinPassword=1&pinNo=", //Recharge By PIN API
			"/api/addFundAPI.jsp?type=recharge&paymentType=1&", //Recharge API with recharge card

			};

	public String[] PakcageAPIArray = {
			"/api/packageApi.jsp?type=getAvailablePackage&",
			"/api/packageApi.jsp?type=getSubscribedPackage&",
			"/api/packageApi.jsp?type=getSubscribedPackageDetails&",
			"/api/packageApi.jsp?type=updateAutoRenewStatus&autorenew=1&",
			"/api/packageApi.jsp?type=subscribePackageID&packageID=",


			};
	
	public String[] ALLCommonAPIArray = {
			"/api/otpVerify.jsp?otpSerial=1800001961&otpToken=187898",
			"/api/pinSearch.jsp?recordPerPage=20",
			"/api/checkRate.jsp?RatePlanID=",
			"/api/checkRate.jsp?country=Bangladesh&RatePlanID=",
			"/api/pinStatusChange.jsp?status=1&pin=",
			"/api/checkRate.jsp?username=",
			"/api/checkRate.jsp?dialedNumber=SMS8801912026219&username=",
			"/api/checkRate.jsp?dialedNumber=8801912026219&username=",
			"/api/checkRate.jsp?country=Bangladesh&username=",
			"/api/rechargeByAPI.jsp?requestType=getActualRechargeableAmount&paymentType=paypal&Amount=5&CustomerID=",
			"/getclientbalance.do?pin=",
			"/api/languageAPI.jsp?type=getLanguageList&username=",
			
			"/api/passwordApi.jsp?type=forgotPassword&username=",
			//"/api/passwordApi.jsp?type=changePassword&username=1234528",

			
			};

	public static String[] HistoryAPIArray = {
			"/api/mtuApi.jsp?type=countrylist", // to see available country list
			"/api/mtuApi.jsp?type=operatorlist&countryName=Bangladesh", //to see available operator
			"/api/mtuHistory.jsp?from_time=2015-06-01%2000:00:00&to_time=2017-09-25%2023:59:59", // MTU History API
			"/profilePictureHandler.do?requesttype=getProfileInfo&", //Get Profile Info
			"/profilePictureHandler.do?updateData=1&name=Mizanur%20Rahman&emailID=mizanur@revesoft.com&nationality=18&state=ctg&dateOfBirth=19910103&", //Set Profile Info
			"/profilePictureHandler.do?requesttype=getProfileInfo&", //Get Profile Info
			"/api/addFundAPI.jsp?type=rechargeHistory&historyCount=50&", //Recharge History API
			"/api/callHistoryApi.jsp?service_type=1&from_time=2015-06-01%2000:00:00&to_time=2017-01-25%2023:59:59&",  //Call History API
			"/api/mtHistoryApi.jsp?service_type=1&from_time=2015-01-01%2000:00:00&to_time=2017-04-25%2023:59:59&",  //MT History API
			"/api/autoRechargeAPI.jsp?type=statusCheck&", //Auto Recharge Enable Disable Status Check
			"/api/autoRechargeAPI.jsp?type=setAutoRecharge&status=0&", //Auto Recharge Status Set
			"/api/braintreeAPI.jsp?type=token&", //Braintree token generation API
			"/api/mtuRate.jsp?country=Bangladesh&", // to see available country list
			"/api/mtuHistory.jsp?from_time=2014-06-01%2012:33:34&to_time=2017-09-01%2012:33:34&", //MTU History API
			"/api/mtuApi.jsp?type=convertAmount&countryName=Bangladesh&operatorName=Robi&rechargeAmount=10&", //To check convert Amount
			"/api/mtuApi.jsp?type=recharge&countryName=Bangladesh&operatorName=Banglalink&serviceType=0&mobileNumber=8801912026219&rechargeAmount=10&", //MTU Recharge API
			};
	
	
	public static String[] JSPTraverseAdminArray=readArray("JSPTraverseAdminArray",readArraySize("JSPTraverseAdminArray"));
	/*public String[] JSPTraverseAdminArray = {
			"/clients/clientAdd.jsp",
			"/ViewClient.do",
			"/ViewAccountRecharge.do",
			"/ViewGateway.do",
			"/gateways/nonProxyIPList.jsp",
			"/gateways/gatewayAddSeries.jsp",
			"/ViewGatewaySeries.do",
			//"/pin/pinBatchAdd.jsp",
			"/pin/pinSingleAdd.jsp",
			"/ViewPinBatch.do",
			"/ViewPinBatchDetails.do?PinBatchID=-2",
			"/ViewPinRecharge.do?PinBatchID=-2",
			"/pin/uploadPin.jsp",
			"/rechargecard/rechargeCardBatchAdd.jsp",
			"/ViewRechargeCardBatch.do",
			"/paymentDetails/paymentDetailsAdd.jsp",
			"/ViewPaymentDetails.do",
			"/rateplan/ratePlanAdd.jsp",
			"/ViewRatePlan.do",
			"/rateplan/ratePlanToMultipleClient.jsp",
			"/rateplan/rateToMultipleRatePlan.jsp",
			"/destination/callDestAdd.jsp",
			"/destination/uploadDestination.jsp",
			"/destination/callDest.jsp",
			"/package/packageAdd.jsp",
			"/ViewPackages.do",
			"/package/packageHistoryparams.jsp",
			"/did/AddDIDCallingCard.jsp",
			"/SearchDIDCallingCard.do",
			"/did/AddOutgoingDID.jsp",
			"/SearchOutgoingDID.do",
			"/did/AddDIDExtension.jsp",
			"/SearchDIDExtension.do",
			"/callshop/callshopAdd.jsp",
			"/ViewCallshop.do",
			"/callroute/AddCallRoute.jsp",
			"/SearchRoute.do",
			"/callroute/StopFailOverCodes.jsp",
			"/callroute/CallSimulator.jsp",
			"/callroute/uploadTranslation.jsp",
			"/routetest/addroutetestagent.jsp",
			"/ViewRouteTest.do",
			"/routetest/testroute.jsp",
			"/routetest/routeTestReportParameter.jsp",
			"/rechargeagent/addRechargeAgent.jsp",
			"/ViewRechargeAgent.do",
			"/topupdestination/topUPDestAdd.jsp",
			"/ViewTopUPAction.do",
			"/topuprateplan/topUpRatePlanAdd.jsp",
			"/ViewTopUpRatePlan.do",
			"/topuprouteplan/addTopUpRoutePlan.jsp",
			"/ViewTopUpRoutePlan.do",
			"/recharge/addRecharge.jsp",
			"/mtoperator/mtOperatorAdd.jsp",
			"/ViewMTOperator.do",
			"/mtrateplan/mtRatePlanAdd.jsp",
			"/ViewMTRatePlan.do",
			"/mtgateway/addGateway.jsp",
			"/ViewMTGateway.do",
			"/transfer/transferRequest.jsp",
			"/transfer/moneyTransferApproval.jsp",
			"/monitoring/monitorparams.jsp",
			"/monitoring/MonitorGraphParams.jsp",
			"/monitoring/ActiveCalls.jsp",
			"/monitoring/ActiveRegistrations.jsp",
			"/monitoring/DiscCauseParams.jsp",
			"/finance/profitLoss.jsp",
			"/billgeneration/billgenparams2.jsp",
			"/payment/paymentView.jsp",
			"/monitoring/CallShopReportParams.jsp",
			"/recharge/topUpReportParameter.jsp",
			"/transfer/mtReportView.jsp?showMenu=false",
			"/general/clientSelectParams.jsp",
			"/general/gatewaylistparams.jsp",
			"/general/ResellerPinReportParams.jsp",
			"/log/generalActivityLogParams.jsp?showMenu=true",
			"/log/liveCallLogs.jsp",
			"/log/invoiceLogParams.jsp",
			"/rateHistory/rateHistoryParam.jsp",
			"/users/userAdd.jsp",
			"/ViewUser.do",
			"/roles/roleAdd.jsp",
			"/ViewRole.do",
			"/SearchDatabackup.do",
			"/databackup/backUpStatus.jsp",
			"/cdr/ReprocessCDRParam.jsp",
			"/language/languageAdd.jsp",
			"/ViewLanguage.do",
			"/searchGlobalConf.do",
			"/config/manageCurrency.jsp",
			"/serviceRestart/serviceRestart.jsp",
			"/searchGlobalConfOnlinePayment.do",
			"/GetAlarmConfiguration.do",
			"/GetMailServerInfo.do",
			"/GetAccountsConfig.do",
			"/GetWebCustomization.do",
			"/partition/partitionAdd.jsp?pTypeReseller=true",
			"/SearchResellerPartition.do",
			"/partition/partitionAdd.jsp",
			"/SearchSwitchPartition.do",
			"/ViewRestrictedIPs.do",
			"/ipmanagement/unAuthAccessParams.jsp",
			"/moneyTransferSecuritySettings/addSecuritySettings.jsp",
			"/searchSecuritySettings.do",
			"/agentLimit/mtAgentTransferLimitAdd.jsp",
			"/ViewTransferLimit.do",
			"/ipname/ipname.jsp",
			"/dialerAnalysis/dialerPerformance.jsp",
			"/imbroadcast/broadcastAdd.jsp",
			"/SearchIMBroadcast.do",
			"/referral/ReferralAdd.jsp",
			"/searchreferral.do",
			"/getappsettings.do",
			"/referral/ReferralReportView.jsp",
			"/referral/ReferralTrendOverview.jsp",
			"/promotionaloffer/promotionalOfferAdd.jsp",
			"/ViewPromotionalOffer.do",
			"/promotionaloffer/promoReportView.jsp",
			"/packagegroup/packageGroupAdd.jsp",
			"/ViewPackageGroup.do",
			"/package/packageShareHistoryParams.jsp",
			"/sms/SMSHistoryReport.jsp",
			"/finance/forfeitBalanceHistoryReport.jsp",
			"/otp/otpHistoryReport.jsp",
			"/signUpInfoSettings/addSignUpInfoSettings.jsp",
			"/searchSignUpInfoSettings.do",

			};
*/
	
	
	
	public static String[] JSPTraverseARResellerArray = {
			"/clients/clientAdd.jsp",
			"/ViewClient.do",
			"/ViewAccountRecharge.do",
			"/pin/pinBatchAdd.jsp",
			"/pin/pinSingleAdd.jsp",
			"/ViewPinBatch.do",
			"/ViewPinBatchDetails.do?PinBatchID=-2",
			"ViewPinRecharge.do?PinBatchID=-2",
			"/pin/uploadPin.jsp",
			"rechargecard/rechargeCardBatchAdd.jsp",
			"/ViewRechargeCardBatch.do",
			"/paymentDetails/paymentDetailsAdd.jsp",
			"/ViewPaymentDetails.do",
			"/rateplan/ratePlanAdd.jsp",
			"/ViewRatePlan.do",
			"/rateplan/ratePlanToMultipleClient.jsp",
			"/rateplan/rateToMultipleRatePlan.jsp",
			"/package/packageAdd.jsp",
			"/ViewPackages.do",
			"/package/packageHistoryparams.jsp",
			"/did/AddDIDCallingCard.jsp",
			"/SearchDIDCallingCard.do",
			"/did/AddOutgoingDID.jsp",
			"/SearchOutgoingDID.do",
			"/did/AddDIDExtension.jsp",
			"/SearchDIDExtension.do",
			"/callshop/callshopAdd.jsp",
			"/ViewCallshop.do",
			"/topuprateplan/topUpRatePlanAdd.jsp",
			"/ViewTopUpRatePlan.do",
			"/recharge/addRecharge.jsp",
			"/mtrateplan/mtRatePlanAdd.jsp",
			"/ViewMTRatePlan.do",
			"/transfer/transferRequest.jsp",
			"/transfer/moneyTransferApproval.jsp",
			"/monitoring/monitorparams.jsp",
			"/monitoring/MonitorGraphParams.jsp",
			"/monitoring/ActiveCalls.jsp",
			"/monitoring/ActiveRegistrations.jsp",
			"/monitoring/DiscCauseParams.jsp",
			"/finance/profitLoss.jsp",
			"/billgeneration/billgenparams2.jsp",
			"/payment/paymentView.jsp",
			"/monitoring/CallShopReportParams.jsp",
			"/recharge/topUpReportParameter.jsp",
			"/transfer/mtReportView.jsp?showMenu=false",
			"/log/liveCallLogs.jsp",
			"/log/invoiceLogParams.jsp",
			"/rateHistory/rateHistoryParam.jsp",
			"/ViewClientProfile.do?isProfile=true",
			"/ViewAccountRecharge.do",
			"/searchGlobalConfOnlinePayment.do",
			"/rechargecard/RechargeAccount.jsp?showMenu=true",
			"/dashboard/dashboardLite.jsp",
			};

	
	
	public static String[] JSPTraverseDRResellerArray = {
			"/clients/clientAdd.jsp",
			"/ViewClient.do",
			"/ViewAccountRecharge.do",
			"/pin/pinBatchAdd.jsp",
			"/pin/pinSingleAdd.jsp",
			"/ViewPinBatch.do",
			"/ViewPinBatchDetails.do?PinBatchID=-2",
			"ViewPinRecharge.do?PinBatchID=-2",
			"/pin/uploadPin.jsp",
			"/paymentDetails/paymentDetailsAdd.jsp",
			"/ViewPaymentDetails.do",
			"/rateplan/ratePlanAdd.jsp",
			"/ViewRatePlan.do",
			"/rateplan/ratePlanToMultipleClient.jsp",
			"/rateplan/rateToMultipleRatePlan.jsp",
			"/did/AddDIDCallingCard.jsp",
			"/SearchDIDCallingCard.do",
			"/did/AddOutgoingDID.jsp",
			"/SearchOutgoingDID.do",
			"/did/AddDIDExtension.jsp",
			"/SearchDIDExtension.do",
			"/callshop/callshopAdd.jsp",
			"/ViewCallshop.do",
			"/recharge/topUpCalculator.jsp",
			"/topuprateplan/topUpRatePlanAdd.jsp",
			"/ViewTopUpRatePlan.do",
			"/recharge/addRecharge.jsp",
			"/mtrateplan/mtRatePlanAdd.jsp",
			"/ViewMTRatePlan.do",
			"/transfer/transferRequest.jsp",
			"/monitoring/monitorparams.jsp",
			"/monitoring/MonitorGraphParams.jsp",
			"/monitoring/ActiveCalls.jsp",
			"/finance/profitLoss.jsp",
			"/billgeneration/billgenparams2.jsp",
			"/payment/paymentView.jsp",
			"/monitoring/CallShopReportParams.jsp",
			"/recharge/topUpReportParameter.jsp",
			"/transfer/mtReportView.jsp?showMenu=false",
			"/log/liveCallLogs.jsp",
			"/ViewClientProfile.do?isProfile=true",
			"/ViewAccountRecharge.do",
			"/searchGlobalConfOnlinePayment.do",
			"/rechargecard/RechargeAccount.jsp?showMenu=true",
			"/dashboard/dashboardLite.jsp",
			};

	
	
	public static String[] JSPTraverseAgentArray = {
			"/clients/clientAdd.jsp",
			"/ViewClient.do",
			"/ViewAccountRecharge.do",
			"/pin/pinSingleAdd.jsp",
			"ViewPinRecharge.do?PinBatchID=-2",
			"/pin/uploadPin.jsp",
			"rechargecard/rechargeCardBatchAdd.jsp",
			"/ViewRechargeCardBatch.do",
			"/paymentDetails/paymentDetailsAdd.jsp",
			"/ViewPaymentDetails.do",
			"/finance/profitLoss.jsp",
			"/payment/paymentView.jsp",
			"/ViewClientProfile.do?isProfile=true",
			"/ViewAccountRecharge.do",
			"/searchGlobalConfOnlinePayment.do",
			"/rechargecard/RechargeAccount.jsp?showMenu=true",
			};

	
	
	public static String[] JSPTraversePINArray = {
			"/ViewClientProfile.do?isSpeedDial=true",
			"/ViewClientProfile.do?isForwarding=true",
			"/ViewClientProfile.do?isCallerID=true",
			"/package/pinPackages.jsp",
			"/home/index.jsp?v=recentcalls",
			"/ViewRecharge.do?showMenu=false",
			"/transfer/transferRequest.jsp",
			"/recharge/addRecharge.jsp",
			"/GetAssignedDiDs.do",
			"/searchGlobalConfOnlinePayment.do",
			"/rechargecard/balanceTransfer.jsp",
			"/payment/paymentView.jsp?showMenu=true",
			"/ViewMTHistory.do?showMenu=false",
			"/ViewRecharge.do?showMenu=false",
			"/referral/GetFreeCredit.jsp",
			"/ViewClientProfile.do?isProfile=true",
			"/pin/costCalculator.jsp",
			"/rechargecard/RechargeAccount.jsp",
			"/pin/webDialer.jsp",
			};

	
	public static String[] clientNameArray = {
			"Originating-",
			"Terminating-",
			"Both-",
			"Reseller-",
			"Agent-",
			"123123",
			"PinBatch-",

			};
	
}
