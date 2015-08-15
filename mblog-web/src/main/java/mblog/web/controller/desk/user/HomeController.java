/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.controller.desk.user;

import mblog.extend.planet.CommentPlanet;
import mblog.persist.service.PostService;
import mblog.shiro.authc.AccountSubject;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;
import mtons.modules.pojos.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author langhsu
 *
 */
@Controller
public class HomeController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private CommentPlanet commentPlanet;

	@RequestMapping(value="/home")
	public String home(Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		AccountSubject subject = getSubject();
		postService.pagingByUserId(page, subject.getProfile().getId());
		
		model.put("page", page);
		
		return getView(Views.HOME_INDEX);
	}

	@RequestMapping(value="/home", params = "method=posts")
	public String posts(Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		AccountSubject subject = getSubject();
		postService.pagingByUserId(page, subject.getProfile().getId());

		model.put("page", page);

		return getView(Views.HOME_POSTS);
	}

	@RequestMapping(value="/home", params = "method=comments")
	public String comments(Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		AccountSubject subject = getSubject();
		page = commentPlanet.paging4Home(page, subject.getProfile().getId());

		model.put("page", page);

		return getView(Views.HOME_COMMENTS);
	}
	
}
