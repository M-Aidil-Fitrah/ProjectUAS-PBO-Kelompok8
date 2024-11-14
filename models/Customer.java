package models;

import java.util.ArrayList;

public class Customer extends Akun {
    private Keranjang keranjang;
    private ArrayList<Invoice> invoiceSelesai;

    public Customer(String id, String username, String password) {
        super(id, username, password);
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<>();
    }

    // public void tambahKeKeranjang(Barang barang) {
    //    keranjang.tambahBarang(barang);
    // }

    // Menambahkan metode untuk menyelesaikan transaksi dan membuat invoice
    public void buatInvoice(Transaksi transaksi, Pembayaran pembayaran) {
        Invoice invoice = new Invoice(transaksi, pembayaran);
        invoiceSelesai.add(invoice);
        System.out.println("Invoice berhasil dibuat dan ditambahkan ke daftar invoice selesai.");
    }

    // Getter untuk keranjang
    public Keranjang getKeranjang() {
        return keranjang;
    }

    // Getter untuk daftar invoice selesai
    public ArrayList<Invoice> getInvoiceSelesai() {
        return invoiceSelesai;
    }

    @Override
    public String getRole() {
        return "Customer";
    }
}
