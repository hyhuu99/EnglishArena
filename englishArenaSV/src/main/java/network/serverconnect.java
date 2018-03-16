package network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import GUI.main;
import GUI.serverform;

public class serverconnect extends Thread {
	
		public ServerSocket listener = null;  
		static String nhan=null;
		public int clientNumber = 0;
		public Socket socket = null;
		int port=6788;
		public static int souser=0;
	
		@Override
		public void run()
		{
						  
			    try {
			           this.listener = new ServerSocket(port);
			           System.out.println("Server is waiting to accept user...");
			           serverform.textArea.append("Máy chủ đang chạy ở port:  " +this.port+   "\n");		
			       
			    } catch (IOException e) {
			           System.out.println(e);
			           System.exit(1);
			       }	 			      
			
			 try {
		           while (true) {
		        	   this.socket = listener.accept();
		        	   souser++;
		        	   System.out.println("Có user kết nối "+this.socket.getPort()+ "\n");
		        	   serverform.textArea.append("Có user kết nối "+this.socket.getPort()+":"+souser +"\n" );
		        	   main.guif.lbuo.setText(String.valueOf(souser));
		        	   mainserver msv=new mainserver(this.socket);
		        	   msv.start();
		           }
		       } 
	            catch (IOException e) {
				e.printStackTrace();
			}
			 try {
	             Thread.sleep(530);
	         } catch (InterruptedException e) {
	         }
			 finally 
			 {
		           try 
		           {
					listener.close();
				    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		public void close() throws IOException
		{
			try {
				listener.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
		       

			 
		


