package com.dove.convertion;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {
	public static OutputStream toPdf(String contentType, InputStream inStream) throws Exception {
		Converter converter;
		boolean shouldShowMessages = true;

		String lowerCaseInPath = contentType.toLowerCase();

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

		converter.convert();

		return outStream;
	}
}
