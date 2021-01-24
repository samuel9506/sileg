/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.Categoriasolicitudes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Asus
 */
@Stateless
public class CategoriasolicitudesFacade extends AbstractFacade<Categoriasolicitudes> implements CategoriasolicitudesFacadeLocal {

    @PersistenceContext(unitName = "SilegPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasolicitudesFacade() {
        super(Categoriasolicitudes.class);
    }
    
}
