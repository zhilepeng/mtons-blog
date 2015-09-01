/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.web.listener;

import java.util.*;

import javax.servlet.ServletContext;

import mblog.data.Config;
import mblog.extend.context.AppContext;
import mblog.lang.Consts;
import mblog.persist.service.ConfigService;
import mblog.persist.service.GroupService;
import mblog.persist.service.MenuService;

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
				System.out.println("+----------.站点初始化.----------+");
            	
            	List<Config> configs = configService.findAll();
            	Map<String, String> configMap = new HashMap<>();

            	if (configs.isEmpty()) {
            		System.err.println("ERROR 站点配置信息加载失败");
					System.exit(1);
            	} else {
            		configs.forEach(conf -> {
						servletContext.setAttribute(conf.getKey(), conf.getValue());
						configMap.put(conf.getKey(), conf.getValue());
					});
            	}

				appContext.setConfig(configMap);
            	
            	servletContext.setAttribute("groups", groupService.findAll());
            	
            	servletContext.setAttribute("menus", menuService.findAll());

				System.out.println("+----------------------------+");
				System.out.println("+-----Mblog加载完毕,可以正常使用-----+");
				System.out.println("+----------------------------+");
            }
        }, 5 * Consts.TIME_MIN);
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
