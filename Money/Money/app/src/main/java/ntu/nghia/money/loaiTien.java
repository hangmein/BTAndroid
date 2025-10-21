package ntu.nghia.money;

public class loaiTien {
    String tenTien;
    int flag;
    double tiGiaSoVoiUSD;

    public loaiTien(String tenTien, int imageID, double tiGiaSoVoiUSD)
    {
        this.tenTien = tenTien;
        this.flag = imageID;
        this.tiGiaSoVoiUSD = tiGiaSoVoiUSD;
    }

    public String getTenTien(){
        return tenTien;
    }

    public int getFlag(){
        return flag;
    }

    public double getTiGia() { return tiGiaSoVoiUSD; }
}