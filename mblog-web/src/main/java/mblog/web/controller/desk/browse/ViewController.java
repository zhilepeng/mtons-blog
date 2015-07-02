/**
 * 
 */
package mblog.web.controller.desk.browse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import mblog.planet.PostPlanet;
import mblog.data.Post;
import mblog.lang.Consts;
import mblog.persist.service.PostService;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;

/**
 * 文章浏览
 * 
 * @author langhsu
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
		
		if (ret == null) {
			return "redirect:" + Consts.ERROR_PAGE_404;
		}
		
		postService.identityViews(id);
		model.put("ret", ret);
		return getView(Views.BROWSE_DETAIL);
	}
}
