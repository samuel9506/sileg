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
import edu.Sileg.utilidades.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Asus
 */
@Named(value = "registroRequest")
@RequestScoped
public class RegistroRequest implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    RolFacadeLocal rolFacadeLocal;

    private Usuario usuReg = new Usuario();
    private String correoIn = "";
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private int id_rol=0;
    private Integer idrol;
    private ArrayList<Rol> listaroles = new ArrayList<>();

    public RegistroRequest() {

    }

    public List<Rol> listarrol() {
      return rolFacadeLocal.findAll();
    }

    public void registroUsuario() {

        String mensajeRequest;

        try {
            usuReg.setFechaRegistro(new Date());
            Rol selectrol = rolFacadeLocal.find(id_rol);
            usuReg.setFkRol(selectrol);
            usuarioFacadeLocal.create(usuReg);
            mensajeRequest = "swal('Registro', 'Exitoso !!!!', 'success');";
        } catch (Exception e) {
            System.out.println("Error RegistroRequest:registrarUsuario " + e.getMessage());
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        usuReg = new Usuario();
    }

    public void recuperarClave() {
        String mensajeRequest = "";
        Usuario usuRec = new Usuario();
        try {
            usuRec = usuarioFacadeLocal.recuperarContrasenia(correoIn);
            int claveNew = (int) (Math.random() * 100000);
            usuRec.setClave("RC-" + claveNew);
            usuarioFacadeLocal.edit(usuRec);
            mensajeRequest = "swal('Su clave', 'Se envio al correo registrado !!!!', 'success');";
            Email.sendClaves(usuRec.getCorreo(),
                    usuRec.getNombres() + " " + usuRec.getApellidos(),
                    correoIn, "RC-" + claveNew);
        } catch (Exception e) {
            System.out.println("Error RegistroRequest:recuperarClave " + e.getMessage());
            mensajeRequest = "swal('Correo NO', 'registrado', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        correoIn = "";
    }

    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
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
