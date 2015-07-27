package mblog.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import mblog.core.utils.PropertiesLoader;
import mtons.modules.utils.GMagickUtils;

import org.apache.log4j.Logger;

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
    	System.out.println(" --- 系统参数初始化... --- ");
        PropertiesLoader propertiesLoader = new PropertiesLoader("mtons.properties");
        String gmHome = propertiesLoader.getProperty(GMagickUtils.GMAGICK_HOME);
		System.setProperty(GMagickUtils.GMAGICK_HOME, gmHome);
        System.out.println(" --- 系统参数初始化结束 --- ");
    }
}
