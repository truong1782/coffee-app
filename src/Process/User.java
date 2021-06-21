/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import DTO.CurrentUser;
import Database.Connect;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vulee
 */
public class User {

    Connect con = new Connect();

    public ResultSet getUser(String tk) throws SQLException {
        con.connectSQL();
        String sql = "SELECT * FROM EMPLOYEE WHERE Username = '" + tk + "'";
        return con.LoadData(sql);

    }

    public CurrentUser getCurrentUser(String tk) throws SQLException {
        ResultSet result = getUser(tk);
        CurrentUser user=null;
        while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
            user = new CurrentUser(result.getInt(1), result.getString(2), result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7));
        }
        return user;
    }
}
