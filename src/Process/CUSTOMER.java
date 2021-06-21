/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Database.Connect;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class CUSTOMER {
    private Component frame;    
    public Connect con= new Connect();
    
    public CUSTOMER(){}; //constructor
    
        public ResultSet getAllCustomer() throws SQLException{
        con.connectSQL();
        String sql = "SELECT * FROM CUSTOMER";
        return con.LoadData(sql);
    }
        
    //Theo moi 1 dong du lieu vao table 
    public void insertCustomer(String ten, String sdt, String mota) throws SQLException{
        String check_existed = "SELECT * FROM CUSTOMER WHERE PhoneCustomer = N'" + sdt + "'";
        if (con.LoadData(check_existed).isBeforeFirst()) {
            JOptionPane.showMessageDialog(frame,"Đã tồn tại khách hàng \"" + sdt + "\" trong danh sách.","Thông báo",JOptionPane.WARNING_MESSAGE);
        }
        else{
            String sql = "INSERT INTO CUSTOMER values(N'" + ten + "','" + sdt +"',N'" + mota +"')";
            con.UpdateData(sql);
            JOptionPane.showMessageDialog(frame,"Đã thêm khách hàng \"" + ten + "\" vào danh sách.","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //Dieu chinh 1 dong du lieu vao table 
    public void editCustomer(Object id, String ten, String sdt, String mota) throws SQLException{
        String sql = "Update CUSTOMER set FullnameCustomer=N'" + ten +"', PhoneCustomer = '" + sdt + "', NoteCustomer =N'" + mota + "' where IdCustomer='" + id +"'";
        con.UpdateData(sql);      
    }
    
    //Xoa 1 dong du lieu vao table 
    public void deleteCustomer(Object id) throws SQLException{
        String sql = "Delete from CUSTOMER where IdCustomer='" + id +"'";
        con.UpdateData(sql);  
    }
    
    //Kiem tra so
    public boolean isInteger(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
