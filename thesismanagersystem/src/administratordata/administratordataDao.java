package administratordata;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class administratordataDao {
	public administratordata querypassword(String id) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session =sessionfactory.openSession();
		administratordata data=(administratordata)session.get(administratordata.class, id);
		session.close();
		return data;
		
		
	}
	public void updatestartyes(String id) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session =sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		administratordata data=(administratordata)session.get(administratordata.class, id);
		data.setStart("yes");
		session.update(data);
		transaction.commit();
		session.close();
		
		
		
	}
	public void updatestartno(String id) {
		Configuration configuration=new Configuration();
		configuration.configure("/dataconfig.cfg.xml");
		SessionFactory sessionfactory=configuration.buildSessionFactory();
		Session session =sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		administratordata data=(administratordata)session.get(administratordata.class, id);
		data.setStart("no");
		session.update(data);
		transaction.commit();
		session.close();
		
		
		
	}

}
