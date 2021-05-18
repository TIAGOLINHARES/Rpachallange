package br.com.rpaChallenge.first.controller;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.rpaChallenge.first.model.Pessoa;

public class RpaController {

	public static void imprimir(List<Pessoa> pessoas) {

		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(getChromeOptions());
		driver.get("http://www.rpachallenge.com/");

		driver.findElement(By.xpath("//button[@_ngcontent-c1]")).click();

		for (int i = 0; i < 10; i++) {

			// first name
			driver.findElement(By.xpath("//input[@ng-reflect-name=\"labelFirstName\"]"))
					.sendKeys(pessoas.get(i).getFirstName());
			driver.findElement(By.xpath("//input[@ng-reflect-name=\"labelLastName\"]"))
					.sendKeys(pessoas.get(i).getLastName());
			driver.findElement(By.xpath("//input[@ng-reflect-name=\"labelCompanyName\"]"))
					.sendKeys(pessoas.get(i).getCompanyName());
			driver.findElement(By.xpath("//input[@ng-reflect-name=\"labelRole\"]"))
					.sendKeys(pessoas.get(i).getRoleInCompany());
			driver.findElement(By.xpath("//input[@ng-reflect-name='labelAddress']"))
					.sendKeys(pessoas.get(i).getAddress());
			driver.findElement(By.xpath("//input[@ng-reflect-name=\"labelEmail\"]"))
					.sendKeys(pessoas.get(i).getEmail());
			driver.findElement(By.xpath("//input[@ng-reflect-name=\"labelPhone\"]"))
					.sendKeys(pessoas.get(i).getPhoneNumber().toString());

			driver.findElement(By.xpath("//input[@_ngcontent-c1]")).click();

		}

		String mensagemFinal = driver.findElement(By.xpath("//div[@class='message2']")).getText();
		System.out.println(mensagemFinal);

		driver.close();
		driver.quit();
	}

	private static ChromeOptions getChromeOptions() {

		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePreferences = new HashMap<String, Object>();

		// 0 = default 1 = Allow 2 = Block
		// precisa adicionar um option para funcionar

		chromePreferences.put("profile.managed_default_content_settings.images", 2);

		options.addArguments("--start-maximized");
		// options.addArguments("--headless");
		options.setExperimentalOption("prefs", chromePreferences);

		return options;

	}

}
