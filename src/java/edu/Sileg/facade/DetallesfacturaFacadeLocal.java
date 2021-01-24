/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.Detallesfactura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface DetallesfacturaFacadeLocal {

    void create(Detallesfactura detallesfactura);

    void edit(Detallesfactura detallesfactura);

    void remove(Detallesfactura detallesfactura);

    Detallesfactura find(Object id);

    List<Detallesfactura> findAll();

    List<Detallesfactura> findRange(int[] range);

    int count();
    
}
