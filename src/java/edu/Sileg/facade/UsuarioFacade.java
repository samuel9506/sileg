/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.facade;

import edu.Sileg.entity.Usuario;
import edu.Sileg.entity.Usuario_;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Asus
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "SilegPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
        
    }
    public Usuario consultar(BigInteger documento){
        try{
        Query ud = em.createQuery("SELECT x FROM Usuario x WHERE x.documento = :doc");
        ud.setParameter("doc",documento);
        return (Usuario) ud.getSingleResult();
        }
        catch(Exception e){
            return new Usuario();
        }
    }
       @Override
    public Usuario recuperarContrasenia(String correoIn) {
        try {
            Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correoIn ");
            qt.setParameter("correoIn", correoIn);
            return (Usuario) qt.getSingleResult();
        } catch (Exception e) {
            return new Usuario();
        }

    }
        @Override
    public Usuario loginUsuario(String correoIn, String claveIn) {
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correoIn AND u.clave = :claveIn");
            q.setParameter("correoIn", correoIn);
            q.setParameter("claveIn", claveIn);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return new Usuario();
        }
    }

    @Override
    public boolean removerUsuario(int id) {
        boolean retorno = false;
        try {
            Query qt = em.createQuery("DELETE FROM Usuario u WHERE u.id = :id");
            qt.setParameter("id", id);
            int salida = qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return retorno;
        }
    }
    
}
