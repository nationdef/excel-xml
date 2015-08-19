package com.jfgw.excel.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jfgw.model.DSModel;

public class ExcelOperation {

	public static void mainWrite(String[] args) {
		// TODO Auto-generated method stub

		try {
			// Workbook wb = WorkbookFactory.create(new
			// File("D:\\lt0813\\lt0813.xlsx"));
			//
			// Sheet sheet1 = wb.createSheet("new sheet");

			Workbook wb = new HSSFWorkbook();
			// Workbook wb = new XSSFWorkbook();
			CreationHelper createHelper = wb.getCreationHelper();
			Sheet sheet = wb.createSheet("new sheet");

			// Create a row and put some cells in it. Rows are 0 based.
			Row row = sheet.createRow((short) 0);
			// Create a cell and put a value in it.
			Cell cell = row.createCell(0);
			cell.setCellValue(1);

			// Or do it on one line.
			row.createCell(1).setCellValue(1.2);
			row.createCell(2).setCellValue(createHelper.createRichTextString("This is a string"));
			row.createCell(3).setCellValue(true);

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream("D:\\lt0813\\lt0813.xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void mainRead(String[] args) {
		try {
			Workbook wb = WorkbookFactory.create(new File("D:\\lt0813\\20150813-lt.xls"));
			Sheet sheet1 = wb.getSheetAt(0);
			for (Row row : sheet1) {
				for (Cell cell : row) {
					System.out.print(cell.getRichStringCellValue().getString());
					CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
					System.out.print(cellRef.formatAsString());
					System.out.print(" - ");

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						System.out.println(cell.getRichStringCellValue().getString());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							System.out.println(cell.getDateCellValue());
						} else {
							System.out.println(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println(cell.getCellFormula());
						break;
					default:
						System.out.println();
					}
				}

				System.out.println("");
			}

		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Workbook wb = WorkbookFactory.create(new File("D:\\lt0813\\20150813-lt.xls"));
			Sheet sheet1 = wb.getSheetAt(0);
			for (Row row : sheet1) {
				for (Cell cell : row) {

					System.out.print(cell.getColumnIndex() + "|");
					System.out.print(cell.getRichStringCellValue().getString());
					// CellReference cellRef = new
					// CellReference(row.getRowNum(), cell.getColumnIndex());
					// System.out.print(cellRef.formatAsString());
					System.out.print(" - ");

				}

				System.out.println("");
			}

		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<DSModel> readExcel() {
		List<DSModel> resList = new ArrayList<DSModel>();

		try {
			Workbook wb = WorkbookFactory.create(new File("D:\\lt0813\\20150813-lt.xls"));
			Sheet sheet1 = wb.getSheetAt(0);
			for (Row row : sheet1) {
				DSModel dsModel = new DSModel();
				for (Cell cell : row) {
					switch (cell.getColumnIndex()) {
					case 0:
						dsModel.setPhoneNum(cell.getRichStringCellValue().getString());
						break;
					case 1:
						break;
					case 2:
						dsModel.setBegTime(cell.getRichStringCellValue().getString());
						break;
					case 3:
						dsModel.setEndTime(cell.getRichStringCellValue().getString());
						break;
					case 4:
						dsModel.setLtInputTime(cell.getRichStringCellValue().getString());
						break;
					default:
						break;
					}
//					System.out.print(cell.getColumnIndex() + "|");
//					System.out.print(cell.getRichStringCellValue().getString());
//					// CellReference cellRef = new
//					// CellReference(row.getRowNum(), cell.getColumnIndex());
//					// System.out.print(cellRef.formatAsString());
//					System.out.print(" - ");

				}

//				System.out.println("");
				resList.add(dsModel);
			}

		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resList;
	}

}
