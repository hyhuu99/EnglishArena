package network;
import java.io.Serializable;
import java.util.ArrayList;

public class data implements Serializable{
    public String action = null;
    public String[] data;
    public ArrayList<String[]> data_arr;
    public boolean accept = false;
    public int login=-1;
    public int dk=0;
    public data(){
        
    }
    public data(String action,String[] data, ArrayList<String[]> data_arr) {
        this.action = action;
        this.data = data;
        this.data_arr = data_arr;
    }
    
}