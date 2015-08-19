package com.jfgw.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.jfgw.excel.poi.ExcelOperation;
import com.jfgw.model.DSModel;
import com.jfgw.model.ResultModel;
import com.jfgw.xml.GetDataFromXml;

public class DealWith {

	
	
	public static void main(String[] args) {
		List<DSModel> xmlList = new ArrayList<DSModel>();

		List<DSModel> ltDataList = new ArrayList<DSModel>();
		
		GetDataFromXml xmlData = new GetDataFromXml();
		String xmlFilePath = "D:\\lt0813\\lt0813.xml";
		xmlList = xmlData.getDate(xmlFilePath);
		System.out.println("一共" + xmlList.size() + "条记录");
		
		ExcelOperation excelData = new ExcelOperation();
		ltDataList =  excelData.readExcel();
		System.out.println("一共" + ltDataList.size() + "条记录");
		
		
		for (int i = 0; i < xmlList.size(); i++) {
			DSModel model = xmlList.get(i);
			String regex = "^2015/8/13.*";
			if (model.getBegTime().matches(regex)) {
				if (!model.getResult().equals("成功")) {
					boolean flag= false;
					for(int j=0; j<ltDataList.size() ; j++){
						DSModel ltData = ltDataList.get(j);
						if(ltData.getPhoneNum().equals(model.getPhoneNum())){
							flag = true;
							System.out.println(model.getPhoneNum() +"-"+model.getBegTime()+"-"+model.getEndTime()+"-"+model.getResult());
							System.out.println(ltData.getPhoneNum() +"-"+ltData.getBegTime()+"-"+ltData.getEndTime()+"-"+ltData.getLtInputTime());
						}
					}
				}
			}
			
		}
		
	}
	
	public  void main1(String[] args) {
		// TODO Auto-generated method stub
		List<DSModel> xmlList = new ArrayList<DSModel>();

		GetDataFromXml xmlData = new GetDataFromXml();
		String xmlFilePath = "D:\\lt0813\\lt0813.xml";
		xmlList = xmlData.getDate(xmlFilePath);
		System.out.println("一共" + xmlList.size() + "条记录");
		setDsDataToExcel(xmlList);

		
		// List<ResultModel> resultList = new ArrayList<ResultModel>();

		// ResultModel firstResult = new ResultModel();
		// firstResult.setResult("成功");
		// firstResult.setNum(0);
		// resultList.add(firstResult);

		// for(int i=0 ; i<xmlList.size(); i++){
		// DSModel model = xmlList.get(i);
		// boolean flag = false;
		//
		// System.out.println("resultList的个数"+resultList.size());
		//
		//// try {
		//// Thread.sleep(1000);
		//// } catch (InterruptedException e) {
		//// // TODO Auto-generated catch block
		//// e.printStackTrace();
		//// }
		// for(int j=0; j<resultList.size(); j++){
		// //System.out.println("ddd");
		// ResultModel result = resultList.get(j);
		// System.out.println(model.getResult());
		//
		// if(model.getResult().trim().equals(result.getResult().trim())){
		// flag = true;
		// result.setNum(result.getNum()+1);
		// System.out.println("111");
		// break;
		// }else {
		// flag = false;
		// }
		// }
		//
		// if(!flag){
		// ResultModel newResult = new ResultModel();
		// newResult.setResult(model.getResult());
		// newResult.setNum(1);
		// resultList.add(newResult);
		// System.out.println("添加一个新的result");
		// }
		//
		//
		// }
		// for(int k=0; k<resultList.size(); k++){
		// ResultModel finResult = resultList.get(k);
		// System.out.println(finResult.getResult() +"---"+finResult.getNum());
		// }

	}

	public static void setDsDataToExcel(List<DSModel> xmlList) {

		// TODO Auto-generated method stub

		try {
			// Workbook wb = WorkbookFactory.create(new
			// File("D:\\lt0813\\lt0813.xlsx"));
			//
			// Sheet sheet1 = wb.createSheet("new sheet");

			Workbook wb = new HSSFWorkbook();
			// Workbook wb = new XSSFWorkbook();
			CreationHelper createHelper = wb.getCreationHelper();
			Sheet sheet = wb.createSheet("20150813");
			Sheet sheetFail = wb.createSheet("20150813失败");
			Row row = sheet.createRow((short) 0);
			// Create a cell and put a value in it.
			Cell cell = row.createCell(0);
			cell.setCellValue(createHelper.createRichTextString("手机号"));

			// Or do it on one line.
			row.createCell(1).setCellValue(createHelper.createRichTextString("开始时间"));
			row.createCell(2).setCellValue(createHelper.createRichTextString("结束时间"));
			row.createCell(3).setCellValue(createHelper.createRichTextString("结果"));

			Row rowFail = sheetFail.createRow((short) 0);
			// Create a cell and put a value in it.
			Cell cellFail = rowFail.createCell(0);
			cellFail.setCellValue(createHelper.createRichTextString("手机号"));

			// Or do it on one line.
			rowFail.createCell(1).setCellValue(createHelper.createRichTextString("开始时间"));
			rowFail.createCell(2).setCellValue(createHelper.createRichTextString("结束时间"));
			rowFail.createCell(3).setCellValue(createHelper.createRichTextString("结果"));
			int rowNum = 0;
			for (int i = 0; i < xmlList.size(); i++) {
				DSModel model = xmlList.get(i);
				String regex = "^2015/8/13.*";
				if (model.getBegTime().matches(regex)) {

					// System.out.println(j);
					int j = i + 1;
					Row row1 = sheet.createRow(j);
					// Create a cell and put a value in it.
					Cell cell1 = row1.createCell(0);
					System.out.println(model.getPhoneNum());
					cell1.setCellValue(createHelper.createRichTextString(model.getPhoneNum()));

					// Or do it on one line.
					row1.createCell(1).setCellValue(createHelper.createRichTextString(model.getBegTime()));
					row1.createCell(2).setCellValue(createHelper.createRichTextString(model.getEndTime()));
					row1.createCell(3).setCellValue(createHelper.createRichTextString(model.getResult()));

					if (!model.getResult().equals("成功")) {
						Row rowFail1 = sheetFail.createRow(rowNum);
						// Create a cell and put a value in it.
						Cell cellFail1 = rowFail1.createCell(0);
						System.out.println(model.getPhoneNum());
						cellFail1.setCellValue(createHelper.createRichTextString(model.getPhoneNum()));

						// Or do it on one line.
						rowFail1.createCell(1).setCellValue(createHelper.createRichTextString(model.getBegTime()));
						rowFail1.createCell(2).setCellValue(createHelper.createRichTextString(model.getEndTime()));
						rowFail1.createCell(3).setCellValue(createHelper.createRichTextString(model.getResult()));
						rowNum++;
					}
				}
			}
			// Create a row and put some cells in it. Rows are 0 based.

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

}
