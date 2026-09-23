# Sistem Pengajuan Desain Departemen COMINFO

## Deskripsi Singkat Program

COMINFO (Communication and Media Information) adalah salah satu departemen di himpunan mahasiswa Sistem Informasi, INFORSA. Di dalamnya ada divisi Visual Creative yang menangani semua urusan desain, mulai dari feeds, poster, sampai kebutuhan visual lain untuk seluruh departemen/biro di INFORSA. Selama ini pengajuan desain dilakukan lewat formulir manual yang isinya nama pemohon, departemen/biro, jenis desain, informasi tambahan, dan deadline pembuatan.

Untuk tugas mini project 2 mata kuliah Pemrograman Berorientasi Objek (PBO), program pengajuan desain dari Minpro 1 saya kembangkan lebih lanjut. Strukturnya saya ubah ke pola MVC (Model-View-Controller) supaya lebih rapi, dan saya tambahkan penerapan inheritance dengan membedakan pengajuan desain jadi dua jenis: **Desain Digital** (misalnya untuk Instagram Feed, Story, atau Web Banner) dan **Desain Cetak** (misalnya poster atau sertifikat fisik), karena keduanya butuh atribut data yang berbeda.

## Penjelasan Alur Program

Program dijalankan lewat class `Main` di package `cominfo`, yang membuat object `PengajuanController` dan `PengajuanView`, lalu memanggil `view.jalankan()`. Saat `PengajuanController` dibuat, method `muatDataAwal()` otomatis mengisi 2 data dummy ke ArrayList supaya program sudah ada data sejak awal dijalankan.

`PengajuanView.jalankan()` menampilkan menu utama dengan 5 pilihan (Tambah, Tampilkan, Update, Hapus, Keluar) dalam perulangan `while` yang baru berhenti kalau user pilih 5. Semua input user divalidasi lewat method bantu `bacaStringTidakKosong()` (menolak input kosong), `bacaInputAngka()` (menolak input non-angka), dan `bacaPilihanMenu()` (membatasi pilihan ke rentang angka valid), jadi program tidak akan crash walau user salah input.

Kalau user pilih **Tambah Pengajuan**, program minta ID (divalidasi harus positif dan belum dipakai), nama, departemen, catatan, dan deadline, lalu user memilih jenis desain lewat submenu: Digital atau Cetak.

- Kalau pilih **Digital**, user memilih target platform (Instagram Feed / Story / Web Banner) dan format file (PNG/JPG/PDF) lewat menu pilihan, bukan ketik manual. Data lalu dibungkus jadi object `PengajuanDigital`.
- Kalau pilih **Cetak**, user memilih ukuran media (A4 Hardpaper / A3 Poster / Banner 2x1m) lewat menu pilihan, lalu memasukkan jumlah cetak (divalidasi harus lebih dari 0). Data lalu dibungkus jadi object `PengajuanCetak`.

Object yang terbentuk kemudian dikirim ke `PengajuanController.simpanPengajuan()` untuk disimpan ke ArrayList.

Untuk **Tampilkan Pengajuan**, seluruh ArrayList ditelusuri dan tiap object memanggil method `tampilkanDetail()` miliknya sendiri. **Update** dan **Hapus** sama-sama minta ID, dicari lewat `cariBerdasarkanId()` di controller; kalau ID cocok, catatan revisi diperbarui atau datanya dihapus dari ArrayList, kalau tidak ketemu program kasih pesan "tidak ditemukan".

## Penjelasan Penerapan Encapsulation dan Inheritance

**Encapsulation** semua atribut di class `Pemohon`, `PengajuanDesain`, `PengajuanDigital`, dan `PengajuanCetak` dideklarasikan `private`, jadi tidak bisa diakses langsung dari luar class. Untuk mengambil atau mengubah nilainya harus lewat method `public` seperti getter/setter (`getNamaLengkap()`, `getIdPengajuan()`, `setStatus()`, dst).

<img width="622" height="513" alt="Screenshot 2026-09-23 155809" src="https://github.com/user-attachments/assets/2fc2b3d9-8a8d-427c-a78b-a1263a940357" />

**Inheritance** `PengajuanDesain` dibuat sebagai **superclass abstract** yang menyimpan atribut dan method umum (id, pemohon, catatan, deadline, status, `tampilkanDetail()`). Dua class turunannya, `PengajuanDigital` dan `PengajuanCetak`, memakai keyword `extends PengajuanDesain` dan memanggil `super(...)` di constructor-nya untuk mewariskan data dari superclass, lalu menambahkan atribut khusus masing-masing (target platform & format file untuk Digital; ukuran media & jumlah cetak untuk Cetak).

<img width="369" height="121" alt="Screenshot 2026-09-23 160510" src="https://github.com/user-attachments/assets/37f5d677-9ca8-495d-9c79-846a410aca3b" />

<img width="466" height="61" alt="Screenshot 2026-09-23 160921" src="https://github.com/user-attachments/assets/de4a5a9b-0d8a-4873-a71a-053534809a3a" />

<img width="477" height="81" alt="Screenshot 2026-09-23 160951" src="https://github.com/user-attachments/assets/e51a624e-c64b-4b68-b71a-b6d6e8e3f3aa" />


## Penjelasan Letak Penerapan Nilai Tambah

**Struktur MVC** project dibagi jadi 4 package: `model` (`Pemohon`, `PengajuanDesain`, `PengajuanDigital`, `PengajuanCetak` menyimpan struktur data), `view` (`PengajuanView` menangani tampilan menu dan input/output ke user), `controller` (`PengajuanController` mengelola ArrayList data dan logika penyimpanan/pencarian/penghapusan), dan `cominfo` (`Main` entry point yang menghubungkan view dan controller).

<img width="351" height="199" alt="Screenshot 2026-09-23 161136" src="https://github.com/user-attachments/assets/bc4c82a5-bfa0-4f5a-b0ef-af38d8469450" />

**Polymorphism (Method Overriding)** method `tampilkanDetail()` di superclass `PengajuanDesain` di-override di `PengajuanDigital` dan `PengajuanCetak` dengan anotasi `@Override`; masing-masing memanggil `super.tampilkanDetail()` dulu untuk menampilkan info umum, lalu menambahkan info khusus jenisnya (platform & format, atau ukuran & jumlah cetak).

<img width="586" height="146" alt="Screenshot 2026-09-23 161248" src="https://github.com/user-attachments/assets/d3a64c56-5ba9-440f-9f58-461d0a421d34" />

<img width="638" height="154" alt="Screenshot 2026-09-23 161317" src="https://github.com/user-attachments/assets/5f8f8817-cb58-40e1-bf01-ffac79d52cd0" />

**Polymorphism (Method Overloading)** di `PengajuanDesain.java` ada dua method `updateInformasi()` dengan parameter berbeda: satu hanya menerima `catatanRevisi`, satu lagi menerima `catatanRevisi` dan `status` sekaligus.

<img width="643" height="159" alt="Screenshot 2026-09-23 161426" src="https://github.com/user-attachments/assets/511a3ffb-0ee2-440c-8287-022c03416b3e" />
