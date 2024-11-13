public class QRIS extends Pembayaran {
    public QRIS(String id) {
        super(id);
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran melalui QRIS diproses.");
    }
}
