import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AutomationQaApplication extends SeleniumHelper {
    @Test
    public void automationApplication() throws InterruptedException {
        driver.get(url);
        Thread.sleep(5000);
        WebElement signIn = driver.findElement(By.className("main__sign-in-link"));
        Thread.sleep(1000);
        signIn.click();
        Thread.sleep(2000);
        // stocam fereastra originala
        String originalWindow = driver.getWindowHandle();
        // verificam daca nu avem o noua fereastra deschisa deja
        assert driver.getWindowHandles().size() == 1;
        // click pe link-ul care deschide o noua fereastra
        WebElement googleAccount = driver.findElement(By.className("S9gUrf-YoZ4jf"));
        googleAccount.click();
        // wait pana ce se deschide cu wait-ul din selenium
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(numberOfWindowsToBe(2));
        // loop untill we find a new window handle
        for (String windowHandle: driver.getWindowHandles()){
            if (!originalWindow.contentEquals(windowHandle)){
                driver.switchTo().window(windowHandle);
                break;
            }
        }
       Thread.sleep(2000);
        WebElement inputEmail = driver.findElement(By.id("identifierId"));
        String email = "0746134404";
        inputEmail.sendKeys(email);
        Thread.sleep(1000);
        WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]//button/span"));
        nextButton.click();
        Thread.sleep(1000);
        WebElement tryAgainButton = driver.findElement(By.xpath("//*[@id=\"next\"]/div/div/a"));
        tryAgainButton.click();
    }
}
