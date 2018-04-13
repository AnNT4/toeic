/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp.utils;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 *
 * @author annt4
 */
public class HienThongBao {
    public static void infoBox(String infoMessage, String titleBar,
            String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    public static void errorBox(String infoMessage, String titleBar,
            String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    public static Optional<ButtonType> confirmBox(String infoMessage, String titleBar,
            String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        return alert.showAndWait();
    }
}
