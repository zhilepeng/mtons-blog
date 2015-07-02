/**
 * 
 */
package mblog.web.controller.desk.browse;

import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import mblog.planet.PostPlanet;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;

/**
 * 相片走廊
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/browse")
public class GalleryController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	
	private int jsonMaxResults = 8;
	
	@RequestMapping("/gallery")
	public String view(Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		page.setMaxResults(jsonMaxResults);
		page = postPlanet.paging(page, "newest");
		model.put("page", page);
		return getView(Views.BROWSE_GALLERY);
	}
	
	@RequestMapping("/gallery_snippet/{pn}")
	public String snippet(@PathVariable Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		page.setMaxResults(jsonMaxResults);
		page = postPlanet.paging(page, "newest");
		model.put("page", page);
		return getView(Views.BROWSE_GALLERY_SNIPPET);
	}
}
