package mblog.web.controller.admin;

import com.alibaba.fastjson.JSONArray;
import mblog.data.AuthMenu;
import mblog.data.Role;
import mblog.persist.service.AuthMenuService;
import mblog.web.controller.BaseController;
import mtons.modules.pojos.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
        return "/admin/authMenus/list";
    }

    @RequestMapping(value = "view")
    public String view(@RequestParam(required = false) Long parentId, AuthMenu authMenu, Model model) {
        model.addAttribute("parentId",parentId);
        model.addAttribute("authMenu", authMenu);
        return "/admin/authMenus/view";
    }

    @RequestMapping("/save")
    public String save(@RequestParam(required = false) Long parentId,AuthMenu authMenu,Model model){
        if(parentId!=null && parentId>0){
            AuthMenu parent = authMenuService.get(parentId);
            authMenu.setParent(parent);
        }
        authMenuService.save(authMenu);
        return "redirect:/admin/authMenus/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id,Model model){
        authMenuService.delete(id);
        return "redirect:/admin/authMenus/list";
    }

    @RequestMapping("treeView")
    public String treeView(){
        return "/admin/authMenus/treeView";
    }

    @RequestMapping("tree")
    @ResponseBody
    public List<AuthMenu.Node> tree(){
        List<AuthMenu> list = authMenuService.tree(1L);
        List<AuthMenu.Node> nodes = new ArrayList<>();
        for(AuthMenu authMenu : list){
            AuthMenu.Node node = authMenu.toNode();
            nodes.add(node);
        }
        return nodes;
    }
}


