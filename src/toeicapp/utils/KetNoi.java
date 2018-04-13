/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author annt4
 */
public class KetNoi {
    Connection con = null;

    public Connection ketNoi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/qllt_toeic", "root", "Aa@123456");
//            if(con != null){
//                System.out.println("Kết nối thành công");
//            }
        } catch (ClassNotFoundException e) {
            System.out.println("Load không được driver");
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex.getMessage());
        }
        return con;
    }
}
