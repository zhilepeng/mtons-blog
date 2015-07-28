/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.extend.analysis.videos;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import mblog.extend.data.Video;
import mblog.utils.URLUtils;

/**
 * 视频分析
 * 
 * @author langhsu
 *
 */
public class VideoAnalysis {
	private Map<String, VideoStrategy> strategies = new HashMap<String, VideoStrategy>();

	public void setStrategies(Map<String, VideoStrategy> strategies) {
		this.strategies = strategies;
	}

	public void addStrategy(String host, VideoStrategy strategy) {
		strategies.put(host, strategy);
	}
	
	public Video take(String url) throws MalformedURLException {
		String host = URLUtils.getHost(url);
		
		VideoStrategy strategy = strategies.get(host);
		
		Assert.notNull(strategy, "地址格式不对, 或不支持该网站" + host);
		
		return strategy.take(url);
	}
	
}
