package network;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.*;
import DTO.*;
import GUI.main;
import BUS.*;


public class mainserver extends Thread {
	private Socket socket = null;
	ObjectInputStream in = null;
    ObjectOutputStream out = null;
    Boolean running = true;
    public thongtin tt=null;
    // mảng để ghép đôi
    public static ArrayList<thongtin> artt=new ArrayList<>();
    int vt=-1;
    private List<Cauhoi> lch=null;
    private int ID;
    public mainserver(Socket socket) {
    this.socket = socket;
    try {
        this.out = new ObjectOutputStream(this.socket.getOutputStream());
    } catch (IOException ex) {
        Logger.getLogger(mainserver.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @Override
    public void run(){
    	//vi tri mang 
        
        data mess = null;
        data dtsent = new data();
        try {
            while(running){
                    in  = new ObjectInputStream(this.socket.getInputStream());
                    mess=(data) in.readObject();
                    //System.out.println(mess.action);
                    switch(mess.action){
                    case "login":              
                    {                  	
                    	dangnhap(mess,dtsent);
                    	break;                   	
                    } 
                    case "dangky":
                    {
                    	dangky(mess,dtsent);
                    	break;
                    }
                    case "ghepdoi":
                    {               	
                    	this.ghepdoi(mess, dtsent);
                    	break;
                    }
                    case "sansang":
                    {
                    	//System.out.println("email: " +mess.data[0]);
                    	int vtt=BUS.quanlytrochoi_BUS.tim(network.artt, mess.data[0]);
                    	//thongtin tttemp=new thongtin(artt.get(vtt).email,artt.get(vtt).socket,0);                  	
                    	data dt_sent=new data();
                    	String[] s=new String[1];
                    	s[0]="dasansang";                  	
                    	dt_sent.action="sansang";
                    	dt_sent.data=s;
                    	//System.out.println("da gui thong tin: " +vtt);
                    	network.artt.get(vtt).cout.writeObject(dt_sent);
                    	// gửi câu hỏi và câu trả lời
                    	//network.artt.get(vtt).cout.flush();                    	
                    	break;
                    }
                    case "loadcauhoi":
                    {
                    	// load cau hoi
                    	loadch13();
                    	//gui cho client 1
                    	dtsent.action="loadcauhoi";
                    	dtsent.data_arr=loadcauhoi();
                    	this.gui(dtsent);
                    	//gui cho client 2
                    	int vtt=BUS.quanlytrochoi_BUS.tim(network.artt, mess.data[0]);
                    	data dt_sent=new data();          	
                    	dt_sent.action="loadcauhoi";
                    	dt_sent.data_arr=loadcauhoi();
                    	network.artt.get(vtt).cout.writeObject(dt_sent);
                    	// gui luon cau tra loi
                    	dtsent.action="loadcautl";
                    	dtsent.data_arr=loadcautl();
                    	this.gui(dtsent);
                    	//gui cau tl cho client 2
                    	data dt_sent2=new data();  
                    	dt_sent2.action="loadcautl";
                    	dt_sent2.data_arr=loadcautl();
                    	network.artt.get(vtt).cout.writeObject(dt_sent2);
                    	break;
                    	
                    }
                    case "traloitruoc":
                    {
                    	this.traloitruoc(mess,dtsent);
                    	break;
                    }
                    case "traloisau":
                    {
                    	this.traloisau(mess,dtsent);
                    	break;
                    }
                    case "traloibang":
                    {
                    	this.traloibang(mess, dtsent);
                    	break;
                    }
                    case "dangxuat":
                    {
                    	 vt=BUS.quanlytrochoi_BUS.tim(network.artt, mess.data[1]);  
                    	 this.dangxuat(vt, mess);
                    	 break;
                    }
                    case "themdiem":
                    {
                    	this.themdiem(mess, dtsent);
                    	break;                	
                    }   
                    case "thachdau":
                    {
                    	this.thachdau(mess, dtsent);
                    	break;                	
                    }    
                    case "ketquamoitd":
                    {
                    	this.ketquamoitd(mess, dtsent);
                    	break;
                    }
                    case "gohome":
                    {
                    	this.gohome(mess, dtsent);
                    	break;
                    }
                    default:
                    {
                    	
                    }
                    }
             }
        }
        catch (IOException | ClassNotFoundException ex) {
            //this.user_disconnect();
            System.out.println("user disconnect 1");
            Logger.getLogger(mainserver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
            	 if(vt==-1)
            	 {
            		 if(in!=null)
            		 {
            			 in.close();
                         this.user_disconnect();
                         dangxuat2();
                         //System.out.println("user disconnect 3");
                         serverconnect.souser--;
                         main.guif.lbuo.setText(String.valueOf(serverconnect.souser));                         
            		 }
            		 else
            		 {            			 
            			 serverconnect.souser--;
            			 main.guif.lbuo.setText(String.valueOf(serverconnect.souser));
            		 }
            	 }
            	 else
            		 System.out.println("da ngat ket noi");
                
            } catch (IOException ex) {
                Logger.getLogger(mainserver.class.getName()).log(Level.SEVERE, null, ex);
            }
        	//System.out.println("da ngat ket noi thanh cong");
        	
      }
    }
  //gui du lieu toi client
    private void gui(data dtsent){
        try {
        	out.reset();
            out.writeObject(dtsent);
            out.flush();
            //System.out.println("gui thanh cong");
        } catch (IOException ex) {
            Logger.getLogger(mainserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void gui_userkhac(data dtsent,int vtt){
        try {
        	network.artt.get(vtt).cout.reset();
			network.artt.get(vtt).cout.writeObject(dtsent);	//gui user kia
			network.artt.get(vtt).cout.flush();
            //System.out.println("gui thanh cong");
        } catch (IOException ex) {
            Logger.getLogger(mainserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void user_disconnect(){
        this.running = false; // terminal thread
    }
    // function đăng nhập
    private void dangnhap(data mess,data dtsent)
    {
    	float vs=Float.parseFloat(mess.data[2]);
    	int ktpb=BUS.dangnhap_BUS.ktphienban(vs);
    	if(BUS.dangnhap_BUS.checkdangnhap(network.artt, mess.data[0])==1)
    	{
    		tt=new thongtin(mess.data[0],this.socket,-1);   
    		tt.set_id(serverconnect.souser-1);
    		this.ID=serverconnect.souser-1;
    		// kiểm tra phiên bản
    		if(ktpb==1)
    		{
    			dtsent.login=BUS.dangnhap_BUS.dn(mess.data[0], mess.data[1]);  
    		}
    		else
    			dtsent.login=-2;
    			                     	
    		dtsent.action=mess.action;
        	if(dtsent.login==1||dtsent.login==2)
        	{
        		ArrayList<String[]> arr_data = new ArrayList<>();
        		Taikhoan tk=BUS.quanlytaikhoan_BUS.thongtintk(mess.data[0]);
        		Ctdiem ctd=new Ctdiem();
        		ctd.setTaikhoan(tk);
        		List<Ctdiem> lctd=DAO.Ctdiem_DAO.dsctd(ctd);                        		
        		for(Ctdiem ctds:lctd)
            	{
        			String[] temp=new String[4];
        			temp[0]=ctds.getEmail();
        			temp[1]=ctds.getNgay().toString();                      			         			
        			temp[2]=Integer.toString(ctds.getSodiem());  
        			if(ctds.getKetqua()==1)
        				temp[3]="Thắng";
        			else
        				temp[3]="Bại";               
        			arr_data.add(temp);                       			
            	}
        		dtsent.data_arr=arr_data;  
        		tt.cout=out;
        		network.artt.add(tt);    
        		artt.add(tt);
        	}
        	String[] s=new String[2];
    		s[0]="link pb moi:";
    		s[1]=" ";	
    		dtsent.data=s;
            this.gui(dtsent);                                                       
    	}        
    	else
    	{
    		dtsent.action=mess.action;
    		dtsent.login=-1;
        	this.gui(dtsent);
        	//System.out.println("trung ");
    	}   		                      
        /*data fun=new data();
        fun.action="fun";
        String[] fundata = new String[2];
        fundata[0]="abc ";
        fun.data= fundata;
        this.sendtoclient(fun);*/
    }
    private void loadch13()
    {
    	lch=BUS.quanlytrochoi_BUS.load13(61);
    }      
    private ArrayList<String[]> loadcauhoi()
    {
    	ArrayList<String[]> arr_data = new ArrayList<>();   	
    	int i=0;
    	for(Cauhoi chs:lch)
    	{
			String[] temp=new String[3];
			temp[0]=chs.getMaCh().toString();
			temp[1]=chs.getNoiDung();	
			temp[2]=Integer.toString(chs.getCapDo());
			arr_data.add(temp);                       			
    	}
    	return arr_data;	
    }
    
    private ArrayList<String[]> loadcautl()
    {
    	ArrayList<String[]> arr_data = new ArrayList<>();   
    	List<Cautraloi> lctl=BUS.quanlytrochoi_BUS.dapan(lch);
    	for(Cautraloi ctls:lctl)
    	{
			String[] temp=new String[3];
			temp[0]=ctls.getCauhoi().getMaCh().toString();
			temp[1]=ctls.getNoiDung();
			temp[2]=Integer.toString(ctls.getKetQua());
			arr_data.add(temp);                       			
    	}
    	return arr_data;
  	
    }
    private void dangky(data mess,data dtsent)
    {
    	dtsent.dk=BUS.dangky_BUS.dangky(mess.data[0], mess.data[1]);
    	dtsent.action=mess.action;
    	this.gui(dtsent); 
    }
    private void ghepdoi(data mess,data dtsent)
    {
    	thongtin tt2 =new thongtin(mess.data[0],socket,1);
    	String[] s=new String[2];
    	for(int i=0;i<artt.size();i++)
    	{
    	   if(artt.get(i).email.equals(mess.data[0]))
    	   {
    		   artt.set(i, tt2);        
    		   vt=i;
    	   }
    	}
      long startTime = System.currentTimeMillis();	 
		  while((System.currentTimeMillis()-startTime)<10000)
		  {
			  int result=0;   
			  for(int j=0;j<artt.size();j++)
	    		{
	    			if(artt.get(j).trangthai==1&&!artt.get(j).email.equals(mess.data[0]))
	    			{
	    				thongtin tt3 =new thongtin(artt.get(j).email,artt.get(j).socket,0);
	    				s[0]=network.artt.get(j).email;//System.out.println(artt2.get(j).email+" "+artt2.get(j).trangthai);    	    			      			    				
	    			    s[1]=mess.data[0];
	    			    artt.set(j, tt3);
	    			    result=1;
	    				break;
	    			}
	    		}   
			  if(result==1)
				  break;
		  }	
		 
    	if(s[1]==null)
    	{
    		s[1]="Hiện không có người online, vui lòng thử bắt đầu lại";
    		dtsent.data=s;
        	dtsent.action=mess.action;       	
    	}
    	else
    	{
    		dtsent.data=s;
        	dtsent.action=mess.action;        	
    	}                   	 
    	this.gui(dtsent);
    }
	private void traloitruoc(data mess,data dtsent)
    {
    	String[] data1=new String[6];
    	//int giay=Integer.parseInt(mess.data[2]);
    	//int ms=Integer.parseInt(mess.data[3]);
    	//String sbd= new StringBuilder().append(mess.data[2]).append(",").append(mess.data[3]).toString();
    	long startTime=System.currentTimeMillis();
    	String time=String.valueOf(startTime);
    	int vtt=BUS.quanlytrochoi_BUS.tim(network.artt, mess.data[0]);
    	data1[0]=mess.data[0];
    	data1[1]=mess.data[1];
    	data1[2]=mess.data[2];
    	data1[3]=mess.data[3];
    	data1[4]=mess.data[4];
    	data1[5]=time;
    	//System.out.println(data1[0]+data1[1]+data1[2]+data1[3]+data1[4]+data1[5]);
    	dtsent.data=data1;
    	dtsent.action=mess.action;
    	this.gui_userkhac(dtsent, vtt); 	   	
    }
    private void traloisau(data mess,data dtsent)
    {
    	int vtt=BUS.quanlytrochoi_BUS.tim(network.artt, mess.data[0]);
    	int vtt2=BUS.quanlytrochoi_BUS.tim(network.artt, mess.data[1]);
    	int diem=Integer.valueOf(mess.data[9]);
    	int diem1=0,diem2=0;// 2 la tra loi truoc, 1 la tra loi sau
    	if(mess.data[4].equals("1")&&mess.data[7].equals("1"))
    	{
    	
    		diem2=diem;
    		diem1=0;
    	}
    	else
    		if(mess.data[4].equals("0")&&mess.data[7].equals("1"))
    		{
    			diem2=diem;
        		diem1=0;
    		}
    		else
    			if(mess.data[4].equals("1")&&mess.data[7].equals("0"))
    			{
    				diem1=diem;
            		diem2=0;
    			}
    	int giay=Integer.parseInt(mess.data[2]);
    	int ms=Integer.parseInt(mess.data[3]);
    	int giaytraloisau=10-giay;
    	int mstraloisau=100-ms;
    	StringBuilder straloisau= new StringBuilder().append(giaytraloisau).append(",").append(mstraloisau);
    	int giay2=Integer.parseInt(mess.data[5]);
    	int ms2=Integer.parseInt(mess.data[6]);
    	int giaytraloitruoc=10-giay2;
    	int mstraloitruoc=100-ms2; 	
    	StringBuilder straloitruoc= new StringBuilder().append(giaytraloitruoc).append(",").append(mstraloitruoc);
    	ArrayList<String[]> arr_data = new ArrayList<>();
    		String[] traloitruoc= new String[3];
    		traloitruoc[0]=mess.data[0];//
    		traloitruoc[1]=straloitruoc.toString();
    		traloitruoc[2]=String.valueOf(diem2);
    	arr_data.add(traloitruoc);
    	String[] traloisau= new String[3];
		traloisau[0]=mess.data[1];//
		traloisau[1]=straloisau.toString();
		traloisau[2]=String.valueOf(diem1);
		arr_data.add(traloisau);
		dtsent.action=mess.action;
		dtsent.data_arr=arr_data;
		data dtsent2=new data();
		dtsent2=dtsent;      
			this.gui_userkhac(dtsent, vtt);						
			this.gui(dtsent2);
    }
    private void traloibang(data mess,data dtsent)
    {
    	int vtt=BUS.quanlytrochoi_BUS.tim(network.artt, mess.data[0]);
    	int vtt2=BUS.quanlytrochoi_BUS.tim(network.artt, mess.data[1]);
    	ArrayList<String[]> arr_data = new ArrayList<>();
		String[] traloitruoc= new String[3];
		traloitruoc[0]=mess.data[0];//
		traloitruoc[1]="0";
		traloitruoc[2]="0";
		arr_data.add(traloitruoc);
		String[] traloisau= new String[3];
		traloisau[0]=mess.data[1];//
		traloisau[1]="0";
		traloisau[2]="0";
		arr_data.add(traloisau);
		dtsent.action=mess.action;
		dtsent.data_arr=arr_data;
		this.gui(dtsent);
    }
    private void messout(String mess)
    {
    	System.out.println("mess: "+mess);
    }
    private void dangxuat(int vt,data datat)
    {
    	 if(datat.data[2].equals("huythidau"))
    	 {
    		 int vtt=BUS.quanlytrochoi_BUS.tim(network.artt, datat.data[0]);
    		 thongtin tt2=new thongtin(datat.data[1],socket,-1);		
    	    	for(int i=0;i<artt.size();i++)
    	    	{
    	    	   if(artt.get(i).email.equals(datat.data[1]))
    	    	   {
    	    		   artt.set(i, tt2);        
    	    	   }
    	    	}	    	
    	    	for(int i=0;i<artt.size();i++)
    	    	{
    	    	   if(artt.get(i).email.equals(datat.data[0]))
    	    	   {
    	    		   thongtin tt3=new thongtin(datat.data[0],artt.get(i).socket,-1);
    	    		   artt.set(i, tt3);        
    	    	   }
    	    	}
    		 data dt_sent=new data();
    		 dt_sent.action="huythidau";
    		 String[] s=new String[1];
    		 s[0]="Người chơi đã hủy thi đấu";
    		 dt_sent.data=s;
    		 try {
				network.artt.get(vtt).cout.writeObject(dt_sent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }
    	 else
    	 {
    		 try
    		 {
    			 int a= BUS.dangnhap_BUS.ngatketnoi(network.artt, vt);                    	 
            	 int b= BUS.dangnhap_BUS.ngatketnoi(artt, vt);
            	 if(a==1)
                 {                	  
            		  in.close(); 
                      this.user_disconnect();
                      System.out.println("user disconnect 2" +b);
                      //System.out.println(tt.email+" "+tt.trangthai+" "+tt.socket);
                      serverconnect.souser--;
                      main.guif.lbuo.setText(String.valueOf(serverconnect.souser));
                 }
                 else
                 {
                	 in.close();
                 	 System.out.println("chua ngat ket noi");
                 	 serverconnect.souser--;
                 	main.guif.lbuo.setText(String.valueOf(serverconnect.souser));
                 }
    		 }				 catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		
    	 }
    	
    }
    private void themdiem(data mess,data dtsent)
    {
    	try
    	{
    		int dk=BUS.quanlytrochoi_BUS.themdiem(mess);
        	dtsent.action="themdiem";
        	this.gui(dtsent);
    	}
    	catch (Exception ex) {
            Logger.getLogger(mainserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
    private void thachdau(data mess,data dtsent)
    {
    	try
    	{
    		String[] s=new String[2];
        	int vtt=BUS.quanlytrochoi_BUS.tim(network.artt, mess.data[0]);
        	for(int i=0;i<artt.size();i++)
        	{
        	   if(artt.get(i).email.equals(mess.data[0])&&artt.get(i).trangthai==-1)
        	   {
        		   thongtin tt3 =new thongtin(artt.get(i).email,artt.get(i).socket,0);
        		    s[0]=artt.get(i).email; 	    			      			    				
    			    s[1]=mess.data[1];
        		   artt.set(i, tt3);    		
        		   break;
        	   }
        	}
        	if(s[1]==null)
        	{
        		s[1]="Hiện người chơi không online, vui lòng thử lại sau ít phút";
        		dtsent.data=s;
            	dtsent.action=mess.action;    
            	this.gui(dtsent);
        	}
        	else
        	{	
        		chuyentrangthai(mess.data[1],socket,0);
        		dtsent.data=s;
            	dtsent.action=mess.action;  
            	this.gui_userkhac(dtsent, vtt);
        	}                   	 
    	}
    	catch (Exception ex) {
            Logger.getLogger(mainserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
    private void ketquamoitd(data mess,data datat)
    {
    	int vtt=BUS.quanlytrochoi_BUS.tim(network.artt, mess.data[1]);
    	if(mess.data[2].equals("1"))
    	{
    		String[] s=new String[3];
    		s[0]=mess.data[0];
    		s[1]=mess.data[1];
    		s[2]="1";
    		datat.data=mess.data;
    		datat.action="ketquamoitd";
    		this.gui_userkhac(datat, vtt);
    	}
    	else
    	{
    		String[] s=new String[3];
    		s[0]="Người chơi từ chối thách đấu! :D";
    		s[1]="0";
    		s[2]="0";
    		datat.data=s;
    		datat.action=mess.action;
    		this.gui_userkhac(datat, vtt);
    	}
    }
    private void chuyentrangthai(String email,Socket sk,int tt)
    {
    	thongtin tt2=new thongtin(email,sk,tt);		
	    	for(int j=0;j<artt.size();j++)
	    	{
	    	   if(artt.get(j).email.equals(email))
	    	   {
	    		   artt.set(j, tt2);     
	    		   break;
	    	   }
	    	}
    }
    private void gohome(data mess,data dtsent)
    {
    		ArrayList<String[]> arr_data = new ArrayList<>();
    		Taikhoan tk=BUS.quanlytaikhoan_BUS.thongtintk(mess.data[0]);
    		Ctdiem ctd=new Ctdiem();
    		ctd.setTaikhoan(tk);
    		List<Ctdiem> lctd=DAO.Ctdiem_DAO.dsctd(ctd);                        		
    		for(Ctdiem ctds:lctd)
        	{
    			String[] temp=new String[4];
    			temp[0]=ctds.getEmail();
    			temp[1]=ctds.getNgay().toString();                      			         			
    			temp[2]=Integer.toString(ctds.getSodiem());  
    			if(ctds.getKetqua()==1)
    				temp[3]="Thắng";
    			else
    				temp[3]="Bại";               
    			arr_data.add(temp);        
        	}
    		this.chuyentrangthai(mess.data[0], socket, -1);
    		dtsent.data_arr=arr_data;  
    		dtsent.action="gohome";
    		this.gui(dtsent);
    }
    
    private void dangxuat2()
    {
    	for(int i=0;i<network.artt.size();i++)
    	{
    		if(network.artt.get(i).ID==this.ID)
    			network.artt.remove(i);
    	}
    }
    
}

	


