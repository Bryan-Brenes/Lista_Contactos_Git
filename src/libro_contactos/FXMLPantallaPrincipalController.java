/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro_contactos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.sun.javafx.binding.StringFormatter;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bryan
 */
public class FXMLPantallaPrincipalController implements Initializable {
    
    Contactos_singleton contactos;

    @FXML
    private MenuItem nuevo_btn_menu;
    @FXML
    private MenuItem guardar_btn_menu;
    @FXML
    private MenuItem salir_btn_menu;
    @FXML
    private MenuItem acercaDe_btn_menu;
    @FXML
    private MenuItem manual_btn_menu;
    @FXML
    private MenuBar menubar;
    @FXML
    private JFXListView<Label> listView;
    @FXML
    private JFXButton nuevo_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        contactos = Contactos_singleton.getInstance();
        
        listView.setExpanded(true);
        listView.depthProperty().set(2);
        
        for (Contacto contacto: contactos.getListaContactos()) {
            
            try{
                StringBuilder sb = new StringBuilder();
                Formatter sf = new Formatter(sb);
                String numeroTel = " ";
                if (!contacto.getTelefonos().isEmpty()) {
                    numeroTel = contacto.getTelefonos().get(0)[1];
                }
                String string = sf.format("%1$5s %2$-50s %3$10s %4$-10s"," ", contacto.getNombre().trim(), " ", numeroTel).toString();
                
                Label lbl = new Label(string);
                lbl.setId(contacto.getNombre());
                
                ImageView img = contacto.getImagen();
                img.setFitHeight(40);
                img.setFitWidth(40);
                lbl.setGraphic(img);

                listView.getItems().add(lbl);
                
                
            }catch(Exception e){
                System.out.println("no se encontro la imagen");
            }
        }
          
    }    //<>
    
    @FXML
    public void salir(){
        Stage stage = (Stage) menubar.getScene().getWindow();
        stage.close();
    }
    
    public void eliminar(){
        String nombreID = " ";
        try {
            nombreID = listView.getSelectionModel().getSelectedItem().getId();
            listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            
        }
        
        if (nombreID.equals(" ")) {
            return;
        }
        
        Contacto contact = new Contacto();
        for (int i = 0; i < contactos.getListaContactos().size(); i++) {
            if (contactos.getListaContactos().get(i).getNombre().equals(nombreID)) {
                contact = contactos.getListaContactos().get(i);
                break;
            }
        }
        
        try {
            contactos.getListaContactos().remove(contact);
        } catch (Exception e) {
            System.out.println("Error al eliminar contacto de la lista");
        }
        
    }
    
    public void cambiarPantalla_a_agregar(){
        Stage stage = (Stage) listView.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLNuevoContacto.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cambiarPantalla_a_ver(){
        Stage stage = (Stage) listView.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPantallaVer.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            //Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
