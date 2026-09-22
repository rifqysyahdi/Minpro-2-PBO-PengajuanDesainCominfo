package model;

public class PengajuanCetak extends PengajuanDesain {
    private String ukuranMedia;
    private int jumlahCetak;

    public PengajuanCetak(int idPengajuan, Pemohon pemohon, String catatanRevisi, String deadline, String ukuranMedia, int jumlahCetak) {
        super(idPengajuan, pemohon, catatanRevisi, deadline);
        this.ukuranMedia = ukuranMedia;
        this.jumlahCetak = jumlahCetak;
    }

    public String getUkuranMedia() {
        return ukuranMedia;
    }

    public void setUkuranMedia(String ukuranMedia) {
        this.ukuranMedia = ukuranMedia;
    }

    public int getJumlahCetak() {
        return jumlahCetak;
    }

    public void setJumlahCetak(int jumlahCetak) {
        this.jumlahCetak = jumlahCetak;
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("Kategori: Desain Cetak");
        super.tampilkanDetail();
        System.out.println("Ukuran Media: " + ukuranMedia);
        System.out.println("Jumlah Cetak: " + jumlahCetak + " pcs");
    }
}