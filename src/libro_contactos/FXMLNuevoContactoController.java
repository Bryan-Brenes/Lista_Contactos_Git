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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
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
    
    private ArrayList<JFXButton> listabotones;
    private ArrayList<JFXButton> listabotonesCorreo;
    
    /*Se debe crear variables globales para almacenar los elementos graficos de 
    manera que se referencien mediante el id, sino con esa variable.
    Esto porque los elementos no son fijos.*/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listabotones = new ArrayList<>();
        listabotonesCorreo = new ArrayList<>();
        
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
    
    public void añadirTelefono(){
        
        Vbox_telefonos.getChildren().add(crearHbox("telefono"));
        System.out.println("Tamaño listabotones: " + listabotones.size());
        
        for (int i = 0; i < listabotones.size() - 1; i++) {
            listabotones.get(i).setVisible(false);
        }
        
        if (listabotones.size() == 1) {
            FontAwesomeIconView icon2 = (FontAwesomeIconView)agregar_numero.getGraphic();
            icon2.setIcon(FontAwesomeIcon.TRASH);
            agregar_numero.setOnAction(e -> removerTelefono());
        }
    }
    
    public void removerTelefono(){
        System.out.println("Tamaño listabotones: " + listabotones.size());
        if (listabotones.size() == 1) {
            Parent root = listabotones.get(0).getParent();
            Vbox_telefonos.getChildren().remove(root);
            listabotones.clear();
            
            FontAwesomeIconView icon2 = (FontAwesomeIconView)agregar_numero.getGraphic();
            icon2.setIcon(FontAwesomeIcon.PLUS_CIRCLE);
            agregar_numero.setOnAction(e -> añadirTelefono());
        }else{
            Parent root = listabotones.get(listabotones.size()-2).getParent();
            Vbox_telefonos.getChildren().remove(root);
            listabotones.remove(listabotones.size() - 2);
        }
    }
    
    public void añadirCorreo(){
        Vbox_correos.getChildren().add(crearHbox("correo"));
        
        for (int i = 0; i < listabotonesCorreo.size() - 1; i++) {
            listabotonesCorreo.get(i).setVisible(false);
        }
        
        if (listabotonesCorreo.size() == 1) {
            FontAwesomeIconView icon2 = (FontAwesomeIconView)agregar_correo.getGraphic();
            icon2.setIcon(FontAwesomeIcon.TRASH);
            agregar_correo.setOnAction(e -> removerCorreo());
        }
    }
    
    public void removerCorreo(){
        if (listabotonesCorreo.size() == 1) {
            Parent root = listabotonesCorreo.get(0).getParent();
            Vbox_correos.getChildren().remove(root);
            listabotonesCorreo.clear();
            
            FontAwesomeIconView icon2 = (FontAwesomeIconView)agregar_correo.getGraphic();
            icon2.setIcon(FontAwesomeIcon.PLUS_CIRCLE);
            agregar_correo.setOnAction(e -> añadirCorreo());
        }else{
            Parent root = listabotonesCorreo.get(listabotonesCorreo.size()-2).getParent();
            Vbox_correos.getChildren().remove(root);
            listabotonesCorreo.remove(listabotonesCorreo.size() - 2);
        }
    }
    
    public HBox crearHbox(String tipo){
        HBox hbox = new HBox(25);
        if (tipo.equals("correo")) {
            JFXComboBox<Label> correos = new JFXComboBox();
            correos.getItems().addAll(new Label("Personal"), new Label("Trabajo"),
                new Label("Otro"));
            correos.getSelectionModel().select(0);
            correos.setPrefSize(USE_COMPUTED_SIZE, 26);
            
            JFXTextField correo_TextField = new JFXTextField();
            correo_TextField.setPromptText("correo");
            correo_TextField.setPrefSize(120, 26);
            correo_TextField.setLabelFloat(true);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
            icon.setSize("16");
            JFXButton add_btn = new JFXButton("",icon);
            listabotonesCorreo.add(add_btn);
            add_btn.setOnAction(e-> añadirCorreo());
            
            hbox.setPrefWidth(100);
            
            hbox.getChildren().addAll(correos, correo_TextField, add_btn);
        }else{
            JFXComboBox<Label> telefonos = new JFXComboBox();
            telefonos.getItems().addAll(new Label("Móvil"), new Label("Casa"),
                new Label("Fax"), new Label("Trabajo"), new Label("Otro"));
            telefonos.getSelectionModel().select(0);
            telefonos.setPrefSize(USE_COMPUTED_SIZE, 26);
            
            JFXTextField telefono_TextField = new JFXTextField();
            telefono_TextField.setPromptText("teléfono");
            telefono_TextField.setPrefSize(120, 26);
            telefono_TextField.setLabelFloat(true);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
            icon.setSize("16");
            JFXButton add_btn = new JFXButton("",icon);
            /*FontAwesomeIconView icon2 = (FontAwesomeIconView)add_btn.getGraphic();
            icon2.setIcon(FontAwesomeIcon.CHECK);*/
            listabotones.add(add_btn);
            add_btn.setOnAction(e-> añadirTelefono());
            
            hbox.setPrefWidth(100);
            
            hbox.getChildren().addAll(telefonos, telefono_TextField, add_btn);
        }
        return hbox;
    }

    
}
