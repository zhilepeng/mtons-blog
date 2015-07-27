/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.controller.desk;

import mblog.core.planet.PostPlanet;
import mblog.web.controller.BaseController;
import mtons.modules.lang.Const;
import mtons.modules.pojos.Paging;

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
	
	private String defaultOrder = "newest";
	
	@RequestMapping(value= {"/", "/index"})
	public String root(Integer pn, String ord, ModelMap model) {
		Paging page = wrapPage(pn);
		String order = defaultOrder;
		if (StringUtils.isNotBlank(ord)) {
			order = ord;
		}
		page = postPlanet.paging(page, Const.ZERO, order);
		model.put("page", page);
		model.put("ord", order);
		return getView(Views.INDEX);
	}
	
}
