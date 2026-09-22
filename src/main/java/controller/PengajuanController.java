package controller;

import model.*;
import java.util.ArrayList;

public class PengajuanController {
    private final ArrayList<PengajuanDesain> listPengajuan;

    public PengajuanController() {
        this.listPengajuan = new ArrayList<>();
        muatDataAwal();
    }

    private void muatDataAwal() {
        Pemohon pemohon1 = new Pemohon("Yahya Jailani", "Biro EDEN");
        PengajuanDigital p1 = new PengajuanDigital(1, pemohon1, "Poster INSTAND", "09/09/2026", "Instagram Feed", "PNG");

        Pemohon pemohon2 = new Pemohon("Ahmad Ahdasuki", "Departemen PSD");
        PengajuanCetak p2 = new PengajuanCetak(2, pemohon2, "Sertifikat ISC", "17/10/2026", "A4 Hardpaper", 50);

        listPengajuan.add(p1);
        listPengajuan.add(p2);
    }

    public void simpanPengajuan(PengajuanDesain pengajuan) {
        listPengajuan.add(pengajuan);
    }

    public ArrayList<PengajuanDesain> getSemuaPengajuan() {
        return listPengajuan;
    }

    public PengajuanDesain cariBerdasarkanId(int id) {
        for (PengajuanDesain p : listPengajuan) {
            if (p.getIdPengajuan() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean hapusPengajuan(int id) {
        PengajuanDesain target = cariBerdasarkanId(id);
        if (target != null) {
            listPengajuan.remove(target);
            return true;
        }
        return false;
    }
}