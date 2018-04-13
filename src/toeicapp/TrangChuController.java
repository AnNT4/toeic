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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import toeicapp.utils.ChuyenForm;
import toeicapp.utils.KetNoi;

/**
 * FXML Controller class
 *
 * @author annt4
 */
public class TrangChuController implements Initializable {

    @FXML private MenuItem miNguPhap;
    @FXML private MenuItem miDienKhuyet;
    @FXML private MenuItem miNghePhoto;
    @FXML private Button btnNguPhap;
    @FXML private Button btnDienKhuyet;
    @FXML private Button btnNghePhoto;
    @FXML private Button btnThoat;
    @FXML private Button btnCauHoi;
    @FXML private Label lblName;
    Stage demoStage = new Stage();
    private Connection conn = null;
    private PreparedStatement prepStatement = null;
    private String username = null;
    static String name;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KetNoi kt = new KetNoi();
        conn = kt.ketNoi();
        String sqlNguoiDung = "SELECT * FROM nguoi_dung WHERE ten_nguoi_dung = ?";
        try {
            prepStatement = conn.prepareStatement(sqlNguoiDung);
            prepStatement.setString(1, name);
            ResultSet rs = prepStatement.executeQuery();
            if(rs.next()){
                name = rs.getString("ten_nguoi_dung");
                lblName.setText("Xin chào " + name);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi " + ex);
        }
               
        
    }    

    @FXML
    private void btnNguPhapClick(ActionEvent event) throws IOException {
        demoStage = (Stage) btnNguPhap.getScene().getWindow();
        demoStage.hide();
        ChuyenForm tf = new ChuyenForm();
        tf.transferForm("toeicapp/NguPhap.fxml",
                    "Luyện thi ngữ pháp");
    }

    @FXML
    private void btnDienKhuyetClick(ActionEvent event) throws IOException {
        demoStage = (Stage) btnNguPhap.getScene().getWindow();
        demoStage.hide();
        ChuyenForm tf = new ChuyenForm();
        tf.transferForm("toeicapp/DienKhuyet.fxml",
                    "Luyện thi điền khuyết");
    }

    @FXML
    private void btnNghePhotoClick(ActionEvent event) throws IOException {
        demoStage = (Stage) btnNguPhap.getScene().getWindow();
        demoStage.hide();
        ChuyenForm tf = new ChuyenForm();
        tf.transferForm("toeicapp/NghePhanPhoto.fxml",
                    "Luyện thi nghe phần photo");
    }

    @FXML
    private void btnThoatClick(ActionEvent event) {
        Platform.exit();
    }
    
    public void ktDangNhap(){
        KetNoi kt = new KetNoi();
        conn = kt.ketNoi();
        String sql = "SELECT * FROM nguoi_dung WHERE ten_nguoi_dung = ? and "
                + "loai_nguoi_dung = ?";
        try {
                prepStatement = conn.prepareStatement(sql);
                prepStatement.setString(1, this.username);
                prepStatement.setInt(2, 1);
                ResultSet rs = prepStatement.executeQuery();
                if(rs.next()) //admin
                    btnCauHoi.setVisible(true);
                else // End Users
                    btnCauHoi.setVisible(false);
        } catch (SQLException ex) {
            System.out.println("Lỗi " + ex);
        }
    }
    
    public void setUserName(String u) {
        this.username = u;
    }
    
    @FXML
    private void btnCauHoiClick(ActionEvent event) throws IOException {
        demoStage = (Stage) btnCauHoi.getScene().getWindow();
        demoStage.hide();
        ChuyenForm tf = new ChuyenForm();
        FXMLLoader loader = tf.transferForm("toeicapp/QuanLyCauHoi.fxml",
                "Quản lý câu hỏi");
    }
    
}
