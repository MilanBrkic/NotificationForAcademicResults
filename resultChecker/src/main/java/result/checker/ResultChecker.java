package result.checker;

import domain.MyMessage;
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

import result.notification.Gmail;
import result.notification.TrayIconDemo;

import java.awt.*;

public class ResultChecker extends Thread {

     MyMessage msg;

    public ResultChecker(MyMessage msg) {
        this.msg = msg;
    }
    
    

    @Override
    public void run() {
       WebDriver driver = null;
        try {
            String webpage = msg.getWebpage();
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\scrapmate\\results\\driver\\chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
//            options.setHeadless(true);
            driver = new ChromeDriver(options);

            driver.navigate().to(webpage);

            WebElement wb = driver.findElement(By.xpath("/html"));
            String prviInnerHtml = wb.getAttribute("innerHTML");
            System.out.println("uzet prvi html");
            int seconds = msg.getSeconds()*1000;
            while (true) {
                try {
                    Thread.sleep(seconds);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                driver.navigate().to(webpage);
                try {
                    Thread.sleep(20000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                WebElement wb2 = driver.findElement(By.xpath("/html"));

                String drugiInnerHtml = wb2.getAttribute("innerHTML");

                boolean rez = prviInnerHtml.equals(drugiInnerHtml);
                System.out.println(rez);
                if (!rez) {
                    sendMailAndNotification();
                    prviInnerHtml = drugiInnerHtml;
                }

            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.close();
            }
        }
    }
    
    
    

    private void sendMailAndNotification() {
        
        TrayIconDemo td = new TrayIconDemo();

        try {
            td.displayTray();
            new Gmail().sendMail(msg);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
