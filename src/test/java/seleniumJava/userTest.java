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

public class userTest {
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
  public void testUsuarioTests() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.cssSelector("form > button.btn.login")).click();
	    driver.findElement(By.id("usr")).clear();
	    driver.findElement(By.id("usr")).sendKeys("mig.alv");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("mig.alv@alu.uclm.es");
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("12345asdf");
	    driver.findElement(By.id("pwd1")).clear();
	    driver.findElement(By.id("pwd1")).sendKeys("12345asdf");
	    driver.findElement(By.name("txtRespuesta")).clear();
	    driver.findElement(By.name("txtRespuesta")).sendKeys("Goku");
	    driver.findElement(By.cssSelector("button.boton.login")).click();
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.id("usr")).clear();
	    driver.findElement(By.id("usr")).sendKeys("mig.alv");
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("12345asdf");
	    driver.findElement(By.cssSelector("button.btn.login")).click();
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
	    driver.findElement(By.xpath("(//button[@type='submit'])[7]")).click();
	    /*try {
	      assertEquals("La publicación está vacía, escribe algo", driver.findElement(By.cssSelector("spam > em")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }*/
	    driver.findElement(By.id("comment")).clear();
	    driver.findElement(By.id("comment")).sendKeys("Probando Test");
	    driver.findElement(By.xpath("(//button[@type='submit'])[7]")).click();
	    driver.findElement(By.cssSelector("div.col-md-1.col-md-offset-9 > button.btn.btn-primary")).click();
	    driver.findElement(By.cssSelector("form > #comment")).clear();
	    driver.findElement(By.cssSelector("form > #comment")).sendKeys("Probando Test Editar");
	    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
	    driver.findElement(By.cssSelector("div.col-md-1 > button.btn.btn-danger")).click();
	    driver.findElement(By.cssSelector("#miModalss > div.modal-dialog > div.modal-content > div.modal-body > form > div.row > div.col-md-1.col-md-offset-8 > button.btn.btn-success")).click();
	    driver.findElement(By.id("comment")).clear();
	    driver.findElement(By.id("comment")).sendKeys("Borrador");
	    driver.findElement(By.xpath("(//button[@type='submit'])[6]")).click();
	    driver.findElement(By.cssSelector("div.col-md-1 > button.btn.btn-danger")).click();
	    driver.findElement(By.cssSelector("#miModalss > div.modal-dialog > div.modal-content > div.modal-body > form > div.row > div.col-md-1.col-md-offset-8 > button.btn.btn-success")).click();
	    driver.findElement(By.xpath("(//button[@type='submit'])[8]")).click();
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    /*try {
	      assertEquals("Para realizar una publicación tendrá que escribir en el cuadro de texto \"Realizar publicación\" su correspondiente publicación y a continuación hacer click sobre el botón Enviar para que dicha publicación sea pública o en Borrador para que sea privada. \n\n Para que se muestren todas las publicaciones tenemos que hacer click en el botón de Mostrar Publicaciones, el cual nos mostrará todas las publicaciones que tengamos en nuestro tablón. Tenemos los botones de Editar y Eliminar que harón las funciones correspondientes de editar la publicación y de eliminarla. \n\n Para cambiar al rol de administrador, dentro del botón de ajustes tiene la opción de Cambiar Rol, la cual le cambiaró el rol a administrador. Dentro de este mismo botón (Ajustes) tiene la opción de borrar su propia cuenta.", driver.findElement(By.cssSelector("div.modal-body")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }*/
	    driver.findElement(By.cssSelector("button.close")).click();
	    assertEquals("Bienvenido - IntraVita", driver.getTitle());
	    Thread.sleep (1000); 
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    Thread.sleep (1000); 
	    driver.findElement(By.cssSelector("button.boton.btn-default")).click();
	    Thread.sleep (1000); 
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    Thread.sleep (1000); 
	    driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();	    
	    Thread.sleep (1000); 
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("1234");
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("1234asdf");
	    driver.findElement(By.id("pwd1")).clear();
	    driver.findElement(By.id("pwd1")).sendKeys("1234asdf");
	    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    driver.findElement(By.xpath("(//button[@type='submit'])[5]")).click();
	    driver.findElement(By.id("usr")).clear();
	    driver.findElement(By.id("usr")).sendKeys("christian.rivera");
	    driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
	    driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
	    driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
	    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
	    driver.findElement(By.id("inputPwd")).clear();
	    driver.findElement(By.id("inputPwd")).sendKeys("venga");
	    driver.findElement(By.id("inputRes")).clear();
	    driver.findElement(By.id("inputRes")).sendKeys("Goku");
	    driver.findElement(By.id("borrarCuenta")).click();
	    driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
	    //assertEquals("Recuperar Credenciales", driver.getTitle());
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
