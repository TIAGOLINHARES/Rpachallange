package br.com.rpaChallenge.first.controller;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.rpaChallenge.first.model.Pessoa;
import lombok.Cleanup;
import lombok.Builder;


public class ExcelController {

	public List<Pessoa> criar() throws IOException {

		List<Pessoa> pessoas = new ArrayList<>();
		
		String caminhoArquivo = "./arquivosExcel/challenge.xlsx";
		@Cleanup
		FileInputStream file = new FileInputStream(caminhoArquivo);
		Workbook workbook = new XSSFWorkbook(file);

		Sheet sheet = workbook.getSheetAt(0);
		// setando as linhas
		List<Row> rows = (List<Row>) toList(sheet.iterator());
		
		// remove cabeçalho
		rows.remove(0);
		
		
		for (Row row : rows) {
			List<Cell> cells = (List<Cell>) toList(row.cellIterator());
			
			
			Pessoa pessoa = new Pessoa();
			
			pessoa.setFirstName(cells.get(0).getStringCellValue());
			pessoa.setLastName(cells.get(1).getStringCellValue());
			pessoa.setCompanyName(cells.get(2).getStringCellValue());
			pessoa.setRoleInCompany(cells.get(3).getStringCellValue());
			pessoa.setAddress(cells.get(4).getStringCellValue());
			pessoa.setEmail(cells.get(5).getStringCellValue());
			pessoa.setPhoneNumber((long) cells.get(6).getNumericCellValue());
			
			pessoas.add(pessoa);
		};

		return pessoas;

	}

	public List<?> toList(Iterator<?> iterator) {

		return IteratorUtils.toList(iterator);

	}
	
	
		
	}

