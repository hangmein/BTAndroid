package ntu.nghia.project;

public class GameMode {
    private int id;             // ID giữ nguyên
    private String tenCheDo;    // Thay cho name
    private String moTa;        // Thay cho description
    private int anhDaiDien;     // Thay cho iconResId

    public GameMode(int id, String tenCheDo, String moTa, int anhDaiDien) {
        this.id = id;
        this.tenCheDo = tenCheDo;
        this.moTa = moTa;
        this.anhDaiDien = anhDaiDien;
    }

    public int getId() { return id; }
    public String getTenCheDo() { return tenCheDo; }
    public String getMoTa() { return moTa; }
    public int getAnhDaiDien() { return anhDaiDien; }
}