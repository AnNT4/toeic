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
public class DapAn {
     private String maDapAn;
    private String noiDungDapAn;
    private CauHoi cauhoi;

    /**
     * @return the maDapAn
     */
    public String getMaDapAn() {
        return maDapAn;
    }

    /**
     * @return the noiDungDapAn
     */
    public String getNoiDungDapAn() {
        return noiDungDapAn;
    }

    /**
     * @param maDapAn the maDapAn to set
     */
    public void setMaDapAn(String maDapAn) {
        this.maDapAn = maDapAn;
    }

    /**
     * @param noiDungDapAn the noiDungDapAn to set
     */
    public void setNoiDungDapAn(String noiDungDapAn) {
        this.noiDungDapAn = noiDungDapAn;
    }
}
