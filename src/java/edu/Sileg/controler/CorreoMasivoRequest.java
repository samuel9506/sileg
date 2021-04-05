package edu.Sileg.controler;

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
     private String mensaje2;
     private String asunto2;
     
    public void envioCorreos(){
        String lista = "";
        List<Usuario> listaUsuario = usuarioFacadeLocal.findAll();
        System.out.println(listaUsuario);
        for(Usuario c : listaUsuario){
            System.out.println(c.getCorreo());
        //"@qq.co,p@hu.co,s@de.co"
        lista += c.getCorreo() + ",";
        
        }
        Email.sendMasivos(lista, asunto2, mensaje2);
        
    }
    
    
    public CorreoMasivoRequest() {
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public String getAsunto2() {
        return asunto2;
    }

    public void setAsunto2(String asunto2) {
        this.asunto2 = asunto2;
    }
    
}
