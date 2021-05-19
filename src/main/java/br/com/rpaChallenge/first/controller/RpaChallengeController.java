package br.com.rpaChallenge.first.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.rpaChallenge.first.model.Pessoa;



public class RpaChallengeController {

	private static Logger log = Logger.getLogger(RpaChallengeController.class);
	
	private By byXpathFirstName = By.xpath("//input[@ng-reflect-name='labelFirstName']");
	private By byXpathLastname = By.xpath("//input[@ng-reflect-name='labelLastName']");
	private By byXpathCompanyName = By.xpath("//input[@ng-reflect-name='labelCompanyName']");
	private By byXpathRoleInCompany = By.xpath("//input[@ng-reflect-name='labelRole']");
	private By byXpathAddress = By.xpath("//input[@ng-reflect-name='labelAddress']");
	private By byXpathEmail = By.xpath("//input[@ng-reflect-name='labelEmail']");
	private By byXpathLabelPhone = By.xpath("//input[@ng-reflect-name='labelPhone']");

	public void initRobot(RpaChallengeController rpaController) {

		ExcelController excelController = new ExcelController();

		List<Pessoa> pessoas = excelController.readInputFile();

		if (!pessoas.isEmpty()) {
			
			log.info("Planilha possui " + pessoas.size() + " Registos");
			rpaController.inputDataInChallange(pessoas);
			excelController.writeOutputFile(pessoas);
		} else {
			
			log.info("Planilha esta vazia");
		}
	}

	public void inputDataInChallange(List<Pessoa> pessoas) {

		WebDriver driver = new ChromeDriver(getChromeOptions());
		driver.get("http://www.rpachallenge.com/");

		driver.findElement(By.xpath("//button[@_ngcontent-c1]")).click();

		for (Pessoa pessoa : pessoas) {
			try {

				if (!pessoa.getLastName().contains("First")) {
					driver.findElement(byXpathFirstName).sendKeys(pessoa.getFirstName());
					driver.findElement(byXpathLastname).sendKeys(pessoa.getLastName());
					driver.findElement(byXpathCompanyName).sendKeys(pessoa.getCompanyName());
					driver.findElement(byXpathRoleInCompany).sendKeys(pessoa.getRoleInCompany());
					driver.findElement(byXpathAddress).sendKeys(pessoa.getAddress());
					driver.findElement(byXpathEmail).sendKeys(pessoa.getEmail());
					driver.findElement(byXpathLabelPhone).sendKeys(pessoa.getPhoneNumber().toString());
				}
				driver.findElement(By.xpath("//input[@_ngcontent-c1]")).click();
				
				
				log.info(pessoa.getFirstName() + " Inserida com Sucesso!");
				pessoa.setRegisterInputSucess(true);
				
			} catch (Exception e) {

				driver.findElement(byXpathFirstName).clear();
				driver.findElement(byXpathLastname).clear();
				driver.findElement(byXpathCompanyName).clear();
				driver.findElement(byXpathRoleInCompany).clear();
				driver.findElement(byXpathAddress).clear();
				driver.findElement(byXpathEmail).clear();
				driver.findElement(byXpathLabelPhone).clear();
				
				log.info("Não foi possivel inserir: " + pessoa.toString() + e.getMessage());
			}
		}

		String mensagemFinal = driver.findElement(By.xpath("//div[@class='message2']")).getText();
		
		log.info(mensagemFinal);

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
