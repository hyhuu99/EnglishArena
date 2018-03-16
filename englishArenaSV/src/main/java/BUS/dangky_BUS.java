package BUS;
import java.util.ArrayList;
import DAO.Taikhoan_DAO;

public class dangky_BUS {
	public static int dangky(String email,String mk)
	{
		if(DAO.Taikhoan_DAO.kiemtratendn(email)==1)
		{
			return DAO.Taikhoan_DAO.dangky(email,mk);			
		}
		else return -1;
			
	}

}
