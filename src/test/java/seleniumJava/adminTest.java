package seleniumJava;
import org.junit.Test;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class adminTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "https://intravitawebapp.herokuapp.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAdminTests() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("usr")).clear();
    driver.findElement(By.id("usr")).sendKeys("jose.maria");
    driver.findElement(By.id("pwd")).clear();
    driver.findElement(By.id("pwd")).sendKeys("1234qwer");
    driver.findElement(By.cssSelector("button.btn.login")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    driver.findElement(By.cssSelector("button.boton.btn-success")).click();
    //assertEquals("jose.maria", driver.findElement(By.cssSelector("em > strong")).getText());
    driver.findElement(By.xpath("(//button[@type='submit'])[8]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    driver.findElement(By.cssSelector("button.boton.btn-default")).click();
    assertEquals("Panel administrador", driver.getTitle());
    //assertEquals("Usuarios", driver.findElement(By.cssSelector("strong > em")).getText());
    //assertEquals("Administradores", driver.findElement(By.xpath("//div[2]/div/div/div/h3/strong/em")).getText());
    driver.findElement(By.xpath("(//button[@type='submit'])[5]")).click();
    driver.findElement(By.xpath("(//button[@type='submit'])[43]")).click();
    driver.findElement(By.xpath("(//button[@type='submit'])[30]")).click();
    driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
    driver.findElement(By.cssSelector("div.col-md-1.col-md-offset-1 > form > button.btn.btn-danger")).click();
    driver.findElement(By.xpath("(//button[@type='submit'])[10]")).click();
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    driver.findElement(By.cssSelector("button.btn.btn-danger")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}