package learningTestingFramework;
import static org.junit.Assert.*;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;

public class JuintTC {
	static WebDriver driver;
	public static void Wait(int timeu) {
		 driver.manage().timeouts().implicitlyWait(timeu, TimeUnit.SECONDS);
	}
	public static void waittillobjectvisible(By ele, int tim) {
		 try {
			 
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void takescreenshot(WebDriver driver) {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot)augmentedDriver).
                            getScreenshotAs(OutputType.FILE);
        
        try {
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        	SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd-HH");
        	String path= new File(System.getProperty("user.dir")).getAbsolutePath();
        	path=path+"\\Screenshot";
        	
        	Timestamp timestamp= new Timestamp(System.currentTimeMillis());
        	String name=path+"\\"+sdf_1.format(timestamp).toString()+"\\"+sdf.format(timestamp).toString()+".png";
        	System.out.println(name);
        	FileUtils.copyFile(screenshot, new File(name));
        	}
    	 
    	catch (IOException e)
    	 {
    	  System.out.println(e.getMessage());
    	 
    	 }
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium Workspace\\chromedriver.exe");
		
		ChromeOptions option= new ChromeOptions();
		option.setBinary(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));
		option.addArguments("disable-infobars");
		option.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		option.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		option.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		option.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		option.setCapability(CapabilityType.TAKES_SCREENSHOT,true );
		
		
		driver= new ChromeDriver(option);
		Wait(5);
		driver.manage().window().maximize();
		Wait(2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
        driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Throwable{
		try {
			driver.navigate().to("http://www.google.com");
			Wait(3);
			takescreenshot(driver);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
	}
	
	
}
