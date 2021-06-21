/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import java.sql.*;
import Database.Connect;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author vulee
 */
public class DISCOUNT {

    private Component frame;
    public Connect con = new Connect();

    public DISCOUNT() {
    }

    ; //constructor
    
    public ResultSet getAllDiscount() throws SQLException {
        con.connectSQL();
        String sql = "SELECT * FROM DISCOUNT";
        return con.LoadData(sql);
    }

    //Theo moi 1 dong du lieu vao table 
    public void insertDiscount(String ten, int giatri) throws SQLException {
        String check_existed = "SELECT * FROM DISCOUNT WHERE CodeDiscount = N'" + ten + "'";
        if (con.LoadData(check_existed).isBeforeFirst()) {
            JOptionPane.showMessageDialog(frame, "Đã tồn tại khuyến mãi \"" + ten + "\" trong danh sách.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            String sql = "INSERT INTO DISCOUNT values(N'" + ten + "','" + giatri + "')";
            con.UpdateData(sql);
            JOptionPane.showMessageDialog(frame, "Đã thêm mã khuyến mãi \"" + ten + "\" vào danh sách.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Dieu chinh 1 dong du lieu vao table 
    public void editDiscount(Object id, String ten, int giatri) throws SQLException {
        if (Integer.parseInt(id.toString()) == 1) {
            JOptionPane.showMessageDialog(frame, "Không được thay đổi.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            return;
        }
        String sql = "Update DISCOUNT set CodeDiscount=N'" + ten + "', Value = '" + giatri + "' where IdDiscount ='" + id + "'";
        con.UpdateData(sql);
    }

    //Xoa 1 dong du lieu vao table 
    public void deleteDiscount(Object id) throws SQLException {
        if (Integer.parseInt(id.toString()) == 1) {
            JOptionPane.showMessageDialog(frame, "Không được xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String sql = "Delete from DISCOUNT where IdDiscount='" + id + "'";
        con.UpdateData(sql);
    }

    //Tim discount
    public int findDiscount(Object name) throws SQLException {
        con.connectSQL();
        String sql = "SELECT Value FROM DISCOUNT WHERE CodeDiscount = '" + name + "'";
        ResultSet result = con.LoadData(sql);
        while (result.next()) {
            return result.getInt(1);
        }
        return -1;
    }

    //Tim kiem
    public ResultSet searchDiscountsByKeyword(String keyword) throws SQLException {
        con.connectSQL();
        String sql = "SELECT *\n"
                + "FROM DBO.DISCOUNT p\n"
                + "WHERE LOWER(p.CodeDiscount) LIKE N'%" + keyword.toLowerCase() + "%'";
        return con.LoadData(sql);
    }
}
