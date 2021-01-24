/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.Campañasfidelizacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface CampañasfidelizacionFacadeLocal {

    void create(Campañasfidelizacion campañasfidelizacion);

    void edit(Campañasfidelizacion campañasfidelizacion);

    void remove(Campañasfidelizacion campañasfidelizacion);

    Campañasfidelizacion find(Object id);

    List<Campañasfidelizacion> findAll();

    List<Campañasfidelizacion> findRange(int[] range);

    int count();
    
}
