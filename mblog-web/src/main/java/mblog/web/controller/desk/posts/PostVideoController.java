/**
 * 
 */
package mblog.web.controller.desk.posts;

import java.net.MalformedURLException;
import java.util.Collections;

import mblog.analysis.videos.VideoAnalysis;
import mblog.data.Attach;
import mblog.data.Post;
import mblog.data.Video;
import mblog.lang.Consts;
import mblog.planet.PostPlanet;
import mblog.web.controller.BaseController;
import mtons.modules.pojos.UserProfile;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
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
			
			Attach att = handleAlbums(video.getThumbnail());
			
			post.setType("video");
			post.setTitle(video.getTitle());
			post.setSummary(video.getDescription());
			post.setContent(video.getBody() + post.getContent());
			post.setAuthorId(up.getId());
			post.setAlbums(Collections.singletonList(att));
			
			postPlanet.post(post);
		}
		return "redirect:/home";
	}
	
	private Attach handleAlbums(String thumbnail) {
		if (StringUtils.isBlank(thumbnail)) {
			return null;
		}
		
		Attach att = new Attach();
		att.setOriginal(thumbnail);
		att.setPreview(thumbnail);
		att.setStore(Consts.ATTACH_STORE_NETWORK);
		return att;
	}

}
