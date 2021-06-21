/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;
import java.sql.*;
import Database.Connect;
import java.awt.Component;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author vulee
 */
public class EMPLOYEE {
    
     private Component frame;    
    public Connect con= new Connect();
    
    public EMPLOYEE(){}; //constructor
    
    public ResultSet getAllEMPLOYEE() throws SQLException{
        con.connectSQL();
        String sql = "SELECT * FROM EMPLOYEE";
        return con.LoadData(sql);
    }
   
    //Theo moi 1 dong du lieu vao table 
    public void insertEmployee( String ten, String taikhoan, String password, String phone, String chucvu, String hinhanh) throws SQLException{
        String check_existed = "SELECT * FROM EMPLOYEE WHERE Username = N'" + taikhoan + "'";
        if (con.LoadData(check_existed).isBeforeFirst()) {
            JOptionPane.showMessageDialog(frame,"Đã tồn tại tên tài khoản \"" + taikhoan + "\" trong danh sách.","Thông báo",JOptionPane.WARNING_MESSAGE);
        }
        else{
            String sql = "INSERT INTO EMPLOYEE values(N'" + ten + "','" + taikhoan +"',N'" + password +"',N'" + phone + "',N'" + chucvu + "',N'" + hinhanh + "')";
            con.UpdateData(sql);
            JOptionPane.showMessageDialog(frame,"Đã thêm nhân viên \"" + ten + "\" vào danh sách.","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    //Dieu chinh 1 dong du lieu vao table 
    public void editEmployee(Object id, String ten, String taikhoan, String phone, String chucvu, String hinhanh) throws SQLException{
        String sql = "Update EMPLOYEE set Fullname=N'" + ten +"', Username = '" + taikhoan + ", Phone =N'" + phone + "', Role =N'" + chucvu + "',Image =N'" + hinhanh + "' where IdEmployee='" + id +"'";
        con.UpdateData(sql);      
    }
    
    //Xoa 1 dong du lieu vao table 
    public void deleteEmployee(Object id) throws SQLException{
        String sql = "Delete from EMPLOYEE where IdEmployee='" + id +"'";
        con.UpdateData(sql);  
    }

   
  

}
