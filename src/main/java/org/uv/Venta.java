/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author carlosart17
 */
@Entity
@Table(name = "venta")
public class Venta implements Serializable{
    
    @Id
    @GeneratedValue(generator = "venta_idventa_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "venta_idventa_seq", sequenceName = "venta_idventa_seq",
            initialValue = 1, allocationSize = 1)
    @Column (name = "idventa")
    private Long idVenta;
    
    @Column (name = "fecha")
    private Date fecha;
    @Column (name = "total")
    private BigDecimal total;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venta")
    private List<VentaDetalle> ventasDetalle;

    public List<VentaDetalle> getVentas() {
        return ventasDetalle;
    }

    public void setVentas(List<VentaDetalle> ventasDetalle) {
        this.ventasDetalle = ventasDetalle;
    }
    
    
    
//    @Column (name = "idcliente")
//    private Long idCliente;

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    

//    public Long getIdCliente() {
//        return idCliente;
//    }
//
//    public void setIdCliente(Long idCliente) {
//        this.idCliente = idCliente;
//    }

    
    
    
}
