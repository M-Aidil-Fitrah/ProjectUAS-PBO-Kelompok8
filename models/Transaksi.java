package models;

import payments.*;

import java.io.*;
import java.util.*;

public class Transaksi {
    private String idTransaksi;      // ID untuk transaksi
    private String username;         // Nama pengguna yang melakukan transaksi
    private int total;               // Total harga untuk transaksi
    private String status;           // Status dari transaksi (misalnya: PENDING, LUNAS)
    private Pembayaran pembayaran;   // Metode pembayaran yang digunakan
    private List<Barang> barangList; // Daftar barang yang dibeli dalam transaksi

    // Konstruktor untuk inisialisasi objek transaksi
    public Transaksi(String idTransaksi, String username, int total, String status, Pembayaran pembayaran, List<Barang> barangList) {
        this.idTransaksi = idTransaksi;
        this.username = username;
        this.total = total;
        this.status = status;
        this.pembayaran = pembayaran;
        this.barangList = barangList;
    }

    // Getter untuk idTransaksi
    public String getIdTransaksi() {
        return idTransaksi;
    }

    // Getter untuk username
    public String getUsername() {
        return username;
    }

    // Getter untuk total harga
    public int getTotal() {
        return total;
    }

    // Getter untuk status transaksi
    public String getStatus() {
        return status;
    }

    // Getter untuk objek pembayaran
    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    // Getter untuk daftar barang
    public List<Barang> getBarangList() {
        return barangList;
    }

    // Setter untuk status transaksi
    public void setStatus(String status) {
        this.status = status;
    }

    // Setter untuk objek pembayaran
    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    // Setter untuk daftar barang
    public void setBarangList(List<Barang> barangList) {
        this.barangList = barangList;
    }

    // Proses pembayaran menggunakan metode yang dipilih
    public String prosesPembayaran(Scanner scanner) {
        return pembayaran.prosesPembayaran(scanner);
    }

    // Menyimpan transaksi ke dalam file "transactions.txt"
    public void simpanTransaksiKeFile() {
        File file = new File("data/transactions.txt"); // Menentukan file tujuan
        List<String> allLines = new ArrayList<>(); // List untuk menyimpan semua baris file
        boolean updated = false;

        try {
            if (file.exists()) { // Mengecek jika file ada
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) { // Membaca setiap baris file
                        if (line.startsWith("idTransaksi=" + idTransaksi + ",")) { // Jika id transaksi sudah ada
                            line = formatTransaksi(); // Format transaksi baru
                            updated = true;
                        }
                        allLines.add(line); // Menambahkan baris ke list
                    }
                }
            }

            if (!updated) { // Jika transaksi belum ada, tambahkan ke list
                allLines.add(formatTransaksi());
            }

            // Menulis ulang data ke file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : allLines) {
                    writer.write(line);
                    writer.newLine(); // Menulis setiap baris ke file
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Tidak dapat menyimpan transaksi ke file."); // Menangani error saat menulis file
        }
    }

    // Format transaksi menjadi string untuk disimpan dalam file
    public String formatTransaksi() {
        StringBuilder sb = new StringBuilder();
        sb.append("idTransaksi=").append(idTransaksi)
        .append(",username=").append(username)
        .append(",total=").append(total)
        .append(",status=").append(status)
        .append(",pembayaran=").append(pembayaran.getClass().getSimpleName()); // Menyimpan nama kelas pembayaran
        if (barangList != null) {
            sb.append(",barang=");
            for (int i = 0; i < barangList.size(); i++) {
                Barang barang = barangList.get(i);
                sb.append(barang.getNama()).append(" (x").append(barang.getJumlahCheckout()).append(")"); // Menambahkan informasi barang
                if (i < barangList.size() - 1) sb.append(",");
            }
        }
        return sb.toString();
    }

    // Override toString untuk menampilkan informasi transaksi
    @Override
    public String toString() {
        StringBuilder barangStr = new StringBuilder();
        if (barangList != null) {
            for (Barang barang : barangList) {
                barangStr.append(barang.getNama()).append(" (x").append(barang.getJumlahCheckout()).append("), "); // Daftar barang
            }
        }

        return "Transaksi{" +
                "idTransaksi='" + idTransaksi + '\'' +
                ", username='" + username + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", pembayaran=" + pembayaran.getClass().getSimpleName() +
                ", barangList=" + barangStr +
                '}';
    }

    // Generate ID transaksi acak
    public static String generateRandomIdTransaksi() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Karakter yang bisa digunakan
        Random random = new Random();
        StringBuilder idTransaksi = new StringBuilder();

        for (int i = 0; i < 4; i++) { // Membuat ID acak 4 karakter
            int index = random.nextInt(characters.length());
            idTransaksi.append(characters.charAt(index));
        }
        return idTransaksi.toString();
    }

    // Memilih metode pembayaran berdasarkan input pengguna
    public static Pembayaran pilihPembayaran(Scanner scanner) {
        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. Bank");
        System.out.println("2. QRIS");
        System.out.println("3. COD");
        System.out.print("Masukkan pilihan (1/2/3): ");

        String input = scanner.nextLine();
        if (!input.matches("[123]")) { // Memastikan input valid
            System.out.println("Pilihan tidak valid!");
            return null;
        }

        int pilihan = Integer.parseInt(input);

        switch (pilihan) {
            case 1:
                return new Bank(); // Mengembalikan objek Bank
            case 2:
                return new QRIS(); // Mengembalikan objek QRIS
            case 3:
                return new COD(); // Mengembalikan objek COD
            default:
                return null;
        }
    }

    // Proses checkout dan pembelian barang
    public static void checkout(Customer customer, ListBarang listBarang, Scanner scanner) {
        if (customer.getKeranjang().isEmpty()) { // Jika keranjang kosong
            System.out.println("Keranjang kosong. Tidak ada barang untuk checkout.");
            return;
        }

        System.out.println("====  Daftar Barang di Keranjang  ====");
        for (Map.Entry<Barang, Integer> entry : customer.getKeranjang().getBarangKeranjang().entrySet()) { // Menampilkan barang di keranjang
            Barang barang = entry.getKey();
            int jumlah = entry.getValue();
            System.out.println("Barang: " + barang.getNama() + ", Jumlah: " + jumlah + ", Harga: Rp" + barang.getHarga() * jumlah);
        }
        System.out.println("======================================");

        Pembayaran pembayaran = pilihPembayaran(scanner); // Memilih metode pembayaran
        if (pembayaran == null) {
            System.out.println("Checkout dibatalkan. Metode pembayaran tidak valid.");
            return;
        }

        int totalHarga = customer.getKeranjang().totalHarga(); // Menghitung total harga
        pembayaran.setTotal(totalHarga); // Menetapkan total pada objek pembayaran

        System.out.println("Metode pembayaran yang dipilih: " + pembayaran.getClass().getSimpleName());
        String hasilPembayaran = pembayaran.prosesPembayaran(scanner); // Memproses pembayaran
        System.out.println(hasilPembayaran);

        String idTransaksi = generateRandomIdTransaksi(); // Membuat ID transaksi baru
        List<Barang> barangList = new ArrayList<>(customer.getKeranjang().getBarangKeranjang().keySet()); // Daftar barang yang dibeli

        Transaksi transaksi = new Transaksi(idTransaksi, customer.getUsername(), totalHarga, "PENDING", pembayaran, barangList); // Membuat objek transaksi
        transaksi.simpanTransaksiKeFile(); // Menyimpan transaksi ke file

        Invoice invoice = new Invoice(idTransaksi, totalHarga, "PENDING", pembayaran); // Membuat invoice
        customer.getInvoiceSelesai().add(invoice); // Menambahkan invoice ke daftar pelanggan

        System.out.println("Checkout berhasil. ID Transaksi: " + idTransaksi);

        // Memperbarui stok barang
        for (Barang barang : barangList) {
            int jumlahDibeli = customer.getKeranjang().getBarangKeranjang().get(barang);
            Barang stokBarang = listBarang.getBarangById(barang.getId());
            if (stokBarang != null) {
                if (stokBarang.getStok() >= jumlahDibeli) {
                    stokBarang.setStok(stokBarang.getStok() - jumlahDibeli); // Mengurangi stok
                } else {
                    System.out.println("Stok tidak mencukupi untuk barang: " + stokBarang.getNama());
                    return; 
                }
            } else {
                System.out.println("Barang dengan ID " + barang.getId() + " tidak ditemukan di daftar barang.");
            }
        }
        
        listBarang.simpanBarangKeFile();
        customer.getKeranjang().kosongkan();
    }
}
