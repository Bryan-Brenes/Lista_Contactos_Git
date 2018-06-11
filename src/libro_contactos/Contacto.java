/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro_contactos;

import java.util.ArrayList;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author bryan
 */
public class Contacto {
    private String nombre;
    private ImageView imagen;
    private ArrayList<String[]> telefonos;
    private ArrayList<String[]> emails;
    private ArrayList<String[]> sonidos;
    private String direccionFisica;
    private String lugarTrabajo;
    private String lugarEstudio;
    private String notas;
    private String acercaFamilia;
    private String profesion;
    private String fechaCumpleanios;
    private String fechaAniversario;
    private String direccionTrabajo;
    private String entretenimiento;
    private String deportes;
    
    public Contacto(){
        this.nombre = "";
        this.imagen = new ImageView(new Image("libro_contactos/user512.png"));
        this.telefonos = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.sonidos = new ArrayList<>();
        this.direccionFisica = "";
        this.lugarTrabajo = "";
        this.lugarEstudio = "";
        this.notas = "";
        this.acercaFamilia = "";
        this.profesion = "";
        this.fechaCumpleanios = "";
        this.fechaAniversario = "";
        this.direccionTrabajo = "";
        this.entretenimiento = "";
        this.deportes = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = new ImageView(imagen);
        this.imagen.setFitHeight(16);
        this.imagen.setFitWidth(16);
    }

    public ArrayList<String[]> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String tipo, String valor) {
        String[] tel = new String[2];
        tel[0] = tipo;
        tel[1] = valor;
        this.telefonos.add(tel);
    }

    public ArrayList<String[]> getEmails() {
        return emails;
    }

    public void setEmails(String tipo, String valor) {
        String[] email = new String[2];
        email[0] = tipo;
        email[1] = valor;
        this.emails.add(email);
    }

    public ArrayList<String[]> getSonidos() {
        return sonidos;
    }

    public void setSonidos(String nombre, String url) {
        String[] sonido = new String[2];
        sonido[0] = nombre;
        sonido[1] = url;
        this.emails.add(sonido);
    }

    public String getDireccionFisica() {
        return direccionFisica;
    }

    public void setDireccionFisica(String direccionFisica) {
        this.direccionFisica = direccionFisica;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getLugarEstudio() {
        return lugarEstudio;
    }

    public void setLugarEstudio(String lugarEstudio) {
        this.lugarEstudio = lugarEstudio;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getAcercaFamilia() {
        return acercaFamilia;
    }

    public void setAcercaFamilia(String acercaFamilia) {
        this.acercaFamilia = acercaFamilia;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getFechaCumpleanios() {
        return fechaCumpleanios;
    }

    public void setFechaCumpleanios(String fechaCumpleanios) {
        this.fechaCumpleanios = fechaCumpleanios;
    }

    public String getFechaAniversario() {
        return fechaAniversario;
    }

    public void setFechaAniversario(String fechaAniversario) {
        this.fechaAniversario = fechaAniversario;
    }

    public String getDireccionTrabajo() {
        return direccionTrabajo;
    }

    public void setDireccionTrabajo(String direccionTrabajo) {
        this.direccionTrabajo = direccionTrabajo;
    }

    public String getEntretenimiento() {
        return entretenimiento;
    }

    public void setEntretenimiento(String entretenimiento) {
        this.entretenimiento = entretenimiento;
    }

    public String getDeportes() {
        return deportes;
    }

    public void setDeportes(String deportes) {
        this.deportes = deportes;
    }
    
    
    
}//<>
