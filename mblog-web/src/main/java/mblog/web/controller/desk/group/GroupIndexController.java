/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.controller.desk.group;

import javax.servlet.http.HttpServletRequest;

import mblog.data.Group;
import mblog.extend.planet.PostPlanet;
import mblog.persist.service.GroupService;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;
import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author langhsu
 *
 */
@Controller
public class GroupIndexController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private GroupService groupService;
	
	private static String DEFAULT_ORDER = "newest";
	
	@RequestMapping("/g/{groupKey}")
	public String root(@PathVariable String groupKey, Integer pn, String ord, ModelMap model,
			HttpServletRequest request) {
		// init params
		Paging page = wrapPage(pn);
		String order = ServletRequestUtils.getStringParameter(request, "ord", DEFAULT_ORDER);
		Group group = groupService.getByKey(groupKey);
		
		// query
		page = postPlanet.paging(page, group.getId(), order);
		
		// callback params
		model.put("page", page);
		model.put("group", group);
		model.put("ord", order);
		return routeView(Views.ROUTE_POST_INDEX, group.getTemplate());
	}
	
}
