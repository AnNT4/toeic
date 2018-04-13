/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import toeicapp.models.CauHoiViewModel;
import toeicapp.models.DapAn;
import toeicapp.models.LoaiCauHoi;
import toeicapp.utils.ChuyenForm;
import toeicapp.utils.HienThongBao;
import toeicapp.utils.KetNoi;

/**
 * FXML Controller class
 *
 * @author annt4
 */
public class QuanLyCauHoiController implements Initializable {

    @FXML private TextField txtCauHoi;
    @FXML private ComboBox<LoaiCauHoi> cboLoaiCauHoi;
    @FXML private TextField txtDapAnA;
    @FXML private TextField txtDapAnB;
    @FXML private TextField txtDapAnC;
    @FXML private TextField txtDapAnD;
    @FXML private TextField txtTimKiem;
    @FXML private Button btnTrangChu;
    @FXML private Button btnThemMoi;
    @FXML private Button btnCapNhat;
    @FXML private Button btnXoa;
    @FXML private Button btnChonAnh;
    @FXML private Button btnChonAudio;
    @FXML private RadioButton radDapAnA;
    @FXML private ToggleGroup chon;
    @FXML private RadioButton radDapAnB;
    @FXML private RadioButton radDapAnC;
    @FXML private RadioButton radDapAnD;
    @FXML private Button btnHuy;
    @FXML private TableView<CauHoiViewModel> tblHienThi;
    @FXML private ImageView picCauHoi;
    @FXML private MediaView mediaCauHoi;
    private Connection conn = null;
    private ObservableList<DapAn> listDapAn = FXCollections.observableArrayList();
    private final int LOAI_HINH_ANH = 3;
    private String searchString = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KetNoi kn = new KetNoi();
        conn = kn.ketNoi();
        this.initCombobox();
        this.initTableView();
    }    

    private void initCombobox() {
        String sql = "SELECT * FROM loai_cau_hoi";
        try {
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet resultSet = prep.executeQuery();
            ObservableList<LoaiCauHoi> listLoaiCauHoi = FXCollections
                    .observableArrayList();
            while (resultSet.next()) {
                listLoaiCauHoi.add(new LoaiCauHoi(resultSet.getInt(1), resultSet
                        .getString(2)));
            }
            cboLoaiCauHoi.setItems(listLoaiCauHoi);
            cboLoaiCauHoi.getSelectionModel().select(0);
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex.getMessage());
        }
    }
    
    private void initTableView() {
        TableColumn<CauHoiViewModel, String> colCauHoi //
                = new TableColumn<CauHoiViewModel, String>("Câu hỏi");

        TableColumn<CauHoiViewModel, String> colLoaiCauHoi //
                = new TableColumn<CauHoiViewModel, String>("Loại câu hỏi");
        
        TableColumn<CauHoiViewModel, String> colDapAnDung //
                = new TableColumn<CauHoiViewModel, String>("Đáp án đúng");

        colCauHoi.setCellValueFactory(new PropertyValueFactory<>("CauHoi"));
        colLoaiCauHoi.setCellValueFactory(new PropertyValueFactory<>("TenLoai"));
        colDapAnDung.setCellValueFactory(new PropertyValueFactory<>("DapAn"));

        tblHienThi.getColumns().addAll(colCauHoi, colLoaiCauHoi, colDapAnDung);
        
        this.getCauHoi();
    }
    
    private void getCauHoi() {
        String query = "SELECT A.ma_cau_hoi AS MaCauHoi, A.cau_hoi AS CauHoi, "
                + "B.ma_loai_cau_hoi AS MaLoai, B.ten_loai_cau_hoi AS LoaiCauHoi, "
                + "C.ma_dap_an AS MaDapAn, C.noi_dung_dap_an AS DapAn "
                + "FROM cau_hoi A "
                + "JOIN loai_cau_hoi B ON A.ma_loai_cau_hoi = B.ma_loai_cau_hoi "
                + "JOIN dap_an C ON A.dap_an_dung = C.ma_dap_an";
        if (!this.searchString.equals("")) {
            query = query + " WHERE A.cau_hoi like ?";
        }

        try {

            PreparedStatement prep = conn.prepareStatement(query);
            if (!this.searchString.equals("")) {
                prep.setString(1, "%" + this.searchString + "%");
            }
            ResultSet resultSet = prep.executeQuery();
            ObservableList<CauHoiViewModel> listCauHoi = FXCollections
                    .observableArrayList();
            while (resultSet.next()) {
                CauHoiViewModel cauHoi = new CauHoiViewModel();
                cauHoi.setMaCauHoi(resultSet.getString("MaCauHoi"));
                cauHoi.setCauHoi(resultSet.getString("CauHoi"));
                cauHoi.setMaDapAn(resultSet.getString("MaDapAn"));
                cauHoi.setDapAn(resultSet.getString("DapAn"));
                cauHoi.setMaLoai(resultSet.getInt("MaLoai"));
                cauHoi.setTenLoai(resultSet.getString("LoaiCauHoi"));
                listCauHoi.add(cauHoi);
            }
            tblHienThi.setItems(listCauHoi);
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex.getMessage());
        }
    }
    
    @FXML
    private void comboboxLoaiCauHoiClick(ActionEvent event) {
        if (this.cboLoaiCauHoi.getValue().getMaLoaiCauHoi() == LOAI_HINH_ANH) {
            this.btnChonAnh.setDisable(false);
            this.btnChonAudio.setDisable(false);
        } else {
            this.btnChonAnh.setDisable(true);
            this.btnChonAudio.setDisable(true);
        }
    }

    @FXML
    private void searchCauHoiKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.searchString = this.txtTimKiem.getText();
            this.getCauHoi();
        }
    }

    @FXML
    private void btnTrangChuClick(ActionEvent event) throws IOException {
        Stage demoStage = (Stage) btnTrangChu.getScene().getWindow();
        demoStage.hide();
        ChuyenForm tf = new ChuyenForm();
        tf.transferForm("toeicapp/TrangChu.fxml", "Trang chủ");
    }

    private boolean validationData() {
        if (this.txtCauHoi.getText().equals("")) {
            HienThongBao.errorBox("Câu hỏi không được trống", "Lỗi", null);
            return false;
        }
        if (this.txtDapAnA.getText().equals("")
                || this.txtDapAnB.getText().equals("")
                || this.txtDapAnC.getText().equals("")
                || this.txtDapAnD.getText().equals("")) {
            HienThongBao.errorBox("Đáp án không được trống", "Lỗi", null);
            return false;
        }
        return true;
    }
    
    @FXML
    private void btnThemMoiClick(ActionEvent event) {
        try {
            if (this.validationData()) {
                String maCauHoi = UUID.randomUUID().toString();
                String maDapAnA = UUID.randomUUID().toString();
                String maDapAnB = UUID.randomUUID().toString();
                String maDapAnC = UUID.randomUUID().toString();
                String maDapAnD = UUID.randomUUID().toString();
                LoaiCauHoi id = cboLoaiCauHoi.getValue();
                String maDapAnDung = maDapAnA;
                if (radDapAnB.isSelected()) {
                    maDapAnDung = maDapAnB;
                } else if (radDapAnC.isSelected()) {
                    maDapAnDung = maDapAnC;
                } else if (radDapAnD.isSelected()) {
                    maDapAnDung = maDapAnD;
                }

                String sql;
                sql = " INSERT INTO cau_hoi (ma_cau_hoi, cau_hoi, "
                        + "ma_loai_cau_hoi)"
                        + " VALUES (?, ?, ?);";
                PreparedStatement prep1 = conn.prepareStatement(sql);
                prep1.setString(1, maCauHoi);
                prep1.setString(2, txtCauHoi.getText().trim());
                prep1.setInt(3, id.getMaLoaiCauHoi());
                prep1.executeUpdate();

                sql = " INSERT INTO dap_an (ma_dap_an, noi_dung_dap_an, "
                        + " ma_cau_hoi) "
                        + " VALUES (?, ?, ?);";
                PreparedStatement prep2 = conn.prepareStatement(sql);
                prep2.setString(1, maDapAnA);
                prep2.setString(2, txtDapAnA.getText().trim());
                prep2.setString(3, maCauHoi);
                prep2.executeUpdate();

                sql = " INSERT INTO dap_an (ma_dap_an, noi_dung_dap_an, "
                        + " ma_cau_hoi) "
                        + " VALUES (?, ?, ?);";
                PreparedStatement prep3 = conn.prepareStatement(sql);
                prep3 = conn.prepareStatement(sql);
                prep3.setString(1, maDapAnB);
                prep3.setString(2, txtDapAnB.getText().trim());
                prep3.setString(3, maCauHoi);
                prep3.executeUpdate();

                sql = " INSERT INTO dap_an (ma_dap_an, noi_dung_dap_an, "
                        + " ma_cau_hoi) "
                        + " VALUES (?, ?, ?);";
                PreparedStatement prep4 = conn.prepareStatement(sql);
                prep4.setString(1, maDapAnC);
                prep4.setString(2, txtDapAnC.getText().trim());
                prep4.setString(3, maCauHoi);
                prep4.executeUpdate();

                sql = " INSERT INTO dap_an (ma_dap_an, noi_dung_dap_an, "
                        + " ma_cau_hoi) "
                        + " VALUES (?, ?, ?);";
                PreparedStatement prep5 = conn.prepareStatement(sql);
                prep5.setString(1, maDapAnD);
                prep5.setString(2, txtDapAnD.getText().trim());
                prep5.setString(3, maCauHoi);
                prep5.executeUpdate();

                sql = " UPDATE cau_hoi SET dap_an_dung = ? "
                        + "WHERE ma_cau_hoi = ? ";
                PreparedStatement prep6 = conn.prepareStatement(sql);
                prep6.setString(1, maDapAnDung);
                prep6.setString(2, maCauHoi);
                prep6.executeUpdate();
                HienThongBao.infoBox("Thêm câu hỏi thành công", "Thành công", 
                        null);
                this.getCauHoi();
                this.resetData();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void btnCapNhatClick(ActionEvent event) {
        try {
            
            String updateCauHoiQuery = "UPDATE cau_hoi "
                + "SET cau_hoi = ?, "
                + "ma_loai_cau_hoi = ?, "
                + "dap_an_dung = ? "
                + "WHERE ma_cau_hoi = ?";

            String updateDapAnQuery = "UPDATE dap_an "
                + "SET noi_dung_dap_an = ? "
                + "WHERE ma_dap_an = ?";

            if (this.validationData()) {
                CauHoiViewModel cauHoiSelected = this.tblHienThi
                        .getSelectionModel().getSelectedItem();

                String maDapAnDung;
                if (this.radDapAnA.isSelected()) {
                    maDapAnDung = this.listDapAn.get(0).getMaDapAn();
                } else if (this.radDapAnB.isSelected()) {
                    maDapAnDung = this.listDapAn.get(1).getMaDapAn();
                } else if (this.radDapAnC.isSelected()) {
                    maDapAnDung = this.listDapAn.get(2).getMaDapAn();
                } else {
                    maDapAnDung = this.listDapAn.get(3).getMaDapAn();
                }

                PreparedStatement prepCauHoi = conn
                        .prepareStatement(updateCauHoiQuery);
                prepCauHoi.setString(1, this.txtCauHoi.getText().trim());
                prepCauHoi.setInt(2, this.cboLoaiCauHoi.getValue()
                        .getMaLoaiCauHoi());
                prepCauHoi.setString(3, maDapAnDung);
                prepCauHoi.setString(4, cauHoiSelected.getMaCauHoi());
                prepCauHoi.executeUpdate();

                for (int i = 0; i < this.listDapAn.size(); i++) {
                    PreparedStatement prepDapAn = conn
                            .prepareStatement(updateDapAnQuery);
                    prepDapAn.setString(2, this.listDapAn.get(i).getMaDapAn());
                    String ndDapAn;
                    switch (i) {
                        case 0:
                            ndDapAn = this.txtDapAnA.getText();
                            break;
                        case 1:
                            ndDapAn = this.txtDapAnB.getText();
                            break;
                        case 2:
                            ndDapAn = this.txtDapAnC.getText();
                            break;
                        default:
                            ndDapAn = this.txtDapAnD.getText();
                            break;
                    }
                    prepDapAn.setString(1, ndDapAn.trim());
                    prepDapAn.executeUpdate();
                }

                HienThongBao.infoBox("Sửa câu hỏi thành công", "Thành công", 
                        null);
                this.getCauHoi();
                this.tblHienThi.getSelectionModel().select(cauHoiSelected);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi" + e.getMessage());
        }
    }

    private void resetData() {
        txtCauHoi.setText("");
        txtCauHoi.requestFocus();
        txtDapAnA.setText("");
        txtDapAnB.setText("");
        txtDapAnC.setText("");
        txtDapAnD.setText("");
        radDapAnA.setSelected(true);
        this.btnChonAnh.setDisable(true);
        this.btnChonAudio.setDisable(true);
        this.btnCapNhat.setDisable(true);
        this.btnXoa.setDisable(true);
        this.btnThemMoi.setDisable(false);
        this.listDapAn = FXCollections.observableArrayList();
        cboLoaiCauHoi.getSelectionModel().select(0);
    }
    
//    private void xoaCauHoi() {
//        String deleteCauHoiQuery = "DELETE FROM cau_hoi WHERE ma_cau_hoi = ?";
//        String deleteDapAnQuery = "DELETE FROM dap_an WHERE ma_cau_hoi = ?";
//        try {
//            CauHoiViewModel cauHoiSelected = this.tblHienThi.getSelectionModel()
//                    .getSelectedItem();
//            PreparedStatement prepDapAn = conn.prepareStatement(deleteDapAnQuery);
//            prepDapAn.setString(1, cauHoiSelected.getMaCauHoi());
//            prepDapAn.executeUpdate();
//
//            PreparedStatement prepCauHoi = conn.prepareStatement(deleteCauHoiQuery);
//            prepCauHoi.setString(1, cauHoiSelected.getMaCauHoi());
//            prepCauHoi.executeUpdate();
//            HienThongBao.infoBox("Xoá câu hỏi thành công", "Thành công", null);
//            this.getCauHoi();
//            this.resetData();
//        } catch (SQLException e) {
//            System.out.println("Lỗi" + e.getMessage());
//        }
//    }
//    
//    @FXML
//    private void btnXoaClick(ActionEvent event) {
//         HienThongBao.confirmBox("Bạn muốn xoá câu hỏi", "Xoá câu hỏi", null)
//                .ifPresent(response -> {
//                    if (response == ButtonType.OK) {
//                        xoaCauHoi();
//                    }
//                });
//    }
    
    private static void copyFileUsing(File source, File dest) 
            throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }
    
    @FXML
    private void btnChonAnhClick(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Chọn ảnh");
            File source = chooser.showOpenDialog(new Stage());
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if (source != null) {
                File dest = new File("./images");
                copyFileUsing(source, dest);
                HienThongBao.infoBox("Thêm ảnh thành công", "Thành công", null);
                //Image img = new Image("./images");
                //this.picCauHoi.setImage(img);
            }
        } catch (IOException e) {
            System.out.println("Lỗi" + e.getMessage());
        }
    }

    @FXML
    private void btnChonAudioClick(ActionEvent event) {
    }

    @FXML
    private void btnHuyClick(ActionEvent event) {
        this.resetData();
    }

    @FXML
    private void tableViewClicked(MouseEvent event) {
        CauHoiViewModel cauHoiSelected = this.tblHienThi.getSelectionModel()
                .getSelectedItem();
        String dapAnQuery = "SELECT * FROM dap_an WHERE ma_cau_hoi = ?";

        try {
            this.btnCapNhat.setDisable(false);
            this.btnXoa.setDisable(false);
            this.btnThemMoi.setDisable(true);

            this.txtCauHoi.setText(cauHoiSelected.getCauHoi());
            for (LoaiCauHoi loaiCauHoi : this.cboLoaiCauHoi.getItems()) {
                if (loaiCauHoi.getMaLoaiCauHoi() == cauHoiSelected.getMaLoai()) {
                    this.cboLoaiCauHoi.setValue(loaiCauHoi);
                    break;
                }
            }

            PreparedStatement prepDapAn = conn.prepareStatement(dapAnQuery);
            prepDapAn.setString(1, cauHoiSelected.getMaCauHoi());
            ResultSet resultDapAn = prepDapAn.executeQuery();
            listDapAn = FXCollections.observableArrayList();
            Integer index = 0;
            while (resultDapAn.next()) {
                String noiDungDapAn = resultDapAn.getString("noi_dung_dap_an");
                String maDapAn = resultDapAn.getString("ma_dap_an");
                DapAn dapAn = new DapAn();
                dapAn.setMaDapAn(maDapAn);
                dapAn.setNoiDungDapAn(noiDungDapAn);
                this.listDapAn.add(dapAn);
                switch (index) {
                    case 0:
                        this.txtDapAnA.setText(noiDungDapAn);
                        this.radDapAnA.setSelected(maDapAn.equals
                            (cauHoiSelected.getMaDapAn()));
                        break;
                    case 1:
                        this.txtDapAnB.setText(noiDungDapAn);
                        this.radDapAnB.setSelected(maDapAn.equals
                            (cauHoiSelected.getMaDapAn()));
                        break;
                    case 2:
                        this.txtDapAnC.setText(noiDungDapAn);
                        this.radDapAnC.setSelected(maDapAn.equals
                            (cauHoiSelected.getMaDapAn()));
                        break;
                    default:
                        this.txtDapAnD.setText(noiDungDapAn);
                        this.radDapAnD.setSelected(maDapAn.equals
                            (cauHoiSelected.getMaDapAn()));
                        break;
                }
                index++;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi" + e.getMessage());
        }
    }
    
}
