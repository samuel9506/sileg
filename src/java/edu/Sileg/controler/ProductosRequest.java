/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.controler;

import edu.Sileg.entity.CatProductos;
import edu.Sileg.entity.Productos;
import edu.Sileg.facade.CatProductosFacadeLocal;
import edu.Sileg.facade.ProductosFacadeLocal;
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
@Named(value = "productosRequest")
@RequestScoped
public class ProductosRequest implements Serializable{

    @EJB
    ProductosFacadeLocal productosFacadeLocal;
    
    @EJB
    CatProductosFacadeLocal catProductosFacadeLocal;
    
    
    private Productos producto = new Productos();
    private Productos productoEditar = new Productos();
    private List<Productos> listaProductos = new Vector();
    private Integer idCategoria;
    
    public ProductosRequest(ProductosFacadeLocal productosFacadeLocal) {
        this.productosFacadeLocal = productosFacadeLocal;
    }
   
    /**
     * Creates a new instance of ProductosRequest
     */
    public ProductosRequest() {
    }
    
    @PostConstruct
    public void init(){
       listaProductos.addAll(productosFacadeLocal.findAll());
    }
    
    public void crearProducto(){
        System.out.println("aca llego");
        producto.setIdcategoriasProductos(catProductosFacadeLocal.find(idCategoria));
        productosFacadeLocal.create(producto);
        listaProductos.add(producto);
    
    }
    
    public void eliminarProducto(Productos producto){
        productosFacadeLocal.remove(producto);
        listaProductos.remove(producto);
    }
    
    public void editar(Productos producto){
        productoEditar = producto;
    }
    
    public void actualizarProducto(){
        productoEditar.setIdcategoriasProductos(catProductosFacadeLocal.find(idCategoria));
        productosFacadeLocal.edit(productoEditar);
        listaProductos.clear();
        listaProductos.addAll(productosFacadeLocal.findAll());
    }
    
    public  void prueba() {
        System.out.println(productoEditar);
    }
    
    public List<CatProductos> traeCategoria (){
        return catProductosFacadeLocal.findAll();
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Productos getProductoEditar() {
        return productoEditar;
    }

    public void setProductoEditar(Productos productoEditar) {
        this.productoEditar = productoEditar;
    }
    
}
