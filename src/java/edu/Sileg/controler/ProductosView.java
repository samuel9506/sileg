/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.controler;



import edu.Sileg.entity.Categoria;
import edu.Sileg.entity.Productos;
import edu.Sileg.facade.CategoriaFacadeLocal;
import edu.Sileg.facade.ProductosFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;




/**
 *
 * @author Asus
 */
@Named(value = "productosView")
@ViewScoped
public class ProductosView  implements Serializable {
    
    
    
    @EJB
    ProductosFacadeLocal productosFacadeLocal;
    
    @EJB
    CategoriaFacadeLocal  categoriaFacadeLocal;
    
    @Inject
    CategoriasView categoriasView;
    
    
   
    private Productos regProductos = new Productos();
    private ArrayList<Productos> listaProductos = new ArrayList<>();
    private int id_categoria = 0;
    private Productos productoEditar = new Productos();
    private Integer idcategoria;
    
    @PostConstruct
    public void cargaInicialProductos() {
        listaProductos.addAll(productosFacadeLocal.listaProductosporcategoria(1));
    }
    
    public void registrarProductos() {
        String mensajeRequest = "";
        try {
            Categoria cateIn = categoriaFacadeLocal.find(id_categoria);
            regProductos.setFkCategoria(cateIn);
            regProductos.setImagenRuta(categoriasView.getRutaImg());
            productosFacadeLocal.create(regProductos);
            listaProductos.add(regProductos);
            mensajeRequest = "swal('Registro', 'Exitoso !!!!', 'success');";

        } catch (Exception e) {
            System.out.println("Error AdministradorView:registrarProductos " + e.getMessage());
            mensajeRequest = "swal('  producto  no se pudo registrar', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
       
        
    }
    
    public void eliminarProducto( Productos proremov){
        
        String mensajeAlerta = "";
        try {
            productosFacadeLocal.removerProductos(proremov.getIdproductos());
            listaProductos.remove(proremov);
            mensajeAlerta = "swal('Removido el usuario', '" + proremov.getNombreProducto() + "', 'success');";
        } catch (Exception e) {
            mensajeAlerta = "swal('Problemas eliminando a ', '" + proremov.getNombreProducto() + "', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeAlerta);

    }
     public void editar(Productos producto){
        productoEditar = producto;
    }
    
    public void actualizarProducto(){
         String mensajeAlerta = "";
        try {
        productoEditar.setFkCategoria(categoriaFacadeLocal.find(idcategoria));
        productosFacadeLocal.edit(productoEditar);
        listaProductos.clear();
        listaProductos.addAll(productosFacadeLocal.findAll());
         mensajeAlerta = "swal('Actualizado el producto', '" + productoEditar.getNombreProducto() + "', 'success');";
           } catch (Exception e) {
          mensajeAlerta = "swal('Problemas al modificar ','" + productoEditar.getNombreProducto();
           
        }
        PrimeFaces.current().executeScript(mensajeAlerta);
    }
      public List<Categoria> traeCategoria (){
        return categoriaFacadeLocal.findAll();
    }
      
         public List<Productos> listaProductos (){
        return productosFacadeLocal.findAll();
    }
  
  

     public ProductosView() {
     }
      public void insertarXLS(List cellDataList) {
        try {
            int filasContador = 0;
            for (int i = 0; i < cellDataList.size(); i++) {
                List cellTemp = (List) cellDataList.get(i);
                Productos newP = new Productos();
                for (int j = 0; j < cellTemp.size(); j++) {
                    XSSFCell hssfCell = (XSSFCell) cellTemp.get(j);                   
                    switch (filasContador) {
                        case 0:
                            newP.setNombreProducto(hssfCell.toString());
                            filasContador++;
                            break;
                        case 1:
                            newP.setPresentacion(hssfCell.toString());
                            filasContador++;
                            break;
                        case 2:
                            newP.setFechacompra(hssfCell.getDateCellValue());
                            filasContador++;
                            break;
                        case 3:
                            newP.setExistencias(hssfCell.getCachedFormulaResultType());
                            filasContador++;
                            break;
                        case 4:
                            newP.setPrecioCompra(hssfCell.getNumericCellValue());
                            filasContador++;
                            break;
                        case 5:
                            newP.setPrecioVenta(hssfCell.getNumericCellValue());
                            filasContador++;
                            break;
                        case 6:
                            Categoria nueva = categoriaFacadeLocal.find((int) Math.floor(hssfCell.getNumericCellValue()));
                            newP.setFkCategoria(nueva);
                            productosFacadeLocal.create(newP);
                            filasContador = 0;
                            break;
                    }

                }
            }

        } catch (Exception e) {
        }
    }

    
  
     public void cargaProductos(FileUploadEvent event) throws IOException {
        InputStream input = event.getFile().getInputStream();
        List cellData = new ArrayList();
        try {
            XSSFWorkbook workBook = new XSSFWorkbook(input);
            XSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTemp = new ArrayList();
                while (iterator.hasNext()) {
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTemp.add(hssfCell);
                }
                cellData.add(cellTemp);
            }
          insertarXLS(cellData);
            
        } catch (IOException e) {
            System.out.println("edu.webapp1966781b.controlador.ProductosView.cargaListaProductos() " + e.getMessage());
            PrimeFaces.current().executeScript("swal('Problemas ingresando el archivo' , 'error');");
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("index.xhtml");
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
           
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/Sileg/reportes/listaProductos.jasper"));

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
   
       
  
    public Productos getRegProductos() {
        return regProductos;
    }

    public void setRegProductos(Productos regProductos) {
        this.regProductos = regProductos;
    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }  

    public Productos getProductoEditar() {
        return productoEditar;
    }

    public void setProductoEditar(Productos productoEditar) {
        this.productoEditar = productoEditar;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }
    
  
    
}
