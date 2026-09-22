package view;

import controller.PengajuanController;
import model.*;
import java.util.Scanner;

public class PengajuanView {
    private final PengajuanController controller;
    private final Scanner scanner;

    public PengajuanView(PengajuanController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    private String bacaStringTidakKosong(String pesan) {
        String input;
        do {
            System.out.print(pesan);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input tidak boleh kosong, silakan coba lagi.");
            }
        } while (input.isEmpty());
        return input;
    }

    private int bacaInputAngka(String pesan) {
        System.out.print(pesan);
        while (!scanner.hasNextInt()) {
            System.out.println("Input harus berupa angka, silakan coba lagi.");
            scanner.next();
            System.out.print(pesan);
        }
        int angka = scanner.nextInt();
        scanner.nextLine();
        return angka;
    }

    private int bacaPilihanMenu(String pesan, int min, int max) {
        while (true) {
            int pilihan = bacaInputAngka(pesan);
            if (pilihan >= min && pilihan <= max) {
                return pilihan;
            }
            System.out.println("Pilihan tidak valid, silakan masukkan angka antara " + min + " sampai " + max + ".");
        }
    }

    public void jalankan() {
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\nSISTEM PENGAJUAN DESAIN COMINFO");
            System.out.println("1. Tambah Pengajuan Desain");
            System.out.println("2. Tampilkan Semua Pengajuan");
            System.out.println("3. Update Catatan / Detail Revisi");
            System.out.println("4. Hapus Pengajuan");
            System.out.println("5. Keluar");

            int pilihan = bacaPilihanMenu("Pilih menu (1-5): ", 1, 5);

            switch (pilihan) {
                case 1 -> tambahData();
                case 2 -> tampilkanData();
                case 3 -> updateData();
                case 4 -> hapusData();
                case 5 -> {
                    System.out.println("Sistem selesai.");
                    lanjut = false;
                }
            }
        }
    }

    private void tambahData() {
        System.out.println("\nTambah Pengajuan Baru");
        int id;
        while (true) {
            id = bacaInputAngka("Masukkan ID Pengajuan: ");
            if (id <= 0) {
                System.out.println("ID Pengajuan harus bernilai positif.");
            } else if (controller.cariBerdasarkanId(id) != null) {
                System.out.println("ID sudah digunakan, silakan pakai ID lain.");
            } else {
                break;
            }
        }

        String nama = bacaStringTidakKosong("Nama Pemohon: ");
        String dept = bacaStringTidakKosong("Departemen/Biro: ");
        String info = bacaStringTidakKosong("Catatan/Kebutuhan: ");
        String deadline = bacaStringTidakKosong("Deadline: ");

        System.out.println("\nPilih Jenis Desain:");
        System.out.println("1. Desain Digital");
        System.out.println("2. Desain Cetak");
        int tipe = bacaPilihanMenu("Pilih jenis (1/2): ", 1, 2);

        Pemohon pemohon = new Pemohon(nama, dept);

        if (tipe == 1) {
            System.out.println("\nPilih Target Platform:");
            System.out.println("1. Instagram Feed");
            System.out.println("2. Instagram Story");
            System.out.println("3. Web Banner");
            int optPlatform = bacaPilihanMenu("Pilih platform (1-3): ", 1, 3);
            String platform = switch (optPlatform) {
                case 1 -> "Instagram Feed";
                case 2 -> "Instagram Story";
                case 3 -> "Web Banner";
                default -> "Digital Media";
            };

            System.out.println("\nPilih Format File:");
            System.out.println("1. PNG");
            System.out.println("2. JPG");
            System.out.println("3. PDF");
            int optFormat = bacaPilihanMenu("Pilih format (1-3): ", 1, 3);
            String format = switch (optFormat) {
                case 1 -> "PNG";
                case 2 -> "JPG";
                case 3 -> "PDF";
                default -> "PNG";
            };

            PengajuanDigital pd = new PengajuanDigital(id, pemohon, info, deadline, platform, format);
            controller.simpanPengajuan(pd);
            System.out.println("Pengajuan desain digital berhasil disimpan.");
        } else if (tipe == 2) {
            System.out.println("\nPilih Ukuran Media:");
            System.out.println("1. A4 Hardpaper");
            System.out.println("2. A3 Poster");
            System.out.println("3. Banner 2x1m");
            int optUkuran = bacaPilihanMenu("Pilih ukuran (1-3): ", 1, 3);
            String ukuran = switch (optUkuran) {
                case 1 -> "A4 Hardpaper";
                case 2 -> "A3 Poster";
                case 3 -> "Banner 2x1m";
                default -> "A4 Standard";
            };

            int jumlah;
            while (true) {
                jumlah = bacaInputAngka("Jumlah Cetak (pcs): ");
                if (jumlah > 0) {
                    break;
                }
                System.out.println("Jumlah cetak minimal harus 1 pcs.");
            }

            PengajuanCetak pc = new PengajuanCetak(id, pemohon, info, deadline, ukuran, jumlah);
            controller.simpanPengajuan(pc);
            System.out.println("Pengajuan desain cetak berhasil disimpan.");
        }
    }

    private void tampilkanData() {
        System.out.println("\nDaftar Pengajuan Desain");
        var list = controller.getSemuaPengajuan();
        if (list.isEmpty()) {
            System.out.println("Belum ada data pengajuan.");
            return;
        }

        for (PengajuanDesain p : list) {
            p.tampilkanDetail();
            System.out.println();
        }
    }

    private void updateData() {
        System.out.println("\nUpdate Catatan / Detail Revisi");
        int id = bacaInputAngka("Masukkan ID Pengajuan: ");
        PengajuanDesain p = controller.cariBerdasarkanId(id);

        if (p == null) {
            System.out.println("Data pengajuan tidak ditemukan.");
            return;
        }

        System.out.println("Catatan saat ini: " + p.getCatatanRevisi());
        String infoBaru = bacaStringTidakKosong("Masukkan Catatan/Revisi Baru: ");

        p.updateInformasi(infoBaru);
        System.out.println("Catatan revisi berhasil diperbarui.");
    }

    private void hapusData() {
        System.out.println("\nHapus Data Pengajuan");
        int id = bacaInputAngka("Masukkan ID Pengajuan: ");
        boolean berhasil = controller.hapusPengajuan(id);

        if (berhasil) {
            System.out.println("Data pengajuan berhasil dihapus.");
        } else {
            System.out.println("Data pengajuan tidak ditemukan.");
        }
    }
}