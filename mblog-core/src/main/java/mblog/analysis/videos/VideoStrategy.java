/**
 * 
 */
package mblog.analysis.videos;

import mblog.data.Video;

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
