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
public class CurrentUser {

    public int getIdEmployee() {
        return IdEmployee;
    }

    public void setIdEmployee(int IdEmployee) {
        this.IdEmployee = IdEmployee;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public CurrentUser(int IdEmployee, String Fullname, String Username, String Password, String Phone, String Role, String Image) {
        this.IdEmployee = IdEmployee;
        this.Fullname = Fullname;
        this.Username = Username;
        this.Password = Password;
        this.Phone = Phone;
        this.Role = Role;
        this.Image = Image;
    }

    public CurrentUser() {
    }
    
    private int IdEmployee;
    private String Fullname;
    private String Username;
    private String Password;
    private String Phone;
    private String Role;
    private String Image;
    
    public static CurrentUser user;
}
