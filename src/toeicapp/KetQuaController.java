/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import toeicapp.utils.ChuyenForm;

/**
 * FXML Controller class
 *
 * @author annt4
 */
public class KetQuaController implements Initializable {

    @FXML private Label lblCauDung;
    @FXML private Label lblCauSai;
    @FXML private Button btnTrangChu;
    FXMLLoader loader;
    static String name;
    Stage demoStage = new Stage();
    private Connection conn = null;
    private PreparedStatement prepStatement = null;
    static int dung, sai;
    static int dung1, sai1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblCauDung.setText(String.valueOf(dung));
        lblCauSai.setText(String.valueOf(sai));
        lblCauDung.setText(String.valueOf(dung1));
        lblCauSai.setText(String.valueOf(sai1));
    }
    
    @FXML
    public void btnThoatClick(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    public void btnTrangChuClick(ActionEvent event) throws IOException {
        Stage demoStage = (Stage) btnTrangChu.getScene().getWindow();
        demoStage.hide();
        ChuyenForm tf = new ChuyenForm();
        tf.transferForm("toeicapp/TrangChu.fxml", "Trang chủ");
    }
    
}
