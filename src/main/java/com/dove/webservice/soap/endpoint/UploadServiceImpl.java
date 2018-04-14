package com.dove.webservice.soap.endpoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataHandler;

import com.dove.convertion.FileUploader;
import com.dove.webservice.soap.UploadSEI;

public class UploadServiceImpl implements UploadSEI {

	public void uploadFile(FileUploader Dfile) {

		DataHandler handler = Dfile.getDfile();
		try {
			InputStream is = handler.getInputStream();

			OutputStream os = new FileOutputStream(
					new File("C:/Android Projects/uploads/" + Dfile.getName() + "." + Dfile.getFileType()));
			byte[] b = new byte[100000];
			int bytesRead = 0;
			while ((bytesRead = is.read(b)) != -1) {
				os.write(b, 0, bytesRead);
			}
			os.flush();
			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
