package mblog.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mblog.data.Role;
import mblog.persist.service.RoleService;
import mblog.web.controller.BaseController;
import mtons.modules.pojos.Paging;

@Controller
@RequestMapping("/admin/roles")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;
	
	@ModelAttribute("role")
	public Role get(@RequestParam(required=false) String id) {
		if (id!=null&&!id.equals("0")){
			return roleService.get(Long.valueOf(id));
		}else{
			return new Role();
		}
	}
	
	@RequestMapping("/list")
	public String list(Integer pn, String key, ModelMap model) {
		Paging page = wrapPage(pn);
		roleService.paging(page, key);
		model.put("page", page);
		model.put("key", key);
		return "/admin/roles/list";
	}
	
	@RequestMapping(value = "view")
	public String view(Role role, Model model) {
		model.addAttribute("role", role);
		return "/admin/roles/view";
	}
	
	@RequestMapping("/save")
	public String save(Role role,Model model){
		roleService.save(role);
		return "redirect:/admin/roles/list";
	}
	
	@RequestMapping("/delete")
	public String delete(Long id,Model model){
		roleService.delete(id);
		return "redirect:/admin/roles/list";
	}
}