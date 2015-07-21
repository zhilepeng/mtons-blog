/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.web.upload.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import mtons.modules.exception.MtonsException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author langhsu
 *
 */
public abstract class AbstractRepo {
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
	
	protected byte[] getNetworkImage(String url) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8"); 
    	GetMethod post = new GetMethod(url);
    	
    	int status = client.executeMethod(post);
    	
    	if (status != HttpStatus.SC_OK) {
    		throw new MtonsException("该地址请求失败");
    	}
    	return post.getResponseBody();
	}
	
}
