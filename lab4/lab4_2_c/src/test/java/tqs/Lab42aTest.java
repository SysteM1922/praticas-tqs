package tqs;

// Generated by Selenium IDE
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

@ExtendWith(SeleniumJupiter.class)
public class Lab42aTest {

    @Test
    public void lab42a(FirefoxDriver driver) {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(780, 688));
        driver.findElement(By.cssSelector("h1")).click();
        assertThat(driver.getTitle(), is("BlazeDemo"));
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
        }
        {
            List<WebElement> elements = driver.findElements(By.name("fromPort"));
            assert (elements.size() > 0);
        }
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'Dublin']")).click();
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector(".btn-primary"));
            assert (elements.size() > 0);
        }
        driver.findElement(By.cssSelector(".btn-primary")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(1) > td:nth-child(7)"));
            assert (elements.size() > 0);
        }
        driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
        {
            WebElement element = driver.findElement(By.id("inputName"));
            Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
            assertTrue(isEditable);
        }
        driver.findElement(By.id("inputName")).sendKeys("Vicente");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("Bué Longe");
        assertFalse(driver.findElement(By.id("rememberMe")).isSelected());
        driver.findElement(By.id("rememberMe")).click();
        assertTrue(driver.findElement(By.id("rememberMe")).isSelected());
        driver.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Thank you for your purchase today!"));
    }
}