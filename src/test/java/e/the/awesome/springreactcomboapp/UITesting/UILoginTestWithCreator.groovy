package e.the.awesome.springreactcomboapp.UITesting

import e.the.awesome.springreactcomboapp.Creator
import e.the.awesome.springreactcomboapp.model.User
import geb.Browser
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class UILoginTestWithCreator {

  @Autowired
  private Creator creator;

  @Test
  void loginTest() {

    System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe")

    creator.saveEntity(new User(age: 23,firstName: "Tomáš"));

    Browser.drive {
      go 'http://localhost:3000/list-user'
      assert title == "Přihlášení"

      // a) typing text into input using GEB jQuery-like API
      $("input[name='username']").value("devglan")

      // a) typing text into input using core WebDriver API
      driver.findElement(By.name("password")).sendKeys("devglan")

      driver.findElement(By.xpath("//button[*[contains(text(),'Přihlásit')]]")).click()

      WebDriverWait wait = new WebDriverWait(driver, 10);
      wait.until(ExpectedConditions.titleIs("Seznam uživatelů"))

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Seznam uživatelů']")))
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Tomáš']")))
    }
  }


}
