/**
 * 
 */
package mblog.core.utils;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author langhsu
 *
 */
public class PreviewTextUtils {

	public static String truncateText(String html, int length){
		Document doc = Jsoup.parse(html);
		String text = doc.text();
		text = StringUtils.abbreviate(text, length);
		return text;
	}
}
