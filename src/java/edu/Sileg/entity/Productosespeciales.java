/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "productosespeciales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productosespeciales.findAll", query = "SELECT p FROM Productosespeciales p")
    , @NamedQuery(name = "Productosespeciales.findByIdprodespeciales", query = "SELECT p FROM Productosespeciales p WHERE p.idprodespeciales = :idprodespeciales")
    , @NamedQuery(name = "Productosespeciales.findByNombreproducto", query = "SELECT p FROM Productosespeciales p WHERE p.nombreproducto = :nombreproducto")
    , @NamedQuery(name = "Productosespeciales.findByExisstencias", query = "SELECT p FROM Productosespeciales p WHERE p.exisstencias = :exisstencias")
    , @NamedQuery(name = "Productosespeciales.findByFechavencimiento", query = "SELECT p FROM Productosespeciales p WHERE p.fechavencimiento = :fechavencimiento")
    , @NamedQuery(name = "Productosespeciales.findByValorcompra", query = "SELECT p FROM Productosespeciales p WHERE p.valorcompra = :valorcompra")})
public class Productosespeciales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idprodespeciales")
    private Integer idprodespeciales;
    @Size(max = 45)
    @Column(name = "nombreproducto")
    private String nombreproducto;
    @Size(max = 45)
    @Column(name = "exisstencias")
    private String exisstencias;
    @Size(max = 45)
    @Column(name = "fechavencimiento")
    private String fechavencimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorcompra")
    private Double valorcompra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosEspecialesidprodespeciales", fetch = FetchType.LAZY)
    private Collection<Campañasfidelizacion> campañasfidelizacionCollection;

    public Productosespeciales() {
    }

    public Productosespeciales(Integer idprodespeciales) {
        this.idprodespeciales = idprodespeciales;
    }

    public Integer getIdprodespeciales() {
        return idprodespeciales;
    }

    public void setIdprodespeciales(Integer idprodespeciales) {
        this.idprodespeciales = idprodespeciales;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getExisstencias() {
        return exisstencias;
    }

    public void setExisstencias(String exisstencias) {
        this.exisstencias = exisstencias;
    }

    public String getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(String fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public Double getValorcompra() {
        return valorcompra;
    }

    public void setValorcompra(Double valorcompra) {
        this.valorcompra = valorcompra;
    }

    @XmlTransient
    public Collection<Campañasfidelizacion> getCampañasfidelizacionCollection() {
        return campañasfidelizacionCollection;
    }

    public void setCampañasfidelizacionCollection(Collection<Campañasfidelizacion> campañasfidelizacionCollection) {
        this.campañasfidelizacionCollection = campañasfidelizacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprodespeciales != null ? idprodespeciales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productosespeciales)) {
            return false;
        }
        Productosespeciales other = (Productosespeciales) object;
        if ((this.idprodespeciales == null && other.idprodespeciales != null) || (this.idprodespeciales != null && !this.idprodespeciales.equals(other.idprodespeciales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.Sileg.entity.Productosespeciales[ idprodespeciales=" + idprodespeciales + " ]";
    }
    
}
