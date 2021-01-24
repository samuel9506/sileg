/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samuel
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p")
    , @NamedQuery(name = "Productos.findByIdproductos", query = "SELECT p FROM Productos p WHERE p.idproductos = :idproductos")
    , @NamedQuery(name = "Productos.findByNombreProducto", query = "SELECT p FROM Productos p WHERE p.nombreProducto = :nombreProducto")
    , @NamedQuery(name = "Productos.findByPresentacion", query = "SELECT p FROM Productos p WHERE p.presentacion = :presentacion")
    , @NamedQuery(name = "Productos.findByExistencias", query = "SELECT p FROM Productos p WHERE p.existencias = :existencias")
    , @NamedQuery(name = "Productos.findByFechacompra", query = "SELECT p FROM Productos p WHERE p.fechacompra = :fechacompra")
    , @NamedQuery(name = "Productos.findByFechavenc", query = "SELECT p FROM Productos p WHERE p.fechavenc = :fechavenc")
    , @NamedQuery(name = "Productos.findByPrecioVenta", query = "SELECT p FROM Productos p WHERE p.precioVenta = :precioVenta")
    , @NamedQuery(name = "Productos.findByPrecioCompra", query = "SELECT p FROM Productos p WHERE p.precioCompra = :precioCompra")
    , @NamedQuery(name = "Productos.findByPuntos", query = "SELECT p FROM Productos p WHERE p.puntos = :puntos")
    , @NamedQuery(name = "Productos.findByImagenRuta", query = "SELECT p FROM Productos p WHERE p.imagenRuta = :imagenRuta")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproductos")
    private Integer idproductos;
    @Size(max = 45)
    @Column(name = "nombreProducto")
    private String nombreProducto;
    @Size(max = 45)
    @Column(name = "presentacion")
    private String presentacion;
    @Column(name = "existencias")
    private Integer existencias;
    @Column(name = "fechacompra")
    @Temporal(TemporalType.DATE)
    private Date fechacompra;
    @Column(name = "fechavenc")
    @Temporal(TemporalType.DATE)
    private Date fechavenc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioVenta")
    private Double precioVenta;
    @Column(name = "precioCompra")
    private Double precioCompra;
    @Column(name = "puntos")
    private Integer puntos;
    @Size(max = 245)
    @Column(name = "imagen_ruta")
    private String imagenRuta;
    @JoinColumn(name = "fk_categoria", referencedColumnName = "idcategoria")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria fkCategoria;

    public Productos() {
    }

    public Productos(Integer idproductos) {
        this.idproductos = idproductos;
    }

    public Integer getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(Integer idproductos) {
        this.idproductos = idproductos;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public Date getFechavenc() {
        return fechavenc;
    }

    public void setFechavenc(Date fechavenc) {
        this.fechavenc = fechavenc;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(String imagenRuta) {
        this.imagenRuta = imagenRuta;
    }

    public Categoria getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(Categoria fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproductos != null ? idproductos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idproductos == null && other.idproductos != null) || (this.idproductos != null && !this.idproductos.equals(other.idproductos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.Sileg.entity.Productos[ idproductos=" + idproductos + " ]";
    }
    
}
