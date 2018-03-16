package BUS;
import java.util.ArrayList;
import network.mainserver;
import network.thongtin;
import DAO.Taikhoan_DAO;
public class dangnhap_BUS {
	public static int dn(String email,String mk)
	{
		return DAO.Taikhoan_DAO.login(email, mk);
	}
	public static int checkdangnhap(ArrayList<thongtin> artt,String tendn)
	{		
		 for (thongtin tt : artt)
		 {
			 if(tendn.equals(tt.email))
				 return 0;
		 }
		return 1;
	}
	public static int ktphienban(float vs)
	{
		float version=(float) 1.0;
		if(vs==version)
			return 1;
		else 
			return 0;
	}
	public static int ngatketnoi(ArrayList<thongtin> artt,int vt)
	{
		if(artt.remove(vt) != null)
			return 1;
		return 0;
	}

}
