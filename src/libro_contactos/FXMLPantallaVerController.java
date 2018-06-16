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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
    private ImageView imageView;
    @FXML
    private VBox datosVbox;
    @FXML
    private MenuBar menubar;
    
    private Contacto contacto;
    
    private Contactos_singleton contactos;
    @FXML
    private ImageView imageView2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactos = Contactos_singleton.getInstance();
        contacto = contactos.getContactoSeleccionado();
        
        inicializarInfoUsuario();
    } 
    
    public void inicializarInfoUsuario(){
        
        imageView2.setImage(contacto.getImagen().getImage());
        
        HBox NombreHbox = new HBox(25);
        NombreHbox.setPadding(new Insets(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, 15));
        Label tituloNombre = new Label("Nombre: ");
        Label valorNombre = new Label(contacto.getNombre());
        NombreHbox.getChildren().addAll(tituloNombre, valorNombre);
        datosVbox.getChildren().add(NombreHbox);
        
        if (!contacto.getTelefonos().isEmpty()) {
            
            //Titulo
            Label lbl = new Label("Teléfonos");
            lbl.setPrefWidth(300);
            lbl.setAlignment(Pos.CENTER);
            datosVbox.getChildren().add(lbl);
            
            /*Agregando telefonos*/
            
            for (String[] telefono : contacto.getTelefonos()) {
                //Contenedor
                HBox telefonosHbox = new HBox(25);
                telefonosHbox.setPadding(new Insets(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, 15));
                Label tipo = new Label(telefono[0] + ": ");
                Label valor = new Label(telefono[1]);
                telefonosHbox.getChildren().addAll(tipo, valor);
                datosVbox.getChildren().add(telefonosHbox);
            }   
        }
        
        if (!contacto.getEmails().isEmpty()) {
            //agregar correos
            //Titulo
            Label lbl = new Label("Correos");
            lbl.setPrefWidth(300);
            lbl.setAlignment(Pos.CENTER);
            datosVbox.getChildren().add(lbl);
            
            /*Agregando telefonos*/
            
            for (String[] email : contacto.getEmails()) {
                //Contenedor
                HBox CorreosHbox = new HBox(25);
                CorreosHbox.setPadding(new Insets(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, 15));
                Label tipo = new Label(email[0] + ": ");
                Label valor = new Label(email[1]);
                CorreosHbox.getChildren().addAll(tipo, valor);
                datosVbox.getChildren().add(CorreosHbox);
            }
        }
        
        //Agregar sonido
        //Titulo
        Label lbl = new Label("Sonido");
        lbl.setPrefWidth(300);
        lbl.setAlignment(Pos.CENTER);
        datosVbox.getChildren().add(lbl);
        
        //Contenedor
        HBox SonidoHbox = new HBox(25);
        SonidoHbox.setPadding(new Insets(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, 15));
        
        Label tipo = new Label("Sonido: ");
        Label valor = new Label(contacto.getSonidos()[0]);
        
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PLAY);
        icon.setSize("16");
        JFXButton playBtn = new JFXButton("", icon);
        playBtn.setOnAction((event) -> {
            reproducirSonido();
        });
        
        if (contacto.getSonidos()[0].equals("Silencio")) {
            playBtn.setDisable(true);
        }
        
        SonidoHbox.getChildren().addAll(tipo, valor, playBtn);
        datosVbox.getChildren().add(SonidoHbox);
        
        if (!contacto.getDireccionFisica().equals("")) {
            //Agregar direccion fisica
            
            HBox direcFisicaHbox = new HBox(25);
            
            Label direcFisicaLabel = new Label("Dirección física: ");
            
            JFXTextArea txtArea = new JFXTextArea();
            txtArea.setEditable(false);
            txtArea.setText(contacto.getDireccionFisica());
            
            datosVbox.getChildren().addAll(direcFisicaLabel, txtArea);
        }
        
        if (!contacto.getLugarTrabajo().equals("")) {
            //Agregar lugar de trbajo
        }
        
        if (!contacto.getLugarEstudio().equals("")) {
            //Agregar lugar de estudio
        }
        
        if (!contacto.getNotas().equals("")) {
            //Agregar notas
        }
        
        if (!contacto.getAcercaFamilia().equals("")) {
            //Agregar acerca de familia
        }
        
        if (!contacto.getProfesion().equals("")) {
            //Agregar profesion
        }
        
        if (!contacto.getFechaCumpleanios().equals("")) {
            //Agregar fecha de cumpleanios
        }
        
        if (!contacto.getFechaAniversario().equals("")) {
            //Agregar aniversario
        }
        
        if (!contacto.getDireccionTrabajo().equals("")) {
            //Agregar direccion de trabajo
        }
        
        if (!contacto.getEntretenimiento().equals("")) {
            //Agregar entretenimiento
        }
        
        if (!contacto.getDeportes().equals("")) {
            //Agregar deportes
        }
        
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) menubar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cambiarPantalla_a_principal(ActionEvent event) {
        Stage stage = (Stage) imageView.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPantallaPrincipal.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reproducirSonido(){
        String opcionSonido = contacto.getSonidos()[0];
        File archivoSonido;
        switch(opcionSonido){
            case "Definite":
                archivoSonido = new File("/home/bryan/Documentos/Ingeniería en computación/Semestre I/Taller de programación/Proyectos/Proyecto 4/Lista_Contactos_Git/src/libro_contactos/definite.wav");
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(archivoSonido));
                    clip.start();
                } catch (Exception e) {
                    System.out.println(e);
                }
                //contacto.setSonidos(opcionSonido, "libro_contactos/definite.wav");
                break;
            case "Graceful":
                archivoSonido = new File("/home/bryan/Documentos/Ingeniería en computación/Semestre I/Taller de programación/Proyectos/Proyecto 4/Lista_Contactos_Git/src/libro_contactos/graceful.wav");
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(archivoSonido));
                    clip.start();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "Quite impressed":
                archivoSonido = new File("/home/bryan/Documentos/Ingeniería en computación/Semestre I/Taller de programación/Proyectos/Proyecto 4/Lista_Contactos_Git/src/libro_contactos/quite-impressed.wav");
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(archivoSonido));
                    clip.start();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "To the point":
                archivoSonido = new File("/home/bryan/Documentos/Ingeniería en computación/Semestre I/Taller de programación/Proyectos/Proyecto 4/Lista_Contactos_Git/src/libro_contactos/to-the-point.wav");
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(archivoSonido));
                    clip.start();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "Unconvinced":
                archivoSonido = new File("/home/bryan/Documentos/Ingeniería en computación/Semestre I/Taller de programación/Proyectos/Proyecto 4/Lista_Contactos_Git/src/libro_contactos/unconvinced.wav");
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(archivoSonido));
                    clip.start();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "Silencio":
                
                break;
            default:
                break;
        }
    }

    
}
