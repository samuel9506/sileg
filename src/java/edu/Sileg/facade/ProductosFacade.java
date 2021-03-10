/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.Productos;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Asus
 */
@Stateless
public class ProductosFacade extends AbstractFacade<Productos> implements ProductosFacadeLocal {

    @PersistenceContext(unitName = "SilegPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosFacade() {
        super(Productos.class);
    }
    
    @Override
     public List<Productos> listaProductosporcategoria(int fk_categoria) {
        try {
            Query qt = em.createQuery("SELECT p FROM Productos p WHERE p.fkCategoria.idcategoriasProductos = :fk_categoria");
            qt.setParameter("fk_categoria", fk_categoria);
            return qt.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
     
     
     @Override
       public boolean removerProductos(int idproductos) {
        boolean retorno = false;
        try {
            Query qt = em.createQuery("DELETE FROM Productos u WHERE u.idproductos = :idproductos");
            qt.setParameter("idproductos", idproductos);
            int salida = qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return retorno;
        }
    }
       
    
}
