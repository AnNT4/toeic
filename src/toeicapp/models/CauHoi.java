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
public class CauHoi {
    private String maCauHoi;
    private String cauHoi;
    private LoaiCauHoi loaiCauHoi;
    private DapAn dapan;
    private byte[] imageData;
    private String imageFileName;
    
    public CauHoi(String maCauHoi, String cauHoi, byte[] imageData, 
            String imageFileName){
        this.maCauHoi = maCauHoi;
        this.cauHoi = cauHoi;
        this.imageData = imageData;
        this.imageFileName = imageFileName;
    }
    
    /**
     * @return the maCauHoi
     */
    public String getMaCauHoi() {
        return maCauHoi;
    }

    /**
     * @param maCauHoi the maCauHoi to set
     */
    public void setMaCauHoi(String maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    /**
     * @return the cauHoi
     */
    public String getCauHoi() {
        return cauHoi;
    }

    /**
     * @param cauHoi the cauHoi to set
     */
    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    /**
     * @return the imageData
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * @param imageData the imageData to set
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    /**
     * @return the imageFileName
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     * @param imageFileName the imageFileName to set
     */
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
