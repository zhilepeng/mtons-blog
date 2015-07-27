/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.utils;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.util.HtmlUtils;

/**
 * 截取文章摘要
 * 
 * @author langhsu
 *
 */
public class PreviewTextUtils {

	public static String truncateText(String html, int length){
		Document doc = Jsoup.parse(html);
		String text = doc.text();
		text = StringUtils.abbreviate(text, length);
		return HtmlUtils.htmlEscape(text);
	}
}
