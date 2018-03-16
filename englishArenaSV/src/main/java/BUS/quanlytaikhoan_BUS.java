package BUS;
import java.util.List;
import DAO.Taikhoan_DAO;
import DTO.Taikhoan;
public class quanlytaikhoan_BUS {
	public static Taikhoan thongtintk(String email)
	{
		return DAO.Taikhoan_DAO.thongtintk(email);
	}
	

}
