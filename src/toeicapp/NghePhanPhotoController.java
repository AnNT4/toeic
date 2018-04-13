/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import toeicapp.utils.ChuyenForm;

/**
 * FXML Controller class
 *
 * @author annt4
 */
public class NghePhanPhotoController implements Initializable {

    @FXML private ImageView picCauHoi;
    @FXML private MediaView mediaCauHoi;
    @FXML private Button btnTrangChu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnTrangChuClick(ActionEvent event) throws IOException {
        Stage demoStage = (Stage) btnTrangChu.getScene().getWindow();
        demoStage.hide();
        ChuyenForm tf = new ChuyenForm();
        tf.transferForm("toeicapp/TrangChu.fxml", "Trang chủ");
    }
    
}
