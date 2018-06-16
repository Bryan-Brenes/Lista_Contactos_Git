/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro_contactos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * FXML Controller class
 *
 * @author bryan
 */
public class FXMLModificarController implements Initializable {

    @FXML
    private MenuBar menubar;
    @FXML
    private MenuItem salir_btn;
    @FXML
    private MenuItem acercaDe_btn;
    @FXML
    private MenuItem manual_btn;
    @FXML
    private JFXButton atras_btn;
    @FXML
    private ImageView imageView2;
    @FXML
    private VBox datosVbox;
    
    private Contacto contacto;
    
    private Contactos_singleton contactos;
    
    private ArrayList<JFXButton> listabotones;
    private ArrayList<JFXButton> listabotonesCorreo;
    
    //Listas que contienen la información del contacto
    private ArrayList<JFXComboBox> listaTipoTelefonos;
    private ArrayList<JFXComboBox> listaTipoCorreos;
    
    private ArrayList<JFXTextField> listaTelefonosTextFields;
    private ArrayList<JFXTextField> listaCorreosTextFields;
    
    private ArrayList<JFXTextArea> listaOpcionalesTextArea;
    private ArrayList<JFXDatePicker> listaOpcionalesFecha;
    
    //Lista de opciones para datos opcionales
    private ArrayList<String> opciones; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactos = Contactos_singleton.getInstance();
        contacto = contactos.getContactoSeleccionado();
        
        listabotones = new ArrayList<>();

        listabotonesCorreo = new ArrayList<>();

        listaTipoCorreos = new ArrayList<>();
 
        listaTipoTelefonos = new ArrayList<>();
      
        listaTelefonosTextFields = new ArrayList<>();
       
        listaCorreosTextFields = new ArrayList<>();
     
        listaOpcionalesTextArea = new ArrayList<>(); 
        
        listaOpcionalesFecha = new ArrayList<>(); 
        
        opciones = new ArrayList<>();
        String[] opt = {"notas", "acerca","profesion","cumpleanios",
            "aniversario", "direccionTrabajo", "entretenimiento", "deportes"};
        opciones.addAll(Arrays.asList(opt));
        
        inicializarInfoUsuario();
    }    

    @FXML
    private void salir() {
        Stage stage = (Stage) menubar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void irPantalla_a_principal() {
        Stage stage = (Stage) imageView2.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPantallaPrincipal.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            //Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void inicializarInfoUsuario(){
        
        //Agregando la imagen
        Image image;
        
        try{
            File file = new File(contacto.getImagenURL());
            BufferedImage bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            Image imagen = image;
            imageView2.setImage(image);
            }catch(IOException e){
                System.out.println(e);
            }
        
        //Agregando el campo de nombre
        
        Label tituloNombre = new Label("Nombre: ");
        JFXTextField nombreTextField = new JFXTextField();
        nombreTextField.setPromptText("Nombre");
        nombreTextField.setText(contacto.getNombre());

        datosVbox.getChildren().addAll(tituloNombre, nombreTextField);
        
        //Agregando los numeros
        if (!contacto.getTelefonos().isEmpty()) {
            
            //Titulo
            Label lbl = new Label("Teléfonos");
            lbl.setPrefWidth(300);
            //lbl.setAlignment(Pos.CENTER);
            datosVbox.getChildren().add(lbl);
            
            /*Agregando telefonos*/
            
            for (String[] telefono : contacto.getTelefonos()) {
                //Contenedor
                HBox telefonosHbox = new HBox(25);
                telefonosHbox.setPadding(new Insets(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, 15));
                Label tipo = new Label(telefono[0] + ": ");
                JFXTextField valor = new JFXTextField();
                valor.setText(telefono[1]);
                valor.setPromptText("telefono");
                
                FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                icon.setSize("16");
                JFXButton tel_btn = new JFXButton("", icon);
                tel_btn.setOnAction((event) -> {
                    //reproducirSonido();
                });
                
                telefonosHbox.getChildren().addAll(tipo, valor, tel_btn);
                datosVbox.getChildren().add(telefonosHbox);
            }   
        }
    }
    
    @FXML
    public void cargarImagen(){
        
        
        
    }
    
    @FXML
    public void añadirTelefono(ActionEvent event){
        
    }
    
    public void removerTelefono(ActionEvent event){

    }
    
    @FXML
    public void añadirCorreo(ActionEvent event){

    }
    
    public void removerCorreo(ActionEvent event){
        
    }
    
    /*Se crea el Hbox que va a contener ya sea los numeros o los correos
    Este se utiliza cuando el usuario selecciona el boton de agregar nuevo
    */
    
    public HBox crearHbox(String tipo){
        HBox hbox = new HBox(25);
        if (tipo.equals("correo")) {
            JFXComboBox<Label> correos = new JFXComboBox();
            correos.getItems().addAll(new Label("Personal"), new Label("Trabajo"),
                new Label("Otro"));
            correos.getSelectionModel().select(0);
            correos.setPrefSize(USE_COMPUTED_SIZE, 26);
            listaTipoCorreos.add(correos);
            
            JFXTextField correo_TextField = new JFXTextField();
            correo_TextField.setPromptText("correo");
            correo_TextField.setPrefSize(120, 26);
            correo_TextField.setLabelFloat(true);
            correo_TextField.setOnKeyReleased(e -> verificarCorreo(e));
            listaCorreosTextFields.add(correo_TextField);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
            icon.setSize("16");
            JFXButton add_btn = new JFXButton("",icon);
            listabotonesCorreo.add(add_btn);
            
            
            add_btn.setOnAction(e-> añadirCorreo(e));
            
            hbox.setPrefWidth(100);
            
            hbox.getChildren().addAll(correos, correo_TextField, add_btn);
        }else{
            
            //Se inicializa un ComboBox con sus caracteristicas adecuadas
            JFXComboBox<Label> telefonos = new JFXComboBox();
            telefonos.getItems().addAll(new Label("Móvil"), new Label("Casa"),
                new Label("Fax"), new Label("Trabajo"), new Label("Otro"));
            telefonos.getSelectionModel().select(0);
            telefonos.setPrefSize(USE_COMPUTED_SIZE, 26);
            listaTipoTelefonos.add(telefonos);
            
            //Se inicializa un textField para el numero de telefono
            JFXTextField telefono_TextField = new JFXTextField();
            telefono_TextField.setPromptText("teléfono");
            telefono_TextField.setPrefSize(120, 26);
            telefono_TextField.setLabelFloat(true);
            telefono_TextField.setOnKeyReleased(e -> verificarTelefono(e));
            listaTelefonosTextFields.add(telefono_TextField);
            
            //Se inicializa un JFXButton con un icon de agregar
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
            icon.setSize("16");
            JFXButton add_btn = new JFXButton("",icon);
            /*FontAwesomeIconView icon2 = (FontAwesomeIconView)add_btn.getGraphic();
            icon2.setIcon(FontAwesomeIcon.CHECK);*/
            
            //Se agregar el boton a una lista de botones para poder referenciarlos
            //mas facilmente
            listabotones.add(add_btn);
            
            //Se incorpora al boton nuevo la funcionalidad de agregar telefono
            add_btn.setOnAction(e-> añadirTelefono(e));
            
            hbox.setPrefWidth(100);
            
            //Se añaden al hbox los elementos que va a contener
            hbox.getChildren().addAll(telefonos, telefono_TextField, add_btn);
        }
        return hbox;
    }
    
    public void verificarTelefono(KeyEvent event){
        JFXTextField txt = (JFXTextField) event.getSource();
        System.out.println(txt.getText());
        String regex = "\\d*";
        if (!txt.getText().matches(regex)) {
            txt.setUnFocusColor(Paint.valueOf("#ff0000"));
            System.out.println("no aceptado");
        }
        else{
            txt.setUnFocusColor(Paint.valueOf("#4d4d4d"));
            System.out.println("aceptado");
        }
    }
    
    public void verificarCorreo(KeyEvent event){
        //String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        String regex = "^(.+)@(.+)$";
        JFXTextField txt = (JFXTextField) event.getSource();
        if (!txt.getText().matches(regex) && !txt.getText().equals("")) {
            txt.setUnFocusColor(Paint.valueOf("#ff0000"));
            System.out.println("no aceptado");
        }
        else{
            txt.setUnFocusColor(Paint.valueOf("#4d4d4d"));
            System.out.println("aceptado");
        }
    }
    
    @FXML
    public void mostrarPopUp(){
        initPopUp(opciones);
        //popup.show(agregarCampos_btn);
    }
    
    public void agregarCampoOpcional(ActionEvent e){
        JFXButton source = (JFXButton) e.getSource();
        
        HBox hbox = new HBox(25);
        
        String sourceID = source.getId();
        
        if (sourceID.equals("notas")) {

            JFXTextArea area = new JFXTextArea();
            area.setId(sourceID);
            area.setPromptText("Notas");
            area.setLabelFloat(true);
            area.setPrefSize(240, USE_COMPUTED_SIZE);
            listaOpcionalesTextArea.add(area);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            icon.setSize("16");
            JFXButton btn = new JFXButton("",icon);
            btn.setId(sourceID);
            btn.setOnAction((event) -> {
                removerCampoOpcional(event);
            });
            hbox.getChildren().addAll(area,btn);
            
            opciones.remove(sourceID);
        }else if(sourceID.equals("acerca")){

            JFXTextArea area = new JFXTextArea();
            area.setId(sourceID);
            area.setPromptText("Acerca de familia");
            area.setLabelFloat(true);
            area.setPrefSize(240, USE_COMPUTED_SIZE);
            listaOpcionalesTextArea.add(area);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            icon.setSize("16");
            JFXButton btn = new JFXButton("",icon);
            btn.setId(sourceID);
            btn.setOnAction((event) -> {
                removerCampoOpcional(event);
            });
            hbox.getChildren().addAll(area,btn);
            
            opciones.remove(sourceID);
        }else if(sourceID.equals("profesion")){

            JFXTextArea area = new JFXTextArea();
            area.setId(sourceID);
            area.setPromptText("Profesión");
            area.setLabelFloat(true);
            area.setPrefSize(240, USE_COMPUTED_SIZE);
            listaOpcionalesTextArea.add(area);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            icon.setSize("16");
            JFXButton btn = new JFXButton("",icon);
            btn.setId(sourceID);
            btn.setOnAction((event) -> {
                removerCampoOpcional(event);
            });
            hbox.getChildren().addAll(area,btn);
            
            opciones.remove(sourceID);
        }else if(sourceID.equals("cumpleanios")){
            JFXDatePicker datePicker = new JFXDatePicker();
            datePicker.setId(sourceID);
            datePicker.setPrefWidth(240);
            datePicker.setPromptText("Cumpleaños");
            listaOpcionalesFecha.add(datePicker);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            icon.setSize("16");
            JFXButton btn = new JFXButton("",icon);
            btn.setId(sourceID);
            btn.setOnAction((event) -> {
                removerCampoOpcional(event);
            });
            hbox.getChildren().addAll(datePicker,btn);
            
            opciones.remove(sourceID);
            
        }else if(sourceID.equals("aniversario")){
            
            JFXDatePicker datePicker = new JFXDatePicker();
            datePicker.setId(sourceID);
            datePicker.setPrefWidth(240);
            datePicker.setPromptText("Aniversario");
            listaOpcionalesFecha.add(datePicker);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            icon.setSize("16");
            JFXButton btn = new JFXButton("",icon);
            btn.setId(sourceID);
            btn.setOnAction((event) -> {
                removerCampoOpcional(event);
            });
            hbox.getChildren().addAll(datePicker,btn);
            
            opciones.remove(sourceID);
            
        }else if(sourceID.equals("direccionTrabajo")){

            JFXTextArea area = new JFXTextArea();
            area.setId(sourceID);
            area.setPromptText("Dirección física del trabajo");
            area.setLabelFloat(true);
            area.setPrefSize(240, USE_COMPUTED_SIZE);
            listaOpcionalesTextArea.add(area);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            icon.setSize("16");
            JFXButton btn = new JFXButton("",icon);
            btn.setId(sourceID);
            btn.setOnAction((event) -> {
                removerCampoOpcional(event);
            });
            hbox.getChildren().addAll(area,btn);
            
            opciones.remove(sourceID);
        }else if(sourceID.equals("entretenimiento")){

            JFXTextArea area = new JFXTextArea();
            area.setId(sourceID);
            area.setPromptText("Entretenimiento");
            area.setLabelFloat(true);
            area.setPrefSize(240, USE_COMPUTED_SIZE);
            listaOpcionalesTextArea.add(area);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            icon.setSize("16");
            JFXButton btn = new JFXButton("",icon);
            btn.setId(sourceID);
            btn.setOnAction((event) -> {
                removerCampoOpcional(event);
            });
            hbox.getChildren().addAll(area,btn);
            
            opciones.remove(sourceID);
        }else if(sourceID.equals("deportes")){

            JFXTextArea area = new JFXTextArea();
            area.setId(sourceID);
            area.setPromptText("Deportes");
            area.setLabelFloat(true);
            area.setPrefSize(240, USE_COMPUTED_SIZE);
            listaOpcionalesTextArea.add(area);
            
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            icon.setSize("16");
            JFXButton btn = new JFXButton("",icon);
            btn.setId(sourceID);
            btn.setOnAction((event) -> {
                removerCampoOpcional(event);
            });
            hbox.getChildren().addAll(area,btn);
            
            opciones.remove(sourceID);
        }

        //Vbox_camposOpcionales.getChildren().add(hbox);
        //popup.hide();
        System.out.println("Lista TextArea: " + listaOpcionalesTextArea);
        System.out.println("Lista DatePickers: " + listaOpcionalesFecha);
    }

    public void initPopUp(ArrayList<String> opciones){
        //popup = new JFXPopup();
        VBox vbox = new VBox(1);
        
        if(opciones.isEmpty()){
            Label lbl = new Label("No hay más opciones disponibles");
            lbl.setPrefHeight(40);
            lbl.setPrefWidth(300);
            lbl.setAlignment(Pos.CENTER);
            vbox.getChildren().add(lbl);
            //popup.setPopupContent(vbox);
            return;
        }
        
        for (String op : opciones) {
            if(op.equals("notas")){

                JFXButton btn = new JFXButton("Notas");
                btn.setId(op);
                btn.setOnAction((event) -> {
                    agregarCampoOpcional(event);
                });
                btn.setPrefWidth(200);
                vbox.getChildren().add(btn);
                
            }else if(op.equals("acerca")){
                
                JFXButton btn = new JFXButton("Acerca de familia");
                btn.setId(op);
                btn.setOnAction((event) -> {
                    agregarCampoOpcional(event);
                });
                btn.setPrefWidth(200);
                vbox.getChildren().add(btn);
            }else if(op.equals("profesion")){
                
                JFXButton btn = new JFXButton("Profesion");
                btn.setId(op);
                btn.setOnAction((event) -> {
                    agregarCampoOpcional(event);
                });
                btn.setPrefWidth(200);
                vbox.getChildren().add(btn);
            }else if(op.equals("cumpleanios")){
                
                JFXButton btn = new JFXButton("Cumpleaños");
                btn.setId(op);
                btn.setOnAction((event) -> {
                    agregarCampoOpcional(event);
                });
                btn.setPrefWidth(200);
                vbox.getChildren().add(btn);
            }else if(op.equals("aniversario")){
                
                JFXButton btn = new JFXButton("Aniversario");
                btn.setId(op);
                btn.setOnAction((event) -> {
                    agregarCampoOpcional(event);
                });
                btn.setPrefWidth(200);
                vbox.getChildren().add(btn);
            }else if(op.equals("direccionTrabajo")){
                
                JFXButton btn = new JFXButton("Dirección física del trabajo");
                btn.setId(op);
                btn.setOnAction((event) -> {
                    agregarCampoOpcional(event);
                });
                btn.setPrefWidth(200);
                vbox.getChildren().add(btn);
            }else if(op.equals("entretenimiento")){
                
                JFXButton btn = new JFXButton("Entretenimiento");
                btn.setId(op);
                btn.setOnAction((event) -> {
                    agregarCampoOpcional(event);
                });
                btn.setPrefWidth(200);
                vbox.getChildren().add(btn);
            }else if(op.equals("deportes")){
                
                JFXButton btn = new JFXButton("Deportes");
                btn.setId(op);
                btn.setOnAction((event) -> {
                    agregarCampoOpcional(event);
                });
                btn.setPrefWidth(200);
                vbox.getChildren().add(btn);
            }
            
            //popup.setPopupContent(vbox);
           
        }
        
        
    }//<>

    private void removerCampoOpcional(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        Parent root = btn.getParent();
        System.out.println("btnID: " + btn.getId());
        
        if (btn.getId().equals("cumpleanios") || btn.getId().equals("aniversario")) {
            int area_index = 0;
            for (JFXDatePicker fecha : listaOpcionalesFecha) {
                if (fecha.getId().equals(btn.getId())) {
                    area_index = listaOpcionalesFecha.indexOf(fecha);
                    break;
                }
            }
            opciones.add(btn.getId());
            listaOpcionalesFecha.remove(area_index);
        }else{
            
            int area_index = 0;
            for (JFXTextArea area : listaOpcionalesTextArea) {
                if (area.getId().equals(btn.getId())) {
                    area_index = listaOpcionalesTextArea.indexOf(area);
                    break;
                }
            }
            opciones.add(btn.getId());
            listaOpcionalesTextArea.remove(area_index);
        }
        //Vbox_camposOpcionales.getChildren().remove(root);
        System.out.println("Lista TextArea: " + listaOpcionalesTextArea);
        System.out.println("Lista DatePicker: " + listaOpcionalesFecha);
        
    }
    
    @FXML
    public void guardarContacto(){
          
        
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
