package DevelopedAPI;

import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import Client.ClientModule;
import MyAction.ActionModule;
import MyPermission.PermissionModule;
import MyVariable.VariableModule;

public class DevelopedAPIModule {

	static Logger logDevelopedAPIModule = Logger.getLogger(DevelopedAPIModule.class.getName());
	
	VariableModule variabledo = new VariableModule();
	ActionModule actiondo = new ActionModule();
	ClientModule clientdo = new ClientModule();

	String[] TokenArray;
    String nonce = "";
    String hashValue = "";
    String strforMD5 = "";
	String APIUrl = "";
    String commonAPIUrl = "";
    String splitToken = "";
    String APIoutput = "";
    String getbody = "";
	int totalcases=0;
	int totalsuccessfulcases=0;
    
    public void APICases() {

    }
    public String getNonce(WebDriver driver) {
        APIUrl = actiondo.BaseURL().concat("/api/callerIDAPI.jsp");
        //System.out.println("Nonce API: " +APIUrl);
        driver.get(APIUrl);
        splitToken=driver.findElement(By.xpath("//body")).getText();
        TokenArray=TokenizerAction(splitToken);
        return TokenArray[3];
    }

    public String getNonceAPI(WebDriver driver, String baseurl) {
    	
    	String[]  NonceArray=null;
    	String splitToken="";

        driver.get(baseurl.concat("/api/callerIDAPI.jsp"));

/*        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        splitToken=driver.findElement(By.tagName("body")).getText();
        //System.out.println("Body Nonce: " +splitToken);
        //splitToken=driver.findElement(By.xpath("//body")).getText();
        NonceArray = splitToken.split("nonce=");
        System.out.println("Nonce: " +NonceArray[1]);
        //NonceArray=TokenizerAction(splitToken);
        return NonceArray[1];
    }
    
	public String[] TokenizerAction(String splitString) //http://crunchify.com/java-stringtokenizer-and-string-split-example/
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
	
	public String APImaker(WebDriver driver, String apidbid, String[] pinArray)
	{
		String stringsum="";
		String executiondata="";
		String nonce="";
		String Query="";


	    nonce=getNonceAPI(driver, pinArray[6]);
	    //System.out.println("PIN: "+pinArray[14]);
	    strforMD5 = nonce.concat(pinArray[14]+pinArray[15]);
	    //System.out.println(strforMD5);
	    hashValue=actiondo.getMD5(strforMD5);
	    //System.out.println("hashValue: "+hashValue);
	    String[] apiID=apidbid.split("[$]");
	    String[] APIparamArray = apiID[4].split("&");
	    
	    for(int k=0;k<APIparamArray.length;k++)
	    {
	    	if(APIparamArray[k].indexOf("user")!=-1)
	    	{
	    		APIparamArray[k]=APIparamArray[k]+pinArray[14];
	    	}
	    	else if(APIparamArray[k].indexOf("password")!=-1)
	    	{
	    		APIparamArray[k]=APIparamArray[k]+hashValue;
	    	}
	    	else if(APIparamArray[k].indexOf("nonce")!=-1)
	    	{
	    		APIparamArray[k]=APIparamArray[k]+nonce;
	    	}
	    	stringsum=stringsum+APIparamArray[k]+"&";
	    	//System.out.println("APIparamArray: "+APIparamArray);
	    }
	    stringsum=stringsum.substring(0, stringsum.length()-1);
	    //System.out.println("stringsum: "+stringsum);
	    return stringsum;
	}
	
	public String[] TokenizerAction1(String splitString) //http://crunchify.com/java-stringtokenizer-and-string-split-example/
	{
		
		//String delims = "=";
		String delims = "[?=&]";
		String[] tokens=splitString.split(delims);
	    for (int i=0; i< tokens.length; i++){
	      //System.out.println("StringTokenizer Output: " +tokens[i]);
	    }
	    //System.out.println("StringTokenizer: " +tokens[3]);
	    return tokens;
	}
	
	public String ArrayBuilder(String[] Arraybuild,int k) {
		String Arraymerge="";
		//System.out.println("Array Length:"+Arraybuild.length+"K: "+k);
		for(int j=0;j<Arraybuild.length;j++)
		{
			if(j!=k && j!=k+1) {
				//System.out.println("J:"+j);
				if(j==0)
				{
					Arraymerge=Arraybuild[0].concat("?");
					
				}
				else if(j%2==0) {
					Arraymerge=Arraymerge.concat(Arraybuild[j]+"&");
				}
				else if(j%2!=0)
				{
					Arraymerge=Arraymerge.concat(Arraybuild[j]+"=");
				}
				//System.out.println("Array Builder1: "+Arraymerge+"\n");
				
			}

		}
		//System.out.println("Array Builder2: "+Arraymerge);
		return Arraymerge;
		
	}
	
	public void APICases(WebDriver driver, String ApiLink) {
		String[] Arrayparam;
		String APIUrl2="";
		//driver.get(ApiLink);
		Arrayparam=TokenizerAction1(ApiLink);
		//System.out.println("Array Length:"+Arrayparam.length);
		for(int i=1; i< Arrayparam.length; i=i+2) {
			if(Arrayparam[i].equals("type")) {
				
/*				APIUrl2=ArrayBuilder(TokenizerAction1(ApiLink),i).concat(Arrayparam[i]+"="+Arrayparam[i+1]);
				driver.get(APIUrl2);
				getbody=driver.findElement(By.tagName("body")).getText();*/
				
			}
			else if(Arrayparam[i].equals("password")) {
				APIUrl2=ArrayBuilder(TokenizerAction1(ApiLink),i).concat(Arrayparam[i]+"="+Arrayparam[i+1]);
				driver.get(APIUrl2);
				getbody=driver.findElement(By.tagName("body")).getText();
				//System.out.println(Arrayparam[i]+":"+APIUrl2+"Output:"+getbody);
			}
			else if(Arrayparam[i].equals("user")) {
				APIUrl2=ArrayBuilder(TokenizerAction1(ApiLink),i).concat(Arrayparam[i]+"="+Arrayparam[i+1]);
				driver.get(APIUrl2);
				getbody=driver.findElement(By.tagName("body")).getText();
				//System.out.println(Arrayparam[i]+":"+APIUrl2+"Output:"+getbody);
			}
			else if(Arrayparam[i].equals("nonce")) {
				APIUrl2=ArrayBuilder(TokenizerAction1(ApiLink),i).concat(Arrayparam[i]+"="+Arrayparam[i+1]);
				driver.get(APIUrl2);
				getbody=driver.findElement(By.tagName("body")).getText();
				//System.out.println(Arrayparam[i]+":"+APIUrl2+"Output:"+getbody);
			}
			
		}
/*        getbody=driver.findElement(By.tagName("body")).getText();
        //System.out.println("Function API Cases:"+getbody);
        variabledo.actualTitle = driver.getTitle();
        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
	        	//actiondo.writing("Test Failed with Exception:" + APIUrl);
	        	//actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
	        	
        	}

        }

        else
        {
        	if(getbody.isEmpty()) {
	        	
	        	actiondo.writing("Test Failed with no output found:" + APIUrl);
	        	//actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
	        	System.out.println("No Output Found");
	        }
        	else {
        		if(getbody.indexOf("status=108") != -1) {
        			actiondo.writing("Invalid Password:" + getbody);
        			System.out.println("Invalid Password:"+getbody);
        		}
        		else if(getbody.indexOf("status=106") != -1) {
        			actiondo.writing("Invalid Nonce:" + getbody);
        			System.out.println("Invalid Nonce:"+getbody);
        		}
        		else if(getbody.indexOf("status=110") != -1) {
        			actiondo.writing("New Nonce:" + getbody);
        			System.out.println("New Nonce:"+getbody);
        		}
        		else {
        			actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
        			System.out.println("Test is Successful");
        		}	
        	}
        }*/
	}
	
	public void ALLCommonAPIAction(WebDriver driver) 
	{
	    
		actiondo.writing("**********ALLCommonAPIAction***********");
		actiondo.ExcelFileWriteAction("**********ALLCommonAPIAction***********","");
		actiondo.ConsolPrint("**********ALLCommonAPIAction***********","");
		
		int i=0;
/*	    APIUrl = actiondo.BaseURL().concat("/profilePictureHandler.do?requesttype=getNonce&username="+variabledo.pin);
	    driver.get(APIUrl);
	    splitToken=driver.findElement(By.xpath("//body")).getText();
	    TokenArray=TokenizerAction(splitToken);*/
	    nonce=getNonce(driver);
	    //System.out.println("Server Nonce:" + nonce);
    	String card=ActionModule.MysqlConnectionAction("select clBillingPassword from vbClient where clCustomerID='"+variabledo.pin1+"'", VariableModule.Remoteconn,",");
    	String[] cardInfo=TokenizerAction(card);
    	//System.out.println("Current Pass:" + cardInfo[0]);
	    strforMD5 = nonce.concat("1234526"+cardInfo[0]);
	    hashValue=actiondo.getMD5(strforMD5);
	    commonAPIUrl = "&currentPassword="+hashValue+"&nonce="+nonce;
	    //String xorpass=actiondo.xorEncrypt("2", nonce);
	    for(;i<variabledo.ALLCommonAPIArray.length;i++){
/*	    	if(i==(variabledo.ALLCommonAPIArray.length-1)){ //Password Change API code
	    		
	    		
		        APIUrl = actiondo.BaseURL().concat(variabledo.ALLCommonAPIArray[i]+commonAPIUrl+"&newPassword="+actiondo.xorEncrypt("3", nonce));
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        actiondo.writing(APIUrl);
		        actiondo.writing(driver.findElement(By.tagName("body")).getText());
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	
		        	}

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
	    	}*/
		    if(i>3)
		    {
		    	variabledo.totalcases++;
		    	//String userconcat=actiondo.BaseURL().concat(variabledo.ALLCommonAPIArray[i]+variabledo.pin1);
		        APIUrl = actiondo.BaseURL().concat(variabledo.ALLCommonAPIArray[i]+variabledo.pin1);
		        ////APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        
		        actiondo.writing(APIUrl);
		        //APIoutput=driver.getPageSource();
		        if(i!=11){
		        actiondo.writing(driver.findElement(By.tagName("body")).getText());
		        getbody=driver.findElement(By.tagName("body")).getText();
		        }
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.ConsolPrint(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;
			        	
		        	}

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	actiondo.ConsolPrint(APIUrl, "Test Failed with no output found");
			        	actiondo.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	actiondo.ConsolPrint(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
		    }
		    else if(i>1 && i<4) {
		    	
		    	variabledo.totalcases++;
		    	//String userconcat=actiondo.BaseURL().concat(variabledo.ALLCommonAPIArray[i]);
		    	APIUrl = actiondo.BaseURL().concat(variabledo.ALLCommonAPIArray[i]+variabledo.RatePlanID);
		        ////APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        
		        actiondo.writing(APIUrl);
		        //APIoutput=driver.getPageSource();
		        if(i!=11){
		        actiondo.writing(driver.findElement(By.tagName("body")).getText());
		        getbody=driver.findElement(By.tagName("body")).getText();
		        }
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
		        	
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;
		        	

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
		    }
		    else
		    {
		    	variabledo.totalcases++;
		    	//String userconcat=actiondo.BaseURL().concat(variabledo.ALLCommonAPIArray[i]);
		        APIUrl = actiondo.BaseURL().concat(variabledo.ALLCommonAPIArray[i]);
		        ////APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        
		        actiondo.writing(APIUrl);
		        //APIoutput=driver.getPageSource();
		        if(i!=11){
		        actiondo.writing(driver.findElement(By.tagName("body")).getText());
		        getbody=driver.findElement(By.tagName("body")).getText();
		        }
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
		        	
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;
		        	

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
		    }
	    }
	    totalcases=i;
	    actiondo.TestSummaryCreator("All Common API", totalcases, totalsuccessfulcases);
	    totalcases=0;
	    totalsuccessfulcases=0;
	}
	

	public void PakcageAPIAction(WebDriver driver) 
	{
		actiondo.writing("**********PakcageAPIAction***********");
		actiondo.ExcelFileWriteAction("**********PakcageAPIAction***********","");
		actiondo.ConsolPrint("**********PakcageAPIAction***********","");
		
		int i=0;
/*	    APIUrl = actiondo.BaseURL().concat("/profilePictureHandler.do?requesttype=getNonce&username="+variabledo.pin);
	    driver.get(APIUrl);
	    splitToken=driver.findElement(By.xpath("//body")).getText();
	    TokenArray=TokenizerAction(splitToken);*/
	    nonce=getNonce(driver);
	    //System.out.println("Server Nonce:" + nonce);
	    strforMD5 = nonce.concat(variabledo.pin+variabledo.pinPass);
	    hashValue=actiondo.getMD5(strforMD5);
	    commonAPIUrl = "user="+variabledo.pin+"&password="+hashValue+"&nonce="+nonce;
	    String packID= "";
	    for(;i<variabledo.PakcageAPIArray.length;i++){
	    	if(i==4){
	    		
	    		variabledo.totalcases++;
		        APIUrl = actiondo.BaseURL().concat(variabledo.PakcageAPIArray[i]+commonAPIUrl);
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        

		    	    splitToken=driver.findElement(By.xpath("//body")).getText();
		    		String delims = "\n";
		    		//String delims = "[=+]";
		    		String[] TokenArray=splitToken.split(delims);
		    		String delims1 = ",";
		    		String[] TokenArray1=TokenArray[2].split(delims1);
		    		packID=TokenArray1[0]+"&";

		    	
		        APIUrl = actiondo.BaseURL().concat(variabledo.PakcageAPIArray[i]+packID+commonAPIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){

			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;
		        	

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }

	    	}
		    else
		    {
		    	variabledo.totalcases++;
		        APIUrl = actiondo.BaseURL().concat(variabledo.PakcageAPIArray[i]+commonAPIUrl);
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        actiondo.writing(APIUrl);
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){

			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;
		        	

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
		    }
	    }
	    totalcases=i;
	    actiondo.TestSummaryCreator("Package API", totalcases, totalsuccessfulcases);
	    totalcases=0;
	    totalsuccessfulcases=0;
	}

	public void HistoryAPIAction(WebDriver driver) 
	{
		actiondo.writing("**********HistoryAPIAction***********");
		actiondo.ExcelFileWriteAction("**********HistoryAPIAction***********","");
		actiondo.ConsolPrint("**********HistoryAPIAction***********","");
		
		int i=0;
/*	    APIUrl = actiondo.BaseURL().concat("/profilePictureHandler.do?requesttype=getNonce&username="+variabledo.pin);
	    driver.get(APIUrl);
	    splitToken=driver.findElement(By.xpath("//body")).getText();
	    TokenArray=TokenizerAction(splitToken);*/
	    nonce=getNonce(driver);
	    //System.out.println("Server Nonce:" + nonce);
	    strforMD5 = nonce.concat(variabledo.pin+variabledo.pinPass);
	    hashValue=actiondo.getMD5(strforMD5);
	    commonAPIUrl = "user="+variabledo.pin+"&password="+hashValue+"&nonce="+nonce;
	    for(;i<variabledo.HistoryAPIArray.length;i++){
	    	if(i<3){

	    		variabledo.totalcases++;
		        APIUrl = actiondo.BaseURL().concat(variabledo.HistoryAPIArray[i]);
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        actiondo.writing(APIUrl);
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
			        	
		        		actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;


		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
	    	}
	    	else if(i>=3 && i<=5){
	    		
	    		variabledo.totalcases++;
	    	    commonAPIUrl = "username="+variabledo.pin+"&password="+hashValue+"&nonce="+nonce;
	    	    
		        APIUrl = actiondo.BaseURL().concat(variabledo.HistoryAPIArray[i]+commonAPIUrl);
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        actiondo.writing(APIUrl);
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){

			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;


		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
	    	}
		    else
		    {
		    	if(i==(variabledo.HistoryAPIArray.length-1)){
		    		
		    		variabledo.totalcases++;
			        APIUrl = actiondo.BaseURL().concat(variabledo.HistoryAPIArray[i]+commonAPIUrl);
			        //APICases(driver,APIUrl);
			        driver.get(APIUrl);
			        try {
			            Thread.sleep(2000);                 //1000 milliseconds is one second.
			        } catch(InterruptedException ex) {
			            Thread.currentThread().interrupt();
			        }
			        
		        	splitToken=driver.findElement(By.xpath("//body")).getText();
		        	splitToken=splitToken.replaceAll("}","");
		    		TokenArray=splitToken.split(":");
		        	for (int j=2; j< TokenArray.length; j++){
		        		System.out.println("StringTokenizer Output: " +TokenArray[j]);
		        	}	
			        APIUrl = actiondo.BaseURL().concat("/api/mtuApi.jsp?type=status&verificationCode="+TokenArray[1]);
			        driver.get(APIUrl);
			        try {
			            Thread.sleep(2000);                 //1000 milliseconds is one second.
			        } catch(InterruptedException ex) {
			            Thread.currentThread().interrupt();
			        }
			        
			        getbody=driver.findElement(By.tagName("body")).getText();
			        variabledo.actualTitle = driver.getTitle();
			        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){

				        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
				        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
				        	actiondo.PlayAlarmAction();
				        	variabledo.totalfailedcases++;


			        }

			        else
			        {
			        	if(getbody.isEmpty()) {
				        	
				        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
				        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
				        	System.out.println("No Output Found");
				        	actiondo.PlayAlarmAction();
				        	
				        }
			        	else {
			        		totalsuccessfulcases++;
				        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
				        	//System.out.println("Successful");
			        	}
			        }
		    	}
		    	else{
		    		
		    		variabledo.totalcases++;
			        APIUrl = actiondo.BaseURL().concat(variabledo.HistoryAPIArray[i]+commonAPIUrl);
			        //APICases(driver,APIUrl);
			        driver.get(APIUrl);
			        try {
			            Thread.sleep(2000);                 //1000 milliseconds is one second.
			        } catch(InterruptedException ex) {
			            Thread.currentThread().interrupt();
			        }
			        actiondo.writing(APIUrl);
			        getbody=driver.findElement(By.tagName("body")).getText();
			        variabledo.actualTitle = driver.getTitle();
			        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){

				        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
				        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
				        	actiondo.PlayAlarmAction();
				        	variabledo.totalfailedcases++;


			        }

			        else
			        {
			        	if(getbody.isEmpty()) {
				        	
				        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
				        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
				        	System.out.println("No Output Found");
				        	actiondo.PlayAlarmAction();
				        	
				        }
			        	else {
			        		totalsuccessfulcases++;
				        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
				        	//System.out.println("Successful");
			        	}
			        }
		    	}
		    }
	    }
	    totalcases=i;
	    actiondo.TestSummaryCreator("History API", totalcases, totalsuccessfulcases);
	    totalcases=0;
	    totalsuccessfulcases=0;
	}
	
	public void AddFundAPIAction(WebDriver driver) 
	{
		actiondo.writing("**********AddFundAPIAction***********");
		actiondo.ExcelFileWriteAction("**********AddFundAPIAction***********","");
		actiondo.ConsolPrint("**********AddFundAPIAction***********","");
		
		int i=0;
/*	    APIUrl = actiondo.BaseURL().concat("/profilePictureHandler.do?requesttype=getNonce&username="+variabledo.pin);
	    driver.get(APIUrl);
	    splitToken=driver.findElement(By.xpath("//body")).getText();
	    TokenArray=TokenizerAction(splitToken);*/
	    nonce=getNonce(driver);
	    //System.out.println("Server Nonce:" + nonce);
	    strforMD5 = nonce.concat(variabledo.pin+variabledo.pinPass);
	    hashValue=actiondo.getMD5(strforMD5);
	    commonAPIUrl = "&user="+variabledo.pin+"&password="+hashValue+"&nonce="+nonce;
	    for(;i<variabledo.AddFundAPIArray.length;i++){
	    	if(i<2){
/*	    	    strforMD5 = nonce.concat("1234531"+variabledo.pinPass);
	    	    hashValue=actiondo.getMD5(strforMD5);
	    	    String commonAPIUrl1 = "user=1234531&password="+hashValue+"&nonce="+nonce;
	    	    */
	    		variabledo.totalcases++;
		        APIUrl = actiondo.BaseURL().concat(variabledo.AddFundAPIArray[i]+variabledo.pin1+commonAPIUrl);
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        ActionModule.writing(APIUrl);
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
			        	ActionModule.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	ActionModule.PlayAlarmAction();
			        	VariableModule.totalfailedcases++;

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	ActionModule.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	ActionModule.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
	    	}
		    else
		    {
		    	
		    	VariableModule.totalcases++;
		    	String card=ActionModule.MysqlConnectionAction("select rcCardNo,rcSerialNo from vbRechargeCard where rcBatchID="+variabledo.NOrcBatchID+" and rcStatus=1 and rcAccountID=-1 and rcIsDeleted=0 limit 1", VariableModule.Remoteconn,",");
		    	String[] cardInfo=TokenizerAction(card);
/*		    	for (int p=0; p< cardInfo.length; p++){
			      System.out.println("StringTokenizer Output: " +cardInfo[p]);
			    }*/
		    	
		        APIUrl = actiondo.BaseURL().concat(variabledo.AddFundAPIArray[i]+"cardSerial="+cardInfo[1]+"&cardNo="+cardInfo[0]+"&"+commonAPIUrl);
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
		    }
	    }
	    totalcases=i;
	    actiondo.TestSummaryCreator("Add Fund API", totalcases, totalsuccessfulcases);
	    totalcases=0;
	    totalsuccessfulcases=0;
	}
	
	
	public void CallerIDAPIAction(String apidbid,String[] ExeArray1, String[] ConfigArray) 
	{
		//System.out.println("R1: ");
		String stringsum="";
		String APIUrl="";
		WebDriver driver=VariableModule.HtmlUnitDriver();//ActionModule.ChromedriverAction()//VariableModule.HtmlUnitDriver()
		logDevelopedAPIModule.debug("**********CallerIDAPIAction***********");
		int i=0;
		String[] apiIDsplit=apidbid.split("[$]");
		stringsum=APImaker(driver, apidbid,ConfigArray);
		String callerIDadd=actiondo.callerIDset();

	    	
	    	
	    	if (apiIDsplit[0].equals("1")) { //Get Caller ID
	    		
	    		
		        APIUrl = ConfigArray[6].concat(stringsum);
		        //APICases(VariableModule.driver,APIUrl);
		        //System.out.println("Get CallerID API: "+APIUrl);
		        logDevelopedAPIModule.debug("Get CallerID API: "+APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        getbody=driver.findElement(By.tagName("body")).getText();
		        //System.out.println("Outpur for Get CallerID API:"+getbody);
		        logDevelopedAPIModule.debug("Outpur for Get CallerID API:"+getbody);
		        if(getbody.indexOf("status=106")!=-1 || getbody.indexOf("status=110")!=-1)
		        {
		        	CallerIDAPIAction(apidbid,ExeArray1, ConfigArray);
		        }
		        
		        if (driver.getTitle().contentEquals(ConfigArray[5])){

			        	if(getbody.indexOf("status=")!=-1)
			        	{
			        		//ActionModulon(ExeArray1,getbody,apidbid,"Failed");
			        	}


		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	

			        	ActionModule.ExeResultInsertion(ExeArray1[0],APIUrl,"2");
			        	System.out.println("No Output Found");
			        	logDevelopedAPIModule.debug("No Output Found");
			        	
			        }
		        	else {
		        		
			        	
			        	ActionModule.ExeResultInsertion(ExeArray1[0],APIUrl,"1");
			        	//System.out.println("Successful");
		        	}
		        }
		        
	        }
	    	else if (apiIDsplit[0].equals("2")) { //Add Caller ID
	    		String stringsum1="";
	    		
		        APIUrl = ConfigArray[6].concat(stringsum);
		        
			    String[] APIparamArray = APIUrl.split("&");
			    
			    for(int k=0;k<APIparamArray.length;k++)
			    {
			    	if(APIparamArray[k].indexOf("callerID=")!=-1)
			    	{
			    		APIparamArray[k]=APIparamArray[k]+callerIDadd;
			    	}
			    	stringsum1=stringsum1+APIparamArray[k]+"&";
			    	//System.out.println("APIparamArray: "+APIparamArray);
			    }
			    APIUrl=stringsum1.substring(0, stringsum1.length()-1);
			    //System.out.println("Add CallerID API: "+APIUrl);
			    logDevelopedAPIModule.debug("Add CallerID API: "+APIUrl);
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        getbody=driver.findElement(By.tagName("body")).getText();
		        //System.out.println("Output for Add CallerID:"+getbody);
		        if(getbody.indexOf("status=106")!=-1 || getbody.indexOf("status=110")!=-1)
		        {
		        	CallerIDAPIAction(apidbid,ExeArray1, ConfigArray);
		        }
		        
		        if (driver.getTitle().contentEquals(ConfigArray[5])){
			        	
			        	if(getbody.indexOf("status=")!=-1)
			        	{
			        		//ActionModulon(ExeArray1,getbody,apidbid,"Failed");
			        	}


		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	
			        	ActionModule.ExeResultInsertion(ExeArray1[0],APIUrl,"2");
			        	//System.out.println("No Output Found");
			        	logDevelopedAPIModule.debug("No Output Found");
			        	
			        }
		        	else {
		        		
			        	
			        	ActionModule.ExeResultInsertion(ExeArray1[0],APIUrl,"1");
		        	}
		        }
	        }
	    	else if(apiIDsplit[0].equals("3")){ // Remove caller id
	    		String stringsum1="";
	    		

		        APIUrl = ConfigArray[6]+stringsum;
		        
			    String[] APIparamArray = APIUrl.split("&");
			    
			    for(int k=0;k<APIparamArray.length;k++)
			    {
			    	if(APIparamArray[k].indexOf("callerID=")!=-1)
			    	{
			    		APIparamArray[k]=APIparamArray[k]+callerIDadd;
			    	}
			    	stringsum1=stringsum1+APIparamArray[k]+"&";
			    	//System.out.println("APIparamArray: "+APIparamArray);
			    }
			    APIUrl=stringsum1.substring(0, stringsum1.length()-1);
			    //System.out.println("Add CallerID API: "+APIUrl);
			    logDevelopedAPIModule.debug("Add CallerID API: "+APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        getbody=driver.findElement(By.tagName("body")).getText();
		        //System.out.println("Output for Add CallerID:"+getbody);
		        if(getbody.indexOf("status=106")!=-1 || getbody.indexOf("status=110")!=-1)
		        {
		        	CallerIDAPIAction(apidbid,ExeArray1, ConfigArray);
		        }
		        stringsum1="";
		        APIUrl = ConfigArray[6].concat(stringsum);
			    String[] APIparamArray1 = APIUrl.split("&");
			    
			    for(int k=0;k<APIparamArray1.length;k++)
			    {
			    	if(APIparamArray1[k].indexOf("callerID=")!=-1)
			    	{
			    		APIparamArray1[k]=APIparamArray1[k]+callerIDadd;
			    	}
			    	stringsum1=stringsum1+APIparamArray1[k]+"&";
			    	//System.out.println("APIparamArray: "+APIparamArray);
			    }
			    APIUrl=stringsum1.substring(0, stringsum1.length()-1);
			    //System.out.println("Remove CallerID : "+APIUrl);
			    logDevelopedAPIModule.debug("Remove CallerID : "+APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        getbody=driver.findElement(By.tagName("body")).getText();
		        //System.out.println("Output for Remove CallerID API:"+getbody);
		        logDevelopedAPIModule.debug("Output for Remove CallerID API:"+getbody);
		        if(getbody.indexOf("status=106")!=-1 || getbody.indexOf("status=110")!=-1)
		        {
		        	CallerIDAPIAction(apidbid,ExeArray1, ConfigArray);
		        }
		        
		        	if (driver.getTitle().contentEquals(ConfigArray[5])) {
			        	
			        	if(getbody.indexOf("status=")!=-1)
			        	{
			        		//ActionModulon(ExeArray1,getbody,apidbid,"Failed");
			        	}

		        	}
			        else
			        {
			        	if(getbody.isEmpty()) {
				        	
				        	
				        	ActionModule.ExeResultInsertion(ExeArray1[0],APIUrl,"2");
				        	System.out.println("No Output Found");
				        	logDevelopedAPIModule.debug("No Output Found");
				        	
				        }
			        	else {
			        		
				        	
				        	ActionModule.ExeResultInsertion(ExeArray1[0],APIUrl,"1");
			        	}
			        }

	    	}
	    	

	}
	public void ReferralAPIAction(String apidbid,String[] ExeArray1, String[] ConfigArray) 
	{

		WebDriver driver=VariableModule.HtmlUnitDriver();
		int i=0;
	    /*================Developed API===================*/
/*	    APIUrl = actiondo.BaseURL().concat("/profilePictureHandler.do?requesttype=getNonce&username="+variabledo.pin);
	    driver.get(APIUrl);
	    splitToken=driver.findElement(By.xpath("//body")).getText();
	    //nonce=driver.getPageSource();
	    //nonce = nonce.substring(43,51);
	    TokenArray=TokenizerAction(splitToken);*/
	    nonce=getNonce(driver);
	    //System.out.println(nonce);
	    strforMD5 = nonce.concat(variabledo.pin+variabledo.pinPass);
	    //System.out.println(strforMD5);
	    hashValue=actiondo.getMD5(strforMD5);
	    commonAPIUrl = "user="+variabledo.pin+"&password="+hashValue+"&nonce="+nonce;

	    for(;i<variabledo.ReferralAPIArray.length;i++){
	    	
	    	
	    	if (i==0) { //Get Running Referral API
	    		
	    		variabledo.totalcases++;
		        APIUrl = actiondo.BaseURL().concat(variabledo.ReferralAPIArray[i]+commonAPIUrl);
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;
		        	}

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }

	        }
	    	else if (i==1) { //Get Refer URL API
	    		
	    		variabledo.totalcases++;
		        APIUrl = actiondo.BaseURL().concat(variabledo.ReferralAPIArray[i]+commonAPIUrl);
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(2000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
	        	splitToken=driver.findElement(By.xpath("//body")).getText();
	        	splitToken=splitToken.replaceAll("Media;URL", "");
	        	//splitToken=splitToken.replaceAll("{:,}''","");
	    		String delims = "[;|\n]";
	    		TokenArray=splitToken.split(delims);
	        	for (int j=2; j< TokenArray.length; j++){
	        		//System.out.println("StringTokenizer Output: " +TokenArray[j]);
	        		if(j%2!=0){
	        			TokenArray[j]=TokenArray[j].replaceAll(actiondo.BaseURL()+"/", "");
	        			TokenArray[j]=TokenArray[j].replaceAll(".refer","&");
	        		}
	        	}
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;
		        	}

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        	
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }

	        }
	        
	        else if (i==2) { //Confirm Invite API
	        	
	        	
	        	for (int j=2; j< TokenArray.length; j=j+2){
	        		//System.out.println("StringTokenizer Output: " +TokenArray[j]);
	        		if(j==2){ //Email Channel
	        			variabledo.totalcases++;
	        			
	    		        APIUrl = actiondo.BaseURL().concat(variabledo.ReferralAPIArray[i]+TokenArray[j+1]+"contact="+actiondo.EmailIDgenerator()+"&"+commonAPIUrl);
	    		        driver.get(APIUrl);
	    		        try {
	    		            Thread.sleep(5000);                 //1000 milliseconds is one second.
	    		        } catch(InterruptedException ex) {
	    		            Thread.currentThread().interrupt();
	    		        }
	    		        getbody=driver.findElement(By.tagName("body")).getText();
	    		        variabledo.actualTitle = driver.getTitle();
	    		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
	    		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
	    			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
					        	actiondo.PlayAlarmAction();
					        	variabledo.totalfailedcases++;
	    		        	}

	    		        }

	    		        else
	    		        {
	    		        	if(getbody.isEmpty()) {
	    			        	
	    			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
	    			        	System.out.println("No Output Found");
					        	actiondo.PlayAlarmAction();
					        	
	    			        }
	    		        	else {
	    		        		totalsuccessfulcases++;
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
	    			        	//System.out.println("Successful");
	    		        	}
	    		        }

	        		}
	        		else if(j==4){ //Twitter Channel
	        			
	        			variabledo.totalcases++;
	    		        APIUrl = actiondo.BaseURL().concat(variabledo.ReferralAPIArray[i]+TokenArray[j+1]+"contact=Twitter:Share&"+commonAPIUrl);
	    		        driver.get(APIUrl);
	    		        try {
	    		            Thread.sleep(5000);                 //1000 milliseconds is one second.
	    		        } catch(InterruptedException ex) {
	    		            Thread.currentThread().interrupt();
	    		        }
	    		        getbody=driver.findElement(By.tagName("body")).getText();
	    		        variabledo.actualTitle = driver.getTitle();
	    		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
	    		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
	    			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
					        	actiondo.PlayAlarmAction();
					        	variabledo.totalfailedcases++;
	    		        	}

	    		        }

	    		        else
	    		        {
	    		        	if(getbody.isEmpty()) {
	    			        	
	    			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
	    			        	System.out.println("No Output Found");
					        	actiondo.PlayAlarmAction();
					        	
	    			        }
	    		        	else {
	    		        		totalsuccessfulcases++;
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
	    			        	//System.out.println("Successful");
	    		        	}
	    		        }

	        		}
	        		else if(j==6){ //Facebook Channel
	        			
	        			variabledo.totalcases++;
	    		        APIUrl = actiondo.BaseURL().concat(variabledo.ReferralAPIArray[i]+TokenArray[j+1]+"contact=FB:Share&"+commonAPIUrl);
	    		        driver.get(APIUrl);
	    		        try {
	    		            Thread.sleep(5000);                 //1000 milliseconds is one second.
	    		        } catch(InterruptedException ex) {
	    		            Thread.currentThread().interrupt();
	    		        }
	    		        getbody=driver.findElement(By.tagName("body")).getText();
	    		        variabledo.actualTitle = driver.getTitle();
	    		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
	    		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
	    			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
					        	actiondo.PlayAlarmAction();
					        	variabledo.totalfailedcases++;
	    		        	}

	    		        }

	    		        else
	    		        {
	    		        	if(getbody.isEmpty()) {
	    			        	
	    			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
	    			        	System.out.println("No Output Found");
					        	actiondo.PlayAlarmAction();
					        	
	    			        }
	    		        	else {
	    		        		totalsuccessfulcases++;
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
	    			        	//System.out.println("Successful");
	    		        	}
	    		        }

	        		}
	        		else if(j==8){ //LinkedIn Channel
	        			
	        			variabledo.totalcases++;
	    		        APIUrl = actiondo.BaseURL().concat(variabledo.ReferralAPIArray[i]+TokenArray[j+1]+"contact=LinkedIn:Share&"+commonAPIUrl);
	    		        driver.get(APIUrl);
	    		        try {
	    		            Thread.sleep(5000);                 //1000 milliseconds is one second.
	    		        } catch(InterruptedException ex) {
	    		            Thread.currentThread().interrupt();
	    		        }
	    		        getbody=driver.findElement(By.tagName("body")).getText();
	    		        variabledo.actualTitle = driver.getTitle();
	    		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
	    		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
	    			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
					        	actiondo.PlayAlarmAction();
					        	variabledo.totalfailedcases++;
	    		        	}

	    		        }

	    		        else
	    		        {
	    		        	if(getbody.isEmpty()) {
	    			        	
	    			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
	    			        	System.out.println("No Output Found");
					        	actiondo.PlayAlarmAction();
					        	
	    			        }
	    		        	else {
	    		        		totalsuccessfulcases++;
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
	    			        	//System.out.println("Successful");
	    		        	}
	    		        }

	        		}
	        		else //SMS Channel
	        		{
	        			
	        			variabledo.totalcases++;
	    		        APIUrl = actiondo.BaseURL().concat(variabledo.ReferralAPIArray[i]+TokenArray[j+1]+"contact="+actiondo.callerIDset()+"&"+commonAPIUrl);
	    		        driver.get(APIUrl);
	    		        try {
	    		            Thread.sleep(5000);                 //1000 milliseconds is one second.
	    		        } catch(InterruptedException ex) {
	    		            Thread.currentThread().interrupt();
	    		        }
	    		        getbody=driver.findElement(By.tagName("body")).getText();
	    		        variabledo.actualTitle = driver.getTitle();
	    		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
	    		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
	    			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
					        	actiondo.PlayAlarmAction();
					        	variabledo.totalfailedcases++;
	    		        	}

	    		        }

	    		        else
	    		        {
	    		        	if(getbody.isEmpty()) {
	    			        	
	    			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
	    			        	System.out.println("No Output Found");
					        	actiondo.PlayAlarmAction();
					        	
	    			        }
	    		        	else {
	    		        		totalsuccessfulcases++;
	    			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
	    			        	//System.out.println("Successful");
	    		        	}
	    		        }

	        		}
	        	}
	        }
	        else if (i==3) { //Record Referred Sign up API

	        		//=====================PIN signup through API==================

	        		//String APIUrl = "";
	        		variabledo.totalcases++;
	        		String currentDate = "";
	        		currentDate = actiondo.getCurrentDate();
	        		int randomValue = (int )(Math.random() * 500 + 1);
	        		String newPIN=variabledo.clientNameArray[5]+'-'+currentDate+'-'+randomValue;
	        		APIUrl = actiondo.BaseURL().concat("/api/signupPin.jsp?type=verify&mobileNumber="+newPIN+"&password=1&callerIDs="+newPIN);
	        		driver.get(APIUrl);
	    	        variabledo.actualTitle = driver.getTitle();
	    	        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
	    	            System.out.println("Test Failed with Exception:"+APIUrl);
	    	        }else {
	    	            //System.out.println("Test Passed:");
	    	        }
	        		APIUrl = actiondo.BaseURL().concat("/api/signupPin.jsp?type=signUp&mobileNumber="+newPIN);
	        		driver.get(APIUrl);
			        getbody=driver.findElement(By.tagName("body")).getText();
			        variabledo.actualTitle = driver.getTitle();
			        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
			        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
				        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
				        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
				        	actiondo.PlayAlarmAction();
				        	variabledo.totalfailedcases++;
			        	}

			        }

			        else
			        {
			        	if(getbody.isEmpty()) {
				        	
				        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
				        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
				        	System.out.println("No Output Found");
				        	actiondo.PlayAlarmAction();
				        }
			        	else {
			        		totalsuccessfulcases++;
				        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
				        	//System.out.println("Successful");
			        	}
			        }
	        	//String referee=clientdo.SinglePINAddAction(driver);
	    	    strforMD5 = nonce.concat(newPIN+variabledo.pinPass);
	    	    //System.out.println(strforMD5);
	    	    hashValue=actiondo.getMD5(strforMD5);
	        	String commonAPIUrl1 = "user="+newPIN+"&password="+hashValue+"&nonce="+nonce;
	        	//System.out.println("New PIN: " +commonAPIUrl1);
	        	//APICases(driver,variabledo.ReferralAPIArray[i],commonAPIUrl);
	        	variabledo.totalcases++;
		        APIUrl = actiondo.BaseURL().concat(variabledo.ReferralAPIArray[i]+TokenArray[11]+commonAPIUrl1);
		        //System.out.println("Referral API: " +APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(5000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;
		        	}

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }

	        }
	        else { //Record Referral Report API
	        	
	        	variabledo.totalcases++;
		        APIUrl = actiondo.BaseURL().concat(variabledo.ReferralAPIArray[i]+commonAPIUrl);
		        System.out.println("Referral API: " +APIUrl);
		        //APICases(driver,APIUrl);
		        driver.get(APIUrl);
		        try {
		            Thread.sleep(5000);                 //1000 milliseconds is one second.
		        } catch(InterruptedException ex) {
		            Thread.currentThread().interrupt();
		        }
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)){
		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;
		        	}

		        }

		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }

	        }
	    }
	    totalcases=i;
	    actiondo.TestSummaryCreator("Referral API", totalcases, totalsuccessfulcases);
	    totalcases=0;
	    totalsuccessfulcases=0;
	} //end of ReferralAPIAction
	
	public void ClientAddAPIAction(WebDriver driver) 
	{
		actiondo.writing("**********ClientAddAPIAction***********");
		actiondo.ExcelFileWriteAction("**********ClientAddAPIAction***********","");
		actiondo.ConsolPrint("**********ClientAddAPIAction***********","");
		
		int i=0;
/*	    APIUrl = actiondo.BaseURL().concat("/profilePictureHandler.do?requesttype=getNonce&username="+variabledo.pin);
	    driver.get(APIUrl);
	    splitToken=driver.findElement(By.xpath("//body")).getText();
	    //nonce=driver.getPageSource();
	    //nonce = nonce.substring(43,51);
	    TokenArray=TokenizerAction(splitToken);*/
	    nonce=getNonce(driver);
		//String APIUrl = "";
		String currentDate = "";
		for(;i<=3;i++){
		
			currentDate = actiondo.getCurrentDate();
			int randomValue = (int )(Math.random() * 500 + 1);
			
			String CustomerID=variabledo.clientNameArray[i]+currentDate+'-'+randomValue;


			if (i==0) { // Originating Client Add API
				variabledo.totalcases++;
				APIUrl=actiondo.BaseURL().concat(variabledo.ClientAddAPIArray[0]+CustomerID+"&password=abc1&customerType="+(i+1)+"&ip="+actiondo.randomIPGenerator());
				driver.get(APIUrl); //call verify API for org client
				
				APIUrl=actiondo.BaseURL().concat(variabledo.ClientAddAPIArray[1]+CustomerID);
				driver.get(APIUrl); //call Sign UP API for org client
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
		        		
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	variabledo.totalfailedcases++;
			        	
		        	}


		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
			}else if (i==1) { // Terminating Client Add API
				variabledo.totalcases++;
				APIUrl=actiondo.BaseURL().concat(variabledo.ClientAddAPIArray[0]+CustomerID+"&password=abc1&customerType="+(i+1)+"&ip="+actiondo.randomIPGenerator());
				driver.get(APIUrl); //call verify API for ter client
				
				APIUrl=actiondo.BaseURL().concat(variabledo.ClientAddAPIArray[1]+CustomerID);
				driver.get(APIUrl); //call Sign UP API for ter client
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
		        		variabledo.totalfailedcases++;
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	
		        	}


		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
			}else if (i==2) { // Both Client Add API
				variabledo.totalcases++;
				APIUrl=actiondo.BaseURL().concat(variabledo.ClientAddAPIArray[0]+CustomerID+"&password=abc1&customerType="+(i+1)+"&ip="+actiondo.randomIPGenerator());
				driver.get(APIUrl); //call verify API for both client
				
				APIUrl=actiondo.BaseURL().concat(variabledo.ClientAddAPIArray[1]+CustomerID);
				driver.get(APIUrl); //call Sign UP API for both client
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        
		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
		        		variabledo.totalfailedcases++;
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	
		        	}


		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
		        
			}else if (i==3) { // Reseller Client Add API
				variabledo.totalcases++;
				APIUrl=actiondo.BaseURL().concat(variabledo.ClientAddAPIArray[0]+CustomerID+"&password=abc1&customerType="+(i+1));
				driver.get(APIUrl); //call verify API for both client
				
				APIUrl=actiondo.BaseURL().concat(variabledo.ClientAddAPIArray[1]+CustomerID);
				driver.get(APIUrl); //call Sign UP API for both client
		        getbody=driver.findElement(By.tagName("body")).getText();
		        variabledo.actualTitle = driver.getTitle();
		        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
		        		variabledo.totalfailedcases++;
			        	actiondo.writing("Test Failed with Exception" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
			        	actiondo.PlayAlarmAction();
			        	
		        	}


		        else
		        {
		        	if(getbody.isEmpty()) {
			        	
			        	actiondo.writing("Test Failed with no output found:" + i + " " + APIUrl);
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
			        	System.out.println("No Output Found");
			        	actiondo.PlayAlarmAction();
			        }
		        	else {
		        		totalsuccessfulcases++;
			        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
			        	//System.out.println("Successful");
		        	}
		        }
			}

		} //for loop end
		
	    totalcases=i;
	    actiondo.TestSummaryCreator("Client Add API", totalcases, totalsuccessfulcases);
	    totalcases=0;
	    totalsuccessfulcases=0;
	    
	    strforMD5 = nonce.concat(variabledo.DIDPIN+variabledo.DIDPINPass);
		APIUrl=actiondo.BaseURL().concat(variabledo.ClientAddAPIArray[2]+variabledo.DIDPIN+"&pass="+actiondo.getMD5(strforMD5)+"&nonce="+nonce);
		driver.get(APIUrl); //DID API Check
		variabledo.totalcases++;
        getbody=driver.findElement(By.tagName("body")).getText();
        variabledo.actualTitle = driver.getTitle();
        	if (variabledo.actualTitle.contentEquals(variabledo.expectedTitle2)) {
        		variabledo.totalfailedcases++;
	        	actiondo.writing("Test Failed with Exception" + " " + APIUrl);
	        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with Exception");
	        	actiondo.PlayAlarmAction();
	        	
        	}

        else
        {
        	if(getbody.isEmpty()) {
	        	
	        	actiondo.writing("Test Failed with no output found:" + " " + APIUrl);
	        	actiondo.ExcelFileWriteAction(APIUrl, "Test Failed with no output found");
	        	actiondo.PlayAlarmAction();
	        	System.out.println("No Output Found");
	        }
        	else {
	        	actiondo.ExcelFileWriteAction(APIUrl, "Test Successful");
	        	//System.out.println("Successful");
        	}
        }
	}
	
}
