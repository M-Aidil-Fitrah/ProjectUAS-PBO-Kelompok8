package models;

public abstract class Pembayaran {
    protected String id;

    public Pembayaran(String id) {
        this.id = id;
    }

    public abstract void prosesPembayaran();
}
