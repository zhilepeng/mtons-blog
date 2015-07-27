/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.controller.desk.user;

import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import mblog.persist.service.PostService;
import mblog.shiro.authc.AccountSubject;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;

/**
 * @author langhsu
 *
 */
@Controller
public class HomeController extends BaseController {
	@Autowired
	private PostService postService;
	
	@RequestMapping("/home")
	public String home(Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		AccountSubject subject = getSubject();
		postService.pagingByUserId(page, subject.getProfile().getId());
		
		model.put("page", page);
		
		return getView(Views.HOME);
	}
	
}
