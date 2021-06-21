/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import java.sql.*;
import Database.Connect;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class PRODUCT {

    private Component frame;
    public Connect con = new Connect();

    public PRODUCT() {
    }

    ; //constructor
    
    public ResultSet getAllProduct() throws SQLException {
        con.connectSQL();
        String sql = "SELECT * FROM PRODUCT";
        return con.LoadData(sql);
    }

    //Theo moi 1 dong du lieu vao table 
    public void insertProduct(String ten, String gia, String mota) throws SQLException {
        String check_existed = "SELECT * FROM PRODUCT WHERE NameProduct = N'" + ten + "'";
        if (con.LoadData(check_existed).isBeforeFirst()) {
            JOptionPane.showMessageDialog(frame, "Đã tồn tại đồ uống \"" + ten + "\" trong danh sách.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            String sql = "INSERT INTO PRODUCT values(N'" + ten + "','" + Integer.parseInt(gia) + "',N'" + mota + "')";
            con.UpdateData(sql);
            JOptionPane.showMessageDialog(frame, "Đã thêm đồ uống \"" + ten + "\" vào danh sách.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Dieu chinh 1 dong du lieu vao table 
    public void editProduct(Object id, String ten, String gia, String mota) throws SQLException {
        String sql = "Update PRODUCT set NameProduct=N'" + ten + "', PriceProduct = '" + Integer.parseInt(gia) + "', NoteProduct =N'" + mota + "' where IdProduct='" + id + "'";
        con.UpdateData(sql);
    }

    //Xoa 1 dong du lieu vao table 
    public void deleteProduct(Object id) throws SQLException {
        String sql = "Delete from BILL_INFO WHERE IdProduct= " + id;
        con.UpdateData(sql);
        sql = "Delete from PRODUCT where IdProduct='" + id + "'";
        con.UpdateData(sql);

    }

//Kiem tra so
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //tim kiem product
    public int findIdProduct(String name) {
        try {
            ResultSet result = getAllProduct();
            while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                if (result.getString(2).equals(name)) {
                    return result.getInt(1);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PRODUCT.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    //Tim kiem
    public ResultSet searchProductsByKeyword(String keyword) throws SQLException {
        con.connectSQL();
        String sql =    "SELECT *\n" +
                        "FROM DBO.PRODUCT p\n" +
                        "WHERE LOWER(p.NameProduct) LIKE N'%" + keyword.toLowerCase() + "%'";
        return con.LoadData(sql);
    }
}
