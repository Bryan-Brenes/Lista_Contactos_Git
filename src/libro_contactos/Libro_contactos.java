/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro_contactos;

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
    
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLPantallaPrincipal.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("FXMLNuevoContacto.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
