/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.controler;

import edu.Sileg.entity.CatProductos;
import edu.Sileg.facade.CatProductosFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Usuario
 */
@Named(value = "categoriaProRequest")
@RequestScoped
public class CategoriaProRequest implements Serializable{
    
    @EJB
    CatProductosFacadeLocal catProductosFacadeLocal;

    private CatProductos categoria = new CatProductos(); 
    private CatProductos categoriaEditar = new CatProductos();
    private List<CatProductos> listaCategorias = new Vector<>();
    private String mensaje= "";
    
    @PostConstruct
    public void init(){
      /*  categoria = new CatProductos();*/
        listaCategorias = catProductosFacadeLocal.findAll();     
    }
    
    /**
     * Creates a new instance of CategoriaProRequest
     */
    public CategoriaProRequest() {
    }
    /*METODO PARA CREAR UNA CATEGORIA*/
    public void crearProducto() {
       
        try {
         catProductosFacadeLocal.create(categoria);
        listaCategorias.add(categoria);
        categoria = new CatProductos();
        mensaje =  "swal('Registro Exitoso!', 'Categoria creada!', 'success')";
        categoria = new CatProductos();
        } catch (Exception e) {
       mensaje =  "swal('Algo inesperado sucedio!', 'error fatal!', 'error')";
            
        }
        PrimeFaces.current().executeScript(mensaje);
        
    }
    
    /*METODO PARA ELIMINAR CATEGORIAS*/
    public void eliminarCategoria(CatProductos categoria){
        
        try {
             catProductosFacadeLocal.remove(categoria);
            listaCategorias.remove(categoria);
            mensaje =  "swal(' OK!', 'Categoria eliminada!', 'success')";
        } catch (Exception e) {
            mensaje =  "swal('Algo inesperado sucedio!', 'error fatal!', 'error')";
        }
       PrimeFaces.current().executeScript(mensaje);
       
    }
    
    /*METODO PARA ACTUALIZAR*/
    public void actualizarCategoria(){
        catProductosFacadeLocal.edit(categoriaEditar);
        listaCategorias.clear();
        listaCategorias.addAll(catProductosFacadeLocal.findAll());
        
    }
    
    public void editarCategoria(CatProductos catEditar){
        this.categoriaEditar = catEditar;
    }
    
    
    
    public CatProductos getCategoria() {
        return categoria;
    }

    public void setCategoria(CatProductos categoria) {
        this.categoria = categoria;
    }

    public Vector<CatProductos> getListaCategorias() {
        return (Vector<CatProductos>) listaCategorias;
    }

    public void setListaCategorias(Vector<CatProductos> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public CatProductos getCategoriaEditar() {
        return categoriaEditar;
    }

    public void setCategoriaEditar(CatProductos categoriaEditar) {
        this.categoriaEditar = categoriaEditar;
    }
    
    
}
