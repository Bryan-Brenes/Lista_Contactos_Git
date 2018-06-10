/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro_contactos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        
        listView.setExpanded(true);
        listView.depthProperty().set(2);
        
        for (int i = 0; i < 10; i++) {
            
            try{
                Label lbl = new Label("item " + i);
                
                Image imgen = new Image(new FileInputStream("/home/bryan/Imágenes/logos/153744-1.png"));
                ImageView img = new ImageView(imgen);
                img.setFitHeight(25);
                img.setFitWidth(25);
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
    

    
}
