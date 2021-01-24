/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.Categoriasolicitudes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface CategoriasolicitudesFacadeLocal {

    void create(Categoriasolicitudes categoriasolicitudes);

    void edit(Categoriasolicitudes categoriasolicitudes);

    void remove(Categoriasolicitudes categoriasolicitudes);

    Categoriasolicitudes find(Object id);

    List<Categoriasolicitudes> findAll();

    List<Categoriasolicitudes> findRange(int[] range);

    int count();
    
}
