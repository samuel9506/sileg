/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.controler;

import edu.Sileg.entity.Proveedores;
import edu.Sileg.facade.ProveedoresFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Usuario
 */
@Named(value = "proveedorRequest")
@RequestScoped
public class ProveedorRequest implements Serializable {

    @EJB
    ProveedoresFacadeLocal proveedoresFacadeLocal;
    private Proveedores proveedor = new Proveedores();
    private Proveedores proveedorEditar = new Proveedores();
    private List<Proveedores> listaProveedores = new Vector();

    /**
     * Creates a new instance of ProveedorRequest
     */
    public ProveedorRequest() {
    }

    @PostConstruct
    public void init() {
        listaProveedores.addAll(proveedoresFacadeLocal.findAll());
    }

    public void crearProveedor() {
        proveedoresFacadeLocal.create(proveedor);
        listaProveedores.add(proveedor);
    }

    public void editarProveedor(Proveedores proveedor) {
        proveedorEditar = proveedor;
    }

    public void actualizarProveedor() {
        proveedoresFacadeLocal.edit(proveedorEditar);
        listaProveedores.clear();
        listaProveedores.addAll(proveedoresFacadeLocal.findAll());
    }

    public void eliminarProveedor(Proveedores proveedor) {
        proveedoresFacadeLocal.remove(proveedor);
        listaProveedores.remove(proveedor);
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public Proveedores getProveedorEditar() {
        return proveedorEditar;
    }

    public void setProveedorEditar(Proveedores proveedorEditar) {
        this.proveedorEditar = proveedorEditar;
    }

    public List<Proveedores> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<Proveedores> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

}
