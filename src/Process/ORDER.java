/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import DTO.Table;
import java.sql.*;
import Database.Connect;
import java.awt.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ORDER {

    private Component frame;
    public Connect con = new Connect();

    public ORDER() {
    }

    ; //constructor
    
    public ResultSet getAllTable() {
        try {
            con.connectSQL();

        } catch (SQLException ex) {
            Logger.getLogger(ORDER.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT * FROM BAN";
        return con.LoadData(sql);
    }

    //Dieu chinh 1 dong du lieu trong table 
    public void editStatus(String name, String status) {
        String sql = "Update BAN set TableStatus =N'" + status + "' where TableNumber=N'" + name + "'";
        con.UpdateData(sql);
    }

    public List<Table> GetListTable() throws SQLException {
        List<Table> listTable = new ArrayList<Table>();
        ResultSet result = getAllTable();
        while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
            Table table = new Table(result.getInt(1), result.getString(2), result.getString(3));
            listTable.add(table);
        }
        return listTable;
    }

    public int GetIdTable(String name) throws SQLException {
        ResultSet result = getAllTable();
        while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
            if (result.getString(2).equals(name)) {
                return result.getInt(1);
            }
        }
        return 0;
    }

    public ResultSet getListBillInfo(String name) throws SQLException {
        int id = GetIdTable(name);
        con.connectSQL();
        String sql = "SELECT c.NameProduct,a.Quantity,a.Price FROM BILL_INFO as a,BILL as b, PRODUCT as c where a.IdBill= b.IdBill and b.IdTable = " + id + " and a.IdProduct=c.IdProduct and b.Status = 1";
        return con.LoadData(sql);
    }

    //Theo moi 1 dong du lieu vao table 
    public void insertBill(String name) throws SQLException {
        int id = GetIdTable(name);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String sql = "INSERT INTO BILL values ('" + id + "',"+ DTO.CurrentUser.user.getIdEmployee() +",NULL,NULL,NULL,'" + dtf.format(now) + "',1)";
        con.UpdateData(sql);
    }

    //check bill ton tai
    public int checkBill(String name) throws SQLException {
        int id = GetIdTable(name);
        String check_existed = "SELECT IdBill FROM BILL WHERE IdTable = " + id + "and Status = 1";
        ResultSet result = con.LoadData(check_existed);
        while (result.next()) {
            return result.getInt(1);
        }
        return -1;
    }

    //get IdBillMax
    public int getIdMax(String name) throws SQLException {
        con.connectSQL();
        String sql = "SELECT MAX(IdBill) FROM BILL";
        ResultSet result = con.LoadData(sql);
        while (result.next()) {
            return result.getInt(1);
        }
        return -1;
    }

    //kiem tra product ton tai trong bill_info
    public int findIdProduct(int idproduct, int idBill) {
        try {
            con.connectSQL();
            String sql = "SELECT IdProduct FROM BILL_INFO WHERE IdProduct = " + idproduct + "AND IdBill = " + idBill;
            ResultSet result = con.LoadData(sql);
            while (result.next()) {
                return result.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ORDER.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void insertBillInfo(String name, int idproduct, int number, int total) throws SQLException {
        String sql;
        int idBill = checkBill(name);
        if (idBill != -1) {
            if (findIdProduct(idproduct, idBill) != -1) {
                sql = "UPDATE BILL_INFO SET Quantity = " + number + ", Price = " + total + "WHERE IdProduct = " + idproduct + "AND IdBill = " + idBill;
            } else {
                sql = "INSERT INTO BILL_INFO values ('" + checkBill(name) + "','" + idproduct + "','" + number + "','" + total + "')";
            }
            con.UpdateData(sql);
        } else {
            insertBill(name);
            sql = "INSERT INTO BILL_INFO values ('" + getIdMax(name) + "','" + idproduct + "','" + number + "','" + total + "')";
            con.UpdateData(sql);
        }
    }

    //Thanh toan
    public void Checkout(String name, Object totalmoney, Object moneyreduce, Object moneyfinal) {
        int id;
        try {
            id = GetIdTable(name);
            String sql = "UPDATE BILL SET Status = 0,TotalMoney=" + totalmoney + ",MoneyReduce=" + moneyreduce + ",MoneyFinal= " + moneyfinal + " WHERE IdTable=" + id;
            con.UpdateData(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ORDER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Xoa mon an trong bill_info
    public void deleteProduct(String name, int idproduct) throws SQLException {
        int idBill = checkBill(name);
        String sql = "Delete from BILL_INFO where IdBill='" + idBill + "' AND IdProduct='" + idproduct + "'";
        con.UpdateData(sql);
    }

    //Xoa tat ca cac idproduct 
    public void deleteBill(String name) throws SQLException {
        int idBill = checkBill(name);
        String sql = "Delete from BILL WHERE IdBill= '" + idBill + "' AND Status = 1";
        con.UpdateData(sql);
        editStatus(name, "Trống");
    }

}
