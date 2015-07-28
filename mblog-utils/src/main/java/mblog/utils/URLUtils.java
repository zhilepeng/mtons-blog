/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
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
