package thesisdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import optthesisdata.optthesisdata;
import studentdata.studentdata;

public class thesisdataDao {
	/*public List<Integer> thesistitilequery() {
	Configuration configuration=new Configuration();
	configuration.configure("/dataconfig.cfg.xml");
	SessionFactory sessionfactory =configuration.buildSessionFactory();
	Session session=sessionfactory.openSession();
	String hql="select thesisid from thesisdata";
	Query query=session.createQuery(hql);
	List<Integer> list =query.list();
	session.close();
	return list;
	
	
	}
	public ArrayList<thesisdata> thesisdataquery(List<Integer> list) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		ArrayList<thesisdata> thesisdatalist = new ArrayList<thesisdata>();
		for(int i=0;i<list.size();i++) {
			int j=list.get(i);
			thesisdata thesisdatas=(thesisdata)session.get(thesisdata.class, j);
			thesisdatalist.add(thesisdatas);
			
				
			}
		session.close();
		return thesisdatalist;
		
		
	}*/
	public ArrayList<thesisdata> thesisdataquery(){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="from thesisdata";
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<thesisdata> data=(ArrayList<thesisdata>) query.list();
		session.close();
		return data;
		
		
	}
	public ArrayList<thesisdata> teacherthesisdataquery(String salaryid){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql= "from thesisdata where salaryid="+salaryid;
		
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<thesisdata> thesisdatalist = new ArrayList<thesisdata>();
		thesisdatalist=(ArrayList<thesisdata>) query.list();
		session.close();
		return thesisdatalist;
		
	}
	public thesisdata queryinside(int thesisid){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		thesisdata datademo=(thesisdata)session.get(thesisdata.class, thesisid);
		System.out.println(datademo.getThesistitle());
		session.close();
		return datademo;
		
		
	}
	public void setchosed(int thesisid,String chosed) {
		
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		thesisdata datademo=session.get(thesisdata.class, thesisid);
		datademo.setIschosed(chosed);
		session.update(datademo);
		session.flush();
		transaction.commit();
		session.close();
		
	}
	public void deletethesis(int thesisid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		thesisdata datademo=session.get(thesisdata.class, thesisid);
		if(datademo==null) {
			return;
		}
		session.delete(datademo);
		transaction.commit();
		session.close();
		
		
		
	}
	/*public thesisdata queryinside(String studentid){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		thesisdata datademo=(thesisdata)session.get(thesisdata.class, studentid);
		session.close();
		return datademo;
		
		
	}*/
	
	public ArrayList<thesisdata> querybysalaryid(String salaryid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select thesisdata from thesisdata where salaryid="+salaryid;
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<thesisdata> data =new ArrayList<thesisdata>();
		data=(ArrayList<thesisdata>) query.list();
		session.close();
		return data;
	}
	public ArrayList<thesisdata> querybysalaryidanddate(String salaryid,String date) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();

		
		//Date date=new Date(2017);
		String hql="from thesisdata where salaryid="+salaryid+" and publicdate LIKE "+"'"+date+"-%-%%"+"'";
		
		Query query=session.createQuery(hql);
		ArrayList<thesisdata> data =new ArrayList<thesisdata>();
		data=(ArrayList<thesisdata>) query.list();
		 
		
		session.close();
		return data;
	}
	public ArrayList<thesisdata> querybysalaryidanddateandpassed(String salaryid,String date) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();

		
		//Date date=new Date(2017);
		String hql="from thesisdata where ischosed='yes' and salaryid="+salaryid+" and publicdate LIKE "+"'"+date+"-%-%%"+"'";
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<thesisdata> data =new ArrayList<thesisdata>();
		data=(ArrayList<thesisdata>) query.list();
		 
		for(thesisdata data1:data) {
			System.out.println(data1.getThesisbrief());
		}
		
		session.close();
		return data;
	}
	public ArrayList<String> querytitles(String salaryid,String date){
	Configuration configuration=new Configuration();
	configuration.configure("/dataconfig.cfg.xml");
	SessionFactory sessionfactory =configuration.buildSessionFactory();
	Session session=sessionfactory.openSession();

	
	//Date date=new Date(2017);
	String hql="select thesistitle from thesisdata where salaryid="+salaryid+" and publicdate LIKE "+"'"+date+"-%-%%"+"'";
	Query query=session.createQuery(hql);
	if(query==null) {
		return null;
	}
	ArrayList<String> datelist = (ArrayList<String>) query.list();
	session.close();
	return datelist;
	
}
	public void savethesis(String thesistitle,String thesisbrief,String salaryid,String teachername,String major) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		thesisdata data=new thesisdata();
		data.setIschosed("no");
		data.setSalaryid(salaryid);
		data.setThesisbrief(thesisbrief);
		data.setThesistitle(thesistitle);
		data.setThesisteacher(teachername);
		java.util.Date d=new java.util.Date();
		java.sql.Date chosedtime=new java.sql.Date(d.getTime());
		data.setPublicdate(chosedtime);
		data.setMajor(major);
		session.save(data);
		transaction.commit();
		session.close();
	}
	public void savethesisupload(String thesistitle,String thesisbrief,String salaryid,String teachername,String major) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		thesisdata data=new thesisdata();
		data.setIschosed("no");
		data.setSalaryid(salaryid);
		data.setThesisbrief(thesisbrief);
		data.setThesistitle(thesistitle);
		data.setThesisteacher(teachername);
		java.util.Date d=new java.util.Date();
		java.sql.Date chosedtime=new java.sql.Date(d.getTime());
		data.setPublicdate(chosedtime);
		data.setMajor(major);
		session.merge(data);
		transaction.commit();
		session.close();
	}
	public void updatethesis(String thesistitle,String thesisbrief,int thesisid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		thesisdata data=new thesisdata();
		data=session.get(thesisdata.class, thesisid);
		data.setThesisbrief(thesisbrief);
		data.setThesistitle(thesistitle);
		

		session.update(data);
		transaction.commit();
		session.close();
	}
	public ArrayList<String> querytitles(){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();

		
		//Date date=new Date(2017);
		String hql="select thesistitle from thesisdata";
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<String> datelist = (ArrayList<String>) query.list();
		session.close();
		return datelist;
		
	}
	public ArrayList<Object[]> querytitlesandnameanddate(){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();

		
		//Date date=new Date(2017);
		String hql="select thesistitle,thesisteacher,publicdate from thesisdata";
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<Object[]> datelist = (ArrayList<Object[]>) query.list();
		session.close();
		return datelist;
		
	}
	public void savethesisbyad(String thesistitle,String thesisbrief,String salaryid,String teachername,String major) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		thesisdata data=new thesisdata();
		data.setIschosed("yes");
		data.setSalaryid(salaryid);
		data.setThesisbrief(thesisbrief);
		data.setThesistitle(thesistitle);
		data.setThesisteacher(teachername);
		java.util.Date d=new java.util.Date();
		java.sql.Date chosedtime=new java.sql.Date(d.getTime());
		data.setPublicdate(chosedtime);
		data.setMajor(major);
		session.save(data);
		transaction.commit();
		session.close();
	}
	public ArrayList<thesisdata> thesisdatabymajor(String major){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="from  thesisdata where major = :major";
		Query query=session.createQuery(hql);
		query.setParameter("major", major);
		if(query==null) {
			return null;
		}
		ArrayList<thesisdata> data=(ArrayList<thesisdata>) query.list();
		session.close();
		return data;
		
	}
	public ArrayList<String> querytitlesbymajor(String major){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();

		
		//Date date=new Date(2017);
		String hql="select thesistitle from thesisdata where major = :major";
		Query query=session.createQuery(hql);
		query.setParameter("major", major);
		if(query==null) {
			return null;
		}
		ArrayList<String> datelist = (ArrayList<String>) query.list();
		session.close();
		return datelist;
		
	}
	public ArrayList<Object[]> querytitlesandnameanddatebymajor(String major){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();

		
		//Date date=new Date(2017);
		String hql="select thesistitle,thesisteacher,publicdate from thesisdata where major = :major";
		Query query=session.createQuery(hql);
		query.setParameter("major", major);
		if(query==null) {
			return null;
		}
		ArrayList<Object[]> datelist = (ArrayList<Object[]>) query.list();
		session.close();
		return datelist;
		
	}
	public int namefortitle(String title){
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();

		
		//Date date=new Date(2017);
		String hql="select thesisid from thesisdata where thesistitle=:title";
		Query query=session.createQuery(hql);
		query.setParameter("title", title);
		if(query==null) {
			return 0;
		}
		ArrayList<Integer> datelist = (ArrayList<Integer>) query.list();
		session.close();
		return datelist.get(0);
		
	}
}
