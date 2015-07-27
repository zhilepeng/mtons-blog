/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.web.listener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import mblog.data.Config;
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
            	System.out.println("+---------.站点信息初始化.--------+");
            	
            	List<Config> configs = configService.findAll();
            	
            	if (configs.isEmpty()) {
            		System.out.println("ERROR 站点配置信息加载失败");
            	} else {
            		configs.forEach(conf -> servletContext.setAttribute(conf.getKey(), conf.getValue()));
            	}
            	
            	servletContext.setAttribute("groups", groupService.findAll());
            	
            	servletContext.setAttribute("menus", menuService.findAll());
            	
            	System.out.println("+----------------------------+");
            	System.out.println("+------------恭喜您------------+");
            	System.out.println("+---Mblog加载完毕,您已经可以使用了---+");
            	System.out.println("+----------------------------+");
            }
        }, 2 * Consts.TIME_MIN);
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
