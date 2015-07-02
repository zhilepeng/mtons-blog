/**
 * 
 */
package mblog.web.controller.desk.user;

import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import mblog.planet.PostPlanet;
import mblog.data.User;
import mblog.persist.service.UserService;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;

/**
 * @author langhsu
 *
 */
@Controller
public class UserHomeController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/{uid}")
	public String home(@PathVariable Long uid, Integer pn, ModelMap model) {
		User user = userService.get(uid);
		Paging page = wrapPage(pn);
		page = postPlanet.pagingByUserId(page, uid);
		
		model.put("user", user);
		model.put("page", page);
		return getView(Views.USER_HOME);
	}
}
