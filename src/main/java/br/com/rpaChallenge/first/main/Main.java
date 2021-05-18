package br.com.rpaChallenge.first.main;



import java.util.List;

import br.com.rpaChallenge.first.controller.ExcelController;
import br.com.rpaChallenge.first.controller.RpaController;
import br.com.rpaChallenge.first.model.Pessoa;



public class Main {

	public static void main(String[] args)  {
		
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		ExcelController excelController = new ExcelController();
		List<Pessoa> pessoas = excelController.criar();
		
		RpaController rpaController = new RpaController();
		
		rpaController.imprimir(pessoas);
		
	}

	
	
	
	
	
	
	
	
}
