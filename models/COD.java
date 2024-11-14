package models;

public class COD extends Pembayaran {
    public COD(String id) {
        super(id);
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran via COD berhasil.");
    }
}
