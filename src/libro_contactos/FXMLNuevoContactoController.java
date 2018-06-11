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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;

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
    private JFXComboBox<Label> tipo_telefono;
    @FXML
    private JFXTextField telefono_textField;
    @FXML
    private FontAwesomeIconView agregarTelefono_btn;
    @FXML
    private VBox Vbox_correos;
    @FXML
    private HBox Hbox_correos;
    @FXML
    private JFXComboBox<Label> correo_tipo;
    @FXML
    private JFXTextField correo_TextField;
    @FXML
    private FontAwesomeIconView agregarCorreo_btn;
    @FXML
    private JFXComboBox<String> sonidoCombobox;
    @FXML
    private JFXTextArea direccionFisica;
    @FXML
    private JFXTextArea LugarTrabajo;
    @FXML
    private JFXTextArea lugarEstudio;
    @FXML
    private JFXButton agregarCampos_btn;
    
    @FXML
    private JFXButton agregar_numero;
    @FXML
    private JFXButton agregar_correo;
    @FXML
    private JFXButton guardar_btn;
    @FXML
    private JFXButton cancelar_btn;
    
    private Contacto contacto;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializacion del singleton de contactos
        contacto = new Contacto();
        
        //Poblando los combobox de tipo
        tipo_telefono.getItems().addAll(new Label("Móvil"), new Label("Casa"),
                new Label("Fax"), new Label("Trabajo"), new Label("Otro"));
        tipo_telefono.getSelectionModel().select(0);
        
        correo_tipo.getItems().addAll(new Label("Personal"), new Label("Trabajo"),
                new Label("Otro"));
        correo_tipo.getSelectionModel().select(0);
        
        /*sonidoCombobox.getItems().addAll(new Label("Silencio"), new Label("sonido1"),
                new Label("Sonido2"), new Label("sonido3"), new Label("sonido4"),
                new Label("sonido5"));*/
        sonidoCombobox.getItems().addAll("Silencio", "Sonido1", "Sonido 2", "Sonido 3",
                "Sonido4", "Sonido 5");
        sonidoCombobox.getSelectionModel().select(0);
        


        
    }   //<>
    
    @FXML
    public void salir(){
        Stage stage = (Stage) imageView.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void cambiarPantalla_a_principal(){
        Stage stage = (Stage) imageView.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPantallaPrincipal.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /*Esta funcion se encarga de cargar la imagen desde un directorio
    y mostrarla en la pantalla, además de modificar el contacto que se está creando
    */
    
    @FXML
    public void cargarImagen(){
        Stage stage = (Stage) imageView.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        //String urlImagen = file.getAbsolutePath();
        
        Image image = new Image("libro_contactos/user512.png");
        
        try{
            BufferedImage bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            Image imagen = image;
            contacto.setImagen(imagen);
            imageView.setImage(image);
            }catch(IOException e){
                System.out.println(e);
            }
        
    }
    
}
