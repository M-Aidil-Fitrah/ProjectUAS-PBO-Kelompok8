package models;

public abstract class Driver {
    private Akun akun; // Mengubah tipe dari Customer ke Akun

    public Driver(Akun akun) {
        this.akun = akun;
    }

    // Getter untuk akun
    public Akun getAkun() {
        return akun;
    }

    // Abstract method manage yang akan diimplementasikan di subclass
    public abstract void manage();
}
