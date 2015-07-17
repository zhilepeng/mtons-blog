/**
 * 
 */
package mblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author langhsu
 *
 */
@Controller
public class RestController {
	
	@RequestMapping("/about")
	public String about() {
		return "/rest/about";
	}
	
	@RequestMapping("/joinus")
	public String joinus() {
		return "/rest/joinus";
	}
	
	@RequestMapping("/help")
	public String help() {
		return "/rest/help";
	}
}
