/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.extend.analysis.videos.support;

import java.util.HashMap;
import java.util.Map;

import mtons.modules.exception.MtonsException;

import com.alibaba.fastjson.JSON;

import mblog.extend.analysis.videos.VideoStrategy;
import mblog.extend.data.Video;
import mblog.utils.HttpUtils;

/**
 * 优酷视频信息抓取
 * 
 * @author langhsu
 *
 */
public class Youku implements VideoStrategy {
	private String source = "优酷";
	private String api = "https://openapi.youku.com/v2/videos/show_basic.json";
	private String clientId = "7c068d0cb01cb88c";
	
	@Override
	public Video take(String url) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("client_id", clientId);
		params.put("video_url", url);
		
		Video ret = null;
		try {
			String body = HttpUtils.post(api, params);
			System.out.println(body);
			ret = JSON.parseObject(body, Video.class);
			
			ret.setSource(source);
			ret.setBody(getHtmlBody(ret));
		} catch (Exception e) {
			throw new MtonsException("该地址请求失败");
		}
		return ret;
	}
	
	private String getHtmlBody(Video video) {
		/*
		<div id="youkuplayer" style="width:480px;height:400px"></div>
        <script type="text/javascript" src="http://player.youku.com/jsapi">
        player = new YKU.Player('youkuplayer',{
        styleid: '0',
        client_id: '7c068d0cb01cb88c',
        vid: 'XMTI4MTIzMDQ1Ng=='
        });
        </script>
		 */
		
		StringBuffer buf = new StringBuffer();
		buf.append("<div id='youkuplayer' class='player'></div>");
		buf.append("<script type=\"text/javascript\" src=\"http://player.youku.com/jsapi\">");
		buf.append("player = new YKU.Player('youkuplayer',{");
		buf.append("styleid: '0',");
		buf.append("client_id: '").append(clientId).append("',");
		buf.append("vid: '").append(video.getId()).append("' }); </script>");
		return buf.toString();
	}
}
