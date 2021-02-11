/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.Sileg.entity.Usuario;
import edu.Sileg.facade.UsuarioFacadeLocal;
import edu.Sileg.utilidades.Email;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Usuario
 */



@Named(value = "correoMasivoRequest")
@RequestScoped
public class CorreoMasivoRequest {
    
    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    
    public void envioCorreos(){
        String lista = "";
        List<Usuario> listaUsuario = usuarioFacadeLocal.findAll();
        System.out.println(listaUsuario);
        for(Usuario c : listaUsuario){
            System.out.println(c.getCorreo());
        //"@qq.co,p@hu.co,s@de.co"
        lista += c.getCorreo() + ",";
        }
        Email.sendMasivos(lista);
        
    }
    
    
    public CorreoMasivoRequest() {
    }
    
}
