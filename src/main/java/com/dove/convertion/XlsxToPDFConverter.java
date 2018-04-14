package com.dove.convertion;

import java.io.*;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class XlsxToPDFConverter extends Converter {

	public XlsxToPDFConverter(InputStream inStream, OutputStream outStream, boolean showMessages,
			boolean closeStreamsWhenComplete) {
		super(inStream, outStream, showMessages, closeStreamsWhenComplete);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert() throws Exception {
		loading();

		processing();
		
		InputStream iStream = inStream;

		// Read workbook into HSSFWorkbook
        @SuppressWarnings("resource")
		XSSFWorkbook my_xls_workbook = new XSSFWorkbook(iStream);
        // Read worksheet into HSSFSheet
        XSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
        // To iterate over the rows
        Iterator<Row> rowIterator = my_worksheet.iterator();
        // We will create output PDF document objects at this point
        Document pdf = new Document();
        PdfWriter.getInstance(pdf, outStream);
        pdf.open();
        // we have two columns in the Excel sheet, so we create a PDF table
        // with two columns
        // Note: There are ways to make this dynamic in nature, if you want
        // to.
        PdfPTable my_table = new PdfPTable(2);
        // We will use the object below to dynamically add new data to the
        // table
        // Loop through rows.
        printPdf(rowIterator, my_table);
        // Finally add the table to PDF document
        pdf.add(my_table);
        pdf.close();
        // we created our pdf file..

		finished();
	}
	
	protected void printPdf(Iterator<Row> rowIterator, PdfPTable my_table) {
        PdfPCell table_cell;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next(); // Fetch CELL
                switch (cell.getCellType()) { // Identify CELL type
                // you need to add more code here based on
                // your requirement / transformations
                case Cell.CELL_TYPE_STRING:
                    // Push the data from Excel to PDF Cell
                    table_cell = new PdfPCell(new Phrase(cell.getStringCellValue()));
                    // feel free to move the code below to suit to your needs
                    my_table.addCell(table_cell);
                    break;
                }
                // next line
            }

        }
    }

}
