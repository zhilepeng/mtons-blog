package mblog.extend.email.impl;

import mblog.extend.email.EmailSender;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

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

    private Map<String, String> config;
    private JavaMailSenderImpl javaMailSender;

    @Override
    public void to(String template, String address, String subject, Map<String, Object> data) {
        final String html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", data);

        new Thread(){
            public void run(){
                send(subject, address, html, true);
            }
        }.start();
    }

    private void send(String subject, String address, String text, boolean html){
        JavaMailSender sender = getJavaMailSender();
        try {
            MimeMessage msg = sender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(msg, true, "UTF-8");
            message.setFrom(config.get("from"));
            message.setSubject(subject);
            message.setTo(address);
            message.setText(text, html);

            sender.send(msg);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    private JavaMailSender getJavaMailSender() {
        if (javaMailSender == null) {
            javaMailSender = new JavaMailSenderImpl();

            javaMailSender.setHost(config.get("host"));
            javaMailSender.setDefaultEncoding("UTF-8");
            javaMailSender.setUsername(config.get("username"));
            javaMailSender.setPassword(config.get("password"));

            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", config.get("auth"));
            props.setProperty("mail.smtp.timeout", config.get("timeout"));

            javaMailSender.setJavaMailProperties(props);
        }
        return javaMailSender;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }
}
