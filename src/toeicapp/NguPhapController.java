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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import toeicapp.models.CauHoi;
import toeicapp.models.CauHoiViewModel;
import toeicapp.models.DapAn;
import toeicapp.utils.ChuyenForm;
import toeicapp.utils.HienThongBao;
import toeicapp.utils.KetNoi;

/**
 * FXML Controller class
 *
 * @author annt4
 */
public class NguPhapController implements Initializable {
    @FXML private TextField txtCauHoi;
    @FXML private ToggleGroup chon;
    @FXML private RadioButton radDapAnA;
    @FXML private RadioButton radDapAnB;
    @FXML private RadioButton radDapAnC;
    @FXML private RadioButton radDapAnD;
    @FXML private Button btnTrangChu;
    @FXML private Button btnKiemTra;
    @FXML private Button btnTiepTheo;
    @FXML private Button btnKetThuc;

    private Connection conn = null;
    KetNoi kn = new KetNoi();
    private String maCauHoi;
    private ResultSet rs;
    private PreparedStatement prepStatement;
    Stage demoStage;
    private List<CauHoiViewModel> listCauHoi = new ArrayList<CauHoiViewModel>();
    private List<DapAn> listDapAn = new ArrayList<DapAn>();
    private Integer indexCauHoi = 0;
    private  int soCauDung = 0;
    private int soCauSai = 0;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnTrangChu.setDisable(true);
        conn = kn.ketNoi();
        String sqlCauHoi = "SELECT * FROM cau_hoi WHERE ma_loai_cau_hoi = '1'";
        try {
            PreparedStatement prepCauHoi = conn.prepareStatement(sqlCauHoi);
            rs = prepCauHoi.executeQuery();

            while (rs.next()) {
                CauHoiViewModel ch = new CauHoiViewModel();
                ch.setMaCauHoi(rs.getString("ma_cau_hoi"));
                ch.setCauHoi(rs.getString("cau_hoi"));
                ch.setMaDapAn(rs.getString("dap_an_dung"));
                ch.setMaLoai(rs.getInt("ma_loai_cau_hoi"));
                listCauHoi.add(ch);
            }
            tiepTuc();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex.getMessage());
        }
    }

    @FXML
    public void btnTrangChuClick(ActionEvent event) throws IOException {
        Stage demoStage = (Stage) btnTrangChu.getScene().getWindow();
        demoStage.hide();
        ChuyenForm tf = new ChuyenForm();
        tf.transferForm("toeicapp/TrangChu.fxml", "Trang chủ");
    }

    public void hienThi(String ma_cau_hoi) {
        String sqlDapAn = "SELECT * FROM dap_an WHERE ma_cau_hoi = ?";
        try {
            PreparedStatement prepDapAn = conn.prepareStatement(sqlDapAn);
            prepDapAn.setString(1, ma_cau_hoi);
            ResultSet resultDapAn = prepDapAn.executeQuery();
            try {
                Integer index = 0;
                listDapAn = new ArrayList<>();
                while (resultDapAn.next()) {
                    String noiDungDapAn = resultDapAn.getString("noi_dung_dap_an");
                    String maDapAn = resultDapAn.getString("ma_dap_an");
                    DapAn dapAn = new DapAn();
                    dapAn.setMaDapAn(maDapAn);
                    dapAn.setNoiDungDapAn(noiDungDapAn);
                    switch (index) {
                        case 0:
                            this.radDapAnA.setText(noiDungDapAn);
                            break;
                        case 1:
                            this.radDapAnB.setText(noiDungDapAn);
                            break;
                        case 2:
                            this.radDapAnC.setText(noiDungDapAn);
                            break;
                        default:
                            this.radDapAnD.setText(noiDungDapAn);
                            break;
                    }
                    listDapAn.add(dapAn);
                    index++;
                }
            } catch (SQLException ex) {
                System.out.println("Lỗi" + ex);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi" + e.getMessage());
        }
    }

    public void tiepTuc() {
        if (listCauHoi.size() > 0 && indexCauHoi < listCauHoi.size()) {
            if (indexCauHoi != 0) {
                checkDapAn(listCauHoi.get(indexCauHoi - 1));
            }

            CauHoiViewModel ch = listCauHoi.get(indexCauHoi);
            txtCauHoi.setText(ch.getCauHoi());
            maCauHoi = ch.getMaCauHoi();
            hienThi(maCauHoi);
            indexCauHoi++;
            resetRadio();
        } else {
            try {
                checkDapAn(listCauHoi.get(indexCauHoi - 1));
                HienThongBao.infoBox("Bạn đã kết thúc bài làm",
                        "Thông báo!!!", "Nhấn nút OK để kết thúc");
                demoStage = (Stage) btnTiepTheo.getScene().getWindow();
                demoStage.hide();
                ChuyenForm tf = new ChuyenForm();
                tf.transferForm("toeicapp/KetQua.fxml", "Kết quả");
            } catch (IOException ex) {
                System.out.println("Lỗi" + ex);
            }
        }
    }

    @FXML
    public void btnTiepTheoClick(ActionEvent event) {
        tiepTuc();
    }
// clear textfiled

    public void textField() {

        radDapAnA.setDisable(true);
        radDapAnB.setDisable(true);
        radDapAnC.setDisable(true);
        radDapAnD.setDisable(true);
        radDapAnA.setText("No answer");
        radDapAnB.setText("No answer");
        radDapAnC.setText("No answer");
        radDapAnD.setText("No answer");
        txtCauHoi.setText("No question!");
        txtCauHoi.setEditable(false);
        btnTiepTheo.setDisable(true);
    }

    public void resetRadio() {
        if(radDapAnA.isSelected()) {
            radDapAnA.setSelected(false);
        }else if(radDapAnB.isSelected()){
            radDapAnB.setSelected(false);
        }else if (radDapAnC.isSelected()){
            radDapAnC.setSelected(false);
        } else { 
            radDapAnD.setSelected(false);
        }
    }
    
    public void checkDapAn(CauHoiViewModel ch) {
        String maDapAnDung = ch.getMaDapAn();
        String maDapAn = "";
        if (radDapAnA.isSelected()) {
            maDapAn = listDapAn.get(0).getMaDapAn();
        } else if (radDapAnB.isSelected()) {
            maDapAn = listDapAn.get(1).getMaDapAn();
        } else if (radDapAnC.isSelected()) {
            maDapAn = listDapAn.get(2).getMaDapAn();
        } else if (radDapAnD.isSelected()) {
            maDapAn = listDapAn.get(3).getMaDapAn();
        }

        if (maDapAnDung.equals(maDapAn)) {
            soCauDung++;
            KetQuaController.dung = soCauDung;
        } else {
            soCauSai++;
            KetQuaController.sai = soCauSai;
        }
    }
}
