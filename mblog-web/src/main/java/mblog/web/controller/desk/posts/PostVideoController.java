/**
 * 
 */
package mblog.web.controller.desk.posts;

import java.net.MalformedURLException;
import java.util.Collections;

import mblog.data.Attach;
import mblog.data.Post;
import mblog.extend.analysis.videos.VideoAnalysis;
import mblog.extend.data.Video;
import mblog.extend.planet.PostPlanet;
import mblog.lang.Consts;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;
import mtons.modules.pojos.UserProfile;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 发布视频
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/post")
public class PostVideoController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private VideoAnalysis videoAnalysis;
	
	@RequestMapping(value = "/submit/video", method = RequestMethod.POST)
	public String postVideo(Post post, String url) throws MalformedURLException {
		
		if (StringUtils.isNotBlank(url)) {
			UserProfile up = getSubject().getProfile();
			
			Video video = videoAnalysis.take(url);
			
			Attach att = handleAlbums(video.getBigThumbnail());
			
			post.setTitle(video.getTitle());
			post.setSummary(video.getDescription());
			post.setContent(video.getBody() + post.getContent());
			post.setAuthorId(up.getId());
			post.setAlbums(Collections.singletonList(att));
			
			postPlanet.post(post);
		}
		return Views.REDIRECT_HOME;
	}
	
	private Attach handleAlbums(String thumbnail) {
		if (StringUtils.isBlank(thumbnail)) {
			return null;
		}
		
		Attach att = new Attach();
		att.setOriginal(thumbnail);
		att.setPreview(thumbnail);
		att.setScreenshot(thumbnail);
		att.setStore(Consts.ATTACH_STORE_NETWORK);
		return att;
	}

}
