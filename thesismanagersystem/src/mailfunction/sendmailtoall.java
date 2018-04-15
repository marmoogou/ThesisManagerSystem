package mailfunction;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendmailtoall extends Thread {
	private String mailusername="thesismanager_1";
	private String mailpassword="bendan120";
	private String mailURL="smtp.sohu.com";
	private String from="thesismanager_1@sohu.com";
	private ArrayList<String> info;
	private String inside;
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
			             for(String a:info) {
			              Message message = createEmail(session,a);
			              ts.sendMessage(message, message.getAllRecipients());
			              }
			              ts.close();
			          }catch (Exception e) {
			              throw new RuntimeException(e);
			          }
			     
	
		super.run();
	}

	 public Message createEmail(Session session,String info) throws Exception{
		          
		          MimeMessage message = new MimeMessage(session);
		          message.setFrom(new InternetAddress(from));
		          message.setRecipient(Message.RecipientType.TO, new InternetAddress(info));
		          message.setSubject("毕业论文管理系统提示邮件");
		          
		          String infomation =inside;
		          message.setContent(infomation, "text/html;charset=UTF-8");
		          message.saveChanges();
		          return message;
		      }
public sendmailtoall(ArrayList<String> info,String inside) {
	this.info=info;
	this.inside=inside;
}
}
