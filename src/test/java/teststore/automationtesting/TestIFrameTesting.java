package teststore.automationtesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestIFrameTesting {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    @DisplayName("Test IFrame Selection Successful")
    public void testToggleSuccessful() {
        driver.get("https://automationtesting.co.uk/iframes.html");
        driver.switchTo().frame(0);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='sidebar']/a"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Accordion')]"))).click();
        WebElement tittle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='main']//h2[text()='Accordion Test']")));
        Assertions.assertEquals("Accordion Test", tittle.getText());
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Toggle')]"))).click();
    }
}
