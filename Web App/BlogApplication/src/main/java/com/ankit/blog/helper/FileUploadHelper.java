package com.ankit.blog.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR = "D:\\Ankit\\Bloggers\\Web App\\BlogApplication\\src\\main\\resources\\static\\image";

	public boolean uploadFile(MultipartFile multipartFile) {
		boolean file = false;

		try {

//			InputStream inputStream  = multipartFile.getInputStream();
//			byte data[] = new byte[inputStream.available()];
//			inputStream.read();
//			
//			FileOutputStream fileOutputStream = new FileOutputStream(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename());
//			fileOutputStream.write(data);
//			
//			fileOutputStream.flush();
//			fileOutputStream.close();
//			
			Files.copy(multipartFile.getInputStream(),
					Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

			file = true;
		} catch (Exception e) {

		}

		return file;
	}
}
