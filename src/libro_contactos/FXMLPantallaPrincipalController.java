/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro_contactos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
        
        for (int i = 0; i < 10; i++) {
            
            try{
                Label lbl = new Label("item " + i);
                
                //Image imgen = new Image(new FileInputStream("/home/bryan/Imágenes/logos/user"));
                Image imagen = new Image("libro_contactos/user512.png");
                ImageView img = new ImageView(imagen);
                img.setFitHeight(40);
                img.setFitWidth(40);
                lbl.setGraphic(img);
                //lbl.setGraphic(new ImageView(new Image("libro_contactos/user.png")));
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
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
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

    
}
