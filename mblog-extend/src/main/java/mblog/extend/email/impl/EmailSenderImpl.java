/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.extend.email.impl;

import mblog.extend.context.AppContext;
import mblog.extend.context.Global;
import mblog.extend.email.EmailSender;
import mblog.lang.Consts;
import mblog.lang.EnumConfig;
import mtons.modules.exception.MtonsException;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

/**
 * @author langhsu on 2015/8/14.
 */
public class EmailSenderImpl implements EmailSender {
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private AppContext appContext;

    // 发送器
    private JavaMailSenderImpl sender;
    private String domain;

    @Override
    public void sendTemplete(String address, String subject, String template, Map<String, Object> data) {
        data.put("domain", getDomain());
        final String html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", data);

        sendText(address, subject, html, true);
    }

    @Override
    public void sendText(String address, String subject, String content, boolean html) {
        MimeMessage msg = sender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(msg, true, "UTF-8");
            message.setFrom(sender.getUsername());
            message.setSubject(subject);
            message.setTo(address);
            message.setText(content, html);

            new Thread(() -> {
                sender.send(msg);
            }).start();

        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    @PostConstruct
    public void init() {
        String host = Global.getConfig("mail.host");
        String username = Global.getConfig("mail.username");
        String password = Global.getConfig("mail.password");

        if (StringUtils.isEmpty(host) || StringUtils.isEmpty(username) ||  StringUtils.isEmpty(password)) {
            throw new MtonsException(Consts.MTONS_CONFIG + " 文件中的 mail.* 相关配置不完整!");
        }

        sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setDefaultEncoding("UTF-8");
        sender.setUsername(username);
        sender.setPassword(password);

        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", Global.getConfig("mail.auth"));
        props.setProperty("mail.smtp.timeout", Global.getConfig("mail.timeout"));

        sender.setJavaMailProperties(props);
    }

    private String getDomain() {
        if (domain == null) {
            domain = appContext.getConfig().get(EnumConfig.SITE_DOMAIN.getKey());
            if (domain.endsWith("/")) {
                domain = domain.substring(0, domain.lastIndexOf("/"));
            }
        }
        return  domain;
    }
}
