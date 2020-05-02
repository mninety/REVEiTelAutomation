package Firebug;

//import org.openqa.selenium.webdriver.common.action_chains;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MyAction.ActionModule;
import MyVariable.VariableModule;



public class FirebugModule {

    public static void FacebookLogin(String post) {
    	String url = "https://facebook.com";
        String user_name = "mninety@gmail.com";
        String pwd = "Ninety02#";
        //WebDriver driver = new FirefoxDriver();
        WebDriver driver = ActionModule.ChromedriverAction();
        
       
        //driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(user_name);
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[contains(@value,'Log In')]")).click();
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}                
        System.out.println("logged in successfully");

        
        driver.findElement(By.className("_3u16")).click();
        //driver.findElement(By.className("_1mf _1mj")).sendKeys("Test");
        WebElement elem=driver.findElement(By.cssSelector("._1mf._1mj"));
        //driver.findElement(By.xpath("//*[@class='_1mf _1mj']")).sendKeys("Test");
        Actions builder = new Actions(driver);
        builder.moveToElement(elem);
        builder.click();
        builder.sendKeys(post);
        builder.perform();
        driver.findElement(By.className("_6c0o")).click();
        try {
            Thread.sleep(10000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.close();

    }
}
