/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.controler;

import edu.Sileg.entity.Detallesfactura;
import edu.Sileg.entity.Factura;
import edu.Sileg.entity.Productos;
import edu.Sileg.entity.Rol;
import edu.Sileg.entity.Usuario;
import edu.Sileg.facade.DetallesfacturaFacadeLocal;
import edu.Sileg.facade.FacturaFacadeLocal;
import edu.Sileg.facade.ProductosFacadeLocal;
import edu.Sileg.facade.UsuarioFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Asus
 */
@Named(value = "detallesSession")
@SessionScoped
public class DetallesSession implements Serializable {

    /**
     * Creates a new instance of DetallesSession
     */
    public DetallesSession() {
    }

    @EJB
    DetallesfacturaFacadeLocal detallesfacturaFacadeLocal;

    @EJB
    ProductosFacadeLocal productosFacadeLocal;

    @EJB
    FacturaFacadeLocal facturaFacadeLocal;

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    private ArrayList<Productos> listaProductos = new ArrayList<>();
    private ArrayList<Detallesfactura> listaDetalles = new ArrayList<>();
    private ArrayList<Factura> listaFactura = new ArrayList<>();
    private Detallesfactura detalles = new Detallesfactura();
    private Factura factu = new Factura();
    private Integer idproductos;
    private Integer cantidad;
    private Double totalunidades;
    private Integer id;
    private Integer idFactura;
    private Productos productoSeleccionado = new Productos();

    public List<Productos> listarProductos() {
        return productosFacadeLocal.findAll();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioFacadeLocal.findAll();
    }

    public void agregar(Productos producto) {
        productoSeleccionado = producto;
    }

    public void agregarProducto() {
        Productos productoSeleccionado = productosFacadeLocal.find(idproductos);
        detalles.setFkProductos(productoSeleccionado);
        listaDetalles.add(detalles);
        detalles = new Detallesfactura();
    }

    public void agregarCarro() {

        detalles.setFkProductos(productoSeleccionado);
        listaDetalles.add(detalles);
        detalles = new Detallesfactura();
    }

   

    public void calcularSubtotal(Detallesfactura det) {

        double subtotal = 0;

        if (det.getCantidad() != null) {
            subtotal = det.getFkProductos().getPrecioVenta() * det.getCantidad();
            

        }

        det.setTotalunidades(subtotal);

        double total = 0;
        for (Detallesfactura d : listaDetalles) {

            total += d.getTotalunidades();

        }
        factu.setSubtotal(total);

        double iva = 0;

        iva = total * 20 / 100;

        factu.setIva(iva);

        double totaliva = 0;

        totaliva = total + iva;

        factu.setTotal(totaliva);

        /*
        facturaFacadeLocal.create(factu);
        det.setFkFactura(factu);
        detallesfacturaFacadeLocal.create(det);
         */
    }

    public void agregarFactura() {
        
        Usuario ususel = usuarioFacadeLocal.find(id);
        factu.setIdUsuario(ususel);
        facturaFacadeLocal.create(factu);

        for (Detallesfactura d : listaDetalles) {
            d.setFkFactura(factu);
            detallesfacturaFacadeLocal.create(d);
        }
        factu = new Factura();
        listaDetalles = new ArrayList<Detallesfactura>();

    }

    public Detallesfactura getDetalles() {
        return detalles;
    }

    public void setDetalles(Detallesfactura detalles) {
        this.detalles = detalles;
    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<Detallesfactura> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(ArrayList<Detallesfactura> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public Integer getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(Integer idproductos) {
        this.idproductos = idproductos;
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

    public Factura getFactu() {
        return factu;
    }

    public void setFactu(Factura factu) {
        this.factu = factu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(ArrayList<Factura> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Productos getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Productos productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

}
