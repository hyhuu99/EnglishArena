package englishArenaSV.englishArenaSV;


import org.hibernate.query.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
import DTO.Cautraloi;
import DTO.Cauhoi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.synth.SynthSpinnerUI;

import DTO.HibernateUtils;
import DTO.Taikhoan;
import net.sourceforge.jtds.jdbc.DateTime;
import DTO.Ctdiem;
import DAO.Taikhoan_DAO;
import java.util.Collections;
public class App 
{
    public static void main( String[] args )
    {
    	/*
    	  SessionFactory factory = HibernateUtils.getSessionFactory();	 
	      Session session = factory.getCurrentSession();
	      try {


	          session.getTransaction().begin();
	       
	 
	          String sql = "Select Cauhoi  from " + Cauhoi.class.getName() + " Cauhoi "; 
	          Query<Cauhoi> query = session.createQuery(sql);
	          List<Cauhoi> cauhois = query.getResultList();
	
	        
	          for (Cauhoi emp:cauhois) {
	              System.out.println("Emp: " + emp.getNoiDung() + " : "
	                      + emp.getCapDo());
	          }
	          session.getTransaction().commit();
	          session.close();
	      } catch (Exception e) {
	          e.printStackTrace();

	          session.getTransaction().rollback();
	      }*/
        /*Taikhoan tk= DAO.Taikhoan_DAO.thongtintk("admin@gmail.com");  
             System.out.println("Emp: " + tk.getPassword() + " : "
                     + tk.getEmail());*/
    	/*Taikhoan tk=new Taikhoan();
    	tk.setMaTk(1);
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();  	
        Ctdiem ctd=new Ctdiem();
        ctd.setTaikhoan(tk);
        ctd.setSodiem(5);
        ctd.setEmail("admin@gmail.com");
        ctd.setKetqua(0);
        ctd.setNgay(date);
    	int a=DAO.Ctdiem_DAO.them(ctd);
    	//int a=DAO.Taikhoan_DAO.dangky("hyhuu95@gmail.com", "2891996");
    	//int a=DAO.Taikhoan_DAO.kiemtratendn("hyhuu99@gmail.com");*/
    	/*Taikhoan tk=new Taikhoan();
    	tk.setMaTk(1);
    	Ctdiem ctd=new Ctdiem();
    	ctd.setTaikhoan(tk);
    	List<Ctdiem> lctd=DAO.Ctdiem_DAO.dsctd(ctd);
    	for(Ctdiem ctds:lctd)
    	{
    		System.out.println("list: "+ctds.getEmail()+ctds.getKetqua());
    	}
            /*ArrayList<String[]> arr_data = new ArrayList<>();
     		Taikhoan tk=BUS.quanlytaikhoan_BUS.thongtintk("admin@gmail.com");
     		Ctdiem ctd=new Ctdiem();
     		ctd.setTaikhoan(tk);
     		List<Ctdiem> lctd=DAO.Ctdiem_DAO.dsctd(ctd);                        		
     		for(Ctdiem ctds:lctd)
         	{
     			String[] temp=new String[4];
     			temp[0]=ctds.getEmail();
     			temp[1]=ctds.getNgay().toString();
     			if(ctds.getKetqua()==1)
     				temp[2]="Thắng";
     			else
     				temp[2]="Bại";                        			
     			temp[3]=Integer.toString(ctds.getSodiem());  
     			arr_data.add(temp);
         	}
     		for(String[] s:arr_data)
     		{
     			System.out.println(s[0]+" "+s[1]+" "+s[2]);
     		}*/
    	/*StringBuilder a = new StringBuilder("Hello");
    	StringBuilder b = new StringBuilder("Hello World");
    	b = a;
    	a.append("Java");
    	System.out.println(b);*/
    	List<Cauhoi> ch=BUS.quanlytrochoi_BUS.load13(61);
    	/*int j=0;
    	for(Cauhoi chs:ch)
     	{
    		j++;
 			System.out.println(chs.getMaCh());
     	}
    	List<Cautraloi> ctl=BUS.quanlytrochoi_BUS.dapan(ch);
    	int i=0;
 		for(Cautraloi ctls:ctl)
     	{		
 			i++;
 			System.out.println(ctls.getCauhoi().getMaCh()); 			 			
     	}
 		System.out.println("so cau tra loi: "+i);
 		System.out.println("so cau hoi: "+j);
 		/*Cauhoi ch=new Cauhoi();
 		ch.setMaCh(4);
 		Cautraloi ctlt=new Cautraloi();
 		ctlt.setCauhoi(ch);
 		
 		List<Cautraloi> ctl=DAO.Cautraloi_DAO.dapan(ctlt);
 		for(Cautraloi ctls:ctl)
     	{			
 			System.out.println(ctls.getNoiDung());
     	}*/
    	int giay2=Integer.parseInt("4");
    	int ms2=Integer.parseInt("90");
    	int giaytraloitruoc=10-giay2;
    	int mstraloitruoc=100-ms2;
    	StringBuilder straloitruoc= new StringBuilder().append(giaytraloitruoc).append(",").append(mstraloitruoc);
    	System.out.println(straloitruoc.toString());
    

 		



    }
}
