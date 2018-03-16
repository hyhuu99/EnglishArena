package DAO;
import java.util.Collection;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;
import DTO.HibernateUtils;
import DTO.Cauhoi;
import DTO.Cautraloi;

public class Cautraloi_DAO {
	public static List<Cautraloi> dapan(List<Cauhoi> ch)
	{
		  SessionFactory factory = HibernateUtils.getSessionFactory();	 
	      Session session = factory.getCurrentSession();
	      List<Cautraloi> ctl=null;
	      try {    	  
	          session.getTransaction().begin(); 
	          String sql = "Select ctl  from " + Cautraloi.class.getName() + " ctl "
	        		  + " Where ctl.cauhoi in :mach ";	          
	          Query<Cautraloi> query = session.createQuery(sql);  
	          query.setParameterList("mach",ch);	         
	          ctl = query.getResultList();        	          
	          session.getTransaction().commit();
	          session.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	      }
		return ctl;
	}

}
