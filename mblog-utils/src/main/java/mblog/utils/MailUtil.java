package mblog.utils;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

/**
 * 
 * @author C
 *
 */
public class MailUtil { 
	private static Logger log = Logger.getLogger("MailUtil");
	
	private MimeMessage mimeMsg; //MIME邮件对象 
	private Session session; //邮件会话对象 
	private Properties props; //系统属性 
	private boolean needAuth = false; //smtp是否需要认证 
	//smtp认证用户名和密码 
	private String username; 
	private String password; 
	private Multipart mp; //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象 
	 
	/**
	 * Constructor
	 * @param smtp 邮件发送服务器
	 */
	public MailUtil(String smtp){ 
		setSmtpHost(smtp); 
		createMimeMessage(); 
	} 

	/**
	 * 设置邮件发送服务器
	 * @param hostName String 
	 */
	public void setSmtpHost(String hostName) { 
		log.info("设置系统属性：mail.smtp.host = "+hostName); 
		if(props == null)
			props = System.getProperties(); //获得系统属性对象 	
		props.put("mail.smtp.host",hostName); //设置SMTP主机 
	} 


	/**
	 * 创建MIME邮件对象  
	 * @return
	 */
	public boolean createMimeMessage() 
	{ 
		try { 
			log.info("准备获取邮件会话对象！"); 
			session = Session.getDefaultInstance(props,null); //获得邮件会话对象 
		} 
		catch(Exception e){ 
			log.error("获取邮件会话对象时发生错误！"+e); 
			return false; 
		} 
	
		log.info("准备创建MIME邮件对象！"); 
		try { 
			mimeMsg = new MimeMessage(session); //创建MIME邮件对象 
//			mp = new MimeMultipart(); //普通邮件
//			mp = new MimeMultipart("mixed"); //附件加图片混合型邮件
			mp = new MimeMultipart("related"); //包含图片的邮件
			return true; 
		} catch(Exception e){ 
			log.error("创建MIME邮件对象失败！"+e); 
			return false; 
		} 
	} 	
	
	/**
	 * 设置SMTP是否需要验证
	 * @param need
	 */
	public void setNeedAuth(boolean need) { 
		log.info("设置smtp身份认证：mail.smtp.auth = "+need); 
		if(props == null) props = System.getProperties(); 
		if(need){ 
			props.put("mail.smtp.auth","true"); 
		}else{ 
			props.put("mail.smtp.auth","false"); 
		} 
	} 

	/**
	 * 设置用户名和密码
	 * @param name
	 * @param pass
	 */
	public void setNamePass(String name,String pass) { 
		username = name; 
		password = pass; 
	} 

	/**
	 * 设置邮件主题
	 * @param mailSubject
	 * @return
	 */
	public boolean setSubject(String mailSubject) { 
		log.info("设置邮件主题！"); 
		try{ 
			mimeMsg.setSubject(mailSubject); 
			return true; 
		} 
		catch(Exception e) { 
			log.error("设置邮件主题发生错误！"); 
			return false; 
		} 
	}
	
	/** 
	 * 设置邮件正文
	 * @param mailBody String 
	 */ 
	public boolean setBody(String mailBody) { 
		try{ 
			BodyPart bp = new MimeBodyPart(); 
			bp.setContent(""+mailBody,"text/html;charset=GBK"); 
			mp.addBodyPart(bp); 
			return true; 
		} catch(Exception e){ 
		log.error("设置邮件正文时发生错误！"+e); 
		return false; 
		} 
	} 
	
	/** 
	 * 设置邮件正文
	 * @param mailBody String 
	 */ 
	public boolean setBody_imgurl(String mailBody,Map<String,URL> images) { 
		try{ 
			BodyPart bp = new MimeBodyPart(); 
			bp.setContent(""+mailBody,"text/html;charset=GBK"); 
			mp.addBodyPart(bp); 
			if(images!=null && images.size()>0) {  
	             Set<Entry<String, URL>> set=images.entrySet();  
	             for (Iterator iterator = set.iterator(); iterator.hasNext();) {  
	                Entry<String, URL> entry = (Entry<String, URL>) iterator.next();  
	                  
	                //创建用于保存图片的MimeBodyPart对象，并将它保存到MimeMultipart中  
	                MimeBodyPart gifBodyPart=new MimeBodyPart();  
//	                FileDataSource fds=new FileDataSource(entry.getValue());//图片所在的目录的绝对路径  
	                  
	                gifBodyPart.setDataHandler(new DataHandler(entry.getValue()));  
	                gifBodyPart.setContentID(entry.getKey());   //cid的值  
	                mp.addBodyPart(gifBodyPart);  
	            }  
	        } 
//			MimeBodyPart bodyImgPart_logo = new MimeBodyPart();
//	        DataSource fds_logo = new FileDataSource("C:\\img\\logo.png");
//	        bodyImgPart_logo.setDataHandler(new DataHandler(fds_logo));
//	        bodyImgPart_logo.setHeader("Content-ID","<imglogo>");
//	        mp.addBodyPart(bodyImgPart_logo);
//	        MimeBodyPart bodyImgPart_post = new MimeBodyPart();
//	        DataSource fds = new FileDataSource("C:\\img\\post.jpg");
//	        bodyImgPart_post.setDataHandler(new DataHandler(fds));
//	        bodyImgPart_post.setHeader("Content-ID","<imgpost>");
//	        mp.addBodyPart(bodyImgPart_post);
			return true; 
		} catch(Exception e){ 
		log.error("设置邮件正文时发生错误！"+e); 
		return false; 
		} 
	}
	
	/** 
	 * 设置邮件正文
	 * @param mailBody String 
	 */ 
	public boolean setBody_img(String mailBody,Map<String,String> images) { 
		try{ 
			BodyPart bp = new MimeBodyPart(); 
			bp.setContent(""+mailBody,"text/html;charset=GBK"); 
			mp.addBodyPart(bp); 
			if(images!=null && images.size()>0) {  
	             Set<Entry<String, String>> set=images.entrySet();  
	             for (Iterator iterator = set.iterator(); iterator.hasNext();) {  
	                Entry<String, String> entry = (Entry<String, String>) iterator.next();  
	                  
	                //创建用于保存图片的MimeBodyPart对象，并将它保存到MimeMultipart中  
	                MimeBodyPart gifBodyPart=new MimeBodyPart();  
	                FileDataSource fds=new FileDataSource(entry.getValue());//图片所在的目录的绝对路径  
	                  
	                gifBodyPart.setDataHandler(new DataHandler(fds));  
	                gifBodyPart.setContentID(entry.getKey());   //cid的值  
	                mp.addBodyPart(gifBodyPart);  
	            }  
	        } 
//			MimeBodyPart bodyImgPart_logo = new MimeBodyPart();
//	        DataSource fds_logo = new FileDataSource("C:\\img\\logo.png");
//	        bodyImgPart_logo.setDataHandler(new DataHandler(fds_logo));
//	        bodyImgPart_logo.setHeader("Content-ID","<imglogo>");
//	        mp.addBodyPart(bodyImgPart_logo);
//	        MimeBodyPart bodyImgPart_post = new MimeBodyPart();
//	        DataSource fds = new FileDataSource("C:\\img\\post.jpg");
//	        bodyImgPart_post.setDataHandler(new DataHandler(fds));
//	        bodyImgPart_post.setHeader("Content-ID","<imgpost>");
//	        mp.addBodyPart(bodyImgPart_post);
			return true; 
		} catch(Exception e){ 
		log.error("设置邮件正文时发生错误！"+e); 
		return false; 
		} 
	}
	
	/** 
	 * 添加附件
	 * @param filename String 
	 */ 
	public boolean addFileAffix(String filename) { 
	
		log.info("增加邮件附件："+filename); 
		try{ 
			BodyPart bp = new MimeBodyPart(); 
			FileDataSource fileds = new FileDataSource(filename); 
			bp.setDataHandler(new DataHandler(fileds)); 
			bp.setFileName(fileds.getName()); 
			
			mp.addBodyPart(bp); 
			
			return true; 
		} catch(Exception e){ 
			log.error("增加邮件附件："+filename+"发生错误！"+e); 
			return false; 
		} 
	} 
	
	/** 
	 * 设置发信人
	 * @param from String 
	 */ 
	public boolean setFrom(String from) { 
		log.info("设置发信人！"); 
		try{ 
			mimeMsg.setFrom(new InternetAddress(from)); //设置发信人 
			return true; 
		} catch(Exception e) { 
			return false; 
		} 
	} 
	/** 
	 * 设置收信人
	 * @param to String 
	 */ 
	public boolean setTo(String to){ 
		if(to == null)return false; 
		try{ 
			mimeMsg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to)); 
			return true; 
		} catch(Exception e) { 
			return false; 
		} 	
	} 
	
	/** 
	 * 设置抄送人
	 * @param copyto String  
	 */ 
	public boolean setCopyTo(String copyto) 
	{ 
		if(copyto == null)return false; 
		try{ 
		mimeMsg.setRecipients(Message.RecipientType.CC,(Address[])InternetAddress.parse(copyto)); 
		return true; 
		} 
		catch(Exception e) 
		{ return false; } 
	} 
	
	/** 
	 * 发送邮件
	 */ 
	public boolean sendOut() 
	{ 
		try{ 
			mimeMsg.setContent(mp); 
			mimeMsg.saveChanges(); 
			log.info("正在发送邮件...."); 
			
			Session mailSession = Session.getInstance(props,null); 
			Transport transport = mailSession.getTransport("smtp"); 
			transport.connect((String)props.get("mail.smtp.host"),username,password); 
			transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO)); 
			//transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC)); 
			//transport.send(mimeMsg); 
			
			log.info("发送邮件成功！"); 
			transport.close(); 
			
			return true; 
		} catch(Exception e) { 
			e.printStackTrace();
			log.error("邮件发送失败！"+e); 
			return false; 
		} 
	} 
	
	/** 
	 * 发送邮件
	 */ 
	public boolean sendOutNoCopy() 
	{ 
		try{ 
			mimeMsg.setContent(mp); 
			mimeMsg.saveChanges(); 
			log.info("正在发送邮件...."); 
			
			Session mailSession = Session.getInstance(props,null); 
			Transport transport = mailSession.getTransport("smtp"); 
			transport.connect((String)props.get("mail.smtp.host"),username,password); 
			transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO)); 
//			transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC)); 
			//transport.send(mimeMsg); 
			
			log.info("发送邮件成功！"); 
			transport.close(); 
			
			return true; 
		} catch(Exception e) { 
			log.error("邮件发送失败！"+e); 
			return false; 
		} 
	} 

	/**
	 * 调用sendOut方法完成邮件发送
	 * @param smtp
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public static boolean send(String smtp,String from,String to,String subject,String content,String username,String password) {
		MailUtil theMail = new MailUtil(smtp);
		theMail.setNeedAuth(true); //需要验证
		
		if(!theMail.setSubject(subject)) return false;
//		if(!theMail.setBody(content)) return false;
		if(!theMail.setBody(content)) return false;
		if(!theMail.setTo(to)) return false;
		if(!theMail.setFrom(from)) return false;
		theMail.setNamePass(username,password);
		
		if(!theMail.sendOutNoCopy()) return false;
		return true;
	}
	
	/**
	 * 
	 * @param smtp
	 * @param from
	 * @param to
	 * @param subject
	 * @param images
	 * @param content
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean send(String smtp,String from,String to,String subject,Map<String,String> images,String content,String username,String password) {
		MailUtil theMail = new MailUtil(smtp);
		theMail.setNeedAuth(true); //需要验证
		
		if(!theMail.setSubject(subject)) return false;
		if(!theMail.setBody_img(content,images)) return false;
		if(!theMail.setTo(to)) return false;
		if(!theMail.setFrom(from)) return false;
		theMail.setNamePass(username,password);
		
		if(!theMail.sendOutNoCopy()) return false;
		return true;
	}
	
	public static boolean send_imgurl(String smtp,String from,String to,String subject,Map<String,URL> images,String content,String username,String password) {
		MailUtil theMail = new MailUtil(smtp);
		theMail.setNeedAuth(true); //需要验证
		
		if(!theMail.setSubject(subject)) return false;
		if(!theMail.setBody_imgurl(content,images)) return false;
		if(!theMail.setTo(to)) return false;
		if(!theMail.setFrom(from)) return false;
		theMail.setNamePass(username,password);
		
		if(!theMail.sendOutNoCopy()) return false;
		return true;
	}
	
	/**
	 * 调用sendOut方法完成邮件发送,带抄送
	 * @param smtp
	 * @param from
	 * @param to
	 * @param copyto
	 * @param subject
	 * @param content
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public static boolean sendAndCc(String smtp,String from,String to,String copyto,String subject,String content,String username,String password) {
		MailUtil theMail = new MailUtil(smtp);
		theMail.setNeedAuth(true); //需要验证
		
		if(!theMail.setSubject(subject)) return false;
		if(!theMail.setBody(content)) return false;
		if(!theMail.setTo(to)) return false;
		if(!theMail.setCopyTo(copyto)) return false;
		if(!theMail.setFrom(from)) return false;
		theMail.setNamePass(username,password);
		
		if(!theMail.sendOut()) return false;
		return true;
	}
	
	/**
	 * 调用sendOut方法完成邮件发送,带附件
	 * @param smtp
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param username
	 * @param password
	 * @param filename 附件路径
	 * @return
	 */
	public static boolean send(String smtp,String from,String to,String subject,String content,String username,String password,String filename) {
		MailUtil theMail = new MailUtil(smtp);
		theMail.setNeedAuth(true); //需要验证
		
		if(!theMail.setSubject(subject)) return false;
		if(!theMail.setBody(content)) return false;
		if(!theMail.addFileAffix(filename)) return false; 
		if(!theMail.setTo(to)) return false;
		if(!theMail.setFrom(from)) return false;
		theMail.setNamePass(username,password);
		
		if(!theMail.sendOut()) return false;
		return true;
	}
	
	/**
	 * 调用sendOut方法完成邮件发送,带附件和抄送
	 * @param smtp
	 * @param from
	 * @param to
	 * @param copyto
	 * @param subject
	 * @param content
	 * @param username
	 * @param password
	 * @param filename
	 * @return
	 */
	public static boolean sendAndCc(String smtp,String from,String to,String copyto,String subject,String content,String username,String password,String filename) {
		MailUtil theMail = new MailUtil(smtp);
		theMail.setNeedAuth(true); //需要验证
		
		if(!theMail.setSubject(subject)) return false;
		if(!theMail.setBody(content)) return false;
		if(!theMail.addFileAffix(filename)) return false; 
		if(!theMail.setTo(to)) return false;
		//if(!theMail.setCopyTo(copyto)) return false;
		if(!theMail.setFrom(from)) return false;
		theMail.setNamePass(username,password);
		
		if(!theMail.sendOut()) return false;
		return true;
	}
	/**
	 * 
	 * @param receiceEmail 接收者邮箱
	 */
	public static void SendEmail(String receiceEmail,String validateCode){
		String smtp = "smtp.163.com";//邮箱服务地址
		String from = "dreamcuiwei@163.com";
		String subject = "用户注册邮箱激活！";
		///邮件的链接内容
        StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8080/mblog/validateEmali?validateCode=");
        sb.append(validateCode);
        sb.append("\">http://localhost:8080/mblog/validateEmali?validateCode="); 
        sb.append(validateCode);
        sb.append("</a>");
		String username="dreamcuiwei@163.com";//邮箱服务用户名
		String password="";//邮箱服务密码
		MailUtil.sendAndCc(smtp, from, receiceEmail, "", subject, sb.toString(), username, password);
	}
	
	public static void main(String[] args){
			
		
	}
} 


