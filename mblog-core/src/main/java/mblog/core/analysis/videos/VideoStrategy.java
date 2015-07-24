/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.core.analysis.videos;

import mblog.core.data.Video;

/**
 * @author langhsu
 *
 */
public interface VideoStrategy {
	
	/**
	 * 获取视频信息
	 * 
	 * @param url 视频地址
	 * @return
	 */
	Video take(String url);
}
