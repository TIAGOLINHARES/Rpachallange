package br.com.rpaChallenge.first.main;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import br.com.rpaChallenge.first.controller.RpaChallengeController;

public class Main {

	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		PropertyConfigurator.configure("resources\\log4j.properties");

		log.info("Iniciando Automação");

		RpaChallengeController rpaController = new RpaChallengeController();
		rpaController.initRobot(rpaController);

		log.info("Fim da automação");
	}

}
