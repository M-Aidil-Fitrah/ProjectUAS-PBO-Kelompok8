public abstract class Driver {
    protected Akun akun;

    public Driver(Akun akun) {
        this.akun = akun;
    }

    public abstract void login();
}
