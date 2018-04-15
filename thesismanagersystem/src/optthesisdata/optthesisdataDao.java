package optthesisdata;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import thesisdata.thesisdata;

public class optthesisdataDao {
	/*public ArrayList<Integer> thesistitilequery() {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select thesisid from optthesisdata";
		Query query=session.createQuery(hql);
		
		ArrayList<Integer> list =(ArrayList<Integer>) query.list();
		session.close();
		return list;
		
		
		}
	public ArrayList<optthesisdata> optthesisdataquery(List<Integer> list) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		ArrayList<optthesisdata> thesisdatalist = new ArrayList<optthesisdata>();
		for(int i=0;i<list.size();i++) {
			int j=list.get(i);
			optthesisdata thesisdatas=(optthesisdata)session.get(optthesisdata.class, j);
			thesisdatalist.add(thesisdatas);
			
				
			}
		session.close();
		return thesisdatalist;
		
		
	}*/
	public ArrayList<optthesisdata> optthesisdataquerya() {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory =configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="from optthesisdata where ischecked= 'yes'";
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<optthesisdata> thesisdatalist = (ArrayList<optthesisdata>) query.list();
		session.close();
		return thesisdatalist;
		
		
	}
	public void savethesisdata(String studentid,String teachername,int thesisid,String  thesistopic, Date chosedtime) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		optthesisdata datademo=session.get(optthesisdata.class, thesisid);
		optthesisdata datademo1=new optthesisdata();
		if(datademo==null) {
		datademo1.setChosedtime(chosedtime);
		datademo1.setIschecked("wtq");
		datademo1.setStudentid(studentid);
		datademo1.setThesisid(thesisid);
		datademo1.setThesisteacher(teachername);
		datademo1.setThesistopic(thesistopic);
	
		session.save(datademo1);
		}else {
			datademo.setChosedtime(chosedtime);
			datademo.setIschecked("wtq");
			datademo.setStudentid(studentid);
			datademo.setThesisteacher(teachername);
			datademo.setThesistopic(thesistopic);
			session.update(datademo);
			
		}
		session.flush();
		transaction.commit();
		session.close();
		
		
	}
	public void savethesisdataupload(String studentid,String teachername,int thesisid,String  thesistopic, Date chosedtime) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		optthesisdata datademo=session.get(optthesisdata.class, thesisid);
		optthesisdata datademo1=new optthesisdata();
		
		datademo1.setChosedtime(chosedtime);
		datademo1.setIschecked("yes");
		datademo1.setStudentid(studentid);
		datademo1.setThesisid(thesisid);
		datademo1.setThesisteacher(teachername);
		datademo1.setThesistopic(thesistopic);
	
		session.merge(datademo1);
		transaction.commit();
		session.close();
		
		
		
	}
	
	public boolean ishasstu(int thesisid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		optthesisdata data=session.get(optthesisdata.class, thesisid);
		if(data!=null&&!(data.getIschecked().equals("no"))) {
			
			
			session.close();
			return false;
		
		}
		session.close();
		return true;
		
	
	}
	public boolean inopt(String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select ischecked from optthesisdata where studentid="+studentid;
		Query query=session.createQuery(hql);
		ArrayList<String> data=(ArrayList<String>) query.list();
		for(String a:data) {
		if(!(a.equals("no"))) {
			
			
			session.close();
			return false;
		
		}
		}
		session.close();
		return true;
		
	
	}
	public boolean tthesisid(String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select ischecked from optthesisdata where studentid="+studentid;
		Query query=session.createQuery(hql);
		ArrayList<String> data1=(ArrayList<String>) query.list();
		
		for(String data:data1) {
		if(data!=null&&(!(data.equals("no")))) {
			session.close();
			return false;
		}}
		session.close();
		return true;
	
	}
	public boolean ismakesure(String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select ischecked from optthesisdata where studentid="+studentid;
		Query query=session.createQuery(hql);
		ArrayList<String> data1=(ArrayList<String>) query.list();
		
		for(String data:data1) {
		if(data!=null&&data.equals("yes")) {
			session.close();
			return true;
		}}
		session.close();
		return false;
		
	}
	public int querythesisid(String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select thesisid from optthesisdata where ischecked='yes' and studentid="+studentid;
		int a=0;
		Query query=session.createQuery(hql);
		if(query==null) {
			return 0;
		}else {
		ArrayList<Integer> data1=(ArrayList<Integer>) query.list();
		
	
		for(int a1:data1) {
			a=a1;
			
		}
		session.close();
		}
		session.close();
		return a;
		
	}
	public String querystudentid(int thesisid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select studentid from optthesisdata where thesisid="+thesisid;
		Query query=session.createQuery(hql);
		
		ArrayList<String> data1=(ArrayList<String>) query.list();
		session.close();
		return data1.get(0);
		
	}
	/*public optthesisdata querydata(String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		optthesisdata data=session.get(optthesisdata.class, studentid);
		session.close();
		return data;
		
		
		
	}*/
	public void setchecked(int thesisid,String checked) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		optthesisdata datademo=session.get(optthesisdata.class, thesisid);
		datademo.setIschecked(checked);
		session.update(datademo);
		session.flush();
		transaction.commit();
		
		session.close();
	}
	public String titleforstuid(String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select thesistopic,ischecked from optthesisdata where studentid="+studentid;
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<Object[]> data=(ArrayList<Object[]>) query.list();
		for(Object[] a:data) {
			if(((String)a[1]).equals("yes")) {
				return (String)a[0];
				
			}
		}
		return null;
	}
	public String teacherforstuid(String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select thesisteacher,ischecked from optthesisdata where studentid="+studentid;
		Query query=session.createQuery(hql);
		if(query==null) {
			return null;
		}
		ArrayList<Object[]> data=(ArrayList<Object[]>) query.list();
		for(Object[] a:data) {
			if(((String)a[1]).equals("yes")) {
				return (String)a[0];
				
			}
		}
		return null;
	}
	public boolean idforchecked(String studentid) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session=sessionfactory.openSession();
		String hql="select ischecked from optthesisdata where studentid="+studentid;
		Query query=session.createQuery(hql);
		
		ArrayList<String> data=(ArrayList<String>) query.list();
		for(String a:data) {
			if(a.equals("wtq")) {
				session.close();
				return false;
				
			}
		}
		
		session.close();
		return true;
		
	}
	
}
