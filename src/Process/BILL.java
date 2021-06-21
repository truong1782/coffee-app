/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Database.Connect;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Acer
 */
public class BILL {

    public Connect con = new Connect();

    public ResultSet getAllBills() throws SQLException {
        con.connectSQL();
        String sql =    "SELECT convert(varchar, b.DateCreate, 103)[Ngày],SUM(b.TotalMoney)[Tổng tiền],SUM(b.MoneyReduce)[Tiền giảm],SUM(b.MoneyFinal)[Doanh thu]\n" +
                        "FROM dbo.BILL b\n" + "WHERE Status = 0 " +
                        "GROUP BY convert(varchar, b.DateCreate, 103)\n" +
                        "ORDER BY convert(varchar, b.DateCreate, 103) ASC";
        return con.LoadData(sql);
    }

    public ResultSet getBillsByDate(Date date_1, Date date_2) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String start_date = formatter.format(date_1);
        String end_date = formatter.format(date_2);
        con.connectSQL();
        String sql = "SELECT convert(varchar, b.DateCreate, 103)[Ngày],SUM(b.TotalMoney)[Tổng tiền],SUM(b.MoneyReduce)[Tiền giảm],SUM(b.MoneyFinal)[Doanh thu]\n"
                + "FROM dbo.BILL b\n"
                + "WHERE Status = 0 AND DateCreate between '" + start_date + "' AND '" + end_date + "'"
                + "GROUP BY convert(varchar, b.DateCreate, 103)"
                + "ORDER BY convert(varchar, b.DateCreate, 103) ASC";
        return con.LoadData(sql);
    }

    public ResultSet getBillsByMonth(int startMonth, int endMonth) throws SQLException {
        con.connectSQL();
        String sql = "SELECT FORMAT(b.DateCreate, 'MM/yyyy')[Tháng], SUM(b.TotalMoney)[Tổng tiền],SUM(b.MoneyReduce)[Tiền giảm],SUM(b.MoneyFinal)[Doanh thu]\n"
                + "FROM dbo.BILL b\n"
                + "WHERE Status = 0 AND MONTH(DateCreate) between '" + startMonth + "' AND '" + endMonth + "' AND YEAR(DateCreate) = YEAR(GETDATE())"
                + "GROUP BY FORMAT(b.DateCreate, 'MM/yyyy')"
                + "ORDER BY FORMAT(b.DateCreate, 'MM/yyyy') ASC";
        return con.LoadData(sql);
    }

    public ResultSet getBillsByYear(int startYear, int endYear) throws SQLException {
        con.connectSQL();
        String sql = "SELECT YEAR(b.DateCreate)[Năm], SUM(b.TotalMoney)[Tổng tiền],SUM(b.MoneyReduce)[Tiền giảm],SUM(b.MoneyFinal)[Doanh thu]\n"
                + "FROM dbo.BILL b\n"
                + "WHERE Status = 0 AND YEAR(DateCreate) between '" + startYear + "' AND '" + endYear + "'"
                + "GROUP BY YEAR(b.DateCreate)"
                + "ORDER BY YEAR(b.DateCreate) ASC";
        return con.LoadData(sql);
    }

}
