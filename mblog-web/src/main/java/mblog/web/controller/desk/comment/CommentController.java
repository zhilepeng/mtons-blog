/**
 * 
 */
package mblog.web.controller.desk.comment;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import mblog.data.Comment;
import mblog.extend.planet.CommentPlanet;
import mblog.persist.service.PostService;
import mblog.web.controller.BaseController;
import mtons.modules.pojos.Data;
import mtons.modules.pojos.Paging;
import mtons.modules.pojos.UserProfile;

/**
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentPlanet commentPlanet;
	@Autowired
	private PostService postService;
	
	@RequestMapping("/list/{toId}")
	public @ResponseBody Paging view(Integer pn, @PathVariable Long toId) {
		Paging page = wrapPage(pn);
		page = commentPlanet.paging(page, toId);
		return page;
	}
	
	@RequestMapping("/submit")
	public @ResponseBody Data post(Long toId, String text, HttpServletRequest request) {
		Data data = Data.failure("操作失败");
		
		long pid = ServletRequestUtils.getLongParameter(request, "pid", 0);
		
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			data = Data.failure("请先登录在进行操作");
			
			return data;
		}
		if (toId > 0 && StringUtils.isNotEmpty(text)) {
			UserProfile up = getSubject().getProfile();
			
			Comment c = new Comment();
			c.setToId(toId);
			c.setContent(HtmlUtils.htmlEscape(text));
			c.setAuthorId(up.getId());
			
			c.setPid(pid);
			
			commentPlanet.post(c);
			// 自增评论数
			postService.identityComments(c.getToId());
			
			data = Data.success("发表成功!", Data.NOOP);
		}
		return data;
	}

	@RequestMapping("/delete")
	public @ResponseBody Data delete(Long id) {
		Data data = Data.failure("操作失败");
		if (id != null) {
			UserProfile up = getSubject().getProfile();
			try {
				commentPlanet.delete(id, up.getId());
				data = Data.success("操作成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		return data;
	}
	
}
