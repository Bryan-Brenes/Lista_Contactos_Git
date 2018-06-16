/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro_contactos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bryan
 */

public class Libro_contactos extends Application {
    
    public static Stage window;
    private Contactos_singleton contactos;
    
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        contactos = Contactos_singleton.getInstance();
        leerContactos();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPantallaPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    //Aqui va el código para guardar los contactos en el archivo
    @Override
    public void stop() throws IOException{

        String fileName = "DatosContactos.bin";
        try {
            
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(contactos.getListaContactos().get(0));
            System.out.println("Si se guardaron");
            os.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Libro_contactos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void leerContactos(){
        String fileName = "DatosContactos.bin";
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            Contacto cont = (Contacto) is.readObject();
            //contactos.setListaContactos(listaContactos);
            System.out.println(cont);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
