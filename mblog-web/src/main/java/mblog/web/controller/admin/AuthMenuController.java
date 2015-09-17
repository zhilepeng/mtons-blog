package mblog.web.controller.admin;

import mblog.data.AuthMenu;
import mblog.data.Role;
import mblog.persist.service.AuthMenuService;
import mblog.web.controller.BaseController;
import mtons.modules.pojos.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by 011938 on 2015/9/17.
 */
@Controller
@RequestMapping("/admin/authMenus")
public class AuthMenuController extends BaseController{

    @Autowired
    private AuthMenuService authMenuService;

    @ModelAttribute("authMenu")
    public AuthMenu get(@RequestParam(required=false) String id) {
        if (id!=null&&!id.equals("0")){
            return authMenuService.get(Long.valueOf(id));
        }else{
            return new AuthMenu();
        }
    }

    @RequestMapping("/list")
    public String list(ModelMap model) {
        List<AuthMenu> list = authMenuService.tree(1L);
        model.put("list", list);
        return "/admin/authMenu/list";
    }
}
