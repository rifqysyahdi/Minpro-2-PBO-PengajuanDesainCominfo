package model;

public abstract class PengajuanDesain {
    private final int idPengajuan;
    private final Pemohon pemohon;
    private String catatanRevisi;
    private final String deadline;
    private String status;

    public PengajuanDesain(int idPengajuan, Pemohon pemohon, String catatanRevisi, String deadline) {
        this.idPengajuan = idPengajuan;
        this.pemohon = pemohon;
        this.catatanRevisi = catatanRevisi;
        this.deadline = deadline;
        this.status = "Diterima";
    }

    public void updateInformasi(String catatanRevisi) {
        this.catatanRevisi = catatanRevisi;
    }

    public void updateInformasi(String catatanRevisi, String status) {
        this.catatanRevisi = catatanRevisi;
        this.status = status;
    }

    public void tampilkanDetail() {
        System.out.println("ID Pengajuan: " + idPengajuan);
        System.out.println("Pemohon: " + pemohon.getNamaLengkap() + " (" + pemohon.getDepartemenBiro() + ")");
        System.out.println("Catatan/Info: " + catatanRevisi);
        System.out.println("Tenggat Waktu: " + deadline);
        System.out.println("Status Saat Ini: " + status);
    }

    public int getIdPengajuan() {
        return idPengajuan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public String getCatatanRevisi() {
        return catatanRevisi;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}