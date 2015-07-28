/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import mblog.utils.PropertiesLoader;
import mtons.modules.utils.Exceptions;
import mtons.modules.utils.GMagickUtils;

/**
 * 系统初始化
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(getClass());
    
    public InitServlet() {
        super();
    }
    
    /**
     * Init
     */
    @Override
    public void init() throws ServletException {
    	System.out.println("+----------加载配置文件 start---------+");
    	
    	// 初始化配置文件
    	try {
    		PropertiesLoader propertiesLoader = new PropertiesLoader("/mtons.properties");
    		String gmHome = propertiesLoader.getProperty(GMagickUtils.GMAGICK_HOME);
    		System.setProperty(GMagickUtils.GMAGICK_HOME, gmHome);
		} catch (Exception e) {
			log.error("系统初始化失败: " + Exceptions.getStackTraceAsString(e));
            System.exit(0);
		}
        
        System.out.println("+----------加载配置文件 end ----------+");
    }
}
