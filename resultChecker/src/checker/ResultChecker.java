package checker;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import notification.Gmail;
import notification.TrayIconDemo;

import java.awt.*;



public class ResultChecker {
	
	public static void main(String[] args) {
		WebDriver driver = null;
		
		
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		
		
		driver.navigate().to("http://is.fon.bg.ac.rs/");
		
		WebElement wb = driver.findElement(By.xpath("/html"));
		String prviInnerHtml = wb.getAttribute("innerHTML");
		System.out.println("uzet prvi html");
		
		
		
		
		while(true) {
			try {
				Thread.sleep(300000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			driver.navigate().to("http://is.fon.bg.ac.rs/");
			
			WebElement wb2 = driver.findElement(By.xpath("/html"));
			
			String drugiInnerHtml = wb2.getAttribute("innerHTML");
			
			boolean rez = prviInnerHtml.equals(drugiInnerHtml);
			System.out.println(rez);
			if(!rez) {
				sendMailAndNotification();
				prviInnerHtml = drugiInnerHtml;
			}
			
		}
		
	}
	private static void sendMailAndNotification() {
		String myself = "milan.brkic1998@gmail.com";
		TrayIconDemo td = new TrayIconDemo();
	    
	    try {
	    	td.displayTray();
			Gmail.sendMail(myself);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
