package ntu.nghia.sql;

import java.io.Serializable;

// Implement Serializable để có thể truyền cả object qua Intent giữa các màn hình
public class DiaDanh implements Serializable {
    private String ten;
    private String moTa;
    private int hinhAnhResId; // ID của ảnh trong res/drawable (ví dụ R.drawable.anh1)

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