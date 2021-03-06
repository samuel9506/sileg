/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Sileg.controler;

import edu.Sileg.entity.CatProductos;
import edu.Sileg.entity.Productos;
import edu.Sileg.facade.CatProductosFacadeLocal;
import edu.Sileg.facade.ProductosFacadeLocal;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import org.primefaces.PrimeFaces;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author Asus
 */
@Named(value = "categoriasView")
@ViewScoped
public class CategoriasView implements Serializable {

    @EJB
    CatProductosFacadeLocal catProductosFacadeLocal;
    
    @EJB
    ProductosFacadeLocal productoFacadeLocal;

    private ArrayList<CatProductos> listaCategorias = new ArrayList<>();
    private Part filePart;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private CatProductos categoriaSelect = new CatProductos();
    private String rutaImg = "Mercado\\20201202184013608.jpg";

    public CategoriasView() {

    }


    @PostConstruct
    public void cargaCategorias() {
        try {
            listaCategorias.addAll(catProductosFacadeLocal.findAll());
            categoriaSelect = catProductosFacadeLocal.find(1);
        } catch (Exception e) {
            System.out.println("edu.webapp1966781b.controlador.CategoriasView.cargaCategorias() " + e.getMessage());
        }
    }

    public void seleccionCategoria(int id) {
        try {
            categoriaSelect = catProductosFacadeLocal.find(id);
        } catch (Exception e) {
            System.out.println("edu.webapp1966781b.controlador.CategoriasView.seleccionCategoria() " + e.getMessage());
        }
    }

    public int cantidadproductoscategoria(int fk_produto) {
        try {
            return catProductosFacadeLocal.cantidadProductoCategoria(fk_produto);
        } catch (Exception e) {
            System.out.println("edu.webapp1966781b.controlador.CategoriasView.catidadproductoscategoria() " + e.getMessage());
            return 0;
        }
    }

    public List<Productos> listaProductos() {
        try {
            return productoFacadeLocal.listaProductosporcategoria(categoriaSelect.getIdcategoriasProductos());
        } catch (Exception e) {
            System.out.println("edu.webapp1966781b.controlador.CategoriasView.listaProductos() " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void accion(String carpeta) {
        String mensajes = "";

        if (filePart != null) {
            if (filePart.getSize() > 4000000) {
                mensajes = "swal('Tamaño del archivo muy Grande !!', 'Intente de nuevo', 'error');";
            } else if (!"image/jpeg".equals(filePart.getContentType())) {
                mensajes = "swal('El archivo no es una imagen valida !!', 'Intente de nuevo', 'error');";
            }
            if (mensajes.equals("")) {
                CatProductos catSelect = catProductosFacadeLocal.find(Integer.parseInt(carpeta));
                File folder = new File("C:/imgdashio/Productos/Categorias/" + catSelect.getNombre());
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                try (InputStream is = filePart.getInputStream()) {
                    Calendar hoy = Calendar.getInstance();
                    String renombraArchivo = sdf.format(hoy.getTime()) + ".";
                    renombraArchivo += FilenameUtils.getExtension(filePart.getSubmittedFileName());
                    Files.copy(is, (new File(folder, renombraArchivo)).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    rutaImg = catSelect.getNombre() + "\\" + renombraArchivo;
                } catch (Exception e) {
                    mensajes = "swal('Por favor !!', 'Intente de nuevo', 'error');";
                }
            }
        } else {
            mensajes = "swal('Por favor !!', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajes);
    }

    public ArrayList<CatProductos> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<CatProductos> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public Part getFilePart() {
        return filePart;
    }

    public void setFilePart(Part filePart) {
        this.filePart = filePart;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public CatProductos getCategoriaSelect() {
        return categoriaSelect;
    }

    public void setCategoriaSelect(CatProductos categoriaSelect) {
        this.categoriaSelect = categoriaSelect;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }
    

}
