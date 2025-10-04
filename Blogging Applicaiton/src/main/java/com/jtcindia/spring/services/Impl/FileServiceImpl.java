package com.jtcindia.spring.services.Impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import com.jtcindia.spring.services.FileService;

//public class FileServiceImpl implements FileService {
//
//	
//	
//	@Override
//	public String uploadImage(String path, MultipartFile file) throws IOException{
//	
//		//File name
//		String name = file.getOriginalFilename();
//		
//		//Full path
//		String filepath = path+ File.separator+name;
//		
//		
//		//Create folder if not created
//		File f=new File(path);
//		if(!f.exists()) {
//			f.mkdir();	
//		}
//		
//		
//		//File copy
//		Files.copy(file.getInputStream(), Paths.get(filepath));
//		
//		return name ;
//	}
//
//	@Override
//	public InputStream getResource(String path, String fileName) {
//	
//		return null;
//	}
//
//}
