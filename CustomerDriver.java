public class CustomerDriver extends Driver {
    private Customer akun;
    private ListBarang listBarang;

    // Konstruktor
    public CustomerDriver(Customer akun) {
        super(akun); // Memanggil konstruktor parent (Driver)
        this.akun = akun;
        this.listBarang = new ListBarang(); // Membuat objek ListBarang baru
    }

    // Setter untuk listBarang
    public void setListBarang(ListBarang listBarang) {
        this.listBarang = listBarang;
    }

    // Menambah barang ke keranjang
    public void tambahBarangKeKeranjang(Barang barang) {
        akun.tambahKeKeranjang(barang); // Memanggil method tambahKeKeranjang di class Customer
    }

    // Proses checkout
    public void checkout() {
        // Membuat objek transaksi dan checkout barang yang ada di keranjang
        Transaksi transaksi = new Transaksi(akun, akun.getKeranjang().getBarangList());
        akun.checkout(transaksi); // Memanggil checkout pada objek akun
    }

    // Menampilkan daftar barang di listBarang
    public void tampilkanBarang() {
        System.out.println("Daftar Barang di List:");
        for (Barang barang : listBarang.getBarangList()) {
            System.out.println(barang.getNama() + " - Harga: " + barang.getHarga());
        }
    }

    // Menghapus barang dari listBarang berdasarkan ID
    public void hapusBarangDariList(String idBarang) {
        if (listBarang.hapusBarang(idBarang)) {
            System.out.println("Barang dengan ID " + idBarang + " berhasil dihapus.");
        } else {
            System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
        }
    }

    // Mengedit barang di listBarang berdasarkan ID
    public void editBarangDariList(String idBarang, Barang barangBaru) {
        if (listBarang.editBarang(idBarang, barangBaru)) {
            System.out.println("Barang dengan ID " + idBarang + " berhasil diubah.");
        } else {
            System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
        }
    }

    // Login untuk Customer
    @Override
    public void login() {
        System.out.println("Customer " + akun.getId() + " telah login.");
    }
}