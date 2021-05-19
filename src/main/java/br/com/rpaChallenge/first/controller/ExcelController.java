package br.com.rpaChallenge.first.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
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

				Cell cell = row.createCell(0);
				cell.setCellValue(pessoa.getFirstName());
				cell = row.createCell(1);
				cell.setCellValue(pessoa.getLastName());
				cell = row.createCell(2);
				cell.setCellValue(pessoa.getCompanyName());
				cell = row.createCell(3);
				cell.setCellValue(pessoa.getRoleInCompany());
				cell = row.createCell(4);
				cell.setCellValue(pessoa.getAddress());
				cell = row.createCell(5);
				cell.setCellValue(pessoa.getEmail());
				cell = row.createCell(6);
				cell.setCellValue(pessoa.getPhoneNumber());

			}

			String diretorioArquivo = "C:\\\\Users\\\\Tiago Linhares\\\\Documents\\\\xls\\\\rpaChallangeFirst.xlsx";
			FileOutputStream fileOutput = new FileOutputStream(diretorioArquivo);
			workbook.write(fileOutput);
			workbook.close();
			
			log.info("Arquivo exportado com sucesso");
		} catch (Exception e) {
			
			log.info("Erro ao exportar o Arquivo");
		}
		
	}

}
