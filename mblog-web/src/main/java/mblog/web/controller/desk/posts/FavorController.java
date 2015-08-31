package mblog.web.controller.desk.posts;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mblog.extend.planet.PostPlanet;
import mblog.web.controller.BaseController;
import mtons.modules.pojos.Data;
import mtons.modules.pojos.UserProfile;

/**
 * @author langhsu on 2015/8/31.
 */
@Controller
@RequestMapping("/post")
public class FavorController extends BaseController {
    @Autowired
    private PostPlanet postPlanet;

    /**
     * 喜欢文章
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/favor")
    public @ResponseBody Data favor(Long id, HttpServletRequest request) {
        Data data = Data.failure("操作失败");
        if (id != null) {
            try {
                UserProfile up = getSubject().getProfile();
                postPlanet.favor(up.getId(), id);

                data = Data.success("操作成功!", Data.NOOP);
            } catch (Exception e) {
                data = Data.failure(e.getMessage());
            }
        }
        return data;
    }
}