package com.student.lab2.hello;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsWeb {
    public static final String URL = "http://localhost:8080/tree";

    @BeforeClass
    public static void startProcess() {
        String[] args = new String[]{""};
        Application.main(args);
    }

    static {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver/chromedriver.exe");
    }

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void checkMainElements() {

        driver.navigate().to(URL);
        Assert.assertTrue(driver.getPageSource().contains("id=\"value_add\"") &&
                driver.getPageSource().contains("id=\"value_del\"") &&
                driver.getPageSource().contains("id=\"out\"") &&
                driver.getPageSource().contains("id=\"Add_but\"") &&
                driver.getPageSource().contains("id=\"Del_but\""));
    }

    @Test
    public void sendEmpty() {
        driver.navigate().to(URL);
        driver.findElement(By.id("Add_but")).click();
        waitForLoad(10);
        driver.findElement(By.id("Del_but")).click();
        waitForLoad(10);
    }

    @Test
    public void sendWrong() {
        driver.navigate().to(URL);
        driver.findElement(By.id("value_add")).sendKeys("asd");
        driver.findElement(By.id("Add_but")).click();
        waitForLoad(10);
    }

    @Test
    public void sendSomeIntsAndDeleteThem() throws InterruptedException {
        driver.navigate().to(URL);
        Random r = new Random();
        Set<Long> keys = parseTreeKeys();
        ArrayList<Long> added = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            int key = r.nextInt()%1000;
            keys.add((long)key);
            added.add((long) key);
            driver.findElement(By.id("value_add")).sendKeys(key+"");
            driver.findElement(By.id("Add_but")).click();
            waitForLoad(1);
            TimeUnit.SECONDS.sleep(1);
        }
        for(long key : added) {
            keys.remove((Long)key);
            driver.findElement(By.id("value_del")).sendKeys(key+"");
            driver.findElement(By.id("Del_but")).click();
            waitForLoad(1);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @After
    public void clean() {
        driver.close();
        driver.quit();
    }

    private void waitForLoad(int timeout) {
        new WebDriverWait(driver, timeout).until((ExpectedCondition<Boolean>) (driver)->{
            try {
                return driver.getPageSource().contains("id=\"value_add\"") &&
                        driver.getPageSource().contains("id=\"value_del\"") &&
                        driver.getPageSource().contains("id=\"out\"") &&
                        driver.getPageSource().contains("id=\"Add_but\"") &&
                        driver.getPageSource().contains("id=\"Del_but\"");
            }catch(Exception e) {
                return false;
            }
        });
    }

    private Set<Long> parseTreeKeys() {
        TreeSet<Long> keys = new TreeSet<>();
        String[] data = driver.findElement(By.id("out")).getText().split("\n");
        for(String line : data) {
            if(!line.startsWith("---") && line.length() > 0) {
                while(!(line = line.trim()).isEmpty()) {
                    int i0 = line.indexOf('\t');
                    i0 = i0 < 0 ? line.length() : i0;
                    int i1 = line.indexOf(' ');
                    i1 = i1 < 0 ? line.length() : i1;
                    int i = Math.min(i0, i1);
                    keys.add(Long.parseLong(line.substring(0, i)));
                    line = line.substring(i);
                }
            }
        }
        return keys;
    }
}
