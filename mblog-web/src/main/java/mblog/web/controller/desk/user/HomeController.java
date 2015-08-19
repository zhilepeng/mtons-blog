/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.controller.desk.user;

import mblog.data.UserFull;
import mblog.extend.planet.CommentPlanet;
import mblog.extend.planet.UserPlanet;
import mblog.persist.service.FeedsService;
import mblog.persist.service.FollowService;
import mblog.persist.service.PostService;
import mblog.persist.service.UserService;
import mblog.shiro.authc.AccountSubject;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;
import mtons.modules.lang.Const;
import mtons.modules.pojos.Paging;
import mtons.modules.pojos.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户主页
 * @author langhsu
 *
 */
@Controller
public class HomeController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private FeedsService feedsService;
	@Autowired
	private CommentPlanet commentPlanet;
	@Autowired
	private UserService userService;
	@Autowired
	private FollowService followService;

	/**
	 * 用户主页
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/home")
	public String home(Integer pn, ModelMap model) {
		Paging paging = wrapPage(pn);
		AccountSubject subject = getSubject();

		feedsService.findUserFeeds(paging, subject.getProfile().getId(), Const.ZERO, Const.ZERO);

		model.put("page", paging);
		initUser(model);

		return getView(Views.HOME_FEEDS);
	}

	/**
	 * 我发布的文章
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/home", params = "method=posts")
	public String posts(Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		AccountSubject subject = getSubject();
		postService.pagingByUserId(page, subject.getProfile().getId());

		model.put("page", page);
		initUser(model);

		return getView(Views.HOME_POSTS);
	}

	/**
	 * 我发表的评论
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/home", params = "method=comments")
	public String comments(Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		AccountSubject subject = getSubject();
		page = commentPlanet.paging4Home(page, subject.getProfile().getId());

		model.put("page", page);
		initUser(model);

		return getView(Views.HOME_COMMENTS);
	}

	/**
	 * 我的关注
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/home/follows")
	public String follows(Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		UserProfile profile = getSubject().getProfile();
		followService.follows(page, profile.getId());

		model.put("page", page);
		initUser(model);

		return getView(Views.HOME_FOLLOWS);
	}

	/**
	 * 我的粉丝
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/home/fans")
	public String fans(Integer pn, ModelMap model) {
		Paging page = wrapPage(pn);
		UserProfile profile = getSubject().getProfile();
		followService.fans(page, profile.getId());

		model.put("page", page);
		initUser(model);

		return getView(Views.HOME_FANS);
	}

	private void initUser(ModelMap model) {
		UserProfile up = getSubject().getProfile();
		UserFull user = userService.getUserFull(up.getId());

		model.put("user", user);
	}
}
