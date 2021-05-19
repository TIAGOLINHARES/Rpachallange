package br.com.rpaChallenge.first.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.rpaChallenge.first.model.Pessoa;

public class ExcelController {
	
	private static Logger log = Logger.getLogger(ExcelController.class);
	
	public List<Pessoa> readInputFile() {
		
		
		List<Pessoa> pessoas = new ArrayList<>();
		

		String caminhoArquivo = "./arquivosExcel/challenge.xlsx";

		try {
			FileInputStream file = new FileInputStream(caminhoArquivo);
			Workbook workbook = new XSSFWorkbook(file);

			Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Pessoa pessoa = new Pessoa();
				
				String firstCell = row.getCell(0).getStringCellValue();
				
				if (!firstCell.equals("") && !firstCell.contains("First")) {
				
					pessoa.setFirstName(row.getCell(0).getStringCellValue());
					pessoa.setLastName(row.getCell(1).getStringCellValue());
					pessoa.setCompanyName(row.getCell(2).getStringCellValue());
					pessoa.setRoleInCompany(row.getCell(3).getStringCellValue());
					pessoa.setAddress(row.getCell(4).getStringCellValue());
					pessoa.setEmail(row.getCell(5).getStringCellValue());
					pessoa.setPhoneNumber((long) row.getCell(6).getNumericCellValue());
					

					pessoas.add(pessoa);
				}
			}
			workbook.close();

		} catch (

		Exception e) {
			
			log.info(e.getMessage());

		}

		return pessoas;

	}

	public void writeOutputFile(List<Pessoa> pessoas ) {

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Pessoas");

			int indexRow = 0;
			
			
			
			for (Pessoa pessoa : pessoas) {

				Row row = sheet.createRow(indexRow++);

				row.createCell(0).setCellValue(pessoa.getFirstName());
				row.createCell(1).setCellValue(pessoa.getLastName());
				row.createCell(2).setCellValue(pessoa.getCompanyName());
				row.createCell(3).setCellValue(pessoa.getRoleInCompany());
				row.createCell(4).setCellValue(pessoa.getAddress());
				row.createCell(5).setCellValue(pessoa.getEmail());
				row.createCell(6).setCellValue(pessoa.getPhoneNumber());
				row.createCell(7).setCellValue(pessoa.getRegisterInputSucess());

			}

			String diretorioArquivo = "C:\\Users\\Tiago Linhares\\Documents\\xls\\rpaChallangeFirst.xlsx";
			FileOutputStream fileOutput = new FileOutputStream(diretorioArquivo);
			workbook.write(fileOutput);
			workbook.close();
			
			log.info("Arquivo exportado com sucesso");
		} catch (Exception e) {
			
			log.info("Erro ao exportar o Arquivo");
		}
		
	}

}
