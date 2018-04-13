/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp.models;

/**
 *
 * @author annt4
 */
public class LoaiCauHoi {
    private int maLoaiCauHoi;
    private String tenLoaiCauHoi;

    public LoaiCauHoi(){
    
    }
    
    public LoaiCauHoi(int maLoaiCauHoi, String tenLoaiCauHoi) {
        this.maLoaiCauHoi = maLoaiCauHoi;
        this.tenLoaiCauHoi = tenLoaiCauHoi;
    }
    
    @Override
    public String toString() {
        return this.tenLoaiCauHoi;
    }
    
    public Integer toInteger(){
        return this.maLoaiCauHoi;
    }
    
    /**
     * @return the maLoaiCauHoi
     */
    public int getMaLoaiCauHoi() {
        return maLoaiCauHoi;
    }

    /**
     * @param maLoaiCauHoi the maLoaiCauHoi to set
     */
    public void setMaLoaiCauHoi(int maLoaiCauHoi) {
        this.maLoaiCauHoi = maLoaiCauHoi;
    }

    /**
     * @return the tenLoaiCauHoi
     */
    public String getTenLoaiCauHoi() {
        return tenLoaiCauHoi;
    }

    /**
     * @param tenLoaiCauHoi the tenLoaiCauHoi to set
     */
    public void setTenLoaiCauHoi(String tenLoaiCauHoi) {
        this.tenLoaiCauHoi = tenLoaiCauHoi;
    }
}
