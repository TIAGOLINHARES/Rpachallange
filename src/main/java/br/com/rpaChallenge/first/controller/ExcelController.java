package br.com.rpaChallenge.first.controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.rpaChallenge.first.model.Pessoa;

public class ExcelController {

	public List<Pessoa> criar() {

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
			System.out.println(e.getMessage());

		}

		return pessoas;

	}

}
