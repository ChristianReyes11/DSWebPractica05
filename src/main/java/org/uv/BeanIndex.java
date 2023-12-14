/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.uv;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author carlosart17
 */
@Named(value = "beanIndex")
@SessionScoped
public class BeanIndex implements Serializable {

    /**
     * Creates a new instance of BeanIndex
     * 
     */
    private Usuario usr= new Usuario();
    public BeanIndex() {
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }
    
    public void mostrar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        
        Long idCliente=Long.valueOf(usr.getUser());
        Cliente cliente= session.get(Cliente.class, idCliente);
        if(cliente!=null){
            List<Venta> lstVentasDeClientes=cliente.getVentas();
            
            addMessage(FacesMessage.SEVERITY_INFO, "atencion", "Cliente: No V:"
            +lstVentasDeClientes.size());
            Logger.getLogger(BeanIndex.class.getName()).log(Level.INFO, "Cliente si existe");
        }else{
            addMessage(FacesMessage.SEVERITY_INFO, "atencion", "No existe");
            Logger.getLogger(BeanIndex.class.getName()).log(Level.INFO, "No existe");
        }
    }
    
    public void guardar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        
        Venta v=new Venta();
        java.util.Date fechaT=new java.util.Date();
        v.setFecha(new Date(fechaT.getTime()));
        Cliente c= new Cliente();
        c.setIdCliente(1L);
        //v.setIdCliente(1L);
        v.setCliente(c);
        v.setTotal(new BigDecimal(20));
        session.save(v);
        addMessage(FacesMessage.SEVERITY_INFO, "Atencion", "Se conecto");
        Logger.getLogger(BeanIndex.class.getName()).log(Level.INFO, "Se conecto");
        t.commit();
        session.close();
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
