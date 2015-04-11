/**
 * 
 */
package mblog.web.controller.front;

import mblog.core.planet.PostPlanet;
import mblog.web.controller.BaseController;
import mtons.modules.pojos.Page;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author langhsu
 *
 */
@Controller
public class IndexController extends BaseController{
	@Autowired
	private PostPlanet postPlanet;
	
	@RequestMapping("/")
	public String root(Integer pn, String ord, ModelMap model) {
		Page page = wrapPage(pn);
		String order = "hottest";
		if (StringUtils.isNotBlank(ord)) {
			order = ord;
		}
		page = postPlanet.paging(page, order);
		model.put("page", page);
		model.put("ord", order);
		return getView(Views.INDEX);
	}
	
	@RequestMapping("/index")
	public String index(Integer pn, String ord, ModelMap model) {
		Page page = wrapPage(pn);
		String order = "hottest";
		if (StringUtils.isNotBlank(ord)) {
			order = ord;
		}
		page = postPlanet.paging(page, order);
		model.put("page", page);
		model.put("ord", order);
		return getView(Views.INDEX);
	}
}
