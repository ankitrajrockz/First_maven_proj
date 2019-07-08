package learningTestingFramework;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.plaf.ToolBarUI;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGTC {
	
	static WebDriver driver;
	public static void Wait(int timeu) {
		 driver.manage().timeouts().implicitlyWait(timeu, TimeUnit.SECONDS);
	}
	public static void waittillobjectvisible(By ele, int tim) {
		 try {
			 WebDriverWait wait = new WebDriverWait (driver, tim);
			
			 wait.until(ExpectedConditions.presenceOfElementLocated(ele));
			 wait.until(ExpectedConditions.elementToBeClickable(ele));
			 wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void fluentwait() {
		@SuppressWarnings("deprecation")
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		        .withTimeout(30, TimeUnit.SECONDS)
		        .pollingEvery(5, TimeUnit.SECONDS)
		        .ignoring(NoSuchElementException.class);
		
	}
	public static void takescreenshot(WebDriver driver) {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot)augmentedDriver).
                            getScreenshotAs(OutputType.FILE);
        
        try {
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS");
        	SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd");
        	String path= new File(System.getProperty("user.dir")).getAbsolutePath();
        	path=path+"\\Screenshot";
        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        	
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
	
	public static void completeWindowSC()  {
		
		try {
			BufferedImage image= new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			//RenderedImage rendImage = image;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS");
        	SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd");
        	String path= new File(System.getProperty("user.dir")).getAbsolutePath();
        	path=path+"\\Screenshot";
        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        	
        	Timestamp timestamp= new Timestamp(System.currentTimeMillis());
        	String name=path+"\\"+sdf_1.format(timestamp).toString()+"\\"+sdf.format(timestamp).toString()+".png";
        	System.out.println(name);
        	
			ImageIO.write(image, "PNG", new File(name));
			ImageIO.getWriterFormatNames();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@BeforeClass
	public void browserInvoke() throws Exception {
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
		option.setCapability(CapabilityType.SUPPORTS_LOCATION_CONTEXT,true );
		
		driver= new ChromeDriver(option);
		Wait(5);
		driver.manage().window().maximize();
		Wait(2);
	}

	@AfterClass
	public void browserClose() throws Exception {
		driver.close();
        driver.quit();
	}
	
	@Test(priority=0)
	public void testngfirst() {
		try { 	 	
			driver.switchTo().frame("");
			driver.navigate().refresh();
			//driver.manage().timeouts().implicitlyWait(time, unit)
			driver.get("http://in.bookmyshow.com");
			Wait(3);
			//takescreenshot(driver);
			String title= driver.getTitle();
			completeWindowSC() ;
			//Assert.assertEquals("actual", "expected");
			Assert.assertTrue(title.contains("Movie Tickets"),"Browser Name mismatchS");
			System.out.println(title);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
	}
	@Test(priority=1)
	public void testngSoftAssert() {
		try {
			
			String title2= driver.getTitle();
			SoftAssert softasrt=new SoftAssert();
			completeWindowSC() ;
			softasrt.assertEquals(title2, "Movie Tickets, Plays, Sports, Events & Cinemas nearby - BookMyShow","Browser Name mismatchS");
			System.out.println(title2);
			softasrt.assertAll();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
	}
}
