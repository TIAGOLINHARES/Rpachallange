package br.com.rpaChallenge.first.main;



import java.io.IOException;
import java.util.List;

import br.com.rpaChallenge.first.controller.ExcelController;
import br.com.rpaChallenge.first.controller.RpaController;
import br.com.rpaChallenge.first.model.Pessoa;



public class Main {

	public static void main(String[] args) throws  IOException {
		
		
		ExcelController excelController = new ExcelController();
		List<Pessoa> pessoas = excelController.criar();
		
		//RpaController rpaController = new RpaController();
		
		RpaController.imprimir(pessoas);
		
	}

	
	
	
	
	
	
	
	
}
