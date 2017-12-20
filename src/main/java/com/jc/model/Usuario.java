
package com.jc.model;

/**
 *
 * @author José Carlos Grijalva González
 */
public class Usuario {
    private int IdUsuario;
    private String UserName;
    private String Nombre;
    private String Email;
    private String Password;

    public Usuario() {
    }

    public Usuario(int IdUsuario, String UserName, String Nombre, String Email, String Password) {
        this.IdUsuario = IdUsuario;
        this.UserName = UserName;
        this.Nombre = Nombre;
        this.Email = Email;
        this.Password = Password;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
}
