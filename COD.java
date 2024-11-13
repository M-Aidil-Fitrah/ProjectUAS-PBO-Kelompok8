public class COD extends Pembayaran {
    public COD(String id) {
        super(id);
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran melalui COD diproses.");
    }
}
