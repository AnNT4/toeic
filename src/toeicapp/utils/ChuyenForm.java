/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp.utils;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author annt4
 */
public class ChuyenForm {
    public FXMLLoader transferForm(String resource, String title) throws IOException
    {
        Stage demoStage = new Stage();
        URL u = getClass().getClassLoader().getResource(resource); 
        FXMLLoader loader = new FXMLLoader(u);
        Parent root = loader.load();
        Scene scene = new Scene(root); 
        demoStage.setScene(scene); 
        demoStage.setTitle(title);
        demoStage.show(); 
        return loader;
    }
}
