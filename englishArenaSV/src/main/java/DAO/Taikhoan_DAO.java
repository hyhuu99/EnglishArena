package DAO;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;
import DTO.Cauhoi;
import DTO.HibernateUtils;
import DTO.Taikhoan;

public class Taikhoan_DAO {
	
	public static List<Taikhoan> dstk()
	{		
		  SessionFactory factory = HibernateUtils.getSessionFactory();	 
	      Session session = factory.getCurrentSession();
	      List<Taikhoan> tks=null;
	      try {    	  
	          session.getTransaction().begin(); 
	          String sql = "Select tk  from " + Taikhoan.class.getName() + " tk "; 
	          Query<Taikhoan> query = session.createQuery(sql);
	          tks = query.getResultList();        	          
	          session.getTransaction().commit();
	          session.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	      }
		return tks;
	}
	public static int login (String tendn,String mk)
	{
		  SessionFactory factory = HibernateUtils.getSessionFactory();	 
	      Session session = factory.getCurrentSession();
	      Taikhoan tks=null;
	      try {    	  
	          session.getTransaction().begin(); 
	          String sql = "Select tk  from " + Taikhoan.class.getName() + " tk "
	        		  + " Where tk.email = :tendn and tk.password = :mk";
	          Query<Taikhoan> query = session.createQuery(sql);
	          query.setParameter("tendn", tendn);
	          query.setParameter("mk", mk);
	          tks = query.getSingleResult();      	    	          
	          session.getTransaction().commit();
	          session.close();
	          if(tks!=null)
	          {
	        	  if(tks.getLoaitaikhoan()==1)
	        		  return 2; //admin
	        	  else if(tks.getLoaitaikhoan()==0)
	        		  return 1; //user	        	  
	          }
	          
	      } catch (Exception e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	      }	
	      return 0;
	}
	public static int dangky(String tendn,String mk)
	{
		 SessionFactory factory = HibernateUtils.getSessionFactory();	 
	      Session session = factory.getCurrentSession();
	   
	      try {    	  
	          session.getTransaction().begin();          	          
	          Taikhoan tk=new Taikhoan();	        
	          tk.setEmail(tendn);
	          tk.setPassword(mk);
	          tk.setTrangThai(1);
	          tk.setLoaitaikhoan(0);	          
	          session.save(tk);	         	          	          
	          session.getTransaction().commit();
	          session.close();
	         return 1;
	          
	      } catch (Exception e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	      }	
	      return 0;
	}
	public static int capnhat(int matk,int trangthai)
	{
		 SessionFactory factory = HibernateUtils.getSessionFactory();	 
	      Session session = factory.getCurrentSession();
	      Taikhoan tkupdate=null;
	   
	      try {    	  
	          session.getTransaction().begin();          
	          String sql = "Select tk  from " + Taikhoan.class.getName() + " tk "
	        		  + " Where tk.maTk = :matk";
	          Query<Taikhoan> query = session.createQuery(sql);
	          query.setParameter("matk", matk);
	          tkupdate = query.getSingleResult(); 
	          tkupdate.setLoaitaikhoan(0);  
	          session.flush();
	          session.getTransaction().commit();
	          session.close();
	          return 1;	          
	      } catch (Exception e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	      }	
	      return 0;
	}
	public static int kiemtratendn(String tendn)
	{
		SessionFactory factory = HibernateUtils.getSessionFactory();	 
	      Session session = factory.getCurrentSession();
	      Taikhoan tks=null;
	      try {    	  
	          session.getTransaction().begin(); 
	          String sql = "Select tk  from " + Taikhoan.class.getName() + " tk "
	        		  + " Where tk.email = :tendn";
	          Query<Taikhoan> query = session.createQuery(sql);
	          query.setParameter("tendn", tendn);
	          tks = query.getSingleResult();      	    	          
	          session.getTransaction().commit();
	          session.close();
	          if(tks!=null)
	          {
	        	  return 0; 	  
	          }	         	          
	      } catch (Exception e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	      }	
	      return 1;
	}
	public static Taikhoan thongtintk (String tendn)
	{
		  SessionFactory factory = HibernateUtils.getSessionFactory();	 
	      Session session = factory.getCurrentSession();
	      Taikhoan tks=null;
	      try {    	  
	          session.getTransaction().begin(); 
	          String sql = "Select tk  from " + Taikhoan.class.getName() + " tk "
	        		  + " Where tk.email = :tendn";
	          Query<Taikhoan> query = session.createQuery(sql);
	          query.setParameter("tendn", tendn);
	          tks = query.getSingleResult();      	    	          
	          session.getTransaction().commit();
	          session.close();	         	          
	      } catch (Exception e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	      }	
	      return tks;
	}

}
