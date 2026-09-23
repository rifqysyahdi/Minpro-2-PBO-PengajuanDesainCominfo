# Sistem Pengajuan Desain Departemen COMINFO

## Deskripsi Singkat Program

COMINFO (Communication and Media Information) adalah salah satu departemen di himpunan mahasiswa Sistem Informasi, INFORSA. Di dalamnya ada divisi Visual Creative yang menangani semua urusan desain, mulai dari feeds, poster, sampai kebutuhan visual lain untuk seluruh departemen/biro di INFORSA. Selama ini pengajuan desain dilakukan lewat formulir manual yang isinya nama pemohon, departemen/biro, jenis desain, informasi tambahan, dan deadline pembuatan.

Untuk tugas mini project 2 mata kuliah Pemrograman Berorientasi Objek (PBO), program pengajuan desain dari Minpro 1 saya kembangkan lebih lanjut. Strukturnya saya ubah ke pola MVC (Model-View-Controller) supaya lebih rapi, dan saya tambahkan penerapan inheritance dengan membedakan pengajuan desain jadi dua jenis: **Desain Digital** (misalnya untuk Instagram Feed, Story, atau Web Banner) dan **Desain Cetak** (misalnya poster atau sertifikat fisik), karena keduanya butuh atribut data yang berbeda.

## Penjelasan Alur Program

Program dijalankan lewat class `Main` di package `cominfo`, yang membuat object `PengajuanController` dan `PengajuanView`, lalu memanggil `view.jalankan()`. Saat `PengajuanController` dibuat, method `muatDataAwal()` otomatis mengisi 2 data dummy ke ArrayList supaya program sudah ada data sejak awal dijalankan.

`PengajuanView.jalankan()` menampilkan menu utama dengan 5 pilihan (Tambah, Tampilkan, Update, Hapus, Keluar) dalam perulangan `while` yang baru berhenti kalau user pilih 5. Semua input user divalidasi lewat method bantu `bacaStringTidakKosong()` (menolak input kosong), `bacaInputAngka()` (menolak input non-angka), dan `bacaPilihanMenu()` (membatasi pilihan ke rentang angka valid), jadi program tidak akan crash walau user salah input.

Kalau user pilih **Tambah Pengajuan**, program minta ID (divalidasi harus positif dan belum dipakai), nama, departemen, catatan, dan deadline, lalu user memilih jenis desain lewat submenu: Digital atau Cetak. Untuk Digital, user memilih target platform dan format file lewat menu pilihan (bukan ketik manual); untuk Cetak, user memilih ukuran media dan memasukkan jumlah cetak (divalidasi harus lebih dari 0). Data yang terkumpul lalu dibungkus jadi object `PengajuanDigital` atau `PengajuanCetak`, dikirim ke `PengajuanController.simpanPengajuan()` untuk disimpan ke ArrayList.

Untuk **Tampilkan Pengajuan**, seluruh ArrayList ditelusuri dan tiap object memanggil method `tampilkanDetail()` miliknya sendiri. **Update** dan **Hapus** sama-sama minta ID, dicari lewat `cariBerdasarkanId()` di controller; kalau ID cocok, catatan revisi diperbarui atau datanya dihapus dari ArrayList, kalau tidak ketemu program kasih pesan "tidak ditemukan".

## Penjelasan Penerapan Encapsulation dan Inheritance

**Encapsulation** semua atribut di class `Pemohon`, `PengajuanDesain`, `PengajuanDigital`, dan `PengajuanCetak` dideklarasikan `private`, jadi tidak bisa diakses langsung dari luar class. Untuk mengambil atau mengubah nilainya harus lewat method `public` seperti getter/setter (`getNamaLengkap()`, `getIdPengajuan()`, `setStatus()`, dst).

<img width="622" height="513" alt="Screenshot 2026-09-23 155809" src="https://github.com/user-attachments/assets/e948f9a5-d287-45fb-b221-84bcc82a9937" />


**Inheritance** `PengajuanDesain` dibuat sebagai **superclass abstract** yang menyimpan atribut dan method umum (id, pemohon, catatan, deadline, status, `tampilkanDetail()`). Dua class turunannya, `PengajuanDigital` dan `PengajuanCetak`, memakai keyword `extends PengajuanDesain` dan memanggil `super(...)` di constructor-nya untuk mewariskan data dari superclass, lalu menambahkan atribut khusus masing-masing (target platform & format file untuk Digital; ukuran media & jumlah cetak untuk Cetak).

<img width="369" height="121" alt="Screenshot 2026-09-23 160510" src="https://github.com/user-attachments/assets/332d1967-abb7-42fb-be39-5f9b47211fd6" />

<img width="466" height="61" alt="image" src="https://github.com/user-attachments/assets/0146b6e2-e747-4144-84c4-39354614e97d" />

<img width="477" height="81" alt="image" src="https://github.com/user-attachments/assets/15aa94fd-b085-47e8-a0ba-adef39b3c889" />


## Penjelasan Letak Penerapan Nilai Tambah

**Struktur MVC** project dibagi jadi 4 package: `model` (`Pemohon`, `PengajuanDesain`, `PengajuanDigital`, `PengajuanCetak` — menyimpan struktur data), `view` (`PengajuanView` — menangani tampilan menu dan input/output ke user), `controller` (`PengajuanController` — mengelola ArrayList data dan logika penyimpanan/pencarian/penghapusan), dan `cominfo` (`Main` — entry point yang menghubungkan view dan controller).

<img width="351" height="199" alt="image" src="https://github.com/user-attachments/assets/4b371225-31c4-4ec8-8e44-9f825952536c" />

**Polymorphism (Method Overriding)** method `tampilkanDetail()` di superclass `PengajuanDesain` di-override di `PengajuanDigital` dan `PengajuanCetak` dengan anotasi `@Override`; masing-masing memanggil `super.tampilkanDetail()` dulu untuk menampilkan info umum, lalu menambahkan info khusus jenisnya (platform & format, atau ukuran & jumlah cetak).

<img width="586" height="146" alt="image" src="https://github.com/user-attachments/assets/01567dee-2d49-46fb-a36e-3a64c33e82fd" />

<img width="638" height="154" alt="image" src="https://github.com/user-attachments/assets/1905e9b3-c440-44fd-8587-5bed38037eb6" />


**Polymorphism (Method Overloading)** — di `PengajuanDesain.java` ada dua method `updateInformasi()` dengan parameter berbeda: satu hanya menerima `catatanRevisi`, satu lagi menerima `catatanRevisi` dan `status` sekaligus.

<img width="643" height="159" alt="image" src="https://github.com/user-attachments/assets/7a3e00de-aaf4-4f15-8fc9-a46b2ae3647b" />
