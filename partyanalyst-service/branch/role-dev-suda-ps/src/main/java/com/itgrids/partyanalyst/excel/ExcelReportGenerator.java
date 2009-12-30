package com.itgrids.partyanalyst.excel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

 

public class ExcelReportGenerator {
	private static String str[]={}; 
	private Properties reportProperties;
	private List<ReportRowDataMapper> reportContent;
	public void write() throws IOException, WriteException {
		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("en", "EN"));
		
		WritableWorkbook workbook = Workbook.createWorkbook(new File(reportProperties.getProperty("reportLocation")), wbSettings);
		workbook.createSheet(reportProperties.getProperty("sheetName"), 0);
		WritableSheet excelSheet = workbook.getSheet(0);
		createHeaders(excelSheet);
		createContent(excelSheet,getReportContent());

		workbook.write();
		workbook.close();
	}

	private void createHeaders(WritableSheet sheet)
			throws WriteException {
/*		CellView cv = new CellView();
		cv.setFormat(initializeWritableCellFormat());
		cv.setFormat(initializeWritableWritableCelFormat());
		cv.setAutosize(true);*/
		// Write a few headers
		addHeader(sheet, 0, 0, reportProperties.getProperty("header1"));
		addHeader(sheet, 1, 0, reportProperties.getProperty("header2"));
		addHeader(sheet, 2, 0, reportProperties.getProperty("header3"));
		addHeader(sheet, 3, 0, reportProperties.getProperty("header4"));
		

	}

	private void createContent(WritableSheet sheet,List<ReportRowDataMapper> reportContent) throws WriteException,
			RowsExceededException {
		// Now a bit of text
		
		for (ReportRowDataMapper reportRowDataMapper : reportContent) {
			
		}
		for (int i = 1; i < 20; i++) {
			// First column
			addLabel(sheet, 0, i, "Boring text " + i);
			// Second column
			addLabel(sheet, 1, i, "Another text");
		}
	}

	private void addHeader(WritableSheet sheet, int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, initializeWritableWritableCelFormat());
		sheet.addCell(label);
	}

	private void addLabel(WritableSheet sheet, int column, int row, String s)
			throws WriteException, RowsExceededException {
		Label label;
		label = new Label(column, row, s, initializeWritableCellFormat());
		sheet.addCell(label);
	}

	public static void main(String[] args) throws WriteException, IOException {
		ExcelReportGenerator test = new ExcelReportGenerator();
		test.write();
		System.out.println("Please check the result file under c:/temp/lars.xls ");
	}

	
	// Lets create a times font
	private WritableCellFormat initializeWritableCellFormat() throws WriteException{
		WritableFont times12pt = new WritableFont(WritableFont.TIMES, 12);
		// Define the cell format
		WritableCellFormat times = new WritableCellFormat(times12pt);
		// Lets automatically wrap the cells
		times.setWrap(true);
		
		return times;
	}
	
	// Create create a bold font
	private WritableCellFormat initializeWritableWritableCelFormat() throws WriteException{
		WritableFont times10ptBoldUnderline = new WritableFont(
				WritableFont.TIMES, 12, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		// Lets automatically wrap the cells
		timesBoldUnderline.setWrap(false);
		
		return timesBoldUnderline;
	}

	public Properties getReportProperties() {
		return reportProperties;
	}

	public void setReportProperties(Properties reportProperties) {
		this.reportProperties = reportProperties;
	}

	public List<ReportRowDataMapper> getReportContent() {
		return reportContent;
	}

	public void setReportContent(List<ReportRowDataMapper> reportContent) {
		this.reportContent = reportContent;
	}
}
