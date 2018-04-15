package teacherdata;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class teacherdataDao {
	
	public teacherdata queryteacher(String salaryid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		teacherdata teacherdatas=(teacherdata)session.get(teacherdata.class, salaryid);
		
		session.close();
		
		return teacherdatas;
		
		
		
		
	}
	public void savedata(String salaryid,String teachername,String teacherpassword,String teacherqq,String teachertel,String teachermail,String teachertitle,String major) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		teacherdata data=new teacherdata();
		data.setSalaryid(salaryid);
		data.setTeacheremail(teachermail);
		data.setTeachername(teachername);
		data.setTeacherpassword(teacherpassword);
		data.setTeacherqq(teacherqq);
		data.setTeachertel(teachertel);
		data.setTeachertitle(teachertitle);
		data.setMajor(major);
		data.setIsadministrator("no");
		session.save(data);
		transaction.commit();
		session.close();
		
		
		
	}
public ArrayList<teacherdata> queryalldata(){
	Configuration configuration=new Configuration();
	configuration.configure("/dataconfig.cfg.xml");
	SessionFactory sessionfactory=configuration.buildSessionFactory();
	Session session=sessionfactory.openSession();
	String hql="from teacherdata";
	Query qyery=session.createQuery(hql);
	ArrayList<teacherdata> data=(ArrayList<teacherdata>) qyery.list();
	session.close();
	return data;
}
public ArrayList<String> queryteachermail(){
	Configuration configuration=new Configuration();
	configuration.configure("/dataconfig.cfg.xml");
	SessionFactory sessionfactory=configuration.buildSessionFactory();
	Session session=sessionfactory.openSession();
	String hql="select teacheremail from teacherdata";
	Query qyery=session.createQuery(hql);
	if(qyery==null) {
		return null;
	}
	ArrayList<String> data=(ArrayList<String>) qyery.list();
	session.close();
	return data;
	
}
public String teachername(String salaryid) {
	Configuration configuration=new Configuration();
	configuration.configure("/dataconfig.cfg.xml");
	SessionFactory sessionfactory=configuration.buildSessionFactory();
	Session session=sessionfactory.openSession();
	String hql="select teachername from teacherdata where salaryid="+salaryid;
	Query qyery=session.createQuery(hql);
	if(qyery==null) {
		return null;
			}
	ArrayList<String> data=(ArrayList<String>) qyery.list();
	session.close();
	return  data.get(0);
}
public String teachermajor(String salaryid) {
	Configuration configuration=new Configuration();
	configuration.configure("/dataconfig.cfg.xml");
	SessionFactory sessionfactory=configuration.buildSessionFactory();
	Session session=sessionfactory.openSession();
	String hql="select major from teacherdata where salaryid="+salaryid;
	Query qyery=session.createQuery(hql);
	if(qyery==null) {
		return null;
			}
	ArrayList<String> data=(ArrayList<String>) qyery.list();
	session.close();
	return  data.get(0);
}
	public void teacherupdate(String password,String tel,String qq,String email,String salaryid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		teacherdata data=new teacherdata();
		data=session.get(teacherdata.class, salaryid);
		data.setTeacheremail(email);
		data.setTeacherqq(qq);
		data.setTeacherpassword(password);
		data.setTeachertel(tel);
		session.update(data);
		transaction.commit();
		session.close();
		
		
	}
	public void teacherupdate(String password,String tel,String qq,String email,String salaryid,String name ,String title,String major) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		teacherdata data=new teacherdata();
		data=session.get(teacherdata.class, salaryid);
		data.setTeacheremail(email);
		data.setTeacherqq(qq);
		data.setTeacherpassword(password);
		data.setTeachertel(tel);
		data.setTeachername(name);
		data.setTeachertitle(title);
		data.setMajor(major);
		session.update(data);
		transaction.commit();
		session.close();
		
		
	}
	public void deleteteacher(String salaryid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		teacherdata data=new teacherdata();
		data=session.get(teacherdata.class, salaryid);
		session.delete(data);
		transaction.commit();
		session.close();
		
	}
	public ArrayList<Object[]> teacheridandname(String major){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select teachername,salaryid from teacherdata where major=:major";
		Query query=session.createQuery(hql);
		query.setParameter("major", major);
		if(query==null) {
			return null;
		}
		ArrayList<Object[]> data=(ArrayList<Object[]>) query.list();
		session.close();
		return data;
		
	}
	public ArrayList<teacherdata> teacherdatabymajor(String major){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="from teacherdata where major like :major";
		Query query=session.createQuery(hql);
		query.setParameter("major", major);
		if(query==null) {
			return null;
		}
		ArrayList<teacherdata> data=(ArrayList<teacherdata>) query.list();
		session.close();
		return data;
		
	}
	public String isad(String salaryid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select isadministrator from teacherdata where salaryid="+salaryid;
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<String> data=(ArrayList<String>) query.list();
		session.close();
		return data.get(0);
	}
	public void upteacher(String salaryid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		teacherdata data=new teacherdata();
		data=session.get(teacherdata.class, salaryid);
		data.setIsadministrator("yes");
		session.update(data);
		transaction.commit();
		session.close();
		
	}
	public String namefortitle(String name) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select teachertitle from teacherdata where teachername=:name";
		Query query=session.createQuery(hql);
		query.setParameter("name", name);
		if(query==null) {
			return null;
		}
		ArrayList<String> data=(ArrayList<String>) query.list();
		session.close();
		return data.get(0);
	}
	public ArrayList<String> teacheremailformajor(String major){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select teacheremail from teacherdata where major=:major";
		Query query=session.createQuery(hql);
		query.setParameter("major", major);
		if(query==null) {
			return null;
		}
		ArrayList<String> data=(ArrayList<String>) query.list();
		session.close();
		return data;
		
	}
	public ArrayList<Object[]> nameforidandmajor(String teachername){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select salaryid,major from teacherdata where teachername=:teachername";
		Query query=session.createQuery(hql);
		query.setParameter("teachername", teachername);
		if(query==null) {
			return null;
		}
		ArrayList<Object[]> data=(ArrayList<Object[]>) query.list();
		session.close();
		return data;
		
	}
}
