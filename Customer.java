import java.util.ArrayList;

public class Customer extends Akun {
    private Keranjang keranjang;
    private ArrayList<Invoice> invoiceSelesai;

    public Customer(String id) {
        super(id);
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<>();
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }

    public ArrayList<Invoice> getInvoiceSelesai() {
        return invoiceSelesai;
    }

    public void lihatBarang(ListBarang listBarang) {
        System.out.println("Daftar Barang:");
        for (Barang barang : listBarang.getBarangList()) {
            System.out.println(barang);
        }
    }

    public void tambahKeKeranjang(Barang barang) {
        keranjang.tambahBarang(barang);
        System.out.println("Barang berhasil ditambahkan ke keranjang.");
    }

    public void checkout(Transaksi transaksi) {
        System.out.println("Checkout berhasil, menunggu konfirmasi admin.");
        invoiceSelesai.add(new Invoice(transaksi, new COD("123")));
    }
}
