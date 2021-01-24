/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.Categoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Asus
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {

    @PersistenceContext(unitName = "SilegPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
      @Override
      public int cantidadProductoCategoria( int categoria){
        try {
            Query qt = em.createNativeQuery("SELECT COUNT(*) FROM productos WHERE fk_categoria = ?1");
            qt.setParameter(1, categoria);
            return ((Number)qt.getSingleResult()).intValue();
        } catch (Exception e) {
            return 0;
        }
    }
    
}
