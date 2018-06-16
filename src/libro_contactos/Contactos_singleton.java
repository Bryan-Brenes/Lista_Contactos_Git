/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro_contactos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author bryan
 */
public class Contactos_singleton implements Serializable {
    
    private ArrayList<Contacto> listaContactos;
    
    private Contacto contactoSeleccionado;
    
    private static Contactos_singleton instance = new Contactos_singleton();
    
    private Contactos_singleton(){
        this.listaContactos = new ArrayList<>();
        this.contactoSeleccionado = new Contacto();
    }
    
    public static Contactos_singleton getInstance(){
        return instance;
    }

    public ArrayList<Contacto> getListaContactos() {
        return listaContactos;
    }
    
    public void setListaContactos(ArrayList<Contacto> lista){
        this.listaContactos = lista;
    }

    public void agregarContacto(Contacto contacto, int indice) {
        this.listaContactos.add(indice, contacto);
    }
    
    public void agregarContacto(Contacto contacto) {
        this.listaContactos.add(contacto);
    }
    
    public void eliminarContacto(int indice){
        this.listaContactos.remove(indice);
    }
    
    public boolean eliminarContacto(String nombre){
        
        int index = -1;
        
        for (Contacto contacto : this.listaContactos) {
            if (contacto.getNombre().equals(nombre)) {
                index = this.listaContactos.indexOf(contacto);
                break;
            }
        }
        if(index != -1)
            this.listaContactos.remove(index);
        else
            return false;
        
        return true;
    }
    
    public void setContactoSeleccionado(Contacto contacto){
        this.contactoSeleccionado = contacto;
    }
    
    public Contacto getContactoSeleccionado(){
        return this.contactoSeleccionado;
    }
    
}
