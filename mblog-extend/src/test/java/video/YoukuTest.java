/**
 * 
 */
package video;

import java.net.MalformedURLException;

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
			
			System.out.println(JSON.toJSONString(v));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
