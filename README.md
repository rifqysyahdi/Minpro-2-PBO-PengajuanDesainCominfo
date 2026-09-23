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

![Encapsulation](/assets/encapsulation.png)

**Inheritance** `PengajuanDesain` dibuat sebagai **superclass abstract** yang menyimpan atribut dan method umum (id, pemohon, catatan, deadline, status, `tampilkanDetail()`). Dua class turunannya, `PengajuanDigital` dan `PengajuanCetak`, memakai keyword `extends PengajuanDesain` dan memanggil `super(...)` di constructor-nya untuk mewariskan data dari superclass, lalu menambahkan atribut khusus masing-masing (target platform & format file untuk Digital; ukuran media & jumlah cetak untuk Cetak).

![Inheritance-superclass](/assets/inheritance-superclass.png)

![Inheritance-subclass-digital](/assets/inheritance-subclass-digital.png)

![Inheritance-subclass-cetak](/assets/inheritance-subclass-cetak.png)


## Penjelasan Letak Penerapan Nilai Tambah

**Struktur MVC** project dibagi jadi 4 package: `model` (`Pemohon`, `PengajuanDesain`, `PengajuanDigital`, `PengajuanCetak` menyimpan struktur data), `view` (`PengajuanView` menangani tampilan menu dan input/output ke user), `controller` (`PengajuanController` mengelola ArrayList data dan logika penyimpanan/pencarian/penghapusan), dan `cominfo` (`Main` entry point yang menghubungkan view dan controller).

![MVC](/assets/mvc.png)

**Polymorphism (Method Overriding)** method `tampilkanDetail()` di superclass `PengajuanDesain` di-override di `PengajuanDigital` dan `PengajuanCetak` dengan anotasi `@Override`; masing-masing memanggil `super.tampilkanDetail()` dulu untuk menampilkan info umum, lalu menambahkan info khusus jenisnya (platform & format, atau ukuran & jumlah cetak).

![Overiding-digital](/assets/overide-digital.png)

![Overiding-cetak](/assets/overide-cetak.png)

**Polymorphism (Method Overloading)** di `PengajuanDesain.java` ada dua method `updateInformasi()` dengan parameter berbeda: satu hanya menerima `catatanRevisi`, satu lagi menerima `catatanRevisi` dan `status` sekaligus.

![Overloading](/assets/overloading.png)
