/**
 * 
 */
package mblog.utils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author langhsu
 *
 */
public class URLUtils {
	
	public static String getHost(String link) {
		URL url = null;
		String host = "";
		try {
			url = new URL(link);
			host = url.getHost();
		} catch (MalformedURLException e) {
		}
		return host;
	}
}
