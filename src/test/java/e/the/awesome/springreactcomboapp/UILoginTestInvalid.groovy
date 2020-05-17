package e.the.awesome.springreactcomboapp

import geb.Browser
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.boot.test.context.SpringBootTest

import static org.junit.jupiter.api.Assertions.assertEquals


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class UILoginTestInvalid {

    @Test
    void loginInvalidTest() {

        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe")


        Browser.drive {
            go 'http://localhost:3000/login'
            assert title == "Přihlášení"


            $("input[name='username']").value("devglan")


            driver.findElement(By.name("password")).sendKeys("devgla")

            driver.findElement(By.xpath("//button[*[contains(text(),'Přihlásit')]]")).click()

            WebDriverWait wait = new WebDriverWait(driver, 10);

            assert title == "Přihlášení"
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Bad credentials')]")))

        }
    }


}
