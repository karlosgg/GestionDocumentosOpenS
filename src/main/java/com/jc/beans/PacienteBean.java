/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jc.beans;


import com.jc.model.Paciente;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author José Carlos Grijalva González
 */
@ManagedBean(name = "pacienteBean")
@SessionScoped
public class PacienteBean implements Serializable {
    private Paciente paciente;
    private List<Paciente> pacientes;
    private int totalRows;
    private int firstRow;
    private int rowsPerPage;
    private int totalPages;
    private int pageRange;
    private Integer[] pages;
    private int currentPage;
    
    private int idEdit;
    /**
     * Creates a new instance of ClienteBean
     */
    public PacienteBean() {
        
         // Set default values somehow (properties files?).
        rowsPerPage = 15; // Default rows per page (max amount of rows to be displayed at once).
        pageRange = 10; // Default page range (max amount of page links to be displayed at once).
    }
    
    public void loadPacientes() throws NamingException, SQLException {
        Connection con=null;
        paciente = null;
        pacientes = new ArrayList<>();
        Context initialContext = new InitialContext();
        DataSource datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        
        con = datasource.getConnection();
    
      try{
         PreparedStatement consulta = con.prepareStatement("SELECT * FROM PACIENTES LIMIT ? OFFSET ? ");
         consulta.setInt(1, rowsPerPage);
         consulta.setInt(2, firstRow);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            paciente=new Paciente(resultado.getInt("IdPaciente"), resultado.getString("Nombre"), resultado.getString("Sexo"),
                    resultado.getString("FNacimiento"), resultado.getString("Direccion"), resultado.getString("Telefono"), resultado.getString("Email"));
            pacientes.add(paciente);
         }
      }catch(SQLException ex){
          System.out.println("eeee "+ex.getMessage());
      }finally{
           con.close();
      }
    }
    
    public void countPacientes() throws NamingException, SQLException{
        Connection con=null;
        Context initialContext = new InitialContext();
        DataSource datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        con = datasource.getConnection();
      try{
         PreparedStatement consulta = con.prepareStatement("SELECT COUNT(*) AS NUM FROM PACIENTES");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            totalRows=resultado.getInt("NUM");
         }
      }catch(SQLException ex){
          System.out.println("eeee "+ex.getMessage());
      }finally{
           con.close();
      }
    }
 
    private void loadPacientesList() throws NamingException, SQLException {
        loadPacientes();
        countPacientes();
 
        // Set currentPage, totalPages and pages.
        currentPage = (totalRows / rowsPerPage) - ((totalRows - firstRow) / rowsPerPage) + 1;
        totalPages = (totalRows / rowsPerPage) + ((totalRows % rowsPerPage != 0) ? 1 : 0);
        int pagesLength = Math.min(pageRange, totalPages);
        pages = new Integer[pagesLength];
 
        // firstPage must be greater than 0 and lesser than totalPages-pageLength.
        int firstPage = Math.min(Math.max(0, currentPage - (pageRange / 2)), totalPages - pagesLength);
 
        // Create pages (page numbers for page links).
        for (int i = 0; i < pagesLength; i++) {
            pages[i] = ++firstPage;
        }
    }

    // Paging actions -----------------------------------------------------------------------------
    public void pageFirst() throws NamingException, SQLException {
        page(0);
    }
 
    public void pageNext() throws NamingException, SQLException {
        page(firstRow + rowsPerPage);
    }
 
    public void pagePrevious() throws NamingException, SQLException {
        page(firstRow - rowsPerPage);
    }
 
    public void pageLast() throws NamingException, SQLException {
        page(totalRows - ((totalRows % rowsPerPage != 0) ? totalRows % rowsPerPage : rowsPerPage));
    }
 
    public void page(ActionEvent event) throws NamingException, SQLException {        
        page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
    }
 
    private void page(int firstRow) throws NamingException, SQLException {
        this.firstRow = firstRow;
        loadPacientesList();
    }
    
    

    public int getIdEdit() {
        return idEdit;
    }
    
    public void setIdEdit(int idEdit) {
        this.idEdit = idEdit;
    }
        
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Paciente> getPacientes() throws NamingException, SQLException {
        if(pacientes==null){
            loadPacientesList();
        }
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageRange() {
        return pageRange;
    }

    public void setPageRange(int pageRange) {
        this.pageRange = pageRange;
    }

    public Integer[] getPages() {
        return pages;
    }

    public void setPages(Integer[] pages) {
        this.pages = pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    
}
