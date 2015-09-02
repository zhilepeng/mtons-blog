/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.extend.upload.impl;

import mtons.modules.exception.MtonsException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author langhsu
 *
 */
public abstract class AbstractFileRepo {
	// 文件允许格式
	protected String[] allowFiles = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	
	protected void validateFile(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new MtonsException("文件不能为空");
		}
		
		if (!checkFileType(file.getOriginalFilename())) {
			throw new MtonsException("文件格式不支持");
    	}
	}
	
	/**
	 * 文件类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	protected boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}
	
	protected String getExt(String filename) {
		int pos = filename.lastIndexOf(".");
		return filename.substring(pos + 1);
	}
	
	protected void checkDirAndCreate(File file) {
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
	}
	
}
