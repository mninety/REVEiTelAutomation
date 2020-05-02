package MyAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.Select;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import BuyCredit.MyPaymentModule;
import Client.ClientModule;
import DevelopedAPI.DevelopedAPIModule;
import Firebug.FirebugModule;
import Login.LoginModule;
import MultithreadAPI.MultithreadAPIModule;
import MultithreadJSPAdmin.MultithreadJSPAdminModule;
import MultithreadPermission.MultithreadPermissionModule;
import MyAction.ActionModule;
import MyAutomation.MyClass.RadioButtonActionListener;
import MyEmail.EmailModule;
//import MyLogger.MyLoggerModule;
import MyPermission.PermissionModule;
import MyPermission.PermissionModule;
import MyVariable.VariableModule;
import ParallelTestNG.ParallelTestModule;
//import net.codejava.mail.MyDesign;
import Socket.Server;

import java.io.IOException;
import org.apache.log4j.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout.Constraints;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MyClass extends JFrame{
	static Logger log = Logger.getLogger(MyClass.class.getName());
	//static Logger logger = LogManager.getLogger();

    //=================Class Object Declare================

    ClientModule clientdo = new ClientModule();

    LoginModule logindo = new LoginModule();
    VariableModule variabledo = new VariableModule();
    static ActionModule actiondo = new ActionModule();
    static PermissionModule permissiondo = new PermissionModule();
    DevelopedAPIModule apido = new DevelopedAPIModule();
    MyPaymentModule paymentdo = new MyPaymentModule();
    EmailModule emaildo = new EmailModule();
    ParallelTestModule paralleldo=new ParallelTestModule();
    MultithreadAPIModule ThreadAPIdo = new MultithreadAPIModule();
    MultithreadPermissionModule ThreadPerdo = new MultithreadPermissionModule();
    MultithreadJSPAdminModule ThreadJSPAdo = new MultithreadJSPAdminModule();

    private JButton buttonOK = new JButton("Choose A Menu");

    private JRadioButton optionAPI = new JRadioButton("Developed API");
    private JRadioButton optionPer = new JRadioButton("Permission Checking");
    private JRadioButton optionJSP = new JRadioButton("JSP Traversal");
    private JRadioButton optionBuy = new JRadioButton("Buy Credit");
    private JRadioButton optionAll = new JRadioButton("All Module");


    public MyClass() {
        super("Test Automation Tools");
    	JFrame frame = new JFrame("Test Automation");
    	
        JLabel label2 = new JLabel("<html>****************Automation-Menu******************<br></html>", SwingConstants.CENTER);

        add(label2);
        
        final DefaultComboBoxModel boxlist = new DefaultComboBoxModel();
 
        boxlist.addElement("Google Chrome");
        boxlist.addElement("Mozilla Firefox");
        
        final JComboBox fruitCombo = new JComboBox(boxlist);    
        fruitCombo.setSelectedIndex(0);
        //add(fruitCombo);
        
        final ButtonGroup group = new ButtonGroup();
        optionAPI.setActionCommand ( "1" );
        group.add(optionAPI);
        optionPer.setActionCommand ( "2" );
        group.add(optionPer);
        optionJSP.setActionCommand ( "3" );
        group.add(optionJSP);
        optionBuy.setActionCommand ( "4" );
        group.add(optionBuy);
        optionAll.setActionCommand ( "5" );
        group.add(optionAll);



        optionAPI.setSelected(true); //Default Selection
        

        setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.BASELINE_LEADING;
        constraints.insets = new Insets(5, 75, 5, 5);
        
        
        constraints.gridy = 0;
        
        constraints.gridy = 1;
        add(fruitCombo, constraints);
        constraints.gridy = 2;
        add(optionAPI, constraints);
        constraints.gridy = 3;
        add(optionPer, constraints);
        constraints.gridy = 4;
        add(optionJSP, constraints);
        constraints.gridy = 5;
        add(optionBuy, constraints);
        constraints.gridy = 6;
        add(optionAll, constraints);

        constraints.gridy = 7;
        add(buttonOK, constraints);

        
    RadioButtonActionListener actionListener = new     RadioButtonActionListener();
        optionAPI.addActionListener(actionListener);
        optionPer.addActionListener(actionListener);
        optionJSP.addActionListener(actionListener);
        optionBuy.addActionListener(actionListener);
        optionAll.addActionListener(actionListener);

        buttonOK.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                //Get "ID"
            	optionAPI.setForeground(Color.BLACK);
            	optionPer.setForeground(Color.BLACK);
            	optionJSP.setForeground(Color.BLACK);
            	optionBuy.setForeground(Color.BLACK);
            	optionAll.setForeground(Color.BLACK);
            	
            	
            String selectedOption = group.getSelection ().getActionCommand ();
            //System.out.println("Selected option " + selectedOption);
                //Switch on "IDS"
            String data = "";
            if (fruitCombo.getSelectedIndex() != -1) {                     
                data = "Browser Selected:" + fruitCombo.getItemAt
                   (fruitCombo.getSelectedIndex());
                System.out.println(data);
                

             }
            //statusLabel.setText(data);
                switch(selectedOption) {
                
                    case "1":

                    	optionAPI.setForeground(Color.GREEN);

                    	//System.out.println("Case1:"+data);
                    	
                        if(data.equals("Browser Selected:Google Chrome")){
                        	//System.out.println("Chrome:"+data);
                    	    //System.setProperty("webdriver.chrome.driver",variabledo.chromedriver);
                    	    WebDriver driver = actiondo.ChromedriverAction();

                    	    //driver.close();
                    	    //apido.CallerIDAPIAction(driver);
                    	    
                    	    apido.ClientAddAPIAction(driver);
                        	apido.ALLCommonAPIAction(driver);
							apido.PakcageAPIAction(driver);
                    	    apido.HistoryAPIAction(driver);
                    	    apido.AddFundAPIAction(driver);
                            //apido.ReferralAPIAction(driver);
                            
                        }
                        else {
                        	//System.out.println("Firefox:"+data);
                        	//System.setProperty("webdriver.gecko.driver",variabledo.firefoxdriver);
                    	    WebDriver driver = actiondo.FirefoxdriverAction();
                    	    

                    	    //apido.CallerIDAPIAction(driver);
                    	    
                            try {
                            	//emaildo.sendEmailWithAttachments(variabledo.mailhost, variabledo.mailport, variabledo.mailFrom, variabledo.mailpassword, variabledo.mailTo,variabledo.mailsubject, variabledo.mailmessage, variabledo.attachFiles);
                                System.out.println("Email sent.");
                            } catch (Exception ex) {
                                System.out.println("Could not send email.");
                                ex.printStackTrace();
                            }
                            
/*                    	    apido.ClientAddAPIAction(driver);
                        	apido.ALLCommonAPIAction(driver);
							apido.PakcageAPIAction(driver);
                    	    apido.HistoryAPIAction(driver);
                    	    apido.AddFundAPIAction(driver);
                            apido.ReferralAPIAction(driver);*/

                        }
                        
            	    
                	
                	break;
                    
                    case "2":
                    	
                    	optionPer.setForeground(Color.GREEN);

                        if(data.equals("Browser Selected:Mozilla Firefox")){
	                		//=====================Firefox====================
	                        //System.setProperty("webdriver.gecko.driver","C:\\WebDriver\\geckodriver.exe");
	                	    WebDriver driver = actiondo.FirefoxdriverAction();
	                        String APIUrl1 = actiondo.BaseURL().concat("/Login.do?username="+variabledo.pin+"&password="+variabledo.pinPass);
	                        driver.get(APIUrl1);
	                    	//permissiondo.PermissionAPIAction(driver);
                        }
                        else {
                        	//=====================Chrome====================
                        	//System.setProperty("webdriver.chrome.driver",variabledo.chromedriver);
                    	    WebDriver driver = actiondo.ChromedriverAction();
                            String APIUrl1 = actiondo.BaseURL().concat("/Login.do?username="+variabledo.pin+"&password="+variabledo.pinPass);
                            driver.get(APIUrl1);
                        	//permissiondo.PermissionAPIAction(driver);
                        }
                        
                    	break;
                    	
                    case "3":
                    	optionJSP.setForeground(Color.GREEN);
                        if(data.equals("Browser Selected:Mozilla Firefox")){
	                		//=====================Firefox====================
                        	//System.setProperty("webdriver.gecko.driver","C:\\WebDriver\\geckodriver.exe");
	                	    WebDriver driver = actiondo.FirefoxdriverAction();
	                	    //firebugdo.ClientAddFirebugAction(driver);
	                	    //permissiondo.JSPTraverseAdminAction(driver);
	                	    //permissiondo.JSPTraversePINAction(driver);

                        }
                        else {
                        	//=====================Chrome====================
                        	//System.setProperty("webdriver.chrome.driver",variabledo.chromedriver);
                    	    WebDriver driver = actiondo.ChromedriverAction();
                    	    LoginModule.AdminloginAction(driver);
                        	//permissiondo.JSPTraverseAdminAction(driver);
                        	//permissiondo.JSPTraversePINAction(driver);
                        }
                	    break;
                        
                    case "4":
                    	optionBuy.setForeground(Color.GREEN);
                        if(data.equals("Browser Selected:Mozilla Firefox")){
	                		//=====================Firefox====================
	                	    WebDriver driver = actiondo.FirefoxdriverAction();
	                	    paymentdo.BalanceTransferAction(driver);
	                	    paymentdo.BuyCreditAction(driver);
                	    
                        }
                        else {
                        	
                        	//=====================Chrome====================
                    	    WebDriver driver = actiondo.ChromedriverAction();
                    	    //paymentdo.RechargeByPINAction(driver);
                    	    paymentdo.BalanceTransferAction(driver);
                        	paymentdo.BuyCreditAction(driver);
                        	
                    	    
                    	    
                    	  //Check the SystemTray is supported

                        }
                        
                	    break;
                	    
                    case "5":
                    	optionAll.setForeground(Color.GREEN);
                    	
                    	ThreadAPIdo.start();
                    	ThreadPerdo.start();
                    	ThreadJSPAdo.start();


                    	  try {
                    	      //emaildo.sendEmailWithAttachments(variabledo.mailhost, variabledo.mailport, variabledo.mailFrom, variabledo.mailpassword, variabledo.mailTo,variabledo.mailsubject, variabledo.mailmessage, variabledo.attachFiles);
                    	      System.out.println("Email sent.");
                    	  } catch (Exception ex) {
                    	      System.out.println("Could not send email.");
                    	      ex.printStackTrace();
                    	  }
/*                        if(data.equals("Browser Selected:Mozilla Firefox")){
	                		//=====================Firefox====================
                        	//System.setProperty("webdriver.gecko.driver","C:\\WebDriver\\geckodriver.exe");
	                	    WebDriver driver = actiondo.FirefoxdriverAction();
	                    	
	                    	apido.ALLCommonAPIAction(driver);
	                        apido.HistoryAPIAction(driver);
	                        apido.ClientAddAPIAction(driver);
	                        apido.PakcageAPIAction(driver);
	                        apido.CallerIDAPIAction(driver);
	                        apido.ReferralAPIAction(driver);
	                        permissiondo.PermissionAPIAction(driver);
	                	    permissiondo.JSPTraverseAdminAction(driver);
	                	    permissiondo.JSPTraversePINAction(driver);
	                	    paymentdo.BalanceTransferAction(driver);
	                	    //paymentdo.BuyCreditAction(driver);
	                	    
	                	    
	                        try {
	                            emaildo.sendEmailWithAttachments(variabledo.mailhost, variabledo.mailport, variabledo.mailFrom, variabledo.mailpassword, variabledo.mailTo,variabledo.mailsubject, variabledo.mailmessage, variabledo.attachFiles);
	                            System.out.println("Email sent.");
	                        } catch (Exception ex) {
	                            System.out.println("Could not send email.");
	                            ex.printStackTrace();
	                        }
                	    
                        }
                        else {
                        	//=====================Chrome====================
                        	//System.setProperty("webdriver.chrome.driver",variabledo.chromedriver);
                    	    WebDriver driver = actiondo.ChromedriverAction();
                        	apido.ALLCommonAPIAction(driver);
                            apido.HistoryAPIAction(driver);
                            apido.ClientAddAPIAction(driver);
                            apido.PakcageAPIAction(driver);
                            apido.CallerIDAPIAction(driver);
                            apido.ReferralAPIAction(driver);
                            permissiondo.PermissionAPIAction(driver);
                    	    permissiondo.JSPTraverseAdminAction(driver);
                    	    permissiondo.JSPTraversePINAction(driver);
                    	    //paymentdo.BuyCreditAction(driver);
                    	    paymentdo.BalanceTransferAction(driver);
                    	    
                    	    
                            try {
                            	emaildo.sendEmailWithAttachments(variabledo.mailhost, variabledo.mailport, variabledo.mailFrom, variabledo.mailpassword, variabledo.mailTo,variabledo.mailsubject, variabledo.mailmessage, variabledo.attachFiles);
                                System.out.println("Email sent.");
                            } catch (Exception ex) {
                                System.out.println("Could not send email.");
                                ex.printStackTrace();
                            }
                        }*/
                	    break;
   
                }
            }

        });
        //setForeground(Color.GREEN);
        setPreferredSize(new Dimension(500, 500));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLocationRelativeTo(null);
    }


	class RadioButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JRadioButton button = (JRadioButton) event.getSource();
            if (button == optionAPI) {
            	//optionAPI.setForeground(Color.green);
                //System.out.println ( "Linux" );

            } else if (button == optionPer) {

                //System.out.println ( "Windows" );

            } else if (button == optionJSP) {

                //System.out.println ( "Mac" );
            }else if (button == optionBuy) {

                //System.out.println ( "Mac" );
            }
            else if (button == optionAll) {

                //System.out.println ( "Mac" );
            }
        }
    }
	
	
	public static void main(String[] args) throws IOException {
        
		//FirebugModule.FacebookLogin("Test");
/*		String accessToken="EAACXxklgdbMBAMQtcTW7umsWa5XsNS0XED90Wp4MUDHQn2B0BVk7zPi4CHXOFN1HZAE2cHxoTVkpLzjAdZAXUwuKYAugZBXR02SZAb7xvIppgmmOExKyxcuQPPDBZCw2zF6OAabqBNigVQQnyZCaW2ZA3jExCAhNZBBZADyPXYHFz6wZDZD";
		FacebookClient  fbclient= new DefaultFacebookClient(accessToken);
        FacebookType response= fbclient.publish("me/feed", FacebookType.class, Parameter.with("message", "Test"));
        System.out.println("Test ID: "+response.getId());*/
        
/*        int port = 5000;
        try {
           Thread t = new Server(port);
           t.start();
        } catch (IOException e) {
           e.printStackTrace();
        }*/
        //String Array=ActionModule.GetDateList("2018-05-27", "2018-06-04");

    	//System.out.println("Date Array: "+Array);
    	
        actiondo.FileBackupAction();
    	VariableModule.StartModule();
    	
    }
	
}
