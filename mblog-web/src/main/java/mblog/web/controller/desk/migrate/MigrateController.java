/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.controller.desk.migrate;

import mblog.data.Post;
import mblog.data.User;
import mblog.extend.planet.PostPlanet;
import mblog.persist.service.PostService;
import mblog.persist.service.UserService;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;
import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 老版本链接兼容方案
 * 
 * @author langhsu
 * @deprecated
 */
@Controller
public class MigrateController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/browse/detail/{id}")
	public String view(@PathVariable Long id, ModelMap model) {
		Post ret = postPlanet.getPost(id);
		
		Assert.notNull(ret, "该文章已被删除");
		
		postService.identityViews(id);
		model.put("ret", ret);
		return getView(Views.BROWSE_DETAIL);
	}
	
//	@RequestMapping("/user/{uid}")
//	public String home(@PathVariable Long uid, Integer pn, ModelMap model) {
//		User user = userService.get(uid);
//		Paging page = wrapPage(pn);
//		page = postPlanet.pagingByAuthorId(page, uid);
//
//		model.put("user", user);
//		model.put("page", page);
//		return getView(Views.TA_HOME);
//	}
}
