package mblog.web.controller.desk.user;

import mblog.persist.service.FollowService;
import mblog.web.controller.BaseController;
import mtons.modules.pojos.Data;
import mtons.modules.pojos.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author langhsu on 2015/8/18.
 */
@Controller
@RequestMapping("/account")
public class UserController extends BaseController {
    @Autowired
    private FollowService followService;

    @RequestMapping("/follow")
    public @ResponseBody Data follow(Long id) {
        Data data = Data.failure("操作失败");
        if (id != null) {
            try {
                UserProfile up = getSubject().getProfile();

                followService.follow(up.getId(), id);

                data = Data.success("关注成功!", Data.NOOP);
            } catch (Exception e) {
                data = Data.failure(e.getMessage());
            }
        }
        return data;
    }

    @RequestMapping("/unfollow")
    public @ResponseBody Data unfollow(Long id) {
        Data data = Data.failure("操作失败");
        if (id != null) {
            try {
                UserProfile up = getSubject().getProfile();

                followService.unfollow(up.getId(), id);

                data = Data.success("取消成功!", Data.NOOP);
            } catch (Exception e) {
                data = Data.failure(e.getMessage());
            }
        }
        return data;
    }

    @RequestMapping("/check_follow")
    public @ResponseBody Data checkFollow(Long id) {
        Data data = Data.failure("操作失败");
        if (id != null) {
            try {
                UserProfile up = getSubject().getProfile();

                boolean check = followService.checkFollow(up.getId(), id);

                data = Data.success("操作成功!", check);
            } catch (Exception e) {
                data = Data.failure(e.getMessage());
            }
        }
        return data;
    }
}
