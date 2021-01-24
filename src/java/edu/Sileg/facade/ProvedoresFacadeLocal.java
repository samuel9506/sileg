/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.Provedores;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface ProvedoresFacadeLocal {

    void create(Provedores provedores);

    void edit(Provedores provedores);

    void remove(Provedores provedores);

    Provedores find(Object id);

    List<Provedores> findAll();

    List<Provedores> findRange(int[] range);

    int count();
    
}
