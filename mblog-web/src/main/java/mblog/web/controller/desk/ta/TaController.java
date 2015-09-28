/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.controller.desk.ta;

import mblog.base.lang.EnumPrivacy;
import mblog.core.data.User;
import mblog.core.planet.PostPlanet;
import mblog.core.planet.UserPlanet;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;
import mtons.modules.pojos.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 访问他人主页
 * @author langhsu
 *
 */
@Controller
public class TaController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private UserPlanet userPlanet;
	
	@RequestMapping("/ta/{uid}")
	public String home(@PathVariable Long uid, Integer pn, ModelMap model) {
		User user = userPlanet.getUser(uid);
		Paging page = wrapPage(pn);
		page = postPlanet.pagingByAuthorId(page, uid, EnumPrivacy.OPEN);
		
		model.put("user", user);
		model.put("page", page);
		return getView(Views.TA_HOME);
	}
}
