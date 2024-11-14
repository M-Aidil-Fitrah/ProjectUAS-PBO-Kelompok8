package models;

public class CustomerDriver extends Driver {
    private Transaksi transaksi;
    private ListBarang barang;

    public CustomerDriver(Customer customer, ListBarang barang) {
        super(customer);
        this.barang = barang;
    }

    public void tambahKeKeranjang(Barang item) {
        Customer customer = (Customer) getAkun();
        customer.getKeranjang().tambahBarang(item);
        System.out.println(item.getNama() + " berhasil ditambahkan ke keranjang.");
    }

    public void checkout() {
        Customer customer = (Customer) getAkun();
        transaksi = new Transaksi(customer, customer.getKeranjang().getBarangList());
        
        if (transaksi != null) {
            System.out.println("Checkout berhasil, menunggu persetujuan admin.");
            System.out.println("Transaksi untuk " + customer.getUsername() + " telah dibuat.");
        }
    }

    @Override
    public void manage() {
        System.out.println("Customer mengelola keranjang dan checkout.");
        
        if (barang != null) {
            System.out.println("Daftar barang yang dimiliki: " + barang.getBarangList());
        }
    }
}
