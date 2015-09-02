/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.utils;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.util.HtmlUtils;
import org.jsoup.safety.Whitelist;

/**
 * 截取文章摘要
 * 
 * @author langhsu
 *
 */
public class PreviewTextUtils {

	public static String truncateText(String html, int length){
		String text = getText(html);
		text = StringUtils.abbreviate(text, length);
		return HtmlUtils.htmlEscape(text);
	}

	/**
	 * 提取纯文本
	 * @param html
	 * @return
	 */
	public static String getText(String html) {
		if (html == null)
			return null;
		return Jsoup.clean(html, Whitelist.none());
	}

	/**
	 * 以下标签可以通过 (b, em, i, strong, u. 纯文本)
	 * @param html
	 * @return
	 */
	public static String getSimpleHtml(String html) {
		if (html == null)
			return null;
		return Jsoup.clean(html, Whitelist.simpleText());
	}

	/**
	 * 获取文章中的img url
	 * @param html
	 * @return
	 */
	public static String getImgSrc(String html) {
		if (html == null)
			return null;
		Document doc = Jsoup.parseBodyFragment(html);
		Element image = doc.select("img").first();
		return image == null ? null : image.attr("src");
	}
}
