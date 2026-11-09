# 🛒 Aplikasi Pencatatan Produk Toko

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-007396?style=for-the-badge&logo=java&logoColor=white)
![NetBeans](https://img.shields.io/badge/NetBeans-1B6AC6?style=for-the-badge&logo=apache-netbeans-IDE&logoColor=white)

Aplikasi desktop berbasis **JavaFX** dan **FXML** yang digunakan untuk mengelola data produk toko secara interaktif. Aplikasi ini dibangun dengan pola arsitektur MVC (Model-View-Controller) tanpa menggunakan database persisten (menggunakan `ObservableList`).

---

## 📸 Tampilan Aplikasi

><img width="922" height="712" alt="Screenshot 2026-11-09 214047" src="https://github.com/user-attachments/assets/7e6c35cc-2228-4598-a5cf-60f5abfb8d89" />


---
## 📸 Dokumentasi & Alur Pengujian Aplikasi

### 1. Halaman Utama (Daftar Produk Toko)
Menampilkan daftar seluruh produk dalam tabel (`TableView`) yang mencakup Kode, Nama, Kategori, dan Harga Produk.
<img width="922" height="942" alt="Screenshot 2026-11-09 214213" src="https://github.com/user-attachments/assets/d9ca8a5b-e6eb-4079-9d20-fdc4d9923d4f" />

### 2. Form Tambah Produk Baru
Formulir interaktif untuk menginputkan produk baru beserta validasi input (nama tidak boleh kosong & harga > 0).
<img width="922" height="942" alt="Screenshot 2026-11-09 214159" src="https://github.com/user-attachments/assets/b0ddbfe8-c052-4692-b59d-d98aea098f51" />


### 3. Fitur Pencarian Produk
- **Pencarian Ditemukan:** Menampilkan data yang sesuai secara spesifik saat nama produk dicari.
 <img width="922" height="942" alt="Screenshot 2026-11-09 214448" src="https://github.com/user-attachments/assets/920e6967-7e5b-4bd3-a25d-6623ce26636e" />

- **Pencarian Tidak Ditemukan:** Menampilkan pesan status jika produk tidak ada dalam daftar.
<img width="922" height="942" alt="Screenshot 2026-11-09 214342" src="https://github.com/user-attachments/assets/26f6d086-5e15-4fcb-9aa7-d406d74e1fa8" />


### 4. Halaman Detail Produk (*Passing Object*)
Menampilkan rincian data produk yang dipilih dari tabel dengan menerima objek `Produk` secara dinamis.
<img width="922" height="942" alt="Screenshot 2026-11-09 214325" src="https://github.com/user-attachments/assets/9fd9dbb6-6a14-44f0-9c79-ad9887565d7f" />

---

## ✨ Fitur Utama

- **➕ Form Tambah Produk:** Input nama produk, kategori (Makanan/Minuman), dan harga.
- **🛡️ Validasi Data:** Sistem otomatis menolak input nama kosong atau harga $\le$ 0.
- **📊 Tabel Produk (`TableView`):** Menampilkan daftar kode produk, nama, kategori, dan harga.
- **🔍 Pencarian Real-time:** Pencarian produk berdasarkan nama melalui tombol Cari.
- **👁️ Halaman Detail Produk:** Menampilkan rincian data produk yang dipilih (*object passing*).

---

## 🛠️ Teknologi yang Digunakan

- **Bahasa Pemrograman:** Java (JDK 17/21)
- **GUI Framework:** JavaFX & FXML
- **GUI Builder:** Scene Builder
- **Build Tool:** Maven
- **IDE:** NetBeans IDE

---

## 🚀 Cara Menjalankan Project

1. **Clone Repositori:**
   ```bash
   git clone [https://github.com/qeysha-27/Aplikasi-Pencatatan-Produk-Toko.git](https://github.com/qeysha-27/Aplikasi-Pencatatan-Produk-Toko.git)
