package mailfunction;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class teachertostudentmail extends Thread {

	private String mailusername="thesismanager_1";
	private String mailpassword="bendan120";
	private String mailURL="smtp.sohu.com";
	private String from="thesismanager_1@sohu.com";
	private mailinfo info;
	@Override
	public void run() {
		
			        try{
			              Properties prop = new Properties();
			              prop.setProperty("mail.host", mailURL);
			              prop.setProperty("mail.transport.protocol", "smtp");
			             prop.setProperty("mail.smtp.auth", "true");
			              Session session = Session.getInstance(prop);
			              session.setDebug(true);
			              Transport ts = session.getTransport();
			             ts.connect(mailURL, mailusername, mailpassword);
			              Message message = createEmail(session,info);
			              ts.sendMessage(message, message.getAllRecipients());
			              ts.close();
			          }catch (Exception e) {
			              throw new RuntimeException(e);
			          }
			     
	
		super.run();
	}

	 public Message createEmail(Session session,mailinfo info) throws Exception{
		          
		          MimeMessage message = new MimeMessage(session);
		          message.setFrom(new InternetAddress(from));
		          message.setRecipient(Message.RecipientType.TO, new InternetAddress(info.getUsermail()));
		          message.setSubject("毕业论文管理系统提示邮件");
		          
		          String infomation = "选题已被通过"+ ",你所选的论文题目：" + info.getThesistitle() + "，指导老师："+info.getThesisteacher()+"指导老师职称："+info.getThesistitle()+",老师qq："+info.getQqnumber()+"，老师邮箱："+info.getTomail()+"，老师手机号码："+info.getTelephone()+",论文简介："+info.getBrief();
		          message.setContent(infomation, "text/html;charset=UTF-8");
		          message.saveChanges();
		          return message;
		      }

public  teachertostudentmail(mailinfo info) {
	this.info=info;
}
	
	
}
