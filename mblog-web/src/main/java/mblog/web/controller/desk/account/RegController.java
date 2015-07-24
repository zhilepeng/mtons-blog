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
import mblog.commons.lang.Consts;
import mblog.commons.persist.service.UserService;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;

/**
 * @author langhsu
 *
 */
@Controller
public class RegController extends BaseController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String view(ModelMap model) {
		UserProfile profile = getSubject().getProfile();
		if (profile != null) {
			return "redirect:/home";
		}
		return getView(Views.REG);
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String reg(User user, ModelMap model) {
		Data data = Data.failure("注册失败");
		String ret = getView(Views.REG);
		
		try {
			user.setAvatar(Consts.AVATAR);
			userService.register(user);
			data = Data.success("恭喜您! 注册成功", Data.NOOP);
			ret = getView(Views.REG_RESULT);
			
		} catch (Exception e) {
			data = Data.failure(e.getMessage());
		}
		model.put("data", data);
		return ret;
	}

}
