package model;

public class Pemohon {
    private String namaLengkap;
    private String departemenBiro;

    public Pemohon(String namaLengkap, String departemenBiro) {
        this.namaLengkap = namaLengkap;
        this.departemenBiro = departemenBiro;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getDepartemenBiro() {
        return departemenBiro;
    }

    public void setDepartemenBiro(String departemenBiro) {
        this.departemenBiro = departemenBiro;
    }
}