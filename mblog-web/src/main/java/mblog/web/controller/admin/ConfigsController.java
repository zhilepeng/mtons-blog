/**
 * 
 */
package mblog.web.controller.admin;

import java.util.Collection;

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
import mblog.web.data.ConfigModel;

/**
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
	
	@RequestMapping("/cache_flush")
	public @ResponseBody Data cacheFlush() {
		ehcacheManager.getCacheManager().removeAllCaches();
		return Data.success("操作成功", Data.NOOP);
	}
	
}
