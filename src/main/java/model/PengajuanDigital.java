package model;

public class PengajuanDigital extends PengajuanDesain {
    private String targetPlatform;
    private String formatFile;

    public PengajuanDigital(int idPengajuan, Pemohon pemohon, String catatanRevisi, String deadline, String targetPlatform, String formatFile) {
        super(idPengajuan, pemohon, catatanRevisi, deadline);
        this.targetPlatform = targetPlatform;
        this.formatFile = formatFile;
    }

    public String getTargetPlatform() {
        return targetPlatform;
    }

    public void setTargetPlatform(String targetPlatform) {
        this.targetPlatform = targetPlatform;
    }

    public String getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(String formatFile) {
        this.formatFile = formatFile;
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("Kategori: Desain Digital");
        super.tampilkanDetail();
        System.out.println("Rencana Rilis: " + targetPlatform);
        System.out.println("Format File: " + formatFile);
    }
}