package BUS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.Cauhoi;
import DTO.Cautraloi;
import DTO.Ctdiem;
import DTO.Taikhoan;
import network.data;
import network.mainserver;
import network.serverconnect;
import network.thongtin;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class quanlytrochoi_BUS {
	static int souser=serverconnect.souser;
	static List<String> users=new LinkedList<>();
	static Timer timer = new Timer();
	public static List<Ctdiem> loaddiem(Ctdiem ctd)
	{
		return DAO.Ctdiem_DAO.dsctd(ctd);
	}
	public static int themdiem(data mess)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date(); 
    	Taikhoan tk=BUS.quanlytaikhoan_BUS.thongtintk(mess.data[1]);
    	Ctdiem ctd=new Ctdiem();
		ctd.setTaikhoan(tk);
		ctd.setSodiem(Integer.parseInt(mess.data[2]));
	    ctd.setEmail(mess.data[0]);
	    ctd.setKetqua(Integer.parseInt(mess.data[3]));
	    ctd.setNgay(date);	    
		return DAO.Ctdiem_DAO.them(ctd);
	}
	public static List<Cauhoi> load13(int size)
	{
	        List<Integer> list = new ArrayList<Integer>(size);
	        List<Integer> mach = new ArrayList<Integer>(size);
	        for(int i = 1; i <= size; i++) {
	            list.add(i);
	        }
	        Random rand = new Random();
	        for(int i = 1; i <= 14; i++) {
	        	int index = rand.nextInt(list.size());
	            mach.add(index);
	            list.remove(index);
	        }	       
		return DAO.Cauhoi_DAO.load13(mach);
	}
	public static List<Cautraloi> dapan(List<Cauhoi> ch)
	{
		return DAO.Cautraloi_DAO.dapan(ch);			
	}
	public static int tim(ArrayList<thongtin> artt,String email)
	{	
					  for(int j=0;j<artt.size();j++)
			    		{
			    			if(artt.get(j).email.equals(email))
			    			{
			    				return j;
			    			}	 
			    						    			
			    		}   				  			  			  			
		return -1;
	}
	
			

}
