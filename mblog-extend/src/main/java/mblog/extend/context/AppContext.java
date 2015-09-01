/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.extend.context;

/**
 * @author langhsu
 * 
 */
public class AppContext {
	
	/*
	 * 文件存储-根目录
	 */
	String root = "/data/mblog";
	
	/*
	 * 文件存储-原文件目录
	 */
	String origDir = "/store/orig";
	
	/*
	 * 文件存储-压缩目录
	 */
	String thumbsDir = "/store/thumbs";
	
	/*
	 * 文件存储-头像目录
	 */
	String avaDir = "/store/ava";
	
	/*
	 * 文件存储-临时文件目录
	 */
	String tempDir = "/store/temp";

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getOrigDir() {
		return origDir;
	}

	public void setOrigDir(String origDir) {
		this.origDir = origDir;
	}

	public String getThumbsDir() {
		return thumbsDir;
	}

	public void setThumbsDir(String thumbsDir) {
		this.thumbsDir = thumbsDir;
	}

	public String getTempDir() {
		return tempDir;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}

	public String getAvaDir() {
		return avaDir;
	}

	public void setAvaDir(String avaDir) {
		this.avaDir = avaDir;
	}

}
