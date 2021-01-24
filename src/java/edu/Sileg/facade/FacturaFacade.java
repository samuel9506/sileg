/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.Factura;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Asus
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura> implements FacturaFacadeLocal {

    @PersistenceContext(unitName = "SilegPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);    
    }
      
    @Override
    public Integer consultarUltimoFactura(Integer idFactura) {
        Query q = em.createQuery("select  last_insert_id();) from sileg s where s.fkFactura.idFactura=:id");
        q.setParameter("id", idFactura);
        Integer ultimo = (Integer) q.getSingleResult();
        return ultimo;
    }
    
}
