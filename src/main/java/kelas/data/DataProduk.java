package kelas.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kelas.model.Produk;

public class DataProduk {

    private final ObservableList<Produk> daftar = FXCollections.observableArrayList();
    private int urutan = 1;

    public ObservableList<Produk> getDaftar() {
        return FXCollections.unmodifiableObservableList(daftar);
    }

    public Produk tambah(String nama, String kategori, long harga, String gambarUri) {
        // PRD%03d digunakan untuk membuat kode produk otomatis, misalnya PRD001, PRD002, dst.
        String kode = String.format("PRD%03d", urutan);
        Produk p = new Produk(kode, nama, kategori, harga, gambarUri);
        daftar.add(p);
        urutan++;
        return p;
    }

    public void hapus(Produk p) {
        daftar.remove(p);
    }
}
