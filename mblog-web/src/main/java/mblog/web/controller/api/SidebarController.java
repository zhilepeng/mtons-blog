/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.controller.api;

import mblog.data.Post;
import mblog.data.Tag;
import mblog.extend.planet.PostPlanet;
import mblog.extend.planet.TagPlanet;
import mblog.web.controller.BaseController;
import mtons.modules.pojos.Data;
import mtons.modules.pojos.UserProfile;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 侧边栏数据加载
 * 
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/api")
public class SidebarController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private TagPlanet tagPlanet;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Data login(String username, String password, ModelMap model) {
		Data data = Data.failure("操作失败");

		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return data;
		}

		AuthenticationToken token = createToken(username, password);
		if (token == null) {
			data.setMessage("用户名或密码错误");
			return data;
		}

		try {
			SecurityUtils.getSubject().login(token);
			data = Data.success("登录成功", getSubject().getProfile());

		} catch (AuthenticationException e) {
			if (e instanceof UnknownAccountException) {
				data.setMessage("用户不存在");
			} else if (e instanceof LockedAccountException) {
				data.setMessage("用户被禁用");
			} else {
				data.setMessage("用户认证失败");
			}
		}
		return data;
	}

	@RequestMapping("/latests.json")
	public @ResponseBody List<Post> latests() {
		UserProfile up = getSubject().getProfile();
		long ignoreUserId = 0;
		if (up != null) {
			ignoreUserId = up.getId();
		}
		List<Post> rets = postPlanet.findRecents(6, ignoreUserId);
		return rets;
	}
	
	@RequestMapping("/hots.json")
	public @ResponseBody List<Post> hots() {
		UserProfile up = getSubject().getProfile();
		long ignoreUserId = 0;
		if (up != null) {
			ignoreUserId = up.getId();
		}
		List<Post> rets = postPlanet.findHots(6, ignoreUserId);
		return rets;
	}
	
	@RequestMapping("/hot_tags.json")
	public @ResponseBody List<Tag> hotTags() {
		List<Tag> rets = tagPlanet.topTags(12, false);
		return rets;
	}
	
}
