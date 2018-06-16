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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
    
    //Listas que contienen la información del contacto
    private ArrayList<JFXComboBox> listaTipoTelefonos;
    private ArrayList<JFXComboBox> listaTipoCorreos;
    
    private ArrayList<JFXTextField> listaTelefonosTextFields;
    private ArrayList<JFXTextField> listaCorreosTextFields;
    
    private ArrayList<JFXTextArea> listaOpcionalesTextArea;
    private ArrayList<JFXDatePicker> listaOpcionalesFecha;
    
    //Lista de opciones para datos opcionales
    private ArrayList<String> opciones; 
    
    private Contactos_singleton contactos;
    
    private Image imagenUsuario;
    
    
    /*Se debe crear variables globales para almacenar los elementos graficos de 
    manera que se referencien mediante el id, sino con esa variable.
    Esto porque los elementos no son fijos.*/
    
    private JFXPopup popup; 
    @FXML
    private VBox Vbox_camposOpcionales;
    @FXML
    private StackPane stackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Image image = new Image("libro_contactos/user512.png");
        imagenUsuario = image;
        
        //Instanciando el singleton
        contactos = Contactos_singleton.getInstance();
        
        //Inicializando listas de informacion
        
        listabotones = new ArrayList<>();
        listabotones.add(agregar_numero);
        
        listabotonesCorreo = new ArrayList<>();
        listabotonesCorreo.add(agregar_correo);
        
        listaTipoCorreos = new ArrayList<>();
        listaTipoCorreos.add(correo_tipo);
        
        listaTipoTelefonos = new ArrayList<>();
        listaTipoTelefonos.add(tipo_telefono);
        
        listaTelefonosTextFields = new ArrayList<>();
        listaTelefonosTextFields.add(telefono_textField);
        
        listaCorreosTextFields = new ArrayList<>();
        listaCorreosTextFields.add(correo_TextField);
        
        listaOpcionalesTextArea = new ArrayList<>(); 
        listaOpcionalesFecha = new ArrayList<>(); 
        
        opciones = new ArrayList<>();
        String[] opt = {"notas", "acerca","profesion","cumpleanios",
            "aniversario", "direccionTrabajo", "entretenimiento", "deportes"};
        opciones.addAll(Arrays.asList(opt));
        
        System.out.println(opciones);
        
        //Inicializacion del singleton de contactos
        contacto = new Contacto();
        
        //Poblando los combobox de tipo
        tipo_telefono.getItems().addAll(new Label("Móvil"), new Label("Casa"),
                new Label("Fax"), new Label("Trabajo"), new Label("Otro"));
        tipo_telefono.getSelectionModel().select(0);
        
        correo_tipo.getItems().addAll(new Label("Personal"), new Label("Trabajo"),
                new Label("Otro"));
        correo_tipo.getSelectionModel().select(0);
        
        sonidoCombobox.getItems().addAll("Silencio", "Definite", "Graceful", "Quite impressed",
                "To the point", "Unconvinced");
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
        imagenUsuario = image;
        
        try{
            BufferedImage bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            Image imagen = image;
            imagenUsuario = imagen;
            imageView.setImage(image);
            }catch(IOException e){
                System.out.println(e);
            }
        
    }
    
    @FXML
    public void añadirTelefono(ActionEvent event){
        
        Vbox_telefonos.getChildren().add(crearHbox("telefono"));
        System.out.println("Cantidad botones tel: " + listabotones.size());
         
        //Cambiar el icono por el de borrar
        JFXButton btn = (JFXButton) event.getSource();
        FontAwesomeIconView icon = (FontAwesomeIconView)btn.getGraphic();
        icon.setIcon(FontAwesomeIcon.TRASH);
        btn.setOnAction(e -> removerTelefono(e));
        
        
        if (listabotones.size() == 1) {
            FontAwesomeIconView icon2 = (FontAwesomeIconView)agregar_numero.getGraphic();
            icon2.setIcon(FontAwesomeIcon.TRASH);
            agregar_numero.setOnAction(e -> removerTelefono(e));
        }
    }
    
    public void removerTelefono(ActionEvent event){

        if (listabotones.size() == 1) {
            Parent root = listabotones.get(0).getParent();
            Vbox_telefonos.getChildren().remove(root);
            listabotones.clear();
            
            FontAwesomeIconView icon2 = (FontAwesomeIconView)agregar_numero.getGraphic();
            icon2.setIcon(FontAwesomeIcon.PLUS_CIRCLE);
            agregar_numero.setOnAction(e -> añadirTelefono(e));
        }
        
        JFXButton btn = (JFXButton) event.getSource();
        Parent root = btn.getParent();
        Vbox_telefonos.getChildren().remove(root);
        
        // Este indice es para eleminiar el combobox y textField
        // correspondientes a este boton
        int btn_index = listabotones.indexOf(btn);
        
        listabotones.remove(btn);
        listaTelefonosTextFields.remove(btn_index);
        listaTipoTelefonos.remove(btn_index);

    }
    
    @FXML
    public void añadirCorreo(ActionEvent event){
        Vbox_correos.getChildren().add(crearHbox("correo"));
        
        System.out.println(listaCorreosTextFields);
        
        //Cambiar el icono por el de borrar
        JFXButton btn = (JFXButton) event.getSource();
        FontAwesomeIconView icon = (FontAwesomeIconView)btn.getGraphic();
        icon.setIcon(FontAwesomeIcon.TRASH);
        btn.setOnAction(e -> removerCorreo(e));
        
        if (listabotonesCorreo.size() == 1) {
            FontAwesomeIconView icon2 = (FontAwesomeIconView)agregar_correo.getGraphic();
            icon2.setIcon(FontAwesomeIcon.TRASH);
            agregar_correo.setOnAction(e -> removerCorreo(e));
        }
    }
    
    public void removerCorreo(ActionEvent event){
        
        if (listabotonesCorreo.size() == 1) {
            Parent root = listabotonesCorreo.get(0).getParent();
            Vbox_correos.getChildren().remove(root);
            listabotonesCorreo.clear();
            
            FontAwesomeIconView icon2 = (FontAwesomeIconView)agregar_correo.getGraphic();
            icon2.setIcon(FontAwesomeIcon.PLUS_CIRCLE);
            agregar_correo.setOnAction(e -> añadirCorreo(e));
        }
        
        JFXButton btn = (JFXButton) event.getSource();
        Parent root = btn.getParent();
        Vbox_correos.getChildren().remove(root);
        
        int btn_index = listabotonesCorreo.indexOf(btn);
        
        listabotonesCorreo.remove(btn);
        listaTipoCorreos.remove(btn_index);
        listaCorreosTextFields.remove(btn_index);
        
        System.out.println(listaCorreosTextFields);
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
        popup.show(agregarCampos_btn);
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

        Vbox_camposOpcionales.getChildren().add(hbox);
        popup.hide();
        System.out.println("Lista TextArea: " + listaOpcionalesTextArea);
        System.out.println("Lista DatePickers: " + listaOpcionalesFecha);
    }

    public void initPopUp(ArrayList<String> opciones){
        popup = new JFXPopup();
        VBox vbox = new VBox(1);
        
        if(opciones.isEmpty()){
            Label lbl = new Label("No hay más opciones disponibles");
            lbl.setPrefHeight(40);
            lbl.setPrefWidth(300);
            lbl.setAlignment(Pos.CENTER);
            vbox.getChildren().add(lbl);
            popup.setPopupContent(vbox);
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
            
            popup.setPopupContent(vbox);
            /*popup.setAnchorX(150);
            popup.setAnchorY(150);*/
               
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
        Vbox_camposOpcionales.getChildren().remove(root);
        System.out.println("Lista TextArea: " + listaOpcionalesTextArea);
        System.out.println("Lista DatePicker: " + listaOpcionalesFecha);
        
    }
    
    @FXML
    public void guardarContacto(){
        
        /*Este if es para mostrar un cuadro de dialogo cuando
        no se ha ingresado ningun nombre ya que este es la "clave principal"*/
        
        contacto = new Contacto();
        
        contacto.setImagen(imagenUsuario);
        
        for (Contacto contact : contactos.getListaContactos()) {
            if (contact.getNombre().equals(nombre_TextField.getText().trim())) {
                stackPane.toFront();
                JFXDialog dialog = new JFXDialog();
                dialog.setDialogContainer(stackPane);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Error"));
                content.setBody(new Text("Ya existe un contacto con el nombre ingresado"));
                JFXButton btn = new JFXButton("Entendido");
                btn.setOnAction((event) -> {
                    stackPane.toBack();
                    dialog.close();
                });
                content.setActions(btn);
                dialog.setContent(content);
                dialog.show();
                return;
            }
        }
        
        if (nombre_TextField.getText().equals("")) {
            stackPane.toFront();
            JFXDialog dialog = new JFXDialog();
            dialog.setDialogContainer(stackPane);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error"));
            content.setBody(new Text("No se ingresó un nombre al contacto"));
            JFXButton btn = new JFXButton("Entendido");
            btn.setOnAction((event) -> {
                stackPane.toBack();
                dialog.close();
                
            });
            content.setActions(btn);
            dialog.setContent(content);
            dialog.show();
        }else{
            
            //Almacenar nombre
            contacto.setNombre(nombre_TextField.getText());
            
            //Elementos por revisar
            /*
            lista telefonos (tipo y valor)
            lista correos (tipo y valor)
            Sonido
            Direccion fisica
            lugar de trabajo
            jugar de estudio
            lista elementos opcionales TextArea
            lista elementos opcionales Fechas
            */
            
            
            //Almacenar telefonos
            if (!listaTelefonosTextFields.isEmpty()) {
                contacto.getTelefonos().clear();
                int index = 0;
                while (index < listaTelefonosTextFields.size()) {                    
                    if (!listaTelefonosTextFields.get(index).getText().equals("")) {
                        listaTelefonosTextFields.get(index).setUnFocusColor(Paint.valueOf("#4d4d4d"));
                        //Chequear si el numero es valido, si solo tiene numeros
                        String regex = "\\d*";
                        if(listaTelefonosTextFields.get(index).getText().matches(regex)){
                            Label lbl = (Label)listaTipoTelefonos.get(index).getValue();
                            String[] telefonos_por_agregar = new String[2];
                            telefonos_por_agregar[0] = lbl.getText();
                            telefonos_por_agregar[1] = listaTelefonosTextFields.get(index).getText();

                            contacto.getTelefonos().add(telefonos_por_agregar);
                        }else
                            listaTelefonosTextFields.get(index).setUnFocusColor(Paint.valueOf("#ff0000"));  
                    }
                    index++;
                }
            }
            
            //Almacenar correos
            if (!listaCorreosTextFields.isEmpty()) {
                contacto.getEmails().clear();
                int index = 0;
                while (index < listaCorreosTextFields.size()) {                    
                    if (!listaCorreosTextFields.get(index).getText().equals("")) {
                        listaCorreosTextFields.get(index).setUnFocusColor(Paint.valueOf("#4d4d4d"));
                        //Chequear si el numero es valido, si solo tiene numeros
                        String regex = "^(.+)@(.+)$";
                        if(listaCorreosTextFields.get(index).getText().matches(regex)){
                            Label lbl = (Label)listaTipoCorreos.get(index).getValue();
                            String[] correo_por_agregar = new String[2];
                            correo_por_agregar[0] = lbl.getText();
                            correo_por_agregar[1] = listaCorreosTextFields.get(index).getText();

                            contacto.getEmails().add(correo_por_agregar);
                        }else
                            listaCorreosTextFields.get(index).setUnFocusColor(Paint.valueOf("#ff0000"));  
                    }
                    index++;
                }
            }
            
            //Almacenar sonido
            /*Para esto se debe ver la seleccion del usuario y dependiendo
            de esta se guarda la ruta corresponiente*/
            
            String opcionSonido = sonidoCombobox.getValue();
            switch(opcionSonido){
                case "Definite":
                    contacto.setSonidos(opcionSonido, "/home/bryan/Documentos/Ingeniería en computación/Semestre I/Taller de programación/Proyectos/Proyecto 4/Lista_Contactos_Git/src/libro_contactos/definite.wav");
                    break;
                case "Graceful":
                    contacto.setSonidos(opcionSonido, "/home/bryan/Documentos/Ingeniería en computación/Semestre I/Taller de programación/Proyectos/Proyecto 4/Lista_Contactos_Git/src/libro_contactos/graceful.wav");
                    break;
                case "Quite impressed":
                    contacto.setSonidos(opcionSonido, "/home/bryan/Documentos/Ingeniería en computación/Semestre I/Taller de programación/Proyectos/Proyecto 4/Lista_Contactos_Git/src/libro_contactos/quite-impressed.wav");
                    break;
                case "To the point":
                    contacto.setSonidos(opcionSonido, "/home/bryan/Documentos/Ingeniería en computación/Semestre I/Taller de programación/Proyectos/Proyecto 4/Lista_Contactos_Git/src/libro_contactos/to-the-point.wav");
                    break;
                case "Unconvinced":
                    contacto.setSonidos(opcionSonido, "/home/bryan/Documentos/Ingeniería en computación/Semestre I/Taller de programación/Proyectos/Proyecto 4/Lista_Contactos_Git/src/libro_contactos/unconvinced.wav");
                    break;
                case "Silencio":
                    contacto.setSonidos(opcionSonido, "");
                    break;
                default:
                    break;
            }
            
            //Almacenar dirección fisica
            
            if (!direccionFisica.getText().equals("")) {
                contacto.setDireccionFisica(direccionFisica.getText());
            }
            
            //Almacenar lugar de trabajo
            
            if (!LugarTrabajo.getText().equals("")) {
                contacto.setLugarTrabajo(LugarTrabajo.getText());
            }
            
            //Almacenar lugar de estudio
            
            if (!lugarEstudio.getText().equals("")) {
                contacto.setLugarEstudio(lugarEstudio.getText());
            }
            
            //Almacenar opcionales TextArea
            
            if (!listaOpcionalesTextArea.isEmpty()) {
                for (JFXTextArea txt : listaOpcionalesTextArea) {
                    String id = txt.getId();
                    System.out.println("ID: " + id);
                    switch(id){
                        case "notas":
                            contacto.setNotas(txt.getText());
                            break;
                        case "acerca":
                            contacto.setAcercaFamilia(txt.getText());
                            break;
                        case "profesion":
                            contacto.setProfesion(txt.getText());
                            break;
                        case "direccionTrabajo":
                            contacto.setDireccionTrabajo(txt.getText());
                            break;
                        case "entretenimiento":
                            contacto.setEntretenimiento(txt.getText());
                            break;
                        case "deportes":
                            contacto.setDeportes(txt.getText());
                            break;
                        default:
                            break;
                            
                    }
                }
            }
            
            //Almacenar opcionales fechas
            
            if (!listaOpcionalesFecha.isEmpty()) {
                for(JFXDatePicker fecha: listaOpcionalesFecha){
                    String id = fecha.getId();
                    switch(id){
                        case "cumpleanios":
                            contacto.setFechaCumpleanios(fecha.getValue().toString());
                            break;
                        case "aniversario":
                            contacto.setFechaAniversario(fecha.getValue().toString());
                            break;
                        default:
                            break;
                    }
                }
            }
            
            
            contacto.imprimirInfoContacto();
        }//<>
        
        //Agregar contacto en el lugar que le corresponde segun orden
        //alfabetico
        int indice = -1;
        
        for (int i = 0; i < contactos.getListaContactos().size(); i++) {
            int comparacion = contactos.getListaContactos().get(i).getNombre().compareTo(contacto.getNombre());
            if ( comparacion > 0 ) {
                indice = i;
                break;
            }
        }
        
        if (indice != -1) {
            contactos.agregarContacto(contacto, indice);
        }else
            contactos.agregarContacto(contacto);
        
        //contactos.agregarContacto(contacto);
        cambiarPantalla_a_principal();
    }
    
    public void reproducirSonido(){
        String opcionSonido = sonidoCombobox.getValue();
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
