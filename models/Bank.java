package models;

public class Bank extends Pembayaran {
    public Bank(String id) {
        super(id);
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran via Bank berhasil.");
    }
}
