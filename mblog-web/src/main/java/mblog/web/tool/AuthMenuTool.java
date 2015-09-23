package mblog.web.tool;

import mblog.data.AuthMenu;
import mblog.persist.service.AuthMenuService;
import mblog.utils.SpringContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * Created by 011938 on 2015/9/23.
 */
@DefaultKey("auth")
@ValidScope(Scope.APPLICATION)
public class AuthMenuTool {

    public List<AuthMenu> findByParentId(long pid) {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

        AuthMenuService authMenuService = (AuthMenuService) wac.getBean("authMenuService");
        List<AuthMenu> list = authMenuService.findByParentId(pid);
        return list;
    }

}
