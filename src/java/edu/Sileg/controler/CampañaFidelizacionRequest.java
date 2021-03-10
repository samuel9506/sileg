/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.controler;

import edu.Sileg.entity.Campañasfidelizacion;
import edu.Sileg.facade.CampañasfidelizacionFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.POST;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Usuario
*/
@Named(value = "campañaFidelizacionRequest")
@RequestScoped
public class CampañaFidelizacionRequest implements Serializable {
    
    private String mensaje = "";
    public CampañaFidelizacionRequest() {
    }
    
    @EJB
    CampañasfidelizacionFacadeLocal campañasfidelizacionFacadeLocal;
    private Campañasfidelizacion campfid = new Campañasfidelizacion();
    private Campañasfidelizacion campfidEditar = new Campañasfidelizacion();
    private List<Campañasfidelizacion> listaCampañas = new ArrayList();
   
    
    
    @PostConstruct
    
    public void ini(){
         //listaCampañas = [{id:1,titulo:hfgsjhg,descripcion:ghjhjj},{id:2,titulo:hfgsjhg,descripcion:ghjhjj}]//
        listaCampañas = campañasfidelizacionFacadeLocal.findAll();
    }

    public void setCampfid(Campañasfidelizacion campfid) {
        this.campfid = campfid;
    }

    public Campañasfidelizacion getCamfid() {
        return this.campfid;
    }
    
    
    public void crearCampaña(){
        campañasfidelizacionFacadeLocal.create(campfid);
        listaCampañas.add(campfid);
        mensaje =  "swal('Correcto!', 'Campaña creada!', '')";
        PrimeFaces.current().executeScript(mensaje);
    }
    
    
    
    
    
    
    public void eliminarCampaña(Campañasfidelizacion c){
       campañasfidelizacionFacadeLocal.remove(c);
       listaCampañas.clear();
       listaCampañas = campañasfidelizacionFacadeLocal.findAll();
       mensaje =  "swal('Peligro!', 'Campaña eliminada!', '')";
       PrimeFaces.current().executeScript(mensaje);
    }

    public List<Campañasfidelizacion> getListaCampañas() {
        return listaCampañas;
    }

    public void setListaCampañas(List<Campañasfidelizacion> listaCampañas) {
        this.listaCampañas = listaCampañas;
    }
   
    public void editarCampañas(Campañasfidelizacion e){
        campfidEditar = e;
        
    }
    
    public void actualizarCampañas(){
        campañasfidelizacionFacadeLocal.edit(campfidEditar);
        listaCampañas.clear();
        listaCampañas = campañasfidelizacionFacadeLocal.findAll();
        mensaje =  "swal('Cambio Exitoso!', 'Campaña Actualizada!', '')";
       PrimeFaces.current().executeScript(mensaje);
    }

    public Campañasfidelizacion getCampfidEditar() {
        return campfidEditar;
    }

    public void setCampfidEditar(Campañasfidelizacion campfidEditar) {
        this.campfidEditar = campfidEditar;
    }
    
}
