package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class thongtin {
	public Socket socket = null;
	public String email;
	public int trangthai;
	public int ID;
	ObjectOutputStream cout = null;
	public thongtin(String tendn,Socket sk,int tt)
	{
		this.email=tendn;
		this.socket=sk;
		this.trangthai=tt;		
	}
	public void set_id(int i)
	{
		this.ID=i;
	}
	public Socket sk()
	{
		return this.socket;
	}
	public void gui_khac(data datat)
	{
		try
		{
			cout.writeObject(datat);
			cout.flush();
			System.out.println("da gui cho user khac");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
