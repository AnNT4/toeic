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
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
 * FXML Controller class
 *
 * @author annt4
 */
public class DangKyController implements Initializable {
    @FXML private Button btnTaoTaiKhoan;
    @FXML private TextField txtUsername;
    @FXML private Label lblErrorUsername;
    @FXML private TextField txtEmail;
    @FXML private Label lblErrorEmail;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblErrorPassword;
    private Connection conn = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;
    Stage demoStage = new Stage();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnTaoTaiKhoanClick(ActionEvent event) throws IOException {
        boolean ktTestFieldUsername = KiemTraNhap.ktTextFieldRong(txtUsername, 
                lblErrorUsername, "Bạn chưa nhập tên người dùng");
        boolean ktTestFieldEmail = KiemTraNhap.ktTextFieldRong(txtPassword, 
                lblErrorEmail, "Bạn chưa nhập mật khẩu");
        boolean ktTestFieldPassword = KiemTraNhap.ktTextFieldRong(txtPassword, 
                lblErrorPassword, "Bạn chưa nhập mật khẩu");
        boolean ktddEmail = KiemTraNhap.ktDinhDangEmail(txtEmail, lblErrorEmail, 
                "Định dạng email chưa đúng");
        if(ktTestFieldUsername && ktTestFieldPassword && ktTestFieldEmail &&
                ktddEmail) {
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();
            String ma = UUID.randomUUID().toString();
            KetNoi kn = new KetNoi();
            conn = kn.ketNoi();
            String sql = "INSERT INTO nguoi_dung(ma_nguoi_dung, ten_nguoi_dung,"
                    + " email, mat_khau) VALUES (?, ?, ?, ?)";
            try {
                prepStatement = conn.prepareStatement(sql);
                prepStatement.setString(1, ma);
                prepStatement.setString(2, username);
                prepStatement.setString(3, email);
                prepStatement.setString(4, password);
                int i = prepStatement.executeUpdate();
                if(i != 0)
                {
                    HienThongBao.infoBox("Tạo tài khoản thành công","Thông báo", 
                            "Thành công");
                    demoStage = (Stage) btnTaoTaiKhoan.getScene().getWindow();
                        demoStage.hide();
                        ChuyenForm tf = new ChuyenForm ();
                        tf.transferForm("toeicapp/DangNhap.fxml",
                            "Đăng nhập hệ thống");
                }
                else
                    HienThongBao.infoBox("Tạo thất bại", "Thông báo!", "Lỗi!");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
    
}
