package mblog.lang;

/**
 * @author langhsu on 2015/9/5.
 */
public interface SiteConfig {
    /**
     * 站点类配置
     */

    String SITE_NAME = "site_name"; // 站点名称

    String SITE_WELCOMES = "site_welcomes"; // 欢迎语
    String SITE_DOMAIN   = "site_domain"; // 站点域名
    String SITE_KEYWORDS = "site_keywords"; // keywords
    String SITE_DESCRIPTION = "site_description"; // description
    String SITE_METAS = "site_metas"; // 扩展Metas

    String SITE_ICP = "site_icp"; // 备案号

    /**
     * 系统类配置
     */

    String SITE_MAIL_HS = "site_mail_host"; // 邮件服务地址
    String SITE_MAIL_UN = "site_mail_username"; // 邮件账号名
    String SITE_MAIL_PW = "site_mail_password"; // 邮件密码

    /**
     * 第三方回调地址
     */
    String SITE_OAUTH_QQ = "site_oauth_qq"; // 第三方登录-QQ回调地址
    String SITE_OAUTH_WEIBO = "site_oauth_weibo"; // 第三方登录-微博回调地址

    String SITE_EDITOR = "site_editor"; // 文本编辑器
}
