/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro_contactos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bryan
 */
public class FXMLNuevoContactoController implements Initializable {

    @FXML
    private MenuItem salir_btn;
    @FXML
    private MenuItem acercaDe_btn;
    @FXML
    private MenuItem manual_btn;
    @FXML
    private JFXButton atras_btn;
    @FXML
    private ImageView imageView;
    @FXML
    private VBox datosVbox;
    @FXML
    private JFXTextField nombre_TextField;
    @FXML
    private VBox Vbox_telefonos;
    @FXML
    private HBox Hbox_telefonos;
    @FXML
    private JFXComboBox<?> tipo_telefono;
    @FXML
    private JFXTextField telefono_textField;
    @FXML
    private FontAwesomeIconView agregarTelefono_btn;
    @FXML
    private VBox Vbox_correos;
    @FXML
    private HBox Hbox_correos;
    @FXML
    private JFXComboBox<?> correo_tipo;
    @FXML
    private JFXTextField correo_TextField;
    @FXML
    private FontAwesomeIconView agregarCorreo_btn;
    @FXML
    private JFXComboBox<?> sonidoCombobox;
    @FXML
    private JFXTextArea direccionFisica;
    @FXML
    private JFXTextArea LugarTrabajo;
    @FXML
    private JFXTextArea lugarEstudio;
    @FXML
    private JFXButton agregarCampos_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void salir(){
        Stage stage = (Stage) imageView.getScene().getWindow();
        stage.close();
    }
    
    public void cambiarPantalla_a_principal(){
        Stage stage = (Stage) imageView.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPantallaPrincipal.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
