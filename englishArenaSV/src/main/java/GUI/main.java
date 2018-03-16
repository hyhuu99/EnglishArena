package GUI;
import GUI.serverform;
import javax.swing.JOptionPane;
public class main {
	public static serverform guif=null;
	public static void main(String[] args) {
		
		main process=new main();
		process.init();				
	}
	public void init()
	{
		guif=new serverform();
		guif.setVisible(true);
		
	}


}
