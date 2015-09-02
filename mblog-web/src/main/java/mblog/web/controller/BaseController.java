/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mblog.data.AccountProfile;
import mtons.modules.utils.MD5Helper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import mblog.data.Attach;
import mblog.extend.context.AppContext;
import mblog.extend.upload.FileRepo;
import mblog.shiro.authc.AccountSubject;
import mtons.modules.pojos.Paging;
import mtons.modules.pojos.UserProfile;

/**
 * Controller 基类
 * 
 * @author langhsu
 * 
 */
public class BaseController {
	@Autowired
	protected HttpSession session;
	@Autowired
	protected AppContext appContext;
	@Autowired
	protected FileRepo fileRepo;

	/**
	 * 获取登录信息
	 * 
	 * @return
	 */
	protected AccountSubject getSubject(){
	    return (AccountSubject) SecurityUtils.getSubject();
	}
	
	protected void putProfile(AccountProfile profile) {
		SecurityUtils.getSubject().getSession(true).setAttribute("profile", profile);
	}

	protected AuthenticationToken createToken(String username, String password) {
		return new UsernamePasswordToken(username, MD5Helper.md5(password));
	}

	/**
	 * 包装分页对象
	 * 
	 * @param pn 页码
	 * @return
	 */
	protected Paging wrapPage(Integer pn) {
		if (pn == null || pn == 0) {
			pn = 1;
		}
		return new Paging(pn, 12);
	}

	protected String getSuffix(String name) {
		int pos = name.lastIndexOf(".");
		return name.substring(pos);
	}

	public String toJson(Object obj) {
		return new Gson().toJson(obj);
	}

	protected String getView(String view) {
		return "/default" + view;
	}
	
	protected String routeView(String route, String group) {
		String format = "/default" + route;
		return String.format(format, group);
	}

	public static String getIpAddr(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
	
	protected void handleAlbums(List<Attach> albums) {
		if (albums == null || albums.isEmpty()) {
			return;
		}

		Iterator<Attach> it = albums.iterator();

		while (it.hasNext()) {
			Attach alb = it.next();

			if (alb == null ||  StringUtils.isBlank(alb.getOriginal())) {
				it.remove();
				continue;
			}

			String root = fileRepo.getRoot();
			File temp = new File(root + alb.getOriginal());

			try {
				// 保存原图
				String orig = fileRepo.storeScale(temp, appContext.getOrigDir(), 750);
				alb.setOriginal(orig);

				// 创建缩放图片
				String preview = fileRepo.storeScale(temp, appContext.getThumbsDir(), 305);
				alb.setPreview(preview);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (temp != null) {
					temp.delete();
				}
			}
		}
	}
	
}
