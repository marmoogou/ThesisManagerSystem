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
		          message.setSubject("��ҵ���Ĺ���ϵͳ��ʾ�ʼ�");
		          
		          String infomation = "ѡ���ѱ�ͨ��"+ ",����ѡ��������Ŀ��" + info.getThesistitle() + "��ָ����ʦ��"+info.getThesisteacher()+"ָ����ʦְ�ƣ�"+info.getThesistitle()+",��ʦqq��"+info.getQqnumber()+"����ʦ���䣺"+info.getTomail()+"����ʦ�ֻ����룺"+info.getTelephone()+",���ļ�飺"+info.getBrief();
		          message.setContent(infomation, "text/html;charset=UTF-8");
		          message.saveChanges();
		          return message;
		      }

public  teachertostudentmail(mailinfo info) {
	this.info=info;
}
	
	
}
