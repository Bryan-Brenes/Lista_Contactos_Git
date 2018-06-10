/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro_contactos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void salir(){
        Stage stage = (Stage) menubar.getScene().getWindow();
        stage.close();
    }

    
}
