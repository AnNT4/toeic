/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 *
 * @author annt4
 */
public class KiemTraNhap {
    Stage demoStage = new Stage();
    public Connection conn = null;
    public PreparedStatement prepStatement = null;
    public ResultSet resultSet = null;
    public Button btn;
    
    // Kiểm tra rỗng
    public static boolean ktTextFieldRong(TextField txt)
    {
        boolean b = false;
        if (txt.getText().length()!=0 || !txt.getText().isEmpty()) 
        {
           b = true;  
        }
        return b;
    }
    public static boolean ktTextFieldRong(TextField txt, Label lbl, String errMsg)
    {
        boolean b = true;
        String msg = null;
        if (!ktTextFieldRong(txt)) 
        {
           b = false;  
           msg = errMsg;
        }
        lbl.setText(msg);
        return b;
    }

    public static boolean ktDinhDangEmail(TextField txt) {
        boolean b = false;
        String email = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        if (txt.getText().matches(email)) {
            b = true;
        }
        return b;
    }

    public static boolean ktDinhDangEmail(TextField txt, Label lbl, String errMsg) {
        boolean b = true;
        String msg = null;
        if (!ktDinhDangEmail(txt)) {
            b = false;
            msg = errMsg;
        }
        lbl.setText(msg);
        return b;
    }
}
