/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class Table {
    private int IdTable;
    
    private String TableNumber;
    
    private String TableStatus;
    
    public Table(int IdTable, String TableNumber,String TableStatus)
    {
        this.IdTable = IdTable;
        this.TableNumber = TableNumber;
        this.TableStatus = TableStatus;
    }
    public int getIdTable() {
        return IdTable;
    }

    public void setIdTable(int IdTable) {
        this.IdTable = IdTable;
    }

    public String getTableNumber() {
        return TableNumber;
    }

    public void setTableNumber(String TableNumber) {
        this.TableNumber = TableNumber;
    }

    public String getTableStatus() {
        return TableStatus;
    }

    public void setTableStatus(String TableStatus) {
        this.TableStatus = TableStatus;
    }
}
