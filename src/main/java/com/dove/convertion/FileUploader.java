package com.dove.convertion;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

public class FileUploader {
	private String Name;
	private String FileType;
	private DataHandler Dfile;
	
	public FileUploader(){}
	public FileUploader(String name, String fileType, String filePath){
		this.Name=name;
		this.FileType=fileType;
		
		DataSource source = new FileDataSource(new File(filePath));
		this.Dfile = new DataHandler(source);
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public DataHandler getDfile() {
		return this.Dfile;
	}

	public void setDfile(DataHandler Dfile) {
		this.Dfile = Dfile;
	}

	public String getFileType() {
		return FileType;
	}

	public void setFileType(String FileType) {
		this.FileType = FileType;
	}
}
