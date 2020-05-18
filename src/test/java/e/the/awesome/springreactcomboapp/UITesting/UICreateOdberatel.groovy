package e.the.awesome.springreactcomboapp.UITesting

import geb.Browser
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class UICreateOdberatel {
    @Test
    void createOdberatelTest() {

        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe")


        Browser.drive {
            go 'http://localhost:3000/add-subscriber'
            assert title == "Přihlášení"


            $("input[name='username']").value("devglan")


            driver.findElement(By.name("password")).sendKeys("devglan")

            driver.findElement(By.xpath("//button[*[contains(text(),'Přihlásit')]]")).click()

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.titleIs("Přidání odběratele"));

            driver.findElement(By.name("firma")).sendKeys("Tomáš Vacek")
            driver.findElement(By.name("ic")).sendKeys("12345678")
            driver.findElement(By.name("dic")).sendKeys("CZ12345678")
            driver.findElement(By.name("psc")).sendKeys("39601")
            driver.findElement(By.name("mesto")).sendKeys("Humpolec")
            driver.findElement(By.name("ulice")).sendKeys("Prazska")
            driver.findElement(By.name("cisloPopisne")).sendKeys("5")

            driver.findElement(By.xpath("//button[*[contains(text(),'Vytvořit')]]")).click()

            wait.until(ExpectedConditions.titleIs("Seznam odběratelů"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Tomáš Vacek')]")));

        }
    }
}
