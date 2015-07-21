/**
 * 
 */
package mblog.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mtons.modules.pojos.Paging;
import mtons.modules.pojos.UserProfile;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import mblog.context.AppContext;
import mblog.data.Attach;
import mblog.shiro.authc.AccountSubject;
import mblog.web.upload.FileRepo;

import com.google.gson.Gson;

/**
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
	
	protected void putProfile(UserProfile profile) {
		SecurityUtils.getSubject().getSession(true).setAttribute("profile", profile);
	}

	/**
	 * 包装分页对象
	 * 
	 * @param pn
	 *            页码
	 * @return
	 */
	protected Paging wrapPage(Integer pn) {
		if (pn == null || pn == 0) {
			pn = 1;
		}
		return new Paging(pn, 10);
	}

	/**
	 * 复制文件
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	protected String copyFile(String root, String dir, MultipartFile file)
			throws IOException {
		String realpath = getRealPath(root) + dir;
		Date current = new Date();
		String path = "/" + current.getTime()
				+ getSuffix(file.getOriginalFilename());
		File destFile = new File(realpath + path);
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}
		file.transferTo(destFile);
		return dir + path;
	}

	protected String genFileName(MultipartFile file) {
		return UUID.randomUUID().toString()
				+ getSuffix(file.getOriginalFilename());
	}

	protected String getRealPath(String root) {
		return session.getServletContext().getRealPath(root);
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
		for (Attach alb : albums) {
			String root = getRealPath("/");
			File temp = new File(root + alb.getOriginal());
			
			try {
				// 保存原图
				String orig = fileRepo.storeScale(temp, appContext.getOrigDir(), 800);
				alb.setOriginal(orig);
				
				// 创建缩放图片
				String preview = fileRepo.storeScale(temp, appContext.getThumbsDir(), 360);
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
