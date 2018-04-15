package studentdata;

import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class studentdataDao {
		public studentdata querystudent(String studentid) {
			Configuration configuration=new Configuration();
			configuration.configure("/dataconfig.cfg.xml");
			SessionFactory sessionfactory=configuration.buildSessionFactory();
			Session session=sessionfactory.openSession();
			studentdata studentdatas=(studentdata)session.get(studentdata.class, studentid);
			//if(studentdatas==null||" ".equals(studentdatas)) System.out.println("cnat find any");
			session.close();
			return studentdatas;
			
			
			
			
		}
	public void savedata(String studentid ,String studentname,String studentpassword,String studentqq,String studentmail,String studenttel,String studentgrade,String studentmajor,String sex){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		studentdata data=new studentdata();
		data.setStudentemail(studentmail);
		data.setStudentgrade(studentgrade);
		data.setStudentid(studentid);
		data.setStudentmajor(studentmajor);
		data.setStudentname(studentname);
		data.setStudentpassword(studentpassword);
		data.setStudentqq(studentqq);
		data.setStudenttel(studenttel);
		data.setStudentsex(sex);
		session.save(data);
		transaction.commit();
		session.close();
		
		
		
	}
	public ArrayList<studentdata> queryalldata(){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="from studentdata";
		Query query=session.createQuery(hql);
		ArrayList<studentdata> data=(ArrayList<studentdata>) query.list();
		session.close();
		return data;
		
		
		
	}
	public void update(String password,String qq,String tel,String email,String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		studentdata data=new studentdata();
		data=session.get(studentdata.class, studentid);
		data.setStudentemail(email);
		data.setStudentpassword(password);
		data.setStudentqq(qq);
		data.setStudenttel(tel);
		session.update(data);
		transaction.commit();
		session.close();
		
		
	}
	public void update(String password,String qq,String tel,String email,String studentid,String name,String major,String grade,String sex) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		studentdata data=new studentdata();
		data=session.get(studentdata.class, studentid);
		data.setStudentemail(email);
		data.setStudentpassword(password);
		data.setStudentqq(qq);
		data.setStudenttel(tel);
		data.setStudentname(name);
		data.setStudentmajor(major);
		data.setStudentgrade(grade);
		data.setStudentsex(sex);
		session.update(data);
		transaction.commit();
		session.close();
		
		
	}
	public void deletestudent(String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		studentdata data=new studentdata();
		data=session.get(studentdata.class, studentid);
		session.delete(data);
		transaction.commit();
		session.close();
		
	}
	public ArrayList<Object[]> studentidandname(String major){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select studentname,studentid from studentdata where studentmajor like :major";
		Query query=session.createQuery(hql);
		query.setParameter("major", major);
		if(query==null) {
			return null;
		}
		ArrayList<Object[]> data=(ArrayList<Object[]>) query.list();
		session.close();
		return data;
		
	}
	public ArrayList<studentdata> studentdatabymajor(String major){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="from studentdata where studentmajor like :major";
		Query query=session.createQuery(hql);
		query.setParameter("major", major);
		if(query==null) {
			return null;
		}
		ArrayList<studentdata> data=(ArrayList<studentdata>) query.list();
		session.close();
		return data;
		
	}
	public String majorofstudent(String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select studentmajor from studentdata where studentid="+studentid;
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<String> data=(ArrayList<String>) query.list();
		session.close();
		return data.get(0);
		
	}
	public ArrayList<String> mailofstudent() {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select studentemail from studentdata ";
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<String> data=(ArrayList<String>) query.list();
		session.close();
		return data;
		
	}
	public ArrayList<String> mailofstudentbymajor(String major) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select studentemail from studentdata where major=:major";
		Query query=session.createQuery(hql);
		query.setParameter("major", major);
		if(query==null) {
			return null;
		}
		ArrayList<String> data=(ArrayList<String>) query.list();
		session.close();
		return data;
		
	}
}
