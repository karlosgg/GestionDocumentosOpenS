
package com.jc.beans;
//import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author José Carlos Grijalva González
 */
//@Named(value = "holaBean")
//@SessionScoped  //ambas funcionaban
@ManagedBean(name="holaBean")
@SessionScoped
public class HolaBean implements Serializable {
////    @ManagedProperty(value="#{AString.str}")   //lo sugiere el libro
    private String nombre;
    private String password;
    private String when;private String hora;

    public HolaBean() {
         when = new GregorianCalendar().getTime().toString(); 
    }

    public String getWhen() { 
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
        String dateString=sdf.format(new GregorianCalendar().getTime());
        return dateString; }
    public void setWhen(String w) { when = w; }

    public String getHora() {
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
        String dateString=sdf.format(new GregorianCalendar().getTime());
        return dateString;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}