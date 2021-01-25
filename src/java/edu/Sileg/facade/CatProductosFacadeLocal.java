/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.CatProductos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface CatProductosFacadeLocal {

    void create(CatProductos catProductos);

    void edit(CatProductos catProductos);

    void remove(CatProductos catProductos);

    CatProductos find(Object id);

    List<CatProductos> findAll();

    List<CatProductos> findRange(int[] range);

    int count();
    
}
