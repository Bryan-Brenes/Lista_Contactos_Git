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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author bryan
 */
public class FXMLPantallaVerController implements Initializable {

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
    private JFXButton agregar_numero;
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
    private JFXButton agregar_correo;
    @FXML
    private FontAwesomeIconView agregarCorreo_btn;
    @FXML
    private JFXComboBox<?> sonidoCombobox;
    @FXML
    private JFXButton play_btn;
    @FXML
    private JFXTextArea direccionFisica;
    @FXML
    private JFXTextArea LugarTrabajo;
    @FXML
    private JFXTextArea lugarEstudio;
    @FXML
    private VBox Vbox_camposOpcionales;
    @FXML
    private JFXButton agregarCampos_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void salir(ActionEvent event) {
    }

    @FXML
    private void cambiarPantalla_a_principal(ActionEvent event) {
    }

    @FXML
    private void cargarImagen(MouseEvent event) {
    }

    @FXML
    private void verificarTelefono(KeyEvent event) {
    }

    @FXML
    private void añadirTelefono(ActionEvent event) {
    }

    @FXML
    private void verificarCorreo(KeyEvent event) {
    }

    @FXML
    private void añadirCorreo(ActionEvent event) {
    }

    @FXML
    private void reproducirSonido(ActionEvent event) {
    }

    @FXML
    private void mostrarPopUp(ActionEvent event) {
    }
    
}
