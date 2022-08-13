import net.bytebuddy.asm.Advice;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.sql.Driver;
import java.time.Duration;
import java.util.Set;

public class Todo {

    //https://todomvc.com/examples/vue/

    public WebDriver driver;

    @BeforeEach
    public void BeforeTest() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/main/resources/Drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void Test1() {
        driver.navigate().to("https://todomvc.com/examples/vue/");
        driver.findElement(By.className("new-todo")).sendKeys("Hey" + Keys.ENTER);

        WebElement checkbox = driver.findElement(By.xpath("//input[@class='toggle']"));
        checkbox.click();
        WebElement Task = driver.findElement(By.xpath("//li[@class='todo completed']"));
        Task.click();


        String checkBoxExpected = "todo completed";
        String checkBoxActual = Task.getAttribute("class");

        Assert.assertEquals(checkBoxExpected, checkBoxActual);

    }

    @Test
    public void Test2() throws InterruptedException {
        driver.navigate().to("https://demo.automationtesting.in/Alerts.html");
        driver.findElement(By.xpath("//a[@href='#Textbox']")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector(".btn-info"));
        Thread.sleep(3000);
        Alert alertFirst = driver.switchTo().alert();
        alertFirst.sendKeys("Trayana" + Keys.ENTER);

        Assert.assertEquals(driver.switchTo().alert().getText(), "Hello Trayana How are you today");

    }

    @Test
    public void Test3() throws InterruptedException {
        driver.navigate().to("https://demo.automationtesting.in/Frames.html");
        driver.findElement(By.xpath("//a[@href='#Single']"));
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("input")).sendKeys("Hello");
        Thread.sleep(3000);
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@href= 'Index.html']")).click();
        Thread.sleep(3000);
    }

    @Test
    public void Test4() throws InterruptedException {
        String mainWindow = driver.getWindowHandle();
        driver.switchTo().window(mainWindow);

        Set<String> allWindows = driver.getWindowHandles();
        int counter = 0;
//         Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {

//            counter++;
            driver.switchTo().window(winHandle);
//            if(counter==3)break;

            driver.navigate().to("https://demo.automationtesting.in/Windows.html");
            driver.findElement(By.xpath("//a[@href='#Tabbed']")).click();
            Thread.sleep(3000);
            driver.findElement(By.tagName("</button>")).click();

            driver.findElement(By.xpath("//h1[@class='display-1 mt-0 mt-md-5 pb-1']"));
            Assert.assertEquals("Selenium automates browsers. \n" + "That's it!", "class");
        }
    }

    @Test
    public void Test5() throws InterruptedException{
        driver.navigate().to("https://demo.automationtesting.in/Static.html");
        Actions actions = new Actions(driver);
        WebElement dragged = driver.findElement(By.id("node"));
        actions.moveToElement(dragged).perform();
        WebElement dropped = driver.findElement(By.xpath("//div[@id='droparea']"));
    }


        @AfterEach
        public void afterClass () {
            driver.quit();
        }
    }

