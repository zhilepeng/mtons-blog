/**
 * 
 */
package mblog.web.controller.desk.account;

import mblog.extend.email.EmailSender;
import mblog.lang.Consts;
import mblog.persist.service.VerifyService;
import mtons.modules.pojos.Data;
import mtons.modules.pojos.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mblog.data.User;
import mblog.persist.service.UserService;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;

import java.util.HashMap;
import java.util.Map;

/**
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/account")
public class ProfileController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private VerifyService verifyService;
	@Autowired
	private EmailSender emailSender;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String view() {
		return getView(Views.ACCOUNT_PROFILE);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String post(String name, String email, ModelMap model) {
		Data data = Data.failure("修改失败");
		UserProfile profile = getSubject().getProfile();
		
		try {
			
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

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public String email() {
		return getView(Views.ACCOUNT_EMAIL);
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String emailPost(String email, ModelMap model) {
		Data data;
		UserProfile profile = getSubject().getProfile();

		try {
			Assert.notNull(email, "缺少必要的参数");

			String code = verifyService.generateCode(profile.getId(), Consts.VERIFY_BIND, email);

			Map<String, Object> context = new HashMap<>();
			context.put("userId", profile.getId());
			context.put("code", code);
			context.put("type", Consts.VERIFY_BIND);

			emailSender.to("bind.vm", email, "邮箱绑定验证", context);

			userService.updateEmail(profile.getId(), email);

			data = Data.success("操作成功，已经发送验证邮件，请前往邮箱验证", Data.NOOP);
		} catch (Exception e) {
			data = Data.failure(e.getMessage());
		}
		model.put("data", data);
		return getView(Views.ACCOUNT_EMAIL);
	}

}
