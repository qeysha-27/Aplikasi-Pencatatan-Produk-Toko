package kelas.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class DaftarController {

    @FXML
    private TableView<Produk> tabel;
    @FXML
    private TableColumn<Produk, String> gambarCol;
    @FXML
    private TableColumn<Produk, String> kodeCol;
    @FXML
    private TableColumn<Produk, String> namaCol;
    @FXML
    private TableColumn<Produk, String> kategoriCol;
    @FXML
    private TableColumn<Produk, Long> hargaCol;
    @FXML
    private TextField cariField;
    @FXML
    private ComboBox<String> filterKategoriBox;
    @FXML
    private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;
    private final ObservableList<Produk> dataTampilan = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // KOLOM GAMBAR PRODUK
        PropertyValueFactory<Produk, String> sumberGambar
                = new PropertyValueFactory<Produk, String>("gambarUri");
        gambarCol.setCellValueFactory(sumberGambar);
        gambarCol.setCellFactory(col -> new TableCell<Produk, String>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(75);
                imageView.setFitHeight(55);
                imageView.setPreserveRatio(true);
                setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(String uri, boolean empty) {
                super.updateItem(uri, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else if (uri != null && !uri.isBlank()) {
                    Image img = new Image(uri, 75, 55, true, true);
                    if (!img.isError()) {
                        imageView.setImage(img);
                        setGraphic(imageView);
                        setText(null);
                    } else {
                        setGraphic(null);
                        setText("Gagal muat");
                    }
                } else {
                    setGraphic(null);
                    setText("Tanpa gambar");
                }
            }
        });

        // KOLOM KODE PRODUK
        PropertyValueFactory<Produk, String> sumberKode
                = new PropertyValueFactory<Produk, String>("kode");
        kodeCol.setCellValueFactory(sumberKode);

        // KOLOM NAMA PRODUK
        PropertyValueFactory<Produk, String> sumberNama
                = new PropertyValueFactory<Produk, String>("nama");
        namaCol.setCellValueFactory(sumberNama);

        // KOLOM KATEGORI PRODUK
        PropertyValueFactory<Produk, String> sumberKategori
                = new PropertyValueFactory<Produk, String>("kategori");
        kategoriCol.setCellValueFactory(sumberKategori);

        // KOLOM HARGA PRODUK
        PropertyValueFactory<Produk, Long> sumberHarga
                = new PropertyValueFactory<Produk, Long>("harga");
        hargaCol.setCellValueFactory(sumberHarga);
        hargaCol.setCellFactory(col -> new TableCell<Produk, Long>() {
            private final NumberFormat rupiahFormat
                    = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));

            @Override
            protected void updateItem(Long harga, boolean empty) {
                super.updateItem(harga, empty);
                if (empty || harga == null) {
                    setText(null);
                } else {
                    setText(rupiahFormat.format(harga));
                }
            }
        });

        filterKategoriBox.getItems().addAll("Semua", "Makanan", "Minuman");
        filterKategoriBox.setValue("Semua");

        tabel.setItems(dataTampilan);
        tabel.setPlaceholder(new Label("Belum ada data produk yang sesuai."));
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
        cari();
    }

    @FXML
    private void cari() {
        if (data == null) {
            return;
        }
        dataTampilan.clear();
        String kata = cariField.getText().trim().toLowerCase(Locale.ROOT);
        String kategoriDipilih = filterKategoriBox.getValue();

        for (Produk produk : data.getDaftar()) {
            String nama = produk.getNama().toLowerCase(Locale.ROOT);
            String kode = produk.getKode().toLowerCase(Locale.ROOT);
            boolean cocokKata = nama.contains(kata) || kode.contains(kata);
            boolean cocokKategori = "Semua".equals(kategoriDipilih)
                    || produk.getKategori().equals(kategoriDipilih);

            if (cocokKata && cocokKategori) {
                dataTampilan.add(produk);
            }
        }
        tabel.sort();
        pesanLabel.setText("Jumlah produk yang tampil: " + dataTampilan.size());
    }

    @FXML
    private void resetPencarian() {
        cariField.clear();
        filterKategoriBox.setValue("Semua");
        cari();
    }

    @FXML
    private void tambah() {
        try {
            navigator.bukaForm();
        } catch (IOException e) {
            pesanLabel.setText("Form produk gagal dibuka.");
            e.printStackTrace();
        }
    }

    @FXML
    private void detail() {
        Produk produk = tabel.getSelectionModel().getSelectedItem();
        if (produk == null) {
            pesanLabel.setText("Pilih satu produk pada tabel terlebih dahulu.");
            return;
        }
        try {
            navigator.bukaDetail(produk);
        } catch (IOException e) {
            pesanLabel.setText("Halaman detail gagal dibuka.");
            e.printStackTrace();
        }
    }

    @FXML
    private void hapus() {
        Produk produk = tabel.getSelectionModel().getSelectedItem();
        if (produk == null) {
            pesanLabel.setText("Pilih satu produk pada tabel terlebih dahulu.");
            return;
        }
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION,
                "Hapus produk " + produk.getKode() + " (" + produk.getNama() + ")?",
                ButtonType.YES, ButtonType.NO);
        dialog.initOwner(tabel.getScene().getWindow());
        ButtonType jawaban = dialog.showAndWait().orElse(ButtonType.NO);
        if (jawaban == ButtonType.YES) {
            data.hapus(produk);
            cari();
        }
    }
}
