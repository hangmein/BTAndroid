package gk1.huynhducnghia;

public class BaiThuocModel {
    private String tieuDe;
    private String moTa;
    private int imageResourceId;

    public BaiThuocModel(String tieuDe, String moTa, int imageResourceId) {
        this.tieuDe = tieuDe;
        this.moTa = moTa;
        this.imageResourceId = imageResourceId;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}