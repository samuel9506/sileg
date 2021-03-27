

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.controler;


import edu.Sileg.entity.Rol;
import edu.Sileg.entity.Usuario;
import edu.Sileg.facade.RolFacadeLocal;
import edu.Sileg.facade.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;


/**
 *
 * @author Usuario
 */
@Named(value = "administradorView")
@ViewScoped
public class AdministradorView implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private Usuario usuarioSelect = new Usuario();
    private Usuario usuReg = new Usuario();
    private int id_rol=0;
    private Integer idrol;
    private ArrayList<Rol> listaroles = new ArrayList<>();
    

    @Inject
    UsuarioSesion usuarioSesion;
    
      @EJB
    RolFacadeLocal rolFacadeLocal;

    @PostConstruct
    public void cargaUsuarios() {
        listaUsuarios.addAll(usuarioFacadeLocal.findAll());
    }

    public void removerUsuario(Usuario usuario) {
        String mensajeAlerta = "";
        try {
            usuarioFacadeLocal.remove(usuario);
            listaUsuarios.remove(usuario);
            mensajeAlerta = "swal('Removido el usuario', '" + usuario.getNombres() + ' ' + usuario.getApellidos() + "', 'success');";
        } catch (Exception e) {
            mensajeAlerta = "swal('Problemas eliminando a ', '" + usuario.getNombres() + ' ' + usuario.getApellidos() + "', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeAlerta);

    }

    public void usuarioSeleccionado(Usuario usuSelect) {
        usuarioSelect = usuSelect;
    }

    public void actualizarUsuario() {
        String mensajeAlerta = "";
        try {
            usuarioFacadeLocal.edit(usuarioSelect);
            listaUsuarios.clear();
            listaUsuarios.addAll(usuarioFacadeLocal.findAll());

            mensajeAlerta = "swal('Actualizado el usuario', '" + usuarioSelect.getNombres() + ' ' + usuarioSelect.getApellidos() + "', 'success');";
        } catch (Exception e) {
            mensajeAlerta = "swal('Problemas Actualizando a ', '" + usuarioSelect.getNombres() + ' ' + usuarioSelect.getApellidos() + "', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeAlerta);

    }

    public void registrarUsuario() {
        String mensaje="";
        try {
            usuReg.setFechaRegistro(new Date());
            Rol selectrol = rolFacadeLocal.find(id_rol);
            usuReg.setFkRol(selectrol);
            usuarioFacadeLocal.create(usuReg);
            listaUsuarios.clear();
            listaUsuarios.addAll(usuarioFacadeLocal.findAll());
            mensaje = "swal('Registro', 'Exitoso !!!!', 'success');";
        } catch (Exception e) {
            System.out.println("Error RegistroRequest:registrarUsuario " + e.getMessage());
            mensaje = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensaje);
        usuReg = new Usuario();
    }

  

    public AdministradorView() {
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuarioSelect() {
        return usuarioSelect;
    }

    public void setUsuarioSelect(Usuario usuarioSelect) {
        this.usuarioSelect = usuarioSelect;
    }

    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public ArrayList<Rol> getListaroles() {
        return listaroles;
    }

    public void setListaroles(ArrayList<Rol> listaroles) {
        this.listaroles = listaroles;
    }

  

}
