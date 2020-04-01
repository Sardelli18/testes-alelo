package testes;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Teste1 {

	WebDriver driver;

	@Before
	public void getDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver();
	}

	@Test
	public void test() {
		driver.get("https://www.google.com.br/");
		WebElement barraPesquisa = driver.findElement(By.name("q"));
		barraPesquisa.sendKeys("Filmes");
		barraPesquisa.sendKeys(Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, 25);
		List<WebElement> cartazes = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@jscontroller='PD38']")));
		List<String> titulos = new ArrayList<String>();
		for (WebElement webElement : cartazes) {
			titulos.add(webElement.getAttribute("aria-label"));
		}
		for (String titulo : titulos) {
			WebElement campoPesquisa = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Pesquisar']")));
			campoPesquisa.clear();
			campoPesquisa.sendKeys(titulo);
			campoPesquisa.sendKeys(Keys.ENTER);
			String dataLancamento = "", direcao = "";
			try {
				direcao = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//a[contains(text(),'Direção')]/../following-sibling::span"))).getText();
				dataLancamento = wait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//a[contains(text(),'Data de lançamento')]/../following-sibling::span")))
						.getText();
			} catch (NoSuchElementException e) {
				// TODO: handle exception
			}
			System.out.println(
					"Filme: " + titulo + "\nData de Lançamento: " + dataLancamento + "\nDireção: " + direcao + "\n");
		}
	}

	@After
	public void closeDriver() {
		driver.quit();
	}

}