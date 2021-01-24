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
@Table(name = "provedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provedores.findAll", query = "SELECT p FROM Provedores p")
    , @NamedQuery(name = "Provedores.findByNit", query = "SELECT p FROM Provedores p WHERE p.nit = :nit")
    , @NamedQuery(name = "Provedores.findByNombre", query = "SELECT p FROM Provedores p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Provedores.findByRepresentante", query = "SELECT p FROM Provedores p WHERE p.representante = :representante")
    , @NamedQuery(name = "Provedores.findByDireccion", query = "SELECT p FROM Provedores p WHERE p.direccion = :direccion")})
public class Provedores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "nit")
    private Integer nit;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "representante")
    private String representante;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;

    public Provedores() {
    }

    public Provedores(Integer nit) {
        this.nit = nit;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nit != null ? nit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provedores)) {
            return false;
        }
        Provedores other = (Provedores) object;
        if ((this.nit == null && other.nit != null) || (this.nit != null && !this.nit.equals(other.nit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.Sileg.entity.Provedores[ nit=" + nit + " ]";
    }
    
}
