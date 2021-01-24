/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.Productosespeciales;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface ProductosespecialesFacadeLocal {

    void create(Productosespeciales productosespeciales);

    void edit(Productosespeciales productosespeciales);

    void remove(Productosespeciales productosespeciales);

    Productosespeciales find(Object id);

    List<Productosespeciales> findAll();

    List<Productosespeciales> findRange(int[] range);

    int count();
    
}
