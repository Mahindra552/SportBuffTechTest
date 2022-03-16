package SportBuffAutomation;

import com.github.javafaker.Faker;
import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class SportBuff {

    public static void main(String[] args) {

        //Print SportBuff Tech Assessment
        System.out.println("SportBuff Tech Assessment");


        //setProperty ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C://SportBuffTechAssessment//chromedriver.exe");

        //Launch the Chrome browser
        WebDriver driver = new ChromeDriver();

        //Launch the SportBuff website video Url
        driver.get("https://demo.sportbuff.com/?videoID=Tennis_Channel_Demo");

        //Maximize the Page (Full window Screen)
        driver.manage().window().maximize();


        //Generate randomEmail and randomUserName using Java Faker
        Faker faker = new Faker();

        String randomFirstName = faker.name().firstName();
        String randomLastName = faker.name().lastName();

        String randomEmail = randomFirstName + "." + randomLastName + "@yopmail.com";
        System.out.println(randomEmail);
        String randomUserName = randomFirstName + " " + randomLastName;
        System.out.println(randomUserName);


        //Enter random User email
        driver.findElement(By.id("email")).sendKeys(randomEmail);

        //Enter random Username
        driver.findElement(By.id("username")).sendKeys(randomUserName);

        // click on Start VOD Demo
        driver.findElement(By.xpath("//button[@type='submit'][contains(.,'Start VOD Demo')]")).click();

        //wait 30 seconds to load video properly
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Demo Page is displayed with h1 Tag text(Sport Buff Demo)
         WebElement demoPage=driver.findElement(By.xpath("//h1[@class='BuffInfo_title__3RDez'][contains(.,'Sport Buff Demo')]"));
         String h1Tag=demoPage.getText();
         System.out.println(h1Tag);

      //Check that a video is playing in the demo page
        WebElement video = driver.findElement(By.id("video"));
        video.isDisplayed();

        //get the videoCurrentTime displayed
        String videoCurrentTime= driver.findElement(By.id("video")).getAttribute("currentTime");
        System.out.println(videoCurrentTime);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");

        Actions action=new Actions(driver);

        //Mouse over actions using Actions class
        action.moveToElement(video).build().perform();

        //click on the dropdown button
        driver.findElement(By.xpath("//button[contains(.,'event flex')]")).click();

        //click on Settings
        driver.findElement(By.xpath("//div[contains(.,'Settings')]")).click();

        //driver.findElement(By.xpath("//div[contains(@id,'SportBuff-container')]")).click();
        /*JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(videoPage.click());*/
        //driver.findElement(By.xpath("/div/div/div[1]/div/div/div/button[2]")).click();


        //Update the random User email in profile section
        driver.findElement(By.id("email")).sendKeys(randomEmail);
        System.out.println(randomEmail);

        //Update the random Username in profile section
        driver.findElement(By.id("username")).sendKeys(randomUserName);
        System.out.println(randomUserName);

        // establish and open connection with URL-return status code 200
        /*HttpURLConnection c=
                (HttpURLConnection)new URL("https://sdk.prod.buffup.net/api/v1/buffs/896014/answer")
                        .openConnection();
        // set the HEAD request with setRequestMethod
        c.setRequestMethod("HEAD");
        // connection started and get response code
        c.connect();
        int r = c.getResponseCode();
        System.out.println("Http response code: " + r);*/


        // Close the driver window
        driver.quit();

    }
}
