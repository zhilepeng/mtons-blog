/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.controller.desk.ta;

import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import mblog.data.User;
import mblog.extend.planet.PostPlanet;
import mblog.persist.service.UserService;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;

/**
 * @author langhsu
 *
 */
@Controller
public class TaController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/ta/{uid}")
	public String home(@PathVariable Long uid, Integer pn, ModelMap model) {
		User user = userService.get(uid);
		Paging page = wrapPage(pn);
		page = postPlanet.pagingByUserId(page, uid);
		
		model.put("user", user);
		model.put("page", page);
		return getView(Views.TA_HOME);
	}
}
