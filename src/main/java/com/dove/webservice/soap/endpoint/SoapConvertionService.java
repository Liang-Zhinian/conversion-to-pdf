package com.dove.webservice.soap.endpoint;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;

import org.apache.cxf.jaxrs.ext.multipart.InputStreamDataSource;

//import com.dove.convertion.Converter;
//import com.dove.convertion.DocToPDFConverter;
//import com.dove.convertion.DocxToPDFConverter;
import com.dove.convertion.FileUploader;
//import com.dove.convertion.OdtToPDFConverter;
//import com.dove.convertion.PptToPDFConverter;
//import com.dove.convertion.PptxToPDFConverter;
import com.dove.convertion.Util;
//import com.dove.convertion.XlsToPDFConverter;
//import com.dove.convertion.XlsxToPDFConverter;
import com.dove.webservice.soap.ConvertionService;

public class SoapConvertionService implements ConvertionService {

	public DataHandler convert(FileUploader Dfile) throws Exception {
		DataHandler handler = Dfile.getDfile();
		//Converter converter;
		//boolean shouldShowMessages = true;

		try {
			String lowerCaseInPath = Dfile.getFileType();
			InputStream inStream = handler.getInputStream();
			OutputStream outStream = Util.toPdf(lowerCaseInPath, inStream);
			/*
			OutputStream outStream = new ByteArrayOutputStream();

			if (lowerCaseInPath.endsWith("doc")) {
				converter = new DocToPDFConverter(inStream, outStream, shouldShowMessages, true);
			} else if (lowerCaseInPath.endsWith("docx")) {
				converter = new DocxToPDFConverter(inStream, outStream, shouldShowMessages, true);
			} else if (lowerCaseInPath.endsWith("ppt")) {
				converter = new PptToPDFConverter(inStream, outStream, shouldShowMessages, true);
			} else if (lowerCaseInPath.endsWith("pptx")) {
				converter = new PptxToPDFConverter(inStream, outStream, shouldShowMessages, true);
			} else if (lowerCaseInPath.endsWith("xls")) {
				converter = new XlsToPDFConverter(inStream, outStream, shouldShowMessages, true);
			} else if (lowerCaseInPath.endsWith("xlsx")) {
				converter = new XlsxToPDFConverter(inStream, outStream, shouldShowMessages, true);
			} else if (lowerCaseInPath.endsWith("odt")) {
				converter = new OdtToPDFConverter(inStream, outStream, shouldShowMessages, true);
			} else {
				converter = null;
			}

			converter.convert();*/

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos = (ByteArrayOutputStream) outStream;
			ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());

			DataHandler outputHandler = new DataHandler(new InputStreamDataSource(swapStream, "application/pdf"));

			FileUploader outFile = new FileUploader();
			outFile.setName(Dfile.getName());
			outFile.setFileType("pdf");
			outFile.setDfile(outputHandler);

			inStream.close();
//			return outFile;
			return outputHandler;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
