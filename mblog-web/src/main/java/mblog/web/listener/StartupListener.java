/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.listener;

import java.util.*;

import javax.servlet.ServletContext;

import mblog.data.Config;
import mblog.extend.context.AppContext;
import mblog.lang.Consts;
import mblog.persist.service.ConfigService;
import mblog.persist.service.GroupService;
import mblog.persist.service.MenuService;

import mblog.print.Printer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

/**
 * @author langhsu
 *
 */
public class StartupListener implements InitializingBean, ServletContextAware {
	@Autowired
	private ConfigService configService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private AppContext appContext;
	
	private ServletContext servletContext;
	
	/**
	 * 加载配置信息到系统
	 * 
	 */
	private void loadConfig() {
        Timer timer = new Timer("loadConfig", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
				Printer.info("站点信息初始化...");
            	
            	List<Config> configs = configService.findAll();
            	Map<String, String> configMap = new HashMap<>();

            	if (configs.isEmpty()) {
					Printer.error("配置信息加载失败,我猜,可能是没有导入初始化数据(db_init.sql)导致的");
					System.exit(1);
            	} else {

					if (configs.size() < 13) {
						Printer.warn("嗯哼,系统检测到'系统配置'有更新,而你好像错过了什么, 赶紧去后台'系统配置'里检查下吧!");
					}
            		configs.forEach(conf -> {
						servletContext.setAttribute(conf.getKey(), conf.getValue());
						configMap.put(conf.getKey(), conf.getValue());
					});
            	}

				appContext.setConfig(configMap);
            	
            	servletContext.setAttribute("groups", groupService.findAll());
            	
            	servletContext.setAttribute("menus", menuService.findAll());

				Printer.info("OK, mblog 加载完了");
            }
        }, 3 * Consts.TIME_MIN);
    }

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loadConfig();
	}

}
