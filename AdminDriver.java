import java.util.ArrayList;

public class AdminDriver extends Driver {
    private ListBarang listBarang;
    private ArrayList<Transaksi> listTransaksi;

    public AdminDriver(Admin akun) {
        super(akun);
        this.listBarang = new ListBarang();
        this.listTransaksi = new ArrayList<>();
    }

    public ListBarang getListBarang() {
        return listBarang;
    }

    public ArrayList<Transaksi> getListTransaksi() {
        return listTransaksi;
    }

    @Override
    public void login() {
        System.out.println("Admin " + akun.getId() + " telah login.");
    }
}
