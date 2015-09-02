/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.controller.admin;

import java.util.Collection;
import java.util.List;

import mblog.data.Config;
import mblog.persist.service.GroupService;
import mblog.persist.service.MenuService;
import mtons.modules.pojos.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mblog.lang.EnumConfig;
import mblog.persist.service.ConfigService;
import mblog.web.controller.BaseController;
import mblog.web.from.ConfigModel;

import javax.servlet.ServletContext;

/**
 * 系统配置
 *
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/admin/config")
public class ConfigsController extends BaseController {
	@Autowired
	private EhCacheCacheManager ehcacheManager;
	@Autowired
	private ConfigService configService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/")
	public String list(ModelMap model) {
		Collection<String> cacheNames = ehcacheManager.getCacheNames();

		model.put("cacheNames", cacheNames);
		model.put("configs", configService.findAll2Map());
		model.put("siteConfigs", EnumConfig.values());
		return "/admin/configs/main";
	}
	
	@RequestMapping("/view")
	public String view(ModelMap model) {
		model.put("configs", configService.findAll2Map());
		model.put("siteConfigs", EnumConfig.values());
		return "/admin/configs/view";
	}
	
	@RequestMapping("/update")
	public String update(ConfigModel config, ModelMap model) {
		configService.update(config.getConfigs());
		return list(model);
	}
	
	@RequestMapping("/flush_cache")
	public @ResponseBody Data flushCache() {
		ehcacheManager.getCacheManager().clearAll();
		return Data.success("操作成功", Data.NOOP);
	}

	@RequestMapping("/flush_conf")
	public @ResponseBody Data flushFiledia() {
		// 刷新系统变量
		List<Config> configs = configService.findAll();
		configs.forEach(conf -> servletContext.setAttribute(conf.getKey(), conf.getValue()));

		// 刷新文章Group
		servletContext.setAttribute("groups", groupService.findAll());

		// 刷新菜单
		servletContext.setAttribute("menus", menuService.findAll());
		return Data.success("操作成功", Data.NOOP);
	}
	
}
