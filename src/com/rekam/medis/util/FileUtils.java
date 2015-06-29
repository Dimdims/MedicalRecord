package com.rekam.medis.util;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rekam.medis.component.ConfigurationHolder;

/**
 * provides utilities for managing files and extension.
 *
 */
public class FileUtils {
	
	protected static Logger logger = Logger.getLogger(FileUtils.class);
	
	/**
	 * transfers file to destination path
	 * @param file
	 * @param path
	 * @param fileName
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static void commonsMultiPartFileTransfer(CommonsMultipartFile file, String path, String fileName) throws IllegalStateException, IOException{
		
		File directory = new File(path);
		
		if(!directory.exists())
			directory.mkdirs();
		
		file.transferTo(new File (path+"/"+fileName));
	
	}
	
	/**
	 * obtains file name extension.
	 * @param fileName
	 * @return
	 */
	public static String getFileNameExtension(String fileName){
		
		String ext = "";
		
		int mid = fileName.lastIndexOf(".");
		
		ext = fileName.substring(mid + 1, fileName.length());
		
		return ext;
		
	}
	
	/**
	 * deletes a file
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String path, String fileName) {
		
		File file = new File (path + "/" + fileName);
		boolean deleted = false;
		
		if(file.exists() == true) deleted = file.delete();
		else {
			deleted = true;
			logger.debug("file - " + path + "/" + fileName + " is not exist.");
		}
		
		return deleted;
	}
	
	/**
	 * deletes a file
	 * @param file
	 * @return
	 */
	public static boolean deleteFile(File file) {
		
		boolean deleted = false;
		if(file.exists() == true) deleted = file.delete();
		else {
			deleted = true;
			logger.debug("file - " + file.getAbsolutePath() + " is not exist.");
		}
		
		return deleted;
	}
	
	/**
	 * clean directory by deleting the existing files and directories in it.
	 * @param path
	 * @return
	 */
	public static boolean cleanDirectory(String path){
		
		File directory = new File(path);
		if(!directory.exists())
			return true;
		
		File[] files = directory.listFiles();
		
		for(File file : files){
			
			if(file.isDirectory())
				FileUtils.cleanDirectory(file.getAbsolutePath());
			else
				file.delete();
		}
		
		return true;
		
	}
	
}
