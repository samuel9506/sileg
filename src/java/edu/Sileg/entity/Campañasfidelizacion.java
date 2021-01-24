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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "campa\u00f1asfidelizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campa\u00f1asfidelizacion.findAll", query = "SELECT c FROM Campa\u00f1asfidelizacion c")
    , @NamedQuery(name = "Campa\u00f1asfidelizacion.findById", query = "SELECT c FROM Campa\u00f1asfidelizacion c WHERE c.id = :id")
    , @NamedQuery(name = "Campa\u00f1asfidelizacion.findByTitulo", query = "SELECT c FROM Campa\u00f1asfidelizacion c WHERE c.titulo = :titulo")
    , @NamedQuery(name = "Campa\u00f1asfidelizacion.findByDescripcion", query = "SELECT c FROM Campa\u00f1asfidelizacion c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Campa\u00f1asfidelizacion.findByFechaInicio", query = "SELECT c FROM Campa\u00f1asfidelizacion c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Campa\u00f1asfidelizacion.findByFechaFinal", query = "SELECT c FROM Campa\u00f1asfidelizacion c WHERE c.fechaFinal = :fechaFinal")
    , @NamedQuery(name = "Campa\u00f1asfidelizacion.findByPuntos", query = "SELECT c FROM Campa\u00f1asfidelizacion c WHERE c.puntos = :puntos")})
public class Campañasfidelizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFinal")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "puntos")
    private Integer puntos;
    @JoinColumn(name = "productosEspeciales_idprodespeciales", referencedColumnName = "idprodespeciales")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productosespeciales productosEspecialesidprodespeciales;

    public Campañasfidelizacion() {
    }

    public Campañasfidelizacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Productosespeciales getProductosEspecialesidprodespeciales() {
        return productosEspecialesidprodespeciales;
    }

    public void setProductosEspecialesidprodespeciales(Productosespeciales productosEspecialesidprodespeciales) {
        this.productosEspecialesidprodespeciales = productosEspecialesidprodespeciales;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campañasfidelizacion)) {
            return false;
        }
        Campañasfidelizacion other = (Campañasfidelizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.Sileg.entity.Campa\u00f1asfidelizacion[ id=" + id + " ]";
    }
    
}
