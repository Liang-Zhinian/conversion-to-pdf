package com.dove.convertion;

import java.io.InputStream;
import java.io.OutputStream;

import org.odftoolkit.odfdom.converter.pdf.PdfConverter;
import org.odftoolkit.odfdom.converter.pdf.PdfOptions;
import org.odftoolkit.odfdom.doc.OdfDocument;

public class OdtToPDFConverter extends Converter {

	public OdtToPDFConverter(InputStream inStream, OutputStream outStream, boolean showMessages,
			boolean closeStreamsWhenComplete) {
		super(inStream, outStream, showMessages, closeStreamsWhenComplete);
	}

	@Override
	public void convert() throws Exception {
		loading();

		processing();

		OdfDocument document = null;
		document = OdfDocument.loadDocument(inStream);
		PdfOptions options = PdfOptions.create();
		PdfConverter.getInstance().convert(document, outStream, options);

		finished();

	}

}
