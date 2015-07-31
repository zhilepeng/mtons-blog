/**
 * 
 */
package video;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;

import mblog.extend.analysis.videos.VideoAnalysis;
import mblog.extend.analysis.videos.support.Youku;
import mblog.extend.data.Video;

/**
 * @author langhsu
 *
 */
public class YoukuTest {

	public static void main(String[] args) throws Exception {
		VideoAnalysis robot = new VideoAnalysis();
		robot.addStrategy("v.youku.com", new Youku());

		try {
			Video v = robot.take("http://v.youku.com/v_show/id_XMTI4MTIzMDQ1Ng==_ev_2.html");
			System.err.println(getVideoId("http://v.youku.com/v_show/id_XNjExMTQ5OTIw.html"));
			System.out.println(JSON.toJSONString(v));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getVideoId(String url) {
		// 正则表达式解析地址，取id
		Pattern p = Pattern.compile(".*id_(\\w+)\\.html");
		String u = url;
		Matcher m = p.matcher(u);
		String id = "";
		if (m.find()) {
			id = m.group(1);
		}
		return id;
	}
}
