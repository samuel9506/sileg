/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "detallesfactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallesfactura.findAll", query = "SELECT d FROM Detallesfactura d")
    , @NamedQuery(name = "Detallesfactura.findByIddetalles", query = "SELECT d FROM Detallesfactura d WHERE d.iddetalles = :iddetalles")
    , @NamedQuery(name = "Detallesfactura.findByCantidad", query = "SELECT d FROM Detallesfactura d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "Detallesfactura.findByTotalunidades", query = "SELECT d FROM Detallesfactura d WHERE d.totalunidades = :totalunidades")})
public class Detallesfactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalles")
    private Integer iddetalles;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalunidades")
    private Double totalunidades;
    @JoinColumn(name = "fk_factura", referencedColumnName = "idFactura")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Factura fkFactura;
    @JoinColumn(name = "fk_productos", referencedColumnName = "idproductos")
    @ManyToOne(fetch = FetchType.EAGER)
    private Productos fkProductos;

    public Detallesfactura() {
    }

    public Detallesfactura(Integer iddetalles) {
        this.iddetalles = iddetalles;
    }

    public Integer getIddetalles() {
        return iddetalles;
    }

    public void setIddetalles(Integer iddetalles) {
        this.iddetalles = iddetalles;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotalunidades() {
        return totalunidades;
    }

    public void setTotalunidades(Double totalunidades) {
        this.totalunidades = totalunidades;
    }

    public Factura getFkFactura() {
        return fkFactura;
    }

    public void setFkFactura(Factura fkFactura) {
        this.fkFactura = fkFactura;
    }

    public Productos getFkProductos() {
        return fkProductos;
    }

    public void setFkProductos(Productos fkProductos) {
        this.fkProductos = fkProductos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalles != null ? iddetalles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallesfactura)) {
            return false;
        }
        Detallesfactura other = (Detallesfactura) object;
        if ((this.iddetalles == null && other.iddetalles != null) || (this.iddetalles != null && !this.iddetalles.equals(other.iddetalles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.Sileg.entity.Detallesfactura[ iddetalles=" + iddetalles + " ]";
    }
    
}
