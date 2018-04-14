package com.dove.webservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;

import com.dove.convertion.FileUploader;
import com.dove.webservice.soap.client.SoapConvertionClient;

public class SoapWorkbench {
	final static String BASE = "http://localhost:8082/webservice/services";

	public static void main(String[] args) throws Exception {

		try {
			convertionStuff();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			
		}
		
		System.exit(0);
	}

	private static void convertionStuff() throws Exception {
		fromPpt();
		fromPptx();
		fromXls();
		fromXlsx();
		fromDocx();
		fromDoc();
	}

	private static void fromPpt() throws Exception {
		SoapConvertionClient converter = new SoapConvertionClient(BASE + "/Convert");
		FileUploader file = new FileUploader("Partha", "ppt", "C:/Android Projects/cxf.ppt");

		DataHandler outFile = converter.convert(file);
		String result = save("ppt", "pdf", outFile);

		System.out.println(result);

		outFile.getInputStream().close();
	}

	private static void fromPptx() throws Exception {
		SoapConvertionClient converter = new SoapConvertionClient(BASE + "/Convert");
		FileUploader file = new FileUploader("Partha", "pptx", "C:/Android Projects/cxf.pptx");

		DataHandler outFile = converter.convert(file);
		String result = save("pptx", "pdf", outFile);

		System.out.println(result);

		outFile.getInputStream().close();
	}

	private static void fromDoc() throws Exception {
		SoapConvertionClient converter = new SoapConvertionClient(BASE + "/Convert");
		FileUploader file = new FileUploader("Partha", "doc", "C:/Android Projects/cxf.doc");

		DataHandler outFile = converter.convert(file);
		String result = save("doc", "pdf", outFile);

		System.out.println(result);

		outFile.getInputStream().close();
	}

	private static void fromDocx() throws Exception {
		SoapConvertionClient converter = new SoapConvertionClient(BASE + "/Convert");
		FileUploader file = new FileUploader("Partha", "docx", "C:/Android Projects/cxf.docx");

		DataHandler outFile = converter.convert(file);
		String result = save("docx", "pdf", outFile);

		System.out.println(result);

		outFile.getInputStream().close();
	}

	private static void fromXls() throws Exception {
		SoapConvertionClient converter = new SoapConvertionClient(BASE + "/Convert");
		FileUploader file = new FileUploader("Partha", "xls", "C:/Android Projects/cxf.xls");

		DataHandler outFile = converter.convert(file);
		String result = save("xls", "pdf", outFile);

		System.out.println(result);

		outFile.getInputStream().close();
	}

	private static void fromXlsx() throws Exception {
		SoapConvertionClient converter = new SoapConvertionClient(BASE + "/Convert");
		FileUploader file = new FileUploader("Partha", "Xlsx", "C:/Android Projects/cxf.Xlsx");

		DataHandler outFile = converter.convert(file);
		String result = save("Xlsx", "pdf", outFile);

		System.out.println(result);

		outFile.getInputStream().close();
	}

	private static String save(String fileName, String contentType, DataHandler dfile) {
		try {
			InputStream is = dfile.getInputStream();

			OutputStream os = new FileOutputStream(
					new File("C:/Android Projects/uploads/" + fileName + "." + contentType));
			byte[] b = new byte[100000];
			int bytesRead = 0;
			while ((bytesRead = is.read(b)) != -1) {
				os.write(b, 0, bytesRead);
			}
			os.flush();
			os.close();
			is.close();

			return "Saved.";
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
