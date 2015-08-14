package mblog.web.controller.desk;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

import mblog.data.OpenOauth;
import mblog.data.User;
import mblog.lang.Consts;
import mblog.lang.EnumOauthType;
import mblog.persist.service.OpenOauthService;
import mblog.persist.service.UserService;
import mblog.web.controller.BaseController;
import mtons.modules.exception.MtonsException;
import weibo4j.model.WeiboException;

/**
 * 第三方登录回调
 *
 * @author langhsu on 2015/8/12.
 */
@Controller
@RequestMapping("/oauth/callback")
public class CallbackController extends BaseController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OpenOauthService openOauthService;
    @Autowired
    private UserService userService;

    /**
     * 微博回调
     *
     * @param code
     * @param request
     * @return
     * @throws WeiboException
     * @throws QQConnectException
     */
    @RequestMapping("/weibo")
    public String callback4Weibo(String code, HttpServletRequest request, ModelMap model) throws WeiboException {
        OpenOauth openOauth = new OpenOauth();

        if (StringUtils.isBlank(code)) {
            throw new MtonsException("缺少必要的参数");
        }
        weibo4j.Oauth oauth = new weibo4j.Oauth();
        weibo4j.http.AccessToken token = oauth.getAccessTokenByCode(code);

        openOauth.setAccessToken(token.getAccessToken());
        openOauth.setExpireIn(token.getExpireIn());
        openOauth.setOauthCode(code);
        openOauth.setOauthUserId(token.getUID());
        openOauth.setRefreshToken(token.getRefreshToken());
        openOauth.setOauthType(EnumOauthType.TYPE_SINA.getValue());

        OpenOauth thirdToken = openOauthService.getOauthByToken(openOauth.getAccessToken());

        if (thirdToken == null) {
            model.put("open", openOauth);
            return getView(Views.OAUTH_REG);
        }
        String username = userService.get(thirdToken.getUserId()).getUsername();
        return login(username, request);
    }

    /**
     * QQ回调
     *
     * @param code
     * @param request
     * @return
     * @throws QQConnectException
     */
    @RequestMapping("/qq")
    public String callback4QQ(String code, String state, HttpServletRequest request, ModelMap model) throws QQConnectException {
        OpenOauth openOauth = new OpenOauth();

        if (StringUtils.isBlank(code)) {
            throw new MtonsException("缺少必要的参数");
        }

        Oauth oauth = new Oauth();
        String queryString = request.getQueryString();
        com.qq.connect.javabeans.AccessToken token = oauth.getAccessTokenByQueryString(queryString, state);

        if (token.getAccessToken().equals("")) {
//          我们的网站被CSRF攻击了或者用户取消了授权 做一些数据统计工作
            throw new MtonsException("腾讯说他们的网站被CSRF攻击了, 反正是没有获取到响应参数");
        }
        OpenID openID = new OpenID(token.getAccessToken());

        String accessToken = token.getAccessToken();
        openOauth.setOauthCode(code);
        openOauth.setAccessToken(accessToken);
        openOauth.setExpireIn(String.valueOf(token.getExpireIn()));
        openOauth.setOauthUserId(openID.getUserOpenID());
        openOauth.setOauthType(EnumOauthType.TYPE_QQ.getValue());

        // 取得 QQ 用户信息
        String userName = String.valueOf(openID.getUserOpenID().getBytes().hashCode());

        openOauth.setUsername("QQ" + userName);
        UserInfo qzoneUserInfo = new UserInfo(accessToken, openID.getUserOpenID());

        UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
        openOauth.setNickname(userInfoBean.getNickname());

        openOauth.setAvatar(userInfoBean.getAvatar().getAvatarURL100());

        // 判断是否存在绑定此accessToken的用户
        OpenOauth thirdToken = openOauthService.getOauthByToken(accessToken);

        if (thirdToken == null) {
            model.put("open", openOauth);
            return getView(Views.OAUTH_REG);
        }
        String username = userService.get(thirdToken.getUserId()).getUsername();
        return login(username, request);
    }

    /**
     * 执行第三方绑定
     * @param openOauth
     * @param request
     * @return
     */
    @RequestMapping("/bind_oauth")
    public String bindOauth(OpenOauth openOauth, HttpServletRequest request) {
        OpenOauth thirdToken = openOauthService.getOauthByToken(openOauth.getAccessToken());
        String username = openOauth.getUsername();

        // 已存在：提取用户信息，登录
        if (thirdToken != null) {
            username = userService.get(thirdToken.getUserId()).getUsername();
            // 不存在：注册新用户，并绑定此token，登录
        } else {
            User user = userService.get(username);
            if(user == null){
                User u = userService.register(wrapUser(openOauth));

                thirdToken = new OpenOauth();
                BeanUtils.copyProperties(openOauth, thirdToken);
                thirdToken.setUserId(u.getId());

                openOauthService.saveOauthToken(thirdToken);
            } else {
                username = user.getUsername();
            }
        }

        String result = login(username, request);
        if(username.startsWith("QQ_") || username.startsWith("SN_")){
            return getView(Views.OAUTH_REG);
        }
        return result;
    }

    /**
     * 执行登录请求
     *
     * @param username
     * @param request
     * @return
     */
    private String login(String username, HttpServletRequest request) {
        String ret = getView(Views.LOGIN);

        if (StringUtils.isNotBlank(username)) {
            String pwd = userService.getUserPassword(username);

            AuthenticationToken token = new UsernamePasswordToken(username, pwd);

            try {
                SecurityUtils.getSubject().login(token);

                ret = Views.REDIRECT_HOME;
            } catch (AuthenticationException e) {
                logger.error(e);
                if (e instanceof UnknownAccountException) {
                    throw new MtonsException("用户不存在");
                } else if (e instanceof LockedAccountException) {
                    throw new MtonsException("用户被禁用");
                } else {
                    throw new MtonsException("用户认证失败");
                }
            }
            return ret;
        }
        throw new MtonsException("登录失败！");
    }

    private User wrapUser(OpenOauth openOauth) {
        User user = new User();
        user.setUsername(openOauth.getUsername());
        user.setName(openOauth.getNickname());
        user.setPassword(openOauth.getAccessToken());

        user.setSource(openOauth.getOauthType());

        if (StringUtils.isNotBlank(openOauth.getAvatar())) {
            //FIXME: 此处这么写有bug
            user.setAvatar(openOauth.getAvatar());
        } else {
            user.setAvatar(Consts.AVATAR);
        }
        return  user;
    }
}