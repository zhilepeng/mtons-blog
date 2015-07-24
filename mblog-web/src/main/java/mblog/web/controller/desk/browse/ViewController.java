/**
 * 
 */
package mblog.web.controller.desk.browse;

import mblog.commons.data.Post;
import mblog.commons.persist.service.PostService;
import mblog.core.planet.PostPlanet;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文章浏览
 * 
 * @author langhsu
 * @deprecated
 *
 */
@Controller
@RequestMapping("/browse")
public class ViewController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private PostService postService;
	
	@RequestMapping("/detail/{id}")
	public String view(@PathVariable Long id, ModelMap model) {
		Post ret = postPlanet.getPost(id);
		
		Assert.notNull(ret, "该文章已被删除");
		
		postService.identityViews(id);
		model.put("ret", ret);
		return getView(Views.BROWSE_DETAIL);
	}
}
