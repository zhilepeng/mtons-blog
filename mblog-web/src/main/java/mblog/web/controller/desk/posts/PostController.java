/**
 * 
 */
package mblog.web.controller.desk.posts;

import mblog.data.Group;
import mblog.data.Post;
import mblog.extend.planet.PostPlanet;
import mblog.persist.service.GroupService;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;
import mtons.modules.pojos.Data;
import mtons.modules.pojos.UserProfile;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 文章操作
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/post")
public class PostController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private GroupService groupService;

	/**
	 * 发布文章页
	 * @param groupKey
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new/{groupKey}", method = RequestMethod.GET)
	public String view(@PathVariable String groupKey, ModelMap model) {
		Group group = groupService.getByKey(groupKey);
		model.put("group", group);
		return routeView(Views.ROUTE_POST_PUBLISH, group.getTemplate());
	}

	/**
	 * 提交发布
	 * @param blog
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String post(Post blog, HttpServletRequest request) {
		
		if (blog != null && StringUtils.isNotBlank(blog.getTitle())) {
			UserProfile profile = getSubject().getProfile();
			
			String[] ablums = request.getParameterValues("delayImages");
			blog.setAlbums(handleAlbums(ablums));
			blog.setAuthorId(profile.getId());
			
			postPlanet.post(blog);
		}
		return Views.REDIRECT_HOME;
	}

	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete/{id}")
	public @ResponseBody Data delete(@PathVariable Long id) {
		Data data = Data.failure("操作失败");
		if (id != null) {
			UserProfile up = getSubject().getProfile();
			try {
				postPlanet.delete(id, up.getId());
				data = Data.success("操作成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		return data;
	}

}
