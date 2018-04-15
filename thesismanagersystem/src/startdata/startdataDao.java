package startdata;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import administratordata.administratordata;

public class startdataDao {
	public void updatestartyes(int id) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session =sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		startdata data=(startdata)session.get(startdata.class, id);
		data.setStart("yes");
		session.update(data);
		transaction.commit();
		session.close();
		
		
		
	}
	public void updatestartno(int id) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session =sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		startdata data=(startdata)session.get(startdata.class, id);
		data.setStart("no");
		session.update(data);
		transaction.commit();
		session.close();
		
		
		
	}
	public String querystart(int id) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session =sessionfactory.openSession();
	
		startdata data=(startdata)session.get(startdata.class, id);
		
		
		session.close();
		return data.getStart();
		
		
		
	}
}
