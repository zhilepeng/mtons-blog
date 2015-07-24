/**
 * 
 */
package mblog.web.controller.desk.account;

import mtons.modules.pojos.Data;
import mtons.modules.pojos.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mblog.commons.data.User;
import mblog.commons.persist.service.UserService;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;

/**
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/account")
public class ProfileController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String view() {
		return getView(Views.ACCOUNT_PROFILE);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String post(String name, String email, ModelMap model) {
		Data data = Data.failure("注册失败");
		UserProfile profile = getSubject().getProfile();
		
		try {
			
			model.put("data", data);
			User user = new User();
			user.setId(profile.getId());
			user.setEmail(email);
			user.setName(name);
			putProfile(userService.update(user));
			
			data = Data.success("操作成功", Data.NOOP);
		} catch (Exception e) {
			data = Data.failure(e.getMessage());
		}
		model.put("data", data);
		return getView(Views.ACCOUNT_PROFILE);
	}

}
