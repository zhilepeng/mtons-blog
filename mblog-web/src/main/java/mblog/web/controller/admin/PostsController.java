/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.controller.admin;

import java.util.List;

import mtons.modules.lang.Const;
import mtons.modules.pojos.Data;
import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mblog.data.Post;
import mblog.extend.planet.PostPlanet;
import mblog.persist.service.PostService;
import mblog.web.controller.BaseController;

/**
 * @author langhsu
 *
 */
@Controller("mng_post_ctl")
@RequestMapping("/admin/posts")
public class PostsController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private PostPlanet postPlanet;
	
	@RequestMapping("/list")
	public String list(Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		postService.paging(page, Const.ZERO, "newest", false);
		model.put("page", page);
		return "/admin/posts/list";
	}
	
	@RequestMapping("/view")
	public String view(Long id, ModelMap model) {
		Post ret = postService.get(id);
		model.put("view", ret);
		return "/admin/posts/view";
	}
	/**
	 * 跳转到文章编辑方法
	 * @param id
	 * @param model
	 * @param P
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String toUpdate(Long id, ModelMap model) {
		Post ret = postService.get(id);
		model.put("view", ret);
		return "/admin/posts/update";
	}
	
	/**
	 * 更新文章方法
	 * @author LBB
	 * @param id
	 * @param model
	 * @param P
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String subUpdate(Post p) {
		if (p != null) {
			postService.update(p);
		}
		return "redirect:/admin/posts/list";
	}
	
	
	@RequestMapping("/delete")
	public @ResponseBody Data delete(@RequestParam("id") List<Long> id) {
		Data data = Data.failure("操作失败");
		if (id != null) {
			try {
				postPlanet.delete(id);
				data = Data.success("操作成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		return data;
	}
}
