package DAO;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;
import DTO.HibernateUtils;
import DTO.Cauhoi;
import DTO.Ctdiem;

public class Cauhoi_DAO {
	public static List<Cauhoi> load13(List<Integer> mach)
	{
		  SessionFactory factory = HibernateUtils.getSessionFactory();	 
	      Session session = factory.getCurrentSession();
	      List<Cauhoi> ch=null;
	      try
	      {
	    	  session.getTransaction().begin();
	    	  String sql = "Select ch  from " + Cauhoi.class.getName() + " ch "
	        		  + " where ch.maCh in :mach ";	   
	    	  Query<Cauhoi> query=session.createQuery(sql);
	    	  query.setParameterList("mach", mach);
	    	  //query.setMaxResults(13);
	    	  ch=query.getResultList();
	    	  session.getTransaction().commit();
	    	  session.close();
	      }
	      catch(Exception e)
	      {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	      }
	      return ch;
	      
	      
	}

}
