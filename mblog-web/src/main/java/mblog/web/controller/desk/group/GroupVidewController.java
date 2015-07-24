/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.controller.desk.group;

import mblog.commons.data.Group;
import mblog.commons.data.Post;
import mblog.commons.persist.service.GroupService;
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
 * 
 */
@Controller
@RequestMapping("/view")
public class GroupVidewController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private PostService postService;
	@Autowired
	private GroupService groupService;

	@RequestMapping("/{id}")
	public String view(@PathVariable Long id, ModelMap model) {
		Post ret = postPlanet.getPost(id);
		
		Assert.notNull(ret, "该文章已被删除");
		
		Group group = groupService.getById(ret.getGroup());

		postService.identityViews(id);
		model.put("ret", ret);
		return routeView(Views.ROUTE_POST_VIEW, group.getTemplate());
	}
}
