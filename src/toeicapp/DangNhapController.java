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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import toeicapp.utils.ChuyenForm;
import toeicapp.utils.HienThongBao;
import toeicapp.utils.KetNoi;
import toeicapp.utils.KiemTraNhap;

/**
 *
 * @author annt4
 */
public class DangNhapController implements Initializable {
    @FXML private Button btnDangNhap;
    @FXML private Button btnDangKy;
    @FXML private Button btnHuy;
    @FXML private TextField txtUsername;
    @FXML private Label lblErrorUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblErrorPassword;
    private Connection conn = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;
    Stage demoStage = new Stage();
    KiemTraNhap ktn = new KiemTraNhap();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnDangNhapClick(ActionEvent event) throws IOException {
         boolean ktTestFieldUsername = KiemTraNhap.ktTextFieldRong(txtUsername, 
                lblErrorUsername, "Bạn chưa nhập tên người dùng");
        boolean ktTestFieldPassword = KiemTraNhap.ktTextFieldRong(txtPassword, 
                lblErrorPassword, "Bạn chưa nhập mật khẩu");
        if(ktTestFieldUsername && ktTestFieldPassword){
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            KetNoi kn = new KetNoi();
            conn = kn.ketNoi();
            String sql = "SELECT * FROM nguoi_dung WHERE ten_nguoi_dung = ? "
                    + "and mat_khau = ?";
            try{
                prepStatement = conn.prepareStatement(sql);
                prepStatement.setString(1, username);
                prepStatement.setString(2, password);
                resultSet = prepStatement.executeQuery();
                if(!resultSet.next())
                {
                    HienThongBao.infoBox("Tên đăng nhập và mật khẩu không đúng!!!", 
                            "Lỗi", null);
                }
                else{
                    TrangChuController.name = resultSet.getString("ten_nguoi_dung");
                    HienThongBao.infoBox("Đăng nhập thành công", "Thành công", 
                            null);
                    demoStage = (Stage) btnDangNhap.getScene().getWindow();
                    demoStage.hide();
                    ChuyenForm tf = new ChuyenForm();
                    FXMLLoader loader =  tf.transferForm(
                            "toeicapp/TrangChu.fxml", "Trang chủ");
                    TrangChuController controller = loader.getController();
                    controller.setUserName(username);
                    controller.ktDangNhap();
                }
            } catch(SQLException ex){
                System.out.println("Error: " + ex);
            }
        }
    }

    @FXML
    private void btnDangkyClick(ActionEvent event) throws IOException {
        demoStage = (Stage) btnDangKy.getScene().getWindow();
        demoStage.hide();
        ChuyenForm tf = new ChuyenForm();
        tf.transferForm("toeicapp/DangKy.fxml", "Đăng ký tài khoản");
    }

    @FXML
    private void btnHuyClick(ActionEvent event) {
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocus();
    }
    
}
