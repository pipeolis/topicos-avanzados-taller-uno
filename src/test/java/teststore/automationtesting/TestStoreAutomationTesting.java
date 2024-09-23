package teststore.automationtesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestStoreAutomationTesting {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.findElement(By.xpath("//div[@class='user-info']/child::a[@class='logout hidden-sm-down']")).click();
        driver.quit();
    }

    @Test
    @DisplayName("Test Login Successful")
    public void testLoginSuccessful() {
        driver.get("https://teststore.automationtesting.co.uk/index.php");
        driver.findElement(By.xpath("//div[@class='container']/child::div[@class='row']/child::div[@class='hidden-sm-down']/descendant::span[contains(text(),'Sign in')]")).click();
        driver.findElement(By.xpath("//section[@id='main']/descendant::div[@id='content']/child::div[@class='no-account']")).click();

        var firstName = "Juan";
        var lastName = "Perez";
        driver.findElement(By.xpath("//label[@class='radio-inline']/child::span[@class='custom-radio']/child::input[@id='field-id_gender-1']")).click();
        driver.findElement(By.xpath("//form[@id='customer-form']/descendant::input[@id='field-firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//form[@id='customer-form']/descendant::input[@id='field-lastname']")).sendKeys(lastName);

        var timestampedEmail = "juan.perez" + System.currentTimeMillis() + "@correo.com";
        driver.findElement(By.xpath("//form[@id='customer-form']/descendant::input[@id='field-email']")).sendKeys(timestampedEmail);
        driver.findElement(By.xpath("//form[@id='customer-form']/descendant::input[@id='field-password']")).sendKeys("1234567890");
        driver.findElement(By.xpath("//form[@id='customer-form']/descendant::input[@id='field-birthday']")).sendKeys("05/31/1970");

        // Click on the optional checkboxes
        driver.findElement(By.xpath("//span[@class='custom-checkbox']/descendant::input[@name='optin']")).click();
        driver.findElement(By.xpath("//span[@class='custom-checkbox']/descendant::input[@name='psgdpr']")).click();
        driver.findElement(By.xpath("//span[@class='custom-checkbox']/descendant::input[@name='newsletter']")).click();
        driver.findElement(By.xpath("//form[@id='customer-form']/descendant::button[@type='submit']")).click();

        var fullName = firstName + " " + lastName;
        WebElement pageTittle = driver.findElement(By.xpath("//div[@class='header-top']/parent::header[@id='header']/descendant::span[contains(text(),'" + fullName + "')]"));
        Assertions.assertEquals("Juan Perez", pageTittle.getText());
    }
}
