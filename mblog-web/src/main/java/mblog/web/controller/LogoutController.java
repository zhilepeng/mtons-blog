/**
 * 
 */
package mblog.web.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * @author langhsu
 *
 */
@Controller
public class LogoutController extends BaseController {

	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

}
