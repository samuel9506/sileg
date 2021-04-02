/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.controler;

import edu.Sileg.entity.Detallesfactura;
import edu.Sileg.entity.Factura;
import edu.Sileg.entity.Productos;
import edu.Sileg.entity.Rol;
import edu.Sileg.entity.Usuario;
import edu.Sileg.facade.DetallesfacturaFacadeLocal;
import edu.Sileg.facade.FacturaFacadeLocal;
import edu.Sileg.facade.ProductosFacadeLocal;
import edu.Sileg.facade.UsuarioFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Asus
 */
@Named(value = "detallesSession")
@SessionScoped
public class DetallesSession implements Serializable {

    /**
     * Creates a new instance of DetallesSession
     */
    public DetallesSession() {
    }

    @EJB
    DetallesfacturaFacadeLocal detallesfacturaFacadeLocal;

    @EJB
    ProductosFacadeLocal productosFacadeLocal;

    @EJB
    FacturaFacadeLocal facturaFacadeLocal;

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    private ArrayList<Productos> listaProductos = new ArrayList<>();
    private ArrayList<Detallesfactura> listaDetalles = new ArrayList<>();
    private ArrayList<Factura> listaFactura = new ArrayList<>();
    private Detallesfactura detalles = new Detallesfactura();
    private Factura factu = new Factura();
    private Integer idproductos;
    private Integer cantidad;
    private Double totalunidades;
    private Integer id;
    private Integer idFactura;
    private Productos productoSeleccionado = new Productos();
    private  List<Factura>listaFacturas=new ArrayList();
    private List<Detallesfactura>listadet=new ArrayList();
    
     @PostConstruct
    public void init(){
       listaFacturas.addAll(facturaFacadeLocal.findAll());
    }
    
    public List<Productos> listarProductos() {
        return productosFacadeLocal.findAll();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioFacadeLocal.findAll();
    }
    
    public List<Usuario> listarClientes(){
          
       return usuarioFacadeLocal.listaCliente(3);
    }

    public void agregar(Productos producto) {
        productoSeleccionado = producto;
    }

    public void agregarProducto() {
        Productos productoSeleccionado = productosFacadeLocal.find(idproductos);
        detalles.setFkProductos(productoSeleccionado);
        listaDetalles.add(detalles);
        detalles = new Detallesfactura();
    }

    public void agregarCarro() {

        detalles.setFkProductos(productoSeleccionado);
        listaDetalles.add(detalles);
        detalles = new Detallesfactura();
    }

   

    public void calcularSubtotal(Detallesfactura det) {

        double subtotal = 0;
        int procantidad=0;
        int cantidadfinal=0;

        if (det.getCantidad() != null) {
            
            procantidad=det.getFkProductos().getExistencias();
            cantidadfinal=procantidad-det.getCantidad();
            subtotal = det.getFkProductos().getPrecioVenta() * det.getCantidad();
            

        }
        det.getFkProductos().setExistencias(cantidadfinal);
        det.setTotalunidades(subtotal);

        double total = 0;
        for (Detallesfactura d : listaDetalles) {

            total += d.getTotalunidades();

        }
        factu.setSubtotal(total);

        double iva = 0;

        iva = total * 20 / 100;

        factu.setIva(iva);

        double totaliva = 0;

        totaliva = total + iva;

        factu.setTotal(totaliva);

        /*
        facturaFacadeLocal.create(factu);
        det.setFkFactura(factu);
        detallesfacturaFacadeLocal.create(det);
         */
    }

    public void agregarFactura() {
        
        Usuario ususel = usuarioFacadeLocal.find(id);
        factu.setIdUsuario(ususel);
        facturaFacadeLocal.create(factu);

        for (Detallesfactura d : listaDetalles) {
            d.setFkFactura(factu);
            detallesfacturaFacadeLocal.create(d);
            
        }
        factu = new Factura();
        listaDetalles = new ArrayList<Detallesfactura>();
       

    }
    
     public void editar(Detallesfactura detallesfactura){
        detalles = detallesfactura;
    }
    
    public void editarDetalles(){
        detalles.setFkFactura(facturaFacadeLocal.find(idFactura));
        detallesfacturaFacadeLocal.edit(detalles);
        listaDetalles.clear();
        listaDetalles.addAll(detallesfacturaFacadeLocal.findAll());
        
        
    }
    
      public void eliminaDetalles(Detallesfactura detallesfactura){
         String message="";
      
        try{
           detallesfacturaFacadeLocal.remove(detalles);
        listaDetalles.remove(detalles); 
        }catch (Exception e) {
       message =  "swall('Algo inesperado sucedio!', 'este producto no puede ser eliminado!', 'Intenta eliminar un producto que no este asociado a las ventas')";
            
        }
        PrimeFaces.current().executeScript(message);
      }
    
     public void descargaListado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sileg", "root","");
           
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/Sileg/reportes/listaFacturas.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Certificado.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }
        }
     
     public void mostrarDetalles( int idfactura){
         
        Factura fac = facturaFacadeLocal.find(idfactura);
        listadet = fac.getDetalles();
       
     }
     
      public void descargaCertificado( int idFactu) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("idFactu",idFactu);
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sileg","root","");
           
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/Sileg/reportes/reporteFactura.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=CertificadoIndividual.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }

    }
      

    public Detallesfactura getDetalles() {
        return detalles;
    }

    public void setDetalles(Detallesfactura detalles) {
        this.detalles = detalles;
    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<Detallesfactura> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(ArrayList<Detallesfactura> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public Integer getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(Integer idproductos) {
        this.idproductos = idproductos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotalunidades() {
        return totalunidades;
    }

    public void setTotalunidades(Double totalunidades) {
        this.totalunidades = totalunidades;
    }

    public Factura getFactu() {
        return factu;
    }

    public void setFactu(Factura factu) {
        this.factu = factu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(ArrayList<Factura> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Productos getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Productos productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public List<Detallesfactura> getListadet() {
        return listadet;
    }

    public void setListadet(List<Detallesfactura> listadet) {
        this.listadet = listadet;
    }

  
   
    }
    


