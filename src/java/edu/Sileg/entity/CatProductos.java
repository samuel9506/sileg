    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Usuario
 */
@Entity
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatProductos.findAll", query = "SELECT c FROM CatProductos c")})
public class CatProductos implements Serializable {

    @OneToMany(mappedBy = "fkCategoria", fetch = FetchType.LAZY)
    private Collection<Productos> productosCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategoria")
    private Integer idcategoriasProductos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    
    public CatProductos() {
    }

    public CatProductos(Integer idcategoriasProductos) {
        this.idcategoriasProductos = idcategoriasProductos;
    }

    public CatProductos(Integer idcategoriasProductos, String nombre) {
        this.idcategoriasProductos = idcategoriasProductos;
        this.nombre = nombre;
    }

    public Integer getIdcategoriasProductos() {
        return idcategoriasProductos;
    }

    public void setIdcategoriasProductos(Integer idcategoriasProductos) {
        this.idcategoriasProductos = idcategoriasProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategoriasProductos != null ? idcategoriasProductos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatProductos)) {
            return false;
        }
        CatProductos other = (CatProductos) object;
        if ((this.idcategoriasProductos == null && other.idcategoriasProductos != null) || (this.idcategoriasProductos != null && !this.idcategoriasProductos.equals(other.idcategoriasProductos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sileg.entity.CatProductos[ idcategoriasProductos=" + idcategoriasProductos + " ]";
    }

    @XmlTransient
    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }
    
}
