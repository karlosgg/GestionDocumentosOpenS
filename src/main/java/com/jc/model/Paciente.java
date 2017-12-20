
package com.jc.model;

/**
 *
 * @author José Carlos Grijalva González
 */
public class Paciente {
    private int IdPaciente;
    private String Nombre ,Sexo, FNacimiento, Direccion, Telefono, Email;

    public Paciente() {
    }

    public Paciente(int IdPaciente, String Nombre, String Sexo, String FNacimiento, String Direccion, String Telefono, String Email) {
        this.IdPaciente = IdPaciente;
        this.Nombre = Nombre;
        this.Sexo = Sexo;
        this.FNacimiento = FNacimiento;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Email = Email;
    }

    public int getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(int IdPaciente) {
        this.IdPaciente = IdPaciente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getFNacimiento() {
        return FNacimiento;
    }

    public void setFNacimiento(String FNacimiento) {
        this.FNacimiento = FNacimiento;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
}
