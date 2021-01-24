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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "categoriasolicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriasolicitudes.findAll", query = "SELECT c FROM Categoriasolicitudes c")
    , @NamedQuery(name = "Categoriasolicitudes.findById", query = "SELECT c FROM Categoriasolicitudes c WHERE c.id = :id")
    , @NamedQuery(name = "Categoriasolicitudes.findByTiposolicitud", query = "SELECT c FROM Categoriasolicitudes c WHERE c.tiposolicitud = :tiposolicitud")})
public class Categoriasolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "tiposolicitud")
    private String tiposolicitud;

    public Categoriasolicitudes() {
    }

    public Categoriasolicitudes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTiposolicitud() {
        return tiposolicitud;
    }

    public void setTiposolicitud(String tiposolicitud) {
        this.tiposolicitud = tiposolicitud;
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
        if (!(object instanceof Categoriasolicitudes)) {
            return false;
        }
        Categoriasolicitudes other = (Categoriasolicitudes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.Sileg.entity.Categoriasolicitudes[ id=" + id + " ]";
    }
    
}
