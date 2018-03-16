package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import network.data;
import network.mainserver;
import network.serverconnect;
import network.thongtin;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class serverform extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverform frame = new serverform();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	serverconnect sv=new serverconnect();
	int tgcheck=1;
	ObjectOutputStream dout = null;
	public JLabel lbuo;
	public static javax.swing.JTextArea textArea;
	public serverform() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 615, 320);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Server", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 275, 600, -226);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 42, 490, 239);
		panel.add(textArea);
		
		JToggleButton btsv1 = new JToggleButton("Khởi động");
		btsv1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tgcheck==1)
				{	
					sv.start();
					tgcheck=0;
				} else
					try {
						sv.close();
						JOptionPane.showMessageDialog(null,
							    "Đã đóng kết nối");
						tgcheck=1;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
							
		});
		btsv1.setBounds(10, 8, 121, 23);
		panel.add(btsv1);
		
		JButton btnNewButton = new JButton("Test");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*data data_sent = new data();
				data_sent.action="ghepdoi";
				try
				{

					dout=new ObjectOutputStream(mainserver.tt.sk().getOutputStream());
					dout.writeObject(data_sent);
					dout.flush();
					System.out.println(data_sent.action);
					System.out.println("gui");
				}
				catch (IOException ex) {
		            Logger.getLogger(serverform.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null,
		            	    "Lỗi",
		            	    "Lỗi phát sinh",
		            	    JOptionPane.ERROR_MESSAGE);
		            
		        }*/
				
			}
		});
		btnNewButton.setBounds(165, 8, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lbuseronline = new JLabel("User online:");
		lbuseronline.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbuseronline.setBounds(279, 12, 82, 14);
		panel.add(lbuseronline);
		
		lbuo = new JLabel("");
		lbuo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbuo.setBounds(350, 12, 82, 14);
		panel.add(lbuo);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
	}
}
