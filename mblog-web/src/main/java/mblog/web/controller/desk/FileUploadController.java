/**
 * 
 */
package mblog.web.controller.desk;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mblog.core.context.AppContext;
import mblog.web.controller.BaseController;
import mblog.web.data.UMEditorResult;
import mblog.web.upload.FileRepo;

import com.google.gson.Gson;

/**
 * 文件上传
 * 
 * @author langhsu
 *
 */
@Controller
public class FileUploadController extends BaseController {
	private static HashMap<String, String> errorInfo = new HashMap<String, String>();
    // 文件允许格式
 	private static String[] allowFiles = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
 	
 	@Autowired
	private AppContext appContext;
	@Autowired
	private FileRepo fileRepo;
	
 	static {
 		errorInfo.put("SUCCESS", "SUCCESS"); //默认成功
		errorInfo.put("NOFILE", "未包含文件上传域");
		errorInfo.put("TYPE", "不允许的文件格式");
		errorInfo.put("SIZE", "文件大小超出限制");
		errorInfo.put("ENTYPE", "请求类型ENTYPE错误");
		errorInfo.put("REQUEST", "上传请求异常");
		errorInfo.put("IO", "IO异常");
		errorInfo.put("DIR", "目录创建失败");
		errorInfo.put("UNKNOWN", "未知错误");
 	}
	
 	@RequestMapping("/aj_um_upload")
 	public void upload(@RequestParam(value = "upfile", required=false) MultipartFile file, 
 			HttpServletResponse response) throws IOException {
 		UMEditorResult data = new UMEditorResult();
 		
 		// 保存图片
		if (file != null && !file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			
			if (this.checkFileType(fileName)) {
				try {
					String path = fileRepo.storeScale(file, appContext.getThumbsDir(), 600);
					data.setName(fileName);
					data.setOriginalName(fileName);
					data.setType(getSuffix(fileName));
					data.setState(errorInfo.get("SUCCESS"));
					data.setUrl(path);
					data.setSize(file.getSize());
				} catch (Exception e) {
					data.setState(errorInfo.get("UNKNOWN"));
					e.printStackTrace();
				}
			} else {
				data.setState(errorInfo.get("TYPE"));
			}
			
			
		} else {
			data.setState(errorInfo.get("NOFILE"));
		}
		
		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(data));
 	}
 	
 	/**
	 * 文件类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}
 	
}
