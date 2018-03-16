package DAO;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;
import DTO.HibernateUtils;
import DTO.Taikhoan;
import DTO.Ctdiem;;
public class Ctdiem_DAO {
	public static List<Ctdiem> dsctd(Ctdiem ctd)
	{		
		  SessionFactory factory = HibernateUtils.getSessionFactory();	 
	      Session session = factory.getCurrentSession();
	      List<Ctdiem> ctds=null;
	      try {    	  
	          session.getTransaction().begin(); 
	          String sql = "Select ctd  from " + Ctdiem.class.getName() + " ctd "
	        		  + " Where ctd.taikhoan = :taikhoan ";
	          Query<Ctdiem> query = session.createQuery(sql);
	          query.setParameter("taikhoan", ctd.getTaikhoan());
	          ctds = query.getResultList();        	          
	          session.getTransaction().commit();
	          session.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	      }
		return ctds;
	}
	public static int them(Ctdiem ctd)
	{
		 SessionFactory factory = HibernateUtils.getSessionFactory();	 
	     Session session = factory.getCurrentSession();
	   
	      try {    	  
	          session.getTransaction().begin();          	          
	          Ctdiem ctd2=new Ctdiem();	        
	          ctd2.setEmail(ctd.getEmail());        
	          ctd2.setSodiem(ctd.getSodiem());
	          ctd2.setTaikhoan(ctd.getTaikhoan());
	          ctd2.setNgay(ctd.getNgay());
	          ctd2.setKetqua(ctd.getKetqua());
	          session.save(ctd2);	         	          	          
	          session.getTransaction().commit();
	          session.close();
	         return 1;
	          
	      } catch (Exception e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	      }	
	      return 0;
	}

}
