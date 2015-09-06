/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import mblog.lang.Consts;
import mblog.print.Printer;
import org.apache.log4j.Logger;

import mblog.utils.PropertiesLoader;
import mtons.modules.utils.Exceptions;
import mtons.modules.utils.GMagickUtils;

/**
 * 系统初始化
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InitServlet() {
        super();
    }
    
    /**
     * Init
     */
    @Override
    public void init() throws ServletException {
        Printer.info("加载配置文件...");
        // 初始化配置文件
    	try {
    		PropertiesLoader p = new PropertiesLoader(Consts.MTONS_CONFIG);
    		String gmHome = p.getProperty(GMagickUtils.GMAGICK_HOME);
    		System.setProperty(GMagickUtils.GMAGICK_HOME, gmHome);
            System.setProperty(Consts.SYSTEM_VERSION, p.getProperty(Consts.SYSTEM_VERSION));

		} catch (Exception e) {
            Printer.error("说实话, 我也不知道啥错, 你自己看吧", e);
            System.exit(0);
		}

        Printer.info("加载配置文件 OK !");
    }
}
