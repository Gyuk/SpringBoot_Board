package com.capstone08.springstudy.controller;

import com.capstone08.springstudy.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppConfig.class)
@WebIntegrationTest(randomPort = true)
public class PostViewControllerHtmlTest {

    private static WebDriver driver;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/user/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void postViewTest() throws Exception {
        String baseURL = "http://localhost:" + port + "/write";
        driver.get(baseURL);

        driver.findElement(By.name("nick")).sendKeys("NICK");
        driver.findElement(By.name("subject")).sendKeys("SUBJECT");
        driver.findElement(By.name("content")).sendKeys("CONTENT");
        driver.findElement(By.tagName("form")).submit();

        WebElement td = driver.findElement(By.cssSelector("td.id_nick"));
        assertEquals("1 | 작성자 : NICK", td.getText());
        td = driver.findElement(By.cssSelector("td.hit"));
        assertEquals("조회수 1", td.getText());
        td = driver.findElement(By.cssSelector("td.subject"));
        assertEquals("제목 : SUBJECT", td.getText());
        td = driver.findElement(By.cssSelector("td.content"));
        assertEquals("CONTENT", td.getText());

        driver.findElement(By.className("back")).click();

        List<WebElement> div = driver.findElements(By.cssSelector("div.ps"));
        assertEquals(1, div.size());
        td = driver.findElement(By.cssSelector("td.bbsNo"));
        assertEquals("1", td.getText());
        td = driver.findElement(By.cssSelector("td.bbsSubject"));
        assertEquals("SUBJECT", td.getText());
        td = driver.findElement(By.cssSelector("td.bbsNick"));
        assertEquals("NICK", td.getText());
        Date d = new Date();
        SimpleDateFormat today = new SimpleDateFormat("yyyy/MM/dd");
        td = driver.findElement(By.cssSelector("td.date"));
        assertEquals(today.format(d), td.getText());
        td = driver.findElement(By.cssSelector("td.hit"));
        assertEquals("1", td.getText());

        driver.findElement(By.className("bbsSubject")).click();

        driver.findElement(By.className("del")).click();

        div = driver.findElements(By.cssSelector("div.ps"));
        assertEquals(0, div.size());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}