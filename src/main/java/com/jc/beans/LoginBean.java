
package com.jc.beans;

import com.jc.model.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author José Carlos Grijalva González
 */
@ManagedBean(name="loginBean")

@SessionScoped
public class LoginBean implements Serializable{
    
    private String user;
    private String pass;
    private String msg;
    private String nombre;
    private String tab;
    private String pag="/secured/tabs/pacientes.xhtml", t1="true", t2, t3, t4;
    private boolean loggedIn;
    @Resource(name="MySqlDS")
    private DataSource ds;
    
    public LoginBean() {
    }
    
    public Usuario getUserb() throws SQLException, NamingException{
       //Connection con = ds.getConnection();
        Connection con=null;
        Usuario us = new Usuario();
        Context initialContext = new InitialContext();
        DataSource datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        
        con = datasource.getConnection();
    
      try{
//         Statement stmt = con.createStatement() ;
//    String query = "SELECT * FROM USUARIOS WHERE Username = '"+user+"' AND Password = '"+pass+"' ;" ;
         PreparedStatement consulta = con.prepareStatement("SELECT * FROM USUARIOS WHERE Username = ? AND Password = ? ");
         consulta.setString(1, user);
         consulta.setString(2, pass);
         ResultSet resultado = consulta.executeQuery();
//            ResultSet resultado = stmt.executeQuery(query) ;
         while(resultado.next()){
            us=new Usuario(resultado.getInt("IdUsuario"), user, resultado.getString("Nombre"), resultado.getString("Email"), pass);
         }
          System.out.println("USUARIO > "+us.getNombre());
      }catch(SQLException ex){
          System.out.println("eeee "+ex.getMessage());
      }finally{
           con.close();
      }
      return us;
    }
    
     public String doLogin() throws SQLException, NamingException{
        // Successful login
        if (user.equals(getUserb().getUserName()) && pass.equals(getUserb().getPassword())) {
            loggedIn = true;
            setNombre(getUserb().getNombre());
            return "/secured/home.xhtml?faces-redirect=true";
        }
        // Set login ERROR
        FacesMessage msg = new FacesMessage("Credenciales erroneas", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
         
        // To login page
        return "/index.xhtml";    
    }
     
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
         
        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
         
        return "/index.xhtml?faces-redirect=true";
    }
    
    public void commandTabClicked(ActionEvent event) {
        /* retrieve buttonId which you clicked */
        tab = event.getComponent().getId();
        switch(tab){
            case "tab_pacientes":
                t1="true";t2="false";t3="false";t4="false";
                pag="/secured/tabs/pacientes.xhtml";
            break;
            case "tab_consultas":
                t2="true";t1="false";t3="false";t4="false";
                pag="/secured/tabs/consultas.xhtml";
            break;
            case "tab_diagnosticos":
                t3="true";t2="false";t1="false";t4="false";
                pag="/secured/tabs/diagnosticos.xhtml";
            break;
            case "tab_reportes":
                t4="true";t1="false";t2="false";t3="false";
                pag="/secured/tabs/reportes.xhtml";
            break;
        }
        
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPag() {
        return pag;
    }

    public void setPag(String pag) {
        this.pag = pag;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public String getT4() {
        return t4;
    }

    public void setT4(String t4) {
        this.t4 = t4;
    }
    
}
