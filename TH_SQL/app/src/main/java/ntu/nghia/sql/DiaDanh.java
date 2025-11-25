package ntu.nghia.sql;

import java.io.Serializable;

public class DiaDanh implements Serializable {
    private String ten;
    private String moTa;
    private int hinhAnhResId;

    public DiaDanh(String ten, String moTa, int hinhAnhResId) {
        this.ten = ten;
        this.moTa = moTa;
        this.hinhAnhResId = hinhAnhResId;
    }

    public String getTen() {
        return ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getHinhAnhResId() {
        return hinhAnhResId;
    }
}